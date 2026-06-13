package compilador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Otimizador {

    private List<Instrucao> codigo;

    public Otimizador(List<Instrucao> codigo) {
        this.codigo = new ArrayList<>(codigo);
    }

    public List<Instrucao> otimizar() {
        boolean mudou = true;
        int seguranca = 0;
        while (mudou && seguranca++ < 100) {
            boolean a = dobramentoDeConstantes();
            boolean b = propagacaoDeConstantes();
            boolean c = reducaoDeForca();
            boolean d = eliminacaoDeCodigoMorto();
            mudou = a || b || c || d;
        }
        return codigo;
    }

    public List<Instrucao> getCodigo() { return codigo; }

    public void imprimirCodigo() {
        System.out.println("=== CODIGO INTERMEDIARIO OTIMIZADO (3AC) ===");
        for (Instrucao i : codigo) {
            System.out.println(i.tipo == Instrucao.Tipo.LABEL ? i.toString() : "    " + i);
        }
        System.out.println();
    }

    // 1. Dobramento de Constantes
    private boolean dobramentoDeConstantes() {
        boolean mudou = false;
        for (Instrucao i : codigo) {
            if (i.tipo == Instrucao.Tipo.BINARIA && ehInteiro(i.arg1) && ehInteiro(i.arg2)) {
                Integer r = avaliar(Integer.parseInt(i.arg1), i.op, Integer.parseInt(i.arg2));
                if (r != null) {
                    i.tipo = Instrucao.Tipo.COPIA;
                    i.arg1 = String.valueOf(r);
                    i.op = null;
                    i.arg2 = null;
                    mudou = true;
                }
            } else if (i.tipo == Instrucao.Tipo.UNARIA && "~".equals(i.op) && ehInteiro(i.arg1)) {
                int v = Integer.parseInt(i.arg1);
                i.tipo = Instrucao.Tipo.COPIA;
                i.arg1 = (v == 0) ? "1" : "0";   
                i.op = null;
                mudou = true;
            }
        }
        return mudou;
    }

    private Integer avaliar(int a, String op, int b) {
        switch (op) {
            case "+":  return a + b;
            case "-":  return a - b;
            case "*":  return a * b;
            case "/":  return (b != 0) ? a / b : null;  
            case "AND": return (a != 0 && b != 0) ? 1 : 0;
            case "OR":  return (a != 0 || b != 0) ? 1 : 0;
            case "<":  return a < b  ? 1 : 0;
            case "<=": return a <= b ? 1 : 0;
            case ">":  return a > b  ? 1 : 0;
            case ">=": return a >= b ? 1 : 0;
            case "==": return a == b ? 1 : 0;
            case "<>": return a != b ? 1 : 0;
            default:   return null;
        }
    }

    // 2. Propagação de Constantes
    //    Válida dentro de um bloco básico (reinicia em rótulos/desvios).
    private boolean propagacaoDeConstantes() {
        boolean mudou = false;
        Map<String, String> constantes = new HashMap<>();
        for (Instrucao i : codigo) {
            if (i.tipo == Instrucao.Tipo.LABEL
                    || i.tipo == Instrucao.Tipo.GOTO
                    || i.tipo == Instrucao.Tipo.IF_FALSE) {
                if (i.tipo == Instrucao.Tipo.IF_FALSE && constantes.containsKey(i.arg1)) {
                    i.arg1 = constantes.get(i.arg1);
                    mudou = true;
                }
                constantes.clear();
                continue;
            }

            // Substitui usos por suas constantes conhecidas.
            if (i.arg1 != null && constantes.containsKey(i.arg1)) {
                i.arg1 = constantes.get(i.arg1);
                mudou = true;
            }
            if (i.arg2 != null && constantes.containsKey(i.arg2)) {
                i.arg2 = constantes.get(i.arg2);
                mudou = true;
            }

            // Atualiza o conhecimento sobre o destino.
            if (i.dest != null) {
                if (i.tipo == Instrucao.Tipo.COPIA && ehInteiro(i.arg1)) {
                    constantes.put(i.dest, i.arg1);
                } else {
                    constantes.remove(i.dest);   
                }
            }
        }
        return mudou;
    }

    // 3. Redução de Força
    private boolean reducaoDeForca() {
        boolean mudou = false;
        for (Instrucao i : codigo) {
            if (i.tipo != Instrucao.Tipo.BINARIA || !"*".equals(i.op)) continue;

            String var = null, cte = null;
            if (!ehInteiro(i.arg1) && ehInteiro(i.arg2)) { var = i.arg1; cte = i.arg2; }
            else if (ehInteiro(i.arg1) && !ehInteiro(i.arg2)) { var = i.arg2; cte = i.arg1; }
            if (var == null) continue;

            int valor = Integer.parseInt(cte);
            int k = log2ExatoPositivo(valor);
            if (k == 0) {                      
                i.tipo = Instrucao.Tipo.COPIA;
                i.arg1 = var; i.op = null; i.arg2 = null;
                mudou = true;
            } else if (k > 0) {                
                i.arg1 = var;
                i.op = "<<";
                i.arg2 = String.valueOf(k);
                mudou = true;
            }
        }
        return mudou;
    }

    private int log2ExatoPositivo(int valor) {
        if (valor <= 0) return -1;
        if ((valor & (valor - 1)) != 0) return -1;  
        return Integer.numberOfTrailingZeros(valor);
    }

    // 4. Eliminação de Código Morto:
    private boolean eliminacaoDeCodigoMorto() {
        boolean mudou = false;

        for (Instrucao i : codigo) {
            if (i.tipo == Instrucao.Tipo.IF_FALSE && ehInteiro(i.arg1)) {
                if (Integer.parseInt(i.arg1) == 0) {   
                    i.tipo = Instrucao.Tipo.GOTO;
                    i.arg1 = null;
                } else {                                
                    i.tipo = null;                      
                }
                mudou = true;
            }
        }
        if (removerMarcadas()) mudou = true;

        // (b) Código inalcançável após GOTO/HALT (até o próximo rótulo).
        boolean alcancavel = true;
        for (Instrucao i : codigo) {
            if (i.tipo == Instrucao.Tipo.LABEL) {
                alcancavel = true;
            } else if (!alcancavel) {
                i.tipo = null;                          // inalcançável -> remove
                mudou = true;
            } else if (i.tipo == Instrucao.Tipo.GOTO || i.tipo == Instrucao.Tipo.HALT) {
                alcancavel = false;
            }
        }
        if (removerMarcadas()) mudou = true;

        // (c) Atribuições a temporárias jamais lidas.
        Set<String> usados = operandosUsados();
        for (Instrucao i : codigo) {
            boolean defineSemEfeito = (i.tipo == Instrucao.Tipo.COPIA
                    || i.tipo == Instrucao.Tipo.BINARIA
                    || i.tipo == Instrucao.Tipo.UNARIA);
            if (defineSemEfeito && i.dest != null && ehTemporaria(i.dest) && !usados.contains(i.dest)) {
                i.tipo = null;
                mudou = true;
            }
        }
        if (removerMarcadas()) mudou = true;

        return mudou;
    }

    private Set<String> operandosUsados() {
        Set<String> usados = new HashSet<>();
        for (Instrucao i : codigo) {
            if (i.arg1 != null && (i.tipo != Instrucao.Tipo.WRITE_CADEIA)) usados.add(i.arg1);
            if (i.arg2 != null) usados.add(i.arg2);
        }
        return usados;
    }

    private boolean removerMarcadas() {
        boolean removeu = codigo.removeIf(i -> i.tipo == null);
        return removeu;
    }

    // Auxiliares
    private boolean ehInteiro(String s) {
        if (s == null || s.isEmpty()) return false;
        return s.matches("-?\\d+");
    }

    private boolean ehTemporaria(String s) {
        return s != null && s.matches("t\\d+");
    }
}

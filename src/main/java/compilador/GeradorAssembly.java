package compilador;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Geração de Código Final (Assembly x86 - sintaxe Intel).
 *
 * Traduz a lista de instruções de três endereços (otimizadas) para Assembly
 * x86 de 16/32 bits. Variáveis INTEGER e temporárias são mapeadas como WORD
 * (diretiva {@code dw}, registradores AX/BX/...); variáveis BOOLEAN como BYTE
 * (diretiva {@code db}, registradores AL/BL/...), onde falso = 0 e verdadeiro = 1.
 *
 * O arquivo gerado contém a seção {@code .data} (reserva de memória de todas as
 * variáveis e temporárias) e a seção {@code .code} (instruções sequenciais).
 */
public class GeradorAssembly {

    private final List<Instrucao> codigo;
    private final Map<String, TipoVariavel> tipos;   // variáveis declaradas (nome -> tipo)
    private final StringBuilder data = new StringBuilder();
    private final StringBuilder code = new StringBuilder();
    private final Map<String, String> cadeias = new LinkedHashMap<>();  // texto -> rótulo
    private int contadorAux = 0;

    public GeradorAssembly(List<Instrucao> codigo, Map<String, TipoVariavel> tipos) {
        this.codigo = codigo;
        this.tipos = tipos;
    }

    public String gerar() {
        gerarSecaoData();
        gerarSecaoCode();

        StringBuilder asm = new StringBuilder();
        asm.append(".model small\n");
        asm.append(".stack 100h\n\n");
        asm.append(".data\n").append(data);
        asm.append("\n.code\n");
        asm.append("main proc\n");
        asm.append("    mov ax, @data\n");
        asm.append("    mov ds, ax\n\n");
        asm.append(code);
        asm.append("main endp\n");
        asm.append("end main\n");
        return asm.toString();
    }

    // ------------------------------------------------------------------
    // Seção .data
    // ------------------------------------------------------------------
    private void gerarSecaoData() {
        // Variáveis declaradas na Tabela de Símbolos.
        for (Map.Entry<String, TipoVariavel> e : tipos.entrySet()) {
            switch (e.getValue()) {
                case BOOLEAN -> data.append(String.format("    %-12s db 0%n", e.getKey()));
                case STRING  -> data.append(String.format("    %-12s db 64 dup(0)%n", e.getKey()));
                default      -> data.append(String.format("    %-12s dw 0%n", e.getKey()));
            }
        }
        // Temporárias geradas no código intermediário (sempre WORD).
        for (String t : coletarTemporarias()) {
            data.append(String.format("    %-12s dw 0%n", t));
        }
        // Cadeias literais usadas em WRITE.
        for (Instrucao i : codigo) {
            if (i.tipo == Instrucao.Tipo.WRITE_CADEIA && !cadeias.containsKey(i.arg1)) {
                String rotulo = "str" + cadeias.size();
                cadeias.put(i.arg1, rotulo);
                String texto = i.arg1.substring(1, i.arg1.length() - 1);  // remove aspas
                data.append(String.format("    %-12s db \"%s\", '$'%n", rotulo, texto));
            }
        }
    }

    private Set<String> coletarTemporarias() {
        Set<String> temps = new LinkedHashSet<>();
        for (Instrucao i : codigo) {
            adicionarSeTemp(temps, i.dest);
            adicionarSeTemp(temps, i.arg1);
            adicionarSeTemp(temps, i.arg2);
        }
        return temps;
    }

    private void adicionarSeTemp(Set<String> temps, String s) {
        if (s != null && s.matches("t\\d+")) temps.add(s);
    }

    // ------------------------------------------------------------------
    // Seção .code
    // ------------------------------------------------------------------
    private void gerarSecaoCode() {
        for (Instrucao i : codigo) {
            code.append("    ; ").append(i).append('\n');
            switch (i.tipo) {
                case COPIA        -> gerarCopia(i);
                case BINARIA      -> gerarBinaria(i);
                case UNARIA       -> gerarUnaria(i);
                case LABEL        -> code.append(i.alvo).append(":\n");
                case GOTO         -> emit("jmp " + i.alvo);
                case IF_FALSE     -> gerarIfFalse(i);
                case READ         -> gerarRead(i);
                case WRITE        -> gerarWrite(i);
                case WRITE_CADEIA -> gerarWriteCadeia(i);
                case HALT         -> { emit("mov ax, 4C00h"); emit("int 21h"); }
            }
            code.append('\n');
        }
    }

    private void gerarCopia(Instrucao i) {
        // Otimização local: cópia direta de imediato vai direto à memória.
        if (ehImediato(i.arg1)) {
            emit("mov " + tamPtr(i.dest) + " [" + i.dest + "], " + i.arg1);
        } else {
            carregarA(i.arg1);
            armazenarA(i.dest);
        }
    }

    private void gerarBinaria(Instrucao i) {
        switch (i.op) {
            case "+" -> { carregarA(i.arg1); carregarB(i.arg2); emit("add ax, bx"); armazenarA(i.dest); }
            case "-" -> { carregarA(i.arg1); carregarB(i.arg2); emit("sub ax, bx"); armazenarA(i.dest); }
            case "*" -> { carregarA(i.arg1); carregarB(i.arg2); emit("imul bx");     armazenarA(i.dest); }
            case "/" -> { carregarA(i.arg1); emit("cwd"); carregarB(i.arg2); emit("idiv bx"); armazenarA(i.dest); }
            case "<<" -> { carregarA(i.arg1); emit("shl ax, " + i.arg2); armazenarA(i.dest); }
            case "AND" -> { carregarA(i.arg1); carregarB(i.arg2); emit("and ax, bx"); armazenarA(i.dest); }
            case "OR"  -> { carregarA(i.arg1); carregarB(i.arg2); emit("or ax, bx");  armazenarA(i.dest); }
            case "<", "<=", ">", ">=", "==", "<>" -> gerarRelacional(i);
            default -> emit("; operador nao suportado: " + i.op);
        }
    }

    private void gerarRelacional(Instrucao i) {
        String jcc = switch (i.op) {
            case "<"  -> "jl";
            case "<=" -> "jle";
            case ">"  -> "jg";
            case ">=" -> "jge";
            case "==" -> "je";
            default   -> "jne";   // "<>"
        };
        String lTrue = "Lcmp" + contadorAux;
        String lFim  = "Lend" + contadorAux;
        contadorAux++;
        carregarA(i.arg1);
        carregarB(i.arg2);
        emit("cmp ax, bx");
        emit(jcc + " " + lTrue);
        emit("mov ax, 0");
        emit("jmp " + lFim);
        code.append(lTrue).append(":\n");
        emit("mov ax, 1");
        code.append(lFim).append(":\n");
        armazenarA(i.dest);
    }

    private void gerarUnaria(Instrucao i) {
        // Negação lógica (~) sobre booleano 0/1.
        carregarA(i.arg1);
        emit("xor ax, 1");
        armazenarA(i.dest);
    }

    private void gerarIfFalse(Instrucao i) {
        carregarA(i.arg1);
        emit("cmp ax, 0");
        emit("je " + i.alvo);
    }

    private void gerarRead(Instrucao i) {
        emit("call _read_integer");   // rotina externa: retorna inteiro em AX
        armazenarA(i.dest);
    }

    private void gerarWrite(Instrucao i) {
        carregarA(i.arg1);
        emit("push ax");
        emit("call _print_integer");
    }

    private void gerarWriteCadeia(Instrucao i) {
        String rotulo = cadeias.get(i.arg1);
        emit("lea dx, " + rotulo);
        emit("mov ah, 09h");
        emit("int 21h");
    }

    // ------------------------------------------------------------------
    // Carga / armazenamento, respeitando WORD (inteiros/temps) x BYTE (booleanos)
    // ------------------------------------------------------------------
    private void carregarA(String operando) { carregar("ax", "al", operando); }
    private void carregarB(String operando) { carregar("bx", "bl", operando); }

    private void carregar(String reg16, String reg8, String operando) {
        if (ehImediato(operando)) {
            emit("mov " + reg16 + ", " + operando);
        } else if (ehBooleano(operando)) {
            emit("xor " + reg16 + ", " + reg16);
            emit("mov " + reg8 + ", byte ptr [" + operando + "]");
        } else {
            emit("mov " + reg16 + ", word ptr [" + operando + "]");
        }
    }

    private void armazenarA(String dest) {
        if (ehBooleano(dest)) {
            emit("mov byte ptr [" + dest + "], al");
        } else {
            emit("mov word ptr [" + dest + "], ax");
        }
    }

    private String tamPtr(String nome) {
        return ehBooleano(nome) ? "byte ptr" : "word ptr";
    }

    private boolean ehBooleano(String nome) {
        return tipos.get(nome) == TipoVariavel.BOOLEAN;
    }

    private boolean ehImediato(String s) {
        return s != null && s.matches("-?\\d+");
    }

    private void emit(String instr) {
        code.append("    ").append(instr).append('\n');
    }
}

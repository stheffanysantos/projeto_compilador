package compilador;

import java.util.LinkedHashMap;
import java.util.Map;


public class TabelaSimbolos {

    private final Map<String, Simbolo> tabela = new LinkedHashMap<>();
    private final TabelaSimbolos pai;     
    private int proximoDeslocamento;      

    
    public TabelaSimbolos() {
        this.pai = null;
        this.proximoDeslocamento = 0;
    }

    public TabelaSimbolos(TabelaSimbolos pai) {
        this.pai = pai;
        this.proximoDeslocamento = (pai != null) ? pai.proximoDeslocamento : 0;
    }

    public TabelaSimbolos getPai() {
        return pai;
    }

    public void declarar(Simbolo simbolo) {
        String chave = simbolo.getNome().toLowerCase();
        if (tabela.containsKey(chave)) {
            throw new IllegalStateException(
                "Variável '" + simbolo.getNome() + "' já foi declarada na linha "
                    + tabela.get(chave).getLinhaDeclaracao() + ".");
        }
        simbolo.setDeslocamento(proximoDeslocamento);
        proximoDeslocamento += simbolo.tamanhoEmBytes();
        tabela.put(chave, simbolo);
    }

    public boolean existe(String nome) {
        return buscar(nome) != null;
    }

    public boolean existeNoEscopoAtual(String nome) {
        return tabela.containsKey(nome.toLowerCase());
    }

    public TipoVariavel buscarTipo(String nome) {
        Simbolo s = buscar(nome);
        return (s != null) ? s.getTipo() : TipoVariavel.DESCONHECIDO;
    }

    public Simbolo buscar(String nome) {
        String chave = nome.toLowerCase();
        TabelaSimbolos escopo = this;
        while (escopo != null) {
            Simbolo s = escopo.tabela.get(chave);
            if (s != null) return s;
            escopo = escopo.pai;
        }
        return null;
    }

    public Map<String, TipoVariavel> mapaTipos() {
        Map<String, TipoVariavel> mapa = new LinkedHashMap<>();
        tabela.values().forEach(s -> mapa.put(s.getNome(), s.getTipo()));
        return mapa;
    }

    public Iterable<Simbolo> simbolos() {
        return tabela.values();
    }

    public void imprimir() {
        System.out.println("TABELA DE SIMBOLOS");
        System.out.printf("%-20s %-10s %-10s %s%n", "VARIAVEL", "TIPO", "DESLOC", "LINHA DECL.");
        if (tabela.isEmpty()) {
            System.out.println("  (nenhuma variável declarada)");
        } else {
            tabela.values().forEach(System.out::println);
        }
        System.out.println();
    }

    public int tamanho() {
        return tabela.size();
    }
}

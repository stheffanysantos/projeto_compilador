package compilador;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Gerenciadora da Tabela de Símbolos.
 *
 * Implementada sobre uma Tabela Hash ({@link LinkedHashMap}). Para dar suporte
 * aos blocos delimitados por BEGIN/END, a estrutura trata escopos aninhados
 * através de um encadeamento de referências ao escopo pai ({@link #pai}).
 *
 * Cada entrada (ver {@link Simbolo}) contém: identificador (lexema), tipo
 * estático (INTEGER, BOOLEAN ou STRING) e deslocamento (posição em bytes no
 * frame da pilha, usada na geração de Assembly).
 */
public class TabelaSimbolos {

    private final Map<String, Simbolo> tabela = new LinkedHashMap<>();
    private final TabelaSimbolos pai;     // escopo pai (null no escopo global)
    private int proximoDeslocamento;      // deslocamento (bytes) a alocar para o próximo símbolo

    /** Cria o escopo global. */
    public TabelaSimbolos() {
        this.pai = null;
        this.proximoDeslocamento = 0;
    }

    /** Cria um escopo aninhado encadeado a um escopo pai. */
    public TabelaSimbolos(TabelaSimbolos pai) {
        this.pai = pai;
        this.proximoDeslocamento = (pai != null) ? pai.proximoDeslocamento : 0;
    }

    public TabelaSimbolos getPai() {
        return pai;
    }

    /**
     * Declara um símbolo no escopo atual, atribuindo-lhe um deslocamento.
     * Lança {@link IllegalStateException} se já existir no MESMO escopo.
     */
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

    /** Busca em todos os escopos visíveis (atual + ancestrais). */
    public boolean existe(String nome) {
        return buscar(nome) != null;
    }

    /** Busca apenas no escopo atual (usado para detectar redeclaração). */
    public boolean existeNoEscopoAtual(String nome) {
        return tabela.containsKey(nome.toLowerCase());
    }

    public TipoVariavel buscarTipo(String nome) {
        Simbolo s = buscar(nome);
        return (s != null) ? s.getTipo() : TipoVariavel.DESCONHECIDO;
    }

    /** Busca o símbolo subindo pela cadeia de escopos pais. */
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

    /** Mapa plano (nome -> tipo) dos símbolos do escopo atual. */
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

package compilador;
import java.util.LinkedHashMap;
import java.util.Map;

public class TabelaSimbolos {

    private final Map<String, Simbolo> tabela = new LinkedHashMap<>();

    public void declarar(Simbolo simbolo) {
        String chave = simbolo.getNome().toLowerCase();
        if (tabela.containsKey(chave)) {
            throw new IllegalStateException(
                "Variável '" + simbolo.getNome() + "' já foi declarada na linha " + tabela.get(chave).getLinhaDeclaracao() + "."
            );
        }
        tabela.put(chave, simbolo);
    }

    public boolean existe(String nome) {
        return tabela.containsKey(nome.toLowerCase());
    }

    public TipoVariavel buscarTipo(String nome) {
        Simbolo s = tabela.get(nome.toLowerCase());
        return (s != null) ? s.getTipo() : TipoVariavel.DESCONHECIDO;
    }

    public Simbolo buscar(String nome) {
        return tabela.get(nome.toLowerCase());
    }

    public void imprimir() {
        System.out.println("TABELA DE SIMBOLOS");
        System.out.printf("%-20s %-10s %s%n", "VARIAVEL", "TIPO", "LINHA DECL.");
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
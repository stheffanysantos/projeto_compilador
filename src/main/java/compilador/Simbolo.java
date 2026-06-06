package compilador;

public class Simbolo {

    private final String nome;
    private final TipoVariavel tipo;
    private final int linhaDeclaracao;

    public Simbolo(String nome, TipoVariavel tipo, int linhaDeclaracao) {
        this.nome = nome.toLowerCase();  // normalização para case insensitive
        this.tipo = tipo;
        this.linhaDeclaracao = linhaDeclaracao;
    }

    public String getNome() {
        return nome;
    }

    public TipoVariavel getTipo() {
        return tipo;
    }

    public int getLinhaDeclaracao() {
        return linhaDeclaracao;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-10s (linha %d)", nome, tipo, linhaDeclaracao);
    }
}
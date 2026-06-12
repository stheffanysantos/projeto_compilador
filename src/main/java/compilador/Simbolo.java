package compilador;

public class Simbolo {

    private final String nome;
    private final TipoVariavel tipo;
    private final int linhaDeclaracao;
    private int deslocamento;   // posição em bytes no frame da pilha (para a geração Assembly)

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

    public int getDeslocamento() {
        return deslocamento;
    }

    public void setDeslocamento(int deslocamento) {
        this.deslocamento = deslocamento;
    }

    /** Tamanho em bytes do tipo: INTEGER ocupa 2 bytes (WORD), BOOLEAN 1 byte. */
    public int tamanhoEmBytes() {
        return (tipo == TipoVariavel.BOOLEAN) ? 1 : 2;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-10s desloc=%-4d (linha %d)", nome, tipo, deslocamento, linhaDeclaracao);
    }
}

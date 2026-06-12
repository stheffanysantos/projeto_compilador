package compilador;

/**
 * Representa uma instrução de código de três endereços (3AC).
 *
 * Cada instrução possui no máximo três operandos. A estrutura é mantida de
 * forma "tuplada" (em vez de uma simples cadeia de texto) para que as fases
 * de otimização e de geração de código final possam inspecionar e reescrever
 * cada operando individualmente.
 */
public class Instrucao {

    public enum Tipo {
        COPIA,        // dest = arg1
        BINARIA,      // dest = arg1 <op> arg2
        UNARIA,       // dest = <op> arg1        (ex.: ~ para negação lógica)
        LABEL,        // alvo:
        GOTO,         // GOTO alvo
        IF_FALSE,     // IF arg1 == 0 GOTO alvo
        READ,         // READ dest
        WRITE,        // WRITE arg1
        WRITE_CADEIA, // WRITE "cadeia"
        HALT          // fim do programa
    }

    public Tipo tipo;
    public String op;    // operador para BINARIA/UNARIA ("+", "-", "*", "/", "AND", "OR", "<", ...)
    public String dest;  // destino (variável ou temporária)
    public String arg1;
    public String arg2;
    public String alvo;  // rótulo de desvio (GOTO/IF_FALSE) ou nome do rótulo (LABEL)

    private Instrucao(Tipo tipo) {
        this.tipo = tipo;
    }

    public static Instrucao copia(String dest, String arg1) {
        Instrucao i = new Instrucao(Tipo.COPIA);
        i.dest = dest;
        i.arg1 = arg1;
        return i;
    }

    public static Instrucao binaria(String dest, String arg1, String op, String arg2) {
        Instrucao i = new Instrucao(Tipo.BINARIA);
        i.dest = dest;
        i.arg1 = arg1;
        i.op = op;
        i.arg2 = arg2;
        return i;
    }

    public static Instrucao unaria(String dest, String op, String arg1) {
        Instrucao i = new Instrucao(Tipo.UNARIA);
        i.dest = dest;
        i.op = op;
        i.arg1 = arg1;
        return i;
    }

    public static Instrucao label(String alvo) {
        Instrucao i = new Instrucao(Tipo.LABEL);
        i.alvo = alvo;
        return i;
    }

    public static Instrucao goTo(String alvo) {
        Instrucao i = new Instrucao(Tipo.GOTO);
        i.alvo = alvo;
        return i;
    }

    public static Instrucao ifFalse(String arg1, String alvo) {
        Instrucao i = new Instrucao(Tipo.IF_FALSE);
        i.arg1 = arg1;
        i.alvo = alvo;
        return i;
    }

    public static Instrucao read(String dest) {
        Instrucao i = new Instrucao(Tipo.READ);
        i.dest = dest;
        return i;
    }

    public static Instrucao write(String arg1) {
        Instrucao i = new Instrucao(Tipo.WRITE);
        i.arg1 = arg1;
        return i;
    }

    public static Instrucao writeCadeia(String cadeia) {
        Instrucao i = new Instrucao(Tipo.WRITE_CADEIA);
        i.arg1 = cadeia;
        return i;
    }

    public static Instrucao halt() {
        return new Instrucao(Tipo.HALT);
    }

    @Override
    public String toString() {
        switch (tipo) {
            case COPIA:        return dest + " = " + arg1;
            case BINARIA:      return dest + " = " + arg1 + " " + op + " " + arg2;
            case UNARIA:       return dest + " = " + op + " " + arg1;
            case LABEL:        return alvo + ":";
            case GOTO:         return "GOTO " + alvo;
            case IF_FALSE:     return "IF " + arg1 + " == 0 GOTO " + alvo;
            case READ:         return "READ " + dest;
            case WRITE:        return "WRITE " + arg1;
            case WRITE_CADEIA: return "WRITE " + arg1;
            case HALT:         return "HALT";
            default:           return "";
        }
    }
}

package compilador;


public class Instrucao {

    public enum Tipo {
        COPIA,        
        BINARIA,      
        UNARIA,       
        LABEL,        
        GOTO,         
        IF_FALSE,     
        READ,         
        WRITE,        
        WRITE_CADEIA, 
        HALT          
    }

    public Tipo tipo;
    public String op;    
    public String dest;  
    public String arg1;
    public String arg2;
    public String alvo;  

    private Instrucao(Tipo tipo) {
        this.tipo = tipo;
    }

    public Instrucao copiar() {
        Instrucao c = new Instrucao(this.tipo);
        c.op = this.op;
        c.dest = this.dest;
        c.arg1 = this.arg1;
        c.arg2 = this.arg2;
        c.alvo = this.alvo;
        return c;
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

package compilador;

public enum TipoVariavel {
    INTEGER,
    BOOLEAN,
    STRING,
    DESCONHECIDO;

    public static TipoVariavel fromString(String texto) {
        return switch (texto.toUpperCase()) {
            case "INTEGER" -> INTEGER;
            case "BOOLEAN" -> BOOLEAN;
            case "STRING"  -> STRING;
            default        -> DESCONHECIDO;
        };
    }
}
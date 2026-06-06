package compilador;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.Token;

public class Main {
    public static void main(String[] args) throws Exception {

        CharStream entrada;
        if (args.length > 0) {
            entrada = CharStreams.fromFileName(args[0]);
        } else {
            entrada = CharStreams.fromStream(System.in);
        }

        // Análise Léxica
        System.out.println("\n ANALISE LEXICA");
        AnalisadorLexicoLexer lexer = new AnalisadorLexicoLexer(entrada);

        lexer.removeErrorListeners();
        lexer.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int linha, int coluna, String msg, RecognitionException e) {
                // Imprime o erro e interrompe
                System.err.printf("ERRO LEXICO [linha %d, coluna %d]: %s%n",
                        linha, coluna + 1, msg);
                throw new RuntimeException("Compilacao abortada por erro lexico.");
            }
        });

        CommonTokenStream tokens;
        try {
            tokens = new CommonTokenStream(lexer);
            tokens.fill();  // força o lexer a processar todos os tokens
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            System.exit(1);
            return;
        }

        System.out.printf("%-20s %-20s %s%n", "TIPO", "VALOR", "LINHA:COLUNA");
        System.out.println("-".repeat(60));

        for (Token token : tokens.getTokens()) {
            if (token.getType() == Token.EOF) continue;
            String nomeTipo = AnalisadorLexicoLexer.VOCABULARY.getSymbolicName(token.getType());
            System.out.printf("%-20s %-20s %d:%d%n",
                    nomeTipo,
                    token.getText(),
                    token.getLine(),
                    token.getCharPositionInLine() + 1
            );
        }

        System.out.println("\n[OK] Analise lexica concluida sem erros.");

        // Análise Sintática
        System.out.println("\n ANALISE SINTATICA");

        tokens.seek(0);  // reinicia o stream de tokens para o parser
        AnalisadorSintaticoParser parser = new AnalisadorSintaticoParser(tokens);

        // Captura e exibe erros sintáticos
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int linha, int coluna, String msg, RecognitionException e) {
                System.err.printf("ERRO SINTATICO [linha %d, coluna %d]: %s%n", linha, coluna + 1, msg);
                throw new RuntimeException("Compilacao abortada por erro sintatico.");
            }
        });

        ParseTree arvore;
        try {
            arvore = parser.programa();
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            System.exit(1);
            return;
        }

        if (parser.getNumberOfSyntaxErrors() > 0) {
            System.err.println("Analise semantica abortada por erros sintaticos.");
            System.exit(1);
            return;
        }

        // Imprime a árvore em notação LISP
        System.out.println("Arvore sintatica:");
        System.out.println(arvore.toStringTree(parser));
        System.out.println("\n[OK] Analise sintatica concluida sem erros.");

        // Análise Semântica
        System.out.println("\n ANALISE SEMANTICA");
        AnalisadorSemantico semantico = new AnalisadorSemantico();
        semantico.visit(arvore);
        semantico.imprimirTabelaSimbolos();

        if (semantico.getTotalErros() > 0) {
            System.err.printf("%n[ERRO] Analise semantica concluida com %d erro(s).%n", semantico.getTotalErros());
            System.err.println("Geracao de codigo abortada.");
            System.exit(1);
            return;
        }

        System.out.println("[OK] Analise semantica concluida sem erros.");

        // Geração de Código Intermediário
        System.out.println("\n GERACAO DE CODIGO INTERMEDIARIO");
        GeradorCodigo gerador = new GeradorCodigo();
        gerador.visit(arvore);
        gerador.imprimirCodigo();

        System.out.println("Compilacao concluida");
    }
}

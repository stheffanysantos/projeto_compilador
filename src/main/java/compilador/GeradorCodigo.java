package compilador;

import java.util.ArrayList;
import java.util.List;

import compilador.AnalisadorSintaticoParser;
import compilador.AnalisadorSintaticoParserBaseVisitor;

public class GeradorCodigo extends AnalisadorSintaticoParserBaseVisitor<String> {

    private final List<String> instrucoes = new ArrayList<>();
    private int contadorTemp = 0;
    private int contadorLabel = 0;

    // Métodos auxiliares
    private String novoTemp() {
        return "t" + contadorTemp++;
    }

    private String novoLabel() {
        return "L" + contadorLabel++;
    }

    private void emite(String instrucao) {
        instrucoes.add(instrucao);
    }

    private void emiteLabel(String label) {
        instrucoes.add(label + ":");
    }

    public List<String> getInstrucoes() {
        return List.copyOf(instrucoes);
    }

    public void imprimirCodigo() {
        System.out.println("=== CODIGO INTERMEDIARIO GERADO ===");
        instrucoes.forEach(System.out::println);
        System.out.println();
    }

    @Override
    public String visitPrograma(AnalisadorSintaticoParser.ProgramaContext ctx) {
        emite("; === Programa: " + ctx.ID().getText() + " ===");
        emite("");
        visit(ctx.declaracoes());
        emite("");
        visit(ctx.blocoComandos());
        emite("");
        emite("HALT");
        return null;
    }

    // Declarações

    @Override
    public String visitDeclaracoes(AnalisadorSintaticoParser.DeclaracoesContext ctx) {
        if (ctx.VAR() != null) {
            emite("; Declaracoes de variaveis");
            visit(ctx.listaDeclaracoes());
        }
        return null;
    }

    @Override
    public String visitListaDeclaracoes(
            AnalisadorSintaticoParser.ListaDeclaracoesContext ctx) {
        visit(ctx.declaracaoTipo());
        if (ctx.listaDeclaracoes() != null) {
            visit(ctx.listaDeclaracoes());
        }
        return null;
    }

    @Override
    public String visitDeclaracaoTipo(
            AnalisadorSintaticoParser.DeclaracaoTipoContext ctx) {
        String tipo = ctx.tipo().getText().toUpperCase();
        AnalisadorSintaticoParser.ListaIdsContext lista = ctx.listaIds();
        while (lista != null) {
            String nome = lista.ID().getText().toLowerCase();
            emite(" VAR " + nome + "; tipo: " + tipo);
            lista = lista.listaIds();
        }
        return null;
    }

    // Bloco de Comandos
    @Override
    public String visitBlocoComandos(AnalisadorSintaticoParser.BlocoComandosContext ctx) {
        visit(ctx.listaComandos());
        return null;
    }

    @Override
    public String visitListaComandos(AnalisadorSintaticoParser.ListaComandosContext ctx) {
        for (var cmd : ctx.comando()) {
            visit(cmd);
        }
        return null;
    }

    @Override
    public String visitComando(AnalisadorSintaticoParser.ComandoContext ctx) {
        return visitChildren(ctx);
    }

    // Atribuição: Id := expr
    @Override
    public String visitCmdAtribuicao(AnalisadorSintaticoParser.CmdAtribuicaoContext ctx) {
        String varNome = ctx.ID().getText().toLowerCase();
        String tempResultado = visit(ctx.expressao());
        emite(" STORE " + varNome + ", " + tempResultado);
        return null;
    }

    // Comando READ
    @Override
    public String visitCmdLeitura(AnalisadorSintaticoParser.CmdLeituraContext ctx) {
        emite(" ; READ");
        AnalisadorSintaticoParser.ListaIdsContext lista = ctx.listaIds();
        while (lista != null) {
            emite(" READ " + lista.ID().getText().toLowerCase());
            lista = lista.listaIds();
        }
        return null;
    }

    // Comando WRITE
    @Override
    public String visitCmdEscrita(AnalisadorSintaticoParser.CmdEscritaContext ctx) {
        emite(" ; WRITE");
        visit(ctx.listaEscrita());
        return null;
    }

    @Override
    public String visitListaEscrita(AnalisadorSintaticoParser.ListaEscritaContext ctx) {
        visit(ctx.itemEscrita());
        if (ctx.listaEscrita() != null) visit(ctx.listaEscrita());
        return null;
    }

    @Override
    public String visitItemEscrita(AnalisadorSintaticoParser.ItemEscritaContext ctx) {
        if (ctx.CADEIA() != null) {
            String temp = novoTemp();
            emite(" LOAD " + temp + ", " + ctx.CADEIA().getText());
            emite(" WRITE " + temp);
        } else {
            String temp = visit(ctx.expressao());
            emite(" WRITE " + temp);
        }
        return null;
    }

    // Comando if
    @Override
    public String visitCmdIf(AnalisadorSintaticoParser.CmdIfContext ctx) {
        String tempCond = visit(ctx.expressao());

        if (ctx.comando().size() == 2) {
            String labelElse = novoLabel();
            String labelFim  = novoLabel();

            emite(" ; IF-THEN-ELSE");
            emite(" JUMPF " + tempCond + ", " + labelElse);
            visit(ctx.comando(0));
            emite("    JUMP " + labelFim);
            emiteLabel(labelElse);
            visit(ctx.comando(1));
            emiteLabel(labelFim);
        } else {
            String labelFim = novoLabel();
            emite(" ; IF-THEN");
            emite(" JUMPF " + tempCond + ", " + labelFim);
            visit(ctx.comando(0));
            emiteLabel(labelFim);
        }

        return null;
    }

    // Comando WHILE
    @Override
    public String visitCmdWhile(AnalisadorSintaticoParser.CmdWhileContext ctx) {
        String labelInicio = novoLabel();
        String labelFim    = novoLabel();

        emite(" ; WHILE");
        emiteLabel(labelInicio);
        String tempCond = visit(ctx.expressao());
        emite(" JUMPF " + tempCond + ", " + labelFim);
        visit(ctx.comando());
        emite(" JUMP " + labelInicio);
        emiteLabel(labelFim);
        return null;
    }

    // Expressões
    @Override
    public String visitExpressao(AnalisadorSintaticoParser.ExpressaoContext ctx) {
        return visit(ctx.expressaoRelacional());
    }

    @Override
    public String visitExpressaoRelacional(AnalisadorSintaticoParser.ExpressaoRelacionalContext ctx) {
        String tempEsq = visit(ctx.expressaoAditiva(0));

        if (ctx.expressaoAditiva().size() > 1) {
            String tempDir = visit(ctx.expressaoAditiva(1));
            String temp    = novoTemp();

            String instrucao = switch (ctx.opRelacional().getText()) {
                case "<"  -> "LT";
                case "<=" -> "LE";
                case ">"  -> "GT";
                case ">=" -> "GE";
                case "==" -> "EQ";
                case "<>" -> "NE";
                default   -> "EQ";
            };
            emite("  " + instrucao + " " + temp + ", " + tempEsq + ", " + tempDir);
            return temp;
        }
        return tempEsq;
    }

    @Override
    public String visitExpressaoAditiva(AnalisadorSintaticoParser.ExpressaoAditivaContext ctx) {
        String resultado = visit(ctx.expressaoOr(0));

        for (int i = 1; i < ctx.expressaoOr().size(); i++) {
            String tempDir = visit(ctx.expressaoOr(i));
            String temp = novoTemp();

            String op = ctx.getChild(2 * i - 1).getText();
            emite("   " + (op.equals("+") ? "ADD" : "SUB")  + " " + temp + ", " + resultado + ", " + tempDir);
            resultado = temp;
        }
        return resultado;
    }

    @Override
    public String visitExpressaoOr(AnalisadorSintaticoParser.ExpressaoOrContext ctx) {
        String resultado = visit(ctx.expressaoAnd(0));

        for (int i = 1; i < ctx.expressaoAnd().size(); i++) {
            String tempDir = visit(ctx.expressaoAnd(i));
            String temp = novoTemp();

            emite("   OR " + temp + ", " + resultado + ", " + tempDir);
            resultado = temp;
        }
        return resultado;
    }

    @Override
    public String visitExpressaoAnd(AnalisadorSintaticoParser.ExpressaoAndContext ctx) {
        String resultado = visit(ctx.expressaoMultiplicativa(0));

        for (int i = 1; i < ctx.expressaoMultiplicativa().size(); i++) {
            String tempDir = visit(ctx.expressaoMultiplicativa(i));
            String temp = novoTemp();

            emite("   AND " + temp + ", " + resultado + ", " + tempDir);
            resultado = temp;
        }
        return resultado;
    }

    @Override
    public String visitExpressaoMultiplicativa(AnalisadorSintaticoParser.ExpressaoMultiplicativaContext ctx) {
        String resultado = visit(ctx.expressaoUnaria(0));

        for (int i = 1; i < ctx.expressaoUnaria().size(); i++) {
            String tempDir = visit(ctx.expressaoUnaria(i));
            String temp    = novoTemp();
            String op = ctx.getChild(2 * i - 1).getText();

            emite("    " + (op.equals("*") ? "MUL" : "DIV")
                    + " " + temp + ", " + resultado + ", " + tempDir);
            resultado = temp;
        }

        return resultado;
    }

    @Override
    public String visitExpressaoUnaria(AnalisadorSintaticoParser.ExpressaoUnariaContext ctx) {

        if (ctx.NEGACAO() != null) {
            String operando = visit(ctx.expressaoPrimaria());
            String temp = novoTemp();
            emite("    NOT " + temp + ", " + operando);
            return temp;
        }
        return visit(ctx.expressaoPrimaria());
    }

    @Override
    public String visitExpressaoPrimaria(AnalisadorSintaticoParser.ExpressaoPrimariaContext ctx) {

        if (ctx.ID() != null) {
            String temp = novoTemp();
            emite("    LOAD " + temp + ", " + ctx.ID().getText().toLowerCase());
            return temp;
        }

        if (ctx.CTE() != null) {
            String temp = novoTemp();
            emite("    LOAD " + temp + ", #" + ctx.CTE().getText());
            return temp;
        }

        if (ctx.TRUE() != null) {
            String temp = novoTemp();
            emite("    LOAD " + temp + ", #1   ; TRUE");
            return temp;
        }

        if (ctx.FALSE() != null) {
            String temp = novoTemp();
            emite("    LOAD " + temp + ", #0   ; FALSE");
            return temp;
        }

        if (ctx.expressao() != null) {
            return visit(ctx.expressao());
        }

        return null;
    }
}
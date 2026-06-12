package compilador;

import java.util.ArrayList;
import java.util.List;

/**
 * Geração de Código Intermediário (Código de Três Endereços - 3AC).
 *
 * Percorre a árvore sintática validada e a lineariza em uma lista de
 * {@link Instrucao}. Utiliza geradores de temporárias virtuais (t0, t1, ...) e
 * de rótulos simbólicos de desvio (L0, L1, ...), conforme a especificação.
 *
 * Os visitadores de expressão retornam diretamente o "endereço" do resultado:
 * o nome de uma temporária, de uma variável ou um literal imediato.
 */
public class GeradorCodigo extends AnalisadorSintaticoParserBaseVisitor<String> {

    private final List<Instrucao> instrucoes = new ArrayList<>();
    private int contadorTemp = 0;
    private int contadorLabel = 0;

    private String novoTemp()  { return "t" + contadorTemp++; }
    private String novoLabel() { return "L" + contadorLabel++; }
    private void emite(Instrucao i) { instrucoes.add(i); }

    public List<Instrucao> getInstrucoes() { return instrucoes; }

    public void imprimirCodigo() {
        System.out.println("=== CODIGO INTERMEDIARIO (3AC) ===");
        for (Instrucao i : instrucoes) {
            // Rótulos sem recuo; demais instruções com recuo.
            System.out.println(i.tipo == Instrucao.Tipo.LABEL ? i.toString() : "    " + i);
        }
        System.out.println();
    }

    @Override
    public String visitPrograma(AnalisadorSintaticoParser.ProgramaContext ctx) {
        visit(ctx.declaracoes());
        visit(ctx.blocoComandos());
        emite(Instrucao.halt());
        return null;
    }

    // ----- Declarações -----
    // Não geram código 3AC; a reserva de memória é feita na geração final.

    // ----- Bloco de Comandos -----
    @Override
    public String visitBlocoComandos(AnalisadorSintaticoParser.BlocoComandosContext ctx) {
        visit(ctx.listaComandos());
        return null;
    }

    @Override
    public String visitListaComandos(AnalisadorSintaticoParser.ListaComandosContext ctx) {
        for (var cmd : ctx.comando()) visit(cmd);
        return null;
    }

    @Override
    public String visitComando(AnalisadorSintaticoParser.ComandoContext ctx) {
        return visitChildren(ctx);
    }

    // ----- Atribuição: ID := expr -----
    @Override
    public String visitCmdAtribuicao(AnalisadorSintaticoParser.CmdAtribuicaoContext ctx) {
        String nome = ctx.ID().getText().toLowerCase();
        String resultado = visit(ctx.expressao());
        emite(Instrucao.copia(nome, resultado));
        return null;
    }

    // ----- READ -----
    @Override
    public String visitCmdLeitura(AnalisadorSintaticoParser.CmdLeituraContext ctx) {
        AnalisadorSintaticoParser.ListaIdsContext lista = ctx.listaIds();
        while (lista != null) {
            emite(Instrucao.read(lista.ID().getText().toLowerCase()));
            lista = lista.listaIds();
        }
        return null;
    }

    // ----- WRITE -----
    @Override
    public String visitCmdEscrita(AnalisadorSintaticoParser.CmdEscritaContext ctx) {
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
            emite(Instrucao.writeCadeia(ctx.CADEIA().getText()));
        } else {
            String temp = visit(ctx.expressao());
            emite(Instrucao.write(temp));
        }
        return null;
    }

    // ----- IF / IF-ELSE -----
    @Override
    public String visitCmdIf(AnalisadorSintaticoParser.CmdIfContext ctx) {
        String cond = visit(ctx.expressao());

        if (ctx.comando().size() == 2) {       // IF-THEN-ELSE
            String labelElse = novoLabel();
            String labelFim  = novoLabel();
            emite(Instrucao.ifFalse(cond, labelElse));
            visit(ctx.comando(0));
            emite(Instrucao.goTo(labelFim));
            emite(Instrucao.label(labelElse));
            visit(ctx.comando(1));
            emite(Instrucao.label(labelFim));
        } else {                                // IF-THEN
            String labelFim = novoLabel();
            emite(Instrucao.ifFalse(cond, labelFim));
            visit(ctx.comando(0));
            emite(Instrucao.label(labelFim));
        }
        return null;
    }

    // ----- WHILE -----
    @Override
    public String visitCmdWhile(AnalisadorSintaticoParser.CmdWhileContext ctx) {
        String labelInicio = novoLabel();
        String labelFim    = novoLabel();
        emite(Instrucao.label(labelInicio));
        String cond = visit(ctx.expressao());
        emite(Instrucao.ifFalse(cond, labelFim));
        visit(ctx.comando());
        emite(Instrucao.goTo(labelInicio));
        emite(Instrucao.label(labelFim));
        return null;
    }

    // ----- Expressões -----
    @Override
    public String visitExpressao(AnalisadorSintaticoParser.ExpressaoContext ctx) {
        return visit(ctx.expressaoRelacional());
    }

    @Override
    public String visitExpressaoRelacional(AnalisadorSintaticoParser.ExpressaoRelacionalContext ctx) {
        String esq = visit(ctx.expressaoAditiva(0));
        if (ctx.expressaoAditiva().size() > 1) {
            String dir = visit(ctx.expressaoAditiva(1));
            String temp = novoTemp();
            emite(Instrucao.binaria(temp, esq, ctx.opRelacional().getText(), dir));
            return temp;
        }
        return esq;
    }

    @Override
    public String visitExpressaoAditiva(AnalisadorSintaticoParser.ExpressaoAditivaContext ctx) {
        String resultado = visit(ctx.expressaoOr(0));
        for (int i = 1; i < ctx.expressaoOr().size(); i++) {
            String dir = visit(ctx.expressaoOr(i));
            String op  = ctx.getChild(2 * i - 1).getText();   // "+" ou "-"
            String temp = novoTemp();
            emite(Instrucao.binaria(temp, resultado, op, dir));
            resultado = temp;
        }
        return resultado;
    }

    @Override
    public String visitExpressaoOr(AnalisadorSintaticoParser.ExpressaoOrContext ctx) {
        String resultado = visit(ctx.expressaoAnd(0));
        for (int i = 1; i < ctx.expressaoAnd().size(); i++) {
            String dir = visit(ctx.expressaoAnd(i));
            String temp = novoTemp();
            emite(Instrucao.binaria(temp, resultado, "OR", dir));
            resultado = temp;
        }
        return resultado;
    }

    @Override
    public String visitExpressaoAnd(AnalisadorSintaticoParser.ExpressaoAndContext ctx) {
        String resultado = visit(ctx.expressaoMultiplicativa(0));
        for (int i = 1; i < ctx.expressaoMultiplicativa().size(); i++) {
            String dir = visit(ctx.expressaoMultiplicativa(i));
            String temp = novoTemp();
            emite(Instrucao.binaria(temp, resultado, "AND", dir));
            resultado = temp;
        }
        return resultado;
    }

    @Override
    public String visitExpressaoMultiplicativa(AnalisadorSintaticoParser.ExpressaoMultiplicativaContext ctx) {
        String resultado = visit(ctx.expressaoUnaria(0));
        for (int i = 1; i < ctx.expressaoUnaria().size(); i++) {
            String dir = visit(ctx.expressaoUnaria(i));
            String op  = ctx.getChild(2 * i - 1).getText();   // "*" ou "/"
            String temp = novoTemp();
            emite(Instrucao.binaria(temp, resultado, op, dir));
            resultado = temp;
        }
        return resultado;
    }

    @Override
    public String visitExpressaoUnaria(AnalisadorSintaticoParser.ExpressaoUnariaContext ctx) {
        if (ctx.NEGACAO() != null) {
            String operando = visit(ctx.expressaoPrimaria());
            String temp = novoTemp();
            emite(Instrucao.unaria(temp, "~", operando));
            return temp;
        }
        return visit(ctx.expressaoPrimaria());
    }

    @Override
    public String visitExpressaoPrimaria(AnalisadorSintaticoParser.ExpressaoPrimariaContext ctx) {
        if (ctx.ID()   != null) return ctx.ID().getText().toLowerCase();
        if (ctx.CTE()  != null) return ctx.CTE().getText();
        if (ctx.TRUE() != null) return "1";   // verdadeiro mapeia para 1
        if (ctx.FALSE()!= null) return "0";   // falso mapeia para 0
        if (ctx.expressao() != null) return visit(ctx.expressao());
        return null;
    }
}

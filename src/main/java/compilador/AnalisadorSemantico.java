package compilador;
import compilador.AnalisadorSintaticoParser;
import compilador.AnalisadorSintaticoParserBaseVisitor;

import org.antlr.v4.runtime.Token;

public class AnalisadorSemantico extends AnalisadorSintaticoParserBaseVisitor<TipoVariavel> {

    // Limites físicos de uma constante inteira (2 bytes com sinal).
    private static final int CTE_MIN = -32768;
    private static final int CTE_MAX = 32767;

    private final TabelaSimbolos tabelaGlobal = new TabelaSimbolos();
    private TabelaSimbolos escopoAtual = tabelaGlobal;  // escopo corrente (suporta aninhamento)
    private int totalErros = 0;

    // Métodos auxiliares
    private void erroSemantico(Token token, String mensagem) {
        totalErros++;
        System.err.printf("ERRO SEMANTICO [linha %d, coluna %d]: %s%n", token.getLine(), token.getCharPositionInLine() + 1, mensagem);
    }

    //Versão sem token
    private void erroSemantico(String mensagem) {
        totalErros++;
        System.err.println("ERRO SEMANTICO: " + mensagem);
    }

    public int getTotalErros() { return totalErros; }
    public void imprimirTabelaSimbolos() { tabelaGlobal.imprimir(); }

    /** Mapa (nome -> tipo) das variáveis globais, consumido pelo gerador de Assembly. */
    public java.util.Map<String, TipoVariavel> getMapaTipos() { return tabelaGlobal.mapaTipos(); }

    @Override
    public TipoVariavel visitPrograma(AnalisadorSintaticoParser.ProgramaContext ctx) {
        return visitChildren(ctx);
    }

    // Declarações
    @Override
    public TipoVariavel visitDeclaracaoTipo(AnalisadorSintaticoParser.DeclaracaoTipoContext ctx) {
        TipoVariavel tipo = TipoVariavel.fromString(ctx.tipo().getText());

        AnalisadorSintaticoParser.ListaIdsContext lista = ctx.listaIds();
        while (lista != null) {
            Token tokenId = lista.ID().getSymbol();
            String nomeVar = tokenId.getText().toLowerCase();

            try {
                escopoAtual.declarar(new Simbolo(nomeVar, tipo, tokenId.getLine()));
            } catch (IllegalStateException e) {
                erroSemantico(tokenId, "variável '" + nomeVar + "' já foi declarada.");
            }
            // Avança para o próximo Id da lista
            lista = lista.listaIds();
        }
        return null;
    }

    // Comando de Atribuição
    @Override
    public TipoVariavel visitCmdAtribuicao(AnalisadorSintaticoParser.CmdAtribuicaoContext ctx) {
        Token tokenId = ctx.ID().getSymbol();
        String nomeVar = tokenId.getText().toLowerCase();

        if (!escopoAtual.existe(nomeVar)) {
            erroSemantico(tokenId, "variável '" + nomeVar + "' não foi declarada.");
            return null;
        }

        TipoVariavel tipoVar  = escopoAtual.buscarTipo(nomeVar);
        TipoVariavel tipoExpr = visit(ctx.expressao());

        // Verifica compatibilidade de tipos
        if (tipoExpr != null
                && tipoExpr != TipoVariavel.DESCONHECIDO
                && tipoVar  != TipoVariavel.DESCONHECIDO
                && !tipoVar.equals(tipoExpr)) {
            erroSemantico(tokenId,"tipo incompatível na atribuição a '" + nomeVar + "': esperado " + tipoVar + ", encontrado " + tipoExpr + ".");
        }
        return null;
    }

    // Bloco BEGIN/END: abre um escopo aninhado encadeado ao escopo pai.
    @Override
    public TipoVariavel visitBlocoComandos(AnalisadorSintaticoParser.BlocoComandosContext ctx) {
        TabelaSimbolos anterior = escopoAtual;
        escopoAtual = new TabelaSimbolos(anterior);   // encadeamento de referência (escopo pai)
        visitChildren(ctx);
        escopoAtual = anterior;                        // ao sair do bloco, restaura o escopo pai
        return null;
    }

    // Comando If
    @Override
    public TipoVariavel visitCmdIf(AnalisadorSintaticoParser.CmdIfContext ctx) {
        TipoVariavel tipoCond = visit(ctx.expressao());

        if (tipoCond != null
                && tipoCond != TipoVariavel.DESCONHECIDO
                && tipoCond != TipoVariavel.BOOLEAN) {
            erroSemantico("condição do IF deve ser BOOLEAN, encontrado " + tipoCond + ".");
        }
        for (var cmd : ctx.comando()) {
            visit(cmd);
        }
        return null;
    }

    // Comando WHILE
    @Override
    public TipoVariavel visitCmdWhile(AnalisadorSintaticoParser.CmdWhileContext ctx) {
        TipoVariavel tipoCond = visit(ctx.expressao());

        if (tipoCond != null
                && tipoCond != TipoVariavel.DESCONHECIDO
                && tipoCond != TipoVariavel.BOOLEAN) {
            erroSemantico("condição do WHILE deve ser BOOLEAN, encontrado " + tipoCond + ".");
        }
        visit(ctx.comando());
        return null;
    }

    // Comando READ
    @Override
    public TipoVariavel visitCmdLeitura(AnalisadorSintaticoParser.CmdLeituraContext ctx) {
        AnalisadorSintaticoParser.ListaIdsContext lista = ctx.listaIds();
        while (lista != null) {
            Token tokenId = lista.ID().getSymbol();
            String nomeVar = tokenId.getText().toLowerCase();
            if (!escopoAtual.existe(nomeVar)) {
                erroSemantico(tokenId, "variável '" + nomeVar + "' não foi declarada.");
            }
            lista = lista.listaIds();
        }
        return null;
    }

    // Expressões
    @Override
    public TipoVariavel visitExpressao(AnalisadorSintaticoParser.ExpressaoContext ctx) {
        return visit(ctx.expressaoRelacional());
    }

    @Override
    public TipoVariavel visitExpressaoRelacional(AnalisadorSintaticoParser.ExpressaoRelacionalContext ctx) {
        TipoVariavel tipoEsq = visit(ctx.expressaoAditiva(0));

        // Se há operador relacional (lista tem 2 expressões aditivas)
        if (ctx.expressaoAditiva().size() > 1) {
            TipoVariavel tipoDir = visit(ctx.expressaoAditiva(1));

            // Os dois lados devem ter o mesmo tipo
            if (tipoEsq != null && tipoDir != null
                    && tipoEsq != TipoVariavel.DESCONHECIDO
                    && tipoDir != TipoVariavel.DESCONHECIDO
                    && !tipoEsq.equals(tipoDir)) {
                erroSemantico("tipos incompatíveis na comparação: " + tipoEsq + " vs " + tipoDir + ".");
            }
            return TipoVariavel.BOOLEAN;
        }
        return tipoEsq;
    }

    @Override
    public TipoVariavel visitExpressaoAditiva(AnalisadorSintaticoParser.ExpressaoAditivaContext ctx) {
        TipoVariavel tipo = visit(ctx.expressaoOr(0));

        for (int i = 1; i < ctx.expressaoOr().size(); i++) {
            TipoVariavel tipoDir = visit(ctx.expressaoOr(i));

            Token op = (ctx.MAIS(i - 1) != null)
                    ? ctx.MAIS(i - 1).getSymbol()
                    : ctx.MENOS(i - 1).getSymbol();

            if (tipo != TipoVariavel.INTEGER || tipoDir != TipoVariavel.INTEGER) {
                erroSemantico(op, "operador '+/-' exige INTEGER, encontrado " + tipo + " e " + tipoDir);
                return TipoVariavel.DESCONHECIDO;
            }
            tipo = TipoVariavel.INTEGER;
        }
        return tipo;
    }

    @Override
    public TipoVariavel visitExpressaoOr(AnalisadorSintaticoParser.ExpressaoOrContext ctx) {
        TipoVariavel tipo = visit(ctx.expressaoAnd(0));

        for (int i = 1; i < ctx.expressaoAnd().size(); i++) {
            TipoVariavel tipoDir = visit(ctx.expressaoAnd(i));

            if (tipo != TipoVariavel.BOOLEAN || tipoDir != TipoVariavel.BOOLEAN) {
                erroSemantico("OR exige BOOLEAN, encontrado " + tipo + " e " + tipoDir);
                return TipoVariavel.DESCONHECIDO;
            }
            tipo = TipoVariavel.BOOLEAN;
        }
        return tipo;
    }

    @Override
    public TipoVariavel visitExpressaoAnd(AnalisadorSintaticoParser.ExpressaoAndContext ctx) {
        TipoVariavel tipo = visit(ctx.expressaoMultiplicativa(0));

        for (int i = 1; i < ctx.expressaoMultiplicativa().size(); i++) {
            TipoVariavel tipoDir = visit(ctx.expressaoMultiplicativa(i));

            if (tipo != TipoVariavel.BOOLEAN || tipoDir != TipoVariavel.BOOLEAN) {
                erroSemantico(
                        "AND exige BOOLEAN, encontrado "
                                + tipo + " e " + tipoDir
                );
                return TipoVariavel.DESCONHECIDO;
            }
            tipo = TipoVariavel.BOOLEAN;
        }
        return tipo;
    }

    @Override
    public TipoVariavel visitExpressaoMultiplicativa(AnalisadorSintaticoParser.ExpressaoMultiplicativaContext ctx) {
        TipoVariavel tipo = visit(ctx.expressaoUnaria(0));

        for (int i = 1; i < ctx.expressaoUnaria().size(); i++) {
            TipoVariavel tipoDir = visit(ctx.expressaoUnaria(i));

            if (tipo != TipoVariavel.INTEGER || tipoDir != TipoVariavel.INTEGER) {
                erroSemantico("*// exige INTEGER, encontrado " + tipo + " e " + tipoDir);
                return TipoVariavel.DESCONHECIDO;
            }

            tipo = TipoVariavel.INTEGER;
        }
        return tipo;
    }

    @Override
    public TipoVariavel visitExpressaoUnaria(AnalisadorSintaticoParser.ExpressaoUnariaContext ctx) {

        if (ctx.NEGACAO() != null) {
            TipoVariavel tipo = visit(ctx.expressaoPrimaria());
            if (tipo != null && tipo != TipoVariavel.DESCONHECIDO
                    && tipo != TipoVariavel.BOOLEAN) {
                erroSemantico("operador de negação (~) exige BOOLEAN, encontrado " + tipo + ".");
            }
            return TipoVariavel.BOOLEAN;
        }
        return visit(ctx.expressaoPrimaria());
    }

    @Override
    public TipoVariavel visitExpressaoPrimaria(AnalisadorSintaticoParser.ExpressaoPrimariaContext ctx) {

        if (ctx.ID() != null) {
            Token tokenId = ctx.ID().getSymbol();
            String nomeVar = tokenId.getText().toLowerCase();

            if (!escopoAtual.existe(nomeVar)) {
                erroSemantico(tokenId, "variável '" + nomeVar + "' não foi declarada.");
                return TipoVariavel.DESCONHECIDO;
            }
            return escopoAtual.buscarTipo(nomeVar);
        }

        if (ctx.CTE() != null) {
            verificarLimiteConstante(ctx.CTE().getSymbol());
            return TipoVariavel.INTEGER;
        }
        if (ctx.TRUE() != null) return TipoVariavel.BOOLEAN;
        if (ctx.FALSE() != null) return TipoVariavel.BOOLEAN;
        if (ctx.expressao() != null) return visit(ctx.expressao());
        return TipoVariavel.DESCONHECIDO;
    }

    /**
     * Verificação de limites de constantes inteiras (Overflow de Constante).
     * Converte o lexema para numérico e aplica a restrição física de 2 bytes
     * com sinal: -32768 ≤ valor ≤ 32767. Fora deste intervalo é erro fatal.
     */
    private void verificarLimiteConstante(Token tokenCte) {
        long valor;
        try {
            valor = Long.parseLong(tokenCte.getText());
        } catch (NumberFormatException e) {
            valor = Long.MAX_VALUE;  // excede qualquer limite
        }
        if (valor < CTE_MIN || valor > CTE_MAX) {
            erroSemantico(tokenCte, "Overflow de Constante: valor " + tokenCte.getText()
                    + " fora do intervalo permitido [" + CTE_MIN + ", " + CTE_MAX + "].");
        }
    }

    // Auxiliar de verificação de tipo de operando
    private void verificarTipoOperando(TipoVariavel tipoAtual, TipoVariavel tipoEsperado, String operador) {
        if (tipoAtual != null && tipoAtual != TipoVariavel.DESCONHECIDO && !tipoAtual.equals(tipoEsperado)) {
            erroSemantico("operador '" + operador + "' exige "
                    + tipoEsperado + ", encontrado " + tipoAtual + "."
            );
        }
    }
}
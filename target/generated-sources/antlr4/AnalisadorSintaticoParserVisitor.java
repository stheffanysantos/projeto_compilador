// Generated from AnalisadorSintaticoParser.g4 by ANTLR 4.13.1

package compilador;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AnalisadorSintaticoParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AnalisadorSintaticoParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AnalisadorSintaticoParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(AnalisadorSintaticoParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalisadorSintaticoParser#declaracoes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracoes(AnalisadorSintaticoParser.DeclaracoesContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalisadorSintaticoParser#listaDeclaracoes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListaDeclaracoes(AnalisadorSintaticoParser.ListaDeclaracoesContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalisadorSintaticoParser#declaracaoTipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracaoTipo(AnalisadorSintaticoParser.DeclaracaoTipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalisadorSintaticoParser#listaIds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListaIds(AnalisadorSintaticoParser.ListaIdsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalisadorSintaticoParser#tipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo(AnalisadorSintaticoParser.TipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalisadorSintaticoParser#blocoComandos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlocoComandos(AnalisadorSintaticoParser.BlocoComandosContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalisadorSintaticoParser#listaComandos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListaComandos(AnalisadorSintaticoParser.ListaComandosContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalisadorSintaticoParser#comando}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComando(AnalisadorSintaticoParser.ComandoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalisadorSintaticoParser#cmdIf}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdIf(AnalisadorSintaticoParser.CmdIfContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalisadorSintaticoParser#cmdWhile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdWhile(AnalisadorSintaticoParser.CmdWhileContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalisadorSintaticoParser#cmdLeitura}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdLeitura(AnalisadorSintaticoParser.CmdLeituraContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalisadorSintaticoParser#cmdEscrita}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdEscrita(AnalisadorSintaticoParser.CmdEscritaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalisadorSintaticoParser#listaEscrita}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListaEscrita(AnalisadorSintaticoParser.ListaEscritaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalisadorSintaticoParser#itemEscrita}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitItemEscrita(AnalisadorSintaticoParser.ItemEscritaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalisadorSintaticoParser#cmdAtribuicao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdAtribuicao(AnalisadorSintaticoParser.CmdAtribuicaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalisadorSintaticoParser#expressao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressao(AnalisadorSintaticoParser.ExpressaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalisadorSintaticoParser#expressaoRelacional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressaoRelacional(AnalisadorSintaticoParser.ExpressaoRelacionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalisadorSintaticoParser#opRelacional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpRelacional(AnalisadorSintaticoParser.OpRelacionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalisadorSintaticoParser#expressaoAditiva}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressaoAditiva(AnalisadorSintaticoParser.ExpressaoAditivaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalisadorSintaticoParser#expressaoOr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressaoOr(AnalisadorSintaticoParser.ExpressaoOrContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalisadorSintaticoParser#expressaoAnd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressaoAnd(AnalisadorSintaticoParser.ExpressaoAndContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalisadorSintaticoParser#expressaoMultiplicativa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressaoMultiplicativa(AnalisadorSintaticoParser.ExpressaoMultiplicativaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalisadorSintaticoParser#expressaoUnaria}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressaoUnaria(AnalisadorSintaticoParser.ExpressaoUnariaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AnalisadorSintaticoParser#expressaoPrimaria}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressaoPrimaria(AnalisadorSintaticoParser.ExpressaoPrimariaContext ctx);
}
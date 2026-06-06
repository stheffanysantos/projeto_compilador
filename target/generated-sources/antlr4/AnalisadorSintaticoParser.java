// Generated from AnalisadorSintaticoParser.g4 by ANTLR 4.13.1

package compilador;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class AnalisadorSintaticoParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PROGRAM=1, INTEGER=2, BOOLEAN=3, STRING=4, BEGIN=5, END=6, IF=7, THEN=8, 
		ELSE=9, WHILE=10, DO=11, READ=12, VAR=13, FALSE=14, TRUE=15, WRITE=16, 
		OR=17, AND=18, MENOR_IG=19, MAIOR_IG=20, IGUAL=21, DIFERENTE=22, MENOR=23, 
		MAIOR=24, MAIS=25, MENOS=26, VEZES=27, DIV=28, NEGACAO=29, ATRIB=30, PONTO_VIG=31, 
		PONTO=32, DPONTOS=33, VIRGULA=34, ABRE_PAR=35, FECHA_PAR=36, CTE=37, CADEIA=38, 
		ID=39, COMENTARIO=40, ESPACO=41;
	public static final int
		RULE_programa = 0, RULE_declaracoes = 1, RULE_listaDeclaracoes = 2, RULE_declaracaoTipo = 3, 
		RULE_listaIds = 4, RULE_tipo = 5, RULE_blocoComandos = 6, RULE_listaComandos = 7, 
		RULE_comando = 8, RULE_cmdIf = 9, RULE_cmdWhile = 10, RULE_cmdLeitura = 11, 
		RULE_cmdEscrita = 12, RULE_listaEscrita = 13, RULE_itemEscrita = 14, RULE_cmdAtribuicao = 15, 
		RULE_expressao = 16, RULE_expressaoRelacional = 17, RULE_opRelacional = 18, 
		RULE_expressaoAditiva = 19, RULE_expressaoOr = 20, RULE_expressaoAnd = 21, 
		RULE_expressaoMultiplicativa = 22, RULE_expressaoUnaria = 23, RULE_expressaoPrimaria = 24;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "declaracoes", "listaDeclaracoes", "declaracaoTipo", "listaIds", 
			"tipo", "blocoComandos", "listaComandos", "comando", "cmdIf", "cmdWhile", 
			"cmdLeitura", "cmdEscrita", "listaEscrita", "itemEscrita", "cmdAtribuicao", 
			"expressao", "expressaoRelacional", "opRelacional", "expressaoAditiva", 
			"expressaoOr", "expressaoAnd", "expressaoMultiplicativa", "expressaoUnaria", 
			"expressaoPrimaria"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "'<='", "'>='", "'=='", "'<>'", 
			"'<'", "'>'", "'+'", "'-'", "'*'", "'/'", "'~'", "':='", "';'", "'.'", 
			"':'", "','", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PROGRAM", "INTEGER", "BOOLEAN", "STRING", "BEGIN", "END", "IF", 
			"THEN", "ELSE", "WHILE", "DO", "READ", "VAR", "FALSE", "TRUE", "WRITE", 
			"OR", "AND", "MENOR_IG", "MAIOR_IG", "IGUAL", "DIFERENTE", "MENOR", "MAIOR", 
			"MAIS", "MENOS", "VEZES", "DIV", "NEGACAO", "ATRIB", "PONTO_VIG", "PONTO", 
			"DPONTOS", "VIRGULA", "ABRE_PAR", "FECHA_PAR", "CTE", "CADEIA", "ID", 
			"COMENTARIO", "ESPACO"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "AnalisadorSintaticoParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AnalisadorSintaticoParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramaContext extends ParserRuleContext {
		public TerminalNode PROGRAM() { return getToken(AnalisadorSintaticoParser.PROGRAM, 0); }
		public TerminalNode ID() { return getToken(AnalisadorSintaticoParser.ID, 0); }
		public TerminalNode PONTO_VIG() { return getToken(AnalisadorSintaticoParser.PONTO_VIG, 0); }
		public DeclaracoesContext declaracoes() {
			return getRuleContext(DeclaracoesContext.class,0);
		}
		public BlocoComandosContext blocoComandos() {
			return getRuleContext(BlocoComandosContext.class,0);
		}
		public TerminalNode PONTO() { return getToken(AnalisadorSintaticoParser.PONTO, 0); }
		public TerminalNode EOF() { return getToken(AnalisadorSintaticoParser.EOF, 0); }
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalisadorSintaticoParserVisitor ) return ((AnalisadorSintaticoParserVisitor<? extends T>)visitor).visitPrograma(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			match(PROGRAM);
			setState(51);
			match(ID);
			setState(52);
			match(PONTO_VIG);
			setState(53);
			declaracoes();
			setState(54);
			blocoComandos();
			setState(55);
			match(PONTO);
			setState(56);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaracoesContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(AnalisadorSintaticoParser.VAR, 0); }
		public ListaDeclaracoesContext listaDeclaracoes() {
			return getRuleContext(ListaDeclaracoesContext.class,0);
		}
		public DeclaracoesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracoes; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalisadorSintaticoParserVisitor ) return ((AnalisadorSintaticoParserVisitor<? extends T>)visitor).visitDeclaracoes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclaracoesContext declaracoes() throws RecognitionException {
		DeclaracoesContext _localctx = new DeclaracoesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaracoes);
		try {
			setState(61);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BEGIN:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
				match(VAR);
				setState(60);
				listaDeclaracoes();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ListaDeclaracoesContext extends ParserRuleContext {
		public DeclaracaoTipoContext declaracaoTipo() {
			return getRuleContext(DeclaracaoTipoContext.class,0);
		}
		public ListaDeclaracoesContext listaDeclaracoes() {
			return getRuleContext(ListaDeclaracoesContext.class,0);
		}
		public ListaDeclaracoesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listaDeclaracoes; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalisadorSintaticoParserVisitor ) return ((AnalisadorSintaticoParserVisitor<? extends T>)visitor).visitListaDeclaracoes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListaDeclaracoesContext listaDeclaracoes() throws RecognitionException {
		ListaDeclaracoesContext _localctx = new ListaDeclaracoesContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_listaDeclaracoes);
		try {
			setState(67);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(63);
				declaracaoTipo();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
				declaracaoTipo();
				setState(65);
				listaDeclaracoes();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaracaoTipoContext extends ParserRuleContext {
		public ListaIdsContext listaIds() {
			return getRuleContext(ListaIdsContext.class,0);
		}
		public TerminalNode DPONTOS() { return getToken(AnalisadorSintaticoParser.DPONTOS, 0); }
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public TerminalNode PONTO_VIG() { return getToken(AnalisadorSintaticoParser.PONTO_VIG, 0); }
		public DeclaracaoTipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracaoTipo; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalisadorSintaticoParserVisitor ) return ((AnalisadorSintaticoParserVisitor<? extends T>)visitor).visitDeclaracaoTipo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclaracaoTipoContext declaracaoTipo() throws RecognitionException {
		DeclaracaoTipoContext _localctx = new DeclaracaoTipoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_declaracaoTipo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			listaIds();
			setState(70);
			match(DPONTOS);
			setState(71);
			tipo();
			setState(72);
			match(PONTO_VIG);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ListaIdsContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(AnalisadorSintaticoParser.ID, 0); }
		public TerminalNode VIRGULA() { return getToken(AnalisadorSintaticoParser.VIRGULA, 0); }
		public ListaIdsContext listaIds() {
			return getRuleContext(ListaIdsContext.class,0);
		}
		public ListaIdsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listaIds; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalisadorSintaticoParserVisitor ) return ((AnalisadorSintaticoParserVisitor<? extends T>)visitor).visitListaIds(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListaIdsContext listaIds() throws RecognitionException {
		ListaIdsContext _localctx = new ListaIdsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_listaIds);
		try {
			setState(78);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(74);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				match(ID);
				setState(76);
				match(VIRGULA);
				setState(77);
				listaIds();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TipoContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(AnalisadorSintaticoParser.INTEGER, 0); }
		public TerminalNode BOOLEAN() { return getToken(AnalisadorSintaticoParser.BOOLEAN, 0); }
		public TerminalNode STRING() { return getToken(AnalisadorSintaticoParser.STRING, 0); }
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalisadorSintaticoParserVisitor ) return ((AnalisadorSintaticoParserVisitor<? extends T>)visitor).visitTipo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_tipo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 28L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlocoComandosContext extends ParserRuleContext {
		public TerminalNode BEGIN() { return getToken(AnalisadorSintaticoParser.BEGIN, 0); }
		public ListaComandosContext listaComandos() {
			return getRuleContext(ListaComandosContext.class,0);
		}
		public TerminalNode END() { return getToken(AnalisadorSintaticoParser.END, 0); }
		public BlocoComandosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blocoComandos; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalisadorSintaticoParserVisitor ) return ((AnalisadorSintaticoParserVisitor<? extends T>)visitor).visitBlocoComandos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlocoComandosContext blocoComandos() throws RecognitionException {
		BlocoComandosContext _localctx = new BlocoComandosContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_blocoComandos);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(BEGIN);
			setState(83);
			listaComandos();
			setState(84);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ListaComandosContext extends ParserRuleContext {
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public List<TerminalNode> PONTO_VIG() { return getTokens(AnalisadorSintaticoParser.PONTO_VIG); }
		public TerminalNode PONTO_VIG(int i) {
			return getToken(AnalisadorSintaticoParser.PONTO_VIG, i);
		}
		public ListaComandosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listaComandos; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalisadorSintaticoParserVisitor ) return ((AnalisadorSintaticoParserVisitor<? extends T>)visitor).visitListaComandos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListaComandosContext listaComandos() throws RecognitionException {
		ListaComandosContext _localctx = new ListaComandosContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_listaComandos);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(86);
				comando();
				setState(87);
				match(PONTO_VIG);
				}
				}
				setState(91); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 549755884704L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComandoContext extends ParserRuleContext {
		public CmdIfContext cmdIf() {
			return getRuleContext(CmdIfContext.class,0);
		}
		public CmdWhileContext cmdWhile() {
			return getRuleContext(CmdWhileContext.class,0);
		}
		public CmdLeituraContext cmdLeitura() {
			return getRuleContext(CmdLeituraContext.class,0);
		}
		public CmdEscritaContext cmdEscrita() {
			return getRuleContext(CmdEscritaContext.class,0);
		}
		public CmdAtribuicaoContext cmdAtribuicao() {
			return getRuleContext(CmdAtribuicaoContext.class,0);
		}
		public BlocoComandosContext blocoComandos() {
			return getRuleContext(BlocoComandosContext.class,0);
		}
		public ComandoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comando; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalisadorSintaticoParserVisitor ) return ((AnalisadorSintaticoParserVisitor<? extends T>)visitor).visitComando(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComandoContext comando() throws RecognitionException {
		ComandoContext _localctx = new ComandoContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_comando);
		try {
			setState(99);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
				enterOuterAlt(_localctx, 1);
				{
				setState(93);
				cmdIf();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 2);
				{
				setState(94);
				cmdWhile();
				}
				break;
			case READ:
				enterOuterAlt(_localctx, 3);
				{
				setState(95);
				cmdLeitura();
				}
				break;
			case WRITE:
				enterOuterAlt(_localctx, 4);
				{
				setState(96);
				cmdEscrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 5);
				{
				setState(97);
				cmdAtribuicao();
				}
				break;
			case BEGIN:
				enterOuterAlt(_localctx, 6);
				{
				setState(98);
				blocoComandos();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdIfContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(AnalisadorSintaticoParser.IF, 0); }
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public TerminalNode THEN() { return getToken(AnalisadorSintaticoParser.THEN, 0); }
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(AnalisadorSintaticoParser.ELSE, 0); }
		public CmdIfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdIf; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalisadorSintaticoParserVisitor ) return ((AnalisadorSintaticoParserVisitor<? extends T>)visitor).visitCmdIf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdIfContext cmdIf() throws RecognitionException {
		CmdIfContext _localctx = new CmdIfContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdIf);
		try {
			setState(113);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(101);
				match(IF);
				setState(102);
				expressao();
				setState(103);
				match(THEN);
				setState(104);
				comando();
				setState(105);
				match(ELSE);
				setState(106);
				comando();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(108);
				match(IF);
				setState(109);
				expressao();
				setState(110);
				match(THEN);
				setState(111);
				comando();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdWhileContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(AnalisadorSintaticoParser.WHILE, 0); }
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public TerminalNode DO() { return getToken(AnalisadorSintaticoParser.DO, 0); }
		public ComandoContext comando() {
			return getRuleContext(ComandoContext.class,0);
		}
		public CmdWhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdWhile; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalisadorSintaticoParserVisitor ) return ((AnalisadorSintaticoParserVisitor<? extends T>)visitor).visitCmdWhile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdWhileContext cmdWhile() throws RecognitionException {
		CmdWhileContext _localctx = new CmdWhileContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdWhile);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(WHILE);
			setState(116);
			expressao();
			setState(117);
			match(DO);
			setState(118);
			comando();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdLeituraContext extends ParserRuleContext {
		public TerminalNode READ() { return getToken(AnalisadorSintaticoParser.READ, 0); }
		public TerminalNode ABRE_PAR() { return getToken(AnalisadorSintaticoParser.ABRE_PAR, 0); }
		public ListaIdsContext listaIds() {
			return getRuleContext(ListaIdsContext.class,0);
		}
		public TerminalNode FECHA_PAR() { return getToken(AnalisadorSintaticoParser.FECHA_PAR, 0); }
		public CmdLeituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdLeitura; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalisadorSintaticoParserVisitor ) return ((AnalisadorSintaticoParserVisitor<? extends T>)visitor).visitCmdLeitura(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdLeituraContext cmdLeitura() throws RecognitionException {
		CmdLeituraContext _localctx = new CmdLeituraContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_cmdLeitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(READ);
			setState(121);
			match(ABRE_PAR);
			setState(122);
			listaIds();
			setState(123);
			match(FECHA_PAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdEscritaContext extends ParserRuleContext {
		public TerminalNode WRITE() { return getToken(AnalisadorSintaticoParser.WRITE, 0); }
		public TerminalNode ABRE_PAR() { return getToken(AnalisadorSintaticoParser.ABRE_PAR, 0); }
		public ListaEscritaContext listaEscrita() {
			return getRuleContext(ListaEscritaContext.class,0);
		}
		public TerminalNode FECHA_PAR() { return getToken(AnalisadorSintaticoParser.FECHA_PAR, 0); }
		public CmdEscritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdEscrita; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalisadorSintaticoParserVisitor ) return ((AnalisadorSintaticoParserVisitor<? extends T>)visitor).visitCmdEscrita(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdEscritaContext cmdEscrita() throws RecognitionException {
		CmdEscritaContext _localctx = new CmdEscritaContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_cmdEscrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(WRITE);
			setState(126);
			match(ABRE_PAR);
			setState(127);
			listaEscrita();
			setState(128);
			match(FECHA_PAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ListaEscritaContext extends ParserRuleContext {
		public ItemEscritaContext itemEscrita() {
			return getRuleContext(ItemEscritaContext.class,0);
		}
		public TerminalNode VIRGULA() { return getToken(AnalisadorSintaticoParser.VIRGULA, 0); }
		public ListaEscritaContext listaEscrita() {
			return getRuleContext(ListaEscritaContext.class,0);
		}
		public ListaEscritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listaEscrita; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalisadorSintaticoParserVisitor ) return ((AnalisadorSintaticoParserVisitor<? extends T>)visitor).visitListaEscrita(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListaEscritaContext listaEscrita() throws RecognitionException {
		ListaEscritaContext _localctx = new ListaEscritaContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_listaEscrita);
		try {
			setState(135);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(130);
				itemEscrita();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(131);
				itemEscrita();
				setState(132);
				match(VIRGULA);
				setState(133);
				listaEscrita();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ItemEscritaContext extends ParserRuleContext {
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public TerminalNode CADEIA() { return getToken(AnalisadorSintaticoParser.CADEIA, 0); }
		public ItemEscritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_itemEscrita; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalisadorSintaticoParserVisitor ) return ((AnalisadorSintaticoParserVisitor<? extends T>)visitor).visitItemEscrita(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ItemEscritaContext itemEscrita() throws RecognitionException {
		ItemEscritaContext _localctx = new ItemEscritaContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_itemEscrita);
		try {
			setState(139);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FALSE:
			case TRUE:
			case NEGACAO:
			case ABRE_PAR:
			case CTE:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(137);
				expressao();
				}
				break;
			case CADEIA:
				enterOuterAlt(_localctx, 2);
				{
				setState(138);
				match(CADEIA);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdAtribuicaoContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(AnalisadorSintaticoParser.ID, 0); }
		public TerminalNode ATRIB() { return getToken(AnalisadorSintaticoParser.ATRIB, 0); }
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public CmdAtribuicaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdAtribuicao; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalisadorSintaticoParserVisitor ) return ((AnalisadorSintaticoParserVisitor<? extends T>)visitor).visitCmdAtribuicao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdAtribuicaoContext cmdAtribuicao() throws RecognitionException {
		CmdAtribuicaoContext _localctx = new CmdAtribuicaoContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_cmdAtribuicao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(ID);
			setState(142);
			match(ATRIB);
			setState(143);
			expressao();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressaoContext extends ParserRuleContext {
		public ExpressaoRelacionalContext expressaoRelacional() {
			return getRuleContext(ExpressaoRelacionalContext.class,0);
		}
		public ExpressaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressao; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalisadorSintaticoParserVisitor ) return ((AnalisadorSintaticoParserVisitor<? extends T>)visitor).visitExpressao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressaoContext expressao() throws RecognitionException {
		ExpressaoContext _localctx = new ExpressaoContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_expressao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			expressaoRelacional();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressaoRelacionalContext extends ParserRuleContext {
		public List<ExpressaoAditivaContext> expressaoAditiva() {
			return getRuleContexts(ExpressaoAditivaContext.class);
		}
		public ExpressaoAditivaContext expressaoAditiva(int i) {
			return getRuleContext(ExpressaoAditivaContext.class,i);
		}
		public OpRelacionalContext opRelacional() {
			return getRuleContext(OpRelacionalContext.class,0);
		}
		public ExpressaoRelacionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressaoRelacional; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalisadorSintaticoParserVisitor ) return ((AnalisadorSintaticoParserVisitor<? extends T>)visitor).visitExpressaoRelacional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressaoRelacionalContext expressaoRelacional() throws RecognitionException {
		ExpressaoRelacionalContext _localctx = new ExpressaoRelacionalContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_expressaoRelacional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			expressaoAditiva();
			setState(151);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 33030144L) != 0)) {
				{
				setState(148);
				opRelacional();
				setState(149);
				expressaoAditiva();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OpRelacionalContext extends ParserRuleContext {
		public TerminalNode MENOR() { return getToken(AnalisadorSintaticoParser.MENOR, 0); }
		public TerminalNode MENOR_IG() { return getToken(AnalisadorSintaticoParser.MENOR_IG, 0); }
		public TerminalNode MAIOR() { return getToken(AnalisadorSintaticoParser.MAIOR, 0); }
		public TerminalNode MAIOR_IG() { return getToken(AnalisadorSintaticoParser.MAIOR_IG, 0); }
		public TerminalNode IGUAL() { return getToken(AnalisadorSintaticoParser.IGUAL, 0); }
		public TerminalNode DIFERENTE() { return getToken(AnalisadorSintaticoParser.DIFERENTE, 0); }
		public OpRelacionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_opRelacional; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalisadorSintaticoParserVisitor ) return ((AnalisadorSintaticoParserVisitor<? extends T>)visitor).visitOpRelacional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OpRelacionalContext opRelacional() throws RecognitionException {
		OpRelacionalContext _localctx = new OpRelacionalContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_opRelacional);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 33030144L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressaoAditivaContext extends ParserRuleContext {
		public List<ExpressaoOrContext> expressaoOr() {
			return getRuleContexts(ExpressaoOrContext.class);
		}
		public ExpressaoOrContext expressaoOr(int i) {
			return getRuleContext(ExpressaoOrContext.class,i);
		}
		public List<TerminalNode> MAIS() { return getTokens(AnalisadorSintaticoParser.MAIS); }
		public TerminalNode MAIS(int i) {
			return getToken(AnalisadorSintaticoParser.MAIS, i);
		}
		public List<TerminalNode> MENOS() { return getTokens(AnalisadorSintaticoParser.MENOS); }
		public TerminalNode MENOS(int i) {
			return getToken(AnalisadorSintaticoParser.MENOS, i);
		}
		public ExpressaoAditivaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressaoAditiva; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalisadorSintaticoParserVisitor ) return ((AnalisadorSintaticoParserVisitor<? extends T>)visitor).visitExpressaoAditiva(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressaoAditivaContext expressaoAditiva() throws RecognitionException {
		ExpressaoAditivaContext _localctx = new ExpressaoAditivaContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_expressaoAditiva);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			expressaoOr();
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MAIS || _la==MENOS) {
				{
				{
				setState(156);
				_la = _input.LA(1);
				if ( !(_la==MAIS || _la==MENOS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(157);
				expressaoOr();
				}
				}
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressaoOrContext extends ParserRuleContext {
		public List<ExpressaoAndContext> expressaoAnd() {
			return getRuleContexts(ExpressaoAndContext.class);
		}
		public ExpressaoAndContext expressaoAnd(int i) {
			return getRuleContext(ExpressaoAndContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(AnalisadorSintaticoParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(AnalisadorSintaticoParser.OR, i);
		}
		public ExpressaoOrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressaoOr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalisadorSintaticoParserVisitor ) return ((AnalisadorSintaticoParserVisitor<? extends T>)visitor).visitExpressaoOr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressaoOrContext expressaoOr() throws RecognitionException {
		ExpressaoOrContext _localctx = new ExpressaoOrContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_expressaoOr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			expressaoAnd();
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(164);
				match(OR);
				setState(165);
				expressaoAnd();
				}
				}
				setState(170);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressaoAndContext extends ParserRuleContext {
		public List<ExpressaoMultiplicativaContext> expressaoMultiplicativa() {
			return getRuleContexts(ExpressaoMultiplicativaContext.class);
		}
		public ExpressaoMultiplicativaContext expressaoMultiplicativa(int i) {
			return getRuleContext(ExpressaoMultiplicativaContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(AnalisadorSintaticoParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(AnalisadorSintaticoParser.AND, i);
		}
		public ExpressaoAndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressaoAnd; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalisadorSintaticoParserVisitor ) return ((AnalisadorSintaticoParserVisitor<? extends T>)visitor).visitExpressaoAnd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressaoAndContext expressaoAnd() throws RecognitionException {
		ExpressaoAndContext _localctx = new ExpressaoAndContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_expressaoAnd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			expressaoMultiplicativa();
			setState(176);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(172);
				match(AND);
				setState(173);
				expressaoMultiplicativa();
				}
				}
				setState(178);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressaoMultiplicativaContext extends ParserRuleContext {
		public List<ExpressaoUnariaContext> expressaoUnaria() {
			return getRuleContexts(ExpressaoUnariaContext.class);
		}
		public ExpressaoUnariaContext expressaoUnaria(int i) {
			return getRuleContext(ExpressaoUnariaContext.class,i);
		}
		public List<TerminalNode> VEZES() { return getTokens(AnalisadorSintaticoParser.VEZES); }
		public TerminalNode VEZES(int i) {
			return getToken(AnalisadorSintaticoParser.VEZES, i);
		}
		public List<TerminalNode> DIV() { return getTokens(AnalisadorSintaticoParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(AnalisadorSintaticoParser.DIV, i);
		}
		public ExpressaoMultiplicativaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressaoMultiplicativa; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalisadorSintaticoParserVisitor ) return ((AnalisadorSintaticoParserVisitor<? extends T>)visitor).visitExpressaoMultiplicativa(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressaoMultiplicativaContext expressaoMultiplicativa() throws RecognitionException {
		ExpressaoMultiplicativaContext _localctx = new ExpressaoMultiplicativaContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_expressaoMultiplicativa);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			expressaoUnaria();
			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VEZES || _la==DIV) {
				{
				{
				setState(180);
				_la = _input.LA(1);
				if ( !(_la==VEZES || _la==DIV) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(181);
				expressaoUnaria();
				}
				}
				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressaoUnariaContext extends ParserRuleContext {
		public TerminalNode NEGACAO() { return getToken(AnalisadorSintaticoParser.NEGACAO, 0); }
		public ExpressaoPrimariaContext expressaoPrimaria() {
			return getRuleContext(ExpressaoPrimariaContext.class,0);
		}
		public ExpressaoUnariaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressaoUnaria; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalisadorSintaticoParserVisitor ) return ((AnalisadorSintaticoParserVisitor<? extends T>)visitor).visitExpressaoUnaria(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressaoUnariaContext expressaoUnaria() throws RecognitionException {
		ExpressaoUnariaContext _localctx = new ExpressaoUnariaContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_expressaoUnaria);
		try {
			setState(190);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NEGACAO:
				enterOuterAlt(_localctx, 1);
				{
				setState(187);
				match(NEGACAO);
				setState(188);
				expressaoPrimaria();
				}
				break;
			case FALSE:
			case TRUE:
			case ABRE_PAR:
			case CTE:
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(189);
				expressaoPrimaria();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressaoPrimariaContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(AnalisadorSintaticoParser.ID, 0); }
		public TerminalNode CTE() { return getToken(AnalisadorSintaticoParser.CTE, 0); }
		public TerminalNode TRUE() { return getToken(AnalisadorSintaticoParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(AnalisadorSintaticoParser.FALSE, 0); }
		public TerminalNode ABRE_PAR() { return getToken(AnalisadorSintaticoParser.ABRE_PAR, 0); }
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public TerminalNode FECHA_PAR() { return getToken(AnalisadorSintaticoParser.FECHA_PAR, 0); }
		public ExpressaoPrimariaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressaoPrimaria; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AnalisadorSintaticoParserVisitor ) return ((AnalisadorSintaticoParserVisitor<? extends T>)visitor).visitExpressaoPrimaria(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressaoPrimariaContext expressaoPrimaria() throws RecognitionException {
		ExpressaoPrimariaContext _localctx = new ExpressaoPrimariaContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_expressaoPrimaria);
		try {
			setState(200);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(192);
				match(ID);
				}
				break;
			case CTE:
				enterOuterAlt(_localctx, 2);
				{
				setState(193);
				match(CTE);
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(194);
				match(TRUE);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 4);
				{
				setState(195);
				match(FALSE);
				}
				break;
			case ABRE_PAR:
				enterOuterAlt(_localctx, 5);
				{
				setState(196);
				match(ABRE_PAR);
				setState(197);
				expressao();
				setState(198);
				match(FECHA_PAR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001)\u00cb\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		">\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002"+
		"D\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004O\b\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0004\u0007Z\b\u0007\u000b\u0007"+
		"\f\u0007[\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\bd\b\b"+
		"\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0003\tr\b\t\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0003\r\u0088\b\r\u0001\u000e\u0001\u000e\u0003\u000e\u008c"+
		"\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u0098"+
		"\b\u0011\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0005"+
		"\u0013\u009f\b\u0013\n\u0013\f\u0013\u00a2\t\u0013\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0005\u0014\u00a7\b\u0014\n\u0014\f\u0014\u00aa\t\u0014\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u00af\b\u0015\n\u0015\f\u0015"+
		"\u00b2\t\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0005\u0016\u00b7\b"+
		"\u0016\n\u0016\f\u0016\u00ba\t\u0016\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0003\u0017\u00bf\b\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u00c9\b\u0018"+
		"\u0001\u0018\u0000\u0000\u0019\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.0\u0000\u0004\u0001"+
		"\u0000\u0002\u0004\u0001\u0000\u0013\u0018\u0001\u0000\u0019\u001a\u0001"+
		"\u0000\u001b\u001c\u00c7\u00002\u0001\u0000\u0000\u0000\u0002=\u0001\u0000"+
		"\u0000\u0000\u0004C\u0001\u0000\u0000\u0000\u0006E\u0001\u0000\u0000\u0000"+
		"\bN\u0001\u0000\u0000\u0000\nP\u0001\u0000\u0000\u0000\fR\u0001\u0000"+
		"\u0000\u0000\u000eY\u0001\u0000\u0000\u0000\u0010c\u0001\u0000\u0000\u0000"+
		"\u0012q\u0001\u0000\u0000\u0000\u0014s\u0001\u0000\u0000\u0000\u0016x"+
		"\u0001\u0000\u0000\u0000\u0018}\u0001\u0000\u0000\u0000\u001a\u0087\u0001"+
		"\u0000\u0000\u0000\u001c\u008b\u0001\u0000\u0000\u0000\u001e\u008d\u0001"+
		"\u0000\u0000\u0000 \u0091\u0001\u0000\u0000\u0000\"\u0093\u0001\u0000"+
		"\u0000\u0000$\u0099\u0001\u0000\u0000\u0000&\u009b\u0001\u0000\u0000\u0000"+
		"(\u00a3\u0001\u0000\u0000\u0000*\u00ab\u0001\u0000\u0000\u0000,\u00b3"+
		"\u0001\u0000\u0000\u0000.\u00be\u0001\u0000\u0000\u00000\u00c8\u0001\u0000"+
		"\u0000\u000023\u0005\u0001\u0000\u000034\u0005\'\u0000\u000045\u0005\u001f"+
		"\u0000\u000056\u0003\u0002\u0001\u000067\u0003\f\u0006\u000078\u0005 "+
		"\u0000\u000089\u0005\u0000\u0000\u00019\u0001\u0001\u0000\u0000\u0000"+
		":>\u0001\u0000\u0000\u0000;<\u0005\r\u0000\u0000<>\u0003\u0004\u0002\u0000"+
		"=:\u0001\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000>\u0003\u0001\u0000"+
		"\u0000\u0000?D\u0003\u0006\u0003\u0000@A\u0003\u0006\u0003\u0000AB\u0003"+
		"\u0004\u0002\u0000BD\u0001\u0000\u0000\u0000C?\u0001\u0000\u0000\u0000"+
		"C@\u0001\u0000\u0000\u0000D\u0005\u0001\u0000\u0000\u0000EF\u0003\b\u0004"+
		"\u0000FG\u0005!\u0000\u0000GH\u0003\n\u0005\u0000HI\u0005\u001f\u0000"+
		"\u0000I\u0007\u0001\u0000\u0000\u0000JO\u0005\'\u0000\u0000KL\u0005\'"+
		"\u0000\u0000LM\u0005\"\u0000\u0000MO\u0003\b\u0004\u0000NJ\u0001\u0000"+
		"\u0000\u0000NK\u0001\u0000\u0000\u0000O\t\u0001\u0000\u0000\u0000PQ\u0007"+
		"\u0000\u0000\u0000Q\u000b\u0001\u0000\u0000\u0000RS\u0005\u0005\u0000"+
		"\u0000ST\u0003\u000e\u0007\u0000TU\u0005\u0006\u0000\u0000U\r\u0001\u0000"+
		"\u0000\u0000VW\u0003\u0010\b\u0000WX\u0005\u001f\u0000\u0000XZ\u0001\u0000"+
		"\u0000\u0000YV\u0001\u0000\u0000\u0000Z[\u0001\u0000\u0000\u0000[Y\u0001"+
		"\u0000\u0000\u0000[\\\u0001\u0000\u0000\u0000\\\u000f\u0001\u0000\u0000"+
		"\u0000]d\u0003\u0012\t\u0000^d\u0003\u0014\n\u0000_d\u0003\u0016\u000b"+
		"\u0000`d\u0003\u0018\f\u0000ad\u0003\u001e\u000f\u0000bd\u0003\f\u0006"+
		"\u0000c]\u0001\u0000\u0000\u0000c^\u0001\u0000\u0000\u0000c_\u0001\u0000"+
		"\u0000\u0000c`\u0001\u0000\u0000\u0000ca\u0001\u0000\u0000\u0000cb\u0001"+
		"\u0000\u0000\u0000d\u0011\u0001\u0000\u0000\u0000ef\u0005\u0007\u0000"+
		"\u0000fg\u0003 \u0010\u0000gh\u0005\b\u0000\u0000hi\u0003\u0010\b\u0000"+
		"ij\u0005\t\u0000\u0000jk\u0003\u0010\b\u0000kr\u0001\u0000\u0000\u0000"+
		"lm\u0005\u0007\u0000\u0000mn\u0003 \u0010\u0000no\u0005\b\u0000\u0000"+
		"op\u0003\u0010\b\u0000pr\u0001\u0000\u0000\u0000qe\u0001\u0000\u0000\u0000"+
		"ql\u0001\u0000\u0000\u0000r\u0013\u0001\u0000\u0000\u0000st\u0005\n\u0000"+
		"\u0000tu\u0003 \u0010\u0000uv\u0005\u000b\u0000\u0000vw\u0003\u0010\b"+
		"\u0000w\u0015\u0001\u0000\u0000\u0000xy\u0005\f\u0000\u0000yz\u0005#\u0000"+
		"\u0000z{\u0003\b\u0004\u0000{|\u0005$\u0000\u0000|\u0017\u0001\u0000\u0000"+
		"\u0000}~\u0005\u0010\u0000\u0000~\u007f\u0005#\u0000\u0000\u007f\u0080"+
		"\u0003\u001a\r\u0000\u0080\u0081\u0005$\u0000\u0000\u0081\u0019\u0001"+
		"\u0000\u0000\u0000\u0082\u0088\u0003\u001c\u000e\u0000\u0083\u0084\u0003"+
		"\u001c\u000e\u0000\u0084\u0085\u0005\"\u0000\u0000\u0085\u0086\u0003\u001a"+
		"\r\u0000\u0086\u0088\u0001\u0000\u0000\u0000\u0087\u0082\u0001\u0000\u0000"+
		"\u0000\u0087\u0083\u0001\u0000\u0000\u0000\u0088\u001b\u0001\u0000\u0000"+
		"\u0000\u0089\u008c\u0003 \u0010\u0000\u008a\u008c\u0005&\u0000\u0000\u008b"+
		"\u0089\u0001\u0000\u0000\u0000\u008b\u008a\u0001\u0000\u0000\u0000\u008c"+
		"\u001d\u0001\u0000\u0000\u0000\u008d\u008e\u0005\'\u0000\u0000\u008e\u008f"+
		"\u0005\u001e\u0000\u0000\u008f\u0090\u0003 \u0010\u0000\u0090\u001f\u0001"+
		"\u0000\u0000\u0000\u0091\u0092\u0003\"\u0011\u0000\u0092!\u0001\u0000"+
		"\u0000\u0000\u0093\u0097\u0003&\u0013\u0000\u0094\u0095\u0003$\u0012\u0000"+
		"\u0095\u0096\u0003&\u0013\u0000\u0096\u0098\u0001\u0000\u0000\u0000\u0097"+
		"\u0094\u0001\u0000\u0000\u0000\u0097\u0098\u0001\u0000\u0000\u0000\u0098"+
		"#\u0001\u0000\u0000\u0000\u0099\u009a\u0007\u0001\u0000\u0000\u009a%\u0001"+
		"\u0000\u0000\u0000\u009b\u00a0\u0003(\u0014\u0000\u009c\u009d\u0007\u0002"+
		"\u0000\u0000\u009d\u009f\u0003(\u0014\u0000\u009e\u009c\u0001\u0000\u0000"+
		"\u0000\u009f\u00a2\u0001\u0000\u0000\u0000\u00a0\u009e\u0001\u0000\u0000"+
		"\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1\'\u0001\u0000\u0000\u0000"+
		"\u00a2\u00a0\u0001\u0000\u0000\u0000\u00a3\u00a8\u0003*\u0015\u0000\u00a4"+
		"\u00a5\u0005\u0011\u0000\u0000\u00a5\u00a7\u0003*\u0015\u0000\u00a6\u00a4"+
		"\u0001\u0000\u0000\u0000\u00a7\u00aa\u0001\u0000\u0000\u0000\u00a8\u00a6"+
		"\u0001\u0000\u0000\u0000\u00a8\u00a9\u0001\u0000\u0000\u0000\u00a9)\u0001"+
		"\u0000\u0000\u0000\u00aa\u00a8\u0001\u0000\u0000\u0000\u00ab\u00b0\u0003"+
		",\u0016\u0000\u00ac\u00ad\u0005\u0012\u0000\u0000\u00ad\u00af\u0003,\u0016"+
		"\u0000\u00ae\u00ac\u0001\u0000\u0000\u0000\u00af\u00b2\u0001\u0000\u0000"+
		"\u0000\u00b0\u00ae\u0001\u0000\u0000\u0000\u00b0\u00b1\u0001\u0000\u0000"+
		"\u0000\u00b1+\u0001\u0000\u0000\u0000\u00b2\u00b0\u0001\u0000\u0000\u0000"+
		"\u00b3\u00b8\u0003.\u0017\u0000\u00b4\u00b5\u0007\u0003\u0000\u0000\u00b5"+
		"\u00b7\u0003.\u0017\u0000\u00b6\u00b4\u0001\u0000\u0000\u0000\u00b7\u00ba"+
		"\u0001\u0000\u0000\u0000\u00b8\u00b6\u0001\u0000\u0000\u0000\u00b8\u00b9"+
		"\u0001\u0000\u0000\u0000\u00b9-\u0001\u0000\u0000\u0000\u00ba\u00b8\u0001"+
		"\u0000\u0000\u0000\u00bb\u00bc\u0005\u001d\u0000\u0000\u00bc\u00bf\u0003"+
		"0\u0018\u0000\u00bd\u00bf\u00030\u0018\u0000\u00be\u00bb\u0001\u0000\u0000"+
		"\u0000\u00be\u00bd\u0001\u0000\u0000\u0000\u00bf/\u0001\u0000\u0000\u0000"+
		"\u00c0\u00c9\u0005\'\u0000\u0000\u00c1\u00c9\u0005%\u0000\u0000\u00c2"+
		"\u00c9\u0005\u000f\u0000\u0000\u00c3\u00c9\u0005\u000e\u0000\u0000\u00c4"+
		"\u00c5\u0005#\u0000\u0000\u00c5\u00c6\u0003 \u0010\u0000\u00c6\u00c7\u0005"+
		"$\u0000\u0000\u00c7\u00c9\u0001\u0000\u0000\u0000\u00c8\u00c0\u0001\u0000"+
		"\u0000\u0000\u00c8\u00c1\u0001\u0000\u0000\u0000\u00c8\u00c2\u0001\u0000"+
		"\u0000\u0000\u00c8\u00c3\u0001\u0000\u0000\u0000\u00c8\u00c4\u0001\u0000"+
		"\u0000\u0000\u00c91\u0001\u0000\u0000\u0000\u000f=CN[cq\u0087\u008b\u0097"+
		"\u00a0\u00a8\u00b0\u00b8\u00be\u00c8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
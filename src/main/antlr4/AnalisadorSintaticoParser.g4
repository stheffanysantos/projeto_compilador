parser grammar AnalisadorSintaticoParser;

@header {
package compilador;
}

options {
    tokenVocab = AnalisadorLexicoLexer;
}

programa
    : PROGRAM ID PONTO_VIG declaracoes blocoComandos PONTO EOF
    ;

declaracoes
    : /* vazio — nenhuma variável declarada */
    | VAR listaDeclaracoes
    ;

listaDeclaracoes
    : declaracaoTipo
    | declaracaoTipo listaDeclaracoes
    ;

declaracaoTipo
    : listaIds DPONTOS tipo PONTO_VIG
    ;

listaIds
    : ID
    | ID VIRGULA listaIds
    ;

tipo
    : INTEGER
    | BOOLEAN
    | STRING
    ;

blocoComandos
    : BEGIN listaComandos END
    ;

listaComandos
    : (comando PONTO_VIG)+
    ;

comando
    : cmdIf
    | cmdWhile
    | cmdLeitura
    | cmdEscrita
    | cmdAtribuicao
    | blocoComandos
    ;

cmdIf
    : IF expressao THEN comando ELSE comando   // com else (prioridade)
    | IF expressao THEN comando                // sem else
    ;

cmdWhile
    : WHILE expressao DO comando
    ;

cmdLeitura
    : READ ABRE_PAR listaIds FECHA_PAR
    ;

cmdEscrita
    : WRITE ABRE_PAR listaEscrita FECHA_PAR
    ;

listaEscrita
    : itemEscrita
    | itemEscrita VIRGULA listaEscrita
    ;

itemEscrita
    : expressao
    | CADEIA
    ;

cmdAtribuicao
    : ID ATRIB expressao
    ;

expressao
    : expressaoRelacional
    ;

expressaoRelacional
    : expressaoAditiva (opRelacional expressaoAditiva)?
    ;

opRelacional
    : MENOR | MENOR_IG | MAIOR | MAIOR_IG | IGUAL | DIFERENTE
    ;

expressaoAditiva
    : expressaoOr ((MAIS | MENOS) expressaoOr)*
    ;

expressaoOr
    : expressaoAnd (OR expressaoAnd)*
    ;

expressaoAnd
    : expressaoMultiplicativa (AND expressaoMultiplicativa)*
    ;

expressaoMultiplicativa
    : expressaoUnaria ((VEZES | DIV) expressaoUnaria)*
    ;

expressaoUnaria
    : NEGACAO expressaoPrimaria
    | expressaoPrimaria
    ;

expressaoPrimaria
    : ID
    | CTE
    | TRUE
    | FALSE
    | ABRE_PAR expressao FECHA_PAR
    ;

lexer grammar AnalisadorLexicoLexer;

@header {
package compilador;
}

PROGRAM  : P R O G R A M ;
INTEGER  : I N T E G E R ;
BOOLEAN  : B O O L E A N ;
STRING   : S T R I N G   ;
BEGIN    : B E G I N     ;
END      : E N D         ;
IF       : I F           ;
THEN     : T H E N       ;
ELSE     : E L S E       ;
WHILE    : W H I L E     ;
DO       : D O           ;
READ     : R E A D       ;
VAR      : V A R         ;
FALSE    : F A L S E     ;
TRUE     : T R U E       ;
WRITE    : W R I T E     ;

// Operadores Lógicos
OR       : O R  ;
AND      : A N D ;

// Operadores Relacionais

MENOR_IG : '<=' ;
MAIOR_IG : '>=' ;
IGUAL    : '==' ;
DIFERENTE: '<>' ;
MENOR    : '<'  ;
MAIOR    : '>'  ;

// Operadores Aritméticos
MAIS     : '+' ;
MENOS    : '-' ;
VEZES    : '*' ;
DIV      : '/' ;

NEGACAO  : '~' ;

// Símbolos
ATRIB    : ':=' ;
PONTO_VIG: ';'  ;
PONTO    : '.'  ;
DPONTOS  : ':'  ;
VIRGULA  : ','  ;
ABRE_PAR : '('  ;
FECHA_PAR: ')'  ;

// Constante Inteira
CTE
    : DIGITO+
    {
        long valor = Long.parseLong(getText());
        if (valor > 65535) {
            // Erro fatal: interrompe imediatamente
            throw new org.antlr.v4.runtime.LexerNoViableAltException(
                this, _input, _tokenStartCharIndex, null
            );
        }
    }
    ;

CADEIA   : '"' (~["\r\n])* '"' ;

ID
    : LETRA (LETRA | DIGITO)*
    {
        if (getText().length() > 16) {
            setText(getText().substring(0, 16));
        }
    }
    ;

COMENTARIO : '/*' .*? '*/' -> skip ;

ESPACO : [ \t\r\n]+ -> skip ;

// Fragmentos
fragment DIGITO : [0-9] ;
fragment LETRA  : [a-zA-Z] ;

fragment A : [aA] ;
fragment B : [bB] ;
fragment C : [cC] ;
fragment D : [dD] ;
fragment E : [eE] ;
fragment F : [fF] ;
fragment G : [gG] ;
fragment H : [hH] ;
fragment I : [iI] ;
fragment J : [jJ] ;
fragment K : [kK] ;
fragment L : [lL] ;
fragment M : [mM] ;
fragment N : [nN] ;
fragment O : [oO] ;
fragment P : [pP] ;
fragment Q : [qQ] ;
fragment R : [rR] ;
fragment S : [sS] ;
fragment T : [tT] ;
fragment U : [uU] ;
fragment V : [vV] ;
fragment W : [wW] ;
fragment X : [xX] ;
fragment Y : [yY] ;
fragment Z : [zZ] ;

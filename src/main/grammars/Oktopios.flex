// 1. Section User Code (Imports et Package - DOIT ÊTRE EN HAUT)
package com.oktopios.intellij.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.oktopios.intellij.psi.OktopiosTypes;

%%

// 2. Section Options et Déclarations
%class OktopiosFlexLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%public

// Macros pour simplifier les règles
WHITE_SPACE      = [ \t\n\r]+
DIGIT            = [0-9]
LETTER           = [a-zA-Z_]
IDENT            = {LETTER} ({LETTER} | {DIGIT})*
INT_LIT          = {DIGIT}+
FLOAT_LIT        = {DIGIT}+ "." {DIGIT}+
STRING_LIT       = \" ([^\\\"] | \\.)* \"
FSTRING_LIT      = f\" ([^\\\"] | \\. | "{" [^}]* "}")* \"

%%

// 3. Section des Règles Lexicales

// Espaces et Commentaires
{WHITE_SPACE}           { return TokenType.WHITE_SPACE; }
"//" [^\r\n]*           { return OktopiosTypes.LINE_COMMENT; }
"/*" ~"*/"              { return OktopiosTypes.BLOCK_COMMENT; }

// Littéraux
{FSTRING_LIT}           { return OktopiosTypes.FSTRING; }
{STRING_LIT}            { return OktopiosTypes.STRING_LITERAL; }
{FLOAT_LIT}             { return OktopiosTypes.NUMBER_LITERAL; }
{INT_LIT}               { return OktopiosTypes.NUMBER_LITERAL; }

// Mots-clés de contrôle
"if"            { return OktopiosTypes.KEYWORD; }
"elif"          { return OktopiosTypes.KEYWORD; }
"else"          { return OktopiosTypes.KEYWORD; }
"switch"        { return OktopiosTypes.KEYWORD; }
"case"          { return OktopiosTypes.KEYWORD; }
"default"       { return OktopiosTypes.KEYWORD; }
"for"           { return OktopiosTypes.KEYWORD; }
"while"         { return OktopiosTypes.KEYWORD; }
"break"         { return OktopiosTypes.KEYWORD; }
"continue"      { return OktopiosTypes.KEYWORD; }
"return"        { return OktopiosTypes.KEYWORD; }
"try"           { return OktopiosTypes.KEYWORD; }
"catch"         { return OktopiosTypes.KEYWORD; }
"finally"       { return OktopiosTypes.KEYWORD; }
"throw"         { return OktopiosTypes.KEYWORD; }

// Déclarations et POO
"var"           { return OktopiosTypes.KEYWORD; }
"val"           { return OktopiosTypes.KEYWORD; }
"fun"           { return OktopiosTypes.KEYWORD; }
"class"         { return OktopiosTypes.KEYWORD; }
"interface"     { return OktopiosTypes.KEYWORD; }
"extends"       { return OktopiosTypes.KEYWORD; }
"implements"    { return OktopiosTypes.KEYWORD; }
"public"        { return OktopiosTypes.KEYWORD; }
"private"       { return OktopiosTypes.KEYWORD; }
"protected"     { return OktopiosTypes.KEYWORD; }
"static"        { return OktopiosTypes.KEYWORD; }
"import"        { return OktopiosTypes.KEYWORD; }
"as"            { return OktopiosTypes.KEYWORD; }

// Types et booléens
"int"           { return OktopiosTypes.TYPE_KW; }
"float"         { return OktopiosTypes.TYPE_KW; }
"string"        { return OktopiosTypes.TYPE_KW; }
"bool"          { return OktopiosTypes.TYPE_KW; }
"true"          { return OktopiosTypes.BOOL_KW; }
"false"         { return OktopiosTypes.BOOL_KW; }
"null"          { return OktopiosTypes.BOOL_KW; }

// Identifiants (doit être après les mots-clés)
{IDENT}         { return OktopiosTypes.IDENTIFIER; }

// Opérateurs complexes
"+="            { return OktopiosTypes.PLUS_EQ; }
"-="            { return OktopiosTypes.MINUS_EQ; }
"=="            { return OktopiosTypes.EQ_EQ; }
"!="            { return OktopiosTypes.NEQ; }
"<="            { return OktopiosTypes.LTE; }
">="            { return OktopiosTypes.GTE; }
"=>"            { return OktopiosTypes.ARROW; }

// Opérateurs simples et ponctuation
"="             { return OktopiosTypes.EQ; }
"+"             { return OktopiosTypes.PLUS; }
"-"             { return OktopiosTypes.MINUS; }
"*"             { return OktopiosTypes.STAR; }
"/"             { return OktopiosTypes.SLASH; }
"{"             { return OktopiosTypes.LBRACE; }
"}"             { return OktopiosTypes.RBRACE; }
"("             { return OktopiosTypes.LPAREN; }
")"             { return OktopiosTypes.RPAREN; }
"["             { return OktopiosTypes.LBRACKET; }
"]"             { return OktopiosTypes.RBRACKET; }
":"             { return OktopiosTypes.COLON; }
";"             { return OktopiosTypes.SEMICOLON; }
","             { return OktopiosTypes.COMMA; }
"."             { return OktopiosTypes.DOT; }

// Règle de secours (Bad Character)
[^]             { return TokenType.BAD_CHARACTER; }
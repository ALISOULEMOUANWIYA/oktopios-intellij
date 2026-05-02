package com.oktopios.intellij.psi

import com.intellij.psi.tree.IElementType
import com.oktopios.intellij.OktopiosLanguage

class OktopiosTokenType(debugName: String) : IElementType(debugName, OktopiosLanguage)

object OktopiosTypes {
    @JvmField val LINE_COMMENT  = OktopiosTokenType("LINE_COMMENT")
    @JvmField val BLOCK_COMMENT = OktopiosTokenType("BLOCK_COMMENT")
    @JvmField val COMMENT       = LINE_COMMENT
    @JvmField val STRING_LITERAL = OktopiosTokenType("STRING_LITERAL")
    @JvmField val FSTRING        = OktopiosTokenType("FSTRING")
    @JvmField val NUMBER_LITERAL = OktopiosTokenType("NUMBER_LITERAL")
    @JvmField val KEYWORD        = OktopiosTokenType("KEYWORD")
    @JvmField val TYPE_KW        = OktopiosTokenType("TYPE_KW")
    @JvmField val BOOL_KW        = OktopiosTokenType("BOOL_KW")
    @JvmField val IDENTIFIER     = OktopiosTokenType("IDENTIFIER")
    @JvmField val PLUS_EQ        = OktopiosTokenType("PLUS_EQ")
    @JvmField val MINUS_EQ       = OktopiosTokenType("MINUS_EQ")
    @JvmField val STAR_EQ        = OktopiosTokenType("STAR_EQ")
    @JvmField val SLASH_EQ       = OktopiosTokenType("SLASH_EQ")
    @JvmField val EQ_EQ          = OktopiosTokenType("EQ_EQ")
    @JvmField val NEQ            = OktopiosTokenType("NEQ")
    @JvmField val LTE            = OktopiosTokenType("LTE")
    @JvmField val GTE            = OktopiosTokenType("GTE")
    @JvmField val ARROW          = OktopiosTokenType("ARROW")
    @JvmField val EQ             = OktopiosTokenType("EQ")
    @JvmField val PLUS           = OktopiosTokenType("PLUS")
    @JvmField val MINUS          = OktopiosTokenType("MINUS")
    @JvmField val STAR           = OktopiosTokenType("STAR")
    @JvmField val SLASH          = OktopiosTokenType("SLASH")
    @JvmField val PERCENT        = OktopiosTokenType("PERCENT")
    @JvmField val LT             = OktopiosTokenType("LT")
    @JvmField val GT             = OktopiosTokenType("GT")
    @JvmField val LBRACE         = OktopiosTokenType("LBRACE")
    @JvmField val RBRACE         = OktopiosTokenType("RBRACE")
    @JvmField val LPAREN         = OktopiosTokenType("LPAREN")
    @JvmField val RPAREN         = OktopiosTokenType("RPAREN")
    @JvmField val LBRACKET       = OktopiosTokenType("LBRACKET")
    @JvmField val RBRACKET       = OktopiosTokenType("RBRACKET")
    @JvmField val COLON          = OktopiosTokenType("COLON")
    @JvmField val SEMICOLON      = OktopiosTokenType("SEMICOLON")
    @JvmField val COMMA          = OktopiosTokenType("COMMA")
    @JvmField val DOT            = OktopiosTokenType("DOT")
}

package com.oktopios.intellij.highlight

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.oktopios.intellij.lexer.OktopiosLexer
import com.oktopios.intellij.psi.OktopiosTypes

class OktopiosSyntaxHighlighter : SyntaxHighlighterBase() {
    companion object {
        @JvmField val COMMENT  = createTextAttributesKey("OKP_COMMENT",  DefaultLanguageHighlighterColors.LINE_COMMENT)
        @JvmField val STRING   = createTextAttributesKey("OKP_STRING",   DefaultLanguageHighlighterColors.STRING)
        @JvmField val NUMBER   = createTextAttributesKey("OKP_NUMBER",   DefaultLanguageHighlighterColors.NUMBER)
        @JvmField val KEYWORD  = createTextAttributesKey("OKP_KEYWORD",  DefaultLanguageHighlighterColors.KEYWORD)
        @JvmField val TYPE     = createTextAttributesKey("OKP_TYPE",     DefaultLanguageHighlighterColors.CLASS_NAME)
        @JvmField val CONSTANT = createTextAttributesKey("OKP_CONSTANT", DefaultLanguageHighlighterColors.CONSTANT)
        @JvmField val OPERATOR = createTextAttributesKey("OKP_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN)
        @JvmField val IDENT    = createTextAttributesKey("OKP_IDENT",    DefaultLanguageHighlighterColors.IDENTIFIER)
        @JvmField val BAD      = createTextAttributesKey("OKP_BAD",      HighlighterColors.BAD_CHARACTER)
        private val EMPTY = emptyArray<TextAttributesKey>()
        private val OPS = setOf(
            OktopiosTypes.PLUS_EQ, OktopiosTypes.MINUS_EQ, OktopiosTypes.STAR_EQ,
            OktopiosTypes.SLASH_EQ, OktopiosTypes.EQ_EQ, OktopiosTypes.NEQ,
            OktopiosTypes.LTE, OktopiosTypes.GTE, OktopiosTypes.ARROW,
            OktopiosTypes.EQ, OktopiosTypes.PLUS, OktopiosTypes.MINUS,
            OktopiosTypes.STAR, OktopiosTypes.SLASH, OktopiosTypes.PERCENT,
            OktopiosTypes.LT, OktopiosTypes.GT
        )
    }
    override fun getHighlightingLexer(): Lexer = OktopiosLexer()
    override fun getTokenHighlights(t: IElementType?): Array<TextAttributesKey> = when(t) {
        OktopiosTypes.LINE_COMMENT,
        OktopiosTypes.BLOCK_COMMENT  -> arrayOf(COMMENT)
        OktopiosTypes.STRING_LITERAL,
        OktopiosTypes.FSTRING        -> arrayOf(STRING)
        OktopiosTypes.NUMBER_LITERAL -> arrayOf(NUMBER)
        OktopiosTypes.KEYWORD        -> arrayOf(KEYWORD)
        OktopiosTypes.TYPE_KW        -> arrayOf(TYPE)
        OktopiosTypes.BOOL_KW        -> arrayOf(CONSTANT)
        OktopiosTypes.IDENTIFIER     -> arrayOf(IDENT)
        TokenType.BAD_CHARACTER      -> arrayOf(BAD)
        in OPS                       -> arrayOf(OPERATOR)
        else                         -> EMPTY
    }
}

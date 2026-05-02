package com.oktopios.intellij.lexer

import com.intellij.lexer.LexerBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.oktopios.intellij.psi.OktopiosTypes

class OktopiosLexer : LexerBase() {

    private var buffer: CharSequence = ""
    private var startOffset = 0
    private var endOffset   = 0
    private var pos         = 0
    private var tokenType: IElementType? = null
    private var tokenStart  = 0

    private val KEYWORDS = setOf(
        "if","elif","else","switch","case","default",
        "for","while","do","until","loop","break","continue","return",
        "filterLoop","filterWhile","sortLoop","permuteLoop","permuteWhile",
        "circularLoop","circularWhile","sleepingLoop",
        "try","catch","finally","throw",
        "var","val","fun","lambda","class","abstract","interface","enum",
        "extends","implements","override","activate","invoke",
        "new","this","super","super_force","static",
        "private","protected","public","global",
        "inject","import","from","use","as","delete","ref","inout","print",
        "__construct","__destruct",
        "and","or","not","in","is",
        "source","filter","order","asc","desc","stride","shuffle",
        "pattern","mode","where","then","using","step","by","whenever"
    )
    private val TYPES = setOf("int","float","string","char","bool","void")
    private val BOOLS = setOf("true","false","null")

    override fun start(buffer: CharSequence, startOffset: Int, endOffset: Int, initialState: Int) {
        this.buffer      = buffer
        this.startOffset = startOffset
        this.endOffset   = endOffset
        this.pos         = startOffset
        this.tokenStart  = startOffset
        advance()
    }

    override fun getState()      = 0
    override fun getTokenType()  = tokenType
    override fun getTokenStart() = tokenStart
    override fun getTokenEnd()   = pos

    override fun advance() {
        tokenStart = pos
        if (pos >= endOffset) { tokenType = null; return }
        val c = buffer[pos]

        // Espaces
        if (c == ' ' || c == '\t' || c == '\r' || c == '\n') {
            while (pos < endOffset && buffer[pos].let { it == ' '||it == '\t'||it == '\r'||it == '\n' }) pos++
            tokenType = TokenType.WHITE_SPACE; return
        }

        // Commentaire ligne
        if (c == '/' && pos+1 < endOffset && buffer[pos+1] == '/') {
            while (pos < endOffset && buffer[pos] != '\n') pos++
            tokenType = OktopiosTypes.LINE_COMMENT; return
        }

        // Commentaire bloc
        if (c == '/' && pos+1 < endOffset && buffer[pos+1] == '*') {
            pos += 2
            while (pos+1 < endOffset && !(buffer[pos] == '*' && buffer[pos+1] == '/')) pos++
            if (pos+1 < endOffset) pos += 2
            tokenType = OktopiosTypes.BLOCK_COMMENT; return
        }

        // F-String
        if (c == 'f' && pos+1 < endOffset && buffer[pos+1] == '"') {
            pos += 2
            while (pos < endOffset) {
                if (buffer[pos] == '\\') { pos += 2; continue }
                if (buffer[pos] == '"')  { pos++; break }
                pos++
            }
            tokenType = OktopiosTypes.FSTRING; return
        }

        // String
        if (c == '"') {
            pos++
            while (pos < endOffset) {
                if (buffer[pos] == '\\') { pos += 2; continue }
                if (buffer[pos] == '"')  { pos++; break }
                pos++
            }
            tokenType = OktopiosTypes.STRING_LITERAL; return
        }

        // Nombres
        if (c.isDigit()) {
            while (pos < endOffset && (buffer[pos].isDigit() || buffer[pos] == '.')) pos++
            tokenType = OktopiosTypes.NUMBER_LITERAL; return
        }

        // Identifiants et mots-clés
        if (c.isLetter() || c == '_') {
            while (pos < endOffset && (buffer[pos].isLetterOrDigit() || buffer[pos] == '_')) pos++
            val word = buffer.subSequence(tokenStart, pos).toString()
            tokenType = when {
                word in KEYWORDS -> OktopiosTypes.KEYWORD
                word in TYPES    -> OktopiosTypes.TYPE_KW
                word in BOOLS    -> OktopiosTypes.BOOL_KW
                else             -> OktopiosTypes.IDENTIFIER
            }
            return
        }

        // Opérateurs 2 caractères
        if (pos+1 < endOffset) {
            val two = "${buffer[pos]}${buffer[pos+1]}"
            val tt = when(two) {
                "+=" -> OktopiosTypes.PLUS_EQ
                "-=" -> OktopiosTypes.MINUS_EQ
                "*=" -> OktopiosTypes.STAR_EQ
                "/=" -> OktopiosTypes.SLASH_EQ
                "==" -> OktopiosTypes.EQ_EQ
                "!=" -> OktopiosTypes.NEQ
                "<=" -> OktopiosTypes.LTE
                ">=" -> OktopiosTypes.GTE
                "=>" -> OktopiosTypes.ARROW
                else -> null
            }
            if (tt != null) { pos += 2; tokenType = tt; return }
        }

        // Opérateurs 1 caractère
        pos++
        tokenType = when(c) {
            '='  -> OktopiosTypes.EQ
            '+'  -> OktopiosTypes.PLUS
            '-'  -> OktopiosTypes.MINUS
            '*'  -> OktopiosTypes.STAR
            '/'  -> OktopiosTypes.SLASH
            '%'  -> OktopiosTypes.PERCENT
            '<'  -> OktopiosTypes.LT
            '>'  -> OktopiosTypes.GT
            '{'  -> OktopiosTypes.LBRACE
            '}'  -> OktopiosTypes.RBRACE
            '('  -> OktopiosTypes.LPAREN
            ')'  -> OktopiosTypes.RPAREN
            '['  -> OktopiosTypes.LBRACKET
            ']'  -> OktopiosTypes.RBRACKET
            ':'  -> OktopiosTypes.COLON
            ';'  -> OktopiosTypes.SEMICOLON
            ','  -> OktopiosTypes.COMMA
            '.'  -> OktopiosTypes.DOT
            else -> TokenType.BAD_CHARACTER
        }
    }

    override fun getBufferSequence() = buffer
    override fun getBufferEnd()      = endOffset
}

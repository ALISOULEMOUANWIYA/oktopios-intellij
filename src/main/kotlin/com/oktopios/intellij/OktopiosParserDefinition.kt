package com.oktopios.intellij

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import com.oktopios.intellij.lexer.OktopiosLexer
import com.oktopios.intellij.psi.OktopiosTypes

class OktopiosParserDefinition : ParserDefinition {
    companion object {
        val FILE         = IFileElementType(OktopiosLanguage)
        val COMMENTS     = TokenSet.create(OktopiosTypes.LINE_COMMENT, OktopiosTypes.BLOCK_COMMENT)
        val STRINGS      = TokenSet.create(OktopiosTypes.STRING_LITERAL, OktopiosTypes.FSTRING)
        val WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE)
    }
    override fun createLexer(project: Project?): Lexer = OktopiosLexer()
    override fun createParser(project: Project?): PsiParser =
        PsiParser { root, builder ->
            val m = builder.mark()
            while (!builder.eof()) builder.advanceLexer()
            m.done(root)
            builder.treeBuilt
        }
    override fun getFileNodeType() = FILE
    override fun getCommentTokens() = COMMENTS
    override fun getStringLiteralElements() = STRINGS
    override fun getWhitespaceTokens() = WHITE_SPACES
    override fun createElement(node: ASTNode): PsiElement =
        throw UnsupportedOperationException("createElement")
    override fun createFile(viewProvider: FileViewProvider): PsiFile =
        OktopiosPsiFile(viewProvider)
}

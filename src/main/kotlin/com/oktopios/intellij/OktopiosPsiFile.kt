package com.oktopios.intellij

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider

class OktopiosPsiFile(viewProvider: FileViewProvider) :
    PsiFileBase(viewProvider, OktopiosLanguage) {
    override fun getFileType() = OktopiosFileType
    override fun toString()    = "Oktopios File"
}

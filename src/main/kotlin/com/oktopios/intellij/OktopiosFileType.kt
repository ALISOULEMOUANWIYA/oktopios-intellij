package com.oktopios.intellij

import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

object OktopiosFileType : LanguageFileType(OktopiosLanguage) {

    override fun getName() = "Oktopios File"
    override fun getDescription() = "Oktopios source file"
    override fun getDefaultExtension() = "okp"

    override fun getIcon(): Icon =
        IconLoader.getIcon("/icons/okp.png", javaClass)
}
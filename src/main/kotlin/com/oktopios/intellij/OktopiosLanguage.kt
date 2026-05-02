package com.oktopios.intellij

import com.intellij.lang.Language

object OktopiosLanguage : Language("Oktopios") {
    private fun readResolve(): Any = OktopiosLanguage
}
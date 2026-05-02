package com.oktopios.intellij.run

import com.intellij.execution.configurations.ConfigurationType
import com.intellij.execution.configurations.ConfigurationFactory
import javax.swing.Icon
import com.intellij.openapi.util.IconLoader

class OktopiosRunConfigurationType : ConfigurationType {

    override fun getDisplayName() = "Oktopios"

    override fun getConfigurationTypeDescription() =
        "Run Oktopios files"

    override fun getIcon(): Icon =
        IconLoader.getIcon("/icons/okp.png", javaClass)

    override fun getId() = "OKTOPIOS_RUN_CONFIGURATION"

    override fun getConfigurationFactories(): Array<ConfigurationFactory> =
        arrayOf(OktopiosRunConfigurationFactory(this))
}
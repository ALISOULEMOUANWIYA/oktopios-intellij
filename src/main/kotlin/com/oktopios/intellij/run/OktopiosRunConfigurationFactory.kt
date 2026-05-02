package com.oktopios.intellij.run

import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.openapi.project.Project

class OktopiosRunConfigurationFactory(type: ConfigurationType) : ConfigurationFactory(type) {
    override fun getId() = "OktopiosRunConfigurationFactory"
    override fun createTemplateConfiguration(project: Project): RunConfiguration =
        OktopiosRunConfiguration(project, this, "Oktopios")
}

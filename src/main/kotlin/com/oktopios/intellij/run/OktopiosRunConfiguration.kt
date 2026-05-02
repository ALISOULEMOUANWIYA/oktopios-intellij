package com.oktopios.intellij.run

import com.intellij.execution.ExecutionException
import com.intellij.execution.Executor
import com.intellij.execution.configurations.*
import com.intellij.execution.process.OSProcessHandler
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import java.awt.BorderLayout
import java.awt.GridLayout
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

class OktopiosRunConfiguration(
    project: Project,
    factory: ConfigurationFactory,
    name: String
) : RunConfigurationBase<RunProfileState>(project, factory, name) {

    var filePath: String = ""

    override fun getConfigurationEditor(): SettingsEditor<out RunConfiguration> =
        OktopiosRunConfigurationEditor()

    override fun checkConfiguration() {
        if (filePath.isBlank())
            throw RuntimeConfigurationError("Spécifiez le fichier .okp à exécuter")
    }

    override fun getState(executor: Executor, env: ExecutionEnvironment): RunProfileState =
        object : CommandLineState(env) {
            override fun startProcess(): ProcessHandler {
                if (filePath.isBlank()) throw ExecutionException("Aucun fichier .okp sélectionné")
                val cmd = GeneralCommandLine("okp", filePath)
                    .withWorkDirectory(project.basePath)
                return OSProcessHandler(cmd)
            }
        }
}

class OktopiosRunConfigurationEditor : SettingsEditor<OktopiosRunConfiguration>() {
    private val fileField = JTextField(40)
    private val panel = JPanel(BorderLayout()).apply {
        val form = JPanel(GridLayout(0, 2, 8, 4))
        form.add(JLabel("Fichier .okp :"))
        form.add(fileField)
        add(form, BorderLayout.NORTH)
    }
    override fun resetEditorFrom(s: OktopiosRunConfiguration) { fileField.text = s.filePath }
    override fun applyEditorTo(s: OktopiosRunConfiguration)   { s.filePath = fileField.text.trim() }
    override fun createEditor() = panel
}

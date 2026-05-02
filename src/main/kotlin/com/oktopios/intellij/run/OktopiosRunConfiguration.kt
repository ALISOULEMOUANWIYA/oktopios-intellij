package com.oktopios.intellij.run

import com.intellij.execution.ExecutionException
import com.intellij.execution.Executor
import com.intellij.execution.configurations.CommandLineState
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.execution.configurations.RunConfigurationBase
import com.intellij.execution.configurations.RunProfileState
import com.intellij.execution.configurations.RuntimeConfigurationError
import com.intellij.execution.process.OSProcessHandler
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializer
import org.jdom.Element
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
        if (filePath.isBlank()) {
            throw RuntimeConfigurationError("Specify the .okp file to run")
        }
    }

    override fun getState(executor: Executor, env: ExecutionEnvironment): RunProfileState =
        object : CommandLineState(env) {
            override fun startProcess(): ProcessHandler {
                if (filePath.isBlank()) {
                    throw ExecutionException("No .okp file selected")
                }

                val cmd = GeneralCommandLine("okp", filePath)
                    .withWorkDirectory(project.basePath)
                return OSProcessHandler(cmd)
            }
        }

    override fun writeExternal(element: Element) {
        super.writeExternal(element)
        XmlSerializer.serializeInto(this, element)
    }

    override fun readExternal(element: Element) {
        super.readExternal(element)
        XmlSerializer.deserializeInto(this, element)
    }
}

class OktopiosRunConfigurationEditor : SettingsEditor<OktopiosRunConfiguration>() {
    private val fileField = JTextField(40)
    private val panel = JPanel(BorderLayout()).apply {
        val form = JPanel(GridLayout(0, 2, 8, 4))
        form.add(JLabel("Oktopios file:"))
        form.add(fileField)
        add(form, BorderLayout.NORTH)
    }

    override fun resetEditorFrom(s: OktopiosRunConfiguration) {
        fileField.text = s.filePath
    }

    override fun applyEditorTo(s: OktopiosRunConfiguration) {
        s.filePath = fileField.text.trim()
    }

    override fun createEditor() = panel
}
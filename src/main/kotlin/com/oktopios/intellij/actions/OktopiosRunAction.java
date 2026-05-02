package com.oktopios.intellij.actions;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;

public class OktopiosRunAction extends AnAction {
    @Override
    public void update(@NotNull AnActionEvent event) {
        VirtualFile file = event.getData(CommonDataKeys.VIRTUAL_FILE);
        event.getPresentation().setEnabledAndVisible(file != null && "okp".equalsIgnoreCase(file.getExtension()));
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        VirtualFile file = event.getData(CommonDataKeys.VIRTUAL_FILE);
        if (project == null || file == null) {
            return;
        }

        GeneralCommandLine commandLine = new GeneralCommandLine("okp", file.getPath())
                .withWorkDirectory(project.getBasePath())
                .withCharset(StandardCharsets.UTF_8);

        try {
            OSProcessHandler handler = new OSProcessHandler(commandLine);
            handler.startNotify();
        } catch (ExecutionException ex) {
            Messages.showErrorDialog(project, "Impossible de lancer okp: " + ex.getMessage(), "Oktopios");
        }
    }
}
package com.oktopios.intellij.run;

import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.LazyRunConfigurationProducer;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationTypeUtil;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class OktopiosRunConfigurationProducer extends LazyRunConfigurationProducer<OktopiosRunConfiguration> {
    @NotNull
    @Override
    public ConfigurationFactory getConfigurationFactory() {
        return ConfigurationTypeUtil.findConfigurationType(OktopiosRunConfigurationType.class).getConfigurationFactories()[0];
    }

    @Override
    protected boolean setupConfigurationFromContext(
            @NotNull OktopiosRunConfiguration configuration,
            @NotNull ConfigurationContext context,
            @NotNull Ref<PsiElement> sourceElement
    ) {
        VirtualFile file = getOktopiosFile(context);
        if (file == null) {
            return false;
        }

        configuration.setName(file.getNameWithoutExtension());
        configuration.setFilePath(file.getPath());
        return true;
    }

    @Override
    public boolean isConfigurationFromContext(
            @NotNull OktopiosRunConfiguration configuration,
            @NotNull ConfigurationContext context
    ) {
        VirtualFile file = getOktopiosFile(context);
        return file != null && file.getPath().equals(configuration.getFilePath());
    }

    private static VirtualFile getOktopiosFile(ConfigurationContext context) {
        PsiElement element = context.getPsiLocation();
        if (element == null || element.getContainingFile() == null) {
            return null;
        }

        VirtualFile file = element.getContainingFile().getVirtualFile();
        if (file == null || !"okp".equalsIgnoreCase(file.getExtension())) {
            return null;
        }

        return file;
    }
}
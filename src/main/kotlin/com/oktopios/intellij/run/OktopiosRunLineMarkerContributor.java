package com.oktopios.intellij.run;

import com.intellij.execution.lineMarker.ExecutorAction;
import com.intellij.execution.lineMarker.RunLineMarkerContributor;
import com.intellij.icons.AllIcons;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OktopiosRunLineMarkerContributor extends RunLineMarkerContributor {
    @Nullable
    @Override
    public Info getInfo(@NotNull PsiElement element) {
        PsiFile psiFile = element.getContainingFile();
        if (psiFile == null || psiFile.getVirtualFile() == null) {
            return null;
        }

        if (!"okp".equalsIgnoreCase(psiFile.getVirtualFile().getExtension())) {
            return null;
        }

        if (element.getTextRange() == null || element.getTextRange().getStartOffset() != 0) {
            return null;
        }

        return new Info(
                AllIcons.RunConfigurations.TestState.Run,
                ExecutorAction.getActions(0),
                psiElement -> "Run Oktopios file"
        );
    }
}
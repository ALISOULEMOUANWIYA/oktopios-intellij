package com.oktopios.intellij;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.oktopios.intellij.highlight.OktopiosSyntaxHighlighter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class OktopiosColorSettingsPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Comment", OktopiosSyntaxHighlighter.COMMENT),
            new AttributesDescriptor("String", OktopiosSyntaxHighlighter.STRING),
            new AttributesDescriptor("Number", OktopiosSyntaxHighlighter.NUMBER),
            new AttributesDescriptor("Keyword", OktopiosSyntaxHighlighter.KEYWORD),
            new AttributesDescriptor("Type", OktopiosSyntaxHighlighter.TYPE),
            new AttributesDescriptor("Constant", OktopiosSyntaxHighlighter.CONSTANT),
            new AttributesDescriptor("Operator", OktopiosSyntaxHighlighter.OPERATOR),
            new AttributesDescriptor("Identifier", OktopiosSyntaxHighlighter.IDENT),
            new AttributesDescriptor("Bad character", OktopiosSyntaxHighlighter.BAD)
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return OktopiosFileType.INSTANCE.getIcon();
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new OktopiosSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "// Exemple Oktopios\n" +
               "fun main() {\n" +
               "  val appName = \"OktopiosApp\"\n" +
               "  print(appName)\n" +
               "}\n";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Oktopios";
    }
}

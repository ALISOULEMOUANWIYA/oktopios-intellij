package com.oktopios.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.oktopios.intellij.OktopiosLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class OktopiosTokenType extends IElementType {
    public OktopiosTokenType(@NotNull @NonNls String debugName) {
        super(debugName, OktopiosLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "OktopiosTokenType." + super.toString();
    }
}
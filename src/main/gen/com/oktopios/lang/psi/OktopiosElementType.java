 package com.oktopios.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.oktopios.intellij.OktopiosLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class OktopiosElementType extends IElementType {
    public OktopiosElementType(@NotNull @NonNls String debugName) {
        super(debugName, OktopiosLanguage.INSTANCE);
    }
}
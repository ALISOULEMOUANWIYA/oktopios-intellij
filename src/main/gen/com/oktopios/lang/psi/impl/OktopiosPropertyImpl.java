// This is a generated file. Not intended for manual editing.
package com.oktopios.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.oktopios.lang.psi.OktopiosTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.oktopios.lang.psi.*;

public class OktopiosPropertyImpl extends ASTWrapperPsiElement implements OktopiosProperty {

  public OktopiosPropertyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull OktopiosVisitor visitor) {
    visitor.visitProperty(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof OktopiosVisitor) accept((OktopiosVisitor)visitor);
    else super.accept(visitor);
  }

}

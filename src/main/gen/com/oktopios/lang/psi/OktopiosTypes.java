// This is a generated file. Not intended for manual editing.
package com.oktopios.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.oktopios.lang.psi.impl.*;

public interface OktopiosTypes {

  IElementType PROPERTY = new OktopiosElementType("PROPERTY");

  IElementType COMMENT = new OktopiosTokenType("COMMENT");
  IElementType KEY = new OktopiosTokenType("KEY");
  IElementType SEPARATOR = new OktopiosTokenType("SEPARATOR");
  IElementType VALUE = new OktopiosTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == PROPERTY) {
        return new OktopiosPropertyImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}

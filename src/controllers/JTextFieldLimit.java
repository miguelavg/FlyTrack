/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author joao
 */
public class JTextFieldLimit extends PlainDocument{
  private int maxChars;

  public JTextFieldLimit() {
  }

  /**
   * Default constructor.
   *
   * @param maxChars the maximum number of characters that can be entered in the
   * field
   */
  public JTextFieldLimit(final int maxChars) {
    super();
    this.maxChars = maxChars;
  }

  @Override
  public void insertString(int offs, String str, AttributeSet a)
          throws BadLocationException {
    if (str != null && (getLength() + str.length() < maxChars + 1)) {
      super.insertString(offs, str, a);
    }
  }

  public int getMaxChars() {
    return maxChars;
  }

  public void setMaxChars(int maxChars) {
    this.maxChars = maxChars;
  }
    
    
}

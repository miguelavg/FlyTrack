/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Toolkit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

/**
 *
 * @author joao
 */
public class RegexDocument   extends JTextFieldLimit {
      protected final Pattern pattern;

  /**
   * Constructor used to construct a document object which pattern matches
   * strings as typed.
   *
   * @param regex pattern to match on typed strings
   * @param maxLength maximum length of full string
   */
  public RegexDocument(String regex, int maxLength) {
    super(maxLength);
    this.pattern = Pattern.compile(regex);
  }

  @Override
  public void insertString(final int offset, final String str,
          final AttributeSet attr) throws BadLocationException {
    final Matcher matcher = pattern.matcher(str);
    if (matcher.matches()) {
      super.insertString(offset, str, attr);
    } else {
      Toolkit.getDefaultToolkit().beep();
    }
  }
}

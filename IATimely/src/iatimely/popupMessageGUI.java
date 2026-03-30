//Alistair Johnson
//This is the popup gui
package iatimely;

/**
 *
 * @author alyj3
 */
import javax.swing.JOptionPane;
import java.awt.Component;

public class popupMessageGUI
{
  public static void show(Component parent, String message)
  {
    JOptionPane.showMessageDialog(parent, message);
  }
}

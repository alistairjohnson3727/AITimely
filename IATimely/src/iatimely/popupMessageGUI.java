/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

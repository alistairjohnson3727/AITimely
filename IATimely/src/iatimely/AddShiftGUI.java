/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iatimely;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author alyj3
 */
public class AddShiftGUI extends JFrame implements ActionListener
{

  private JLabel descriptionLabel;
  private JTextField descriptionField;
  private JLabel dataLabel;
  private JTextField dateField;
  private JLabel EmployeeLabel;
  private JTextField employeeIDField;
  private JButton addButton;

  public AddShiftGUI()
  {

    super("Add Shift");
    this.setBounds(300, 300, 200, 100);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

}

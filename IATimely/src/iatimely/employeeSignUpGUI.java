/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iatimely;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author alyj3
 */
public class employeeSignUpGUI extends JFrame implements ActionListener
{

  private JLabel titleLabel;
  private JLabel userLabel;
  private JLabel passLabel;
  private JTextField userField;
  private JTextField passField;
  private JPanel centerPanel;
  private JButton signUpButton;
  private JLabel idLabel;
  private JTextField idField;
  private dbAccess db;

  public employeeSignUpGUI()
  {
    super("employee sign up");
    this.setBounds(100, 100, 400, 400);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    titleLabel = new JLabel("Sign Up");
    userLabel = new JLabel("Username: ");
    userField = new JTextField(20);
    userField.addActionListener(this);
    passLabel = new JLabel("Password: ");
    passField = new JTextField(20);
    passField.addActionListener(this);
    idLabel = new JLabel("manager Id:");
    idField = new JTextField(20);
    idField.addActionListener(this);
    signUpButton = new JButton("Sign up");
    signUpButton.addActionListener(this);

    centerPanel = new JPanel();
    centerPanel.add(userLabel);
    centerPanel.add(userField);
    centerPanel.add(passLabel);
    centerPanel.add(passField);
    centerPanel.add(idLabel);
    centerPanel.add(idField);

    this.add(titleLabel, BorderLayout.NORTH);
    this.add(centerPanel, BorderLayout.CENTER);
    this.add(signUpButton, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  public void actionPerformed(ActionEvent e)
  {
    dbAccess db = new dbAccess("iaTimely");
    String command = e.getActionCommand();
    String username = userField.getText().trim();
    if (command.equals("Sign up"))
    {
      if (db.isEmpUserAvailable(username))
      {
        try
        {
          int managerID = Integer.parseInt(idField.getText().trim());
          int employeeID = generateUniqueEmployeeID();
          String password = passField.getText().trim();

          boolean success = db.addEmployeeAcc(employeeID, username, password);
          boolean success2 = db.addEmployeeManager(managerID, employeeID);
          db.closeDbConn();

          if (success && success2)
          {
            JOptionPane.showMessageDialog(this, "Employee account created! your id is " + employeeID);
          }
        }
        catch (NumberFormatException ex)
        {
          JOptionPane.showMessageDialog(this, "Invalid Employee ID");
        }
      }
      else
      {
        JOptionPane.showMessageDialog(this, "username taken.");
      }
      /*
      else 
      {
        JOptionPane.showMessageDialog(this, "error checking");
      }
      */
    }
  }

  private Integer generateUniqueEmployeeID()
  {
    int newID = (int) (Math.random() * 9000) + 1000;
    boolean exists = true;
    while (exists)
    {
      newID = (int) (Math.random() * 9000) + 1000;
      exists = false;

      try
      {
        String sql = "SELECT EmployeeLogin FROM employeeID WHERE employeeID = ?";
        PreparedStatement ps = db.getDbConn().prepareStatement(sql);
        ps.setInt(1, newID);

        ResultSet rs = ps.executeQuery();

        if (rs.next())
        {
          exists = true; // ID already exists
        }

        rs.close();
        ps.close();
      }
      catch (Exception e)
      {
        System.out.println("Error checking employeeID uniqueness");
      }
    }
    return newID;
  }
}

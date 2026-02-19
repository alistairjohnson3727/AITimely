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
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author alyj3
 */
public class managerSignupGUI extends JFrame implements ActionListener
{

  private JLabel titleLabel;
  private JLabel userLabel;
  private JLabel passLabel;
  private JTextField userField;
  private JTextField passField;
  private JPanel centerPanel;
  private JButton signUpButton;
  private dbAccess db;

  public managerSignupGUI()
  {
    super("Manager sign up");
    this.setBounds(100, 100, 400, 400);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    titleLabel = new JLabel("Sign Up");
    userLabel = new JLabel("Username: ");
    userField = new JTextField(20);
    userField.addActionListener(this);
    passLabel = new JLabel("Password: ");
    passField = new JTextField(20);
    passField.addActionListener(this);
    signUpButton = new JButton("Sign up");
    signUpButton.addActionListener(this);

    centerPanel = new JPanel();
    centerPanel.add(userLabel);
    centerPanel.add(userField);
    centerPanel.add(passLabel);
    centerPanel.add(passField);


    this.add(titleLabel, BorderLayout.NORTH);
    this.add(centerPanel, BorderLayout.CENTER);
    this.add(signUpButton, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();

    if (command.equals("Sign up"))
    {
      if (command.equals("Sign up"))
      {
        try
        {
          int managerID = generateUniqueManagerID();
          String username = userField.getText().trim();
          String password = passField.getText().trim();

          dbAccess db = new dbAccess("iaTimely");
          boolean success = db.addManagerAcc(managerID, username, password);
          db.closeDbConn();

          if (success)
          {
            JOptionPane.showMessageDialog(this, "Manager account created! your id is " + managerID);
          }
          else
          {
            JOptionPane.showMessageDialog(this, "Sign up failed.");
          }
        }
        catch (NumberFormatException ex)
        {
          JOptionPane.showMessageDialog(this, "Invalid Manager ID");
        }
      }
    }
  }
  
  private Integer generateUniqueManagerID()
  {
    int newID;
    boolean exists;

    do
    {
      newID = (int)(Math.random() * 9000) + 1000;
      exists = false;

      try
      {
        String sql = "SELECT ManagerLogin FROM managerID WHERE managerID = ?";
        PreparedStatement ps = db.getDbConn().prepareStatement(sql);
        ps.setInt(1, newID);

        ResultSet rs = ps.executeQuery();

        if(rs.next())
        {
          exists = true; // ID already exists
        }

        rs.close();
        ps.close();
      }
      catch(Exception e)
      {
        System.out.println("Error checking managerID uniqueness");
      }

    } while(exists);

    return newID;
  }
}

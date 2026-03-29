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
  private JButton closeButton;
  private JPanel buttonPanel;
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
    closeButton = new JButton("Back");
    closeButton.addActionListener(this);

    centerPanel = new JPanel();
    centerPanel.add(userLabel);
    centerPanel.add(userField);
    centerPanel.add(passLabel);
    centerPanel.add(passField);
    
    buttonPanel = new JPanel();
    buttonPanel.add(signUpButton);
    buttonPanel.add(closeButton);

    this.add(titleLabel, BorderLayout.NORTH);
    this.add(centerPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  public void actionPerformed(ActionEvent e)
  {
    int managerID = generateUniqueManagerID();
    String username = userField.getText().trim();
    String password = passField.getText().trim();

    dbAccess db = new dbAccess("iaTimely");
    String command = e.getActionCommand();

    if (command.equals("Sign up"))
    {
      if (db.isManUserAvailable(username))
      {
        try
        {

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
          JOptionPane.showMessageDialog(this, "Error signing up");
        }
      }
      else
      {
        JOptionPane.showMessageDialog(this, "username taken");
      }
    }
    else if(command.equals("Back"))
    {
      new StartGUI();
      this.dispose();
    }
  }

  private Integer generateUniqueManagerID()
  {
    int newID;
    boolean exists;

    do
    {
      newID = (int) (Math.random() * 9000) + 1000;
      exists = false;

      try
      {
        String sql = "SELECT ManagerLogin FROM managerID WHERE managerID = ?";
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
        System.out.println("Error checking managerID uniqueness");
      }

    } while (exists);

    return newID;
  }
}

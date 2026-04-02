//Alistair Johnson
//This class allows the user to sign up as a employee
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
 * GUI for creating a new Employee account
 */
public class employeeSignUpGUI extends JFrame implements ActionListener
{

  // Labels and input fields
  private JLabel titleLabel;
  private JLabel userLabel;
  private JLabel passLabel;
  private JLabel idLabel;
  private JTextField userField;
  private JTextField passField;
  private JTextField idField;

  // Panels for layout
  private JPanel centerPanel;
  private JPanel buttonPanel;

  // Buttons
  private JButton signUpButton;
  private JButton closeButton;

  // Database access object
  private dbAccess db;

  // Constructor: sets up the GUI
  public employeeSignUpGUI()
  {
    super("Employee Sign Up"); // window title
    this.setBounds(100, 100, 400, 400); // window size and position
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    // Create labels and text fields
    titleLabel = new JLabel("Sign Up");
    userLabel = new JLabel("Username: ");
    userField = new JTextField(20);
    passLabel = new JLabel("Password: ");
    passField = new JTextField(20);
    idLabel = new JLabel("Manager ID: ");
    idField = new JTextField(20);

    // Create buttons
    signUpButton = new JButton("Sign up");
    signUpButton.addActionListener(this);
    closeButton = new JButton("Back");
    closeButton.addActionListener(this);

    // Center panel: holds input fields
    centerPanel = new JPanel();
    centerPanel.add(userLabel);
    centerPanel.add(userField);
    centerPanel.add(passLabel);
    centerPanel.add(passField);
    centerPanel.add(idLabel);
    centerPanel.add(idField);

    // Button panel: holds buttons
    buttonPanel = new JPanel();
    buttonPanel.add(signUpButton);
    buttonPanel.add(closeButton);

    // Add panels and labels to frame
    this.add(titleLabel, BorderLayout.NORTH);
    this.add(centerPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);

    this.setVisible(true); // show GUI
  }

  // Handle button clicks
  @Override
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == closeButton)
    {
      new StartGUI();
      this.dispose();
      return;
    }

    if (e.getSource() == signUpButton)
    {
      dbAccess db = new dbAccess("iaTimely");

      String username = userField.getText().trim();
      int managerID;

      try
      {
        managerID = Integer.parseInt(idField.getText().trim());
      }
      catch (NumberFormatException ex)
      {
        JOptionPane.showMessageDialog(this, "Invalid Manager ID");
        return;
      }

      if ((db.isEmpUserAvailable(username)) && (db.managerExists(managerID)))
      {
        int employeeID = db.generateUniqueEmployeeID();
        String password = passField.getText().trim();

        boolean success = db.addEmployeeAcc(employeeID, username, password);
        boolean success2 = db.addEmployeeManager(managerID, employeeID);
        db.closeDbConn();

        if (success && success2)
        {
          JOptionPane.showMessageDialog(this, "Employee account created! Your ID is " + employeeID);
        }
      }
      else
      {
        JOptionPane.showMessageDialog(this, "Username taken and/or manager code does not exist.");
      }
    }
  }
}

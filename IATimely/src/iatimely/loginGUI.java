//Alistair Johnson
//This is the login gui for both employees and managers 
package iatimely;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class loginGUI extends JFrame implements ActionListener
{

  // Labels for title, username, and password
  private JLabel title;
  private JLabel userLabel;
  private JLabel passLabel;

  // Text fields to input username and password
  private JTextField userField;
  private JTextField passField;

  // Buttons for selecting login type or going back
  private JButton employeeButton;
  private JButton managerButton;
  private JButton closeButton;

  // Panels to organize buttons and text fields
  private JPanel buttonPanel;
  private JPanel centerPanel;

  // Constructor to set up GUI
  public loginGUI()
  {
    super("Login"); // Set window title
    this.setBounds(100, 100, 400, 500); // Set window size and position
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Close only this window
    this.setLayout(new BorderLayout()); // Use BorderLayout for main frame

    // Create title label at the top
    title = new JLabel("Login Page", JLabel.CENTER);

    // Create username label and text field
    userLabel = new JLabel("Username:");
    userField = new JTextField(20);

    // Create password label and text field
    passLabel = new JLabel("Password:");
    passField = new JTextField(20);

    // Create buttons and add ActionListener to each
    employeeButton = new JButton("Employee");
    employeeButton.addActionListener(this);

    managerButton = new JButton("Manager");
    managerButton.addActionListener(this);

    closeButton = new JButton("Back");
    closeButton.addActionListener(this);

    // Panel to hold login buttons
    buttonPanel = new JPanel();
    buttonPanel.add(employeeButton);
    buttonPanel.add(managerButton);
    buttonPanel.add(closeButton);

    // Panel to hold username/password fields
    centerPanel = new JPanel();
    centerPanel.add(userLabel);
    centerPanel.add(userField);
    centerPanel.add(passLabel);
    centerPanel.add(passField);

    // Add components to the frame
    this.add(title, BorderLayout.NORTH);
    this.add(centerPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);

    this.setVisible(true); // Show the window
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand(); // Get button text
    String username = userField.getText().trim(); // Get username input
    String password = passField.getText().trim(); // Get password input

    dbAccess db = new dbAccess("iaTimely"); // Open database connection

    // Handle employee login
    if (command.equals("Employee"))
    {
      boolean valid = db.checkEmployeeLogin(username, password); // Check credentials
      if (valid) // If login successful
      {
        int id = db.getEmployeeIDByUsername(username); // Get employee ID
        if (id == -1)
        {
          // Show error if ID not found
          popupMessageGUI.show(this, "error finding id");
        }
        else
        {
          // Create Employee object and open employee dashboard
          Employee emp = new Employee(username, password, id);
          new EmployeeGUI(emp);
          this.dispose(); // Close login window
        }
      }
      else
      {
        // Show invalid login message
        popupMessageGUI.show(this, "Invalid employee login");
      }
    }
    // Handle manager login
    else if (command.equals("Manager"))
    {
      boolean valid = db.checkManagerLogin(username, password); // Check credentials
      if (valid)
      {
        int id = db.getManagerIDByUsername(username); // Get manager ID
        if (id == -1)
        {
          popupMessageGUI.show(this, "error getting id");
        }
        else
        {
          // Create Manager object and open manager dashboard
          Manager man = new Manager(username, password, id);
          new ManagerGUI(man);
          this.dispose(); // Close login window
        }
      }
      else
      {
        // Show invalid login message
        popupMessageGUI.show(this, "Invalid manager login");
      }
    }
    // Handle Back button
    else if (command.equals("Back"))
    {
      new StartGUI(); // Return to start page
      this.dispose(); // Close login window
    }

    db.closeDbConn(); // Close database connection at the end
  }
}

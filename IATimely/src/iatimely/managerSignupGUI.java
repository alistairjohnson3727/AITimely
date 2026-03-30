//Alistair Johnson
//This gui allows him to sign up as a manager
package iatimely;

// Import layout and GUI components
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
 * Manager Signup GUI class. Provides a user interface for creating new manager
 * accounts in the system.
 */
public class managerSignupGUI extends JFrame implements ActionListener
{

  // Title label at the top of the frame
  private JLabel titleLabel;

  // Label for the username field
  private JLabel userLabel;

  // Label for the password field
  private JLabel passLabel;

  // Text field for entering username
  private JTextField userField;

  // Text field for entering password
  private JTextField passField;

  // Panel containing the username/password fields
  private JPanel centerPanel;

  // Button to submit signup form
  private JButton signUpButton;

  // Button to go back to previous GUI
  private JButton closeButton;

  // Panel containing the buttons
  private JPanel buttonPanel;

  // Database access object
  private dbAccess db;

  // Constructor sets up the GUI components and layout
  public managerSignupGUI()
  {
    // Set frame title
    super("Manager sign up");

    // Set frame position and size
    this.setBounds(100, 100, 400, 400);

    // Dispose frame when closed
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    // Initialize title label
    titleLabel = new JLabel("Sign Up");

    // Initialize username label and field
    userLabel = new JLabel("Username: ");
    userField = new JTextField(20);
    userField.addActionListener(this);

    // Initialize password label and field
    passLabel = new JLabel("Password: ");
    passField = new JTextField(20);
    passField.addActionListener(this);

    // Initialize buttons and add action listeners
    signUpButton = new JButton("Sign up");
    signUpButton.addActionListener(this);
    closeButton = new JButton("Back");
    closeButton.addActionListener(this);

    // Create center panel and add username/password fields
    centerPanel = new JPanel();
    centerPanel.add(userLabel);
    centerPanel.add(userField);
    centerPanel.add(passLabel);
    centerPanel.add(passField);

    // Create button panel and add buttons
    buttonPanel = new JPanel();
    buttonPanel.add(signUpButton);
    buttonPanel.add(closeButton);

    // Add components to frame
    this.add(titleLabel, BorderLayout.NORTH);
    this.add(centerPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);

    // Make the frame visible
    this.setVisible(true);
  }

  // Handles button clicks and text field actions
  public void actionPerformed(ActionEvent e)
  {
    // Generate a unique manager ID
    int managerID = generateUniqueManagerID();

    // Get the entered username and password
    String username = userField.getText().trim();
    String password = passField.getText().trim();

    // Connect to database
    dbAccess db = new dbAccess("iaTimely");

    // Determine which button was pressed
    String command = e.getActionCommand();

    // If the sign-up button was clicked
    if (command.equals("Sign up"))
    {
      // Check if username is available
      if (db.isManUserAvailable(username))
      {
        try
        {
          // Attempt to add manager account to database
          boolean success = db.addManagerAcc(managerID, username, password);

          // Close database connection
          db.closeDbConn();

          // If account creation succeeded
          if (success)
          {
            // Notify user of success and show their manager ID
            JOptionPane.showMessageDialog(this, "Manager account created! your id is " + managerID);
          }
          else
          {
            // Notify user of failure
            JOptionPane.showMessageDialog(this, "Sign up failed.");
          }
        }
        catch (NumberFormatException ex)
        {
          // Handle invalid input format
          JOptionPane.showMessageDialog(this, "Error signing up");
        }
      }
      else
      {
        // Notify user that username is already taken
        JOptionPane.showMessageDialog(this, "username taken");
      }
    }
    else if (command.equals("Back"))
    {
      // Open the start GUI
      new StartGUI();

      // Close the current frame
      this.dispose();
    }
  }

  // Generates a unique manager ID that does not already exist in the database
  private Integer generateUniqueManagerID()
  {
    int newID;
    boolean exists;

    do
    {
      // Generate random 4-digit number
      newID = (int) (Math.random() * 9000) + 1000;
      exists = false;

      try
      {
        // Prepare SQL query to check if ID already exists
        String sql = "SELECT ManagerLogin FROM managerID WHERE managerID = ?";
        PreparedStatement ps = db.getDbConn().prepareStatement(sql);
        ps.setInt(1, newID);

        // Execute query
        ResultSet rs = ps.executeQuery();

        // If ID exists, set exists to true
        if (rs.next())
        {
          exists = true;
        }

        // Close database resources
        rs.close();
        ps.close();
      }
      catch (Exception e)
      {
        // Handle exception when checking ID uniqueness
        System.out.println("Error checking managerID uniqueness");
      }

    } while (exists);  // Repeat until unique ID is found

    // Return the unique ID
    return newID;
  }
}

//Alistair Johnson
//This class adds shifts to employees 
package iatimely;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * GUI for adding a shift
 */
public class AddShiftGUI extends JFrame implements ActionListener
{

  // GUI components
  private JLabel title;
  private JLabel descriptionLabel;
  private JTextArea descriptionField;
  private JLabel dateLabel;
  private JTextField dateField;
  private JLabel EmployeeLabel;
  private JTextField employeeIDField;
  private JButton addButton;
  private JPanel middlePanel;
  private JButton closeButton;
  private JPanel buttonPanel;

  // Database access object
  private dbAccess db;

  // Constructor: sets up the GUI window and components
  public AddShiftGUI()
  {
    super("Add Shift"); // window title
    this.setBounds(300, 300, 400, 400); // position and size
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // close behavior

    // Initialize labels and input fields
    title = new JLabel("Add Shift");
    descriptionLabel = new JLabel("Description: ");
    descriptionField = new JTextArea();
    dateLabel = new JLabel("Date(YYYY-MM-DD): ");
    dateField = new JTextField(10);
    EmployeeLabel = new JLabel("Employee ID: ");
    employeeIDField = new JTextField(20);

    // Create buttons and attach event listeners
    addButton = new JButton("Add Shift");
    addButton.addActionListener(this);
    closeButton = new JButton("Close");
    closeButton.addActionListener(this);

    // Panel to hold input fields
    middlePanel = new JPanel();
    middlePanel.add(descriptionLabel);
    middlePanel.add(descriptionField);
    middlePanel.add(dateLabel);
    middlePanel.add(dateField);
    middlePanel.add(EmployeeLabel);
    middlePanel.add(employeeIDField);

    // Panel to hold buttons
    buttonPanel = new JPanel();
    buttonPanel.add(addButton);
    buttonPanel.add(closeButton);

    // Add components to the frame
    this.add(title, BorderLayout.NORTH);
    this.add(middlePanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);

    this.setVisible(true); // make window visible
  }

  // Handles button clicks
  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();

    // If "Add Shift" button is clicked
    if (command.equals("Add Shift"))
    {
      int shiftID = generateUniqueShiftID(); // generate unique ID
      int employeeID = Integer.parseInt(employeeIDField.getText()); // get employee ID
      String description = descriptionField.getText().trim(); // get description
      String date = dateField.getText().trim(); // get date

      // Create database connection
      dbAccess db = new dbAccess("iaTimely");

      // Insert shift and link to employee
      boolean success1 = db.addShift(shiftID, description, date);
      boolean success2 = db.addShiftEmployee(shiftID, employeeID);

      db.closeDbConn(); // close connection

      // Show success or error message
      if (success1 && success2)
      {
        JOptionPane.showMessageDialog(this, "Shift Added! shift id is " + shiftID);
      }
      else
      {
        JOptionPane.showMessageDialog(this, "Error Adding Shift.");
      }
    }
    // If "Close" button is clicked
    else if (command.equals("Close"))
    {
      this.dispose(); // close the window
    }
  }

  // Generates a random unique shift ID (4-digit number)
  private Integer generateUniqueShiftID()
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
        // Check if ID already exists in database
        String sql = "SELECT shiftID FROM Shift WHERE shiftID = ?";
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
        System.out.println("Error checking shiftID uniqueness");
      }

    } while (exists); // repeat until unique ID is found

    return newID;
  }
}

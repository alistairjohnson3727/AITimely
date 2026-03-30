//Alistair Johnson
package iatimely;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *. Allows a manager to input an
 * employee ID and open a table showing that employee's shifts.
 */
public class ViewShiftGUI extends JFrame implements ActionListener
{

  // Label for the window title
  private JLabel title;

  // Label and text field for entering the employee ID
  private JLabel empLabel;
  private JTextField empIDField;

  // Button to trigger the viewing of shifts
  private JButton viewButton;

  // Panels to organize the GUI components
  private JPanel middlePanel;
  private JPanel buttonPanel;

  // Reference to the manager using the GUI
  private Manager man;

  // Database access object for querying employee and shift information
  private dbAccess db;

  /**
   * Constructor: sets up the GUI components and layout
   *
   * @param m the Manager object representing the logged-in manager
   */
  public ViewShiftGUI(Manager m)
  {
    super("View Shifts"); // Set window title
    this.setBounds(300, 300, 400, 400); // Set window size and position
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Close only this window

    man = m;

    // Initialize database connection
    db = new dbAccess("iaTimely");

    // Initialize GUI components
    title = new JLabel("View Shifts", SwingConstants.CENTER); // Centered title
    empLabel = new JLabel("Employee ID: "); // Label for employee input
    empIDField = new JTextField(20); // Input field for employee ID

    viewButton = new JButton("View Shift"); // Button to load shifts
    viewButton.addActionListener(this); // Attach action listener

    // Middle panel holds input fields
    middlePanel = new JPanel();
    middlePanel.add(empLabel);
    middlePanel.add(empIDField);

    // Button panel holds action buttons
    buttonPanel = new JPanel();
    buttonPanel.add(viewButton);

    // Add components to the frame
    this.add(title, BorderLayout.NORTH);
    this.add(middlePanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);

    // Make window visible
    this.setVisible(true);
  }

  /**
   * Handles button clicks
   *
   * @param e the action event triggered by a button click
   */
  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();

    // If the "View Shift" button is clicked
    if (command.equals("View Shift"))
    {
      try
      {
        // Get employee ID from the input field
        int employeeID = Integer.parseInt(empIDField.getText().trim());

        // Check if this employee is under the logged-in manager
        boolean check1 = db.isEmployeeUnderManager(employeeID, man.getManID());

        if (check1)
        {
          // Open a new window displaying the employee's shifts
          new ViewTableGUI(employeeID);
        }
        else
        {
          // Show error if the employee is not under this manager
          JOptionPane.showMessageDialog(this, "Error viewing shift. Employee is not under you.");
        }
      }
      catch (Exception ex)
      {
        // Show error if input is invalid or another exception occurs
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
      }
    }
  }
}

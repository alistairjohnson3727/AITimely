//Alistair Johnson
//This gui lets the manager removes shifts from employees
package iatimely;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * GUI for removing a shift
 */
public class RemoveShiftGUI extends JFrame implements ActionListener
{

  // Labels and input field
  private JLabel title;
  private JLabel shiftLabel;
  private JTextField shiftIDField;

  private JLabel empLabel;
  private JTextField empIDField;

  // Buttons
  private JButton removeButton;

  // Panels
  private JPanel middlePanel;
  private JPanel buttonPanel;

  private Manager man;

  // Constructor: sets up the GUI
  public RemoveShiftGUI(Manager m)
  {
    super("Add Shift"); // window title (should probably be "Remove Shift")
    this.setBounds(300, 300, 400, 400); // position and size
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // close behavior

    man = m;

    // Create components
    title = new JLabel("Remove Shift");
    shiftLabel = new JLabel("Shift ID: ");
    shiftIDField = new JTextField(20);

    empLabel = new JLabel("Employee ID: ");
    empIDField = new JTextField(20);

    // Create buttons and add listeners
    removeButton = new JButton("Remove Shift");
    removeButton.addActionListener(this);

    // Panel for input
    middlePanel = new JPanel();
    middlePanel.add(shiftLabel);
    middlePanel.add(shiftIDField);
    middlePanel.add(empLabel);
    middlePanel.add(empIDField);

    // Panel for buttons
    buttonPanel = new JPanel();
    buttonPanel.add(removeButton);

    // Add components to frame
    this.add(title, BorderLayout.NORTH);
    this.add(middlePanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);

    this.setVisible(true); // show window
  }

  // Handles button clicks
  @Override
  public void actionPerformed(ActionEvent e)
  {
    // Create database connection
    dbAccess db = new dbAccess("iaTimely");
    String command = e.getActionCommand();
    int shiftID = Integer.parseInt(shiftIDField.getText());
    int employeeID = Integer.parseInt(empIDField.getText());
    boolean check1 = db.isEmployeeUnderManager(employeeID, man.getManID());
    boolean check2 = db.isEmpHaveShift(shiftID, employeeID);
    // If "Remove Shift" button is clicked
    if (command.equals("Remove Shift"))
    {
      if (check1 && check2)
      {
        // Remove shift from employee and shift tables
        boolean success2 = db.removeEmployeeShift(shiftID);
        boolean success1 = db.removeShift(shiftID);

        db.closeDbConn(); // close connection

        // Show result message
        if (success1 && success2)
        {
          JOptionPane.showMessageDialog(this, "Shift Removed!");
        }
        else
        {
          JOptionPane.showMessageDialog(this, "Error removing");
        }
      }
    }
  }
}

//Alistair Johnson
//This class updates shifts from employees
package iatimely;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * GUI for updating an existing shift
 */
public class UpdateShiftGUI extends JFrame implements ActionListener
{

  // Labels and input fields
  private JLabel title;
  private JLabel newDescriptionLabel;
  private JTextArea newDescriptionField;
  private JLabel newDateLabel;
  private JTextField newDateField;
  private JLabel shiftLabel;
  private JTextField shiftIDField;
  private JLabel empLabel;
  private JTextField empIDField;

  // Buttons
  private JButton updateButton;
  private JButton closeButton;

  // Panels
  private JPanel middlePanel;
  private JPanel buttonPanel;

  private Manager man;

  // Constructor: sets up the GUI
  public UpdateShiftGUI(Manager m)
  {
    super("Update Shift"); // window title
    this.setBounds(300, 300, 300, 300); // position and size
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // close behavior

    man = m;
    // Create labels and input fields
    title = new JLabel("Update Shift");
    newDescriptionLabel = new JLabel("New Description: ");
    newDescriptionField = new JTextArea();
    newDateLabel = new JLabel("New Date(YYYY-MM-DD): ");
    newDateField = new JTextField(10);
    shiftLabel = new JLabel("Shift ID: ");
    shiftIDField = new JTextField(20);
    empLabel = new JLabel("Employee ID: ");
    empIDField = new JTextField(20);
    // Create buttons and add listeners
    updateButton = new JButton("Update Shift");
    updateButton.addActionListener(this);
    closeButton = new JButton("Close");
    closeButton.addActionListener(this);

    // Panel for input fields
    middlePanel = new JPanel();
    middlePanel.add(newDescriptionLabel);
    middlePanel.add(newDescriptionField);
    middlePanel.add(newDateLabel);
    middlePanel.add(newDateField);
    middlePanel.add(shiftLabel);
    middlePanel.add(shiftIDField);
    middlePanel.add(empLabel);
    middlePanel.add(empIDField);

    // Panel for buttons
    buttonPanel = new JPanel();
    buttonPanel.add(updateButton);
    buttonPanel.add(closeButton);

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
    String command = e.getActionCommand();

    // If "Update Shift" button is clicked
    if (command.equals("Update Shift"))
    {
      // Get input values
      int shiftID = Integer.parseInt(shiftIDField.getText());
      int empID = Integer.parseInt(empIDField.getText());
      String description = newDescriptionField.getText().trim();
      String date = newDateField.getText().trim();

      // Update shift in database
      dbAccess db = new dbAccess("iaTimely");
      boolean check1 = db.isEmpHaveShift(shiftID, empID);
      boolean check2 = db.isEmployeeUnderManager(empID, man.getManID());
      if (check1 && check2)
      {
        boolean update = db.updateShift(shiftID, description, date);
        db.closeDbConn();

        // Show result message
        if (update)
        {
          JOptionPane.showMessageDialog(this, "Shift updated!");
        }
        else
        {
          JOptionPane.showMessageDialog(this, "Error Updating");
        }
      }
      else
      {
        JOptionPane.showMessageDialog(this, "This employee is not under you and/or shift does not belong to employee");
      }
    }
    // If "Close" button is clicked
    else if (command.equals("Close"))
    {
      this.dispose(); // close window
    }
  }
}

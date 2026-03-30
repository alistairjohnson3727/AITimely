//Alistair Johnson
//This gui lets the manager removes shifts from employees
package iatimely;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * GUI for removing a shift
 */
public class RemoveShiftGUI extends JFrame implements ActionListener
{

  // Labels and input fields
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

  // Database access
  private dbAccess db;

  public RemoveShiftGUI(Manager m)
  {
    super("Remove Shift"); // Fixed title
    this.setBounds(300, 300, 400, 400);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    man = m;

    // Initialize database connection
    db = new dbAccess("iaTimely");

    // Initialize components
    title = new JLabel("Remove Shift", SwingConstants.CENTER);
    shiftLabel = new JLabel("Shift ID: ");
    shiftIDField = new JTextField(20);
    empLabel = new JLabel("Employee ID: ");
    empIDField = new JTextField(20);

    removeButton = new JButton("Remove Shift");
    removeButton.addActionListener(this);

    middlePanel = new JPanel();
    middlePanel.add(shiftLabel);
    middlePanel.add(shiftIDField);
    middlePanel.add(empLabel);
    middlePanel.add(empIDField);

    buttonPanel = new JPanel();
    buttonPanel.add(removeButton);

    this.add(title, BorderLayout.NORTH);
    this.add(middlePanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);

    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();

    if (command.equals("Remove Shift"))
    {
      try
      {
        int shiftID = Integer.parseInt(shiftIDField.getText().trim());
        int employeeID = Integer.parseInt(empIDField.getText().trim());

        // Check if employee belongs to this manager
        boolean check1 = db.isEmployeeUnderManager(employeeID, man.getManID());
        // Check if employee has this shift
        boolean check2 = db.isEmpHaveShift(shiftID, employeeID);

        if (check1 && check2)
        {
          // Remove shift assignment for employee first
          boolean success2 = db.removeEmployeeShift(shiftID);
          // Then remove the shift itself
          boolean success1 = db.removeShift(shiftID);

          if (success1 && success2)
          {
            JOptionPane.showMessageDialog(this, "Shift Removed!");
          }
          else
          {
            JOptionPane.showMessageDialog(this, "Error removing shift.");
          }
        }
        else
        {
          JOptionPane.showMessageDialog(this, "This employee does not have this shift or is not under you.");
        }

      }
      catch (NumberFormatException ex)
      {
        JOptionPane.showMessageDialog(this, "Invalid Shift ID or Employee ID.");
      }
      catch (Exception ex)
      {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
      }
    }
  }
}

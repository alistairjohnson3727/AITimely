package iatimely;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

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
  private JPanel buttonPanel;

  private Manager man;

  // Database access object
  private dbAccess db;

  // Constructor: sets up the GUI window and components
  public AddShiftGUI(Manager m)
  {
    super("Add Shift");
    this.setBounds(300, 300, 400, 400);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    man = m;

    // Initialize database connection
    db = new dbAccess("iaTimely");

    // Initialize labels and input fields
    title = new JLabel("Add Shift", SwingConstants.CENTER);
    descriptionLabel = new JLabel("Description: ");
    descriptionField = new JTextArea(5, 20);
    dateLabel = new JLabel("Date (YYYY-MM-DD): ");
    dateField = new JTextField(10);
    EmployeeLabel = new JLabel("Employee ID: ");
    employeeIDField = new JTextField(20);

    addButton = new JButton("Add Shift");
    addButton.addActionListener(this);

    middlePanel = new JPanel();
    middlePanel.add(descriptionLabel);
    middlePanel.add(new JScrollPane(descriptionField));
    middlePanel.add(dateLabel);
    middlePanel.add(dateField);
    middlePanel.add(EmployeeLabel);
    middlePanel.add(employeeIDField);

    buttonPanel = new JPanel();
    buttonPanel.add(addButton);

    this.add(title, BorderLayout.NORTH);
    this.add(middlePanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);

    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
    if (command.equals("Add Shift"))
    {
      try
      {
        int employeeID = Integer.parseInt(employeeIDField.getText());

        if (db.isEmployeeUnderManager(employeeID, man.getManID()))
        {
          int shiftID = db.generateUniqueShiftID();
          String description = descriptionField.getText().trim();
          String date = dateField.getText().trim();

          boolean success1 = db.addShift(shiftID, description, date);
          boolean success2 = db.addShiftEmployee(shiftID, employeeID);

          if (success1 && success2)
          {
            JOptionPane.showMessageDialog(this, "Shift Added! Shift ID: " + shiftID);
          }
          else
          {
            JOptionPane.showMessageDialog(this, "Error Adding Shift.");
          }
        }
        else
        {
          JOptionPane.showMessageDialog(this, "You do not have this employee.");
        }
      }
      catch (NumberFormatException ex)
      {
        JOptionPane.showMessageDialog(this, "Invalid Employee ID.");
      }
      catch (Exception ex)
      {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
      }
    }
  }
}

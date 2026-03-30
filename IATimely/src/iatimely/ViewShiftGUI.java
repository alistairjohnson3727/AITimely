//Alistair Johnson
package iatimely;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * GUI for removing a shift
 */
public class ViewShiftGUI extends JFrame implements ActionListener
{

  // Labels and input fields
  private JLabel title;
  private JLabel empLabel;
  private JTextField empIDField;

  // Buttons
  private JButton viewButton;

  // Panels
  private JPanel middlePanel;
  private JPanel buttonPanel;

  private Manager man;

  // Database access
  private dbAccess db;

  public ViewShiftGUI(Manager m)
  {
    super("Remove Shift"); // Fixed title
    this.setBounds(300, 300, 400, 400);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    man = m;

    // Initialize database connection
    db = new dbAccess("iaTimely");

    // Initialize components
    title = new JLabel("View Shifts", SwingConstants.CENTER);
    empLabel = new JLabel("Employee ID: ");
    empIDField = new JTextField(20);

    viewButton = new JButton("View Shift");
    viewButton.addActionListener(this);

    middlePanel = new JPanel();
    middlePanel.add(empLabel);
    middlePanel.add(empIDField);

    buttonPanel = new JPanel();
    buttonPanel.add(viewButton);

    this.add(title, BorderLayout.NORTH);
    this.add(middlePanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);

    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();

    if (command.equals("View Shift"))
    {
      try
      {
        int employeeID = Integer.parseInt(empIDField.getText().trim());

        // Check if employee belongs to this manager
        boolean check1 = db.isEmployeeUnderManager(employeeID, man.getManID());

        if (check1)
        {
          new ViewTableGUI(employeeID);
        }
        else
        {
          JOptionPane.showMessageDialog(this, "Error viewing shift.");
        }
      }
      catch (Exception ex)
      {
        JOptionPane.showMessageDialog(this, "Error " + ex.getMessage());
      }
    }
  }
}

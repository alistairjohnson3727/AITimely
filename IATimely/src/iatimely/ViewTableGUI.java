//Alistair Johnson
package iatimely;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

public class ViewTableGUI extends JFrame implements ActionListener
{

  dbAccess db = new dbAccess();

  //reference to the manager viewing the shifts
  private Manager man;

  // JTable component to display shifts
  private JTable tblShifts;

  // Scroll pane for the table (enables scrolling if there are many shifts)
  private JScrollPane scrollPane;

  /**
   * Constructor: initializes the GUI and loads the employee's shifts.
   *
   * @param empID the employee ID whose shifts will be displayed
   */
  public ViewTableGUI(int empID)
  {
    super("View Shift"); // Set window title
    this.setBounds(300, 300, 400, 400); // Set window size and position
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Close only this window

    // Initialize database connection
    db = new dbAccess("iaTimely");

    // Initialize JTable and wrap it in a scroll pane
    tblShifts = new JTable();
    scrollPane = new JScrollPane(tblShifts);

    // Add the scroll pane (with the table) to the center of the window
    this.add(scrollPane, BorderLayout.CENTER);

    // Load the shifts for the given employee ID
    tblShifts.setModel(db.loadEmployeeShifts(empID));

    // Make the window visible
    this.setVisible(true);
  }

  /**
   * Action handler (not implemented in this GUI)
   *
   * @param e the action event
   */
  @Override
  public void actionPerformed(ActionEvent e)
  {
    // Currently not supported; no buttons or actions defined
    throw new UnsupportedOperationException("Not supported yet.");
  }
}

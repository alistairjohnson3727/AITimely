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

  // Optional: reference to the manager viewing the shifts
  private Manager man;

  // JTable component to display shifts
  private JTable tblShifts;

  // Scroll pane for the table (enables scrolling if there are many shifts)
  private JScrollPane scrollPane;

  // Database access object for querying shift information
  private dbAccess db;

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
    loadEmployeeShifts(empID);

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

  /**
   * Loads shifts for a specific employee and displays them in the JTable.
   *
   * @param empID the employee ID whose shifts are being loaded
   */
  private void loadEmployeeShifts(int empID)
  {
    // Create a new database access object
    dbAccess db = new dbAccess("iaTimely");

    try
    {
      // SQL query: join Shift and ShiftEmployee tables to get the employee's shifts
      String sql = "SELECT Shift.shiftID, Shift.description, Shift.date "
        + "FROM Shift "
        + "JOIN ShiftEmployee ON Shift.shiftID = ShiftEmployee.shiftID "
        + "WHERE ShiftEmployee.employeeID = ? "
        + "ORDER BY Shift.date ASC";

      // Prepare statement to prevent SQL injection
      java.sql.PreparedStatement ps = db.getDbConn().prepareStatement(sql);
      ps.setInt(1, empID); // Set employee ID in the query

      // Execute query and get results
      java.sql.ResultSet rs = ps.executeQuery();

      // Define column names for the table
      String[] columns =
      {
        "Shift ID", "Description", "Date"
      };

      // Create a table model with column names and 0 rows initially
      DefaultTableModel model = new DefaultTableModel(columns, 0);

      // Loop through result set and add each shift as a row in the table model
      while (rs.next())
      {
        model.addRow(new Object[]
        {
          rs.getInt("shiftID"),
          rs.getString("description"),
          rs.getString("date")
        });
      }

      // Assign the model to the JTable to display the data
      tblShifts.setModel(model);

      // Close database resources
      rs.close();
      ps.close();
    }
    catch (Exception e)
    {
      // Show an error message if loading fails
      JOptionPane.showMessageDialog(this, "Error loading shifts: " + e.getMessage());
    }
  }
}

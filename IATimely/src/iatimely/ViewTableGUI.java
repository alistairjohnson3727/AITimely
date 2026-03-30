//Alistair Johnson
package iatimely;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 * GUI for viewing a shift
 */
public class ViewTableGUI extends JFrame implements ActionListener
{
  private Manager man;
  
    // Table to show shifts
  private JTable tblShifts;
  private JScrollPane scrollPane;
  // Database access
  private dbAccess db;

  public ViewTableGUI(int empID)
  {
    super("View Shift"); // Fixed title
    this.setBounds(300, 300, 400, 400);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    // Initialize database connection
    db = new dbAccess("iaTimely");
    tblShifts = new JTable();
    scrollPane = new JScrollPane(tblShifts);
    
    this.add(scrollPane, BorderLayout.CENTER);
    loadEmployeeShifts(empID);
    
    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }
  
  private void loadEmployeeShifts(int empID)
  {
    dbAccess db = new dbAccess("iaTimely");

    try
    {
      // SQL query to get shifts for this employee
      String sql
        = "SELECT Shift.shiftID, Shift.description, Shift.date "
        + "FROM Shift "
        + "JOIN ShiftEmployee ON Shift.shiftID = ShiftEmployee.shiftID "
        + "WHERE ShiftEmployee.employeeID = ? "
        + "ORDER BY Shift.date ASC";

      // Prepare and execute query
      java.sql.PreparedStatement ps = db.getDbConn().prepareStatement(sql);
      ps.setInt(1, empID);

      java.sql.ResultSet rs = ps.executeQuery();

      // Table column names
      String[] columns =
      {
        "Shift ID", "Description", "Date"
      };

      // Create table model
      DefaultTableModel model = new DefaultTableModel(columns, 0);

      // Add rows from database to table
      while (rs.next())
      {
        model.addRow(new Object[]
        {
          rs.getInt("shiftID"),
          rs.getString("description"),
          rs.getString("date")
        });
      }

      // Set table model
      tblShifts.setModel(model);

      // Close resources
      rs.close();
      ps.close();
    }
    catch (Exception e)
    {
      // Show error message if something goes wrong
      JOptionPane.showMessageDialog(this, "Error loading shifts: " + e.getMessage());
    }
  }
}


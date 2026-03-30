//Alistair Johnson
//This is the employee GUI
package iatimely;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class EmployeeGUI extends JFrame implements ActionListener
{

  // Labels to display info
  private JLabel title;
  private JLabel date;
  private JLabel EmployeeID;

  // Button for logging out
  private JButton closeButton;

  // Table to show shifts
  private JTable tblShifts;
  private JScrollPane scrollPane;

  // Constructor: sets up the employee dashboard
  public EmployeeGUI(Employee emp)
  {
    super("Employee Dashboard"); // window title
    this.setBounds(150, 150, 400, 300); // position and size
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    // Get current date
    LocalDate currentDate = LocalDate.now();

    // Create labels with employee info
    title = new JLabel("Welcome " + emp.getUser() + "!", JLabel.CENTER);
    date = new JLabel("Current Date: " + currentDate, JLabel.CENTER);
    EmployeeID = new JLabel("Employee ID: " + emp.getEmployeeID(), JLabel.CENTER);

    // Panel for top section (title + info)
    JPanel topPanel = new JPanel(new GridLayout(3, 1));
    topPanel.add(title);
    topPanel.add(EmployeeID);
    topPanel.add(date);

    // Create logout button
    closeButton = new JButton("Log out");
    closeButton.addActionListener(this);

    // Create table to display shifts
    tblShifts = new JTable();
    scrollPane = new JScrollPane(tblShifts);

    // Add components to frame
    this.add(topPanel, BorderLayout.NORTH);
    this.add(scrollPane, BorderLayout.CENTER);
    this.add(closeButton, BorderLayout.SOUTH);

    // Load employee shifts automatically
    loadEmployeeShifts(emp);

    this.setVisible(true); // show window
  }

  // Handles button clicks
  @Override
  public void actionPerformed(ActionEvent err)
  {
    String command = err.getActionCommand();

    // If logout button is clicked
    if (command.equals("Log out"))
    {
      new StartGUI(); // go back to start screen
      this.dispose(); // close current window
    }
  }

  // Loads shifts for the given employee from the database
  private void loadEmployeeShifts(Employee emp)
  {
    dbAccess db = new dbAccess("iaTimely");

    try
    {
      // Get employee ID from object
      int employeeID = emp.getEmployeeID();

      // Check if ID is valid
      if (employeeID == -1)
      {
        JOptionPane.showMessageDialog(this, "Employee ID not set!");
        return;
      }

      // SQL query to get shifts for this employee
      String sql
        = "SELECT Shift.shiftID, Shift.description, Shift.date "
        + "FROM Shift "
        + "JOIN ShiftEmployee ON Shift.shiftID = ShiftEmployee.shiftID "
        + "WHERE ShiftEmployee.employeeID = ? "
        + "ORDER BY Shift.date ASC";

      // Prepare and execute query
      java.sql.PreparedStatement ps = db.getDbConn().prepareStatement(sql);
      ps.setInt(1, employeeID);

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

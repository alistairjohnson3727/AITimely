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

  private JLabel title;
  private JLabel date;
  private JLabel EmployeeID;
  private JButton closeButton;
  private JTable tblShifts;
  private JScrollPane scrollPane;

  public EmployeeGUI(Employee emp)
  {
    super("Employee Dashboard");
    this.setBounds(150, 150, 400, 300);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    LocalDate currentDate = LocalDate.now();

    title = new JLabel("Welcome " + emp.getUser() + "!", JLabel.CENTER);
    date = new JLabel("Current Date: " + currentDate, JLabel.CENTER);
    EmployeeID = new JLabel("Employee ID: " + emp.getEmployeeID(), JLabel.CENTER);
    JPanel topPanel = new JPanel(new GridLayout(3, 1));
    topPanel.add(title);
    topPanel.add(EmployeeID);
    topPanel.add(date);

    closeButton = new JButton("Log out");
    closeButton.addActionListener(this);
    
    tblShifts = new JTable();
    scrollPane = new JScrollPane(tblShifts);

    this.add(topPanel, BorderLayout.NORTH);
    this.add(scrollPane, BorderLayout.CENTER);
    this.add(closeButton,BorderLayout.SOUTH);
    
    // Automatically load employee shifts
    loadEmployeeShifts(emp);
    
    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent err)
  {
    String command = err.getActionCommand();
    if(command.equals("Log out"))
    {
      new StartGUI();
      this.dispose();
    }
  }

  private void loadEmployeeShifts(Employee emp)
  {
    dbAccess db = new dbAccess("iaTimely");

    try
    {
      int employeeID = emp.getEmployeeID(); // get employeeID directly from object
      if (employeeID == -1)
      {
        JOptionPane.showMessageDialog(this, "Employee ID not set!");
        return;
      }

      String sql
        = "SELECT Shift.shiftID, Shift.description, Shift.date "
        + "FROM Shift "
        + "JOIN ShiftEmployee ON Shift.shiftID = ShiftEmployee.shiftID "
        + "WHERE ShiftEmployee.employeeID = ? "
        + "ORDER BY Shift.date ASC";

      java.sql.PreparedStatement ps = db.getDbConn().prepareStatement(sql);
      ps.setInt(1, employeeID);

      java.sql.ResultSet rs = ps.executeQuery();

      // Column headers
      String[] columns =
      {
        "Shift ID", "Description", "Date"
      };
      DefaultTableModel model = new DefaultTableModel(columns, 0);

      while (rs.next())
      {
        model.addRow(new Object[]
        {
          rs.getInt("shiftID"),
          rs.getString("description"),
          rs.getString("date")
        });
      }

      tblShifts.setModel(model);

      rs.close();
      ps.close();
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(this, "Error loading shifts: " + e.getMessage());
    }
  }
}

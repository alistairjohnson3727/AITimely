//Alistair Johnson
//This is the manager GUI. It allows to add, remove, update, and view employee shifts
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
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

public class ManagerGUI extends JFrame implements ActionListener
{

  // Labels for displaying info
  private JLabel title;
  private JLabel date;
  private JLabel managerID;

  // Buttons for actions
  private JButton addShift;
  private JButton removeShift;
  private JButton updateShift;
  private JButton closeButton;

  // Panels and table
  private JPanel buttonPanel;
  private JPanel centerPanel;
  private JTable tblEmployees;
  private JScrollPane scrollPane;

  // Constructor: sets up manager dashboard
  public ManagerGUI(Manager man)
  {
    super("Manager Dashboard"); // window title
    this.setBounds(150, 150, 550, 300); // position and size
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    // Get current date
    LocalDate currentDate = LocalDate.now();

    // Create labels with manager info
    title = new JLabel("Welcome " + man.getUser() + "!", JLabel.CENTER);
    date = new JLabel("Current Date: " + currentDate, JLabel.CENTER);
    managerID = new JLabel("Manager ID: " + man.getManID(), JLabel.CENTER);

    // Top panel for title and info
    JPanel topPanel = new JPanel(new GridLayout(3, 1));
    topPanel.add(title);
    topPanel.add(managerID);
    topPanel.add(date);

    // Table to display employees
    tblEmployees = new JTable();
    scrollPane = new JScrollPane(tblEmployees);

    // Create buttons and add listeners
    addShift = new JButton("Add Shift");
    addShift.addActionListener(this);

    removeShift = new JButton("Remove Shift");
    removeShift.addActionListener(this);

    updateShift = new JButton("Update Shift");
    updateShift.addActionListener(this);

    closeButton = new JButton("Log Out");
    closeButton.addActionListener(this);

    // Panel for buttons
    buttonPanel = new JPanel();
    buttonPanel.add(addShift);
    buttonPanel.add(removeShift);
    buttonPanel.add(updateShift);
    buttonPanel.add(closeButton);

    // Add components to frame
    this.add(topPanel, BorderLayout.NORTH);
    this.add(tblEmployees, BorderLayout.CENTER); // shows employee table
    this.add(buttonPanel, BorderLayout.SOUTH);

    // Load employees managed by this manager
    loadEmployees(man);

    this.setVisible(true); // show window
  }

  // Handles button clicks
  @Override
  public void actionPerformed(ActionEvent err)
  {
    String command = err.getActionCommand();

    // Open Add Shift window
    if (command.equals("Add Shift"))
    {
      new AddShiftGUI();
    }
    // Open Remove Shift window
    else if (command.equals("Remove Shift"))
    {
      new RemoveShiftGUI();
    }
    // Open Update Shift window
    else if (command.equals("Update Shift"))
    {
      new UpdateShiftGUI();
    }
    // Open View Shift window
    else if (command.equals("View Shift"))
    {
      new ViewShiftGUI();
    }
    // Log out and return to start screen
    else if (command.equals("Log Out"))
    {
      new StartGUI();
      this.dispose();
    }
  }

  // Loads employees assigned to this manager from the database
  private void loadEmployees(Manager man)
  {
    dbAccess db = new dbAccess("iaTimely");

    try
    {
      // Get manager ID
      int managerID = man.getManID();

      // Check if ID is valid
      if (managerID == -1)
      {
        JOptionPane.showMessageDialog(this, "Manager ID not set!");
        return;
      }

      // SQL query to get employees under this manager
      String sql
        = "SELECT EmployeeLogin.employeeID, EmployeeLogin.username "
        + "FROM EmployeeLogin "
        + "JOIN EmployeeManager ON EmployeeLogin.employeeID = EmployeeManager.employeeID "
        + "WHERE EmployeeManager.managerID = ?";

      // Prepare and execute query
      java.sql.PreparedStatement ps = db.getDbConn().prepareStatement(sql);
      ps.setInt(1, managerID);

      java.sql.ResultSet rs = ps.executeQuery();

      // Column names for table
      String[] columns =
      {
        "employee ID", "username"
      };

      // Create table model
      DefaultTableModel model = new DefaultTableModel(columns, 0);

      // Add rows from database
      while (rs.next())
      {
        model.addRow(new Object[]
        {
          rs.getInt("employeeID"),
          rs.getString("username")
        });
      }

      // Set table model
      tblEmployees.setModel(model);

      // Close resources
      rs.close();
      ps.close();
    }
    catch (Exception e)
    {
      // Show error message
      JOptionPane.showMessageDialog(this, "Error loading employee's: " + e.getMessage());
    }
  }
}

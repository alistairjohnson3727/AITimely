/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

  private JLabel title;
  private JLabel date;
  private JLabel managerID;
  private JButton addShift;
  private JButton removeShift;
  private JButton updateShift;
  private JButton closeButton;
  private JPanel buttonPanel;
  private JPanel centerPanel;
  private JTable tblEmployees;
  private JScrollPane scrollPane;

  public ManagerGUI(Manager man)
  {
    super("Manager Dashboard");
    this.setBounds(150, 150, 550, 300);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    LocalDate currentDate = LocalDate.now();
    title = new JLabel("Welcome " + man.getUser() + "!", JLabel.CENTER);
    date = new JLabel("Current Date: " + currentDate, JLabel.CENTER);
    managerID = new JLabel("Manager ID: " + man.getManID(), JLabel.CENTER);
    
    JPanel topPanel = new JPanel(new GridLayout(3, 1));
    topPanel.add(title);
    topPanel.add(managerID);
    topPanel.add(date);
    
    tblEmployees = new JTable();
    scrollPane = new JScrollPane(tblEmployees);


    addShift = new JButton("Add Shift");
    addShift.addActionListener(this);
    removeShift = new JButton("Remove Shift");
    removeShift.addActionListener(this);
    updateShift = new JButton("Update Shift");
    updateShift.addActionListener(this);
    closeButton = new JButton("Log Out");
    closeButton.addActionListener(this);

    buttonPanel = new JPanel();
    buttonPanel.add(addShift);
    buttonPanel.add(removeShift);
    buttonPanel.add(updateShift);
    buttonPanel.add(closeButton);
    this.add(topPanel, BorderLayout.NORTH);
    this.add(tblEmployees, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);

    loadEmployees(man);

    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent err)
  {
    String command = err.getActionCommand();
    if (command.equals("Add Shift"))
    {
      new AddShiftGUI();
    }
    else if (command.equals("Remove Shift"))
    {
      new RemoveShiftGUI();
    }
    else if (command.equals("Update Shift"))
    {
      new UpdateShiftGUI();
    }
    else if (command.equals("View Shift"))
    {
      new ViewShiftGUI();
    }
    else if (command.equals("Log Out"))
    {
      new StartGUI();
      this.dispose();
    }
  }

  private void loadEmployees(Manager man)
  {
    dbAccess db = new dbAccess("iaTimely");

    try
    {
      int managerID = man.getManID(); // get managerID directly from object
      if (managerID == -1)
      {
        JOptionPane.showMessageDialog(this, "Manager ID not set!");
        return;
      }

      String sql
        = "SELECT EmployeeLogin.employeeID, EmployeeLogin.username "
        + "FROM EmployeeLogin "
        + "JOIN EmployeeManager ON EmployeeLogin.employeeID = EmployeeManager.employeeID "
        + "WHERE EmployeeManager.managerID = ?";

      java.sql.PreparedStatement ps = db.getDbConn().prepareStatement(sql);
      ps.setInt(1, managerID);

      java.sql.ResultSet rs = ps.executeQuery();

      // Column headers
      String[] columns =
      {
        "employee ID", "username"
      };
      DefaultTableModel model = new DefaultTableModel(columns, 0);

      while (rs.next())
      {
        model.addRow(new Object[]
        {
          rs.getInt("employeeID"),
          rs.getString("username")
        });
      }

      tblEmployees.setModel(model);

      rs.close();
      ps.close();
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(this, "Error loading employee's: " + e.getMessage());
    }
  }
}

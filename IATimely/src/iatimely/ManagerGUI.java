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
  dbAccess db = new dbAccess();
  
  // Labels for displaying info
  private JLabel title;
  private JLabel date;
  private JLabel managerID;

  // Buttons for actions
  private JButton addShift;
  private JButton removeShift;
  private JButton updateShift;
  private JButton viewShift;
  private JButton closeButton;

  // Panels and table
  private JPanel buttonPanel;
  private JPanel centerPanel;
  private JTable tblEmployees;
  private JScrollPane scrollPane;

  private Manager man;

  // Constructor: sets up manager dashboard
  public ManagerGUI(Manager m)
  {
    super("Manager Dashboard"); // window title
    this.setBounds(150, 150, 550, 300); // position and size
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    man = m;

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

    viewShift = new JButton("View Shift");
    viewShift.addActionListener(this);

    closeButton = new JButton("Log Out");
    closeButton.addActionListener(this);

    // Panel for buttons
    buttonPanel = new JPanel();
    buttonPanel.add(addShift);
    buttonPanel.add(removeShift);
    buttonPanel.add(updateShift);
    buttonPanel.add(viewShift);
    buttonPanel.add(closeButton);

    // Add components to frame
    this.add(topPanel, BorderLayout.NORTH);
    this.add(tblEmployees, BorderLayout.CENTER); // shows employee table
    this.add(buttonPanel, BorderLayout.SOUTH);

    // Load employees managed by this manager
    tblEmployees.setModel(db.loadEmployees(man));

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
      new AddShiftGUI(man);
    }
    // Open Remove Shift window
    else if (command.equals("Remove Shift"))
    {
      new RemoveShiftGUI(man);
    }
    // Open Update Shift window
    else if (command.equals("Update Shift"))
    {
      new UpdateShiftGUI(man);
    }

    else if (command.equals("View Shift"))
    {
      new ViewShiftGUI(man);
    }
    // Log out and return to start screen
    else if (command.equals("Log Out"))
    {
      new StartGUI();
      this.dispose();
    }
  }
}

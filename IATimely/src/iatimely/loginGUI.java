package iatimely;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class loginGUI extends JFrame implements ActionListener
{

  private JLabel title;
  private JLabel userLabel;
  private JLabel passLabel;

  private JTextField userField;
  private JTextField passField;

  private JButton employeeButton;
  private JButton managerButton;

  private JPanel buttonPanel;
  private JPanel centerPanel;

  public loginGUI()
  {
    super("Login");
    this.setBounds(100, 100, 400, 500);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    title = new JLabel("Login Page", JLabel.CENTER);

    userLabel = new JLabel("Username:");
    userField = new JTextField(20);

    passLabel = new JLabel("Password:");
    passField = new JTextField(20);

    employeeButton = new JButton("Employee");
    employeeButton.addActionListener(this);

    managerButton = new JButton("Manager");
    managerButton.addActionListener(this);

    buttonPanel = new JPanel();
    buttonPanel.add(employeeButton);
    buttonPanel.add(managerButton);

    centerPanel = new JPanel();
    centerPanel.add(userLabel);
    centerPanel.add(userField);
    centerPanel.add(passLabel);
    centerPanel.add(passField);

    this.add(title, BorderLayout.NORTH);
    this.add(centerPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);

    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
    String username = userField.getText().trim();
    String password = passField.getText().trim();

    dbAccess db = new dbAccess("iaTimely");

    if (command.equals("Employee"))
    {
      boolean valid = db.checkEmployeeLogin(username, password);

      if (valid)
      {
        new EmployeeGUI();
        this.dispose();
      }
      else
      {
        popupMessageGUI.show(this, "Invalid employee login");
      }
    }
    else if (command.equals("Manager"))
    {
      boolean valid = db.checkManagerLogin(username, password);

      if (valid)
      {
        new ManagerGUI();
        this.dispose();
      }
      else
      {
        popupMessageGUI.show(this, "Invalid manager login");
      }
    }

    db.closeDbConn();
  }
}

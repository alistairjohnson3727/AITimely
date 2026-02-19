package iatimely;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author alyj3
 */
public class RemoveShiftGUI extends JFrame implements ActionListener
{

  private JLabel title;
  //private JLabel employeeLabel;
  //private JTextField employeeIDField;
  private JLabel shiftLabel;
  private JTextField shiftIDField;
  private JButton removeButton;
  private JPanel middlePanel;

  public RemoveShiftGUI()
  {

    super("Add Shift");
    this.setBounds(300, 300, 400, 400);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    title = new JLabel("Remove Shift");
    shiftLabel = new JLabel("Shift ID: ");
    shiftIDField = new JTextField(20);
    //employeeLabel = new JLabel("Employee ID");
    //employeeIDField = new JTextField(20);
    removeButton = new JButton("Remove Shift");
    removeButton.addActionListener(this);
    middlePanel = new JPanel();

    //middlePanel.add(employeeLabel);
    //middlePanel.add(employeeIDField);
    middlePanel.add(shiftLabel);
    middlePanel.add(shiftIDField);

    this.add(title, BorderLayout.NORTH);
    this.add(middlePanel, BorderLayout.CENTER);
    this.add(removeButton, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
    if (command.equals("Remove Shift"))
    {
      int shiftID = Integer.parseInt(shiftIDField.getText());
      //int employeeID = Integer.parseInt(employeeIDField.getText());

      dbAccess db = new dbAccess("iaTimely");
      boolean success2 = db.removeEmployeeShift(shiftID);
      boolean success1 = db.removeShift(shiftID);

      db.closeDbConn();
      if (success1 && success2)
      {
        JOptionPane.showMessageDialog(this, "Shift Removed!");
      }
      
      else
      {
        JOptionPane.showMessageDialog(this, "Error removing");
      }
    }
  }
}

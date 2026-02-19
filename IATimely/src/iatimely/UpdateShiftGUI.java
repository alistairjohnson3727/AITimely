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
public class UpdateShiftGUI extends JFrame implements ActionListener
{
  private JLabel title;
  private JLabel newDescriptionLabel;
  private JTextArea newDescriptionField;
  //private JLabel oldDateLabel;
  //private JTextField oldDateField;
  private JLabel newDateLabel;
  private JTextField newDateField;
  private JLabel shiftLabel;
  private JTextField shiftIDField;
  private JButton updateButton;
  private JPanel middlePanel;
  public UpdateShiftGUI()
  {

    super("Update Shift");
    this.setBounds(300, 300, 300, 300);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
    title = new JLabel("Update Shift");
    newDescriptionLabel = new JLabel("New Description: ");
    newDescriptionField = new JTextArea();
    //oldDateLabel = new JLabel("Old Date(YYYY-MM-DD): ");
    //oldDateField = new JTextField(10);
    newDateLabel = new JLabel("New Date(YYYY-MM-DD): ");
    newDateField = new JTextField(10);
    shiftLabel = new JLabel("Shift ID: ");
    shiftIDField = new JTextField(20);
    updateButton = new JButton("Update Shift");
    updateButton.addActionListener(this);
    middlePanel = new JPanel();
    
    middlePanel.add(newDescriptionLabel);
    middlePanel.add(newDescriptionField);
    //middlePanel.add(oldDateLabel);
    //middlePanel.add(oldDateField);
    middlePanel.add(newDateLabel);
    middlePanel.add(newDateField);
    middlePanel.add(shiftLabel);
    middlePanel.add(shiftIDField);
    
    this.add(title, BorderLayout.NORTH);
    this.add(middlePanel,BorderLayout.CENTER);
    this.add(updateButton,BorderLayout.SOUTH);
    this.setVisible(true);
  }

@Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
    if(command.equals("Update Shift"))
    {
      int shiftID = Integer.parseInt(shiftIDField.getText());
      String description = newDescriptionField.getText().trim();
      String date = newDateField.getText().trim();
      dbAccess db = new dbAccess("iaTimely");
      boolean update = db.updateShift(shiftID, description, date);
      db.closeDbConn();
      if(update)
      {
        JOptionPane.showMessageDialog(this, "Shift updated!");
      }
      else
      {
        JOptionPane.showMessageDialog(this, "Error Updating"); 
      }
    }
  }

}

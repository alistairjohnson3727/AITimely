package iatimely;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
  private JLabel EmployeeLabel;
  private JTextField employeeIDField;
  private JButton updateButton;
  private JPanel middlePanel;
  public UpdateShiftGUI()
  {

    super("Update Shift");
    this.setBounds(300, 300, 400, 400);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
    title = new JLabel("Update Shift");
    newDescriptionLabel = new JLabel("New Description: ");
    newDescriptionField = new JTextArea();
    //oldDateLabel = new JLabel("Old Date(YYYY-MM-DD): ");
    //oldDateField = new JTextField(10);
    newDateLabel = new JLabel("New Date(YYYY-MM-DD): ");
    newDateField = new JTextField(10);
    EmployeeLabel = new JLabel("Shift ID: ");
    employeeIDField = new JTextField(20);
    updateButton = new JButton("Add Shift");
    updateButton.addActionListener(this);
    middlePanel = new JPanel();
    
    middlePanel.add(newDescriptionLabel);
    middlePanel.add(newDescriptionField);
    //middlePanel.add(oldDateLabel);
    //middlePanel.add(oldDateField);
    middlePanel.add(newDateLabel);
    middlePanel.add(newDateField);
    middlePanel.add(EmployeeLabel);
    middlePanel.add(employeeIDField);
    
    this.add(title, BorderLayout.NORTH);
    this.add(middlePanel,BorderLayout.CENTER);
    this.add(updateButton,BorderLayout.SOUTH);
    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

}

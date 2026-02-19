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
public class RemoveShiftGUI extends JFrame implements ActionListener
{
  private JLabel title;
  private JLabel dateLabel;
  private JTextField dateField;
  private JLabel EmployeeLabel;
  private JTextField employeeIDField;
  private JButton removeButton;
  private JPanel middlePanel;
  public RemoveShiftGUI()
  {

    super("Add Shift");
    this.setBounds(300, 300, 200, 100);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    title = new JLabel("Remove Shift");
    dateLabel = new JLabel("Date(YYYY-MM-DD): ");
    dateField = new JTextField(10);
    EmployeeLabel = new JLabel("Employee ID: ");
    employeeIDField = new JTextField(20);
    removeButton = new JButton("Remove Shift");
    removeButton.addActionListener(this);
    middlePanel = new JPanel();
    

    middlePanel.add(dateLabel);
    middlePanel.add(dateField);
    middlePanel.add(EmployeeLabel);
    middlePanel.add(employeeIDField);
    
    this.add(title, BorderLayout.NORTH);
    this.add(middlePanel,BorderLayout.CENTER);
    this.add(removeButton,BorderLayout.SOUTH);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }

}

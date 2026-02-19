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
public class ViewShiftGUI extends JFrame implements ActionListener
{
  private JLabel title;
  private JLabel dateLabel;
  private JTextField dateField;
  private JButton viewButton;
  private JPanel middlePanel;
  public ViewShiftGUI()
  {

    super("View Shift");
    this.setBounds(300, 300,400, 4100);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    title = new JLabel("View Shift");
    dateLabel = new JLabel("Date(YYYY-MM-DD): ");
    dateField = new JTextField(10);
    viewButton = new JButton("View Shift");
    viewButton.addActionListener(this);
    middlePanel = new JPanel();
    

    middlePanel.add(dateLabel);
    middlePanel.add(dateField);

    
    this.add(title, BorderLayout.NORTH);
    this.add(middlePanel,BorderLayout.CENTER);
    this.add(viewButton,BorderLayout.SOUTH);
    this.setVisible(true);
  }

@Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
    if(command.equals("View Shift"))
    {
      String date = dateField.getText().trim();
      dbAccess db = new dbAccess("iaTimely");
      String description = db.viewShift(date);
      if(description != null)
      {
        JOptionPane.showMessageDialog(this, description);
      }
      else
      {
        JOptionPane.showMessageDialog(this, "Error viewing or does not exist");
      }
    }
  }

}

//Alistair Johnson
//This class allows managers to view shift descriptions 
package iatimely;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * GUI for viewing a shift by date
 */
public class ViewShiftGUI extends JFrame implements ActionListener
{

  // Labels and input field
  private JLabel title;
  private JLabel dateLabel;
  private JTextField dateField;

  // Button to view shift
  private JButton viewButton;

  // Panel for input
  private JPanel middlePanel;

  // Constructor: sets up the GUI
  public ViewShiftGUI()
  {
    super("View Shift"); // window title
    this.setBounds(300, 300, 300, 300); // position and size
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // close behavior

    // Create labels and input
    title = new JLabel("View Shift");
    dateLabel = new JLabel("Date(YYYY-MM-DD): ");
    dateField = new JTextField(10);

    // Create view button
    viewButton = new JButton("View Shift");
    viewButton.addActionListener(this);

    // Panel for input fields
    middlePanel = new JPanel();
    middlePanel.add(dateLabel);
    middlePanel.add(dateField);

    // Add components to frame
    this.add(title, BorderLayout.NORTH);
    this.add(middlePanel, BorderLayout.CENTER);
    this.add(viewButton, BorderLayout.SOUTH);

    this.setVisible(true); // show window
  }

  // Handles button clicks
  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();

    // If "View Shift" button is clicked
    if (command.equals("View Shift"))
    {
      String date = dateField.getText().trim(); // get input date
      dbAccess db = new dbAccess("iaTimely");   // create database connection

      String description = db.viewShift(date); // fetch shift description

      // Show result in a dialog
      if (description != null)
      {
        JOptionPane.showMessageDialog(this, "Description: " + description);
      }
      else
      {
        JOptionPane.showMessageDialog(this, "Error viewing or shift does not exist");
      }
    }
  }
}

//Alistair Johnson
// This class lets the user choose to sign up as a manager or employee
package iatimely;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author alyj3
 */
public class ChoiceSignupGUI extends JFrame implements ActionListener
{

  // Buttons for different choices
  private JButton signupManButton;
  private JButton signupEmpButton;
  private JButton closeButton;

  // Constructor: sets up the window and buttons
  public ChoiceSignupGUI()
  {
    super("Choose"); // window title
    this.setBounds(100, 100, 200, 150); // position and size
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // close behavior

    // Create "Employee" button
    signupEmpButton = new JButton("Employee");
    signupEmpButton.addActionListener(this);

    // Create "Manager" button
    signupManButton = new JButton("Manager");
    signupManButton.addActionListener(this);

    // Create "Back" button
    closeButton = new JButton("Back");
    closeButton.addActionListener(this);

    // Add buttons to the frame using BorderLayout
    this.add(signupManButton, BorderLayout.NORTH);
    this.add(signupEmpButton, BorderLayout.CENTER);
    this.add(closeButton, BorderLayout.SOUTH);

    this.setVisible(true); // show the window
  }

  // Handles button clicks
  @Override
  public void actionPerformed(ActionEvent err)
  {
    String command = err.getActionCommand();

    // If "Employee" is clicked
    if (command.equals("Employee"))
    {
      new employeeSignUpGUI(); // open employee signup window
      this.dispose(); // close current window
    }
    // If "Manager" is clicked
    else if (command.equals("Manager"))
    {
      new managerSignupGUI(); // open manager signup window
      this.dispose();
    }
    // If "Back" is clicked
    else if (command.equals("Back"))
    {
      new StartGUI(); // go back to start screen
      this.dispose();
    }
  }
}

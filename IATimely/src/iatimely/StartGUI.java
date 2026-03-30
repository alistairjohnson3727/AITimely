//Alistair Johnson
//This is the start GUI of the GUI. This is where it links all my other guis to this. 
package iatimely;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * GUI for the start page of the application
 */
public class StartGUI extends JFrame implements ActionListener
{

  // Labels for title and description
  private JLabel TitleLabel;
  private JLabel paragraphLabel;

  // Panels for buttons and content
  private JPanel buttonPanel;
  private JPanel startPanel;

  // Buttons for user actions
  private JButton loginButton;
  private JButton signupButton;
  private JButton closeButton;

  // Constructor: sets up the start page
  public StartGUI()
  {
    super("Start page"); // window title
    this.setBounds(100, 100, 800, 500); // position and size
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // close behavior

    // Labels
    String description = "";
    TitleLabel = new JLabel("Welcome to AITimely"); // main title
    paragraphLabel = new JLabel(description);       // optional description

    // Buttons
    loginButton = new JButton("Login");
    loginButton.addActionListener(this);

    signupButton = new JButton("Sign up");
    signupButton.addActionListener(this);

    closeButton = new JButton("Close");
    closeButton.addActionListener(this);

    // Panel for buttons
    buttonPanel = new JPanel();
    buttonPanel.add(loginButton);
    buttonPanel.add(signupButton);
    buttonPanel.add(closeButton);

    // Panel for start content
    startPanel = new JPanel();
    startPanel.add(paragraphLabel);

    // Add components to frame
    this.add(TitleLabel, BorderLayout.NORTH);
    this.add(startPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);

    this.setVisible(true); // show window
  }

  // Handles button clicks
  @Override
  public void actionPerformed(ActionEvent err)
  {
    String command = err.getActionCommand();

    if (command.equals("Login"))
    {
      new loginGUI(); // open login window
      this.dispose(); // close current window
    }
    else if (command.equals("Sign up"))
    {
      new ChoiceSignupGUI(); // open signup choice window
      this.dispose();
    }
    else if (command.equals("Close"))
    {
      this.dispose(); // close window
    }
  }

  // Main method: launches start page
  public static void main(String[] args)
  {
    new StartGUI();
  }
}

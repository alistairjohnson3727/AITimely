/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iatimely;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author alyj3
 */
public class StartGUI extends JFrame implements ActionListener
{
  private JLabel TitleLabel;
  private JLabel paragraphLabel;

  
  private JPanel buttonPanel;
  private JPanel startPanel;
  
  private JButton loginButton;
  private JButton signupButton;
  private JButton closeButton;

  public StartGUI()
  {
    super("Start page");
    this.setBounds(100,100,800,500);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    String description = "";
    TitleLabel = new JLabel("Welcome to AITimely");
    paragraphLabel = new JLabel(description);
    
    loginButton = new JButton("Login");
    loginButton.addActionListener(this);
    signupButton = new JButton("Sign up");
    signupButton.addActionListener(this);
    closeButton = new JButton("Close");
    closeButton.addActionListener(this);
    
    buttonPanel = new JPanel();
    buttonPanel.add(loginButton);
    buttonPanel.add(signupButton);
    buttonPanel.add(closeButton);
    
    startPanel = new JPanel();
    startPanel.add(paragraphLabel);
    this.add(TitleLabel, BorderLayout.NORTH);
    this.add(startPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }
  @Override
  public void actionPerformed(ActionEvent err)
  {
    String command = err.getActionCommand();
    if (command.equals("Login"))
    {
      new loginGUI();
    }
    else if(command.equals("Sign up"))
    {
      new ChoiceSignupGUI();
    }
    else if(command.equals("Close"))
    {
      this.dispose();
    }
  }
  
  public static void main(String[] args)
  {
    new StartGUI();
  }
}

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
public class employeeSignUpGUI extends JFrame implements ActionListener
{
  private JLabel titleLabel;
  private JLabel userLabel;
  private JLabel passLabel;
  private JTextField userField;
  private JTextField passField;
  private JPanel centerPanel;
  private JButton signUpButton;
  
  public employeeSignUpGUI()
  {
    super("employee sign up");
    this.setBounds(100, 100, 400, 400);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    
    titleLabel = new JLabel("Sign Up");
    userLabel = new JLabel("Username: ");
    userField = new JTextField(20);
    userField.addActionListener(this);
    passLabel = new JLabel("Password: ");
    passField = new JTextField(20);
    passField.addActionListener(this);
    signUpButton = new JButton("Sign up");
    signUpButton.addActionListener(this);
    
    centerPanel = new JPanel();
    centerPanel.add(userLabel);
    centerPanel.add(userField);
    centerPanel.add(passLabel);
    centerPanel.add(passField);
    
    this.add(titleLabel, BorderLayout.NORTH);
    this.add(centerPanel, BorderLayout.CENTER);
    this.add(signUpButton, BorderLayout.SOUTH);
    this.setVisible(true);
  } 
  
      public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();

        if (command.equals("Sign up"))
        {
            // new InsertDisplay(this);
        }
    }
}

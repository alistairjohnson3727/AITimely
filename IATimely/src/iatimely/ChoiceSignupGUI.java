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

/**
 *
 * @author alyj3
 */
public class ChoiceSignupGUI extends JFrame implements ActionListener
{
  private JLabel titlePanel;
  private JButton signupManButton;
  private JButton signupEmpButton;
  private JButton closeButton;
  
  public ChoiceSignupGUI()
  {
    super("Choose");
    this.setBounds(100,100,200,100);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    signupEmpButton = new JButton("Employee");
    signupEmpButton.addActionListener(this);
    signupManButton = new JButton("Manager");
    signupManButton.addActionListener(this);
    titlePanel = new JLabel("Choose manager or employee sign up");
    
    this.add(titlePanel, BorderLayout.NORTH);
    this.add(signupManButton,BorderLayout.CENTER);
    this.add(signupEmpButton,BorderLayout.SOUTH);
    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent err)
  {
    String command = err.getActionCommand();
    if (command.equals("Employee"))
    {
      new employeeSignUpGUI();
    }
    else if(command.equals("Manager"))
    {
      new managerSignupGUI();
    }
  }
}

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
  private JButton signupManButton;
  private JButton signupEmpButton;
  private JButton closeButton;
  
  public ChoiceSignupGUI()
  {
    super("Choose");
    this.setBounds(100,100,200,150);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    signupEmpButton = new JButton("Employee");
    signupEmpButton.addActionListener(this);
    signupManButton = new JButton("Manager");
    signupManButton.addActionListener(this);
    closeButton = new JButton("Back");
    closeButton.addActionListener(this);

    this.add(signupManButton,BorderLayout.NORTH);
    this.add(signupEmpButton,BorderLayout.CENTER);
    this.add(closeButton, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent err)
  {
    String command = err.getActionCommand();
    if (command.equals("Employee"))
    {
      new employeeSignUpGUI();
      this.dispose();
    }
    else if(command.equals("Manager"))
    {
      new managerSignupGUI();
      this.dispose();
    }
    else if(command.equals("Back"))
    {
      new StartGUI();
      this.dispose();
    }
  }
}

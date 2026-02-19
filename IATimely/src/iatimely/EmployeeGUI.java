/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iatimely;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class EmployeeGUI extends JFrame implements ActionListener
{
  private JLabel title;
  private JButton viewShift;
    public EmployeeGUI()
    {
        super("Employee Dashboard");
        this.setBounds(150, 150, 400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        title = new JLabel("Welcome Employee!", JLabel.CENTER);
        viewShift = new JButton("View Shift");
        viewShift.addActionListener(this);
        
        this.add(title, BorderLayout.CENTER);
        this.add(viewShift,BorderLayout.SOUTH);

        this.setVisible(true);
    }

  @Override
  public void actionPerformed(ActionEvent err)
  {
    String command = err.getActionCommand();
    if(command.equals("View Shift"));
    {
      new ViewShiftGUI();
    }
  }
}

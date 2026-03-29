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
import java.time.LocalDate;
import javax.swing.JButton;

public class EmployeeGUI extends JFrame implements ActionListener
{
  private JLabel title;
  private JLabel date;
  private JButton viewShift;
    public EmployeeGUI(Employee emp)
    {
        super("Employee Dashboard");
        this.setBounds(150, 150, 400, 300);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        LocalDate currentDate = LocalDate.now();
        
        title = new JLabel("Welcome Employee!", JLabel.CENTER);
        date = new JLabel("Current Date: " + currentDate, JLabel.CENTER);
        viewShift = new JButton("View Shift");
        viewShift.addActionListener(this);
        
        this.add(title, BorderLayout.NORTH);
        this.add(date,BorderLayout.CENTER);
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

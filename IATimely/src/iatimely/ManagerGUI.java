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
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ManagerGUI extends JFrame implements ActionListener
{
  private JLabel title;
  private JButton addShift;
  private JButton removeShift;
  private JButton updateShift;
  private JPanel buttonPanel;
    public ManagerGUI()
    {
        super("Manager Dashboard");
        this.setBounds(150, 150, 400, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        title = new JLabel("Welcome Manager!", JLabel.CENTER);
        addShift = new JButton("Add Shift");
        addShift.addActionListener(this);
        removeShift = new JButton("Remove Shift");
        removeShift.addActionListener(this);
        updateShift = new JButton("Update Shift");
        updateShift.addActionListener(this);
        
        buttonPanel = new JPanel();
        buttonPanel.add(addShift);
        buttonPanel.add(removeShift);
        buttonPanel.add(updateShift);
        this.add(title, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }
}

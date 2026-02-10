/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iatimely;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class EmployeeGUI extends JFrame
{
    public EmployeeGUI()
    {
        super("Employee Dashboard");
        this.setBounds(150, 150, 400, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel label = new JLabel("Welcome Employee!", JLabel.CENTER);
        this.add(label, BorderLayout.CENTER);

        this.setVisible(true);
    }
}

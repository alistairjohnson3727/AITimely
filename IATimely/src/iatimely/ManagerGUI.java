/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iatimely;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ManagerGUI extends JFrame
{
    public ManagerGUI()
    {
        super("Manager Dashboard");
        this.setBounds(150, 150, 400, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel label = new JLabel("Welcome Manager!", JLabel.CENTER);
        this.add(label, BorderLayout.CENTER);

        this.setVisible(true);
    }
}

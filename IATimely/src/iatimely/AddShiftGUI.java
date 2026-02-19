/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iatimely;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author alyj3
 */
public class AddShiftGUI extends JFrame implements ActionListener
{
  private JLabel title;
  private JLabel descriptionLabel;
  private JTextArea descriptionField;
  private JLabel dateLabel;
  private JTextField dateField;
  private JLabel EmployeeLabel;
  private JTextField employeeIDField;
  private JButton addButton;
  private JPanel middlePanel;
  private dbAccess db;
  public AddShiftGUI()
  {

    super("Add Shift");
    this.setBounds(300, 300, 200, 100);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    title = new JLabel("Add Shift");
    descriptionLabel = new JLabel("Description: ");
    descriptionField = new JTextArea();
    dateLabel = new JLabel("Date(YYYY-MM-DD): ");
    dateField = new JTextField(10);
    EmployeeLabel = new JLabel("Employee ID: ");
    employeeIDField = new JTextField(20);
    addButton = new JButton("Add Shift");
    addButton.addActionListener(this);
    middlePanel = new JPanel();
    
    middlePanel.add(descriptionLabel);
    middlePanel.add(descriptionField);
    middlePanel.add(dateLabel);
    middlePanel.add(dateField);
    middlePanel.add(EmployeeLabel);
    middlePanel.add(employeeIDField);
    
    this.add(title, BorderLayout.NORTH);
    this.add(middlePanel,BorderLayout.CENTER);
    this.add(addButton,BorderLayout.SOUTH);
    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }
  
  
    private Integer generateUniqueShiftID()
  {
    int newID;
    boolean exists;

    do
    {
      newID = (int)(Math.random() * 9000) + 1000;
      exists = false;

      try
      {
        String sql = "SELECT shiftID FROM Shift WHERE shiftID = ?";
        PreparedStatement ps = db.getDbConn().prepareStatement(sql);
        ps.setInt(1, newID);

        ResultSet rs = ps.executeQuery();

        if(rs.next())
        {
          exists = true; // ID already exists
        }

        rs.close();
        ps.close();
      }
      catch(Exception e)
      {
        System.out.println("Error checking shiftID uniqueness");
      }

    } while(exists);

    return newID;
  }

}

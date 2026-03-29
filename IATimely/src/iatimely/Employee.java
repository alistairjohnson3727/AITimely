/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iatimely;

import java.util.ArrayList;

/**
 *
 * @author alyj3
 */
public class Employee
{
  private String username;
  private String password;
  private int employeeID;

  public Employee(String u, String p, int e)
  {
    username = u;
    password = p;
    employeeID = e;

  }
  public int getEmployeeID()
  {
    return employeeID;
  }
}

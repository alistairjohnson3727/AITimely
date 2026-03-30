//Alistair Johnson
//This is a object class to store variables 
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
  
  public String getUser()
  {
    return username;
  }
  
  public int getEmployeeID()
  {
    return employeeID;
  }
}

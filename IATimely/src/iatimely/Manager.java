//Alistair Johnson
//This is a object class for managers
package iatimely;

import java.util.ArrayList;

/**
 *
 * @author alyj3
 */
public class Manager
{
  private String user;
  private String password;
  private int managerID;
  //Contructor for Managers 
  public Manager(String u, String p, int m)
  {
    user = u;
    password = p;
    managerID = m;
  }
  // returns the user
  public String getUser()
  {
    return user;
  }
  //returns the managers ID
  public int getManID()
  {
    return managerID;
  }
}

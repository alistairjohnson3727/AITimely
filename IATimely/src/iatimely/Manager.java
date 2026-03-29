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
  
  public String getUser()
  {
    return user;
  }
  
  public int getManID()
  {
    return managerID;
  }
}

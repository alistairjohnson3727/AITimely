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
  ArrayList<Employee> emp = new ArrayList<>();
  public Manager(String u, String p, int m)
  {
    user = u;
    password = p;
    managerID = m;
  }
  
  public Manager(int m)
  {
    managerID = m;
  } 
}

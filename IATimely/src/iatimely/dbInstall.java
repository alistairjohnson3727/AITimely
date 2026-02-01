/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iatimely;


/**
 *
 * @author alyj3
 */
public class dbInstall
{

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args)
  {
    String dbName = "iaTimely";
    dbAccess objAccess = new dbAccess();
    objAccess.createDb(dbName);
    String tableStatement =
      "CREATE TABLE TestDatabase(year int, make varchar(55), model varchar(55))";
    objAccess.createTable(tableStatement, dbName);
    
    String employeeLogin =
      "CREATE TABLE EmployeeLogin(employeeID int, username varchar(55), password varchar(55))";
    objAccess.createTable(employeeLogin, dbName);
    
    String managerLogin = 
      "CREATE TABLE ManagerLogin(managerID int, username varchar(55), password varchar(55))";
    objAccess.createTable(managerLogin, dbName);
    
    String EmployeeManager = 
      "CREATE TABLE EmployeeManager(managerID int, employeeID int)";
    objAccess.createTable(EmployeeManager, dbName);
    
    String Shift =
      "CREATE TABLE Shift(shiftID int, description varchar(200), date DATE)";
    objAccess.createTable(Shift, dbName);
    
    String ShiftEmployee =
      "CREATE TABLE ShiftEmployee(shiftID int, employeeID int)";
    objAccess.createTable(ShiftEmployee, dbName);
  }
  
}

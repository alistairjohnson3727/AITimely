//Alistair Johnson
//1/31/2026
//This class creates the database and tables
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
    //Testing table
    String tableStatement =
      "CREATE TABLE TestDatabase(year int, make varchar(55), model varchar(55))";
    objAccess.createTable(tableStatement, dbName);
    //Employee table
    String employeeLogin =
      "CREATE TABLE EmployeeLogin(employeeID int, username varchar(55), password varchar(55))";
    objAccess.createTable(employeeLogin, dbName);
    //Manager table
    String managerLogin = 
      "CREATE TABLE ManagerLogin(managerID int, username varchar(55), password varchar(55))";
    objAccess.createTable(managerLogin, dbName);
    //table that assigns employees to managers
    String EmployeeManager = 
      "CREATE TABLE EmployeeManager(managerID int, employeeID int)";
    objAccess.createTable(EmployeeManager, dbName);
    //shift table
    String Shift =
      "CREATE TABLE Shift(shiftID int, description varchar(200), date DATE)";
    objAccess.createTable(Shift, dbName);
    //table that assigns Shifts to employees
    String ShiftEmployee =
      "CREATE TABLE ShiftEmployee(shiftID int, employeeID int)";
    objAccess.createTable(ShiftEmployee, dbName);
  }
  
}

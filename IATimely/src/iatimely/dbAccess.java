// Alistair Johnson
// 1/31/2026
/*
This class is allows me to access my tables. I can view, add, remove, and update
to any table I want. This class also creates tables and the database. 
*/


package iatimely;
//import
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Database access class
public class dbAccess
{
  //database name
  private String dbName;
  //database connection
  private Connection dbConn;
  //data from database
  private ArrayList<ArrayList<String>> data;

  //default constructor
  public dbAccess()
  {
    this.dbName = "";
    this.dbConn = null;
    this.data = null;
  }

  //constructor with database name
  public dbAccess(String dbName)
  {
    this.dbName = dbName;
    setdbConn(); //connect to database
    this.data = null;
  }

  //get database name
  public String getdbName()
  {
    return dbName;
  }

  //set database name
  public void setdbName(String dbName)
  {
    this.dbName = dbName;
  }

  //get database connection
  public Connection getDbConn()
  {
    return dbConn;
  }

  //connect to mysql database
  public void setdbConn()
  {
    //connection url
    String connectionURL =
      "jdbc:mysql://localhost:3306/" + this.dbName;
    this.dbConn = null;
    try
    {
      //load mysql driver
      Class.forName("com.mysql.cj.jdbc.Driver");
      //connect to database
      this.dbConn = DriverManager.getConnection(
        connectionURL, "root", "mysql1");
    }
    catch (ClassNotFoundException ex)
    {
      System.out.println("Driver not found, check library");
    }
    catch (SQLException err)
    {
      System.out.println("SQL Connection error.");
    }
  }

  //close database connection
  public void closeDbConn()
  {
    try
    {
      this.dbConn.close();
    }
    catch (SQLException e)
    {
      System.out.println("error closing");
    }
  }

  //ADD record to table
  //Works for all tables, but you must give the query
  public boolean addRecord(String insertQuery, Object[] values)
  {
    try
    {
      //prepare sql statement
      PreparedStatement ps = this.dbConn.prepareStatement(insertQuery);
      //set values
      for (int i = 0; i < values.length; i++)
      {
        ps.setObject(i + 1, values[i]);
      }
      //execute insert
      ps.executeUpdate();
      ps.close();
      return true;
    }
    catch (SQLException e)
    {
      System.out.println("error adding record");
      return false;
    }
  }
  // add a record to Employee table
  public boolean addEmployeeAcc(int employeeID, String user, String pass)
  {
    String query = "INSERT INTO EmployeeLogin VALUES (?, ?, ?)";
    return addRecord(query, new Object[]{employeeID, user, pass});
  }
  // add a record to manager table
  public boolean addManagerAcc(int managerID, String user, String pass)
  {
    String query = "INSERT INTO ManagerLogin VALUES (?, ?, ?)";
    return addRecord(query, new Object[]{managerID, user, pass});
  }
  //add a record to shift table
  public boolean addShift(int shiftID, String description, String date)
  {
    String query = "INSERT INTO Shift VALUES (?, ?, ?)";
    return addRecord(query, new Object[]{shiftID, description, date});
  }
  //add a record to EmployeeManager table
  public boolean addEmployeeManager(int managerID, int employeeID)
  {
    String query = "INSERT INTO EmployeeManager VALUES (?, ?)";
    return addRecord(query, new Object[]{managerID, employeeID});
  }
  //add a record to ShiftEmployee table
  public boolean addShiftEmployee(int ShiftID, int EmployeeID)
  {
    String query = "INSERT INTO ShiftEmployee VALUES (?, ?)";
    return addRecord(query, new Object[]{ShiftID, EmployeeID});
  }
  

  //UPDATE record in table
  //works for all tables, but you must give the query
  public boolean updateRecord(String updateQuery, Object[] values)
  {
    try
    {
      //prepare sql statement
      PreparedStatement ps = this.dbConn.prepareStatement(updateQuery);
      //set values
      for (int i = 0; i < values.length; i++)
      {
        ps.setObject(i + 1, values[i]);
      }
      //execute update
      ps.executeUpdate();
      ps.close();
      return true;
    }
    catch (SQLException e)
    {
      System.out.println("error updating record");
      return false;
    }
  }
  //update employee
  public boolean updateEmployee(int employeeID, String username, String password) 
  {
    String query = "UPDATE EmployeeLogin SET username=?, password=? WHERE employeeID=?";
    return updateRecord(query, new Object[]{username, password, employeeID});
  }
  //update manager
  public boolean updateManager(int managerID, String username, String password) 
  {
    String query = "UPDATE ManagerLogin SET username=?, password=? WHERE managerID=?";
    return updateRecord(query, new Object[]{username, password, managerID});
  }
  //update shift
  public boolean updateShift(int shiftID, String description, String date) 
  {
    String query = "UPDATE Shift SET description=?, date=? WHERE shiftID=?";
    return updateRecord(query, new Object[]{description, date, shiftID});
  }
  //update EmployeeManager
  public boolean updateEmployeeManager(int managerId, int employeeId, int newManagerId, int newEmployeeId) 
  {
    String query = "UPDATE EmployeeManager SET managerID=? AND employeeID=? WHERE managerID=? AND employeeID=?";
    return updateRecord(query, new Object[]{newManagerId, newEmployeeId, managerId, employeeId});
  }
  
  //update EmployeeShift
  public boolean updateEmployeeShift(int employeeId, int shiftId, int newEmployeeId, int newShiftId) 
  {
    String query = "UPDATE EmployeeShift SET shiftID=? AND employeeID=? WHERE shiftID=? AND employeeID=?";
    return updateRecord(query, new Object[]{newShiftId, newEmployeeId, shiftId, employeeId});
  }

  
  
  
  //REMOVE record from table
  //works for all tables, but you must give the query
  private boolean removeRecord(String deleteQuery, Object[] values)
  {
    try
    {
      //prepare sql statement
      PreparedStatement ps = this.dbConn.prepareStatement(deleteQuery);
      //set values
      for (int i = 0; i < values.length; i++)
      {
        ps.setObject(i + 1, values[i]);
      }
      //execute delete
      ps.executeUpdate();
      ps.close();
      return true;
    }
    catch (SQLException e)
    {
      System.out.println("error removing record");
      return false;
    }
  }
  //remove Employee
  public boolean removeEmployee(int id) 
  {
    String query = "DELETE FROM EmployeeLogin WHERE employeeID=?";
    return removeRecord(query, new Object[]{id});
  }
  
  //remove Employee
  public boolean removeManager(int id) 
  {
    String query = "DELETE FROM ManagerLogin WHERE managerID=?";
    return removeRecord(query, new Object[]{id});
  }
  //remove shift
  public boolean removeShift(int id)
  {
    String query = "DELETE FROM Shift WHERE shiftID=?"; 
    return removeRecord(query, new Object[]{id});
  }
  
  //remove EmployeeManager
  public boolean removeEmployeeManager(int EmployeeID, int ManagerID)
  {
    String query = "DELETE FROM EmployeeManager WHERE managerID=? AND employeeID=?";
    return removeRecord(query, new Object[]{EmployeeID, ManagerID});
  }
  //remove EmployeeShift
  public boolean removeEmployeeShift(int EmployeeID, int ShiftID)
  {
    String query = "DELETE FROM EmployeeShift WHERE employeeID=? AND shiftID=?";
    return removeRecord(query, new Object[]{EmployeeID, ShiftID});
  }
  
  //convert arraylist to 2d array
  public Object[][] to2dArray(ArrayList<ArrayList<String>> data)
  {
    if(data.size() == 0)
    {
      return new Object[0][0];
    }
    else
    {
      int columnCount = data.get(0).size();
      Object[][] dataList = new Object[data.size()][columnCount];
      for(int i = 0; i < data.size(); i++)
      {
        for(int j = 0; j < columnCount; j++)
        {
          dataList[i][j] = data.get(i).get(j);
        }
      }
      return dataList;
    }
  }

  //get all data from a table
  public ArrayList<ArrayList<String>> getTable(String tableName, String[] columns)
  {
    int columnCount = columns.length;
    Statement s;
    ResultSet rs;
    //sql select query
    String dbQuery = "SELECT * FROM " + tableName;
    this.data = new ArrayList<>();

    try
    {
      //create statement
      s = this.dbConn.createStatement();
      //run query
      rs = s.executeQuery(dbQuery);
      //loop through results
      while(rs.next())
      {
        ArrayList<String> row = new ArrayList<>();
        for(int i = 0; i < columnCount; i++)
        {
          row.add(rs.getString(columns[i]));
        }
        this.data.add(row);
      }
    }
    catch(SQLException e)
    {
      System.out.println("error getting data");
    }
    return data;
  }

  //create new database
  public void createDb(String newDbName)
  {
    setdbName(newDbName);
    String connectionURL = "jdbc:mysql://localhost:3306/";
    String query = "CREATE DATABASE " + this.dbName;
    try
    {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection newConn =
        DriverManager.getConnection(connectionURL, "root", "mysql1");
      Statement s = newConn.createStatement();
      s.executeUpdate(query);
      newConn.close();
      System.out.println("New database created.");
    }
    catch (Exception e)
    {
      System.out.println("Database not created.");
    }
  }

  //create table in database
  public void createTable(String newTable, String dbName)
  {
    setdbName(dbName);
    setdbConn();
    try
    {
      Statement s = this.dbConn.createStatement();
      s.execute(newTable);
      this.dbConn.close();
      System.out.println("Table created");
    }
    catch (SQLException e)
    {
      System.out.println("error creating table");
    }
  }

  public static void main(String[] args)
  {
    //Databases name
    dbAccess db = new dbAccess("iaTimely");
    
    //column names for the TestDatabase
    String[] columnNames =
    {"year", "make", "model"};
    
    // ADD
    String insert = "INSERT INTO TestDatabase VALUES (?, ?, ?)";
    db.addRecord(insert, new Object[]{33, "Project1", "RN"});
    

    //testing if it works for other classes
    String insert2 = "INSERT INTO EmployeeLogin VALUES (?, ?, ?)";
    db.addRecord(insert2, new Object[]{1, "Aly","Aj1308"});

    //Testing if viewTable method works, when the other records are deleted from delete
    String insert3 = "INSERT INTO TestDatabase VALUES (?, ?, ?)";
    db.addRecord(insert3, new Object[]{34, "Project", "RN"});
    
    // UPDATE
    String update =
      "UPDATE TestDatabase SET make=?, model=? WHERE year=?";
    db.updateRecord(update,
      new Object[]{"UpdatedMake", "UpdatedModel", 33});

    // DELETE
    String delete =
      "DELETE FROM TestDatabase WHERE year=?";
    db.removeRecord(delete, new Object[]{33});
    
    // VIEW TABLE
    System.out.println(db.getTable("TestDatabase", columnNames));
  }
}
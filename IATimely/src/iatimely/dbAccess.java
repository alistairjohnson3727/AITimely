// Alistair Johnson
// 1/31/2026
/*
This class is allows me to access my tables. I can view, add, remove, and update
to any table I want. This class also creates tables and the database. 
 */
package iatimely;

// Import SQL and utility classes
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Database access class for handling MySQL operations
public class dbAccess
{

  // Name of the database
  private String dbName;

  // Database connection object
  private Connection dbConn;

  // Data retrieved from database
  private ArrayList<ArrayList<String>> data;

  // Default constructor initializes empty values
  public dbAccess()
  {
    // Initialize database name as empty
    this.dbName = "";

    // Initialize database connection as null
    this.dbConn = null;

    // Initialize data as null
    this.data = null;
  }

  // Constructor with database name
  public dbAccess(String dbName)
  {
    // Set database name
    this.dbName = dbName;

    // Connect to database
    setdbConn();

    // Initialize data as null
    this.data = null;
  }

  // Get the database name
  public String getdbName()
  {
    return dbName;
  }

  // Set the database name
  public void setdbName(String dbName)
  {
    this.dbName = dbName;
  }

  // Get the database connection
  public Connection getDbConn()
  {
    return dbConn;
  }

  // Connect to MySQL database
  public void setdbConn()
  {
    // Connection URL for MySQL
    String connectionURL = "jdbc:mysql://localhost:3306/" + this.dbName;

    // Initialize connection as null
    this.dbConn = null;

    try
    {
      // Load MySQL driver
      Class.forName("com.mysql.cj.jdbc.Driver");

      // Establish connection with username "root" and password "mysql1"
      this.dbConn = DriverManager.getConnection(connectionURL, "root", "mysql1");
    }
    catch (ClassNotFoundException ex)
    {
      // Handle error if driver not found
      System.out.println("Driver not found, check library");
    }
    catch (SQLException err)
    {
      // Handle SQL connection errors
      System.out.println("SQL Connection error.");
    }
  }

  // Close the database connection
  public void closeDbConn()
  {
    try
    {
      // Close the connection
      this.dbConn.close();
    }
    catch (SQLException e)
    {
      // Handle errors when closing
      System.out.println("error closing");
    }
  }

  // Add a record to any table using prepared statement
  public boolean addRecord(String insertQuery, Object[] values)
  {
    try
    {
      // Prepare SQL statement
      PreparedStatement ps = this.dbConn.prepareStatement(insertQuery);

      // Set values for placeholders
      for (int i = 0; i < values.length; i++)
      {
        ps.setObject(i + 1, values[i]);
      }

      // Execute the insert operation
      ps.executeUpdate();

      // Close the prepared statement
      ps.close();

      // Return success
      return true;
    }
    catch (SQLException e)
    {
      // Print error message if failed
      System.out.println("error adding record");
      return false;
    }
  }

  // Add a new employee account
  public boolean addEmployeeAcc(int employeeID, String user, String pass)
  {
    // SQL query for EmployeeLogin table
    String query = "INSERT INTO EmployeeLogin VALUES (?, ?, ?)";

    // Call general addRecord method
    return addRecord(query, new Object[]
    {
      employeeID, user, pass
    });
  }

  // Add a new manager account
  public boolean addManagerAcc(int managerID, String user, String pass)
  {
    // SQL query for ManagerLogin table
    String query = "INSERT INTO ManagerLogin VALUES (?, ?, ?)";

    // Call general addRecord method
    return addRecord(query, new Object[]
    {
      managerID, user, pass
    });
  }

  // Add a shift record
  public boolean addShift(int shiftID, String description, String date)
  {
    // SQL query for Shift table
    String query = "INSERT INTO Shift VALUES (?, ?, ?)";

    // Call general addRecord method
    return addRecord(query, new Object[]
    {
      shiftID, description, date
    });
  }

  // Assign an employee to a manager
  public boolean addEmployeeManager(int managerID, int employeeID)
  {
    // SQL query for EmployeeManager table
    String query = "INSERT INTO EmployeeManager VALUES (?, ?)";

    // Call general addRecord method
    return addRecord(query, new Object[]
    {
      managerID, employeeID
    });
  }

  // Assign a shift to an employee
  public boolean addShiftEmployee(int ShiftID, int EmployeeID)
  {
    // SQL query for ShiftEmployee table
    String query = "INSERT INTO ShiftEmployee VALUES (?, ?)";

    // Call general addRecord method
    return addRecord(query, new Object[]
    {
      ShiftID, EmployeeID
    });
  }

  // Clear all data from tables
  public void clearAllData()
  {
    try
    {
      // Create a statement object
      Statement s = this.dbConn.createStatement();

      // Delete data from all tables in order
      s.executeUpdate("DELETE FROM ShiftEmployee");
      s.executeUpdate("DELETE FROM EmployeeManager");
      s.executeUpdate("DELETE FROM Shift");
      s.executeUpdate("DELETE FROM EmployeeLogin");
      s.executeUpdate("DELETE FROM ManagerLogin");

      // Confirm deletion
      System.out.println("All table data deleted.");
    }
    catch (SQLException e)
    {
      // Handle errors
      System.out.println("Error deleting data.");
    }
  }

  // Update any record with given SQL query
  public boolean updateRecord(String updateQuery, Object[] values)
  {
    try
    {
      // Prepare SQL statement
      PreparedStatement ps = this.dbConn.prepareStatement(updateQuery);

      // Set values for placeholders
      for (int i = 0; i < values.length; i++)
      {
        ps.setObject(i + 1, values[i]);
      }

      // Execute update
      ps.executeUpdate();

      // Close statement
      ps.close();

      // Return success
      return true;
    }
    catch (SQLException e)
    {
      // Print error if failed
      System.out.println("error updating record");
      return false;
    }
  }

  // Update an employee's login info
  public boolean updateEmployee(int employeeID, String username, String password)
  {
    // SQL query for EmployeeLogin table
    String query = "UPDATE EmployeeLogin SET username=?, password=? WHERE employeeID=?";

    // Call general updateRecord method
    return updateRecord(query, new Object[]
    {
      username, password, employeeID
    });
  }

  // Update a manager's login info
  public boolean updateManager(int managerID, String username, String password)
  {
    // SQL query for ManagerLogin table
    String query = "UPDATE ManagerLogin SET username=?, password=? WHERE managerID=?";

    // Call general updateRecord method
    return updateRecord(query, new Object[]
    {
      username, password, managerID
    });
  }

  // Update a shift's details
  public boolean updateShift(int shiftID, String description, String date)
  {
    // SQL query for Shift table
    String query = "UPDATE Shift SET description=?, date=? WHERE shiftID=?";

    // Call general updateRecord method
    return updateRecord(query, new Object[]
    {
      description, date, shiftID
    });
  }

  // Remove a record with general query
  private boolean removeRecord(String deleteQuery, Object[] values)
  {
    try
    {
      // Prepare SQL statement
      PreparedStatement ps = this.dbConn.prepareStatement(deleteQuery);

      // Set values for placeholders
      for (int i = 0; i < values.length; i++)
      {
        ps.setObject(i + 1, values[i]);
      }

      // Execute delete
      ps.executeUpdate();

      // Close statement
      ps.close();

      // Return success
      return true;
    }
    catch (SQLException e)
    {
      // Print error
      System.out.println("error removing record");
      return false;
    }
  }

  // Remove employee by ID
  public boolean removeEmployee(int id)
  {
    // SQL query for EmployeeLogin table
    String query = "DELETE FROM EmployeeLogin WHERE employeeID=?";

    // Call general removeRecord method
    return removeRecord(query, new Object[]
    {
      id
    });
  }

  // Remove manager by ID
  public boolean removeManager(int id)
  {
    // SQL query for ManagerLogin table
    String query = "DELETE FROM ManagerLogin WHERE managerID=?";

    // Call general removeRecord method
    return removeRecord(query, new Object[]
    {
      id
    });
  }

  // Remove shift by ID
  public boolean removeShift(int id)
  {
    // SQL query for Shift table
    String query = "DELETE FROM Shift WHERE shiftID=?";

    // Call general removeRecord method
    return removeRecord(query, new Object[]
    {
      id
    });
  }

  // Remove an employee-manager assignment
  public boolean removeEmployeeManager(int EmployeeID, int ManagerID)
  {
    // SQL query for EmployeeManager table
    String query = "DELETE FROM EmployeeManager WHERE managerID=? AND employeeID=?";

    // Call general removeRecord method
    return removeRecord(query, new Object[]
    {
      EmployeeID, ManagerID
    });
  }

  // Remove a shift-employee assignment
  public boolean removeEmployeeShift(int ShiftID)
  {
    // SQL query for ShiftEmployee table
    String query = "DELETE FROM ShiftEmployee WHERE shiftID=?";

    // Call general removeRecord method
    return removeRecord(query, new Object[]
    {
      ShiftID
    });
  }

  // View a shift description by date
  public String viewShift(String date)
  {
    // Initialize description as null
    String description = null;

    try
    {
      // SQL query to get description by date
      String sql = "SELECT description FROM Shift WHERE date = ?";
      PreparedStatement ps = this.dbConn.prepareStatement(sql);
      ps.setString(1, date);

      // Execute query
      ResultSet rs = ps.executeQuery();

      // If a result is found, get description
      if (rs.next())
      {
        description = rs.getString("description");
      }

      // Close resources
      rs.close();
      ps.close();
    }
    catch (SQLException e)
    {
      // Print error
      System.out.println("Error viewing shift by date.");
    }

    // Return shift description
    return description;
  }

  // Check employee login credentials
  public boolean checkEmployeeLogin(String username, String password)
  {
    try
    {
      // SQL query to match username and password
      String sql = "SELECT * FROM EmployeeLogin WHERE username = ? AND password = ?";
      PreparedStatement stmt = dbConn.prepareStatement(sql);
      stmt.setString(1, username);
      stmt.setString(2, password);

      // Execute query and check if a match exists
      ResultSet rs = stmt.executeQuery();
      return rs.next();
    }
    catch (Exception e)
    {
      // Print error
      System.out.println("Employee login error: " + e.getMessage());
      return false;
    }
  }

  // Check manager login credentials
  public boolean checkManagerLogin(String username, String password)
  {
    try
    {
      // SQL query to match username and password
      String sql = "SELECT * FROM ManagerLogin WHERE username = ? AND password = ?";
      PreparedStatement stmt = dbConn.prepareStatement(sql);
      stmt.setString(1, username);
      stmt.setString(2, password);

      // Execute query and check if a match exists
      ResultSet rs = stmt.executeQuery();
      return rs.next();
    }
    catch (Exception e)
    {
      // Print error
      System.out.println("Manager login error: " + e.getMessage());
      return false;
    }
  }

  // Check if an employee username is available
  public boolean isEmpUserAvailable(String username)
  {
    try
    {
      // SQL query to find username
      String sql = "SELECT username FROM EmployeeLogin WHERE username = ?";
      PreparedStatement ps = this.dbConn.prepareStatement(sql);
      ps.setString(1, username);

      // Execute query
      ResultSet rs = ps.executeQuery();

      // True if username is not found
      boolean available = !rs.next();

      // Close resources
      rs.close();
      ps.close();

      return available;
    }
    catch (SQLException e)
    {
      System.out.println("Error checking employee username.");
      return false;
    }
  }

  // Check if a manager username is available
  public boolean isManUserAvailable(String username)
  {
    try
    {
      // SQL query to find username
      String sql = "SELECT username FROM ManagerLogin WHERE username = ?";
      PreparedStatement ps = this.dbConn.prepareStatement(sql);
      ps.setString(1, username);

      // Execute query
      ResultSet rs = ps.executeQuery();

      // True if username is not found
      boolean available = !rs.next();

      // Close resources
      rs.close();
      ps.close();

      return available;
    }
    catch (SQLException e)
    {
      System.out.println("Error checking manager username.");
      return false;
    }
  }

  // Get employee ID by username
  public Integer getEmployeeIDByUsername(String username)
  {
    // Default value if not found
    int employeeID = -1;
    try
    {
      // SQL query
      String sql = "SELECT employeeID FROM EmployeeLogin WHERE username = ?";
      PreparedStatement ps = this.dbConn.prepareStatement(sql);
      ps.setString(1, username);

      // Execute query
      ResultSet rs = ps.executeQuery();

      // If found, assign employeeID
      if (rs.next())
      {
        employeeID = rs.getInt("employeeID");
      }

      // Close resources
      rs.close();
      ps.close();
    }
    catch (SQLException e)
    {
      System.out.println("Error getting employee ID: " + e.getMessage());
    }

    return employeeID;
  }

  // Get manager ID by username
  public Integer getManagerIDByUsername(String username)
  {
    // Default value if not found
    int managerID = -1;
    try
    {
      // SQL query
      String sql = "SELECT managerID FROM ManagerLogin WHERE username = ?";
      PreparedStatement ps = this.dbConn.prepareStatement(sql);
      ps.setString(1, username);

      // Execute query
      ResultSet rs = ps.executeQuery();

      // If found, assign managerID
      if (rs.next())
      {
        managerID = rs.getInt("managerID");
      }

      // Close resources
      rs.close();
      ps.close();
    }
    catch (SQLException e)
    {
      System.out.println("Error getting manager ID: " + e.getMessage());
    }

    return managerID;
  }

  // Convert ArrayList<ArrayList<String>> to 2D Object array
  public Object[][] to2dArray(ArrayList<ArrayList<String>> data)
  {
    if (data.size() == 0)
    {
      return new Object[0][0];
    }
    else
    {
      // Determine number of columns
      int columnCount = data.get(0).size();

      // Create 2D array
      Object[][] dataList = new Object[data.size()][columnCount];

      // Fill array with data
      for (int i = 0; i < data.size(); i++)
      {
        for (int j = 0; j < columnCount; j++)
        {
          dataList[i][j] = data.get(i).get(j);
        }
      }

      return dataList;
    }
  }

  // Get all data from a table
  public ArrayList<ArrayList<String>> getTable(String tableName, String[] columns)
  {
    int columnCount = columns.length;

    Statement s;
    ResultSet rs;

    // SQL select query
    String dbQuery = "SELECT * FROM " + tableName;

    // Initialize data
    this.data = new ArrayList<>();

    try
    {
      // Create statement
      s = this.dbConn.createStatement();

      // Execute query
      rs = s.executeQuery(dbQuery);

      // Loop through results
      while (rs.next())
      {
        ArrayList<String> row = new ArrayList<>();

        // Add each column to row
        for (int i = 0; i < columnCount; i++)
        {
          row.add(rs.getString(columns[i]));
        }

        // Add row to data
        this.data.add(row);
      }
    }
    catch (SQLException e)
    {
      System.out.println("error getting data");
    }

    return data;
  }

  // Create a new database
  public void createDb(String newDbName)
  {
    // Set database name
    setdbName(newDbName);

    // Connection URL for MySQL server
    String connectionURL = "jdbc:mysql://localhost:3306/";

    // SQL create database query
    String query = "CREATE DATABASE " + this.dbName;

    try
    {
      // Load driver
      Class.forName("com.mysql.cj.jdbc.Driver");

      // Connect to MySQL server
      Connection newConn = DriverManager.getConnection(connectionURL, "root", "mysql1");

      // Create statement
      Statement s = newConn.createStatement();

      // Execute database creation
      s.executeUpdate(query);

      // Close connection
      newConn.close();

      System.out.println("New database created.");
    }
    catch (Exception e)
    {
      System.out.println("Database not created.");
    }
  }

  // Create a new table in the database
  public void createTable(String newTable, String dbName)
  {
    // Set database name and connect
    setdbName(dbName);
    setdbConn();

    try
    {
      // Create statement
      Statement s = this.dbConn.createStatement();

      // Execute table creation SQL
      s.execute(newTable);

      // Close connection
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
    {
      "year", "make", "model"
    };
    /*
    // ADD
    String insert = "INSERT INTO TestDatabase VALUES (?, ?, ?)";
    db.addRecord(insert, new Object[]
    {
      33, "Project1", "RN"
    });

    //testing if it works for other classes
    String insert2 = "INSERT INTO EmployeeLogin VALUES (?, ?, ?)";
    db.addRecord(insert2, new Object[]
    {
      1, "Aly", "Aj1308"
    });

    //Testing if viewTable method works, when the other records are deleted from delete
    String insert3 = "INSERT INTO TestDatabase VALUES (?, ?, ?)";
    db.addRecord(insert3, new Object[]
    {
      34, "Project", "RN"
    });

    // UPDATE
    String update
      = "UPDATE TestDatabase SET make=?, model=? WHERE year=?";
    db.updateRecord(update,
      new Object[]
      {
        "UpdatedMake", "UpdatedModel", 33
      });

    // DELETE
    String delete
      = "DELETE FROM TestDatabase WHERE year=?";
    db.removeRecord(delete, new Object[]
    {
      33
    });

    //testing all the specific table methods
    db.addEmployeeAcc(0, "poop", "123me");
    db.addManagerAcc(3, "frlks", "fkjr");
    db.addShift(4, "workworkwork", "2026-11-11");
    db.addEmployeeManager(3, 0);
    db.addShiftEmployee(4, 0);

    db.updateEmployee(0, "fart", "123you");
    db.updateManager(3, "frank", "Iheartcode");
    db.updateShift(4, "workaboutsmth", "2026-11-12");

    db.removeEmployee(0);
    db.removeManager(3);
    db.removeShift(4);
    db.removeEmployeeManager(0, 3);
    //db.removeEmployeeShift(0, 4);
     */
    db.clearAllData();

    // VIEW TABLE
    System.out.println(db.getTable("TestDatabase", columnNames));
  }
}

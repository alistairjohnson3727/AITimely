package iatimely;

import java.sql.Connection;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class dbAccess
{
  private String dbName;
  private Connection dbConn;
  private ArrayList<ArrayList<String>> data;
  
  public dbAccess()
  {
    this.dbName = "";
    this.dbConn = null;
    this.data = null;
  }

  public dbAccess(String dbName)
  {
    this.dbName = dbName;
    setdbConn();
    this.data = null;
  }

  public String getdbName()
  {
    return dbName;
  }

  public void setdbName(String dbName)
  {
    this.dbName = dbName;
  }

  public Connection getDbConn()
  {
    return dbConn;
  }

  public void setdbConn()
  {
    String connectionURL =
      "jdbc:mysql://localhost:3306/" + this.dbName;
    this.dbConn = null;
    try
    {
      Class.forName("com.mysql.cj.jdbc.Driver");
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


  public boolean addRecord(String insertQuery, Object[] values)
  {
    try
    {
      PreparedStatement ps = this.dbConn.prepareStatement(insertQuery);
      for (int i = 0; i < values.length; i++)
      {
        ps.setObject(i + 1, values[i]);
      }
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


  public boolean updateRecord(String updateQuery, Object[] values)
  {
    try
    {
      PreparedStatement ps = this.dbConn.prepareStatement(updateQuery);
      for (int i = 0; i < values.length; i++)
      {
        ps.setObject(i + 1, values[i]);
      }
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


  public boolean removeRecord(String deleteQuery, Object[] values)
  {
    try
    {
      PreparedStatement ps = this.dbConn.prepareStatement(deleteQuery);
      for (int i = 0; i < values.length; i++)
      {
        ps.setObject(i + 1, values[i]);
      }
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

  public ArrayList<ArrayList<String>> getTable(String tableName, String[] columns)
  {
    int columnCount = columns.length;
    Statement s;
    ResultSet rs;
    String dbQuery = "SELECT * FROM " + tableName;
    this.data = new ArrayList<>();

    try
    {
      s = this.dbConn.createStatement();
      rs = s.executeQuery(dbQuery);
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
    dbAccess db = new dbAccess("iaTimely");
    
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
    db.addRecord(insert, new Object[]{34, "Project", "RN"});
    
    // UPDATE
    String update =
      "UPDATE TestDatabase SET make=?, model=? WHERE year=?";
    db.updateRecord(update,
      new Object[]{"UpdatedMake", "UpdatedModel", 33});

    // DELETE
    String delete =
      "DELETE FROM TestDatabase WHERE year=?";
    db.removeRecord(delete, new Object[]{33});

    System.out.println(db.getTable("TestDatabase", columnNames));
  }
}
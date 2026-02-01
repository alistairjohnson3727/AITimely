/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    String connectionURL
      = "jdbc:mysql://localhost:3306/" + this.dbName;
    this.dbConn = null;
    try
    {
      Class.forName("com.mysql.cj.jdbc.Driver");
      this.dbConn = DriverManager.getConnection(connectionURL,
        "root", "mysql1");
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
  public Object[][] to2dArray(ArrayList<ArrayList<String>> data)
  {
    if(data.size() == 0)
    {
     Object[][] dataList = new Object[0][0];
     return dataList;
    }
    else
    {
      int columnCount = data.get(0).size();
      Object[][] dataList = new Object[data.size()][columnCount];
      for(int i = 0; i < data.size(); i++)
      {
       ArrayList<String> row = data.get(i);
       for(int j = 0; j < columnCount; j++)
       {
         dataList[i][j] = row.get(j);
       }
      }
      return dataList;
    }
  }
  public ArrayList<ArrayList<String>> getData(String tableName, String[] columns)
  {
   int columnCount = columns.length;
   Statement s = null;
   ResultSet rs = null;
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
      String cell = rs.getString(columns[i]); 
      row.add(cell);
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
  public void setData(ArrayList<ArrayList<String>> data)
  {
   this.data = data;
  }
  
  public void createDb(String newDbName)
  {
    setdbName(newDbName);
    Connection newConn;
    // mysql connection
    String connectionURL = "jdbc:mysql://localhost:3306/";
    String query = "CREATE DATABASE " + this.dbName;
    try
    {
      Class.forName("com.mysql.jdbc.Driver");
      newConn = DriverManager.getConnection(connectionURL, "root", "mysql1");
      Statement s = newConn.createStatement();
      s.executeUpdate(query);
      System.out.println("New database created.");
      newConn.close();
      
    }
    catch (ClassNotFoundException ex)
    {
     System.out.println("Driver not found, check library");
    }
    catch (SQLException se)
    {
      System.out.println("SQL Connection error, Db was not created!");
    }
  }
  public void createTable(String newTable, String dbName)
  {
   System.out.println(newTable); 
   setdbName(dbName);
   setdbConn();
   Statement s;
   try
   {
    s = this.dbConn.createStatement();
    s.execute(newTable);
    System.out.println("Table crated ");
    this.dbConn.close();
   }
   catch (SQLException e)
   {
     System.out.println("error creating table " + newTable);
   }
  }
  public static void main(String[] args)
  {
    
    String dbName = "iaTimely";
    String table = "TestDatabase";
    String[] columnNames =
    {"year", "make", "model"};
    String insertQuery = 
      "INSERT INTO TestDatabase VALUES (?, ?, ?)";
    
    dbAccess objAccess = new dbAccess(dbName);
    Connection mydbConn = objAccess.getDbConn();
    int year = 33;
    String make = "Project1";
    String model1 = "RN";

    
    try
    {
      PreparedStatement ps = mydbConn.prepareStatement(insertQuery);
      ps.setInt(1, year);
      ps.setString(2, make);
      ps.setString(3, model1);

      ps.executeUpdate();
      System.out.println("inserted into db succesfully");
    }
    catch (SQLException e)
    {
      System.out.println("error inserting");
    }
    
    ArrayList<ArrayList<String>> data = objAccess.getData(table, columnNames);
    System.out.println(data);
   }
}

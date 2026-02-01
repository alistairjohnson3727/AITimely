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
  }
  
}

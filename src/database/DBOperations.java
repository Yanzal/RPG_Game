/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * the DBOperations class runs all of the methods necessary for the database
 * which includes creating/inserting and deleting tables/data from the database
 * @author Lukey
 */
public class DBOperations {
    
    String url = "jdbc:derby:HeroDB;create=true";
    String usernameDerby = "pdc";
    String passwordDerby = "pdc";
    
    Connection conn = null;
    Statement statement;
    ResultSet rs;
    
    /**
     * This method establishes the connection to the database
     */
    public void establishConnection()
    {
        try {
            conn = DriverManager.getConnection(url, usernameDerby, passwordDerby);
            Statement statement = conn.createStatement();
            boolean c = doesTableExist("HERO");
            if(c == false) {
                statement.executeUpdate("CREATE TABLE HERO (HERONAME VARCHAR(50), HEROTYPE VARCHAR(20), "
                    + "HEROHP INT, HEROMAXHP INT, HEROXP INT, HEROLEVEL INT, HERODAMAGE INT, HEROWEAPONNUMBER INT, HEROPOTIONS INT, HEROSUPERS INT, "
                    + "HEROKILLCOUNT INT, HEROGOLD INT, HEROWEAPON VARCHAR(20), HEROWEAPONDURABILITY INT, HEROWEAPONINDEX INT)");
            }
            boolean d = doesTableExist("INVENTORY");
            if(d == false) {
                statement.executeUpdate("CREATE TABLE INVENTORY (HERONAME VARCHAR(50), ITEMS VARCHAR(150))");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);  
        } 
    }
    /**
     * This method checks if there are any saves saved in the database
     * @return true if saves are present
     */
    public boolean checkSaves() {
        try {
            Statement statement = conn.createStatement();
            rs = statement.executeQuery("SELECT * FROM HERO");
           
            if(rs.next() == false) {
                return false;
            }
            else {
                return true;
            }
        } catch(SQLException ex) {
             Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    /**
     * this method retrieves the names of the save files in the databse
     * @return string array of names
     */
    public String[] getNames() {
        try {
            Statement statement = conn.createStatement();
            rs = statement.executeQuery("SELECT HERONAME FROM HERO");
            
            ArrayList<String> result = new ArrayList<String>();
            
            while(rs.next()) {
                result.add(rs.getString(1));
            }
            if(result.size() != 0) {
                String[] arr = new String[result.size()];
                for(int i = 0; i <result.size();i++) {
                    arr[i] = result.get(i).toString();
                }
                return arr;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * this method gets a row of data from the database based on the filename given
     * @param fileName
     * @return row of data from the filename
     */
    public String[] getFile(String fileName) {
        try {
            String a = "SELECT HERONAME, HEROTYPE, HEROHP, HEROMAXHP, HEROXP, HEROLEVEL, HERODAMAGE,"
                    +" HEROWEAPONNUMBER, HEROPOTIONS, HEROSUPERS, HEROKILLCOUNT, HEROGOLD, HEROWEAPON,"
                    +" HEROWEAPONDURABILITY, HEROWEAPONINDEX FROM HERO WHERE HERONAME=\'"+fileName+"\'";
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(a);
            
            ArrayList<String> result = new ArrayList<String>();
            int columns = rs.getMetaData().getColumnCount();
            
            while(rs.next()) {
                for(int i = 1; i < columns+1; i++) {
                    result.add(rs.getString(i));
                }
            }
            if(result.size() != 0) {
                String[] arr = new String[result.size()];
                for(int i = 0; i <result.size();i++) {
                    arr[i] = result.get(i).toString();
                }
                return arr;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * this method gets a row of data from the database based on the filename given
     * @param fileName
     * @return row of data from the filename
     */
    public String getInventory(String fileName) {
        String b = "";
        System.out.println(fileName);
        try {
            String a = "SELECT ITEMS FROM INVENTORY WHERE HERONAME=\'"+fileName+"\'";
            System.out.println(a);
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(a);
            
            if(rs.next()) {
                b = rs.getString("ITEMS");
            }
            
        } catch (SQLException ex) {
           // Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return b;
    }
    /**
     * this method deletes a row of data based on the filename in the database
     * @param fileName 
     */
    public void deleteFile(String fileName) {
        try {
            String a = "DELETE FROM HERO WHERE HERONAME=\'"+fileName+"\'";
            Statement statement = conn.createStatement();
            statement.execute(a);
            String b = "DELETE FROM INVENTORY WHERE HERONAME=\'"+fileName+"\'";
            statement.execute(b);
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void deleteInventory(String fileName) {
        try {
            String a = "DELETE FROM INVENTORY WHERE HERONAME=\'"+fileName+"\'";
            Statement statement = conn.createStatement();
            statement.execute(a);
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * this method checks if an existing file with the same name given is present in the database
     * it ensures that no multiple copies of names can be created in the database
     * @param fileName
     * @return true or false
     */
    public boolean checkExistingFile(String fileName) {
            String[] files = getNames();
            if(files != null) {
                for(int i = 0; i < files.length; i++) {
                    if(files[i].equals(fileName)) {
                        return true;
                    }       
                } 
            }
            return false;
    }
    /**
     * this method checks the inventory of the existing file if it exists with the same name
     * it ensures no multiples of it are created in the database
     * @param fileName
     * @return true or false
     */
    public boolean checkExistingInventory(String fileName) {
        String[] files = getNames();
        if(files != null) {
            for(int i = 0; i < files.length; i ++) {
                if(files[i].equals(fileName)) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * this method is used to save a row of data and insert it into the database
     * it firstchecks if a file with the same name is in the database and deletes it
     * if present then it overwrites it by inserting a new savefile
     * @param heroName
     * @param heroType
     * @param heroHP
     * @param heroMaxHP
     * @param heroXP
     * @param heroLVL
     * @param heroDamage
     * @param heroWeaponNumber
     * @param heroPotions
     * @param heroSupers
     * @param heroKillCount
     * @param heroGold
     * @param heroWeapon
     * @param heroWeaponDurability 
     */
    public void insertTable(String heroName, String heroType, int heroHP, int heroMaxHP, int heroXP, int heroLVL, int heroDamage, int heroWeaponNumber,
                             int heroPotions, int heroSupers, int heroKillCount, int heroGold, String heroWeapon, int heroWeaponDurability, int heroWeaponIndex) {
        
        boolean b = checkExistingFile(heroName);
        if(b==true) {
            deleteFile(heroName);
        }
        try {
           Statement statement = conn.createStatement();
           String a = "INSERT INTO HERO VALUES(\'"+heroName+"\', \'" + heroType +"\', " + heroHP+", " + heroMaxHP+", "+heroXP+", "+heroLVL+", " +heroDamage+", "
                   +heroWeaponNumber+", "+heroPotions+", " + heroSupers+", " + heroKillCount +", " +heroGold +", \'"+heroWeapon+"\', " +heroWeaponDurability+", "+heroWeaponIndex+")";
           statement.executeUpdate(a);
           
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * this method saves the players inventory in the database
     * @param inventory 
     */
    public void insertInventory(ArrayList<String> inventory, String name) {
        
        boolean b = checkExistingInventory(name);
        if(b == true) {
            deleteInventory(name);
        }
        try {
            String all = "";
            for(int i = 0;  i < inventory.size(); i++) {
                if(i!=0) {
                    all = all + "," + inventory.get(i).toString();
                }
                else {
                    all = all + inventory.get(i).toString();
                }
            }
            Statement statement = conn.createStatement();
            String sqlQuery = "INSERT INTO INVENTORY VALUES(\'"+name+"\', \'" + all + "\')";
            statement.executeUpdate(sqlQuery);
        } catch (SQLException ex) {
          //  Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
          System.out.println(ex.getMessage());
        }
        
    }
    /**
     * this method closes the connection of the database
     */
    public void closeConnections() {
        if(conn!= null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
    }
    /**
     * using metadata of the database this method finds if a table with the inputted
     * name exists in the database and returns either true or false
     * @param tableName
     * @return 
     */
    public boolean doesTableExist(String tableName) {
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rs = dbmd.getTables(null, null, tableName, null);
            if(rs.next()) {
                return true;
            }
            else
            {
                return false;
            }
        }catch (SQLException ex) {
                Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
            }
        return false;
    }
    private void checkTableExisting(String newTableName) {
        try {
      //      System.out.println("check existing tables..... ")
            String[] types = {"Table"};
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);
            Statement dropStatement = null;
            
            while(rsDBMeta.next()) {
                String tableName = rsDBMeta.getString("TABLE_NAME");
                System.out.println("found: " + tableName);
                if(tableName.compareToIgnoreCase(newTableName) == 0) {
                    String sqlDropTable = "DROP TABLE " + newTableName;
                    dropStatement = conn.createStatement();
                    dropStatement.executeUpdate(sqlDropTable);
                }
            }
            if(rsDBMeta != null) {
                rsDBMeta.close();
            }
            if(dropStatement != null) {
                dropStatement.close();
            }
        } catch (SQLException ex) {
            
        }
    }
}

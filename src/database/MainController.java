/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import controllers.InventoryScreenController;
import controllers.StartScreenController;
import java.io.IOException;
import javax.swing.JFrame;
/**
 * This method instantiates the database and the JFrame for the game to be played on
 * @author Lukey
 */
public class MainController {
    
    private static JFrame startScreen;
    public static DBOperations dboperations = new DBOperations();
    /**
     * main method that instantiates the database and the JFrame
     * @param args 
     */
    public static void main(String[] args)
    {   
        dboperations.establishConnection();
        
        startScreen = new StartScreenController("RPG");
        startScreen.setVisible(true);
        startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

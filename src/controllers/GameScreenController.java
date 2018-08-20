/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import models.Hero;
import views.ViewBattleScreen;
import views.ViewGameScreen;
import views.ViewStartScreen;

/**
 * This class is the controller for the gamescreen, it holds methods and handles events given by
 * actions from the view, it takes in input on what event happens and sends it to the view to update
 * @author Lukey
 */
public class GameScreenController extends JFrame{
    
    private Hero hero = null;
    private ViewGameScreen view;
    public static JFrame gameScreen;
    
    /**
     * This method instantiates the view for the JFrame and sets the view onto its contentpane
     * in this method action listeners are added to the buttons on the view screen
     * @param title 
     */
    
    public GameScreenController(String title) {
        
        super(title);
        hero = ViewStartScreen.hero;
        this.view = new ViewGameScreen(this.hero);
        this.setSize(1800, 1100);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.black);
        this.view.setBackground(Color.black);
        getContentPane().add(this.view);
        this.setLocationRelativeTo(null);
      
        /**
        * this method adds an action listener to the save button
        */
        view.getSaveButton().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                eventHandlerSaveButton();
            }
        });
        /**
        * this method adds an action listener to the exit button
        */
        view.getExitButton().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                eventHandlerExitButton();
            }
        });
        /**
        * this method adds an action listener to the battle button
        */
        view.getBattleButton().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                eventHandlerBattleButton();
            }
        });
        /**
        * this method adds an action listener to the potion button
        */
        view.getPotionButton().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                eventHandlerPotionButton();
            }
        });
        /**
        * this method adds an action listener to the super button
        */
        view.getSuperButton().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                eventHandlerSuperButton();
            }
        });
        /**
        * this method adds an action listener to the shop button
        */
        view.getShopButton().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                eventHandlerShopButton();
            }
        });
        /**
        * this method adds an action listener to the boss button
        */
        view.getBossButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerBossButton();
            }
        });
        /**
        * this method adds an action listener to the inventory button
        */
        view.getInventoryButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerInventoryButton();
            }
        });
    }
    /**
     * this method is the result of when the save button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerSaveButton() {
        view.saveUpdate();
    }
    /**
     * this method is the result of when the exit button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerExitButton() {
        int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "CLOSE", JOptionPane.YES_NO_OPTION);
        if(x == JOptionPane.YES_OPTION) {
            System.exit(0);
        } 
    }
    /**
     * this method is the result of when the battle button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerBattleButton() {
        this.setVisible(false);
        BattleScreenController battleScreen = new BattleScreenController("Battle");
        battleScreen.runBattleScreen();
    }
    /**
     * this method is the result of when the potion button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerPotionButton() {
        view.potionUpdate();
    }
    /**
     * this method is the result of when the super button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerSuperButton() {
        view.superUpdate();
    }
    /**
     * this method is the result of when the shop button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerShopButton() {
        this.setVisible(false);
        ShopScreenController shopScreen = new ShopScreenController("Shop");
        shopScreen.runShopScreen();
    }
    /**
     * this method is the result of when the boss button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerBossButton() {
        this.setVisible(false);
        ViewBattleScreen.bossClicked = true;
        BattleScreenController battleScreen = new BattleScreenController("Battle");
        battleScreen.runBattleScreen();
    }
    /**
     * this method is the result of when the Inventory button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerInventoryButton() {
        InventoryScreenController inventory = new InventoryScreenController("Inventory", "gamescreen");
        inventory.runInventoryScreen("gamescreen");
    }
    /**
     * this method instantiates the gamescreen
     */ 
    public void runGameScreen() {
        gameScreen = new GameScreenController("RPG");
        gameScreen.setVisible(true);
        gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
}

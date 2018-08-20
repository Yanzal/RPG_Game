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
import models.Hero;
import views.ViewBattleScreen;
import views.ViewStartScreen;

/**
 * This class is the controller for the battle screen, it holds methods and handles events given by
 * actions from the view, it takes in input on what event happens and sends it to the view to update
 * @author Lukey
 */
public class BattleScreenController extends JFrame {
    
    private Hero hero = null;
    private final ViewBattleScreen view;
    private static JFrame battleScreen;
    
    /**
     * This method instantiates the view for the JFrame and sets the view onto its contentpane
     * in this method action listeners are added to the buttons on the view screen
     * @param title 
     */
    public BattleScreenController(String title) {
        super(title); 
        hero = ViewStartScreen.hero;
        this.view = new ViewBattleScreen(this.hero);
        this.setSize(1200, 900);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.black);
        this.view.setBackground(Color.black);
        getContentPane().add(this.view);
        this.setLocationRelativeTo(null);
        
        /**
         * this method adds an action listener to the attack button
         */
        view.getAttackButton().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                eventHandlerAttackButton();
            }
        });
        /**
         * this method adds an action listener to the run button
         */
        view.getRunButton().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                eventHandlerRunButton();
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
         * this method adds an action listener to the continue button
         */
        view.getContinueButton().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                eventHandlerContinueButton();
            }
        });
        /**
         * this method adds an action listener to the home button
         */
        view.getHomeButton().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                eventHandlerHomeButton();
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
         * this method adds an action listener to the inventory button
         */
        view.getInventoryButton().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                eventHandlerInventoryButton();
            }
        });
    }
    /**
     * this method is the result of when the attack button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerAttackButton() {
        view.setCriticalLabelVisible(false);
        view.attackUpdate();
    }
    /**
     * this method is the result of when the run button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerRunButton() {
        view.runUpdate();
    }
    /**
     * this method is the result of when the potion is pressed, it sends an update
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
     * this method is the result of when the continue button is pressed
     * the method sets the current JFrame invisible and instantiates the gamescreen
     */
    public void eventHandlerContinueButton() {
        this.setVisible(false);
//        if(view.getMusicClip().isRunning()) {
  //          view.getMusicClip().stop();
    //    }
        GameScreenController gameScreen = new GameScreenController("RPG");
        gameScreen.setVisible(true);
        
    }
    /**
     * this method is the result of when the home button is pressed
     * it resets the hero and stats the frame to the beginning
     */
    public void eventHandlerHomeButton() {
        this.setVisible(false);
        view.resetHero();
        StartScreenController startScreen = new StartScreenController("RPG");
        startScreen.runStartScreen();
    }
    /**
     * this method is the result of when the exit button is pressed
     * it closes the system
     */
    public void eventHandlerExitButton() {
        System.exit(0);
    }
    /**
     * this method loads up the users inventory when clicked in the battle
     * screen
     */
    public void eventHandlerInventoryButton() {
        InventoryScreenController inventory = new InventoryScreenController("Inventory", "battlescreen");
        inventory.runInventoryScreen("battlescreen");
    }
    /**
     * this method is used to instantiate the battlescreen
     */
    public void runBattleScreen() {
        battleScreen = new BattleScreenController("BATTLE");
        battleScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        battleScreen.setVisible(true);
    }
}

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
import views.ViewShopScreen;
import views.ViewStartScreen;

/**
 * This class is the controller for the shopscreen, it holds methods and handles events given by
 * actions from the view, it takes in input on what event happens and sends it to the view to update
 * @author Lukey
 */
public class ShopScreenController extends JFrame {
    
    private Hero hero = null;
    private ViewShopScreen view;
    private static JFrame shopScreen;
    
    /**
    * This method instantiates the view for the JFrame and sets the view onto its contentpane
    * in this method action listeners are added to the buttons on the view screen
    * @param title 
    */
    
    public ShopScreenController(String title) {
        super(title);
        hero = ViewStartScreen.hero;
        this.view = new ViewShopScreen(this.hero);
        this.setSize(1000, 900);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.black);
        this.view.setBackground(Color.black);
        getContentPane().add(this.view);
        this.setLocationRelativeTo(null);
        
        /**
        * this method adds an action listener to the shopback button
        */
        view.getShopBackButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerShopBackButton();
            }
        });
        /**
        * this method adds an action listener to the one button
        */
        view.getOneButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerOneButton();
            }
        });
        /**
        * this method adds an action listener to the two button
        */
        view.getTwoButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerTwoButton();
            }
        });
        /**
        * this method adds an action listener to the three button
        */
        view.getThreeButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerThreeButton();
            }
        });
        /**
        * this method adds an action listener to the four button
        */
        view.getFourButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerFourButton();
            }
        });
        /**
        * this method adds an action listener to the five button
        */
        view.getFiveButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerFiveButton();
            }
        });
        /**
        * this method adds an action listener to the six button
        */
        view.getSixButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerSixButton();
            }
        });
        /**
        * this method adds an action listener to the seven button
        */
        view.getSevenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerSevenButton();
            }
        });
        /**
        * this method adds an action listener to the eight button
        */
        view.getEightButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerEightButton();
            }
        });
        /**
        * this method adds an action listener to the nine button
        */
        view.getNineButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerNineButton();
            }
        });
        /**
        * this method adds an action listener to the ten button
        */
        view.getTenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerTenButton();
            }
        });
        /**
        * this method adds an action listener to the buy button
        */
        view.getBuyButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerBuyButton();
            }
        });
    }
    /**
     * this method is the result of when the shopback button is pressed
     * it sets the shop frame to not visible and reinstantiates the gamescreen
     */
    public void eventHandlerShopBackButton() {
        this.setVisible(false);
        GameScreenController gameScreen = new GameScreenController("RPG");
        gameScreen.runGameScreen();
    }
    /**
     * this method is the result of when the one button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerOneButton() {
        view.oneUpdate();
    }
    /**
     * this method is the result of when the two button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerTwoButton() {
        view.twoUpdate();
    }
    /**
     * this method is the result of when the three button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerThreeButton() {
        view.threeUpdate();
    }
    /**
     * this method is the result of when the four button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerFourButton() {
        view.fourUpdate();
    }
    /**
     * this method is the result of when the five button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerFiveButton() {
        view.fiveUpdate();
    }
    /**
     * this method is the result of when the six button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerSixButton() {
        view.backUpdate();
    }
    /**
     * this method is the result of when the seven button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerSevenButton() {
        view.backUpdate();
    }
    /**
     * this method is the result of when the eight button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerEightButton() {
        view.backUpdate();
    }
    /**
     * this method is the result of when the nine button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerNineButton() {
        view.backUpdate();
    }
    /**
     * this method is the result of when the ten button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerTenButton() {
        view.backUpdate();
    }
    /**
     * this method is the result of when the buy button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerBuyButton() {
        view.buyUpdate();
    }
    /**
     * this method instantiates the shop screen
     */
    public void runShopScreen() {
        shopScreen = new ShopScreenController("Shop");
        shopScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        shopScreen.setVisible(true);
    }
}

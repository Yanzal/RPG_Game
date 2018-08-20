/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import models.Hero;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import views.ViewInventoryScreen;
import views.ViewStartScreen;

/**
 * The main class implements and initializes the game
 * First it initializes a character either by loading or
 * creating a new one and then calls methods from other classes
 * which links them together
 * @author Lukey
 */
public class InventoryScreenController extends JFrame{
    
    private Hero hero;
    private ViewInventoryScreen view;
    private static JFrame inventoryScreen;
    private String currentItemSelection;

    /**
    * This method instantiates the view for the JFrame and sets the view onto its contentpane
    * in this method action listeners are added to the buttons on the view screen
    * @param title 
    */
    
    public InventoryScreenController(String title, String invoker) {
        
        super(title);
        this.currentItemSelection = null;
        hero = ViewStartScreen.hero;
        this.view = new ViewInventoryScreen(this.hero, invoker);
        this.setSize(600,800);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.black);
        this.view.setBackground(Color.black);
        getContentPane().add(this.view);
        this.setLocationRelativeTo(null);
        
        /**
         * This method adds an actionlistener to the equip button
         */
        view.getEquipButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerEquipButton();
            }
        });
        /**
         * This method adds an actionlistener to the cancel button
         */
        view.getCancelButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerCancelButton();
            }
        });
        /**
         * This method adds a mouse listener to the inventory list, updating
         * the current item selection and sending it to the view
         */
        view.getInventoryList().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                eventHandlerItemChosen();
            }
        });
    }
    /**
     * this method sends an update to the view to when the equip button
     * is initiated
     */
    public void eventHandlerEquipButton() {
        if(this.currentItemSelection == null) {
            view.getInventoryList().clearSelection();
        }
        else {
            int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to equip this item?", null, JOptionPane.YES_NO_OPTION);
            if(x == JOptionPane.YES_OPTION) {
                view.setItem(this.currentItemSelection);
            } 
        }
    }
    /**
     * this method closes the inventory frame
     */
    public void eventHandlerCancelButton() {
        this.dispose();
    }
    /**
     * this method is the result of when a name is clicked on the itemlist in the inventory screen
     * it sets the current item selection and updates it in the view
     */
    public void eventHandlerItemChosen() {
        this.currentItemSelection = (String) this.view.getInventoryList().getSelectedValue();
        if(currentItemSelection != null) {
            view.itemUpdate(currentItemSelection);
        }
    }
    /**
     * this method instantiates the inventoryScreen
     */
    public void runInventoryScreen(String invoker) {
        inventoryScreen = new InventoryScreenController("Inventory", invoker);
        inventoryScreen.setVisible(true);
        inventoryScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}



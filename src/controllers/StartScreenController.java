/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import models.Hero;
import models.Warrior;
import models.Mage;
import models.Archer;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import views.ViewStartScreen;

/**
 * The main class implements and initializes the game
 * First it initializes a character either by loading or
 * creating a new one and then calls methods from other classes
 * which links them together
 * @author Lukey
 */
public class StartScreenController extends JFrame{
    
    private Hero hero;
    private ViewStartScreen view;
    private static JFrame startScreen;
    private String currentHeroSelection;

    /**
    * This method instantiates the view for the JFrame and sets the view onto its contentpane
    * in this method action listeners are added to the buttons on the view screen
    * @param title 
    */
    
    public StartScreenController(String title) {
        
        super(title);
        this.currentHeroSelection = null;
        this.view = new ViewStartScreen(this.hero);
        this.setSize(1000,800);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.black);
        this.view.setBackground(Color.black);
        getContentPane().add(this.view);
        this.setLocationRelativeTo(null);
        
        /**
        * this method adds an action listener to the newgame button
        */
        view.getNewGameButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerNewGameButton();
            }
        });
        /**
        * this method adds an action listener to the loadgame button
        */
        view.getLoadGameButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerLoadGameButton();
            }
        });
        /**
        * this method adds an action listener to the loadback button
        */
        view.getLoadBackButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerLoadBackButton();
            }
        });
        /**
        * this method adds an action listener to the loadhero button
        */
        view.getLoadHeroButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerLoadHeroButton();
            }
        });    
        /**
        * this method adds an action listener to the delete button
        */
        view.getDeleteButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerDeleteButton();
            }
        });
        /**
        * this method adds an action listener to the warrior button
        */
        view.getWarriorButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerWarriorButton();
            }
        });
        /**
        * this method adds an action listener to the archer button
        */
        view.getArcherButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerArcherButton();
            }
        });
        /**
        * this method adds an action listener to the mage button
        */
        view.getMageButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerMageButton();
            }
        });
        /**
        * this method adds an action listener to the chooseheroback button
        */
        view.getChooseHeroBackButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerChooseHeroBackButton();
            }
        });
        /**
        * this method adds an action listener to the namenext button
        */
        view.getNameNextButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerNameNextButton();
            }
        });
        /**
        * this method adds an action listener to the nameload button
        */
        view.getNameLoadButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerNameLoadButton();
            }
        });
        /**
        * this method adds an action listener to the overwrite button
        */
        view.getOverwriteButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerOverwriteButton();
            }
        });
        /**
        * this method adds an action listener to the choosenameback button
        */
        view.getChooseNameBackButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerChooseNameBackButton();
            }
        });
        /**
        * this method adds an action listener to the continue button
        */
        view.getContinueButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerContinueButton();
            }
        });
        /**
        * this method adds an action listener to the continueback button
        */
        view.getContinueBackButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerContinueBackButton();
            }
        }); 
    }
    /**
    * this method adds a mouse listener to the scroll pane of the herolist
    * it is encapsulated in another method as it requires the herolist
    * to be instantiated, it is instantiated when the user enters the load screen
    */
    public void loadMouseListener() {
        view.getHeroList().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            eventHandlerHeroChosen();
            }
        });
    }
    /**
    * this method adds a key listener to the textfield in the choosename screen
    * it is encapsulated in aother method as it requires textfield
    * to be instantiated, it is instantiated when the user enters the choosename
    * this method also adds an action listener so the user can press enter to choose a name
    */
    public void loadKeyListener() {
        view.getNameTextField().addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                eventHandlerNameTextField();
            }
        });
        view.getNameTextField().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eventHandlerNameNextButton();
            }
        });
    }
    /**
     * this method is the result of when the newgame button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerNewGameButton() {
        this.view.chooseHeroScreen();
    }
    /**
     * this method is the result of when the load button is pressed, it sends an update
     * to the view and loads the mouse listener
     */
    public void eventHandlerLoadGameButton() {
        view.loadScreen();
        if(view.getHeroList()!= null) {
            loadMouseListener();
        }
    }
    /**
     * this method is the result of when the loadback button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerLoadBackButton() {
        view.loadBackButtonUpdate();
    }
    /**
     * this method is the result of when the loadhero button it first clears any selection on the list
     * then it asks the user if they want to load the file or not, it then sets the frame invisible and
     * loads the gamescreen of that chosen hero
     */
    public void eventHandlerLoadHeroButton() {
        if(this.currentHeroSelection == null) {
            view.getHeroList().clearSelection();
        }
        else {
            int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to load this file?", "WARNING", JOptionPane.YES_NO_OPTION);
            if(x == JOptionPane.YES_OPTION) {
                view.loadHero(this.currentHeroSelection);
                this.setVisible(false);
                GameScreenController gameScreen = new GameScreenController("RPG");
                gameScreen.runGameScreen();
            } 
        }
    }
    /**
     * this method is the result of when the delete button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerDeleteButton() {
        view.deleteUpdate();
    }
    /**
     * this method is the result of when the warrior button is pressed, it sends an update
     * to the view and instantiates the users hero and enters the namescreen
     */
    public void eventHandlerWarriorButton() {
        view.setHeroChoice("Warrior");
        Warrior warrior = new Warrior("null", "Warrior", 150, 1, 10);
        view.hero = warrior;
        view.hero.setClass("Warrior");
        view.chooseNameScreen();
        loadKeyListener();
    }
    /**
     * this method is the result of when the archer button is pressed, it sends an update
     * to the view and instantiates the users hero and enters the namescreen
     */
    public void eventHandlerArcherButton() {
        view.setHeroChoice("Archer");
        Archer archer = new Archer("null", "Archer", 125, 1, 12);
        view.hero = archer;
        view.hero.setClass("Archer");
        view.chooseNameScreen();
        loadKeyListener();
    }
    /**
     * this method is the result of when the mage button is pressed, it sends an update
     * to the view and instantiates the users hero and enters the namescreen
     */
    public void eventHandlerMageButton() {
        view.setHeroChoice("Mage");
        Mage mage = new Mage("null", "Mage", 100, 1, 15);
        view.hero = mage;
        view.hero.setClass("Mage");
        view.chooseNameScreen();
        loadKeyListener();
    }
    /**
     * this method is the result of when the chooseheroback button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerChooseHeroBackButton() {
        view.chooseHeroBackButtonUpdate();
    }
    /**
     * this method is the result of when the name button is pressed
     * it first checks if the field is empty and prompts the user
     * it then also checks if the name currently exists in the database
     */
    public void eventHandlerNameNextButton() {
        if(view.getNameTextField().getText().isEmpty()) {
            view.setNameCheckLabelVisible(true);
        }
        else {
            boolean a = view.checkName();
            if(a == false) {
                String b = view.getNameTextField().getText();
                view.hero.setName(b);
                view.continueScreen();
            }
            else {
                view.setNameCheckLabel("That file already exists");
                view.setNameCheckLabelVisible(true);
                view.getOverwriteButton().setVisible(true);
                view.getNameLoadButton().setVisible(true);
            }
        }
    }
    /**
     * this method is the result of when the nameload button is pressed, it sends an update
     * to the view and then sets the frame invisible and loads the gamescreen
     */
    public void eventHandlerNameLoadButton() {
            int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to load this file?", "CLOSE", JOptionPane.YES_NO_OPTION);
            if(x == JOptionPane.YES_OPTION) {
                view.loadHero(view.getNameTextField().getText());
                this.setVisible(false);
                GameScreenController gameScreen = new GameScreenController("RPG");
                gameScreen.runGameScreen();
            }
    }
    /**
     * this method is the result of when the overwrite button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerOverwriteButton() {
        int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to overwrite this file?", "WARNING", JOptionPane.INFORMATION_MESSAGE);
        if(x == JOptionPane.YES_OPTION) {
            view.overwriteFile();
            view.continueScreen();
            view.getContinueBackButton().setVisible(false);
        }
    }
    /**
     * this method is the result of when the choosenameback button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerChooseNameBackButton() {
        view.chooseNameBackButtonUpdate();
    }
    /**
     * this method is the result of when the continue button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerContinueButton() {
        this.setVisible(false);
        ArrayList<String> itemList = new ArrayList<String>();
        itemList.add("Stick " + view.hero.getWeapon() + " (INF)");
        view.hero.setInventory(itemList);
        for(int i = 0; i < view.hero.getInventory().size(); i++) {
            if(view.hero.getInventory().get(i).equalsIgnoreCase("Stick Sword (INF)")) {
                view.hero.setArrayIndex(i);
                break;
            }
        }
        view.hero.setDurability();
        GameScreenController gameScreen = new GameScreenController("RPG");
        gameScreen.runGameScreen();
    }
    /**
     * this method is the result of when the continueback button is pressed, it sends an update
     * to the view
     */
    public void eventHandlerContinueBackButton() {
        view.continueBackButtonUpdate();
    }
    /**
     * this method is the result of when a name is clicked on the herolist in the load screen
     * it sets the current hero selection and updates it in the view
     */
    public void eventHandlerHeroChosen() {
        this.currentHeroSelection = (String) this.view.getHeroList().getSelectedValue();
        if(currentHeroSelection != null) {
            view.heroUpdate();
        }
    }
    /**
     * this method is the result of when a letter is entered to the name field
     * it checks if the name is present in the database and updates the view if
     * necessary
     */
    public void eventHandlerNameTextField() {
        boolean a = view.checkName();
        if(a == false) {
            view.keyUpdate();
        }
        else {
            view.keyVisibleUpdate();
        }
    }
    /**
     * this method instantiates the startscreen
     */
    public void runStartScreen() {
        startScreen = new StartScreenController("RPG");
        startScreen.setVisible(true);
        startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import models.Enemy;
import models.Hero;
import models.Randomizers;
/**
 * This Class is the view for the battle screen, it instantiates many JObjects for the controller to be able to use
 * this class holds methods that update the view which happens when the controller calls the methods
 * @author Lukey
 */
public class ViewBattleScreen extends JPanel {
    
    public Hero hero = null;
    public static boolean bossClicked = false;
    public static Enemy enemy = null;
    private int EnemyMaxHP = 0;
    private static int timesRan = 0;
    
    private JLabel playerNameLabel, playerLvlLabel, playerHPLabel, enemyNameLabel = new JLabel(), enemyLvlLabel = new JLabel(), enemyHPLabel = new JLabel();
    private JLabel criticalLabel, levelUpLabel, endLabel, endLvlLabel, endKillsLabel, endGoldLabel, rankLabel, rankLetterLabel, enemyPicture, heroPicture, enemyDamageLabel, heroDamageLabel;
    private JPanel buttonPanel, playerPanel, playerHPPanel, enemyPanel, enemyLvlPanel, enemyHPPanel, enemyDamagePanel, heroDamagePanel;
    private JPanel criticalPanel, levelUpPanel, allPanel, endPanel, endButtonPanel, statPanel, rankPanel;
    
    private JButton runButton = new JButton(), potionButton = new JButton(), inventoryButton = new JButton();
    private JButton superButton = new JButton(), continueButton = new JButton(), exitButton = new JButton(), homeButton = new JButton();
    public static JButton attackButton;
    
    private JProgressBar playerHPBar, enemyHPBar, playerXPBar;
    private JTextArea updateArea;
    private JScrollPane scroll;
    private Clip clip;
    
    Container con;
    
    Font TNR100 = new Font("Times New Roman", Font.PLAIN, 100);
    Font ALG70 = new Font("Algerian", Font.ITALIC, 70);
    Font TNR60 = new Font("Times New Roman", Font.PLAIN, 60);
    Font TNR50 = new Font("Times New Roman", Font.PLAIN, 50);
    Font TNR40 = new Font("Times New Roman", Font.PLAIN, 40);
    Font TNR35 = new Font("Times New Roman", Font.PLAIN, 35);
    Font TNR30 = new Font("Times New Roman", Font.PLAIN, 30);

    /**
     * clip accessor
     * @return clip
     */
    public Clip getMusicClip() {
        return clip;
    }
    /**
     * inventoryButton accessor
     * @return inventoryButton
     */
    public JButton getInventoryButton() {
        return inventoryButton;
    }
    /**
     * attackButton accessor
     * @return attackButton
     */
    public JButton getAttackButton() {
        return attackButton;
    }
    /**
     * runButton accessor
     * @return runButton
     */
    public JButton getRunButton() {
        return runButton;
    }
    /**
     * potionButton accessor
     * @return potionButton
     */
    public JButton getPotionButton() {
        return potionButton;
    }
    /**
     * superButton accessor
     * @return superButton
     */
    public JButton getSuperButton() {
        return superButton;
    }
    /**
     * continueButton accessor
     * @return continueButton
     */
    public JButton getContinueButton() {
        return continueButton;
    }
    /**
     * exitButton accessor
     * @return exitButton
     */
    public JButton getExitButton() {
        return exitButton;
    }
    /**
     * homeButton accessor
     * @return homeButton
     */
    public JButton getHomeButton() {
        return homeButton;
    }
    /**
     * This method sets the critical label to be visible or not
     * @param visibility 
     */
    public void setCriticalLabelVisible(boolean visibility) {
        criticalLabel.setVisible(visibility);
    }
    /**
     * this method returns a boolean which checks whether or not 
     * the user has pressed the boss button
     * @return 
     */
    public boolean getBossClicked() {
        return bossClicked;
    }
    /**
     * this method sets the boss button
     * @param bossClicked 
     */
    public void setBossClicked(boolean bossClicked) {
        this.bossClicked = bossClicked;
    }
    /**
     * this method is instantiated in the controller which sets the view for the JFrame
     * it sets the hero to the hero given in the controller.
     * @param m 
     */
    public ViewBattleScreen(Hero m) {
        super();
        this.hero = m;
        setLayout(null);
        
        if(bossClicked == false) {
            randomEnemy();
        }
        else {
            enableBoss();
        }
        runBattleScreen();
  //      music();
    }
    /**
     * this method plays the background music
     */
    public void music() {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File("resources/battlescreenmusic.wav"));
            clip = AudioSystem.getClip();
            clip.open(audio);
        //    if(clip.isRunning()) {
          //      clip.stop();
           // } else {
                clip.start();
            //}
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(ViewBattleScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ViewBattleScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(ViewBattleScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * this method sets the initial state of the Frame, it sets all visible aspects of the battle screen
     * 
     */
    public void runBattleScreen() {
        
        //this panel runs invisible in the initial frame, it only becomes visible for the deathscreen
        allPanel = new JPanel();
        allPanel.setBounds(100, 100, 1000, 650);
        allPanel.setLayout(null);
        allPanel.setBackground(new Color(0,0,0,64));
        allPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f), Color.white));
        allPanel.setVisible(false);
        this.add(allPanel);
        
        //a container is set in the frame as the size of the frame itself, all panels/jobjects are
        //put inside this container
        con = new Container();
        con.setBounds(0, 0, 1200, 900);
        con.setLayout(null);
        add(con);
        
        enemyDamageLabel = new JLabel(new ImageIcon("resources/damage.png"));
        enemyDamageLabel.setText("");
        enemyDamageLabel.setFont(TNR30);
        enemyDamageLabel.setForeground(Color.white);
        enemyDamageLabel.setHorizontalTextPosition(JLabel.CENTER);
        enemyDamageLabel.setVerticalTextPosition(JLabel.CENTER);
        enemyDamageLabel.setBounds(0, 0, 300, 200);
        enemyDamageLabel.setVisible(false);
        con.add(enemyDamageLabel);
        
        heroDamageLabel = new JLabel(new ImageIcon("resources/damage.png"));
        heroDamageLabel.setText("");
        heroDamageLabel.setFont(TNR30);
        heroDamageLabel.setForeground(Color.white);
        heroDamageLabel.setHorizontalTextPosition(JLabel.CENTER);
        heroDamageLabel.setVerticalTextPosition(JLabel.CENTER);
        heroDamageLabel.setBounds(0, 0, 300, 200);
        heroDamageLabel.setVisible(false);
        con.add(heroDamageLabel);

        playerHPPanel = new JPanel();
        playerHPPanel.setBackground(Color.black);
        playerHPPanel.setBounds(0, 360, 500, 100);
        playerHPPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        con.add(playerHPPanel);
        
        playerHPBar = new JProgressBar(0, hero.getMaxHealth());
        playerHPBar.setBackground(Color.black);
        playerHPBar.setForeground(Color.green);
        playerHPBar.setBounds(15, 530, 473, 50);
        playerHPBar.setValue(hero.getHealth());
        
        if(playerHPBar.getValue()<hero.getMaxHealth()/4) {
                playerHPBar.setForeground(Color.red);
            }
            else {
                playerHPBar.setForeground(Color.green);
            }
        con.add(playerHPBar);
        
        playerXPBar = new JProgressBar(0, hero.getNextLevel());
        playerXPBar.setBackground(Color.LIGHT_GRAY);
        playerXPBar.setForeground(Color.CYAN);
        playerXPBar.setBounds(15, 580, 473, 20);
        playerXPBar.setValue(hero.getExp());
        con.add(playerXPBar);

        enemyHPBar = new JProgressBar(0, enemy.getMaxHealth());
        enemyHPBar.setBackground(Color.black);
        enemyHPBar.setForeground(Color.green);
        enemyHPBar.setBounds(705, 180, 473, 50);
        enemyHPBar.setValue(enemy.getHealth());
        if(enemyHPBar.getValue()<enemy.getMaxHealth()/4) {
                enemyHPBar.setForeground(Color.red);
            }
            else {
                enemyHPBar.setForeground(Color.green);
            }
        con.add(enemyHPBar);
        
        try {
            BufferedImage heroImage = null;
            if(hero.getPlayerClass().equals("Warrior")) {
                heroImage = ImageIO.read(new File("resources/battlewarrior.png"));
            }
            if(hero.getPlayerClass().equals("Archer")) {
                heroImage = ImageIO.read(new File("resources/battlearcher.png"));
            }
            if(hero.getPlayerClass().equals("Mage")) {
                heroImage = ImageIO.read(new File("resources/battlemage.png"));
            }
            heroPicture = new JLabel(new ImageIcon(heroImage));
            heroPicture.setBounds(600, 140, 600, 500);
            con.add(heroPicture);
        } catch (IOException ex) {
            Logger.getLogger(ViewStartScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            BufferedImage enemyImage = null;
            if(enemy.getName().equals("Ghost")) {
                enemyImage = ImageIO.read(new File("resources/ghost.png"));
            }
            if(enemy.getName().equals("BloodVeld")) {
                enemyImage = ImageIO.read(new File("resources/bloodveld.png"));
            }
            if(enemy.getName().equals("Demon")) {
                enemyImage = ImageIO.read(new File("resources/demon.png"));
            }
            if(enemy.getName().equals("IceMan")) {
                enemyImage = ImageIO.read(new File("resources/iceMan.png"));
            }
            if(enemy.getName().toLowerCase().equals("boss")) {
                enemyImage = ImageIO.read(new File("resources/boss.png"));
            }
            enemyPicture = new JLabel(new ImageIcon(enemyImage));
            enemyPicture.setBounds(30, 30, 500, 300);
            con.add(enemyPicture);
        } catch (IOException ex) {
            Logger.getLogger(ViewStartScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        playerPanel = new JPanel();
        playerPanel.setBackground(Color.black);
        playerPanel.setBounds(0, 450, 500, 75);
        playerPanel.setLayout(null);
        con.add(playerPanel);
        
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.black);
        buttonPanel.setBounds(0, 600, 500, 300);
        buttonPanel.setLayout(null);
        con.add(buttonPanel);
        
        playerNameLabel = new JLabel(" "+hero.getName());
        playerNameLabel.setForeground(Color.white);
        playerNameLabel.setBounds(0, 00, 460, 80);
        playerNameLabel.setFont(TNR60);
        playerNameLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        playerPanel.add(playerNameLabel);
     
        playerLvlLabel = new JLabel("LVL: "+hero.getLevel());
        playerLvlLabel.setForeground(Color.white);
        playerLvlLabel.setBounds(380, 25, 700, 40);
        playerLvlLabel.setFont(TNR30);
        playerPanel.add(playerLvlLabel);
        
        playerHPLabel = new JLabel(hero.getHealth()+"/"+hero.getMaxHealth());
        playerHPLabel.setForeground(Color.white);
        playerHPLabel.setFont(TNR100);
        playerHPPanel.add(playerHPLabel);
        
        attackButton = new JButton();
        attackButton.setText("(" + hero.getWeaponDurability()+")");
        if(hero.getWeaponType().equals("Stick")) {
            attackButton.setText("(INF)");
        }
        attackButton.setForeground(Color.white);
        attackButton.setBackground(Color.black);
        attackButton.setBounds(15, 15, 230, 105);
        attackButton.setIcon(new ImageIcon("resources/battle.png"));
        attackButton.setRolloverIcon(new ImageIcon("resources/battle2.png"));
        attackButton.setFont(TNR35);
        
        this.timesRan = 0;
        Randomizers.runRandom(hero, enemy, timesRan);
        runButton.setText("(" + Randomizers.getRunPercentage()+"%)");
        runButton.setForeground(Color.white);
        runButton.setBackground(Color.black);
        runButton.setBounds(257, 15, 230, 105);
        runButton.setIcon(new ImageIcon("resources/runbutton.png"));
        runButton.setRolloverIcon(new ImageIcon("resources/runbutton2.png"));
        runButton.setFont(TNR30);
        
        potionButton.setText("(" + hero.getPotions()+ ")");
        potionButton.setForeground(Color.white);
        potionButton.setBackground(Color.black);
        potionButton.setBounds(15, 132, 230, 105);
        potionButton.setIcon(new ImageIcon("resources/potion.png"));
        potionButton.setRolloverIcon(new ImageIcon("resources/potion2.png"));
        potionButton.setFont(TNR40);
        
        superButton.setText("(" + hero.getSupers()+")");
        superButton.setForeground(Color.white);
        superButton.setBackground(Color.black);
        superButton.setBounds(257, 132, 230, 105);
        superButton.setIcon(new ImageIcon("resources/super.png"));
        superButton.setRolloverIcon(new ImageIcon("resources/super2.png"));
        superButton.setFont(TNR40);
        
        buttonPanel.add(attackButton);
        buttonPanel.add(runButton);
        buttonPanel.add(potionButton);
        buttonPanel.add(superButton);
        
        inventoryButton.setText("        ");
        inventoryButton.setForeground(Color.white);
        inventoryButton.setBackground(Color.black);
        inventoryButton.setBounds(510, 460, 300, 100);
        inventoryButton.setFont(TNR60);
        inventoryButton.setBorderPainted(false);
        inventoryButton.setIcon(new ImageIcon("resources/inventory.png"));
        inventoryButton.setRolloverIcon(new ImageIcon("resources/inventory2.png"));
        add(inventoryButton);
        
        enemyHPPanel = new JPanel();
        enemyHPPanel.setBackground(Color.black);
        enemyHPPanel.setBounds(700, 0, 500, 100);
        enemyHPPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        con.add(enemyHPPanel);
        
        enemyPanel = new JPanel();
        enemyPanel.setBackground(Color.black);
        enemyPanel.setBounds(900, 100, 300, 60);
        enemyPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        con.add(enemyPanel);
        
        enemyLvlPanel = new JPanel();
        enemyLvlPanel.setBackground(Color.black);
        enemyLvlPanel.setBounds(700, 100, 200, 60);
        enemyLvlPanel.setLayout(null);
        con.add(enemyLvlPanel);
        
        enemyHPLabel.setForeground(Color.white);
        enemyHPLabel.setBounds(0, 00, 480, 80);
        enemyHPLabel.setFont(TNR100);
        enemyHPPanel.add(enemyHPLabel);
        
        enemyLvlLabel.setForeground(Color.white);
        enemyLvlLabel.setBounds(35, 0, 700, 80);
        enemyLvlLabel.setFont(TNR30);
        enemyLvlPanel.add(enemyLvlLabel);
        
        enemyNameLabel.setForeground(Color.white);
        enemyNameLabel.setBounds(300, 00, 480, 80);
        enemyNameLabel.setFont(TNR60);
        enemyNameLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
        enemyPanel.add(enemyNameLabel);
        
        updateArea = new JTextArea("");
        updateArea.setEditable(false);
        updateArea.setBounds(0, 0, 650, 250);
        updateArea.setForeground(Color.white);
        updateArea.setBackground(Color.DARK_GRAY);
        updateArea.setFont(TNR30);
        updateArea.setLineWrap(true);
        
        this.scroll = new JScrollPane(updateArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setLocation(510, 585);
        scroll.setSize(680, 265);
        add(scroll);
        
        criticalPanel = new JPanel();
        criticalPanel.setBackground(Color.black);
        criticalPanel.setBounds(400, 280, 400, 100);
        con.add(criticalPanel);
        
        criticalLabel = new JLabel("!CRITICAL!");
        criticalLabel.setForeground(Color.red);
        criticalLabel.setFont(ALG70);
        criticalLabel.setVisible(false);
        criticalPanel.add(criticalLabel);
        
        levelUpPanel = new JPanel();
        levelUpPanel.setBackground(Color.black);
        levelUpPanel.setBounds(0, 280, 440, 100);
        levelUpPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        con.add(levelUpPanel);
        
        levelUpLabel = new JLabel(" ! LEVEL UP ! ");
        levelUpLabel.setForeground(Color.yellow);
        levelUpLabel.setFont(ALG70);
        levelUpLabel.setVisible(false);
        levelUpPanel.add(levelUpLabel);
        
        continueButton.setText("Continue");
        continueButton.setBackground(Color.black);
        continueButton.setForeground(Color.white);
        continueButton.setBounds(15, 15, 472, 223);
        continueButton.setFont(TNR100);
        continueButton.setVisible(false);
        buttonPanel.add(continueButton);
    }
    /**
     * This method instantiates a random enemy for the battle mode it also sets the labels for the enemy
     * a spawned enemy is based on hero level
     */
    public void randomEnemy() {
        String monsterName = Randomizers.getRandomEnemy();
        int monsterLevel = Randomizers.getRandomMonsterLevel(this.hero);
        int monsterHealth = Randomizers.getRandomEnemyHealth();

        monsterHealth += monsterLevel*10;
        this.EnemyMaxHP = monsterHealth;
        
        this.enemy = new Enemy(monsterName, 1, monsterLevel, monsterHealth);
        enemy.setMaxHealth(monsterHealth);
        
        enemyNameLabel.setText(monsterName+" ");
        enemyLvlLabel.setText("LVL: " + monsterLevel);
        enemyHPLabel.setText(monsterHealth+"/"+this.EnemyMaxHP+" ");
    }
    /**
     * this method is used when the user has clicked the boss button
     * like the random enemy method it sets the labels for the enemy
     */
    public void enableBoss() {
        this.enemy = new Enemy("BOSS", 50, 40, 800);
        this.EnemyMaxHP = 800;
        
        enemy.setMaxHealth(800);
        enemyNameLabel.setText("BOSS");
        enemyLvlLabel.setText("LVL: 40");
        enemyHPLabel.setText(800+"/"+this.EnemyMaxHP);
    }
    /**
     * this method updates the users health and enemies health once the user has attacked
     * it updates all the labels corresponding to the attacks from both hero and enemy
     */
    public void attackUpdate() {
        if(hero.getWeaponDurability() > 0) {
            if(!hero.getWeaponType().equals("Stick")) {
                hero.setWeaponDurability(hero.getWeaponDurability()-1);
                if(hero.getWeaponNumber()!= 0) {
                    hero.getInventory().set(hero.getArrayIndex(), hero.getWeaponType() + " " + hero.getWeapon() + " (" + hero.getWeaponDurability() + ")");
                }
                if(hero.getWeaponDurability() == 0) {
                    attackButton.setEnabled(false);
                }
                attackButton.setText("(" + hero.getWeaponDurability()+")");
                if(hero.getWeaponType().equals("Stick")) {
                    attackButton.setText("Attack (INF)");
                }       
            }
            int playerDamage = Randomizers.getRandomHeroDamage(hero);
            boolean check = Randomizers.isCritical(hero);
            if(check == true) {
                criticalLabel.setVisible(true);
                playerDamage*=2;
            }
            int enemyDamage = Randomizers.getRandomEnemyDamage(enemy);
            enemy.setHealth(enemy.getHealth()-playerDamage);
            hero.setHealth(hero.getHealth()-enemyDamage);

            int[] enemyBounds = Randomizers.getEnemyBounds();
            enemyDamageLabel.setLocation(enemyBounds[0], enemyBounds[1]);
    //        enemyDamageLabel.setLocation(, );
            enemyDamageLabel.setText(""+playerDamage);
            enemyDamageLabel.setVisible(true);
            
            int[] heroBounds = Randomizers.getHeroBounds();
            heroDamageLabel.setLocation(heroBounds[0], heroBounds[1]);
    //        heroDamageLabel.setLocation(700, 200);
            heroDamageLabel.setText(""+enemyDamage);
            heroDamageLabel.setVisible(true);
            
            Timer timer = new Timer(1000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    enemyDamageLabel.setVisible(false);
                    heroDamageLabel.setVisible(false);
                }
            });
            timer.setRepeats(false);
            timer.start();
            updateArea.setText("You hit the " + enemy.getName() + " for " + playerDamage + " Damage!!!");
            updateArea.append("\nThe " + enemy.getName() + " hit you for " + enemyDamage + " Damage!!!");

            if(hero.getWeaponDurability() == 0) {
                updateArea.append("\nYour weapon has run out of durability!!");
            }
            if(hero.getHealth() < 1) {
                hero.setHealth(0);
                deathScreen();
            }
            if(enemy.getHealth() < 1) {
                enemy.setHealth(0);
                if(bossClicked == true) {
                    deathScreen();
                }
                else {
                    victoryScreen();
                }
            }
            playerHPLabel.setText(hero.getHealth()+"/"+hero.getMaxHealth());
            enemyHPLabel.setText(enemy.getHealth()+"/"+this.EnemyMaxHP);
            
            playerHPBar.setValue(hero.getHealth());
            if(playerHPBar.getValue()<hero.getMaxHealth()/4) {
                playerHPBar.setForeground(Color.red);
            }
            else {
                playerHPBar.setForeground(Color.green);
            }
            playerXPBar.setValue(hero.getExp());
            enemyHPBar.setValue(enemy.getHealth());
            
            if(enemyHPBar.getValue()<enemy.getMaxHealth()/4) {
                enemyHPBar.setForeground(Color.red);
            }
            else {
                enemyHPBar.setForeground(Color.green);
            }
        }
        else {         
            int enemyDamage = Randomizers.getRandomEnemyDamage(enemy);
            hero.setHealth(hero.getHealth()-enemyDamage);
            
            updateArea.setText("Your weapon has ran out of durability!!");
            updateArea.append("\nThe " + enemy.getName() + " hit you for " + enemyDamage + " Damage!!!");
            
            if(hero.getHealth() < 1) {
                hero.setHealth(0);
                deathScreen();
            }
            playerHPLabel.setText(hero.getHealth()+"/"+hero.getMaxHealth());
            attackButton.setEnabled(false);
            playerHPBar.setValue(hero.getHealth());
        }
    }
    /**
     * this method updates the screen once the enemy has been defeated, it updates labels
     * to show what is the outcome of the battle and updates heros values
     */
    public void victoryScreen() {
        attackButton.setVisible(false);
        runButton.setVisible(false);
        potionButton.setVisible(false);
        superButton.setVisible(false);
        
        hero.setKillCount(hero.getKillCount()+1);
        updateArea.append("\nYou have Successfully defeated the " + enemy.getName());
        updateArea.append("\nYour killcount is now : " + hero.getKillCount());
                
        int gold = Randomizers.getRandomGold(enemy);
        int exp = Randomizers.getRandomExp(enemy);
        int levelup = hero.levelUP(exp);
        
        hero.setGold(hero.getGold()+gold);
        updateArea.append("\nGold  +$" + gold);
        updateArea.append("\nExp   +"+exp);
        
        if(levelup == 1) {
            hero.setLevel(hero.getLevel()+1);
            levelUpLabel.setVisible(true);
            playerLvlLabel.setText("LVL: " + hero.getLevel());
            
            int damageIncrease = Randomizers.getRandomStatIncrease(hero);
            int healthIncrease = Randomizers.getRandomHealthIncrease(hero);
            
            hero.setDamage(hero.getDamage()+damageIncrease);
            hero.setMaxHealth(hero.getMaxHealth()+healthIncrease);
            hero.setHealth(hero.getHealth()+healthIncrease);
            if(hero.getHealth()>hero.getMaxHealth()) {
                hero.setHealth(hero.getMaxHealth());
            }
            updateArea.append("\nCongratulations you have leveled up !!!");
            updateArea.append("\nHealth Increase      +  " + healthIncrease);
            updateArea.append("\nDamage Increase    +  " + damageIncrease);
            
            playerHPLabel.setText(hero.getHealth()+"/"+hero.getMaxHealth());
            playerXPBar.setMaximum(hero.getNextLevel());
            playerXPBar.setValue(hero.getExp());
        }
        continueButton.setVisible(true);
    }
    /**
     * this method updates the screen to the end panel, it sets everything in the current visible
     * frame false and displays the players ranking, this method is displayed if the hero wins the game
     * or loses
     */
    public void deathScreen() {
//        buttonPanel.setVisible(false);
//        playerPanel.setVisible(false);
//        playerHPPanel.setVisible(false);
//        enemyPanel.setVisible(false);
//        enemyLvlPanel.setVisible(false);
//        enemyHPPanel.setVisible(false);
//        criticalPanel.setVisible(false);
//        levelUpPanel.setVisible(false);
//        playerNameLabel.setVisible(false);
//        playerLvlLabel.setVisible(false);
//        playerHPLabel.setVisible(false);
//        enemyNameLabel.setVisible(false);
//        enemyLvlLabel.setVisible(false);
//        enemyHPLabel.setVisible(false);
//        criticalLabel.setVisible(false);
//        updateArea.setVisible(false);
        scroll.setVisible(false);
//        playerHPBar.setVisible(false);
//        playerXPBar.setVisible(false);
//        enemyHPBar.setVisible(false);
        inventoryButton.setVisible(false);
//        enemyPicture.setVisible(false);
//        heroPicture.setVisible(false);
        con.setVisible(false);
        
        allPanel.setVisible(true);
        
        endPanel = new JPanel();
        endPanel.setBounds(20, 20, 960, 100);
        endPanel.setBackground(Color.black);
        allPanel.add(endPanel);
        
        endLabel = new JLabel("!!!YOU HAVE DIED!!!");
        if(bossClicked == true) {
            if(hero.getHealth()> 0) {
                endLabel.setText("!!!YOU WIN!!!");
            }
        }
        endLabel.setForeground(Color.red);
        endLabel.setFont(TNR100);
        endPanel.add(endLabel);
        
        endButtonPanel = new JPanel();
        endButtonPanel.setBounds(550, 250, 400, 340);
        endButtonPanel.setBackground(Color.black);
        endButtonPanel.setLayout(null);
        allPanel.add(endButtonPanel);
        
        homeButton.setText("Home");
        homeButton.setBackground(Color.black);
        homeButton.setForeground(Color.white);
        homeButton.setBounds(50, 20, 320, 140);
        homeButton.setFont(TNR100);
        endButtonPanel.add(homeButton);
        
        exitButton.setText("Exit");
        exitButton.setBackground(Color.black);
        exitButton.setForeground(Color.white);
        exitButton.setBounds(50, 180, 320, 140);
        exitButton.setFont(TNR100);
        endButtonPanel.add(exitButton);
        
        statPanel = new JPanel();
        statPanel.setBounds(30, 200, 600, 200);
        statPanel.setBackground(Color.black);
        statPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        allPanel.add(statPanel);
        
        endLvlLabel = new JLabel("Player Lvl:              " + hero.getLevel());
        endLvlLabel.setForeground(Color.white);
        endLvlLabel.setFont(TNR50);
        statPanel.add(endLvlLabel);
        
        endKillsLabel = new JLabel("Monsters Killed:     " + hero.getKillCount());
        endKillsLabel.setForeground(Color.white);
        endKillsLabel.setFont(TNR50);
        statPanel.add(endKillsLabel);
        
        endGoldLabel = new JLabel("Total Gold:           $" + hero.getGold());
        endGoldLabel.setForeground(Color.white);
        endGoldLabel.setFont(TNR50);
        statPanel.add(endGoldLabel);
        
        rankPanel = new JPanel();
        rankPanel.setBounds(30, 430, 440, 180);
        rankPanel.setBackground(Color.black);
        rankPanel.setLayout(null);
        allPanel.add(rankPanel);
        
        rankLabel = new JLabel("RANK:");
        rankLabel.setForeground(Color.white);
        rankLabel.setFont(TNR30);
        rankLabel.setBounds(50, 80, 100, 40);
        rankPanel.add(rankLabel);
        
        rankLetterLabel = new JLabel(getRank());
        rankLetterLabel.setForeground(Color.yellow);
        Font ALG120 = new Font("Algerian", Font.BOLD + Font.ITALIC, 200);
        rankLetterLabel.setFont(ALG120);
        rankLetterLabel.setLocation(200,0);
        rankLetterLabel.setSize(170, 200);
        rankPanel.add(rankLetterLabel);
        bossClicked = false;
        hero.setArrayIndex(0);
        hero.getInventory().clear();
    }
    /**
     * this method gets the current rank of the player based on the heros stats they received throughout the game
     * @return rank
     */
    public String getRank() {
        if(hero.getLevel()>50 || hero.getKillCount()>100 || hero.getGold() > 10000) {
            return "S";
        }
        else if(hero.getLevel()>10 || hero.getKillCount()>20 || hero.getGold()>1000) {
            return "A";
        }
        else if(hero.getLevel()>5 || hero.getKillCount()>10 || hero.getGold()>500) {
            return "B";
        }
        else if(hero.getLevel()>1 || hero.getKillCount()>5 || hero.getGold()>100) {
            return "C";
        }
        else {
            return "F";
        }
    }
    /**
     * this method updates the players potions and also checks if the user is able to use a potion
     * it updates all its corresponding labels
     */
    public void potionUpdate() {
        if(hero.getPotions() < 1) {
            updateArea.setText("Sorry you do not have any potions");
        }
        else if(hero.checkHealth(hero.getHealth())) {
            updateArea.setText("You are already at the max HP");
        }
        else {    
            int enemyDamage = Randomizers.getRandomEnemyDamage(enemy);
            updateArea.setText("You drink a potion and feel refreshed");
            updateArea.append("\nThe " + enemy.getName() + " hit you for " + enemyDamage + " Damage!!!");
            
            hero.setPotions(hero.getPotions()-1);
            hero.setHealth(hero.getHealth()+20);
            if(hero.getHealth() > hero.getMaxHealth()) {
                hero.setHealth(hero.getMaxHealth());
            }
            
            hero.setHealth(hero.getHealth()-enemyDamage);
            playerHPLabel.setText(hero.getHealth()+"/"+hero.getMaxHealth());
            
            int[] heroBounds = Randomizers.getHeroBounds();
            heroDamageLabel.setLocation(heroBounds[0], heroBounds[1]);
            heroDamageLabel.setText(""+enemyDamage);
            heroDamageLabel.setVisible(true);
            Timer timer = new Timer(1000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    heroDamageLabel.setVisible(false);
                }
            });
            timer.start();
            potionButton.setText("("+hero.getPotions()+")");
            this.playerHPBar.setValue(hero.getHealth());
            if(this.playerHPBar.getValue()<hero.getMaxHealth()/4) {
                this.playerHPBar.setForeground(Color.red);
            }
            else {
                this.playerHPBar.setForeground(Color.green);
            }
        }
    }
    /**
     * this method updates the players supers and also checks if the user is able to use a super
     * it updates all its corresponding labels
     */
    public void superUpdate() {
        if(hero.getSupers() < 1) {
            updateArea.setText("Sorry you do not have any supers");
        }
        else if(hero.checkHealth(hero.getHealth())) {
            updateArea.setText("You are already at the max HP");
        }
        else {
            int enemyDamage = Randomizers.getRandomEnemyDamage(enemy);
            updateArea.setText("You drink a potion and feel refreshed");
            updateArea.append("\nThe " + enemy.getName() + " hit you for " + enemyDamage + " Damage!!!");
            
            hero.setSupers(hero.getSupers()-1);
            hero.setHealth(hero.getMaxHealth());
            hero.setHealth(hero.getHealth()-enemyDamage);
            
            playerHPLabel.setText(hero.getHealth()+"/"+hero.getMaxHealth());
            superButton.setText("("+hero.getSupers()+")");
            playerHPBar.setValue(hero.getMaxHealth());
            
            Timer timer = new Timer(1000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    heroDamageLabel.setVisible(false);
                }
            });
            timer.start();
            
            if(playerHPBar.getValue()<hero.getMaxHealth()/4) {
                playerHPBar.setForeground(Color.red);
            }
            else {
                playerHPBar.setForeground(Color.green);
            }
        }
    }
    /**
     * this method checks if the user has been successful in running away from the enemy
     * it gets a random run percentage based on hero and enemy level
     * @return true or false if user is successful
     */
    public boolean runUpdate() {
        double a = Randomizers.runRandom(hero, enemy, timesRan);
        Random rand = new Random();
        
        double b = 0 + (100 - 0) * rand.nextDouble();
        if(b < a) {
            updateArea.setText("You Safely get away from the enemy");
            attackButton.setVisible(false);
            runButton.setVisible(false);
            potionButton.setVisible(false);
            superButton.setVisible(false);
            continueButton.setVisible(true);
            bossClicked = false;
            timesRan++;
            Randomizers.runRandom(hero, enemy, timesRan);
            runButton.setText("Run (" + Randomizers.getRunPercentage()+"%)");
            return true;
        }
        else {
            updateArea.setText("You fail to escape the enemy!");
            int enemyDamage = Randomizers.getRandomEnemyDamage(enemy);
            hero.setHealth(hero.getHealth()-enemyDamage);
            if(hero.getHealth()<= 0) {
                hero.setHealth(0);
                deathScreen();
            }
            updateArea.append("\nThe " + enemy.getName() + " hit you for " + enemyDamage + " Damage!!!");
            playerHPLabel.setText(hero.getHealth()+"/"+hero.getMaxHealth());
            timesRan++;
            Randomizers.runRandom(hero, enemy, timesRan);
            runButton.setText("(" + Randomizers.getRunPercentage()+"%)");
            return false;
        }
    }
    /**
     * this method resets the current hero back to its original state, it is used for the home
     * button as the hero is static
     */
    public void resetHero() {
        if(this.hero.getPlayerClass().equals("Warrior")) {
            this.hero.setHealth(150);
            this.hero.setMaxHealth(150);
            this.hero.setExp(0);
            this.hero.setLevel(1);
            this.hero.setDamage(10);
            this.hero.setWeaponNumber(1);
            this.hero.setPotions(10);
            this.hero.setSupers(0);
            this.hero.setKillCount(0);
            this.hero.setGold(150);
            this.hero.setWeapon("Sword");
            this.hero.setDurability();
        }
        if(this.hero.getPlayerClass().equals("Archer")) {
            this.hero.setHealth(125);
            this.hero.setMaxHealth(125);
            this.hero.setExp(0);
            this.hero.setLevel(1);
            this.hero.setDamage(12);
            this.hero.setWeaponNumber(1);
            this.hero.setPotions(11);
            this.hero.setSupers(0);
            this.hero.setKillCount(0);
            this.hero.setGold(100);
            this.hero.setWeapon("Bow");
            this.hero.setDurability();
        }
        if(this.hero.getPlayerClass().equals("Mage")) {
            this.hero.setHealth(100);
            this.hero.setMaxHealth(100);
            this.hero.setExp(0);
            this.hero.setLevel(1);
            this.hero.setDamage(15);
            this.hero.setWeaponNumber(1);
            this.hero.setPotions(8);
            this.hero.setSupers(0);
            this.hero.setKillCount(0);
            this.hero.setGold(80);
            this.hero.setWeapon("Wand");
            this.hero.setDurability();
        }
    }
}

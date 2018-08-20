/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;
/**
 * This class is the main object that is used to store the users character preference
 * As this class is abstract no variables are initialized until the user either chooses 
 * a character or loads a game
 * @author Lukey
 */
public abstract class Hero {
    
    protected static String name;
    protected static String type;
    protected static String weapon;
    protected static String[] weapons;
    protected static int MAXHEALTH;
    protected static int health;
    protected static int exp;
    protected static int level;
    protected static int damage;
    protected static int weaponNumber;
    protected static int weaponDamage;
    protected static int weaponDurability;
    protected static int potions;
    protected static int supers;
    protected static int killCount;
    protected static int gold;
    protected static int[] weaponDamageList;
    protected static final int maxLevel = 200;
    protected static final int maxLevelXP = 100000;
    protected static ArrayList<String> inventory;
    protected static int arrayIndex;
    
    /**
     * Constructor: Sets up the Hero with the specified parameters given
     * @param name
     * @param type
     * @param health
     * @param level
     * @param damage 
     */
    public Hero(String name, String type, int health, int level, int damage) {
        this.name = name;
        this.type = type;
        this.health = health;
        this.level = level;
        this.damage = damage;
    }
    /**
     * an abstract method which sets the arrayindex
     */
    public abstract void setArrayIndex(int arrayIndex);
    /**
     * an abstract method which gets the arrayindex
     * @return integer
     */
    public abstract int getArrayIndex();
    /**
     * An abstract method which gets the durability of the weapon number
     * @return durability
     */
    public abstract int getWeaponNumberDurability(int weaponNumber);
    /**
     * An abstract method which sets the inventory
     */
    public abstract void setInventory(ArrayList<String> inventory);
    /**
     * An abstract method which gets the inventory
     * @return arraylist<string> inventory
     */
    public abstract ArrayList<String> getInventory();
    /**
     * An abstract method which gets the level
     */
    public abstract int getLevel();
    /**
     * An abstract method which sets the level
     */
    public abstract void setLevel(int level);
    /**
     * An abstract method which sets damage
     */
    public abstract void setDamage(int damage);
    /**
     * An abstract method which gets damage
     */
    public abstract int getDamage();
    /**
     * An abstract method which sets the weapon number
     */
    public abstract void setWeaponNumber(int weaponNumber);
    /**
     * An abstract method which gets the weapon number
     */
    public abstract int getWeaponNumber(); 
    /**
     * An abstract method which sets the weapon damage
     */
    public abstract void setWeaponDamage(int weaponDamage);
    /**
     * An abstract method which gets the weapon damage
     */
    public abstract int getWeaponDamage();
    /**
     * An abstract method which sets the amount of potions
     */
    public abstract void setPotions(int potions);
    /**
     * An abstract method which gets the amount of potions
     */
    public abstract int getPotions();
    /**
     * An abstract method which sets the amount of supers
     */
    public abstract void setSupers(int supers);
    /**
     * An abstract method which gets the amount of supers
     */
    public abstract int getSupers();
    /**
     * An abstract method which sets the current weapon
     */
    public abstract void setWeapon(String weapon);
    /**
     * An abstract method which gets the current weapon
     */
    public abstract String getWeapon();
    /**
     * An abstract method which sets the maximum health
     */
    public abstract void setMaxHealth(int maxHealth);
    /**
     * An abstract method which gets the maximum health
     */
    public abstract int getMaxHealth();
    /**
     * An abstract method which sets the health
     */
    public abstract void setHealth(int health);
    /**
     * An abstract method which gets the health
     */
    public abstract int getHealth();
    /**
     * An abstract method which sets the gold
     */
    public abstract void setGold(int gold);
    /**
     * An abstract method which gets the gold
     */
    public abstract int getGold();
    /**
    * An abstract method which gets the weapon damage corresponding to the inputted number
    */
    public abstract int getWeaponNumberDamage(int weaponNumber);
    /**
     * An abstract method which gets the type of weapon
     */
    public abstract String getWeaponType();
    /**
     * An abstract method which gets the weapon durability
     */
    public abstract int getWeaponDurability();
    /**
     * An abstract method which sets the weapon durability
     * @param weaponDurability 
     */
    public abstract void setWeaponDurability(int weaponDurability);
    /**
     * An abstract method which checks if the user is currently at max health
     */
    public abstract boolean checkHealth(int health);
    
    /**
     * name mutator
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * name accessor
     * @return 
     */
    public String getName() {
        return name;
    }
    /**
     * hero type mutator
     * @param name 
     */
    public void setClass(String type) {
        this.type = type;
    }
    /**
     * hero type accessor
     * @return 
     */
    public String getPlayerClass() {
        return type;
    }
    /**
     * exp mutator
     * @param name 
     */
    public void setExp(int exp) {
        this.exp = exp;
    }
    /**
     * exp accessor
     * @return 
     */
    public int getExp() {
        return exp;
    }
    /**
     * kill count mutator
     * @param name 
     */
    public void setKillCount(int killCount) {
        this.killCount = killCount;
    }
    /**
     * kill count accessor
     * @return 
     */
    public int getKillCount() {
        return killCount;
    }
    /**
     * This method updates the current players exp and levels up if necessary
     * @param exp
     * @return 1 or 0
     */
    public int levelUP(int exp) {
        double xp = 0.0f;
        
        double tempLevel = level;
        xp = tempLevel * 300.0 * Math.pow(2.0, tempLevel / 7.0);
        xp = xp/4;
        
        setExp(this.exp+exp);
        if(this.exp > xp)
        {
            this.level++;
            return 1;
        }
        return 0;
    }
    /**
     * this method finds out how much exp is needed in total for the next level
     * @return a(xp)
     */
    public int getNextLevel() {
        double xp = 0.0f;
        xp = level * 300 * Math.pow(2.0, level / 7.0);
        xp = xp/4;
        int a = (int) xp;
        return a;
    }
    /**
     * this method generates a random number from 10 - 30
     * @return number
     */
    public int generator()
    {
        Random rand = new Random();
        int number = rand.nextInt(20)+10;
        return number;
    }
    /**
     * this method returns the amount of crit chance a weapon has
     * @param weapon
     * @return crit chance
     */
    public int getCritChance(int weapon) {
        if(weapon == 1) {
            return 10;
        }
        if(weapon == 2) {
            return 12;
        }
        if(weapon == 3) {
            return 16;
        }
        if(weapon == 4) {
            return 25;
        }
        return 0;
    }
    /**
     * this method sets the players weapon durability based on their
     * current weapon
     */
    public void setDurability() {
        if(this.weaponNumber == 1) {
            this.setWeaponDurability(10);
        }
        if(this.weaponNumber == 2) {
            this.setWeaponDurability(15);
        }
        if(this.weaponNumber == 3) {
            this.setWeaponDurability(20);
        }
        if(this.weaponNumber == 4) {
            this.setWeaponDurability(30);
        }
    }
    /**
     * String representation of the hero
     * @return 
     */
    public String toString() {
        return "Welcome " + name;
    }
}

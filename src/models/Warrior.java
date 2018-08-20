/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents a character that is based as an Warrior
 * A Warrior has different stats compared to other characters
 * @author Lukey
 */
public class Warrior extends Hero {
    
    private static boolean alive = true;
    private static int MAXHEALTH = 150;
    private static int health = 150;
    private static int damage = 10;
    private static int killcount = 0;
    private static int totalDamage = 0;
    private static int potions = 10;
    private static int supers = 0;
    private static int level = 1;
    private static int exp = 0;
    private static int gold = 150;
    private static int weaponNumber = 1;
    private static int weaponDamage;
    private static int weaponDurability;
    private static int[] weaponDamageList = { 5, 10, 15, 20 };
    private static String weapon = "Sword";
    private static String[] weapons = { "Stick", "Plastic", "Wood", "Metal" };
    private static ArrayList<String> inventory;
    private static int arrayIndex = 0;
    
    /**
    * Constructor: initializes an warrior and sets up with the parameters given
    * @param name
    * @param type
    * @param health
    * @param level
    * @param damage 
    */
    public Warrior(String name, String type, int health, int level, int damage) {
        
        
        super(name, type, health, level, damage);
    }
    /**
     * arrayIndex mutator
     * @param arrayIndex 
     */
    public void setArrayIndex(int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }
    /**
     * arrayIndex accessor
     * @return arrayIndex
     */
    public int getArrayIndex() {
        return arrayIndex;
    }
    /**
     * this method returns the durability of the certain weapon of the numebr given
     * @param weaponNumber
     * @return durability 
     */
    public int getWeaponNumberDurability(int weaponNumber) {
        if(weaponNumber == 1)
        {
            return 10;
        }
        if(weaponNumber == 2)
        {
            return 20;
        }
        if(weaponNumber == 3)
        {
            return 30;
        }
        if(weaponNumber == 4)
        {
            return 40;
        }
        return 0;
    }
    /**
     * inventory mutator
     * @param inventory 
     */
    public void setInventory(ArrayList<String> inventory) {
        this.inventory = inventory;
    }
    /**
     * inventory accessor
     * @return inventory
     */
    public ArrayList<String> getInventory() {
        return inventory;
    }
    /**
     * weaponDurability mutator
     * @param weaponDurability 
     */
    public void setWeaponDurability(int weaponDurability) {
        this.weaponDurability = weaponDurability;
    }
    /**
     * weaponDurability accessor
     * @return weaponDurability
     */
    public int getWeaponDurability() {
        return weaponDurability;
    }
    /**
     * maxhealth mutator
     * @param maxHealth 
     */
    public void setMaxHealth(int maxHealth) {
        this.MAXHEALTH = maxHealth;
    }
    /**
     * maxhealth accessor
     * @return MAXHEALTH
     */
    public int getMaxHealth() {
        return MAXHEALTH;
    }
    /**
     * health mutator
     * @param health 
     */
    public void setHealth(int health) {
        this.health = health;
    }
    /**
     * health accessor
     * @return health
     */
    public int getHealth() {
        return health;
    }
    /**
     * damage mutator
     * @param damage 
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }
    /**
     * damage accessor
     * @return 
     */
    public int getDamage() {
        return damage;
    }
    /**
     * potions mutator
     * @param potions 
     */
    public void setPotions(int potions) {
        this.potions = potions;
    }
    /**
     * potions accessor
     * @return potions
     */
    public int getPotions() {
        return potions;
    }
    /**
     * supers mutator
     * @param 
     */
    public void setSupers(int supers) {
        this.supers = supers;
    }
    /**
     * supers accessor
     * @return supers
     */
    public int getSupers() {
        return supers;
    }
    /**
     * level mutator
     * @param level 
     */
    public void setLevel(int level) {
        this.level = level;
    }
    /**
     * level accessor
     * @return level
     */
    public int getLevel() {
        return this.level;
    }
    /**
     * gold mutator
     * @param gold 
     */
    public void setGold(int gold) {
        this.gold = gold;
    }
    /**
     * gold accessor
     * @return gold
     */
    public int getGold() {
        return gold;
    }
    /**
     * weapon number mutator
     * @param weaponNumber 
     */
    public void setWeaponNumber(int weaponNumber) {
        this.weaponNumber = weaponNumber;
    }
    /**
     * weapon number accessor
     * @return weaponnumber
     */
    public int getWeaponNumber() {
        return weaponNumber;
    }
    /**
     * this method gets the damage corresponding to the weapon
     * @param weaponNumber
     * @return damage
     */
    public int getWeaponNumberDamage(int weaponNumber)
    {
        return weaponDamageList[weaponNumber];
    }
    /**
     * this weapon gets the type of weapon corresponding to the character
     * @return type of weapon
     */
    public String getWeaponType() {
        return weapons[weaponNumber-1];
    }
    /**
     * weapondamage mutator
     * @param weaponDamage 
     */
    public void setWeaponDamage(int weaponDamage) {
        this.weaponDamage = weaponDamage;
    }
    /**
     * weapondamage mutator
     * @param weapon 
     */
    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }
    /**
     * weapon accessor
     * @return weapon
     */
    public String getWeapon() {
        return weapon;
    }
    /**
     * weapondamage accessor
     * @return 
     */
    public int getWeaponDamage() {
        return weaponDamageList[weaponNumber-1];
    }
    /**
     * This method checks the users current health against the max health
     * returns true if current health equals max health
     * @param health
     * @return true/false
     */
    public boolean checkHealth(int health) {
        if(this.MAXHEALTH == health) {
            return true;
        }
        else {
            return false;
        }
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
            this.setWeaponDurability(20);
        }
        if(this.weaponNumber == 3) {
            this.setWeaponDurability(30);
        }
        if(this.weaponNumber == 4) {
            this.setWeaponDurability(40);
        }
    }
}

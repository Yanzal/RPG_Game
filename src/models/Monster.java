/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Random;

/**
 * This class is the main object that is used to store the enemies in the game
 * Initially I had an idea to store multiple classes would extend this class
 * As this class is abstract no variables are initialized until the user gets into
 * battle
 * @author Lukey
 */

public abstract class Monster {
    
    protected static String name;
    protected static int damage;
    protected static int level;
    protected static int health;
    protected static int maxHealth;
    
    /**
     * Constructor: initializes a monster with the parameters given
     * @param name
     * @param damage
     * @param level
     * @param health 
     */
    public Monster(String name, int damage, int level, int health)
    {
        this.name = name;
        this.damage = damage;
        this.level = level;
        this.health = health;
    }
    /**
     * name mutator
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * name accessor
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * damage mutator
     * @param name 
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }
    /**
     * damage accessor
     * @return damage
     */
    public int getDamage() {
        return damage;
    }
    /**
     * level mutator
     * @param name 
     */
    public void setLevel(int level) {
        this.level = level;
    }
    /**
     * level accessor
     * @return level
     */
    public int getLevel() {
        return level;
    }
    /**
     * health mutator
     * @param name 
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
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    /**
     * String representation of a monster
     * @return string
     */
    public String toString() { 
        return "A wild " + name + " has appeared!";
    }
}

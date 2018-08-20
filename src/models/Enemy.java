/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Random;

/**
 * This class represents an enemy that is based on a monster
 * this class is used to initialize different monsters
 * which depend on user level and strength
 * @author Lukey
 */
public class Enemy extends Monster{
    
    private static String name;
    private static int damage;
    private static int level;
    static Random rand = new Random();
    
    /**
     * Constructor: initializes an enemy with the parameters given
     * @param name
     * @param damage
     * @param level
     * @param health 
     */
    public Enemy(String name, int damage, int level, int health) { 
        super(name, damage, level, health);
    }
}

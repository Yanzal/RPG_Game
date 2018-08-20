/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import models.Hero;
import models.Enemy;
import java.util.ArrayList;
import java.util.Random;

/**
 * The Randomizers class implements methods which
 * returns and assigns variables to random numbers 
 * depending on the method called
 * @author Lukey
 */
public class Randomizers {
    
    private static ArrayList<String> enemies = new ArrayList<String>();
    private static String enemyname;
    private static double runPercentage;
    static Random rand = new Random();
    
    /**
     * this method randomizes an enemy 
     * @return enemyname
     */
    public static String getRandomEnemy() {
        enemies.add("Ghost");
        enemies.add("BloodVeld");
        enemies.add("Demon");
        enemies.add("IceMan");
        
        int i = rand.nextInt(4);
        enemyname = enemies.get(i);
        
        switch(enemyname) {
            case "Ghost": {
                return "Ghost";
            }
            case "BloodVeld": {
               return "BloodVeld";
            }
            case "Demon": {
                return "Demon";
            }
            case "IceMan": {
                return "IceMan";
            }
            default: {
                return "error";
            }
        }
    }
    /**
     * this method returns the enemy health based on the name randomized
     * @return enemyhealth
     */
    public static int getRandomEnemyHealth() {
        switch(enemyname) {
            case "Ghost": {
                return 30;
            }
            case "BloodVeld": {
               return 35;
            }
            case "Demon": {
                return 40;
            }
            case "IceMan": {
                return 50;
            }
            default: {
                return 0;
            }
        }
    }
    /**
     * this method randomly generates damage based on the heros base damage and weapon
     * @param hero
     * @return herodamage
     */
    public static int getRandomHeroDamage(Hero hero) {
        int tempDamage = hero.getWeaponDamage(); //gets the weapon damage user currently has
        tempDamage = tempDamage + rand.nextInt(hero.getDamage()); //randomly generates a number from the users base damage
        return tempDamage;
    }
    /**
     * this method checks if the user has landed a critical hit
     * @param hero
     * @return true if hit is critical
     */
    public static boolean isCritical(Hero hero) {
        int number = 100;
        if(hero.getWeaponNumber() == 2) {
            number = 80;
        }
        else if (hero.getWeaponDamage() == 3) {
            number = 60;
        }
        else if (hero.getWeaponNumber() == 4) {
            number = 40;
        }
        int crit = rand.nextInt(number); //checks if user has landed a critical hit
            if(crit < 10) {
                return true;
            }
            else
            {
                return false;
            }
    }
    /**
     * this method generates random damage based on enemy level
     * @param enemy
     * @return enemydamage
     */
    public static int getRandomEnemyDamage(Enemy enemy) {
        int level = enemy.getLevel();
        level = level*4/2;
        int damage = rand.nextInt(level);
        return damage+(level);
    }
    /**
     * this method randomly generates the monsters level based on hero level
     * @param hero
     * @return monster level
     */
    public static int getRandomMonsterLevel(Hero hero) {
        int level = hero.getLevel();
        level = rand.nextInt(level)+3;
        return level;
    }
    /**
     * this method generates a double percentage in which the user has to run away
     * from the enemy, it utilizes the heros level, the enemies level and the times the user
     * has attempted to run, it goes through some math to return a chance of running away
     * @return double
     */
    public static double runRandom(Hero hero, Enemy enemy, int timesRan) {
        double a = 0.0f;
        double b = hero.getLevel();
        double c = enemy.getLevel();
        if(b > c) {
            c += b/2;
        }
        if(b == c) {
            c*=c/2;
        }
        if(timesRan == 0) {
            a = b/c;
            a*=100;
            a = Math.round(a *100);
        }
        else  {
            a = b/c;
            a*=100;
            int d = enemy.getLevel();
            d = rand.nextInt(d)+1;
            a += timesRan*10/d;
            
            a = Math.round(a*100);    
        }
        a = a/100;
        if(a > 100.0) {
            a = 100.0;
        }
        runPercentage = a;
        return a;
    }
    /**
     * runPercentage accessor
     * @return runPercentage
     */
    public static double getRunPercentage() {
        return runPercentage;
    }
    /**
     * this method randomizes hero gold gained based on enemy level
     * @param enemy
     * @return gold
     */
    public static int getRandomGold(Enemy enemy) {
        int a = rand.nextInt(20)+20;
        int b = enemy.getLevel()*5;
        b = rand.nextInt(b)+10;
        return a+b;
    }
    /**
     * this method randomizes hero xp gained based on enemy level
     * @param enemy
     * @return exp
     */
    public static int getRandomExp(Enemy enemy) {
        int a = rand.nextInt(20)+20;
        int b = enemy.getLevel()*10;
        return a+b;
    }
    /**
     * this method randomizes hero hp gained based on hero level
     * @param hero
     * @return 
     */
    public static int getRandomHealthIncrease(Hero hero) {
        int a = rand.nextInt(10)+10+hero.getLevel();
        return a;
    }
    /**
     * this method randomizes hero damage increase based on hero level
     * @param hero
     * @return damage
     */
    public static int getRandomStatIncrease(Hero hero) {
        int a = rand.nextInt(5)+hero.getLevel();
        return a;
    }
    /**
     * this method returns a random bound for the damage label for the enemy
     * @return array of ints
     */
    public static int[] getEnemyBounds() {
        int a = rand.nextInt(200) + 100;
        int b = rand.nextInt(100) + 80;
        
        int[] c = new int[2];
        c[0] = a;
        c[1] = b;
        
        return c;
    }
    /**
     * this method returns a random bound for the damage label for the hero
     * @return array of ints
     */
    public static int[] getHeroBounds() {
        int a = rand.nextInt(100) + 650;
        int b = rand.nextInt(100) + 200;
        
        int[] c = new int[2];
        c[0] = a;
        c[1] = b;
        
        return c;
    }
}

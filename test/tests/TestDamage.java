/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import models.Enemy;
import models.Hero;
import models.Randomizers;
import models.Warrior;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Lukey
 */
public class TestDamage {
    
    private static int nbErrors;
    
    @Test
    /**
     * This method creates both a hero and an enemy with values assigned
     * this unit test will check if values set inside both of these models work
     * setting the damage dealt to these models should be precise
     */
    public void testDamages() {
        Enemy enemy = new Enemy(null, 20, 1, 100);
        Warrior warrior = new Warrior(null, null, 100, 1, 10);
        warrior.setHealth(100);

        //after an attack both hero and enemy have randomized attack damage
        //however for the sake of the unit testing i will be using a set damage
        //to test it
        System.out.println("ENEMY HEALTH : " + enemy.getHealth());
        System.out.println("HERO HEALTH : " + warrior.getHealth());
        assertEquals(100,enemy.getHealth(), 0);
        assertEquals(100,warrior.getHealth(), 0);
        
        int enemyDamaged = 20;
        int warriorDamaged = 50;
        enemy.setHealth(enemy.getHealth()-enemyDamaged);
        warrior.setHealth(warrior.getHealth()-warriorDamaged);
        
        System.out.println("ENEMY HIT FOR " + warriorDamaged);
        System.out.println("HERO HIT FOR " + enemyDamaged);
        
        assertEquals(80,enemy.getHealth(), 0);
        assertEquals(50,warrior.getHealth(), 0);
        
        System.out.println("ENEMY HEALTH : " + enemy.getHealth());
        System.out.println("HERO HEALTH : " + warrior.getHealth());
        
        //Testing random damage with the randomizers
        //warriors max damage should not be greater than the warriors max damage AND the weapon damage
        //weapon damage are set in a default depending on the type of hero
        //however the enemys damage can be a range
        
        int randomWarriorDamage = Randomizers.getRandomHeroDamage(warrior);
        
        assertTrue(randomWarriorDamage < warrior.getDamage()+warrior.getWeaponDamage());
        
    }
    public static void main(String[] args) {
        TestDamage test = new TestDamage();
        try {
            test.testDamages();
        }
        catch (Throwable e) {
            test.nbErrors++;
            e.printStackTrace();
        }
        if(test.nbErrors > 0) {
            throw new RuntimeException("There were " + test.nbErrors+" error(s)");
        }
    }
}

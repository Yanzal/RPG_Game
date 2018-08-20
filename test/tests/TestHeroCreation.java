/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import models.Enemy;
import models.Hero;
import models.Warrior;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Lukey
 */
public class TestHeroCreation {
    
    private static int nbErrors;
    
    @Test
    /**
     * This method creates both a hero and an enemy with values assigned
     * this unit test checks whether or not the health and damage that is created
     * within the characters are correct
     */
    public void testCreation() {
        Enemy enemy = new Enemy(null, 20, 1, 100);
        Warrior warrior = new Warrior(null, null, 100, 1, 10);
        warrior.setHealth(100);
        //Enemies HP is currently at 100, damage is currently at 20
        //Warriors HP is currently at 100, damage is currently at 10
        
        //Testing if these results are correct
        
        assertEquals(100,enemy.getHealth(), 0);
        assertEquals(20,enemy.getDamage(), 0);
        assertEquals(100,warrior.getHealth(), 0);
        assertEquals(10,warrior.getDamage(), 0);
        System.out.println("ENEMY CREATED");
        System.out.println("\tDamage : " + enemy.getDamage() + " Health: " + enemy.getHealth());
        System.out.println("HERO CREATED");
        System.out.println("\tDamage : " + warrior.getDamage() + " Health: " + warrior.getHealth());
    }
    public static void main(String[] args) {
        TestHeroCreation test = new TestHeroCreation();
        try {
            test.testCreation();
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

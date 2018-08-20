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
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Lukey
 */
public class TestSetWeapons {
    
    private static int nbErrors;
    
    @Test
    /**
     * This method creates both a hero and an enemy with values assigned
     * this unit test will check if values set inside both of these models work
     * setting the damage dealt to these models should be precise
     */
    public void testSetWeapons() {

        final String item1 = "Stick Sword";
        final String item2 = "Metal Sword";

        Warrior warrior = new Warrior(null,null, 100, 1, 10);
        System.out.println("Warrior current weapon : " + warrior.getWeaponType() +" " + warrior.getWeapon());
        
        //new characters should always spawn with a Stick plus their weapon type
        //as this is a warrior it should have a Stick Sword as its current weapon
        
        String expected = item1;
        String actual = warrior.getWeaponType() + " " + warrior.getWeapon();
        
        Assert.assertEquals(expected, actual);
        
        //Changing the weapon
        warrior.setWeaponNumber(4);
        //changing the weapon number of the warrior is defined in its class, each hero
        //holds weapons in an array, weapon number 4 is a metal sword
        
        //now the warriors weapon should be metal sword
        
        expected = item2;
        actual = warrior.getWeaponType() + " " + warrior.getWeapon();
        
        Assert.assertEquals(expected, actual);
        System.out.println("Warrior now weapon : " + warrior.getWeaponType() +" " + warrior.getWeapon());
    }
    public static void main(String[] args) {
        TestSetWeapons test = new TestSetWeapons();
        try {
            test.testSetWeapons();
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

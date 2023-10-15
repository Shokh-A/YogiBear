/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yogibear;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Shokhjakhon
 */
public class CharacterTest {
    
    public CharacterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of moveX method, of class Character.
     */
    @Test
    public void testMoveX() {
        System.out.println("moveX");
        Character instance = new Character(10,10,10,10, null);
        instance.moveX();
        int expResult = 11;
        assertEquals(expResult, instance.getX());
    }

    /**
     * Test of moveY method, of class Character.
     */
    @Test
    public void testMoveY() {
        System.out.println("moveY");
        Character instance = new Character(10,10,10,10, null);
        instance.moveY();
        int expResult = 11;
        assertEquals(expResult, instance.getY());
    }
    
}

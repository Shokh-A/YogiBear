/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yogibear;

import java.awt.Graphics;
import java.io.IOException;
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
public class LevelTest {
    
    public LevelTest() {
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
     * Test of collides method, of class Level.
     */
    @Test
    public void testCollides() throws IOException {
        System.out.println("collides");
        Character player = new Character(10,10,10,10,null);
        Level instance = new Level("data/levels/level0" + 11 + ".txt");
        instance.baskets.add(new Basket(10,10,10,10,null));
        boolean expResult = true;
        boolean result = instance.collides(player);
        assertEquals(expResult, result);
    }

    /**
     * Test of isOver method, of class Level.
     */
    @Test
    public void testIsOver() throws IOException {
        System.out.println("isOver");
        Character player = new Character(10,10,10,10,null);
        Level instance = new Level("data/levels/level0" + 11 + ".txt");
        instance.baskets.add(new Basket(10,10,10,10,null));
        boolean expResult = true;
        instance.collides(player);
        boolean result = instance.isOver();
        assertEquals(expResult, result);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yogibear;

import java.awt.Graphics;
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
public class SpriteTest {
    
    public SpriteTest() {
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
     * Test of collides method, of class Sprite.
     */
    @Test
    public void testCollides() {
        System.out.println("collides");
        Sprite other = new Sprite(10, 10, 10, 10, null);
        Sprite instance = new Sprite(10, 10, 10, 10, null);
        boolean expResult = true;
        boolean result = instance.collides(other);
        assertEquals(expResult, result);
    }
    
}

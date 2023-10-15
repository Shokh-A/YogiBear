/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yogibear;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
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
public class GameEngineTest {
    
    public GameEngineTest() {
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
     * Test of getHighScores method, of class GameEngine.
     */
    @Test
    public void testGetHighScores() {
        System.out.println("getHighScores");
        GameEngine instance = new GameEngine();
        int expResult = 10;
        int result = instance.getHighScores().size();
        assertEquals(expResult, result);
    }
    
}

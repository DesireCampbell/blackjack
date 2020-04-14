/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project.viewcontroller;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Desire Richards-Campbell
 */
public class PlayBlackjackTest {
    
    public PlayBlackjackTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of main method, of class PlayBlackjack.
     */
    /* Any double value should be truncated, not rounded. The expected output 
    here should be 232.36, not 232.37 */
    @Test
    public void testGoodRoundToCent() {
        System.out.println("roundToCent");
        double  value = 232.36999999;
        double expected = 232.36;
        double actual = PlayBlackjack.roundToCent(value);
        int compResult = Double.compare(expected,actual);
        //assertEquals won't work with doubles, so compare to output of Double.compare()
        assertEquals(0,compResult);
    }
    
    /* Here we will test an edge case that might cause problems: here there is 
    no value after the decimal, so the input and output values should be the same, 
    we'll check this here to see if it causes problems*/
    @Test
    public void testBoundaryRoundToCent() {
        System.out.println("roundToCent");
        double  value = 232.00;
        double expected = 232.00;
        double actual = PlayBlackjack.roundToCent(value);
        int compResult = Double.compare(expected,actual);
        //assertEquals won't work with doubles, so compare to output of Double.compare()
        assertEquals(0,compResult);
    }
    
}

/*
    BlackjackHand.java
    Author: Desire Richards-Campbell (991571959)
    Date: 02 Apr. 2020

    Description
    [Description about the class]
*/
package ca.sheridancollege.project.model;

import java.util.ArrayList;

/**
 * 
 * @author Desire Richards-Campbell (991571959)
 */
public class BlackjackHand extends GroupOfCards{

    private double bet;
    private double insuranceBet;
    
       
    public BlackjackHand(double bet) {
        super(0);
        this.bet = bet;
    }
      
    public BlackjackHand(double bet, ArrayList<Card> cards) {
        super(0);
        this.bet = bet;
        
    }

}//end of class BlackjackHand()

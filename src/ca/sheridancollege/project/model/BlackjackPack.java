/*
    BlackjackPack.java
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
public class BlackjackPack extends GroupOfCards{

    public BlackjackPack(int numDecks) {
        super(numDecks*52);
        //generate decks
        ArrayList<Card> newCards = new ArrayList();
        for (int i = 0; i < numDecks; i++) {
            System.out.println("Deck #"+ (i+1));
            //generate whole deck
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    BlackjackCard card = new BlackjackCard(rank,suit);
                    //System.out.println("\t"+ card);
                    newCards.add(card);
                }
            }
        }
        super.setCards(newCards);
        //shuffle whole pack
        super.shuffle();
    }

}//end of class BlackjackPack()

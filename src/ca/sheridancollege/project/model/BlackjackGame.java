/*
    BlackjackGame.java
    Author: Desire Richards-Campbell (991571959)
    Date: 02 Apr. 2020

    Description
    Models a game of Blackjack with Players, Hands of Cards, etc.
*/
package ca.sheridancollege.project.model;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Desire Richards-Campbell (991571959)
 */
public class BlackjackGame extends Game{

    public BlackjackGame() {
        //set the name of the game in the parent object
        super("Blackjack");
        //
    }
    
    /**
     * Returns the Dealer player
     * @return the Dealer player, or null if no dealer found.
     */
    public BlackjackDealer getDealer() throws Exception {
        //The Dealer should be last player in the list, so we'll iterate 
        // backwards through the list until we find a Dealer object:
        for (int i = this.getPlayers().size()-1; i >= 0; i--) {
            if(this.getPlayers().get(i) instanceof BlackjackDealer){
                return (BlackjackDealer)(this.getPlayers().get(i));
            }
        }
        throw new Exception("No dealer in player list");
    }


    /**
     * Initiates basic game loop.
     */
    @Override
    public void play() {

        //
    }

    @Override
    public void declareWinner() {
        
    }

    @Override
    public String toString() {
        //A game is made up of players, so print all the players:
        String s = "";
        for (Player player : this.getPlayers()) {
            if (player instanceof BlackjackDealer) {
                s += (BlackjackDealer)player + "\n";
            }else{
                s += (BlackjackPlayer)player + "\n";
            }
        }
        return s;
    }
    
    
    
    /**
     * The four options for a player's turn HIT, DOUBLE, SPLIT, STAND
     */
    public void hit(BlackjackPlayer player, BlackjackHand hand) {
        try {
            //Dealer gives next card from pack to player
            hand.addCard(this.getDealer().dealCard());
        } catch (Exception ex) {
            //no dealer found
        }
    }
    
    public void doubleDown(BlackjackPlayer player, BlackjackHand hand) throws Exception {
        if (hand.showCards().size() > 2) {
            //must have only two cards in hand
            throw new Exception(player.getPlayerID() +"'s hand has too many card to double down");
        }else if (player.getBalance() < hand.getBet()) {
            //must have enough money to double bet
            throw new Exception(player.getPlayerID() +"'s balance too low to double down");
        }else{
            //decrease player balance
            player.setBalance(player.getBalance() - hand.getBet());
            //double bet
            hand.setBet(hand.getBet() * 2);
            //hit
            this.hit(player, hand);
            //stand
            this.stand(player, hand);
        }
        this.hit(player, hand);
        
    }
    
    public void splitPair(BlackjackPlayer player, BlackjackHand hand) throws Exception {
        //player splits hand into two hands
        if (hand.showCards().size() > 2) {
            //must have only two cards in hand
            throw new Exception(player.getPlayerID() +"'s hand has too many card to split pair");
        }else if (player.getBalance() < hand.getBet()) {
            //must have enough money to double bet
            throw new Exception(player.getPlayerID() +"'s balance too low to split pair");
        }else{
            //decrease player balance
            player.setBalance(player.getBalance() - hand.getBet());
            //create new hand
            BlackjackHand newHand = new BlackjackHand(hand.getBet());
            //move card to new hand
            newHand.addCard(hand.showCards().remove(1));
            //now both hand and newHand have the same bet and one card each
            if (!hand.equals(newHand)) {
                throw new Exception("something failed during split pair: hand values aren't equal");
            }
            //get a new card for each hand right away
            this.hit(player, hand);
            this.hit(player, newHand);
            //now both hands should have two cards each
            if (hand.showCards().size() == 2 && newHand.showCards().size() == 2) {
                throw new Exception("something failed during split pair: hands aren't both two cards each");
            }
        }
        this.hit(player, hand);
    }
    
    public void stand(BlackjackPlayer player, BlackjackHand hand) {
        //global flag?
    }
    

}//end of class BlackjackGame()

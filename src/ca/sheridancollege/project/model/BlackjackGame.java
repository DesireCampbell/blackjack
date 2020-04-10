/*
    BlackjackGame.java
    Author: Desire Richards-Campbell (991571959)
    Date: 02 Apr. 2020

    Description
    Models a game of Blackjack with Players, Hands of Cards, etc.
*/
package ca.sheridancollege.project.model;

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
    public BlackjackDealer getDealer() {
        //The Dealer should be last player in the list, so we'll iterate 
        // backwards through the list until we find a Dealer object:
        for (int i = this.getPlayers().size()-1; i >= 0; i--) {
            if(this.getPlayers().get(i) instanceof BlackjackDealer){
                return (BlackjackDealer)(this.getPlayers().get(i));
            }
        }
        return null;
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
    
    
    

}//end of class BlackjackGame()

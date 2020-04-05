package ca.sheridancollege.project.viewcontroller;

import ca.sheridancollege.project.model.*;
import java.util.*;

public class PlayBlackjack {

    // width of the CLI interface (monospace characters)
    private final int SCREEN_WIDTH = 80;
    
    /**
     *
     */
    public void main() {

        Game blackjack = new BlackjackGame();
        blackjack.play();
        
        
        // WHOLE GAME LOOP /////////////////////////////////////////////////////
        
        
        // SET UP PLAYERS //////////////////////////////////////////////////////
        
        //prompt for number of players
        int numPlayers = Integer.parseInt( readInput("How many players for this game: ") );
        //loop through players
        ArrayList<Player> players = new ArrayList();
        for (int i = 0; i < numPlayers; i++) {
            //name? 
            String name = readInput("Player "+ i+1 +"'s name: ");
            Player newPlayer = new BlackjackPlayer(name);
            players.add(newPlayer);
        }
        //Create dealer, create pack of 6 decks //add to blackjack.players in last position
        Player dealer = new BlackjackDealer();
        //loop through all players //bet amount? //decrease balance //create hand
        
        
        // NEW HAND BETTING AND DEALING ////////////////////////////////////////
        
        
        
        // BEFORE FIRST TURN ///////////////////////////////////////////////////
        
        
        
        // PLAYER TURNS ////////////////////////////////////////////////////////
        
        
        
        // SETTLEMENT //////////////////////////////////////////////////////////
        
        
        
        
        
        
        
        
    }

    private static String readInput(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        return input.next().trim();
    }
    
    private static void printToScreen(String prompt) {
        System.out.println(prompt);
    }
    
    private static void printCards(BlackjackHand hand) {
        
    }
    
    private static void print() {
        
    }
    
    
    
    

}

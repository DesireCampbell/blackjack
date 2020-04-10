package ca.sheridancollege.project.viewcontroller;

import ca.sheridancollege.project.model.*;
import java.util.*;

public class PlayBlackjack {

    // width of the CLI interface (monospace characters)
    private final int SCREEN_WIDTH = 80;
    
    /**
     *
     */
    public static void main(String args[]) {

        //create game object
        BlackjackGame blackjack = new BlackjackGame();
        blackjack.play();
        
        
        // WHOLE GAME LOOP /////////////////////////////////////////////////////
        
        
        // SET UP PLAYERS //////////////////////////////////////////////////////
        
        //prompt for number of players:
        int numPlayers = readUserInt("How many players for this game: ");
        //get player names:
        ArrayList<Player> players = new ArrayList();
        for (int i = 0; i < numPlayers; i++) {
            //prompt user:
            String name = readUserLine("Player "+ (i+1) +"'s name: ");
            //catch exception here:
            try {
                Player newPlayer = new BlackjackPlayer(name);
                players.add(newPlayer);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                //try same player again
                i--;
            }
        }
        //Create dealer //add to blackjack.players in last position
        players.add(new BlackjackDealer());
        //set new list of players to the blackjack object
        blackjack.setPlayers(players);
        
        
        // NEW HAND BETTING AND DEALING ////////////////////////////////////////
        
        //loop through all players //bet amount? //decrease balance //create empty hand
        for (Player o : blackjack.getPlayers()) {
            if(o instanceof BlackjackDealer){
                //dealer doesn't have a real bet
                BlackjackDealer dealer = (BlackjackDealer)o;
                BlackjackHand newHand = new BlackjackHand(0.0);
                dealer.addHand(newHand);
            }else{
                //downcast player object
                BlackjackPlayer player = (BlackjackPlayer)o;
                //prompt player for bet amount:
                String prompt = player.getPlayerID() 
                    +" ($"+ player.getBalance() +")"
                    +", how much to bet on this hand: ";
                double bet = readUserDouble(prompt);
                //decrease balance:
                try {
                    double newBalance = player.getBalance() - bet;    
                    player.setBalance(newBalance);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                //set bet to hand:
                BlackjackHand newHand = new BlackjackHand(bet);
                //System.out.println(newHand);
                player.addHand(newHand);
            }
        }
        
        //now players have bets
        //each player is dealt one card, then each is dealt another

        for (int i = 0; i < 2; i++) {
            for (Player o : blackjack.getPlayers()) {
                //downcast player
                BlackjackPlayer player = (BlackjackPlayer)o;
                //players only have one hand each, hit once
                blackjack.hit(player.getHands().get(0));
            }
        }

        
        System.out.println("");
        System.out.println(blackjack);//testing method()
        
        
        
        
        // BEFORE FIRST TURN ///////////////////////////////////////////////////
        
        //check dealer hand for possible natural
        
        //prompt players for insurance bets
        
        //check daler hand value
        

        
        
        
        // PLAYER TURNS ////////////////////////////////////////////////////////
        
        //loop through every player
        /*
        for (Player o : blackjack.getPlayers()) {
            //downcast to BlackjackPlayer
            BlackjackPlayer player = (BlackjackPlayer)o;
            //loop through every hand
            for (BlackjackHand hand : player.getHands()) {
                //check the hand for next steps:
                ArrayList<String> availableActions = new ArrayList();
                String prompt = "Choose an action: ";
                    
                if (hand.getValue() > 21) {
                    //bust
                }else if (hand.getValue() == 21) {
                    //force stand
                }else {
                    //can stand()
                    availableActions.add("stand");
                    prompt += "Stand";
                    //can hit()
                    availableActions.add("hit");
                    prompt += ", Hit";
                    if (hand.showCards().size() == 2) {
                        if (hand.getValue() >= 9 && hand.getValue() <= 11) {
                            //can doubledown()
                            availableActions.add("double");
                            prompt += ", Double Down";
                        }
                        if ( ((BlackjackCard)(hand.showCards().get(0))).getRank() == ((BlackjackCard)(hand.showCards().get(1))).getRank() ) {
                            //can splitpairs()
                            availableActions.add("split");
                            prompt += ", Split Pairs";
                        }
                    }
                    
                    //prompt user for input
                    String userAction = "";
                    while (!availableActions.contains(userAction)) {
                        userAction = readUserString(prompt).toLowerCase();
                        switch (userAction) {
                            case "stand": player.stand(); break;
                            case "hit": player.hit(); break;
                            case "double": player.doubleDown(); break;
                            case "split": player.splitPair(); break;
                            default: System.out.println("Command not recognised. ");
                        }
                    }
                    
                    
                    
                }
            }
        }
        */
        
        
        
        // SETTLEMENT //////////////////////////////////////////////////////////
        
        
        
        
        
        
        
        
        
        
    }

    /**
     * Methods for getting input from user. Bad inputs are handled, user is 
     * prompted repeatedly until good data is given.
     * @param prompt
     * @return 
     */
    private static String readUserLine(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        try {
            return input.nextLine().trim();
        } catch (Exception e) {
            System.out.println("ERROR reading input");
            System.out.println(e.getMessage());
            return readUserLine(prompt);
        }
        
    }
    
    private static String readUserString(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        try {
            return input.next().trim();
        } catch (Exception e) {
            System.out.println("ERROR reading input");
            System.out.println(e.getMessage());
            return readUserString(prompt);
        }
        
    }

    private static int readUserInt(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        try {
            return input.nextInt();
        } catch (Exception e) {
            System.out.println("ERROR reading input");
            System.out.println(e.getMessage());
            return readUserInt(prompt);
        }
    }

    private static Double readUserDouble(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        try {
            return input.nextDouble();
        } catch (Exception e) {
            System.out.println("ERROR reading input");
            System.out.println(e.getMessage());
            return readUserDouble(prompt);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    //testing only
    public static void printAllPlayers(BlackjackGame game,String tagString){
        System.out.println();
        System.out.println("**************************************************");
        System.out.println(tagString);
        System.out.println("**************************************************");
        
        System.out.println(game);
        /*
        for (Player player : game.getPlayers()) {
            System.out.println(((BlackjackPlayer)player).toString());
        }
        */
        System.out.println("**************************************************");
        System.out.println();
    }

    
    

}

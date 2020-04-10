package ca.sheridancollege.project.viewcontroller;

import ca.sheridancollege.project.model.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(" SET UP PLAYERS ");
        
        
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
        do{
            
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println(" NEW HAND BETTING DEALING ");

            //loop through all players //bet amount? //decrease balance //create empty hand
            for (Player p : blackjack.getPlayers()) {
                if(p instanceof BlackjackDealer){
                    //dealer doesn't have a real bet
                    BlackjackDealer dealer = (BlackjackDealer)p;
                    BlackjackHand newHand = new BlackjackHand(0.0);
                    dealer.addHand(newHand);
                }else{
                    //downcast player object
                    BlackjackPlayer player = (BlackjackPlayer)p;
                    //prompt player for bet amount:
                    String prompt = player.getPlayerID() +" ($"+ player.getBalance() +"), how much to bet on this hand: ";
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
                for (Player p : blackjack.getPlayers()) {
                    //downcast player
                    BlackjackPlayer player = (BlackjackPlayer)p;
                    //players only have one hand each, hit once
                    blackjack.hit(player.getHands().get(0));
                }
            }


            System.out.print("\n end of NEW HAND BETTING DEALING \n"+ blackjack +"\n");//testing




            // BEFORE FIRST TURN ///////////////////////////////////////////////////


            System.out.println("--------------------------------------------------------------------------------");
            System.out.println(" BEFORE FIRST TURN ");

            //check dealer hand for possible natural
            boolean insuranceRound = false;

            BlackjackDealer dealer = null;
            BlackjackCard visibleCard = null;
            try {
                dealer = blackjack.getDealer();
                visibleCard = (BlackjackCard)dealer.getHands().get(0).showCards().get(0);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                //if we don't have a dealer, end the game
                System.exit(0);
            }

            //check dealer's hand's first card (second card should be face down)
            if (visibleCard.getRank() == Rank.ACE) {
                //prompt players for insurance bets
                System.out.println("Dealer has an ACE, and might have a natural 21. Do you want to place an insurance bet?");
                for (Player p : blackjack.getPlayers()) {
                    if(p instanceof BlackjackDealer){
                        //dealer does nothing
                    }else{
                        //downcast player object
                        BlackjackPlayer player = (BlackjackPlayer)p;
                        BlackjackHand hand = player.getHands().get(0);
                        double bet = hand.getBet();
                        double halfBet = roundToCent(bet / 2);
                        //prompt player for bet amount:
                        String prompt = String.format("%s ($%.2f), place an insurance bet of $%.2f (y/n): ",
                                player.getPlayerID(), player.getBalance(), halfBet);
                        if(readUserBoolean(prompt)){
                            try {
                                //decrease balance:
                                double newBalance = player.getBalance() - halfBet;    
                                player.setBalance(newBalance);
                                //set insurance bet
                                hand.setInsuranceBet(halfBet);
                            } catch (Exception e) {
                                System.out.println(e.getMessage()); //gives error for negative balance, TODO change to specific message
                            }
                        }
                    }
                    //ask next player
                }
                insuranceRound = true;
            }//end of if


            //check daler hand for natural
            if (dealer.getHands().get(0).getValue() == 21) {
                //dealer natural -> move to settlement
                System.out.println(dealer.getPlayerID() +" has a natural 21, moving to settlement. ");
            }else if (insuranceRound) {
                System.out.println(dealer.getPlayerID() +" does not have a natural 21, player turns continue. ");
            }



            System.out.print("\n end of BEFORE FIRST TURN \n"+ blackjack +"\n");//testing

            // PLAYER TURNS ////////////////////////////////////////////////////////


            System.out.println("--------------------------------------------------------------------------------");
            System.out.println(" PLAYER TURNS");

            //loop through every player
            for (Player o : blackjack.getPlayers()) {
                if (o instanceof BlackjackDealer) {
                    //dealer plays
                    //downcast dealer
                    dealer = (BlackjackDealer)o;
                    BlackjackHand hand = dealer.getHands().get(0);

                    //flag to continue this hand
                    boolean continueHand = true;

                    while(continueHand){
                    //show dealer's hand
                    System.out.println(dealer.getPlayerID() + hand);
                    if (hand.getValue() > 21) {
                            //bust
                            System.out.println(dealer.getPlayerID() +" busts!");
                            continueHand = false;
                        }else if (hand.getValue() >= 17) {
                            //force stand
                            System.out.println(dealer.getPlayerID() +" stands.");
                            continueHand = false;
                        }else{
                            blackjack.hit(hand);
                            System.out.println(dealer.getPlayerID() +" hits!"); 
                        }
                    }

                }else{
                    //downcast to BlackjackPlayer
                    BlackjackPlayer player = (BlackjackPlayer)o;
                    //loop through every hand
                    for (BlackjackHand hand : player.getHands()) {

                        //show player's current hand
                        System.out.println(player.getPlayerID() +" ($"+ player.getBalance() +"): "+ hand);

                        //flag to continue this hand
                        boolean continueHand = true;

                        //prompt user for input
                        String prompt = player.getPlayerID() +", enter a command (stand, hit, double down, split pair): ";
                        while (continueHand) {

                            if (hand.getValue() > 21) {
                                //bust
                                System.out.println(player.getPlayerID() +" busts!");
                                continueHand = false;
                            }else if (hand.getValue() == 21) {
                                //force stand
                                System.out.println(player.getPlayerID() +" has 21 and stands.");
                                continueHand = false;
                            }else{
                                String userAction = readUserString(prompt).toLowerCase();
                                try {
                                    switch (userAction) {
                                        //TODO add messages
                                        case "stand": blackjack.stand(player, hand); 
                                            System.out.println(player.getPlayerID() +" stands."); 
                                            continueHand = false; 
                                            break;
                                        case "hit": blackjack.hit(hand); 
                                            System.out.println(player.getPlayerID() +" hits!"); 
                                            //show player's current hand
                                            System.out.println(player.getPlayerID() +" ($"+ player.getBalance() +"): "+ hand);
                                            break;
                                        case "double": blackjack.doubleDown(player, hand); 
                                            System.out.println(player.getPlayerID() +" doubles down!"); 
                                            //show player's current hand
                                            System.out.println(player.getPlayerID() +" ($"+ player.getBalance() +"): "+ hand);
                                            continueHand = false; 
                                            break;
                                        case "split": blackjack.splitPair(player, hand); 
                                            System.out.println(player.getPlayerID() +" splits their pair!"); 
                                            //show player's current hand
                                            System.out.println(player.getPlayerID() +" ($"+ player.getBalance() +"): "+ hand);
                                            break;
                                        default: System.out.println("Command not recognised. ");
                                    }
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }

                            }
                        }//end of while-loop - player has stood or busted
                        //next hand, or next player
                        System.out.println("");
                    }//end of hands loop
                }//end of if-dealer
            }//end of players loop


            System.out.print("\n end of PLAYER TURNS \n"+ blackjack +"\n");//testing



            // SETTLEMENT //////////////////////////////////////////////////////////

            System.out.println("--------------------------------------------------------------------------------");
            System.out.println(" SETTLEMENT ");     

    //        BlackjackDealer dealer = null;
    //        try {
    //            dealer = blackjack.getDealer();
    //        } catch (Exception ex) {
    //            System.out.println(ex.getMessage());
    //            //if we don't have a dealer, end the game
    //            System.exit(0);
    //        }
            BlackjackHand dealerHand = dealer.getHands().get(0);

            //loop through every player
            for (Player o : blackjack.getPlayers()) {
                if (o instanceof BlackjackDealer) {
                //do nothing for dealer
                }else{
                    //downcast to BlackjackPlayer
                    BlackjackPlayer player = (BlackjackPlayer)o;
                    //loop through every hand
                    for (BlackjackHand hand : player.getHands()) {

                        double winnings = 0;

                        if (dealerHand.getValue() == 21 && dealerHand.showCards().size() == 2) {
                            //dealer had natural 21 -> payout insurance bet
                            player.setBalance(player.getBalance() + hand.getInsuranceBet() + hand.getInsuranceBet() );
                            winnings += hand.getInsuranceBet();

                            if (hand.getValue() == 21) {
                                //player has natural too -> standoff, player breaks even
                                player.setBalance(player.getBalance() + hand.getBet() );
                                winnings += 0;
                            }else{
                                //else -> no payout
                                winnings -= hand.getBet();
                            }

                        }else if (hand.getValue() == 21 && hand.showCards().size() == 2) {
                            //player has natural 21, dealer does not -> payout 1.5x bet
                            player.setBalance(player.getBalance() + hand.getBet() + (hand.getBet() * 1.5) );
                            winnings += hand.getBet() * 1.5;
                        }else if(dealerHand.getValue() <= 21){
                            //dealer stood
                            if (hand.getValue() <= 21 && hand.getValue() > dealerHand.getValue()) {
                                //dealer total < player total, neither busted -> payout bet
                                player.setBalance(player.getBalance() + hand.getBet() + hand.getBet() );
                                winnings += hand.getBet();
                            }else if(hand.getValue() == dealerHand.getValue()){
                                //dealer total == player total, neither busted, player breaks even
                                player.setBalance(player.getBalance() + hand.getBet() );
                                winnings += 0;
                            }
                        }else if(dealerHand.getValue() > 21){
                            if (hand.getValue() <= 21) {
                                //dealer busted, player did not -> payout bet
                                player.setBalance(player.getBalance() + hand.getBet() + hand.getBet() );
                                winnings += hand.getBet();
                            }
                        }else{
                            //else, player busted -> no payout
                            winnings -= hand.getBet();
                        }


                        System.out.printf("%s wins $%.2f \n",player.getPlayerID(),winnings);


                    }//end of hands loop
                }//end of if-dealer
            }//end of players loop


        }while(readUserBoolean("Play again (y/n)?"));        
        
        
    }//end of main()
    
    
    
    
    
    /**
     * prints a formatted table of all players' hands, with an indicator of the 
     * hand being played currently
     */
    private static void printCurrentTable() {
/*
jack ($90.00):
	      20  [K♣][9♠]            $10.00 
	      20  [K♦][3♠][6♠]        $10.00 
----------------------------------------
jill ($45.00):
	      14  [8♦][6♠]            $55.00 
----------------------------------------
jason ($80.00):
          17  [7♥][J♣]            $20.00 
[playing] 17  [7♥][J♣]            $20.00 
	      17  [7♥][J♣]            $20.00 
----------------------------------------
the dealer:
	      18  [A♠][7♣]
----------------------------------------
*/


    }

    
    
    
    
    
    /**
     * Removes fractions of a cent in given value. 
     * @param value the value to be rounded down to 1/100 place
     * @return given value truncated to two decimal places
     */
    private static double roundToCent(double value) {
        return (int)(value * 100) / 100.0;
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
    
    
    private static Boolean readUserBoolean(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        try {
            char c = input.next().trim().toLowerCase().charAt(0);
            boolean b;
            switch (c) {
                //all possible "yes" responses
                case 'y': b = true; break;
                case '1': b = true; break;
                //otherwise assume "no" response
                default:  b = false;
            }
            return b;
        } catch (Exception e) {
            System.out.println("ERROR reading input");
            System.out.println(e.getMessage());
            return readUserBoolean(prompt);
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

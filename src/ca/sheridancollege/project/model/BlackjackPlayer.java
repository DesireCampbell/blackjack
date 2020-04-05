package ca.sheridancollege.project.model;

import java.util.ArrayList;

public class BlackjackPlayer extends Player{

	private ArrayList<BlackjackHand> hands;
    private int currentHandIndex = 0;
	private double balance = 0.0;

    /**
     * Constructor for Blackjack Player
     * @param name 
     */
    public BlackjackPlayer(String name) {
        super(name);
        //new player alwasy start with default balance
        this.balance = 100.0;
        //initialize hands
        hands = new ArrayList<BlackjackHand>();
        
    }
    
    
    
    /**
     * Get all player hands
     * @return 
     */
	public ArrayList<BlackjackHand> getHands() {
		return this.hands;
	}

    /**
     * Get the hand currently in play
     * @return 
     */
	public BlackjackHand getCurrentHand() {
		return this.hands.get(currentHandIndex);
	}

    /**
     * get a specific player hand
     * @return 
     */
	public BlackjackHand getHandAtIndex(int index) {
		return this.hands.get(index);
    }
    
    /**
     * Add a new hand to the player
     * @param hand 
     */
    public void addHand(BlackjackHand hand) {
        if (hand == null) {
            throw new IllegalArgumentException("ERROR adding new hand to player, hand is null");
        }
        this.getHands().add(hand);
    }

    
    
    public int getCurrentHandIndex() {
        return currentHandIndex;
    }

    public void setCurrentHandIndex(int newHandIndex) {
        if (newHandIndex < 0 || newHandIndex >= this.hands.size()) {
            throw new IndexOutOfBoundsException("ERROR: Requested hand index "
                    + newHandIndex +" does not exist");
        }else{
            this.currentHandIndex = newHandIndex;    
        }
    }
    
    public void setToNextHandIndex() {
        setCurrentHandIndex(currentHandIndex + 1);
    }
    
    
    /**
     * obtains a new card
     */
    public void getCard() {
        //find dealer object
        //call dealer.dealCard()
    }



    /**
     * 
     * @return 
     */
	public double getBalance() {
        return this.balance;
	}

	/**
	 * 
	 * @param balance
	 */
	public void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("ERROR setting new balance for "
                    + "player "+ this.getPlayerID() +": balance cannot be "
                            + "negative" );
        }
        //round DOWN to nearest cent
        balance = (int)(balance * 100)/100.0;
		this.balance = balance;
	}

    @Override
    public void play() {
        
    }
    
    
    
    /**
     * The four options for a player's turn HIT, DOUBLE, SPLIT, STAND
     */
    public void hit() {
        
    }
    
    public void doubleDown() {
        
    }
    
    public void splitPair() {
        
    }
    
    public void stand() {
        
    }
    

    @Override
    public String toString() {
        //A player is made from a Name, a balance, and his hands:
        String s = this.getPlayerID() +" ("+ this.getBalance() +"): ";
        for (BlackjackHand hand : hands) {
            s += hand +"\n";
        }
        return s;
    }
    
    

}
package ca.sheridancollege.project.model;

public class BlackjackPlayer extends Player{

	private BlackjackHand hand;
	private double balance;

    /**
     * Constructor for Blackjack Player
     * @param name 
     */
    public BlackjackPlayer(String name) {
        super(name);
        //new player alwasy start with default balance
        balance = 100.0;
        
    }

    /**
     * 
     * @return 
     */
	public BlackjackHand getHand() {
		return this.hand;
	}

	/**
	 * 
	 * @param hand
	 */
	public void setHand(BlackjackHand hand) {
		this.hand = hand;
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
		this.balance = balance;
	}

    @Override
    public void play() {
        
    }

}
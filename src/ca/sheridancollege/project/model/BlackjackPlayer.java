package ca.sheridancollege.project.model;

public class BlackjackPlayer {

	private GroupOfCards hand;
	private double balance;

    public BlackjackPlayer() {
        super();
    }

    /**
     * 
     * @return 
     */
	public GroupOfCards getHand() {
		return this.hand;
	}

	/**
	 * 
	 * @param hand
	 */
	public void setHand(GroupOfCards hand) {
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

}
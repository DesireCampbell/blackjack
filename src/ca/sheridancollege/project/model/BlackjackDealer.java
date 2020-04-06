package ca.sheridancollege.project.model;

public class BlackjackDealer extends BlackjackPlayer {

	private BlackjackPack pack;
    
    /**
     * 
     */
    public BlackjackDealer() {
        //always the same name
        super("the dealer");
        //generate the pack, six decks
        this.setPack(new BlackjackPack(6));
        
    }
    
	public BlackjackPack getPack() {
		return this.pack;
	}

	/**
	 * 
     * @param pack
	 */
	public void setPack(BlackjackPack pack) {
		this.pack = pack;
	}
    
    public BlackjackCard dealCard() {
        //remove the first card from the pack and return it.
        return (BlackjackCard)(this.pack.showCards().remove(0));
    }
    
    @Override
    public void play() {
        //check hand value
        //hit until hand value > 16
    }

    @Override
    public String toString() {
        //A Dealer is made from a Name, his hand:
        String s = this.getPlayerID() +": "+ this.getCurrentHand();
        return s;
    }
     

}
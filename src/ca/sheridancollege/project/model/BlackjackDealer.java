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

}

package ca.sheridancollege.project;

public enum Suit {
	SPADES  ("Spades",  '♠'),
    HEARTS  ("Hearts",  '♥'),
	DIAMONDS("Diamonds",'♦'),
	CLUBS   ("Clubs",   '♣');
    
    private String name;
    private char symbol;

    /**
     * 
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return 
     */
    public char getSymbol() {
        return symbol;
    }



    /**
     * 
     * @param name
     * @param symbol 
     */
    private Suit(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return this.name;
    }

    
    
    
}
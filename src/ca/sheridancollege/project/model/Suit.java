/*
    Suit.java
    Author: Desire Richards-Campbell (991571959)
    Author: Swaranjit Singh (991577993)

    Description
    Selection of possible categories for BlackjackCards
*/

package ca.sheridancollege.project.model;

/**
 * Selection of possible categories for BlackjackCards
 * @author Desire Richards-Campbell (991571959)
 * @author Swaranjit Singh (991577993)
 */

public enum Suit {
	SPADES  ("Spades",   '♠'),
    HEARTS  ("Hearts",   '♥'),
	DIAMONDS("Diamonds", '♦'),
	CLUBS   ("Clubs",    '♣');
    
    private String name;
    private char symbol;

    /**
     * Accessor for the name property.
     * @return The friendly name of the suit, as String.
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor for the symbol property.
     * @return The unicode symbol for the suit, as character primitive.
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Constructor for enum.
     * @param name 
     * @param symbol 
     */
    private Suit(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    /**
     * Default String representation of Suit enum.
     * @return The suit's friendly name, as String.
     */
    @Override
    public String toString() {
        return this.name;
    }
}
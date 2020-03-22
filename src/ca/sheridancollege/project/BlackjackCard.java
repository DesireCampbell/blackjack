package ca.sheridancollege.project;

public class BlackjackCard extends Card {

    private Rank rank;
    private Suit suit;

    /**
     *
     * @return
     */
    public Rank getRank() {
        return this.rank;
    }

    /**
     *
     * @param rank
     */
    public void setRank(Rank rank) {
        this.rank = rank;
    }

    /**
     *
     * @return
     */
    public Suit getSuit() {
        return this.suit;
    }

    /**
     *
     * @param suit
     */
    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return this.rank + " of " + this.suit;
    }

    /**
     * get a single unicode character for the card
     *
     * @return
     */
    public char getUnicodeCardFace() {
        // return char in format: ((char)'\u1F0A0')
        // u1F0 + suit + rank
        /**
         * 
         * blank card   🂠
         * spades       🂡	🂢	🂣	🂤	🂥	🂦	🂧	🂨	🂩	🂪	🂫	🂬	🂭
         * hearts       🂱	🂲	🂳	🂴	🂵	🂶	🂷	🂸	🂹	🂺	🂻	🂼	🂽
         * diamonds     🃁	🃂	🃃	🃄	🃅	🃆	🃇	🃈	🃉	🃊	🃋	🃌	🃍
         * clubs        🃑	🃒	🃓	🃔	🃕	🃖	🃗	🃘	🃙	🃚	🃛	🃜	🃝
         * 
         * u1F0     card
         *     A    SPACES
         *     B    HEARTS
         *     C    DIAMONDS
         *     D    CLUBS
         *      1   ACE
         *      2   TWO
         *      3   THREE
         *      4   FOUR
         *      5   FIVE
         *      6   SIX
         *      7   SEVEN
         *      8   EIGHT
         *      9   NINE
         *      A   TEN
         *      B   JACK
         *      C   QUEEN
         *      D   KING
         *     A0   Back of card
         */
        
        return this.rank.getSymbol() + " of " + this.suit.getSymbol();

    }

    /**
     *
     * @return
     */
    public char getUnicodeCardBack() {
        return ((char) '\uF0A0');
        //return 🂠;

    }

}

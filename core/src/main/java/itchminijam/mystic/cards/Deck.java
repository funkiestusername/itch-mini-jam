package itchminijam.mystic.cards;

import java.util.Stack;

public class Deck {
    private Stack<Card> cards; // last in first out (subject to change based on final gameplay design)
    private int genericCount, arcanaCount; // keep track of card types

    public Deck(int genericCount, int arcanaCount) {
        this.genericCount = genericCount;
        this.arcanaCount = arcanaCount;

        initDeck();
    }

    private void initDeck() {
        // initialise the deck with random starting cards based on starting counts
    }

    public void addCard(Card card) {
        // add a new card to the top of the stack
    }

    public void drawCard() {
        // take top card
    }

    // can add more deck manipulation methods as and when they are needed.

    public void shuffle() {
        // shuffle the deck
    }
}

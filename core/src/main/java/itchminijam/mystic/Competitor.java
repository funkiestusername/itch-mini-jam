package itchminijam.mystic;

import itchminijam.mystic.cards.Deck;

public class Competitor {
    protected int health;
    protected Deck deck;
    protected int maxCardsNextTurn; // max amount of cards they can play on their next turn
    protected int maxCards; // max amount allowed to play on the current turn
    protected int cardsToDrawNextTurn; // amount of cards competitor draws on their next turn;


    public void damage(int amount) {
        // to heal, give a negative value
        health -= amount;
    }

    public int getMaxCardsNextTurn() {
        return maxCardsNextTurn;
    }

    public int getHealth() {
        return health;
    }

    public Deck getDeck() {
        return deck;
    }
}

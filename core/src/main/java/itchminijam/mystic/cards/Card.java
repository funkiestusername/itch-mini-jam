package itchminijam.mystic.cards;

import itchminijam.mystic.Competitor;

public interface Card {
    // to implement new card, create file in cards folder and extend either GenericCard or ArcanaCard
    // override this method to put logic for that new card.
    void playCard(Competitor opponent, Competitor self);
}

package itchminijam.mystic.screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import itchminijam.mystic.Computer;
import itchminijam.mystic.MysticTarotBattler;
import itchminijam.mystic.Player;
import itchminijam.mystic.framework.AbstractScreen;

public class GameScreen extends AbstractScreen<MysticTarotBattler> {
    public GameScreen(MysticTarotBattler game) {
        super(game);
    }

    private Player player;
    private Computer computer;

    //TODO: decide starting state and discuss progression/loss consequences
    //      Open game, start a level 1, each win increases level, when out of lives level resets and keep a card.
    //SUGGESTION: ignore the limitation announced? or maybe try justify project based on what the limitation is without
    //            without actually changing anything?

    private int level;

    @Override
    protected void initialise() {
        level = 1;

        player = new Player();
        computer = new Computer();

        shuffleDecks();
    }

    private void showStartOfLevel() {

    }

    private void pickCardToKeep() {
        // when lost all lives, choose an arcana card to keep
    }

    private void loadAssets() {
        AssetManager assets = MysticTarotBattler.getAssets();

        assets.load("cards/the-fool.jpg", Texture.class);
    }

    @Override
    protected void update(float dt) {
        if (player.getHealth() <= 0) {
            // player lost the battle
        } else if (computer.getHealth() <= 0) {
            // player won the battle
        }
    }

    private void shuffleDecks() {
        // shuffle player 1 and computer decks
    }
}

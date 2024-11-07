package itchminijam.mystic;

import itchminijam.mystic.framework.AbstractGame;
import itchminijam.mystic.screens.GameScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MysticTarotBattler extends AbstractGame {
    @Override
    protected void createScreens() {
        addScreen("game", new GameScreen(this));
    }

    @Override
    protected String getInitialScreenName() {
        return "game";
    }
}

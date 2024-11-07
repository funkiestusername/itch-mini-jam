package itchminijam.mystic.screens;

import com.badlogic.gdx.graphics.Texture;
import itchminijam.mystic.MysticTarotBattler;
import itchminijam.mystic.framework.AbstractScreen;
import itchminijam.mystic.framework.scene2d.GameObject;

public class GameScreen extends AbstractScreen<MysticTarotBattler> {
    public GameScreen(MysticTarotBattler game) {
        super(game);
    }

    private GameObject gdxLogo;

    @Override
    protected void initialise() {
        gdxLogo = new GameObject();
        gdxLogo.setTexture(new Texture("libgdx.png"));
        gdxLogo.centreOnScreem();
        mainStage.addActor(gdxLogo);
    }

    @Override
    protected void update(float dt) {

    }
}

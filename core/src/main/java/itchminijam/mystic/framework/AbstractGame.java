package itchminijam.mystic.framework;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import itchminijam.mystic.framework.scene2d.actors.ScreenFaderActor;

import java.util.HashMap;

public abstract class AbstractGame implements ApplicationListener {
    private static AbstractGame instance;

    private AssetManager assets;
    private InputMultiplexer inputMultiplexer;

    private HashMap<String, AbstractScreen> screens;
    private AbstractScreen currentScreen;
    private boolean isSwitchingScreens;

    private Stage screenFxStage;
    private ScreenFaderActor screenFader;

    public AbstractGame() {
        assets = new AssetManager();
        inputMultiplexer = new InputMultiplexer();
        screens = new HashMap<>();
        instance = this;
        isSwitchingScreens = false;
    }

    protected abstract void createScreens();
    protected abstract String getInitialScreenName();

    @Override
    public void create() {
        screenFxStage = new Stage();
        screenFader = new ScreenFaderActor(Color.BLACK);
        screenFxStage.addActor(screenFader);

        Gdx.input.setInputProcessor(inputMultiplexer);

        createScreens();

        setCurrentScreen(getInitialScreenName());
    }

    @Override
    public void resize(int width, int height) {
        if (currentScreen != null) {
            currentScreen.resize(width, height);
        }
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();

        screenFxStage.act(dt);

        if (currentScreen != null) {
            currentScreen.update(dt);
            currentScreen.render(dt);
        }

        screenFxStage.draw();
    }

    @Override
    public void pause() {
        if (currentScreen != null) {
            currentScreen.pause();
        }
    }

    @Override
    public void resume() {
        if (currentScreen != null) {
            currentScreen.resume();
        }
    }

    @Override
    public void dispose() {
        if (currentScreen != null) {
            currentScreen.dispose();
        }
    }

    public void fadeToScreen(String screenName) {
        // fade to black, then switch current screen, then fade back in
        if (!isSwitchingScreens) {
            isSwitchingScreens = true;
            screenFader.fadeOut(1);
            currentScreen.hide();
            screenFader.addAction(Actions.after(Actions.sequence(
                Actions.run(() -> {
                    isSwitchingScreens = false;
                    screenFader.fadeIn(1);
                    setCurrentScreen(screenName);
                })
            )));
        }
    }

    public AbstractScreen addScreen(String screenName, AbstractScreen screen) {
        screens.put(screenName, screen);
        return screen;
    }

    public void setCurrentScreen(String screenName) {
        // instantly sets the current screen
        if (currentScreen != null) {
            currentScreen.hide();
        }
        currentScreen = screens.get(screenName);
        if (currentScreen != null) {
            currentScreen.show();
        }
    }

    public AbstractScreen getScreen(String screenName) {
        return screens.get(screenName);
    }

    public static AssetManager getAssets() {
        return instance.assets;
    }

    public InputMultiplexer getInputMultiplexer() {
        return inputMultiplexer;
    }

    public HashMap<String, AbstractScreen> getScreens() {
        return screens;
    }

    public AbstractScreen getCurrentScreen() {
        return currentScreen;
    }

    public boolean isSwitchingScreens() {
        return isSwitchingScreens;
    }
}

package itchminijam.mystic.framework;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import itchminijam.mystic.framework.scene2d.actors.ScreenFaderActor;

public abstract class AbstractScreen<T extends AbstractGame> implements Screen, InputProcessor {
    private T game;

    protected ScreenFaderActor screenFader;

    protected Stage mainStage;
    protected Stage uiStage;
    protected Stage screenFxStage;

    protected World box2dWorld;

    private Color clearColour;
    private boolean initialised;

    public AbstractScreen(T game) {
        this.game = game;

        mainStage = new Stage();
        uiStage = new Stage();
        screenFxStage = new Stage();

        screenFader = new ScreenFaderActor(Color.BLACK);
        screenFxStage.addActor(screenFader);

        box2dWorld = new World(new Vector2(), false);

        clearColour = new Color(0, 0, 0, 1);

        initialised = false;
    }

    // called once when screen is first shown
    protected abstract void initialise();

    // called once every frame
    protected abstract void update(float dt);

    @Override
    public void show() {
        game.getInputMultiplexer().addProcessor(this);
        game.getInputMultiplexer().addProcessor(uiStage);
        game.getInputMultiplexer().addProcessor(mainStage);

        if (!initialised) {
            initialise();
            initialised = true;
        }
    }

    @Override
    public void hide() {
        game.getInputMultiplexer().removeProcessor(this);
        game.getInputMultiplexer().removeProcessor(uiStage);
        game.getInputMultiplexer().removeProcessor(mainStage);
    }

    // various custom render methods in case manual rendering is needed
    protected void preStageRender() {}
    protected void postStageRender() {}

    @Override
    public void render(float dt) {
        uiStage.act(dt);
        mainStage.act(dt);
        screenFxStage.act(dt);

        update(dt);
        box2dWorld.step(dt, 6, 3);

        Gdx.gl.glClearColor(clearColour.r, clearColour.g, clearColour.b, clearColour.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        preStageRender();

        mainStage.draw();
        uiStage.draw();
        screenFxStage.draw();

        postStageRender();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        mainStage.dispose();
        uiStage.dispose();
        screenFxStage.dispose();
        box2dWorld.dispose();
    }

    public Color getClearColour() {
        return clearColour;
    }

    public void setClearColour(Color clearColour) {
        this.clearColour = clearColour;
    }

    public T getGame() {
        return game;
    }

    public ScreenFaderActor getScreenFader() {
        return screenFader;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public Stage getUiStage() {
        return uiStage;
    }

    public Stage getScreenFxStage() {
        return screenFxStage;
    }

    public World getBox2dWorld() {
        return box2dWorld;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}

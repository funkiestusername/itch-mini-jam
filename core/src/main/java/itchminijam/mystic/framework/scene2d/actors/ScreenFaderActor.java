package itchminijam.mystic.framework.scene2d.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class ScreenFaderActor extends Actor {
    private ShapeRenderer shapes;

    public ScreenFaderActor(Color color) {
        this.shapes = new ShapeRenderer();
        shapes.setAutoShapeType(true);
        setColor(color.r, color.g, color.b, 0);
        setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void fadeIn(float duration) {
        clearActions();
        addAction(Actions.sequence(
            Actions.alpha(1f, 0f),
            Actions.fadeOut(duration)
        ));
    }

    public void fadeOut(float duration) {
        clearActions();
        addAction(Actions.sequence(
            Actions.alpha(0f, 0f),
            Actions.fadeIn(duration)
        ));
    }

    public void fadeOutIn(float outDuration, float delay, float inDuration) {
        clearActions();
        addAction(Actions.sequence(
            Actions.alpha(0f, 0f),
            Actions.fadeIn(outDuration),
            Actions.delay(delay),
            Actions.alpha(1f, 0f),
            Actions.fadeOut(inDuration)
        ));
    }

    @Override
    public boolean remove() {
        shapes.dispose();
        return super.remove();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.setColor(getColor());
        shapes.rect(getX(), getY(), getWidth(), getHeight());
        shapes.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }
}

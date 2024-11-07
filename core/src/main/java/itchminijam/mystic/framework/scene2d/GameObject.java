package itchminijam.mystic.framework.scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

public class GameObject extends Group {
    private final TextureRegion texture;
    private final Vector2 centre;

    public GameObject() {
        texture = new TextureRegion();
        centre = new Vector2();
    }

    public GameObject(float x, float y) {
        this();
        setPosition(x, y);
    }

    public void setTexture(Texture texture) {
        this.texture.setRegion(texture);
        setSize(texture.getWidth(), texture.getHeight());
    }

    public void setTextureRegion(TextureRegion region) {
        this.texture.setRegion(region);
        setSize(texture.getRegionWidth(), texture.getRegionHeight());
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public Vector2 getCentre() {
        // returns the centre of this Actor
        centre.set(getX() + getWidth() / 2, getY() + getHeight() / 2);
        return centre;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture,
            getX(), getY(),
            getOriginX(), getOriginY(),
            getWidth(), getHeight(),
            getScaleX(), getScaleY(),
            getRotation());
    }

    public void centreOnScreem() {
        float x = Gdx.graphics.getWidth() / 2 - getTexture().getRegionWidth() / 2;
        float y = Gdx.graphics.getHeight() / 2 - getTexture().getRegionHeight() / 2;
        setPosition(x, y);
    }
}

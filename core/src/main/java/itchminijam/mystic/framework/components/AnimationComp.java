package itchminijam.mystic.framework.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import itchminijam.mystic.framework.AbstractGame;

import java.util.HashMap;

public class AnimationComp<T> {
    private final HashMap<String, Animation<T>> animations = new HashMap<>();
    private Animation<T> currentAnimation = null;
    private float elapsedTime = 0.0f;
    private boolean paused = false;

    public void update(float dt) {
        if (currentAnimation != null && !paused) {
            elapsedTime += dt;
        }
    }

    public Animation<T> get(String name) {
        return animations.get(name);
    }

    public void put(String name, Animation<T> animation) {
        animations.put(name, animation);
    }


    public static Animation<TextureRegion> createTextureAnimationFromFiles(String[] fileNames, float frameDuration, boolean loop) {
        // returns an Animation using the TextureRegion objects loaded from the given file names
        Array<TextureRegion> keyFrames = new Array<>();

        for (String fileName : fileNames) {
            keyFrames.add(new TextureRegion(AbstractGame.getAssets().get(fileName, Texture.class)));
        }

        Animation<TextureRegion> toReturn = new Animation<>(frameDuration, keyFrames);

        if (loop)
            toReturn.setPlayMode(Animation.PlayMode.LOOP);
        else
            toReturn.setPlayMode(Animation.PlayMode.NORMAL);

        return toReturn;
    }

    public static Animation<TextureRegion> createTextureAnimationFromSheet(String fileName, int rows, int cols, float frameDuration, boolean loop) {
        // returns an Animation using the TextureRegion objects loaded from the sheet
        // the algorithm simply divides the sheet into equal segments based on the given rows and cols
        Texture sheetTexture = AbstractGame.getAssets().get(fileName, Texture.class);
        int frameWidth = sheetTexture.getWidth() / cols;
        int frameHeight = sheetTexture.getHeight() / rows;

        TextureRegion[][] textureRegions = TextureRegion.split(sheetTexture, frameWidth, frameHeight);

        Array<TextureRegion> keyFrames = new Array<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                keyFrames.add(textureRegions[row][col]);
            }
        }

        Animation<TextureRegion> toReturn = new Animation<>(frameDuration, keyFrames);

        if (loop)
            toReturn.setPlayMode(Animation.PlayMode.LOOP);
        else
            toReturn.setPlayMode(Animation.PlayMode.NORMAL);

        return toReturn;
    }


    public void setCurrentAnimation(String animationName) {
        this.currentAnimation = animations.get(animationName);
        elapsedTime = 0.0f; // reset the elapsedTime so animation starts on frame 0
    }

    public T getCurrentKeyFrame() {
        if (currentAnimation != null) {
            return currentAnimation.getKeyFrame(elapsedTime);
        }
        return null;
    }

    public Animation<T> getCurrentAnimation() {
        return currentAnimation;
    }

    public float getElapsedTime() {
        return elapsedTime;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isPaused() {
        return paused;
    }
}

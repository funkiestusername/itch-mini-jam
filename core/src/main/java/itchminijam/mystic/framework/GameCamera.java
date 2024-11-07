package itchminijam.mystic.framework;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameCamera extends OrthographicCamera {
    private Rectangle worldBounds;

    public GameCamera(float viewportWidth, float viewportHeight) {
        super(viewportWidth, viewportHeight);
    }

    @Override
    public void update() {
        if (worldBounds != null) {
            position.x = MathUtils.clamp(position.x, viewportWidth / 2, worldBounds.width - viewportWidth / 2);
            position.y = MathUtils.clamp(position.y, viewportHeight / 2, worldBounds.height - viewportHeight / 2);
        }

        super.update();
    }

    public void setWorldBounds(float width, float height) {
        // worldBounds prevents the camera from viewing pixels outwith the given pixel width and height
        worldBounds = new Rectangle(0, 0, width, height);
    }

    public void snapToTarget(Vector2 target) {
        // centers the camera at the given target Vector2
        position.set(target, 0);
        update();
    }

    public void interpolateToTarget(Vector2 target) {
        // interpolate between current position and target position to calculate distance to move
        // a += (b - a) * smoothing
        if (position.x > target.x - 0.5f && position.x < target.x + 0.5f && position.y > target.y - 0.5f && position.y < target.y + 0.5f) {
            position.x = target.x;
            position.y = target.y;
        } else {
            // still got some distance to travel
            position.x += (target.x - position.x) * 0.1f;
            position.y += (target.y - position.y) * 0.1f;
        }
        update();
    }

}

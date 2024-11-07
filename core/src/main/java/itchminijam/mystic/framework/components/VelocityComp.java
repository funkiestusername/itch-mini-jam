package itchminijam.mystic.framework.components;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class VelocityComp {
    private final Vector2 velocity = new Vector2();
    private final Vector2 acceleration = new Vector2();

    private float accelerationScalar;
    private float maxSpeed;
    private float frictionScalar; // friction is assumed to be positive

    public VelocityComp(float accelerationScalar, float maxSpeed, float frictionScalar) {
        this.accelerationScalar = accelerationScalar;
        this.maxSpeed = maxSpeed;
        this.frictionScalar = frictionScalar;
    }

    public void accelerateAtAngle(float degrees) {
        // angles the acceleration vector and scales it by accelerationScalar
        acceleration.add(new Vector2(accelerationScalar, 0).setAngleDeg(degrees));
    }

    public void applyToActor(Actor actor, float dt) {
        // move the transform's position based on the velocity vector

        // apply the acceleration
        velocity.add(acceleration.scl(dt));

        float speed = getSpeed();

        // apply friction when not accelerating
        if (acceleration.len() == 0) {
            speed -= frictionScalar * dt;
        }

        // clamp the speed to within maxSpeed
        speed = MathUtils.clamp(speed, 0, maxSpeed);

        // update velocity
        setSpeed(speed);

        // update the actor's position
        actor.moveBy(velocity.x * dt, velocity.y * dt);

        // reset the acceleration
        acceleration.set(0, 0);
    }

    public float getAccelerationScalar() {
        return accelerationScalar;
    }

    public float getSpeed() {
        return velocity.len();
    }

    public void setSpeed(float speed) {
        // sets the magnitude of the velocity to the given speed
        if (velocity.len() == 0) {
            velocity.set(speed, 0);
        } else {
            velocity.setLength(speed);
        }
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public float getFrictionScalar() {
        return frictionScalar;
    }
}

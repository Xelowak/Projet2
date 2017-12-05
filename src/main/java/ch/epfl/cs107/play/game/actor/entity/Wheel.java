package ch.epfl.cs107.play.game.actor.entity;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.*;
import ch.epfl.cs107.play.window.Canvas;

public class Wheel extends GameEntity {

    // Attributes
    protected final float MAX_WHEEL_SPEED = 20.f;
    // private ShapeGraphics wheelGraphics;
    private ImageGraphics wheelGraphics;
    private WheelConstraint constraint;

    // Constructor
    public Wheel(ActorGame actorGame, boolean fixed, Vector position) {
        super(actorGame, fixed, position);
        Circle temp = new Circle(0.5f);
        buildShape(temp, 1, false, 0);
        // wheelGraphics = createShapeGraphics(temp, null, Color.BLACK, .2f, 1.f, 0);
        wheelGraphics = createImageGraphics("wheel.png", 2.0f*0.5f, 2.0f*0.5f, new Vector(0.5f, 0.5f));
    }

    public void attach(Entity vehicle, Vector anchor, Vector axis) {
        WheelConstraintBuilder constraintBuilder = getOwner().getWheelConstraintBuilder();
        constraintBuilder.setFirstEntity(vehicle);
        // point d'ancrage du vehicule :
        constraintBuilder.setFirstAnchor(anchor);
        // Entity associee à la roue :
        constraintBuilder.setSecondEntity(this.getEntity());
        // point d'ancrage de la roue (son centre) :
        constraintBuilder.setSecondAnchor(Vector.ZERO);
        // axe le long duquel la roue peut se deplacer :
        constraintBuilder.setAxis(axis);
        // frequence du ressort associe
        constraintBuilder.setFrequency(3.0f);
        constraintBuilder.setDamping(0.5f);
        // force angulaire maximale pouvant etre appliquee à la roue pour la faire tourner :
        constraintBuilder.setMotorMaxTorque(10.0f);
        constraint = constraintBuilder.build();
    }

    // Switch on the wheel's motor
    public void power(float speed) {
        constraint.setMotorEnabled(true);
        constraint.setMotorSpeed(speed);
    }

    // Switch off the wheel's motor
    public void relax() {
        constraint.setMotorEnabled(false);
    }

    // Destroy the constraint between the vehicle and the wheel
    public void detach() {
        constraint.destroy();
    }

    // Returns relative rotation speed, in radians per second
    public float getSpeed() {
        return constraint.getSecondBody().getAngularVelocity();
    }

    @Override
    public void draw(Canvas canvas) {
        wheelGraphics.draw(canvas);
    }
}

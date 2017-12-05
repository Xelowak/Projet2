package ch.epfl.cs107.play.game.actor.entity;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.math.*;
import ch.epfl.cs107.play.math.Shape;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.*;

public class Stickman extends GameEntity {

    private Circle head;
    private ShapeGraphics headGraphics;
    private Polyline arms;
    private ShapeGraphics armsGraphics;
    private PrismaticConstraint prismaticConstraint;
    private boolean right;

    public Stickman(ActorGame actorGame, boolean fixed, Vector position, boolean right) {
        super(actorGame, fixed, position);
        if (right) {
            head = new Circle(0.2f, getHeadLocation());
            arms = new Polyline(getShoulderLocation(), getHandLocation());
        } else {
            head = new Circle(0.2f, getHeadLocation().oppositeX());
            arms = new Polyline(getShoulderLocation().oppositeX(), getHandLocation().oppositeX());
        }
        headGraphics = createShapeGraphics(head);
        armsGraphics = createShapeGraphics(arms);

        this.right = right;

        Circle fixture = new Circle(.1f);
        this.buildShape(fixture, -1, true, 0);
    }

    public void attach(Entity vehicle, Vector anchor, Vector axis) {
        PrismaticConstraintBuilder constraintBuilder = getOwner().getPrismaticConstraintBuilder();
        constraintBuilder.setFirstEntity(vehicle);
        constraintBuilder.setFirstAnchor(anchor);
        constraintBuilder.setSecondEntity(this.getEntity());
        constraintBuilder.setSecondAnchor(Vector.ZERO);
        constraintBuilder.setLimitEnabled(true);
        constraintBuilder.setMotorEnabled(true);
        constraintBuilder.setInternalCollision(false);
        constraintBuilder.setUpperTranslationLimit(0.f);
        constraintBuilder.setLowerTranslationLimit(0.f);
        constraintBuilder.setAxis(axis);
        prismaticConstraint = constraintBuilder.build();
    }

    public Vector getHeadLocation() {
        return new Vector(0.f, 1.5f);
    }

    public Vector getShoulderLocation() {
        return new Vector(-0.1f, 1.05f);
    }

    public Vector getHandLocation() {
        return new Vector(0.5f, 0.75f);
    }

    private ShapeGraphics createShapeGraphics(Shape shape) {
        return super.createShapeGraphics(shape, Color.BLACK, Color.BLACK, 0.1f, 1.f, 0.f);
    }

    public void setPolylineArms(Polyline arms) {
        this.arms = arms;
    }

    public void switchDir() {
        right = !right;
    }

    @Override
    public void update(float deltaTime) {
        if (right) {
        head = new Circle(0.2f, getHeadLocation());
        arms = new Polyline(getShoulderLocation(), getHandLocation());
    } else {
        head = new Circle(0.2f, getHeadLocation().oppositeX());
        arms = new Polyline(getShoulderLocation().oppositeX(), getHandLocation().oppositeX());
    }
        headGraphics = createShapeGraphics(head);
        armsGraphics = createShapeGraphics(arms);
    }

    @Override
    public void draw(Canvas canvas) {
        headGraphics.draw(canvas);
        armsGraphics.draw(canvas);
    }
}


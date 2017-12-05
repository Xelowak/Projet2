package ch.epfl.cs107.play.game.actor.entity;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.event.KeyEvent;

public class Bike extends GameEntity {

    // Attributes
    private Wheel leftWheel;
    private Wheel rightWheel;
    private boolean right = true;
    private Stickman stickman;

    // Polygon of the bike
    private Polygon polygon = new Polygon(
            0.0f, 0.5f,
            0.5f, 1.f,
            0.f, 2.f,
            -0.5f, 1.f
    );

    ActorGame game;

    // Constructor
    public Bike(ActorGame actorGame, boolean fixed, Vector position) {
        super(actorGame, fixed, position);
        this.game = actorGame;
        buildShape(polygon, true);
        rightWheel = new Wheel(this.getOwner(), false, new Vector(position.getX() + 1.f, position.getY()));
        leftWheel = new Wheel(this.getOwner(), false, new Vector(position.getX() - 1.f, position.getY()));
        attachWheels();
        stickman = new Stickman(this.getOwner(), false, this.getPosition(), right);
        attachStickman();
    }

    // Method to attach both Wheels to the bike
    private void attachWheels() {
        leftWheel.attach(this.getEntity(), new Vector(-1.f, 0.f), new Vector(-0.5f, -1.f));
        rightWheel.attach(this.getEntity(), new Vector(1.f, 0.f), new Vector(0.5f, -1.f));
    }

    private void attachStickman() {
        stickman.attach(this.getEntity(), new Vector(0, 0.5f), Vector.Y);
    }

    private boolean getRight() {
        return right;
    }


    @Override
    public void draw(Canvas canvas) {
        leftWheel.draw(canvas);
        rightWheel.draw(canvas);
        stickman.draw(canvas);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        rightWheel.relax();
        leftWheel.relax();

        if (game.getKeyboard().get(KeyEvent.VK_SPACE).isPressed()) {
           right = !right;
           stickman.switchDir();
        }
        if (game.getKeyboard().get(KeyEvent.VK_DOWN).isDown()) {
            rightWheel.power(0.f);
            leftWheel.power(0.f);
        }
        if (game.getKeyboard().get(KeyEvent.VK_UP).isDown()) {
            if (right) {
                if (leftWheel.getSpeed() < leftWheel.MAX_WHEEL_SPEED) {
                    leftWheel.power(-18.f);
                }
            } else {
                if (rightWheel.getSpeed() < rightWheel.MAX_WHEEL_SPEED) {
                    rightWheel.power(18.f);
                }
            }
        }
        if (game.getKeyboard().get(KeyEvent.VK_LEFT).isDown()) {
            this.getEntity().applyAngularForce(10.f);
        }
        if (game.getKeyboard().get(KeyEvent.VK_RIGHT).isDown()) {
            this.getEntity().applyAngularForce(-10.f);
        }

        stickman.update(deltaTime);

    }
}



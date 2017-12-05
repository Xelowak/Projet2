package ch.epfl.cs107.play.game.actor;

import ch.epfl.cs107.play.game.actor.crate.Crate;
import ch.epfl.cs107.play.game.actor.entity.Bike;
import ch.epfl.cs107.play.game.actor.entity.Ground;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

import java.awt.*;

public class BikeGame extends ActorGame {

    // Create the polyLine of the Ground
    Polyline polyline = new Polyline(
            -1000.0f, -1000.0f,
            -1000.0f, 0.0f,
            0.0f, 0.0f,
            3.0f, 1.0f,
            8.0f, 1.0f,
            15.0f, 3.0f,
            16.0f, 3.0f,
            25.0f, 0.0f,
            35.0f, -5.0f,
            50.0f, -5.0f,
            55.0f, -4.0f,
            65.0f, 0.0f,
            6500.0f, -1000.0f
    );

    // Create the color of the ground
    Color colorGround = new Color(58, 157, 35);

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {

        // Call super method
        super.begin(window, fileSystem);

        // Initialise all the actors
        Crate crate1 = new Crate(this,false, new Vector(0.f, 5.f), 10.f, 1.f,1.f);
        Crate crate2 = new Crate(this,false, new Vector(0.2f, 7.0f), 10.f, 1.f,1.f);
        Crate crate3 = new Crate(this,false, new Vector(2.0f, 6.0f), 10.f, 1.f,1.f);
        Ground ground = new Ground(this, true, polyline, colorGround, null, 0.f, 10.f);
        Bike bike = new Bike(this, false, new Vector(4.f, 5.f));

        // Add the actors to the ArrayList
        addActor(crate1);
        addActor(crate2);
        addActor(crate3);
        addActor(ground);
        addActor(bike);

        this.setViewCandidate(bike);

        return true;
    }

}


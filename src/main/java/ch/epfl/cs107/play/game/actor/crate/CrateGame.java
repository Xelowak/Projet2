package ch.epfl.cs107.play.game.actor.crate;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class CrateGame extends ActorGame {

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {

        // Call the super method
        super.begin(window,fileSystem);

        // Create 3 creates
        Crate crate1 = new Crate(this,false, new Vector(0.f, 5.f), 10.f, 1.f,1.f);
        Crate crate2 = new Crate(this,false, new Vector(0.2f, 7.0f), 10.f, 1.f,1.f);
        Crate crate3 = new Crate(this,false, new Vector(2.0f, 6.0f), 10.f, 1.f,1.f);

        // Add the crates to the ArrayList
        addActor(crate1);
        addActor(crate2);
        addActor(crate3);

        return true;
    }

}

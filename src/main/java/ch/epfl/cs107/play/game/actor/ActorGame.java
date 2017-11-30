/*
 *	Author: 	 	Kilian Schneiter  
 *	Author:    	Capucine Berger
 *	Date:      	23 nov. 2017
 */

package ch.epfl.cs107.play.game.actor;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Initialiser;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;
import java.util.ArrayList;

abstract class ActorGame implements Game, Initialiser {
	private ArrayList<Actor> actors = new ArrayList<Actor>();
	private World world;
	private FileSystem fileSystem;
	private Window window;

	// Viewport properties
	private Vector viewCenter;
	private Vector viewTarget;
	private Positionable viewCandidate;
	private static final float VIEW_TARGET_VELOCITY_COMPENSATION = 0.2f;
	private static final float VIEW_INTERPOLATION_RATIO_PER_SECOND = 0.1f;
	private static final float VIEW_SCALE = 10.0f;
	
	public ActorGame(World w, FileSystem f, Window win ) {
		world = w; 
		fileSystem = f;
		window = win;
	}
	
	public void addActor(Actor a) {
		actors.add(a);
	}
	
	public Keyboard getKeyboard() {
		return window.getKeyboard () ;
	}
	
	public Canvas getCanvas() {
		return window ;
	}	
	
	public void setViewCandidate(Positionable p) {
		viewCandidate = p;
	}
	
	public boolean begin(Window window, FileSystem fileSystem) {
        
		// Initial position of the view
		viewCenter = Vector.ZERO;
		viewTarget = Vector.ZERO;
		
        // Store context
        this.window = window;
        
        // Create physics engine
        world = new World();
   
        // Gravity setting
        world.setGravity(new Vector(0.0f, -9.81f));
        
        return true;
	}
	
	public void end() {
		
	}
	
	public void update(float deltaTime) {
    	
		world.update(deltaTime);
		
		for(int i = 0; i < actors.size(); i++) {
			actors.get(i).update(deltaTime);
		}
    	
    	// Update expected viewport center
    	if (viewCandidate != null) {
    		viewTarget = viewCandidate.getPosition().add(viewCandidate.getVelocity().mul(VIEW_TARGET_VELOCITY_COMPENSATION));
    	}
    	// Interpolate with previous location
    	float ratio = (float)Math.pow(VIEW_INTERPOLATION_RATIO_PER_SECOND, deltaTime);
    	viewCenter = viewCenter.mixed(viewTarget, ratio) ;
    	// Compute new viewport
    	Transform viewTransform = Transform.I.scaled(VIEW_SCALE).translated(viewCenter);
    	window.setRelativeTransform(viewTransform) ;
    	
    	for(int i = 0; i < actors.size(); i++) {
    		actors.get(i).draw(window);
    	}
    	
    }
	
}

// The actual rendering will be done now, by the program loop

/*
 *	Author: 	 	Kilian Schneiter  
 *	Author:    	Capucine Berger
 *	Date:      	23 nov. 2017
 */

package ch.epfl.cs107.play.game.actor;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.*;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.util.ArrayList;

public abstract class ActorGame implements Game {

	// Attributes
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
	private static final float VIEW_SCALE = 20.0f;

	// Function to add an Actor to the ArrayList
	public void addActor(Actor a) {
		actors.add(a);
	}

	// Function to remove an Actor from the ArrayList
	public void removeActor(Actor a) {
		int index = actors.indexOf(a);
		actors.remove(index);
	}

	public Actor getActor(int index) {
		return actors.get(index);
	}

	public Keyboard getKeyboard() {
		return window.getKeyboard () ;
	}

	// Function to get the window
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

	// Initialise an entity with its position
	public Entity initalise(Vector vector, boolean fixed) {
		EntityBuilder entityBuilder = world.createEntityBuilder();
		entityBuilder.setFixed(fixed);
		entityBuilder.setPosition(vector);
		return entityBuilder.build();
	}

	// Initialise an entity (overloaded initialise)
	public Entity initalise(boolean fixed) {
		EntityBuilder entityBuilder = world.createEntityBuilder();
		entityBuilder.setFixed(fixed);
		return entityBuilder.build();
	}

	public WheelConstraintBuilder getWheelConstraintBuilder() {
		return Game.super.getWheelConstraintBuilder(world);
	}

	public PrismaticConstraintBuilder getPrismaticConstraintBuilder() {
		return Game.super.getPrismaticConstraintBuilder(world);
	}

	@Override
	public void end() {
		
	}

	@Override
	public void update(float deltaTime) {

		// Call the super method update
		world.update(deltaTime);

		// Updates each Actor of the ArrayList
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

    	// Draw each Actor of the ArrayList
    	for(int i = 0; i < actors.size(); i++) {
    		actors.get(i).draw(window);
    	}
    	
    }
	
}
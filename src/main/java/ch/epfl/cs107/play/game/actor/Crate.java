/*
 *	Author:      Kilian Schneiter
 *	Date:        28 nov. 2017
 */

package ch.epfl.cs107.play.game.actor;

import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.Initialiser;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Window;

public class Crate extends GameEntity implements Actor {
	
	private Polygon polygon;
	private float friction;
	private ImageGraphics image;
	
	public Crate(ActorGame g, boolean fix, Vector pos, Entity e, Polygon p, float f, String i, float height, float width) {
		super(g, fix, pos);
		polygon = p;
		friction = f;
		image = new ImageGraphics(i, height, width);
	}

	@Override
	public Transform getTransform() {
		return null;
	}

	@Override
	public Vector getVelocity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
	}
}

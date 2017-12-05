/*
 *  Author:		 Kilian Schneiter
 *	Author:      Capucine Berger
 *	Date:        23 nov. 2017
 */

package ch.epfl.cs107.play.game.actor.entity;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.math.*;
import ch.epfl.cs107.play.math.Shape;

import java.awt.*;

public abstract class GameEntity implements Actor {

	// Attributes
	private Entity entity;
	private ActorGame actorGame;

	// Constructor
	public GameEntity(ActorGame actorGame, boolean fixed, Vector position) {
		this.actorGame = actorGame;
		entity = actorGame.initalise(position, fixed);
	}
	
	// Overloaded constructor
	public GameEntity(ActorGame actorGame, boolean fixed) {
		this.actorGame = actorGame;
		entity = actorGame.initalise(fixed);
	}

	// Destroyer
	public void destroy() {
		if(entity != null) {
			entity.destroy();
		}
	}
	
	protected Entity getEntity() {
		return entity;
	}
	
	public ActorGame getOwner() {
		return actorGame;
	}

	// Build the shape of the entity
	public void buildShape(Shape shape) {
		buildShape(shape, -1, false, 0);
	}

	// Overloaded buildShape with ghost collisions
	public void buildShape(Shape shape, boolean ghost) {
		buildShape(shape, -1, ghost, 0);
	}

	// Overloaded buildShape with friction
	public void buildShape(Shape shape, float friction) {
		buildShape(shape, friction, false, 0);
	}

	public void buildShape(Shape shape, float friction, boolean ghost, int collisionGroup) {
		PartBuilder partbuilder = entity.createPartBuilder();
		if (shape != null) partbuilder.setShape(shape);
		partbuilder.setFriction(friction);
		partbuilder.setGhost(ghost);
		partbuilder.setCollisionGroup(collisionGroup);
		partbuilder.build();
	}

	// Build the ImageGraphics of the entity
	public ImageGraphics createGraphics(String image, float width, float height) {
		ImageGraphics imageGraphics = new ImageGraphics(image, height, width);
		imageGraphics.setParent(entity);
		return imageGraphics;
	}

	// Build the ShapeGraphics of the entity
	public ShapeGraphics createShapeGraphics(Shape shape, Color filling, Color colorLine, float thick, float alpha, float depth) {
		ShapeGraphics shapeGraphics = new ShapeGraphics(shape, filling, colorLine, thick, alpha, depth);
		shapeGraphics.setParent(entity);
		return shapeGraphics;
	}

	public ImageGraphics createImageGraphics(String image, float width, float height, Vector vector) {
		ImageGraphics imageGraphics = new ImageGraphics(image, width, height, vector);
		imageGraphics.setParent(entity);
		return imageGraphics;
	}

	@Override
	public Vector getVelocity() {
		return entity.getVelocity();
	}

	@Override
	public Transform getTransform() {
		return entity.getTransform();
	}
}
/*
 *  Author:		 Kilian Schneiter
 *	Author:      Capucine Berger
 *	Date:        23 nov. 2017
 */

package ch.epfl.cs107.play.game.actor;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.Vector;

abstract class GameEntity implements Game {
	
	private Entity entity;
	private ActorGame game;
	private boolean fixed;
	private Vector position;
	
	// Constructor
	public GameEntity(ActorGame g, boolean f, Vector p) {
		game = g;
		fixed = f;
		position = p;
	}
	
	// Overloaded constructor
	public GameEntity(ActorGame g, boolean f) {
		game = g;
		fixed = f;
	}

	public void destroy() {
		if(entity != null) {
			entity = null;
		}
	}
	
	protected Entity getEntity() {
		return entity;
	}
	
	protected ActorGame getOwner() {
		return game;
	}
}
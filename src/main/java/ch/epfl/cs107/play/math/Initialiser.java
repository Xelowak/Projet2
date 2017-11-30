/*
 *	Author:     Kilian Schneiter
 *	Author:		Capucine Berger
 *	Date:       28 nov. 2017
 */

package ch.epfl.cs107.play.math;

public interface Initialiser {
	
	public default void initalise(World world, Entity entity, Vector vector, boolean fixed) {
		EntityBuilder entityBuilder = world.createEntityBuilder();
		entityBuilder.setFixed(fixed);
		entityBuilder.setPosition(vector);
	}

}

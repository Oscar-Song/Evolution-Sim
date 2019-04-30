/**
 * 
 */
package com.oscarsong.environment_sim;

/**
 * The root interface of how other objects can interact with Grid
 * @author oscarsong
 *
 */
public interface GridInterface {

	/**
	 * Check if a certain position on the grid is occupied by a Person
	 * @param pos - Position to check
	 * @return True or False
	 */
	boolean checkOccupied(Position pos);
	
	/**
	 * Move a person from an old position to a new position
	 * @param oldpos - Old Position
	 * @param newpos - new Position
	 * @param person - Person object
	 */
	void movePerson(Position oldpos, Position newpos, Person person);
	
}

/**
 * 
 */
package com.oscarsong.environment_sim;

/**
 * Interface that inherits from the root GridInterface
 * and defines how a Woman interacts with Grid
 * @author oscarsong
 *
 */

public interface GridWomanInterface extends GridInterface{

	/**
	 * Allows a woman to put a baby Person on a Position
	 * @param pos - designated position
	 * @param person - A baby person
	 */
	public void setPerson(Position pos, Person person);
}

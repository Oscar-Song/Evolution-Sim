/**
 * 
 */
package com.oscarsong.environment_sim;

/**
 * Interface the specifies how a person 
 * interacts with the world
 * @author oscarsong
 *
 */
public interface PersonInterface {

	/**
	 * Object makes a random move in one of the 4 directions
	 * @return - Position
	 */
	Position randMove();
	/**
	 * Object ages 
	 */
	void age();
	/**
	 * Baby object be born with mutation- one of the traits will increase/decrease randomly
	 */
	void mutate();	
	/**
	 * Get object's survival Points
	 * @return - Survival points
	 */
	int getSP();
}

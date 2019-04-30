package com.oscarsong.environment_sim;

/**
 * Interface that inherits from the root GridInterface
 * and defines how a Man interacts with Grid
 * @author oscarsong
 *
 */
public interface GridManInterface extends GridInterface{
	/**
	 * Get a Person object from a position
	 * @param pos - Position
	 * @return Person object
	 */
	Person getPerson(Position pos);
	/**
	 * Put two men into fighting
	 * @param person1 - first person
	 * @param person2 - second person
	 * @return - who won the fight
	 */
	boolean fightWin(Person person1, Person person2);
}

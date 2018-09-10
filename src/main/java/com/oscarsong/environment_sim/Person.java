/**
 * 
 */
package com.oscarsong.environment_sim;

import java.util.Random;

/**
 * Abstract class that all persons will inherit from.
 * This class will specify that an individual person 
 * should have certain survival traits, a physical location,
 * and age.
 * <p>
 * @author oscarsong
 *
 */
public abstract class Person implements PersonInterface{


	Position pos;
	int age;
	int attractiveness;
	int adaptability;
	String name;
	
	/**
	 * Abstract constructor
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param traits - Defines a person's survival abilities
	 */
	public Person(int x, int y, Traits traits) {
		this.pos = new Position(x,y);
		this.age = 0;
		name = traits.name;
		adaptability = traits.adaptability;
		attractiveness = traits.attractiveness;
	}
	/**
	 * Allows an individual to move in a random direction.
	 * Position is only modified in the concrete class.
	 * <p>
	 * Will generate a new random move if the last one is 
	 * out of range of the grid.
	 * </p>
	 * Will return a new position for the concrete class to 
	 * use. Differs in male vs females.
	 * 
	 * @return Position - a new position to move to.
	 */
	public Position randMove() {
		Random r = new Random();
		Position newPos;
		
		do {
			int randNum = r.nextInt(4);
			int x = this.pos.PosX + Position.moves[randNum].PosX;
			int y = this.pos.PosY + Position.moves[randNum].PosY;
			newPos = new Position(x,y);		
		}while(!Position.isGoodPos(newPos));
		return newPos;
		
	}
	
	/**
	 * Increment the person's age
	 */
	public void age() {
		this.age++;
	}
	
	/**
	 * Return the person's Survival Points
	 * @return Survival Points
	 */
	public int getSP() {
		return (attractiveness + adaptability);
	}
}

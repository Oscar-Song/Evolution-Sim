/**
 *
 */
package com.oscarsong.environment_sim;

/**
 * Singleton class that provides grid for the map that simulated people
 * live on. However only the length and width of the map is
 * visible to others.
 * <p>
 * @author oscarsong
 *
 */
public class Grid implements GridManInterface, GridWomanInterface{

	private static Grid grid_instance = null;
	public final static int gridSize = 20;		//Length and width of the square grid
	private Person[][] personGrid;		//2D array that functions as the object map

	/**
	 * Private method that instantiate the grid and
	 * initialzes the object map
	 * with each slot either set to null or to a Person
	 */
	private Grid() {
		personGrid = new Person[gridSize][gridSize];

	}

	/**
	 * Singleton method that ensures grid can
	 * not be instantiated again if it has already
	 * been so
	 * <p>
	 * @return Grid: An instance of Grid object
	 */
	public static Grid getInstance() {
		if(grid_instance == null) {
			grid_instance = new Grid();
		}
		return grid_instance;
	}

	/**
	 * Check if a certain position is already occupied by another person
	 * @param pos - the position to check on
	 * @return Boolean: true or false
	 */
	public boolean checkOccupied(Position pos) {
		//System.out.println("check occupied: "+x+" "+y);
		if(personGrid[pos.PosX][pos.PosY] == null) {
			return false;
		}
		return true;
	}

	/**
	 * Modifier method that sets a position on the grid to a person
	 * @param pos - Position with x and y coordinate
	 * @param person - Person object
	 */
	public void setPerson(Position pos, Person person) {

		personGrid[pos.PosX][pos.PosY] = person;

	}
	/**
	 * Access specifier method that returns a person on a certain position
	 * @param pos - position to be accessed
	 * @return Person - a person object at that position
	 */
	public Person getPerson(Position pos) {
		return personGrid[pos.PosX][pos.PosY];
	}

	/**
	 * Move a person from one place to another
	 */
	public void movePerson(Position oldpos, Position newpos, Person person) {
		personGrid[oldpos.PosX][oldpos.PosY] = null;
		personGrid[newpos.PosX][newpos.PosY] = person;
	}

	public boolean fightWin(Person person1, Person person2) {
		// TODO Auto-generated method stub
		if(person1.traits.map.get("strength") > person2.traits.map.get("strength")) {
			setPerson(person1.pos, null);
			return true;
		}else {
			//Add this to death roll
			setPerson(person2.pos, null);
			return false;
		}
	}
}

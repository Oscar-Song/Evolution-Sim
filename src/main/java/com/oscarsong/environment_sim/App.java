package com.oscarsong.environment_sim;
/******************************************************************************
 *  Compilation:  javac App.java
 *  Execution:    java App
 *  Dependencies: none
 *
 *  Simulation of natural selection. This class provides a visual representation
 *  of cells movements on a 2D grid. It uses StdDraw from Princeton to simulate 
 *  how mutation plays a role in survival of species
 *
 *  Todo
 *  ----
 *    - Build a Jenkins Pipeline 
 *    - Code review
 *    - Add more Unit tests
 *
 *
 ******************************************************************************/
import java.util.ArrayList;

/**
 * App. This class runs the main method as well as setting 
 * some key variables for StdDraw representation and
 * back-end running.
 * <p>
 *@author oscarsong
 *@version 1.0
 *@since 1.0
 */
public class App 
{
	
	public static final double offset = 0.5;		//Describes where to draw the figure and how big it should be
	public static final int doomsday = 50;			//Decides how long is the loop
	public static ArrayList<Person> peopleLeft;		//Keep track of People still living
	public static Grid grid;
	
	/**
	 * Initializes the grid
	 */
	private static void initializeGrid() {
		grid = Grid.getInstance();
    	StdDraw.setScale(0,Grid.gridSize);
	}

	/**
	 * Initializes the person objects on the map
	 */
	private static void initializePerson() {
		//Initializes people
		
		Person newPerson;
		Position pos;
		Traits traits;
		
		//Setting up the Starks
		pos = new Position(10,15);
		traits = new Traits("Stark", 15, 8,7); //SP = 30
		newPerson = new Man(pos.PosX,pos.PosY,traits);
		grid.setPerson(pos, newPerson);
		
		pos = new Position(11,15);
		traits = new Traits(3,"Stark", 10,10); //SP = 20
		newPerson = new Woman(pos.PosX,pos.PosY,traits);
		grid.setPerson(pos, newPerson);
		
		//Setting up the Lannisters
		pos = new Position(14,10);
		traits = new Traits("Lannister", 10, 10, 10); //SP = 30
		newPerson = new Man(pos.PosX,pos.PosY,traits);
		grid.setPerson(pos, newPerson);
		
		pos = new Position(15,10);
		traits = new Traits(3,"Lannister", 12,8); //SP = 20
		newPerson = new Woman(pos.PosX,pos.PosY,traits);
		grid.setPerson(pos, newPerson);
		
		
		//Setting up the Baratheons
		pos = new Position(2,8);
		traits = new Traits("Baratheon", 5,5,20); //SP = 30
		newPerson = new Man(pos.PosX,pos.PosY,traits);
		grid.setPerson(pos, newPerson);
		
		pos = new Position(3,8);
		traits = new Traits(3,"Baratheon", 8,12); //SP = 20
		newPerson = new Woman(pos.PosX,pos.PosY,traits);
		grid.setPerson(pos, newPerson);
		
		
		
		
	}
	
	public static void changeColor(Person p) {
		if(p.name.equals("Stark"))
			StdDraw.setPenColor(StdDraw.GRAY);
		else if(p.name.equals("Lannister"))
			StdDraw.setPenColor(StdDraw.YELLOW);
		else if(p.name.equals("Baratheon"))
			StdDraw.setPenColor(StdDraw.BLACK);
	}
	/**
	 * Lays out the grid base on who's alive on where.
	 * Resets list of people left
	 */
	public static void display() {
		peopleLeft = new ArrayList<Person>();
		for (int x = 0; x < Grid.gridSize; x++)
		{
		  for (int y = 0; y <Grid.gridSize; y++)
		  {
			  Position pos = new Position(x,y);
			  if(grid.checkOccupied(pos)) {
				  Person p = grid.getPerson(pos);
				  changeColor(p);
				  if(p instanceof Man) {
					  StdDraw.filledSquare(x+offset, y+offset, offset);
				  }
				  else {
					  StdDraw.filledCircle(x+offset, y+offset, offset);
				  }
				  peopleLeft.add(grid.getPerson(pos));
			  }
			  else{
				  StdDraw.setPenColor(StdDraw.WHITE);
				  StdDraw.filledSquare(x+offset, y+offset, offset);
			  }
			  StdDraw.setPenColor(StdDraw.BLACK);
			  StdDraw.square(x+offset, y+offset, offset);		  
		  }
		}	
	}
	
	/**
	 * Allows all objects to take actions such as moving and aging
	 * @param year - Taken the current year as an int
	 */
	public static void action(int year) {
		System.out.println("Total people now: "+peopleLeft.size());
		for(Person person: peopleLeft){
			person.age++;
			//Natural disaster
			if(year % 10 == 0) {
				System.out.println("Disaster time!");
				System.out.println("Adaptability threshold: "+(year/5));
				if(person.adaptability < (year/5))
					grid.setPerson(person.pos, null);
					continue;
			}
				
			if(person instanceof Man) {
				Man man = (Man)person;
				man.randMove();
				//Man die of aging
				if(man.age > 50)
					grid.setPerson(man.pos, null);
				
			}
			else if(person instanceof Woman) {
				Woman woman = (Woman)person;
				woman.randMove();
				if(woman.age > 60)
					grid.setPerson(woman.pos, null);
				
			}
		}
	}
	/**
	 * Starts the simulation that runs until doomsday is reached. 
	 * @param args - Standard String arguments
	 */
    public static void main( String[] args )
    {
    	initializeGrid();
    	initializePerson();
    	display();
    	int i = 0;
    	while (true) {
    		System.out.println("Year: "+i);
    		display();
    		action(i++);
    		
    	}	
    }
}

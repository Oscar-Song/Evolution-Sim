package com.oscarsong.environment_sim;
import javafx.util.Pair;
/**
 * This class encapsulates a person's position as well
 * as provides methods for how a person can move.
 * <p>
 * @author oscarsong
 *
 */
public class Position {

	public int PosX;
	public int PosY;
	public final static Pair[] moves = {	//Final selection of moves available
			new Pair(-1,0),
			new Pair(0,-1),
			new Pair(1,0),
			new Pair(0,1)
	};
	public final static Pair[] surround = {
			new Pair(-1,-1),
			new Pair(-1,0),
			new Pair(-1,1),
			new Pair(0,-1),
			new Pair(0,1),
			new Pair(1,-1),
			new Pair(1,0),
			new Pair(1,1)
	};
	/**
	 * Constructor method to set a position at certain coordinate
	 * @param x - x coordinate on the grid
	 * @param y - y coordinate on the grid
	 */
	public Position(int x, int y){
		this.PosX = x;
		this.PosY = y;
	}
	
	
	/**
	 * Checks if a position is within the range of the grid
	 * @param pos - the position to check on
	 * @return - Boolean
	 */
	public static Boolean isGoodPos(Position pos) {
		int x = pos.PosX;
		int y = pos.PosY;
		return (x >= 0 && x < Grid.gridSize && y >= 0 && y < Grid.gridSize);
	}
	
}

/**
 * 
 */
package com.oscarsong.environment_sim;

import java.util.HashMap;

/**
 * This class implements the methods of PersonInterface 
 * and inherits from Person abstract class.
 * <p>
 * A Man object differs in that it has strength as part of its 
 * survival points as well as habit of fighting another male.
 * @author oscarsong
 *
 */
public class Man extends Person{
	//Strength is used for fighting
	public Traits traits;
	private GridManInterface gridInterface;
	/**
	 * Constructor method to instantiate a man with a certain location
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param traits - setting of the man's survival abilities
	 */
	public Man(int x, int y, Traits traits) {
		super(x,y,traits);
		this.traits = traits;
		gridInterface = Grid.getInstance();
	}
	
	/**
	 * This method uses the inherited method to get a possible move,
	 * then check if someone else has occupied that spot and react 
	 * appropriately based on that person's gender.
	 * <p>
	 * @return Position - position of the new spot
	 * TODO: A man should not have that much access to the grid.
	 */
	public Position randMove() {
		
		for(int i = 0; i < Position.moves.length;i++) {
			int x = this.pos.PosX+(Integer)Position.moves[i].getKey();
			int y = this.pos.PosY+(Integer)Position.moves[i].getValue();
			Position newPos = new Position(x,y);
			if(Position.isGoodPos(newPos) && gridInterface.checkOccupied(pos)) {
				Person otherPerson = gridInterface.getPerson(newPos);
				if(otherPerson instanceof Woman) {
					//must be a woman. Check if can mate
					Woman otherWoman = (Woman)otherPerson;
					//If they are not related and the woman is prettier, mate
					if(this.name.equals(otherWoman.name)) {
						otherWoman.reproduce(this);
					}
				}
			}
		}
		Position pos = super.randMove();
		if(gridInterface.checkOccupied(pos)) {
			Person otherPerson = gridInterface.getPerson(pos);
			if(otherPerson.name != this.name){
				if(!fightWin(otherPerson))
					return pos;
			}
			else return pos;
		}
		gridInterface.movePerson(this.pos,pos,this);
		this.pos = pos;
		return pos;
	}
	/**
	 * Fights the other man to death
	 * @param otherPerson - The other man that needs to be fight off
	 * @return - True if won the battle or false not
	 */
	public boolean fightWin(Person otherPerson) {
		return gridInterface.fightWin(this, otherPerson);
	}
	/**
	 * Mutate a certain trait
	 * @see com.oscarsong.environment_sim.PersonInterface#mutate()
	 */
	public void mutate() {
		// TODO Auto-generated method stub
		int r = (int)(Math.random() * 2);
		int change = (Math.random()<0.7)? 5: -5;
		switch(r){
		case 0:
			this.traits.map.put("strength", this.traits.map.get("strength") + change);
			break;
		default:
			this.traits.map.put("adaptability", this.traits.map.get("adaptability") + change);
			break;
		}
	}
}

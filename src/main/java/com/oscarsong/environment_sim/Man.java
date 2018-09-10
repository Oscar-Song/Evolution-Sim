/**
 * 
 */
package com.oscarsong.environment_sim;

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
	public int strength;
	private GridManInterface gridInterface;
	/**
	 * Constructor method to instantiate a man with a certain location
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param traits - setting of the man's survival abilities
	 */
	public Man(int x, int y, Traits traits) {
		super(x,y,traits);
		strength = traits.strength;
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
			int x = this.pos.PosX+Position.moves[i].PosX;
			int y = this.pos.PosY+Position.moves[i].PosY;
			Position newPos = new Position(x,y);
			if(Position.isGoodPos(newPos) && gridInterface.checkOccupied(pos)) {
				Person otherPerson = gridInterface.getPerson(newPos);
				if(otherPerson instanceof Woman) {
					//must be a woman. Check if can mate
					Woman otherWoman = (Woman)otherPerson;
					//If they are not related and the woman is prettier, mate
					if(this.name.equals(otherWoman.name) && otherWoman.attractiveness >= this.attractiveness) {
						otherWoman.reproduce(this);
					}
				}
			}
		}
		Position pos = super.randMove();
		if(gridInterface.checkOccupied(pos)) {
			Person otherPerson = gridInterface.getPerson(pos);
			//if that person is a man, fight each other until death
			if(otherPerson instanceof Man) {
				Man otherMan = (Man)otherPerson;
				if(!fightWin(otherMan))
					return pos;
			}
			else
				return pos;
		}
		gridInterface.movePerson(this.pos,pos,this);
		this.pos = pos;
		return pos;
	}
	/**
	 * Fights the other man to death
	 * @param otherMan - The other man that needs to be fight off
	 * @return - True if won the battle or false not
	 */
	public boolean fightWin(Man otherMan) {
		return gridInterface.menFight(this, otherMan);
	}
	/**
	 * Mutate a certain trait
	 * @see com.oscarsong.environment_sim.PersonInterface#mutate()
	 */
	public void mutate() {
		// TODO Auto-generated method stub
		int r = (int)(Math.random() * 3);
		switch(r){
		case 0: 
			this.strength += (Math.random()<0.7)?(5):(-5);
			break;
		case 1:
			this.attractiveness += (Math.random()<0.7)?(5):(-5);
			break;
		default:
			this.adaptability += (Math.random()<0.7)?(5):(-5);
			break;
		}
	}

	/**
	 * @return survival points with strength included
	 */
	@Override
	public int getSP() {
		return strength + super.getSP();
	}

}

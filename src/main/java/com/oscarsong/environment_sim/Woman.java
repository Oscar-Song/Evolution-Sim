/**
 * 
 */
package com.oscarsong.environment_sim;

/**
 * This class implements the methods of PersonInterface 
 * and inherits from Person abstract class.
 * <p>
 * A Woman object differs in that it doesn't have strength as a trait,
 * but instead has the ability to reproduce after been impregnated
 * by a Man as well as a limit of how much to reproduce.
 * @author oscarsong
 *
 */
public class Woman extends Person {

	public int reproductiveness;
	private GridWomanInterface gridInterface;
	
	/**
	 * This class implements the methods of PersonInterface 
	 * and inherits from Person abstract class.
	 * <p>
	 * A woman object differs in that it doesn't fight and 
	 * has the ability to reproduce
	 * @param x - x coordinate of the position
	 * @param y - y coordinate of the position
	 * @param traits - Defines a woman's survival abilities
	 */
	public Woman(int x, int y, Traits traits) {
		super(x, y, traits);
		this.reproductiveness = traits.reproductiveness;
		gridInterface = Grid.getInstance();
	}

	/**
	 * This method uses the inherited method to get a possible move,
	 * then only move if it has not been occupied
	 * <p>
	 * @return Position - position of the new spot
	 */
	public Position randMove() {
		Position pos = super.randMove();
		if(!gridInterface.checkOccupied(pos)) { 
			gridInterface.movePerson(this.pos,pos,this);
			this.pos = pos;
		}
		return pos;
	}
	
	/**
	 * After the man tries to reproduce with her, she will proceed
	 * only if this man has enough SP comparing to hers(that she
	 * finds him to be suitable).
	 * <p>
	 * The woman will set a baby on vacant spaces around her until 
	 * the reproduction limit is reached. The baby will have traits 
	 * based on that of his/her father and mother.
	 * @param man - the man she is supposed to reproduce with 
	 */
	public void reproduce(Man man) {
		//Abort reproduction if the Man is not good enough
		if(this.getSP() > (man.getSP() * 1.5)) {
			return;
		}
		Traits traits = new Traits(man,this);
		int babyAmt = 0;
		for(int i = 0; i < Position.surround.length;i++) {
			int x = this.pos.PosX+Position.surround[i].PosX;
			int y = this.pos.PosY+Position.surround[i].PosY;
			Position pos = new Position(x,y);
			if(Position.isGoodPos(pos) && !gridInterface.checkOccupied(pos)) {
				Person baby = (Math.random() <0.5) ? new Man(x,y,traits):new Woman(x,y,traits);
				int randNum = (int)Math.random();
				if((baby.name.equals("Lannister") && randNum<0.5))
					baby.mutate();
					
				gridInterface.setPerson(pos, baby);
				babyAmt++;
			}
			if(babyAmt>=this.reproductiveness)
				break;
		}
		
	}
	
	/** 
	 * Mutate a certain trait
	 * @see com.oscarsong.environment_sim.PersonInterface#mutate()
	 */
	@Override
	public void mutate() {
		int r = (int)(Math.random() * 2);
		switch(r){
		case 0:
			this.attractiveness += (Math.random()<0.7)?(5):(-5);
			break;
		default:
			this.adaptability += (Math.random()<0.7)?(5):(-5);
			break;
		}
	}
}

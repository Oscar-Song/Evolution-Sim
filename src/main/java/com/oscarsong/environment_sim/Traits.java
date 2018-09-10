/**
 * 
 */
package com.oscarsong.environment_sim;

/**
 * Encapsulates the traits a person can possess
 * @author oscarsong
 *
 */
public class Traits {

	public int strength;
	public int attractiveness;
	public int adaptability;
	public String name;
	public int reproductiveness;
	/**
	 * Constructor for a baby
	 * @param man - father
	 * @param woman - mother
	 */
	public Traits(Man man, Woman woman) {
		this.name = man.name;
		this.attractiveness = woman.attractiveness;
		this.adaptability = avg(man.adaptability,woman.adaptability);
		this.strength = man.strength;
	}
	/**
	 * Constructor for the First Men
	 * @param name - name
	 * @param strength - strength
	 * @param attractiveness - attractiveness
	 * @param adaptability - adaptability
	 */
	public Traits(String name, int strength, int attractiveness, int adaptability) {
		this.name = name;
		this.strength = strength;
		this.attractiveness = attractiveness;
		this.adaptability = adaptability;
	}
	/**
	 * Constructor for the First Women
	 * @param reproductiveness - how to much to reproduce
	 * @param name - name
	 * @param attractiveness - attractiveness
	 * @param adaptability - adaptability
	 */
	public Traits(int reproductiveness, String name, int attractiveness, int adaptability) {
		this.reproductiveness = reproductiveness;
		this.name = name;
		this.attractiveness = attractiveness;
		this.adaptability = adaptability;
	}
	/**
	 * To get the average of two integers
	 * @param m - man's value
	 * @param w - woman's value
	 * @return an average value of the two
	 */
	public static int avg(int m, int w) {
		return (m+w)/2;
	}
}

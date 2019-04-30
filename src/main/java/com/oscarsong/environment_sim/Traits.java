/**
 * 
 */
package com.oscarsong.environment_sim;

import java.util.Map;
import java.util.HashMap;

/**
 * Encapsulates the traits a person can possess
 * @author oscarsong
 *
 */
public class Traits {

	public Map<String, Integer> map;
	public String name;

	/**
	 * Constructor for a baby
	 * @param man - father
	 * @param woman - mother
	 */
	public Traits(Man man, Woman woman) {
		this.name = man.name;
		this.map = new HashMap<String, Integer>();
		this.map.put("adaptability", avg(man.traits.map.get("adaptability"),woman.traits.map.get("adaptability")));
		this.map.put("strength", man.traits.map.get("strength"));
		this.map.put("fertility", woman.traits.map.get("fertility"));
	}
	/**
	 * Constructor for the First Man or Woman
	 * @param name - name
	 * @param strength - strength
	 * @param adaptability - adaptability
	 */
	public Traits(String name, int strength, int adaptability, int fertility) {
		this.name = name;
		this.map = new HashMap<String, Integer>();
		this.map.put("strength", strength);
		this.map.put("adaptability", adaptability);
		this.map.put("fertility", fertility);
	}

	/**
	 * To get the average of two integers
	 * @param m - man's value
	 * @param w - woman's value
	 * @return an average value of the two
	 */
	public static Integer avg(int m, int w) {
		return (m+w)/2;
	}
}

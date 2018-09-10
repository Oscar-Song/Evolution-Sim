/**
 * 
 */
package com.oscarsong.environment_sim;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * JUnit test class for Position.java
 * @author oscarsong
 *
 */
public class PositionTest {

	private static Position position;
	private static int testNum;
	
	/**
	 * 
	 * @throws java.lang.Exception - throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testNum = 1;
		System.out.println("Testing Position class now...");
	}

	/**
	 * @throws java.lang.Exception - throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Finished testing Position");
	}

	/**
	 * @throws java.lang.Exception - throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("Test "+testNum);
	}

	/**
	 * @throws java.lang.Exception - throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		testNum++;
	}

	@Test
	public void testPosition() {
		position = new Position(5, 10);
		assertEquals(position.PosX, 5);
		assertEquals(position.PosY, 10);
	}
	
	@Test
	public void testMoves() {
		assertEquals(Position.moves.length,4);
	}
	@Test
	public void testIsGoodPos() {
		position = new Position(10, 15);
		assertTrue(Position.isGoodPos(position));
		position = new Position(20,20);
		assertFalse(Position.isGoodPos(position));
		position = new Position(-1,-1);
		assertFalse(Position.isGoodPos(position));
	}

}

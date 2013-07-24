package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import poker.GUI.Coordinates;


public class CoordinatesTest {


	public void setUp() throws Exception {
		int x = 400;
		int y = 300;
		coordinatesList = new ArrayList<Coordinates>();
		// Initialize HandEvaluator
		coordinates = new Coordinates(x,y);
	}

	public void testPlayerHands() {
		assertEquals(400, coordinates.getAxisX());
		assertEquals(400, coordinates.getAxisY());
	}

	
	List<Coordinates> coordinatesList;
	Coordinates coordinates;
}

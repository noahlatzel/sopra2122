/**
 * 
 */
package de.wwu.sopra.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Valeria Vassallo
 *
 */
class RouteTest {

	/**
	 * Test method for {@link de.wwu.sopra.model.Route#Route(int, de.wwu.sopra.model.Fahrzeug)}.
	 */
	@Test
	void testRoute() {
		Fahrzeug fzeug = new Fahrzeug(1, 100);
		
		Route route = new Route(1, fzeug);
		
		assertTrue(route instanceof Route);
	}

	/**
	 * Test method for {@link de.wwu.sopra.model.Route#getRoutenNummer()}.
	 */
	@Test
	void testGetRoutenNummer() {
		Fahrzeug fzeug = new Fahrzeug(1, 100);
		
		Route route = new Route(1, fzeug);
		
		assertEquals(route.getRoutenNummer(), 1);
	}

	/**
	 * Test method for {@link de.wwu.sopra.model.Route#setRoutenNummer(int)}.
	 */
	@Test
	void testSetRoutenNummer() {
		Fahrzeug fzeug = new Fahrzeug(1, 100);
		
		Route route = new Route(2, fzeug);
		route.setRoutenNummer(1);
		
		assertEquals(route.getRoutenNummer(), 1);
	}

	/**
	 * Test method for {@link de.wwu.sopra.model.Route#getFahrzeug()}.
	 */
	@Test
	void testGetFahrzeug() {
		Fahrzeug fzeug = new Fahrzeug(1, 100);
		
		Route route = new Route(1, fzeug);
		
		assertTrue(route.getFahrzeug() instanceof Fahrzeug);
		assertEquals(route.getFahrzeug().getFahrzeugNummer(), 1);
		assertEquals(route.getFahrzeug().getKapazitaet(), 100);
	}

}

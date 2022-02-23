/**
 * 
 */
package de.wwu.sopra.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 *
 */
class RouteTest {

	/**
	 * Test method for {@link de.wwu.sopra.model.Route#Route(int, de.wwu.sopra.model.Fahrzeug)}.
	 * @throws IllegalArgumentException 
	 */
	@Test
	void testRoute() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(1, 100);
		
		Route route = new Route(100, fzeug);
		
		assertTrue(route instanceof Route);
	}

	/**
	 * Test method for {@link de.wwu.sopra.model.Route#getRoutenNummer()}.
	 * @throws IllegalArgumentException 
	 */
	@Test
	void testGetRoutenNummer() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(2, 100);
		
		Route route = new Route(2, fzeug);
		
		assertEquals(route.getRoutenNummer(), 2);
	}

	/**
	 * Test method for {@link de.wwu.sopra.model.Route#setRoutenNummer(int)}.
	 * @throws IllegalArgumentException 
	 */
	@Test
	void testSetRoutenNummer() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(3, 100);
		
		Route route = new Route(3, fzeug);
		route.setRoutenNummer(1);
		
		assertEquals(route.getRoutenNummer(), 1);
	}

	/**
	 * Test method for {@link de.wwu.sopra.model.Route#getFahrzeug()}.
	 * @throws IllegalArgumentException 
	 */
	@Test
	void testGetFahrzeug() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(4, 100);
		
		Route route = new Route(4, fzeug);
		
		assertTrue(route.getFahrzeug() instanceof Fahrzeug);
		assertEquals(route.getFahrzeug().getFahrzeugNummer(), 4);
		assertEquals(route.getFahrzeug().getKapazitaet(), 100);
	}
	
	/**
	 * Test zur Routenerstellung mit Fahrzeug bereits mit Route
	 * @throws IllegalArgumentException 
	 */
	@Test
	void testRoutenerstellungMitFahrzeugMitRoute() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(5, 100);
		
		Route routeFirst = new Route(5, fzeug);
		Route routeSecond = new Route(6, fzeug);
		
		assertEquals(fzeug.getRoute(), routeFirst);
		assertNotEquals(fzeug.getRoute(), routeSecond);
	}
	
	/**
	 * Test zur Routenerstellung mit schon existierender RoutenNummer
	 * @throws IllegalArgumentException 
	 */
	@Test
	void testRoutenerstellungMitExistierenderRoutenNummer() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(6, 100);
		Fahrzeug fzeug2 = new Fahrzeug(7, 200);
		
		@SuppressWarnings("unused")
		Route routeFirst = new Route(7, fzeug);
		
		@SuppressWarnings("unused")
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Route routeSecond = new Route(7, fzeug2);
        });
	}

}

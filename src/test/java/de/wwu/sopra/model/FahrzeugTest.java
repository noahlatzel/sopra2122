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
class FahrzeugTest {

	/**
	 * Pruefmethode fuer {@link de.wwu.sopra.model.Fahrzeug#Fahrzeug(int, float)}.
	 * @throws IllegalArgumentException 
	 */
	@Test
	void testFahrzeug() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(6548945, 100);
		
		assertTrue(fzeug instanceof Fahrzeug);
	}

	/**
	 * Pruefmethode fuer {@link de.wwu.sopra.model.Fahrzeug#getFahrzeugNummer()}.
	 * @throws IllegalArgumentException 
	 */
	@Test
	void testGetFahrzeugNummer() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(1000, 100);
		
		assertEquals(fzeug.getFahrzeugNummer(), 1000);
	}

	/**
	 * Pruefmethode fuer {@link de.wwu.sopra.model.Fahrzeug#setFahrzeugNummer(int)}.
	 * @throws IllegalArgumentException 
	 */
	@Test
	void testSetFahrzeugNummer() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(10, 100);
		fzeug.setFahrzeugNummer(2);
		
		assertEquals(fzeug.getFahrzeugNummer(), 2);
	}

	/**
	 * Pruefmethode fuer {@link de.wwu.sopra.model.Fahrzeug#getKapazitaet()}.
	 * @throws IllegalArgumentException 
	 */
	@Test
	void testGetKapazitaet() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(3, 100);
		
		assertEquals(fzeug.getKapazitaet(), 100);
	}

	/**
	 * Pruefmethode fuer {@link de.wwu.sopra.model.Fahrzeug#setKapazitaet(float)}.
	 * @throws IllegalArgumentException 
	 */
	@Test
	void testSetKapazitaet() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(4, 100);
		fzeug.setKapazitaet(200);
		
		assertEquals(fzeug.getKapazitaet(), 200);
	}

	/**
	 * Pruefmethode fuer {@link de.wwu.sopra.model.Fahrzeug#getRoute()}.
	 * @throws IllegalArgumentException 
	 */
	@Test
	void testGetRoute() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(5, 100);
		@SuppressWarnings("unused")
		Route route = new Route(5, fzeug);
		
		assertTrue(fzeug.getRoute() instanceof Route);
		assertEquals(fzeug.getRoute().getRoutenNummer(), 5);
	}

}

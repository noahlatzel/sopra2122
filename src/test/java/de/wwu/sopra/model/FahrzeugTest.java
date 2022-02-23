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
	 */
	@Test
	void testFahrzeug() {
		Fahrzeug fzeug = new Fahrzeug(1, 100);
		
		assertTrue(fzeug instanceof Fahrzeug);
	}

	/**
	 * Pruefmethode fuer {@link de.wwu.sopra.model.Fahrzeug#getFahrzeugNummer()}.
	 */
	@Test
	void testGetFahrzeugNummer() {
		Fahrzeug fzeug = new Fahrzeug(1, 100);
		
		assertEquals(fzeug.getFahrzeugNummer(), 1);
	}

	/**
	 * Pruefmethode fuer {@link de.wwu.sopra.model.Fahrzeug#setFahrzeugNummer(int)}.
	 */
	@Test
	void testSetFahrzeugNummer() {
		Fahrzeug fzeug = new Fahrzeug(10, 100);
		fzeug.setFahrzeugNummer(1);
		
		assertEquals(fzeug.getFahrzeugNummer(), 1);
	}

	/**
	 * Pruefmethode fuer {@link de.wwu.sopra.model.Fahrzeug#getKapazitaet()}.
	 */
	@Test
	void testGetKapazitaet() {
		Fahrzeug fzeug = new Fahrzeug(1, 100);
		
		assertEquals(fzeug.getKapazitaet(), 100);
	}

	/**
	 * Pruefmethode fuer {@link de.wwu.sopra.model.Fahrzeug#setKapazitaet(float)}.
	 */
	@Test
	void testSetKapazitaet() {
		Fahrzeug fzeug = new Fahrzeug(1, 100);
		fzeug.setKapazitaet(200);
		
		assertEquals(fzeug.getKapazitaet(), 200);
	}

	/**
	 * Pruefmethode fuer {@link de.wwu.sopra.model.Fahrzeug#getRoute()}.
	 */
	@Test
	void testGetRoute() {
		Fahrzeug fzeug = new Fahrzeug(1, 100);
		@SuppressWarnings("unused")
		Route route = new Route(5, fzeug);
		
		assertTrue(fzeug.getRoute() instanceof Route);
		assertEquals(fzeug.getRoute().getRoutenNummer(), 5);
	}

}

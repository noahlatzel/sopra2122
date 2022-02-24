/**
 * 
 */
package de.wwu.sopra.datenhaltung.management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.benutzer.Fahrer;
import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import de.wwu.sopra.datenhaltung.management.Route;

/**
 * 
 *
 */
class FahrzeugTest {

	/**
	 * Pruefmethode fuer
	 * {@link de.wwu.sopra.datenhaltung.management.Fahrzeug#Fahrzeug(int, float)}.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	void testFahrzeug() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(6548945, 100);

		assertTrue(fzeug instanceof Fahrzeug);
	}

	/**
	 * Pruefmethode fuer
	 * {@link de.wwu.sopra.datenhaltung.management.Fahrzeug#getFahrzeugNummer()}.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	void testGetFahrzeugNummer() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(1000, 100);

		assertEquals(fzeug.getFahrzeugNummer(), 1000);
	}

	/**
	 * Pruefmethode fuer
	 * {@link de.wwu.sopra.datenhaltung.management.Fahrzeug#setFahrzeugNummer(int)}.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	void testSetFahrzeugNummer() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(10, 100);
		fzeug.setFahrzeugNummer(2);

		assertEquals(fzeug.getFahrzeugNummer(), 2);
	}

	/**
	 * Pruefmethode fuer
	 * {@link de.wwu.sopra.datenhaltung.management.Fahrzeug#getKapazitaet()}.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	void testGetKapazitaet() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(3, 100);

		assertEquals(fzeug.getKapazitaet(), 100);
	}

	/**
	 * Pruefmethode fuer
	 * {@link de.wwu.sopra.datenhaltung.management.Fahrzeug#setKapazitaet(float)}.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	void testSetKapazitaet() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(4, 100);
		fzeug.setKapazitaet(200);

		assertEquals(fzeug.getKapazitaet(), 200);
	}

	/**
	 * Pruefmethode fuer
	 * {@link de.wwu.sopra.datenhaltung.management.Fahrzeug#getRoute()}.
	 * 
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

	/**
	 * Test fuer getFahrer und setFahrer
	 */
	@Test
	void testFahrer() {
		Fahrzeug fzeug = new Fahrzeug(6, 100);
		Fahrer fahrer = new Fahrer("fahrer1", "sicheresPasswort", "thomas@uni-muenster.de", "Einsteinstra�e 64",
				"Thomas", "Thomas", "DE291234", null);
		fzeug.setFahrer(fahrer);
		assertTrue(fzeug.getFahrer().equals(fahrer));
	}

	/**
	 * Test fuer doppelte Fahrzeugnummer in setFahrzeugnummer
	 */
	@Test
	void testThrowsSetFahrzeugnummer() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Fahrzeug(7, 100);
			new Fahrzeug(7, 100);
		});
	}
}

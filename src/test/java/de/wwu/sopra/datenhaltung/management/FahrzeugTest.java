/**
 * 
 */
package de.wwu.sopra.datenhaltung.management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.benutzer.Fahrer;
import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;

/**
 * 
 *
 */
class FahrzeugTest {
	@BeforeEach
	void reset() {
		Lager.reset();
		FahrzeugRegister.reset();
	}

	/**
	 * Pruefmethode fuer
	 * {@link de.wwu.sopra.datenhaltung.management.Fahrzeug#Fahrzeug(int, float)}.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	void testFahrzeug() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(100);
		assertTrue(fzeug.toString().equals("" + fzeug.getFahrzeugNummer()));
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
		Fahrzeug fzeug = new Fahrzeug(8);
		fzeug.setFahrzeugNummer(-1);
		assertEquals(fzeug.getFahrzeugNummer(), -1);
	}

	/**
	 * Pruefmethode fuer
	 * {@link de.wwu.sopra.datenhaltung.management.Fahrzeug#setFahrzeugNummer(int)}.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	void testSetFahrzeugNummer() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(100);
		fzeug.setFahrzeugNummer(99);

		assertEquals(fzeug.getFahrzeugNummer(), 99);
	}

	/**
	 * Pruefmethode fuer
	 * {@link de.wwu.sopra.datenhaltung.management.Fahrzeug#getKapazitaet()}.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	void testGetKapazitaet() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(100);

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
		Fahrzeug fzeug = new Fahrzeug(100);
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
		Fahrzeug fzeug = new Fahrzeug(100);
		Route route = new Route(fzeug);
		int nummer = route.getRoutenNummer();
		assertTrue(fzeug.getRoute() instanceof Route);
		assertEquals(fzeug.getRoute().getRoutenNummer(), nummer);
	}

	/**
	 * Test fuer getFahrer und setFahrer
	 */
	@Test
	void testFahrer() {
		Fahrzeug fzeug = new Fahrzeug(100);
		Produkt cola = new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29);
		List<Produkt> produkte = new ArrayList<Produkt>();
		produkte.add(cola);
		Bestellung bestellung = new Bestellung(null, produkte,
				new Kunde("Beton", "1234", "hart@test.de", "Abstiege 1", "Zementa", "test", "test"));
		ArrayList<Bestellung> bestellungen = new ArrayList<Bestellung>();
		bestellungen.add(bestellung);
		Route route = new Route(fzeug);
		route.setBestellungen(bestellungen);
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
			Fahrzeug temp = new Fahrzeug(100);
			FahrzeugRegister.addFahrzeug(temp);
			new Fahrzeug(100).setFahrzeugNummer(temp.getFahrzeugNummer());
		});
	}
}

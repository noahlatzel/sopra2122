package de.wwu.sopra.datenhaltung.management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.bestellung.Bestellung;

/**
 * 
 *
 */
class RouteTest {

	/**
	 * Test method for
	 * {@link de.wwu.sopra.datenhaltung.management.Route#Route(int, de.wwu.sopra.datenhaltung.management.Fahrzeug)}.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	void testRoute() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(2352, 100);

		Route route = new Route(100, fzeug);

		assertTrue(route instanceof Route);
	}

	/**
	 * Test method for
	 * {@link de.wwu.sopra.datenhaltung.management.Route#getRoutenNummer()}.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	void testGetRoutenNummer() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(36436, 100);

		Route route = new Route(98, fzeug);

		assertEquals(route.getRoutenNummer(), 98);
	}

	/**
	 * Test method for
	 * {@link de.wwu.sopra.datenhaltung.management.Route#setRoutenNummer(int)}.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	void testSetRoutenNummer() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(548, 100);

		Route route = new Route(3, fzeug);
		route.setRoutenNummer(1520);

		assertEquals(route.getRoutenNummer(), 1520);
	}

	/**
	 * Test method for
	 * {@link de.wwu.sopra.datenhaltung.management.Route#getFahrzeug()}.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	void testGetFahrzeug() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(3548, 100);

		Route route = new Route(546548, fzeug);

		assertTrue(route.getFahrzeug() instanceof Fahrzeug);
		assertEquals(route.getFahrzeug().getFahrzeugNummer(), 3548);
		assertEquals(route.getFahrzeug().getKapazitaet(), 100);
	}

	/**
	 * Test zur Routenerstellung mit Fahrzeug bereits mit Route
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	void testRoutenerstellungMitFahrzeugMitRoute() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(12354, 100);

		Route routeFirst = new Route(8462, fzeug);
		Route routeSecond = new Route(8463, fzeug);

		assertEquals(fzeug.getRoute(), routeFirst);
		assertNotEquals(fzeug.getRoute(), routeSecond);
	}

	/**
	 * Test zur Routenerstellung mit schon existierender RoutenNummer
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	void testRoutenerstellungMitExistierenderRoutenNummer() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(987987, 100);
		Fahrzeug fzeug2 = new Fahrzeug(987988, 200);

		@SuppressWarnings("unused")
		Route routeFirst = new Route(654879, fzeug);

		@SuppressWarnings("unused")
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Route routeSecond = new Route(654879, fzeug2);
		});
	}

	/**
	 * Test fuer Bestellungen einer Route
	 */
	@Test
	void testBestellungen() {
		ArrayList<Produkt> produkte = new ArrayList<Produkt>();
		produkte.add(new Produkt("Cola", "Lecker", 0.99, 1.29));
		Bestellung bestellung = new Bestellung(0, null, produkte, null);
		ArrayList<Bestellung> bestellungen = new ArrayList<Bestellung>();
		bestellungen.add(bestellung);
		Fahrzeug fzeug = new Fahrzeug(9879871, 100);
		Route routeFirst = new Route(10, fzeug);
		routeFirst.setBestellungen(bestellungen);
		assertTrue(routeFirst.getBestellungen().contains(bestellung));
	}
}

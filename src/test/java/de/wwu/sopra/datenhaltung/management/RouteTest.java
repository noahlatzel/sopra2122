package de.wwu.sopra.datenhaltung.management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.bestellung.BestellStatus;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;

/**
 * 
 *
 */
class RouteTest {
	ArrayList<Bestellung> testbestellungen;

	@AfterEach
	void cleanAfter() {
		Lager.reset();
		FahrzeugRegister.reset();
	}

	@BeforeEach
	void reset() {
		Lager.reset();
		FahrzeugRegister.reset();

		Kunde kunde2 = new Kunde("Bierman", "1234", "hart@test.de", "Destille", "Maxi", "malvoll", "test");
		Produkt cola = new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29);
		Produkt bier = new Produkt("Krombacher Pils", "Eine Perle der Natur", 0.99, 1.96);
		Produkt korn = new Produkt("Sasse Korn", "LEEEECKER", 4.20, 6.66);
		List<Produkt> produkte = new ArrayList<Produkt>();
		produkte.add(bier);
		produkte.add(cola);
		produkte.add(korn);
		Lager.produktZumSortimentHinzufuegen(new Produkt("Coca Cola", "Lecker", 0.49, 0.99));
		Lager.produktZumSortimentHinzufuegen(new Produkt("Krombacher Pils", "Lecker", 0.49, 0.99));
		Lager.produktZumSortimentHinzufuegen(new Produkt("Sasse Korn", "Lecker", 0.49, 0.99));
		testbestellungen = new ArrayList<Bestellung>();
		Bestellung testbestellung1 = new Bestellung(LocalDateTime.now(), produkte, kunde2);
		testbestellungen.add(testbestellung1);

	}

	/**
	 * Test method for
	 * {@link de.wwu.sopra.datenhaltung.management.Route#Route(int, de.wwu.sopra.datenhaltung.management.Fahrzeug)}.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	void testRoute() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(100);

		Route route = new Route(fzeug);

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
		Fahrzeug fzeug = new Fahrzeug(100);

		Route route = new Route(fzeug);
		int nummer = route.getRoutenNummer();
		assertEquals(route.getRoutenNummer(), nummer);
	}

	/**
	 * Test method for
	 * {@link de.wwu.sopra.datenhaltung.management.Route#setRoutenNummer(int)}.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	void testSetRoutenNummer() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(100);

		Route route = new Route(fzeug);
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
		Fahrzeug fzeug = new Fahrzeug(100);
		int temp = fzeug.getFahrzeugNummer();

		Route route = new Route(fzeug);

		assertTrue(route.getFahrzeug() instanceof Fahrzeug);
		assertTrue(route.getFahrzeug().getFahrzeugNummer() == temp);
		assertEquals(route.getFahrzeug().getKapazitaet(), 100);
	}

	/**
	 * Test zur Routenerstellung mit Fahrzeug bereits mit Route
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test
	void testRoutenerstellungMitFahrzeugMitRoute() throws IllegalArgumentException {
		Fahrzeug fzeug = new Fahrzeug(100);

		Route routeFirst = new Route(fzeug);
		Route routeSecond = new Route(fzeug);

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
		Fahrzeug fzeug = new Fahrzeug(100);
		FahrzeugRegister.addFahrzeug(fzeug);
		Fahrzeug fzeug2 = new Fahrzeug(200);
		FahrzeugRegister.addFahrzeug(fzeug2);

		Route routeFirst = new Route(fzeug);

		@SuppressWarnings("unused")
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
			Route routeSecond = new Route(fzeug2);
			routeSecond.setRoutenNummer(routeFirst.getRoutenNummer());
		});
	}

	/**
	 * Test fuer Bestellungen einer Route
	 */
	@Test
	void testBestellungen() {
		Kunde kunde1 = new Kunde("Beton", "1234", "hart@test.de", "Abstiege 1", "Zementa", "test", "test");
		ArrayList<Produkt> produkte = new ArrayList<Produkt>();
		produkte.add(new Produkt("Cola", "Lecker", 0.99, 1.29));
		produkte.add(new Produkt("Cola", "Lecker", 0.99, 1.29));
		Lager.produktZumSortimentHinzufuegen(new Produkt("Cola", "Lecker", 0.49, 0.99));
		Fahrzeug fzeug3 = new Fahrzeug(200);
		Bestellung bestellung = new Bestellung(null, produkte, kunde1);
		ArrayList<Bestellung> bestellungen = new ArrayList<Bestellung>();
		bestellungen.add(bestellung);
		assertThrows(AssertionError.class, () -> {
			Fahrzeug fzeug = new Fahrzeug(1);
			Route routeSecond = new Route(fzeug);
			routeSecond.setBestellungen(bestellungen);
		});
		Route routeFirst = new Route(fzeug3);
		routeFirst.setBestellungen(bestellungen);
		assertTrue(routeFirst.getBestellungen().contains(bestellung));
		for (Bestellung b : bestellungen) {
			assertTrue(b.getStatus().equals(BestellStatus.IN_BEARBEITUNG));
		}
	}
}

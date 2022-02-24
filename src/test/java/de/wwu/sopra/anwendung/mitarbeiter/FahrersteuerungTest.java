package de.wwu.sopra.anwendung.mitarbeiter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.benutzer.Fahrer;
import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.bestellung.BestellStatus;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import de.wwu.sopra.datenhaltung.management.FahrzeugStatus;
import de.wwu.sopra.datenhaltung.management.Produkt;
import de.wwu.sopra.datenhaltung.management.Route;

/**
 * test zum Fahrersteuerung
 * 
 * @author Johannes Thiel
 *
 */
public class FahrersteuerungTest {

	Fahrer fahrer;
	List<Bestellung> bestellungen;
	Kunde kunde;
	Bestellung testbestellung;
	Bestellung testbestellung1;
	List<Produkt> produkte;

	// vor jedem Test
	@BeforeEach
	public void init() {
		fahrer = new Fahrer("killerman", "passwort", "123@online.de", "ostbad 1", "Herbert", "schulze", "123", null);

		bestellungen = new ArrayList<Bestellung>();
		kunde = new Kunde("Beton", "1234", "hart@test.de", "Abstiege 1", "Zementa", "test", "test");
		produkte = new ArrayList<Produkt>();
		produkte.add(new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29));
		testbestellung = new Bestellung(1, LocalDateTime.now(), produkte, kunde);
		testbestellung1 = new Bestellung(2, LocalDateTime.now(), produkte, kunde);
		bestellungen.add(testbestellung);
		bestellungen.add(testbestellung1);

	}

	// Test von testFahrerZuordnen
	@Test
	public void testFahrzeugZuordnen() {

		// Testobjekte
		Fahrersteuerung steuerung = new Fahrersteuerung(fahrer);
		Fahrzeug fahrzeug = new Fahrzeug(10124123, 100);
		Route route = new Route(24136, fahrzeug);

		fahrer.setFahrzeug(fahrzeug);
		fahrzeug.setStatus(FahrzeugStatus.BELEGT);

		// wenn der fahrer schon ein Fahrzeug hat
		assertThrows(NullPointerException.class, () -> {
			steuerung.fahrzeugZuordnen(fahrzeug);
		});

		// wenn das Fahrzeug keine Route hat
		fahrer.setFahrzeug(null);
		fahrzeug.setStatus(FahrzeugStatus.FREI);
		assertThrows(NullPointerException.class, () -> {
			steuerung.fahrzeugZuordnen(fahrzeug);
		});

		// Normaler durchlauf
		fahrzeug.setStatus(FahrzeugStatus.BELEGT);
		steuerung.fahrzeugZuordnen(fahrzeug);
		assertTrue(fahrer.getFahrzeug().equals(fahrzeug));
		assertTrue(fahrzeug.getFahrer().equals(fahrer));
		assertTrue(fahrzeug.getStatus().equals(FahrzeugStatus.IN_ZUSTELLUNG));
	}

	// Test von Route Ausgeben
	@Test
	public void testRouteAusgeben() {

		// Testobjekte
		Fahrersteuerung steuerung = new Fahrersteuerung(fahrer);
		Fahrzeug fahrzeug = new Fahrzeug(100124124, 100);
		Route route = new Route(1241, fahrzeug);

		// test der korrektheit der ausggebenen Route
		steuerung.fahrzeugZuordnen(fahrzeug);
		assertTrue(steuerung.routeAusgeben().equals(route));
	}

	// Test von Kunde ist nicht da
	@Test
	public void testKundeNichtDa() {

		// Testobjekte
		Fahrersteuerung steuerung = new Fahrersteuerung(fahrer);
		Fahrzeug fahrzeug = new Fahrzeug(103124, 100);
		Route route = new Route(5124, fahrzeug);
		route.setBestellungen(bestellungen);
		steuerung.fahrzeugZuordnen(fahrzeug);
		steuerung.kundeNichtDa();

		// Normalfall
		assertTrue(testbestellung.getStatus().equals(BestellStatus.STORNIERT));
		steuerung.kundeNichtDa();

		// schon alle bestellungen ausgeliefert
		assertThrows(NullPointerException.class, () -> {
			steuerung.kundeNichtDa();
		});

	}

	// Tets von positioin ausgeben
	@Test
	public void testPositionDesfahrzeuges() {

		// testobjekte
		Fahrersteuerung steuerung = new Fahrersteuerung(fahrer);
		Fahrzeug fahrzeug = new Fahrzeug(1041231, 100);
		Route route = new Route(101231, fahrzeug);
		route.setBestellungen(bestellungen);
		steuerung.fahrzeugZuordnen(fahrzeug);

		// ueberpruefen
		assertTrue(steuerung.positionDesFahrzeugs().equals("Abstiege 1"));
	}

	// Test von Persoenlich daten Bearbeiten und anzeigen
	@Test
	public void testPersoenlicheDatenBearbeiten() {

		// testobjekte
		Fahrersteuerung steuerung = new Fahrersteuerung(fahrer);
		steuerung.persoenlicheDatenBearbeiten("1", "1", "1", "1", "1", "1", "1");
		String wunsch = "1;1;1;1;1;1;1;";
		String ergebnis = steuerung.persoenlicheDatenAnzeigen();

		// ueberpruefen
		assertTrue(ergebnis.equals(wunsch));
	}

	// Test von BestellungAusliefern()
	@Test
	public void testBestellungAusliefern() {

		// testobjekte
		Fahrersteuerung steuerung = new Fahrersteuerung(fahrer);
		Fahrzeug fahrzeug = new Fahrzeug(1231109, 100);
		Route route = new Route(61323, fahrzeug);
		route.setBestellungen(bestellungen);
		steuerung.fahrzeugZuordnen(fahrzeug);
		steuerung.bestellungAusliefern();
		// normalfall testen
		assertTrue(steuerung.getAktuelleBestellung() == 1);
		assertTrue(bestellungen.get(steuerung.getAktuelleBestellung() - 1).getStatus() == BestellStatus.ABGESCHLOSSEN);
		assertTrue(bestellungen.get(steuerung.getAktuelleBestellung() - 1).getBestellnummer() == 1);
		assertTrue(bestellungen.get(steuerung.getAktuelleBestellung()).getBestellnummer() == 2);
		steuerung.bestellungAusliefern();

		// wenn alle bestellunfen schon asugeliefert sind
		assertThrows(NullPointerException.class, () -> {
			steuerung.bestellungAusliefern();
		});
	}

	// Test von RouteAbschliesen

	@Test
	public void testrouteAbschliesen() {

		// testobjekte
		Fahrersteuerung steuerung = new Fahrersteuerung(fahrer);
		Fahrzeug fahrzeug = new Fahrzeug(123109, 100);
		Route route = new Route(6123, fahrzeug);
		route.setBestellungen(bestellungen);
		steuerung.fahrzeugZuordnen(fahrzeug);

		// route abschliesen wenn noch nicht zu ende
		assertThrows(IllegalArgumentException.class, () -> {
			steuerung.routeAbschliesen();
		});
		steuerung.bestellungAusliefern();
		steuerung.bestellungAusliefern();
		steuerung.routeAbschliesen();

		// test des abschliesens
		assertTrue(fahrzeug.getFahrer() == null);
		assertTrue(fahrzeug.getStatus().equals(FahrzeugStatus.FREI));
		assertTrue(fahrzeug.getRoute() == null);
		assertTrue(fahrer.getFahrzeug() == null);
	}
}

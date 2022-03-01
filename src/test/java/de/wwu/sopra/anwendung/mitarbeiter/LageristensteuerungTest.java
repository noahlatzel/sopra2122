package de.wwu.sopra.anwendung.mitarbeiter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.bestellung.BestellStatus;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.bestellung.IdZaehler;
import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import de.wwu.sopra.datenhaltung.management.FahrzeugStatus;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Produkt;
import de.wwu.sopra.datenhaltung.management.Route;
import de.wwu.sopra.datenhaltung.management.Statistiken;
import de.wwu.sopra.datenhaltung.verwaltung.BenutzerRegister;
import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;
import de.wwu.sopra.datenhaltung.verwaltung.GrosshaendlerRegister;

public class LageristensteuerungTest {
	Lageristensteuerung lageristenSteuerung;
	NachbestellungTupel nachbestellung1;
	Produkt produkt1;
	NachbestellungTupel nachbestellung2;
	Produkt produkt2;
	HashSet<NachbestellungTupel> nachbestellungen;
	Statistiken statistiken = new Statistiken();

	@BeforeEach
	void init() {
		lageristenSteuerung = new Lageristensteuerung(new Lager(), statistiken);
		produkt1 = new Produkt("Cola", "Lecker", 0.99, 1.29);
		produkt2 = new Produkt("Fanta", "Lecker", 0.99, 1.29);
		GrosshaendlerRegister.setPreis(produkt1, 0.99);
		GrosshaendlerRegister.setPreis(produkt2, 0.99);
		nachbestellung1 = new NachbestellungTupel(produkt1, 5);
		nachbestellung2 = new NachbestellungTupel(produkt2, 2);
		nachbestellungen = new HashSet<NachbestellungTupel>();
		nachbestellungen.add(nachbestellung1);
		nachbestellungen.add(nachbestellung2);
	}

	/**
	 * Testet den Konstruktor fuer die Klassen NachbestellungTupel
	 */
	@Test
	void testTupel() {
		assertTrue(nachbestellung1.getMenge() == 5);
		assertTrue(nachbestellung1.getProdukt().equals(produkt1));
	}

	/**
	 * Testet die Funktion bestelleNach().
	 */
	@Test
	void testBestelleNach() {

		// preisRegister.setPreis(produkt1, 0.99);
		// preisRegister.setPreis(produkt2, 0.99);
		lageristenSteuerung.bestelleNach(nachbestellungen);
		assertTrue(lageristenSteuerung.getLager().getProduktBestand("Fanta") == 2);
		assertTrue(lageristenSteuerung.getLager().getProduktBestand("Cola") == 5);
	}

	/**
	 * Testet die Funktion planeRoute().
	 */
	@Test
	void testPlaneRoute() {
		Fahrzeug fahrzeug = new Fahrzeug(13234, 10);
		ArrayList<Produkt> produkte1 = new ArrayList<Produkt>();
		produkte1.add(new Produkt("Cola", "Lecker", 0.99, 1.29));
		produkte1.add(new Produkt("Cola", "Lecker", 0.99, 1.29));
		produkte1.add(new Produkt("Cola", "Lecker", 0.99, 1.29));
		ArrayList<Produkt> produkte2 = new ArrayList<Produkt>();
		produkte2.add(new Produkt("Fanta", "Lecker", 0.99, 1.29));
		produkte2.add(new Produkt("Fanta", "Lecker", 0.99, 1.29));

		Kunde kunde = new Kunde("kunde", "666", "email69", "Kassel", "UnfassbarerVorname", "EinwandfreierNachname",
				"KapitalistenBankverbindung");
		Bestellung bestellung1 = new Bestellung(0, null, produkte1, kunde);
		Bestellung bestellung2 = new Bestellung(1, null, produkte2, kunde);

		ArrayList<Bestellung> bestellungen = new ArrayList<Bestellung>();
		bestellungen.add(bestellung1);
		bestellungen.add(bestellung2);

		lageristenSteuerung.planeRoute(bestellungen, fahrzeug);
		assertTrue(fahrzeug.getRoute().getBestellungen().equals(bestellungen));
		assertTrue(lageristenSteuerung.zeigeRouteVonFahrzeug(fahrzeug).getBestellungen().equals(bestellungen));
	}

	/**
	 * Testet die Fehlerbehandlung fuer planeRoute(). Zu geringe Kapazitaet des
	 * Fahrzeugs.
	 */
	@Test
	void testThrowsPlaneRoute() {
		Fahrzeug fahrzeug = new Fahrzeug(111, 2);
		ArrayList<Produkt> produkte1 = new ArrayList<Produkt>();
		produkte1.add(new Produkt("Cola", "Lecker", 0.99, 1.29));
		produkte1.add(new Produkt("Cola", "Lecker", 0.99, 1.29));
		produkte1.add(new Produkt("Cola", "Lecker", 0.99, 1.29));
		ArrayList<Produkt> produkte2 = new ArrayList<Produkt>();
		produkte2.add(new Produkt("Fanta", "Lecker", 0.99, 1.29));
		produkte2.add(new Produkt("Fanta", "Lecker", 0.99, 1.29));

		Kunde kunde = new Kunde("kunde", "666", "email69", "Kassel", "UnfassbarerVorname", "EinwandfreierNachname",
				"KapitalistenBankverbindung");
		Bestellung bestellung1 = new Bestellung(2, null, produkte1, kunde);
		Bestellung bestellung2 = new Bestellung(3, null, produkte2, kunde);

		ArrayList<Bestellung> bestellungen = new ArrayList<Bestellung>();
		bestellungen.add(bestellung1);
		bestellungen.add(bestellung2);

		assertThrows(IllegalArgumentException.class, () -> {
			lageristenSteuerung.planeRoute(bestellungen, fahrzeug);
		});
	}

	/**
	 * Testet die Fehlerbehandlung von ZeigeRouteVonFahrzeug.
	 */
	@Test
	void testThrowsZeigeRouteVonFahrzeug() {
		Fahrzeug fahrzeug = new Fahrzeug(919, 2);
		assertThrows(IllegalArgumentException.class, () -> {
			lageristenSteuerung.zeigeRouteVonFahrzeug(fahrzeug);
		});
	}

	/**
	 * Testet zeigeOffeneBestellungen
	 */
	@Test
	void testOffeneBestellungen() {
		Kunde kunde1 = new Kunde("Beton", "1234", "hart@test.de", "Abstiege 1", "Zementa", "test", "test");
		Kunde kunde2 = new Kunde("Bierman", "1234", "hart@test.de", "Destille", "Maxi", "malvoll", "test");

		List<Produkt> produkte = new ArrayList<Produkt>();
		Produkt cola = new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29);
		Produkt bier = new Produkt("Krombacher Pils", "Eine Perle der Natur", 0.99, 1.96);
		Produkt korn = new Produkt("Sasse Korn", "LEEEECKER", 4.20, 6.66);
		produkte.add(bier);
		produkte.add(cola);
		produkte.add(korn);

		Bestellung testbestellung1 = new Bestellung(IdZaehler.getBestellungsId(), LocalDateTime.now(), produkte,
				kunde2);
		Bestellung testbestellung2 = new Bestellung(IdZaehler.getBestellungsId(), LocalDateTime.now(), produkte,
				kunde1);

		testbestellung1.setStatus(BestellStatus.ABGESCHLOSSEN);
		testbestellung2.setStatus(BestellStatus.OFFEN);

		BenutzerRegister.benutzerHinzufuegen(kunde2);
		BenutzerRegister.bestellungZuBestellungslisteHinzufuegen(kunde2, testbestellung2);
		BenutzerRegister.benutzerHinzufuegen(kunde1);
		BenutzerRegister.bestellungZuBestellungslisteHinzufuegen(kunde1, testbestellung1);

		assertTrue(lageristenSteuerung.zeigeOffeneBestellungen().contains(testbestellung2));
		assertTrue(lageristenSteuerung.zeigeOffeneBestellungen().size() == 1);

	}

	/**
	 * Testet zeigeFreieFahrzeuge
	 */
	@Test
	void testFreieFahrzeuge() {
		Fahrzeug fahrzeug = new Fahrzeug(920, 2);
		Fahrzeug fahrzeug1 = new Fahrzeug(921, 2);
		fahrzeug1.setStatus(FahrzeugStatus.BELEGT);
		FahrzeugRegister.addFahrzeug(fahrzeug1);
		FahrzeugRegister.addFahrzeug(fahrzeug);
		assertTrue(lageristenSteuerung.zeigeFreieFahrzeuge().contains(fahrzeug));
		assertTrue(lageristenSteuerung.zeigeFreieFahrzeuge().size() == 1);
		FahrzeugRegister.removeFahrzeug(fahrzeug);
	}

	@Test
	void testBelegteFahrzeuge() {
		ArrayList<Produkt> produkte1 = new ArrayList<Produkt>();
		produkte1.add(new Produkt("Cola", "Lecker", 0.99, 1.29));
		produkte1.add(new Produkt("Cola", "Lecker", 0.99, 1.29));
		produkte1.add(new Produkt("Cola", "Lecker", 0.99, 1.29));
		Kunde kunde2 = new Kunde("Bierman", "1234", "hart@test.de", "Destille", "Maxi", "malvoll", "test");
		Bestellung testbestellung1 = new Bestellung(IdZaehler.getBestellungsId(), LocalDateTime.now(), produkte1,
				kunde2);
		ArrayList<Bestellung> bestellungen = new ArrayList<Bestellung>();
		bestellungen.add(testbestellung1);
		Fahrzeug fahrzeug = new Fahrzeug(90022, 2);
		Fahrzeug fahrzeug1 = new Fahrzeug(92003, 3);
		FahrzeugRegister.addFahrzeug(fahrzeug1);
		FahrzeugRegister.addFahrzeug(fahrzeug);
		Route route = new Route(110, fahrzeug1);
		route.setBestellungen(bestellungen);
		assertTrue(lageristenSteuerung.getFahrzeugeMitRoute().contains(fahrzeug1));
		assertTrue(lageristenSteuerung.zeigeFreieFahrzeuge().size() == 1);
	}
}

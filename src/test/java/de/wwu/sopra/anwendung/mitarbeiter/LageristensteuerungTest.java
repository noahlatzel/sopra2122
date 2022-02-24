package de.wwu.sopra.anwendung.mitarbeiter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Produkt;

public class LageristensteuerungTest {
	LageristenSteuerung lageristenSteuerung;
	NachbestellungTupel nachbestellung1;
	Produkt produkt1;
	NachbestellungTupel nachbestellung2;
	Produkt produkt2;
	HashSet<NachbestellungTupel> nachbestellungen;

	@BeforeEach
	void init() {
		lageristenSteuerung = new LageristenSteuerung(new Lager());
		produkt1 = new Produkt("Cola", "Lecker", 0.99, 1.29);
		produkt2 = new Produkt("Fanta", "Lecker", 0.99, 1.29);
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
	 * 
	 */
}

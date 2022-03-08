package de.wwu.sopra.anwendung.mitarbeiter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.benutzer.Lagerist;
import de.wwu.sopra.datenhaltung.bestellung.BestellStatus;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import de.wwu.sopra.datenhaltung.management.FahrzeugStatus;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Produkt;
import de.wwu.sopra.datenhaltung.management.Route;
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

	@AfterEach
	void cleanAfter() {
		Lager.reset();
		FahrzeugRegister.reset();
	}

	@BeforeEach
	void init() {
		Lager.reset();
		FahrzeugRegister.reset();
		lageristenSteuerung = new Lageristensteuerung(new Lagerist("noahlatzel", "123", "nlatzel@uni-muenster.de",
				"Muenster", "Noah", "Latzel", "GuteBank", null));
		Lager.produktZumSortimentHinzufuegen(new Produkt("Coca Cola", "Lecker", 0.99, 1.29));
		// Lager.produktZumSortimentHinzufuegen(new Produkt("Fanta", "Lecker", 0.49,
		// 0.99));
		Lager.produktZumSortimentHinzufuegen(new Produkt("Krombacher Pils", "Lecker", 0.49, 0.99));
		Lager.produktZumSortimentHinzufuegen(new Produkt("Sasse Korn", "Lecker", 0.49, 0.99));
		Lager.produktZumSortimentHinzufuegen(new Produkt("Cola", "Lecker", 0.49, 0.99));

		produkt1 = new Produkt("Coca Cola", "Lecker", 0.99, 1.29);
		produkt2 = new Produkt("Cola", "Lecker", 0.99, 1.29);
		GrosshaendlerRegister.setEinkaufspreis(produkt1, 0.99);
		GrosshaendlerRegister.setEinkaufspreis(produkt2, 0.99);
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
		List<Produkt> temp = (List<Produkt>) Lager.getLager();
		for (Produkt p : temp) {
			Lager.removeProdukt(p);
		}
		Lager.addProdukt(produkt2);
		Lager.addProdukt(produkt1);
		int anzahl_cola = Lager.getProduktBestand("Coca Cola");
		int anzahl_fanta = Lager.getProduktBestand("Cola");
		lageristenSteuerung.bestelleNach(nachbestellungen);
		assertTrue(Lager.getProduktBestand("Coca Cola") == anzahl_cola + 5);
		assertTrue(Lager.getProduktBestand("Cola") == anzahl_fanta + 2);
		assertThrows(AssertionError.class, () -> {
			lageristenSteuerung.bestelleNach(new HashSet<NachbestellungTupel>());
		});
	}

	/**
	 * Testet die Funktion planeRoute().
	 */
	@Test
	void testPlaneRoute() {
		Fahrzeug fahrzeug = new Fahrzeug(10);
		ArrayList<Produkt> produkte1 = new ArrayList<Produkt>();
		produkte1.add(new Produkt("Cola", "Lecker", 0.99, 1.29));
		produkte1.add(new Produkt("Cola", "Lecker", 0.99, 1.29));
		produkte1.add(new Produkt("Cola", "Lecker", 0.99, 1.29));
		ArrayList<Produkt> produkte2 = new ArrayList<Produkt>();
		produkte2.add(new Produkt("Coca Cola", "Lecker", 0.99, 1.29));
		produkte2.add(new Produkt("Coca Cola", "Lecker", 0.99, 1.29));

		Kunde kunde = new Kunde("kunde", "666", "email69", "Kassel", "UnfassbarerVorname", "EinwandfreierNachname",
				"KapitalistenBankverbindung");
		Bestellung bestellung1 = new Bestellung(null, produkte1, kunde);
		Bestellung bestellung2 = new Bestellung(null, produkte2, kunde);

		ArrayList<Bestellung> bestellungen = new ArrayList<Bestellung>();
		bestellungen.add(bestellung1);
		bestellungen.add(bestellung2);

		lageristenSteuerung.planeRoute(bestellungen, fahrzeug);
		assertTrue(fahrzeug.getRoute().getBestellungen().equals(bestellungen));
		assertTrue(lageristenSteuerung.zeigeRouteVonFahrzeug(fahrzeug).getBestellungen().equals(bestellungen));
		assertThrows(AssertionError.class, () -> {
			lageristenSteuerung.planeRoute(bestellungen, fahrzeug);
		});
		assertThrows(AssertionError.class, () -> {
			lageristenSteuerung.planeRoute(new ArrayList<Bestellung>(), new Fahrzeug(20));
		});
		assertTrue(lageristenSteuerung.getSortiment().equals(Lager.getLagerbestand().keySet()));
	}

	/**
	 * Testet die Fehlerbehandlung fuer planeRoute(). Zu geringe Kapazitaet des
	 * Fahrzeugs.
	 */
	@Test
	void testThrowsPlaneRoute() {
		Fahrzeug fahrzeug = new Fahrzeug(2);
		ArrayList<Produkt> produkte1 = new ArrayList<Produkt>();
		produkte1.add(new Produkt("Cola", "Lecker", 0.99, 1.29));
		produkte1.add(new Produkt("Cola", "Lecker", 0.99, 1.29));
		produkte1.add(new Produkt("Cola", "Lecker", 0.99, 1.29));
		ArrayList<Produkt> produkte2 = new ArrayList<Produkt>();
		produkte2.add(new Produkt("Coca Cola", "Lecker", 0.99, 1.29));
		produkte2.add(new Produkt("Coca Cola", "Lecker", 0.99, 1.29));

		Kunde kunde = new Kunde("kunde", "666", "email69", "Kassel", "UnfassbarerVorname", "EinwandfreierNachname",
				"KapitalistenBankverbindung");

		Bestellung bestellung1 = new Bestellung(null, produkte1, kunde);
		Bestellung bestellung2 = new Bestellung(null, produkte2, kunde);

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
		Fahrzeug fahrzeug = new Fahrzeug(2);
		assertThrows(AssertionError.class, () -> {
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

		Bestellung testbestellung1 = new Bestellung(LocalDateTime.now(), produkte, kunde2);
		Bestellung testbestellung2 = new Bestellung(LocalDateTime.now(), produkte, kunde1);

		testbestellung1.setStatus(BestellStatus.ABGESCHLOSSEN);
		testbestellung2.setStatus(BestellStatus.OFFEN);

		BenutzerRegister.benutzerHinzufuegen(kunde2);
		BenutzerRegister.bestellungZuBestellungslisteHinzufuegen(kunde2, testbestellung1);
		BenutzerRegister.benutzerHinzufuegen(kunde1);
		BenutzerRegister.bestellungZuBestellungslisteHinzufuegen(kunde1, testbestellung2);

		assertTrue(lageristenSteuerung.zeigeOffeneBestellungen().contains(testbestellung2));
		assertTrue(lageristenSteuerung.zeigeOffeneBestellungen().size() == 1);

	}

	/**
	 * Testet zeigeFreieFahrzeuge
	 */
	@Test
	void testFreieFahrzeuge() {
		HashSet<Fahrzeug> temp = (HashSet<Fahrzeug>) FahrzeugRegister.getFahrzeuge().clone();
		for (Fahrzeug f : temp) {
			FahrzeugRegister.removeFahrzeug(f);
		}
		Fahrzeug fahrzeug = new Fahrzeug(2);
		Fahrzeug fahrzeug1 = new Fahrzeug(2);
		fahrzeug1.setStatus(FahrzeugStatus.BELEGT);
		FahrzeugRegister.addFahrzeug(fahrzeug1);
		FahrzeugRegister.addFahrzeug(fahrzeug);
		assertTrue(lageristenSteuerung.zeigeFreieFahrzeuge().contains(fahrzeug));
		assertTrue(lageristenSteuerung.zeigeFreieFahrzeuge().size() == 1);
		FahrzeugRegister.removeFahrzeug(fahrzeug);
	}

	@Test
	void testBelegteFahrzeuge() {
		HashSet<Fahrzeug> temp = FahrzeugRegister.getFahrzeuge();
		for (Fahrzeug f : temp) {
			FahrzeugRegister.removeFahrzeug(f);
		}
		ArrayList<Produkt> produkte1 = new ArrayList<Produkt>();
		produkte1.add(new Produkt("Cola", "Lecker", 0.99, 1.29));
		produkte1.add(new Produkt("Cola", "Lecker", 0.99, 1.29));
		produkte1.add(new Produkt("Cola", "Lecker", 0.99, 1.29));
		Kunde kunde2 = new Kunde("Bierman", "1234", "hart@test.de", "Destille", "Maxi", "malvoll", "test");
		Bestellung testbestellung1 = new Bestellung(LocalDateTime.now(), produkte1, kunde2);
		ArrayList<Bestellung> bestellungen = new ArrayList<Bestellung>();
		bestellungen.add(testbestellung1);
		Fahrzeug fahrzeug = new Fahrzeug(20);
		Fahrzeug fahrzeug1 = new Fahrzeug(20);
		FahrzeugRegister.addFahrzeug(fahrzeug1);
		FahrzeugRegister.addFahrzeug(fahrzeug);
		Route route = new Route(fahrzeug1);
		route.setBestellungen(bestellungen);
		assertTrue(lageristenSteuerung.getFahrzeugeMitRoute().contains(fahrzeug1));
		assertTrue(lageristenSteuerung.zeigeFreieFahrzeuge().size() == 1);
	}

	// Test von Persoenlich daten Bearbeiten und anzeigen
	@Test
	public void testPersoenlicheDatenBearbeiten() {

		lageristenSteuerung.persoenlicheDatenBearbeiten("1", "1", "1", "1", "1", "1", "1");
		String wunsch = "1;1;1;1;1;1;1;";
		String ergebnis = lageristenSteuerung.persoenlicheDatenAnzeigen();

		// ueberpruefen
		assertTrue(ergebnis.equals(wunsch));
		assertThrows(AssertionError.class, () -> {
			lageristenSteuerung.persoenlicheDatenBearbeiten("", "1", "1", "1", "1", "1", "1");
		});
		assertThrows(AssertionError.class, () -> {
			lageristenSteuerung.persoenlicheDatenBearbeiten("1", "", "1", "1", "1", "1", "1");
		});
		assertThrows(AssertionError.class, () -> {
			lageristenSteuerung.persoenlicheDatenBearbeiten("1", "1", "", "1", "1", "1", "1");
		});
		assertThrows(AssertionError.class, () -> {
			lageristenSteuerung.persoenlicheDatenBearbeiten("1", "1", "1", "", "1", "1", "1");
		});
		assertThrows(AssertionError.class, () -> {
			lageristenSteuerung.persoenlicheDatenBearbeiten("1", "1", "1", "1", "", "1", "1");
		});
		assertThrows(AssertionError.class, () -> {
			lageristenSteuerung.persoenlicheDatenBearbeiten("1", "1", "1", "1", "1", "", "1");
		});
		assertThrows(AssertionError.class, () -> {
			lageristenSteuerung.persoenlicheDatenBearbeiten("1", "1", "1", "1", "1", "1", "");
		});
	}
}

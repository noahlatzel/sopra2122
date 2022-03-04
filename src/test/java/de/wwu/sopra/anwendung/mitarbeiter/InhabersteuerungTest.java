package de.wwu.sopra.anwendung.mitarbeiter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.benutzer.Benutzer;
import de.wwu.sopra.datenhaltung.benutzer.Fahrer;
import de.wwu.sopra.datenhaltung.benutzer.Inhaber;
import de.wwu.sopra.datenhaltung.benutzer.Lagerist;
import de.wwu.sopra.datenhaltung.benutzer.Rolle;
import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import de.wwu.sopra.datenhaltung.management.Kategorie;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Produkt;
import de.wwu.sopra.datenhaltung.management.Statistiken;
import de.wwu.sopra.datenhaltung.verwaltung.BenutzerRegister;
import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;

public class InhabersteuerungTest {
	String benutzername;
	String passwort;
	String email;
	String adresse;
	String vorname;
	String name;
	String bankverbindung;
	Inhaber inhaber;
	Inhabersteuerung ihs;

	@BeforeEach
	void reset() {
		Lager.reset();
		FahrzeugRegister.reset();
	}

	@BeforeEach
	void init() {
		benutzername = "JackTheBoss";
		passwort = "supersicherespasswort123!";
		email = "cuzimapirate@online.de";
		adresse = "En su casa 12, 15087 Lima, Peru";
		vorname = "Jack";
		name = "Sparrow";
		bankverbindung = "DE00 0000 0000 0000 00";
		BenutzerRegister.getBenutzerListe().clear();
		inhaber = new Inhaber(benutzername, passwort, email, adresse, vorname, name, bankverbindung);
		ihs = new Inhabersteuerung(inhaber);
	}

	/**
	 * Testet das Erstellen, Bearbeiten und Loeschen eines Mitarbeiters
	 */
	@Test
	void testMitarbeiterVerwaltung() throws IllegalArgumentException, NullPointerException {
		// Erstellung Lagerist-Objekt

		ihs.mitarbeiterRegistrieren("lagerista", "notatpirate", "everglow@online.de",
				"No en mi casa 23, 15086 Lima, Peru", "Mia", "Miga", "DE11 1111 1111 1111 11", Rolle.LAGERIST);
		assertTrue(inhaber.getLageristen().size() > 0);
		Lagerist neuerLagerist = inhaber.getLageristen().get(0);
		assertEquals(neuerLagerist.getBenutzername(), "lagerista");
		assertEquals(neuerLagerist.getVorname(), "Mia");
		assertEquals(neuerLagerist.getName(), "Miga");

		assertThrows(IllegalArgumentException.class, () -> {
			ihs.mitarbeiterRegistrieren("", "notatpirate", "everglow@online.de", "No en mi casa 23, 15086 Lima, Peru",
					"Mia", "Miga", "DE11 1111 1111 1111 11", Rolle.LAGERIST);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			ihs.mitarbeiterRegistrieren("Test", "notatpirate", "everglow@online.de",
					"No en mi casa 23, 15086 Lima, Peru", "Mia", "Miga", "DE11 1111 1111 1111 11", Rolle.INHABER);
		});

		// Bearbeitung Lagerist-Objekt

		ihs.mitarbeiterDatenAendern(neuerLagerist, "lagerista", "notapirate", "everglow@online.dee",
				"No en mi casa 24, 15086 Lima, Peru", "Miaa", "Migaa", "PR11 1111 1111 1111 11");
		assertEquals(neuerLagerist.getBenutzername(), "lagerista");
		assertEquals(neuerLagerist.getPasswort(), "notapirate");

		assertThrows(IllegalArgumentException.class, () -> {
			ihs.mitarbeiterDatenAendern(neuerLagerist, "lagerista", "", "everglow@online.de",
					"No en mi casa 23, 15086 Lima, Peru", "Mia", "Miga", "DE11 1111 1111 1111 11");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			ihs.mitarbeiterDatenAendern(null, "lagerista", "", "everglow@online.de",
					"No en mi casa 23, 15086 Lima, Peru", "Mia", "Miga", "DE11 1111 1111 1111 11");
		});
		assertThrows(NullPointerException.class, () -> {
			ihs.mitarbeiterDatenAendern(null, "lagerista", "a", "everglow@online.de",
					"No en mi casa 23, 15086 Lima, Peru", "Mia", "Miga", "DE11 1111 1111 1111 11");
		});

		// Loeschen Lagerist-Objekt

		ihs.mitarbeiterLoeschen(neuerLagerist);
		assertTrue(inhaber.getLageristen().size() == 0);

		assertThrows(NullPointerException.class, () -> {
			ihs.mitarbeiterLoeschen(null);
		});

		// Erstellung Fahrer-Objekt

		ihs.mitarbeiterRegistrieren("fahrer1", "alsonotatpirate", "everglow@online.de", "Eleven 22, 15085 Lima, Peru",
				"Leo", "Diario", "DE22 2222 2222 2222 22", Rolle.FAHRER);
		assertTrue(inhaber.getFahrer().size() > 0);
		Fahrer neuerFahrer = inhaber.getFahrer().get(0);
		assertEquals(neuerFahrer.getBenutzername(), "fahrer1");
		assertEquals(neuerFahrer.getVorname(), "Leo");
		assertEquals(neuerFahrer.getName(), "Diario");

		// Bearbeitung Fahrer-Objekt

		ihs.mitarbeiterDatenAendern(neuerFahrer, "fahrer", "alsonotatpirate", "ateez@online.de",
				"Eleven 22, 15085 Lima, Peru", "Leo", "Diario", "DE22 2222 2222 2222 22");
		assertEquals(neuerFahrer.getBenutzername(), "fahrer");
		assertEquals(neuerFahrer.getEmail(), "ateez@online.de");

		// Loeschen Fahrer-Objekt

		ihs.mitarbeiterLoeschen(neuerFahrer);
		assertTrue(inhaber.getFahrer().size() == 0);
	}

	/**
	 * Testet die Verwaltung von Fahrzeugdaten
	 */
	@Test
	void testFahrzeugeVerwalten() {
		HashSet<Fahrzeug> fahrzeugee = (HashSet<Fahrzeug>) FahrzeugRegister.getFahrzeuge().clone();
		for (Fahrzeug fzeug : fahrzeugee) {
			FahrzeugRegister.removeFahrzeug(fzeug);
		}

		// Erstellung von Fahrzeuge
		Fahrzeug fzeug1 = new Fahrzeug(321894215);
		Fahrzeug fzeug2 = new Fahrzeug(52);
		Fahrzeug fzeug3 = new Fahrzeug(1000);

		FahrzeugRegister.addFahrzeug(fzeug1);
		FahrzeugRegister.addFahrzeug(fzeug2);
		FahrzeugRegister.addFahrzeug(fzeug3);

		HashSet<Fahrzeug> fahrzeuge = new HashSet<Fahrzeug>();
		fahrzeuge.add(fzeug1);
		fahrzeuge.add(fzeug2);
		fahrzeuge.add(fzeug3);

		// Testet, ob alle Fahrzeuge korrekt angezeigt werden
		assertEquals(fahrzeuge, ihs.fahrzeugeAnzeigen());

		// Fahrzeug1kapazitaet aendern
		ihs.fahrzeugDatenAendern(fzeug1, 345345, 123456789);
		assertEquals(fzeug1.getKapazitaet(), 123456789);
		assertEquals(fzeug1.getFahrzeugNummer(), 345345);
		// Testet das Loeschen von einem Fahrzeug
		ihs.fahrzeugLoeschen(fzeug3);
		assertFalse(ihs.fahrzeugeAnzeigen().contains(fzeug3));
	}

	/**
	 * Testet, ob alle Daten des Benutzers im richtigen Format zurueckgegeben werden
	 */
	@Test
	void testPersoenlicheDatenAnzeigen() {
		String persoenlicheDaten = ihs.persoenlicheDatenAnzeigen();
		String result = benutzername + ";" + passwort + ";" + email + ";" + adresse + ";" + vorname + ";" + name + ";"
				+ bankverbindung;
		assertEquals(persoenlicheDaten, result);
	}

	/**
	 * Testet, ob die Daten des Benutzers erfolgreich geaendert werden koennen
	 */
	@Test
	void testPersoenlicheDatenAendern() throws IllegalArgumentException {
		ihs.persoenlicheDatenAendern("adw", "adw", "imapirateyeahyeah@online.de", "adw", "adw", "adw", "adw");
		// Email aendern
		assertEquals(inhaber.getEmail(), "imapirateyeahyeah@online.de");

		assertThrows(IllegalArgumentException.class, () -> {
			ihs.persoenlicheDatenAendern(benutzername, passwort, "", adresse, vorname, name, bankverbindung);
		});
	}

	/**
	 * Testet, ob ein Produkt vom Inhaber bearbeitet werden kann
	 */
	@Test
	void testProduktBearbeiten() {
		Produkt producto = new Produkt("Chicha", "Peruanisch", 9.8, 9.99);
		ihs.produktBearbeiten(producto, producto.getName(), producto.getBeschreibung(), 10.99);
		assertEquals(producto.getVerkaufspreis(), 10.99);
		assertThrows(IllegalArgumentException.class, () -> {
			ihs.produktBearbeiten(producto, "", producto.getBeschreibung(), 10.99);
		});
	}

	/**
	 * Tests, dass der Inhaber Produkte zu Lager hinzufuegen und entfernen kann
	 */
	@Test
	void testLagerVerwalten() throws IllegalArgumentException {
		HashSet<Produkt> produkteLager = (HashSet<Produkt>) Lager.getLager().clone();

		System.out.println(Lager.getLagerbestand().keySet().size());

		for (Produkt p : produkteLager) {
			Lager.removeProdukt(p);
		}

		// Erstellung von Lager und Produkte
		Produkt producto = new Produkt("Chicha", "Peruanisch", 9.8, 9.99);
		Produkt product = new Produkt("Cola", "American", 5.99, 7.99);

		// Erstellung von Produkte-um-hinzufuegen Liste
		List<Produkt> productsToAdd = new ArrayList<Produkt>();
		productsToAdd.add(producto);
		productsToAdd.add(product);
		Lager.produktZumSortimentHinzufuegen(new Produkt("Cola", "Lecker", 0.49, 0.99));
		Lager.produktZumSortimentHinzufuegen(new Produkt("Chicha", "Lecker", 0.49, 0.99));
		ihs.lagerVerwalten(productsToAdd, "hinzufuegen");

		assertThrows(IllegalArgumentException.class, () -> {
			ihs.lagerVerwalten(productsToAdd, "no");
		});

		System.out.println(Lager.getLagerbestand().keySet().size());
		assertTrue(Lager.getLagerbestand().keySet().contains(product));
		assertTrue(Lager.getLagerbestand().keySet().contains(producto));

		// Erstellung von Produkte-um-loeschen Liste
		List<Produkt> productsToRemove = new ArrayList<Produkt>();
		productsToRemove.add(product);

		ihs.lagerVerwalten(productsToRemove, "loeschen");

		assertFalse(Lager.getLagerbestand().keySet().contains(product));
		assertTrue(Lager.getLagerbestand().keySet().contains(producto));
	}

	/**
	 * Testet, ob der Inhaber eine Kategorie bearbeiten kann, indem er ihren Namen
	 * aendert oder Ober- und Unterkategorien hinzufuegt und entfernt
	 */
	@Test
	void testKategorieVerwalten() throws IllegalArgumentException {
		// Erstellung von Kategorien
		Kategorie softDrinks1 = new Kategorie("Fizzy Drinks");
		Kategorie sauer1 = new Kategorie("Sauer");

		ihs.kategorieBearbeiten(softDrinks1, sauer1, "ober", null);
		assertTrue(softDrinks1.getOberkategorie().equals(sauer1));

		// Erstellung von Kategorien
		Kategorie softDrinks = new Kategorie("Fizzy Drinks");
		Kategorie sauer = new Kategorie("Sauer");

		// Unterkategorie zu ersten Kategorie hinzufuegen
		ihs.kategorieBearbeiten(softDrinks, sauer, "unter", "aaa");
		assertTrue(softDrinks.getName() == "aaa");
		assertTrue(softDrinks.getUnterkategorien().contains(sauer));

		assertThrows(IllegalArgumentException.class, () -> {
			ihs.kategorieBearbeiten(softDrinks, sauer, "aaaaaa", null);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			ihs.kategorieBearbeiten(null, null, "aaaaaa", "Soft Drinks");
		});

		// Erstellung von Produkten
		Produkt product = new Produkt("Stang", "The World's Most Sour Soda", 2.99, 3.99);
		Produkt product2 = new Produkt("Fanta", "Yummy", 1.99, 2.99);

		// Erstellung von Produkte-um-hinzufuegen Liste
		List<Produkt> productsToAdd = new ArrayList<Produkt>();
		productsToAdd.add(product);
		productsToAdd.add(product2);

		ihs.kategorieProdukteVerwalten(sauer, productsToAdd, "hinzufuegen");

		assertTrue(sauer.getProdukte().contains(product));
		assertTrue(sauer.getProdukte().contains(product2));

		assertThrows(IllegalArgumentException.class, () -> {
			ihs.kategorieProdukteVerwalten(sauer, productsToAdd, "bbbbbbbb");
		});

		// Erstellung von Produkte-um-loeschen Liste
		List<Produkt> productsToRemove = new ArrayList<Produkt>();
		productsToRemove.add(product2);

		ihs.kategorieProdukteVerwalten(sauer, productsToRemove, "loeschen");
		assertFalse(sauer.getProdukte().contains(product2));
	}

	/**
	 * Testet, ob die abgerufenen statistischen Daten im richtigen Format mit den
	 * richtigen Daten zurueckgegeben werden
	 */
	@Test
	void teststatistikenAusgeben() {

		Statistiken.setArbeitszeit(0);
		Statistiken.setAusgaben(0);
		Statistiken.setEinnahmen(0);
		Statistiken.setUmsatz(0);

		HashMap<String, Float> statistics = ihs.statistikenAusgeben();

		HashMap<String, Float> statisticsSupposedResult = new HashMap<String, Float>();
		statisticsSupposedResult.put("umsatz", (float) 0);
		statisticsSupposedResult.put("ausgaben", (float) 0);
		statisticsSupposedResult.put("einnahmen", (float) 0);
		statisticsSupposedResult.put("arbeitszeit", (float) 0);

		assertEquals(statistics, statisticsSupposedResult);
	}

	/**
	 * testet, ob die Liste mit den richtigen Daten zurueckgegeben wird
	 */
	@Test
	void testMitarbeiternAnzeigen() {
		Fahrer fahrer1 = new Fahrer("driver", "passwort", "sam@online.de", "ostbad 1", "Sam", "Winchester", "1234",
				inhaber);
		Fahrer fahrer2 = new Fahrer("conductor", "passwort", "dean@online.de", "ostbad 1", "Dean", "Winchester", "1235",
				inhaber);
		inhaber.fahrerHinzufuegen(fahrer1);
		inhaber.fahrerHinzufuegen(fahrer2);

		Lagerist lagerist1 = new Lagerist("lagerista", "passwort", "cas@online.de", "ostbad 1", "Castiel", "Angel",
				"1235", inhaber);
		inhaber.lageristHinzufuegen(lagerist1);

		List<Benutzer> mitarbeitern = new ArrayList<Benutzer>();
		mitarbeitern.add(fahrer1);
		mitarbeitern.add(fahrer2);
		mitarbeitern.add(lagerist1);

		assertEquals(mitarbeitern, ihs.mitarbeiternAnzeigen());
	}
}

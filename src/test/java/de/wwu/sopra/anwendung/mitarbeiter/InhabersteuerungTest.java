package de.wwu.sopra.anwendung.mitarbeiter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	Statistiken statistiken = new Statistiken();
	BenutzerRegister benutzerReg = new BenutzerRegister();
	
	@BeforeEach
	void init() {
		benutzername = "JackTheBoss";
		passwort = "supersicherespasswort123!";
		email = "cuzimapirate@online.de";
		adresse = "En su casa 12, 15087 Lima, Peru";
		vorname = "Jack";
		name = "Sparrow";
		bankverbindung = "DE00 0000 0000 0000 00";
		inhaber = new Inhaber(benutzername, passwort, email, adresse, vorname, name, bankverbindung);
		ihs = new Inhabersteuerung(inhaber, statistiken, benutzerReg);
	}
	
	/**
	 * Testet das Erstellen, Bearbeiten und Loeschen eines Mitarbeiters
	 */
	@Test
	void testMitarbeiterVerwaltung() {
		// Erstellung Lagerist-Objekt
		
		ihs.mitarbeiterRegistrieren("lagerista", "notatpirate", "everglow@online.de", "No en mi casa 23, 15086 Lima, Peru", "Mia", "Miga", "DE11 1111 1111 1111 11", Rolle.LAGERIST);
		assertTrue(inhaber.getLageristen().size() > 0);
		Lagerist neuerLagerist = inhaber.getLageristen().get(0);
		assertEquals(neuerLagerist.getBenutzername(), "lagerista");
		assertEquals(neuerLagerist.getVorname(), "Mia");
		assertEquals(neuerLagerist.getName(), "Miga");
		
		// Bearbeitung Lagerist-Objekt
		
		ihs.mitarbeiterDatenAendern(neuerLagerist, "lagerista", "notapirate", "everglow@online.de", "No en mi casa 23, 15086 Lima, Peru", "Mia", "Miga", "DE11 1111 1111 1111 11");
		assertEquals(neuerLagerist.getBenutzername(), "lagerista");
		assertEquals(neuerLagerist.getPasswort(), "notapirate");
		
		// Loeschen Lagerist-Objekt
		
		ihs.mitarbeiterLoeschen(neuerLagerist);
		assertTrue(inhaber.getLageristen().size() == 0);
		
		// Erstellung Fahrer-Objekt
		
		ihs.mitarbeiterRegistrieren("fahrer1", "alsonotatpirate", "everglow@online.de", "Eleven 22, 15085 Lima, Peru", "Leo", "Diario", "DE22 2222 2222 2222 22", Rolle.FAHRER);
		assertTrue(inhaber.getFahrer().size() > 0);
		Fahrer neuerFahrer = inhaber.getFahrer().get(0);
		assertEquals(neuerFahrer.getBenutzername(), "fahrer1");
		assertEquals(neuerFahrer.getVorname(), "Leo");
		assertEquals(neuerFahrer.getName(), "Diario");
	
		// Bearbeitung Fahrer-Objekt
		
		ihs.mitarbeiterDatenAendern(neuerFahrer, "fahrer", "alsonotatpirate", "ateez@online.de", "Eleven 22, 15085 Lima, Peru", "Leo", "Diario", "DE22 2222 2222 2222 22");
		assertEquals(neuerFahrer.getBenutzername(), "fahrer");
		assertEquals(neuerFahrer.getEmail(), "ateez@online.de");
		
		// Loeschen Fahrer-Objekt
		
		ihs.mitarbeiterLoeschen(neuerFahrer);
		assertTrue(inhaber.getFahrer().size() == 0);
	}
	
	/**
	 * Testet die Bearbeitung von Fahrzeugdaten
	 */
	@Test
	void testFahrzeugDatenAendern() {
		// Erstellung von Fahrzeug
		Fahrzeug fzeug = new Fahrzeug(94231, 321894215);
		
		// Fahrzeugkapazitaet aendern
		ihs.fahrzeugDatenAendern(fzeug, fzeug.getFahrzeugNummer(), 123456789);
		assertEquals(fzeug.getKapazitaet(), 123456789);
	}
	
	/**
	 * Testet, ob alle Daten des Benutzers im richtigen Format zurueckgegeben werden
	 */
	@Test
	void testPersoenlicheDatenAnzeigen() {
		String persoenlicheDaten = ihs.persoenlicheDatenAnzeigen();
		String result = benutzername + ";" + passwort + ";" + email + ";" + adresse + ";" + vorname + ";" + name + ";" + bankverbindung;
		assertEquals(persoenlicheDaten, result);
	}
	
	/**
	 * Testet, ob die Daten des Benutzers erfolgreich geaendert werden koennen
	 */
	@Test
	void testPersoenlicheDatenAendern() {
		ihs.persoenlicheDatenAendern(benutzername, passwort, "imapirateyeahyeah@online.de", adresse, vorname, name, bankverbindung);
		// Email aendern
		assertEquals(inhaber.getEmail(), "imapirateyeahyeah@online.de");
	}
	
	/**
	 * Testet, ob ein Produkt vom Inhaber bearbeitet werden kann
	 */
	@Test
	void testProduktBearbeiten() {
		Produkt producto = new Produkt("Chicha", "Peruanisch", 9.8, 9.99);
		ihs.produktBearbeiten(producto, producto.getName(), producto.getBeschreibung(), 10.99);
		assertEquals(producto.getVerkaufspreis(), 10.99);
	}
	
	/**
	 * Tests, dass der Inhaber Produkte zu Lager hinzufuegen und entfernen kann
	 */
	@Test
	void testLagerVerwalten() {
		// Erstellung von Lager und Produkte
		Lager lager = new Lager();
		Produkt producto = new Produkt("Chicha", "Peruanisch", 9.8, 9.99);
		Produkt product = new Produkt("Cola", "American", 5.99, 7.99);
		
		// Erstellung von Produkte-um-hinzufuegen Liste
		List<Produkt> productsToAdd = new ArrayList<Produkt>();
		productsToAdd.add(producto);
		productsToAdd.add(product);
		
		ihs.lagerVerwalten(lager, productsToAdd, "hinzufuegen");
		
		// Produkte von Lager erhalten
		HashSet<Produkt> lagerProdukte = lager.getLager();
		
		assertTrue(lagerProdukte.size() == 2);
		
		// Erstellung von Produkte-um-loeschen Liste
		List<Produkt> productsToRemove = new ArrayList<Produkt>();
		productsToRemove.add(product);
		
		ihs.lagerVerwalten(lager, productsToRemove, "loeschen");
		
		assertTrue(lagerProdukte.size() == 1);
	}
	
	/**
	 * Testet, ob der Inhaber eine Kategorie bearbeiten kann, indem er ihren Namen aendert oder Ober- und Unterkategorien hinzufuegt und entfernt
	 */
	@Test
	void testKategorieVerwalten() {
		// Erstellung von Kategorien
		Kategorie softDrinks = new Kategorie("Fizzy Drinks");
		Kategorie sauer = new Kategorie("Sauer");
		
		// Name der ersten Kategorie aendern
		ihs.kategorieBearbeiten(softDrinks, null, null, "Soft Drinks");
		
		assertEquals(softDrinks.getName(), "Soft Drinks");
		
		// Unterkategorie zu ersten Kategorie hinzufuegen
		ihs.kategorieBearbeiten(softDrinks, sauer, "unter", null);
		
		assertTrue(softDrinks.getUnterkategorien().contains(sauer));
		
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
		
		// Erstellung von Produkte-um-loeschen Liste
		List<Produkt> productsToRemove = new ArrayList<Produkt>();
		productsToRemove.add(product2);
		
		ihs.kategorieProdukteVerwalten(sauer, productsToRemove, "loeschen");
		assertFalse(sauer.getProdukte().contains(product2));
	}
	
	/**
	 * Testet, ob die abgerufenen statistischen Daten im richtigen Format mit den richtigen Daten zurueckgegeben werden
	 */
	@Test
	void teststatistikenAusgeben() {
		
		HashMap<String, Float> statistics = ihs.statistikenAusgeben();
		
		HashMap<String, Float> statisticsSupposedResult = new HashMap<String, Float>();
		statisticsSupposedResult.put("umsatz", (float) 0);
		statisticsSupposedResult.put("ausgaben", (float) 0);
		statisticsSupposedResult.put("einnahmen", (float) 0);
		statisticsSupposedResult.put("arbeitszeit", (float) 0);
		
		assertEquals(statistics, statisticsSupposedResult);
	}
}

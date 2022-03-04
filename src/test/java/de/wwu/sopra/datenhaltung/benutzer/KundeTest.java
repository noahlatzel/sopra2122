package de.wwu.sopra.datenhaltung.benutzer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.bestellung.Warenkorb;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Produkt;

/**
 * Testklasse zur Klasse Kunde
 * 
 * @author Paul Dirksen
 *
 */
public class KundeTest {

	Kunde kunde;
	Bestellung bestellung;
	LocalDateTime datum = null;

	@BeforeEach
	public void setup() {
		kunde = new Kunde("kunde", "666", "email69", "Kassel", "UnfassbarerVorname", "EinwandfreierNachname",
				"KapitalistenBankverbindung");
		ArrayList<Produkt> produkte = new ArrayList<Produkt>();
		produkte.add(new Produkt("Cola", "Lecker", 0.99, 1.29));
		Lager.getLagerbestand().put("Cola", 0);
		bestellung = new Bestellung(datum, produkte, kunde);
	}

	/**
	 * Der Constructor des Kundes wird getestet
	 */
	@Test
	public void testKunde() {
		assertTrue(kunde.getBenutzername().equals("kunde"));
		assertTrue(kunde.getPasswort().equals("666"));
		assertTrue(kunde.getEmail().equals("email69"));
		assertTrue(kunde.getAdresse().equals("Kassel"));
		assertTrue(kunde.getVorname().equals("UnfassbarerVorname"));
		assertTrue(kunde.getName().equals("EinwandfreierNachname"));
		assertTrue(kunde.getBankverbindung().equals("KapitalistenBankverbindung"));
	}

	/**
	 * Testet das Hinzufuegen einer neuen Bestellung zur Liste der Bestellungen und
	 * das Verhalten bei null-Uebergabe
	 */
	@Test
	public void bestellungHinzufuegenTest() {
		// Testen des Hinzufuegens einer Bestellung
		kunde.bestellungHinzufuegen(bestellung);
		assertTrue(kunde.getBestellungen().contains(bestellung));

		// Wenn Bestellung bereits enthalten ist wird sie nicht nochmal uebergeben
		int i = kunde.getBestellungen().size();
		kunde.bestellungHinzufuegen(bestellung);
		assertTrue(kunde.getBestellungen().size() == i);

		// Uebergeben von null erzeugt Exception
		assertThrows(NullPointerException.class, () -> {
			kunde.bestellungHinzufuegen(null);
		});
		assertThrows(AssertionError.class, () -> {
			kunde.bestellungHinzufuegen(new Bestellung(datum, new ArrayList<Produkt>(), kunde));
		});
	}

	/**
	 * Testet den Abruf vergangener Bestellungen des Kunden
	 */
	@Test
	void testGetBestellungen() {
		// Erstellung von Bestellungen-Liste
		ArrayList<Produkt> produkte = new ArrayList<Produkt>();
		produkte.add(new Produkt("Cola", "Lecker", 0.99, 1.29));
		Bestellung bestellung2 = new Bestellung(datum, produkte, kunde);
		Bestellung bestellung3 = new Bestellung(datum, produkte, kunde);
		Bestellung bestellung4 = new Bestellung(datum, produkte, kunde);
		List<Bestellung> bestellungen = new ArrayList<Bestellung>();
		bestellungen.add(bestellung);
		bestellungen.add(bestellung2);
		bestellungen.add(bestellung3);
		bestellungen.add(bestellung4);

		// Bestellungen zum Kunde hinzufuegen
		kunde.bestellungHinzufuegen(bestellung);
		kunde.bestellungHinzufuegen(bestellung2);
		kunde.bestellungHinzufuegen(bestellung3);
		kunde.bestellungHinzufuegen(bestellung4);

		List<Bestellung> kundeBestellungen = kunde.getBestellungen();
		assertEquals(bestellungen, kundeBestellungen);
	}

	/**
	 * Testet Warenkorb Getter und Setter
	 */
	@Test
	void testGetWarenkorb() {
		// Erstellung von Warenkorb und zu Kunde zuordnen
		ArrayList<Produkt> produkte = new ArrayList<Produkt>();
		produkte.add(new Produkt("Cola", "Lecker", 0.99, 1.29));
		Warenkorb warenkorb = new Warenkorb(produkte, kunde);
		Warenkorb kundesWarenkorb = kunde.getWarenkorb();

		assertEquals(warenkorb, kundesWarenkorb);
	}

	/**
	 * Testet, dass ein Kunde seinen Warenkorb leeren kann
	 */
	@Test
	void testKundesWarenkorbLeeren() {
		// Erstellung von Warenkorb
		Produkt produkt1 = new Produkt("Wasser", "Yum Yum", 0.3, 0.4);
		Produkt produkt2 = new Produkt("Cola", "Gesund", 0.5, 0.6);
		List<Produkt> produkte = new ArrayList<Produkt>();
		produkte.add(produkt1);
		produkte.add(produkt2);
		Warenkorb warenkorb = new Warenkorb(produkte, kunde);

		// Kundes Warenkorb leeren
		kunde.getWarenkorb().warenkorbLeeren();

		assertEquals(kunde.getWarenkorb().getProdukte().size(), 0);
	}

	/**
	 * Testet die Rolle Getter
	 */
	@Test
	void testGetRolle() {
		assertEquals(kunde.getRolle(), Rolle.KUNDE);
	}

	/**
	 * Testet das null-Setzen der Bestellungen
	 */
	@Test
	void entferneKundeTest() {
		kunde.kundeEntfernen();
		assertNull(kunde.getBestellungen());
	}
}

package de.wwu.sopra.datenhaltung.benutzer;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.bestellung.Warenkorb;
import de.wwu.sopra.datenhaltung.system.Produkt;

import org.junit.jupiter.api.BeforeEach;

/**
 * Testklasse zur Klasse Kunde
 * @author Paul Dirksen
 *
 */
public class KundeTest {

	Kunde kunde;
	Bestellung bestellung;
	LocalDateTime datum = null;
	
	@BeforeEach
	public void setup() {
		kunde = new Kunde("kunde", "666", "email69", "Kassel", "UnfassbarerVorname", "EinwandfreierNachname", "KapitalistenBankverbindung");
		bestellung = new Bestellung(1234, 3, datum, new ArrayList<Produkt>(), kunde);
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
	 * Testet das Hinzufuegen einer neuen Bestellung zur Liste der Bestellungen und das Verhalten bei null-Uebergabe
	 */
	@Test
	public void bestellungHinzufuegenTest() {
		//Testen des Hinzufuegens einer Bestellung
		kunde.bestellungHinzufuegen(bestellung);
		assertTrue(kunde.getBestellungen().contains(bestellung));
		
		//Wenn Bestellung bereits enthalten ist wird sie nicht nochmal uebergeben
		int i = kunde.getBestellungen().size();
		kunde.bestellungHinzufuegen(bestellung);
		assertTrue(kunde.getBestellungen().size() == i);
		
		//Uebergeben von null erzeugt Exception
		assertThrows(NullPointerException.class, () -> {
			kunde.bestellungHinzufuegen(null);
		});
	}
	
	/**
	 * Testet den Abruf vergangener Bestellungen des Kunden
	 */
	@Test
	void testGetBestellungen() {
		// Erstellung von Bestellungen-Liste
		Bestellung bestellung2 = new Bestellung(1235, 3, datum, new ArrayList<Produkt>(), kunde);
		Bestellung bestellung3 = new Bestellung(1236, 3, datum, new ArrayList<Produkt>(), kunde);
		Bestellung bestellung4 = new Bestellung(1237, 3, datum, new ArrayList<Produkt>(), kunde);
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
		Warenkorb warenkorb = new Warenkorb(58, new ArrayList<Produkt>(), kunde);
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
		Warenkorb warenkorb = new Warenkorb(58, produkte, kunde);
		
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
	
}

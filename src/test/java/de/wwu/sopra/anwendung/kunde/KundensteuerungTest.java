package de.wwu.sopra.anwendung.kunde;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.bestellung.IdZaehler;
import de.wwu.sopra.datenhaltung.bestellung.Warenkorb;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Produkt;

public class KundensteuerungTest {

	Kunde kunde;
	Lager lager;
	List<Produkt> liste;
	List<Produkt> liste2;
	List<Bestellung> bestellungen;
	Warenkorb warenkorb = new Warenkorb(liste2,kunde);
	
	// vor jedem Test
		@BeforeEach
		public void init() {
			lager.addProdukt(new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29));
			lager.addProdukt(new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29));
			lager.addProdukt(new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29));
			lager.addProdukt(new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29));
			lager.addProdukt(new Produkt("Cola", "Toller Geschmack", 0.99, 1.29));
			Produkt cola = new Produkt("Cola", "Toller Geschmack", 0.99, 1.29);
			Produkt cola2 = new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29);
			Produkt cola3 = new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29);
			Produkt cola4 = new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29);
			liste.add(cola);
			liste2.add(cola2);
			liste2.add(cola3);
			liste2.add(cola4);
		}
	
	/**
	 * Testet, ob der Konstruktor funktioniert.
	 */
	@Test
	void testKonstruktor() {
		assertTrue(kunde.getName().equals("Mueller"));
		assertTrue(kunde.getVorname().equals("Agatha"));
		assertTrue(kunde.getAdresse().equals("Privet Drive 3"));
		assertTrue(kunde.getBankverbindung().equals("De414580364567893456"));
		assertTrue(kunde.getBenutzername().equals("MuellerAgi.123"));
		assertTrue(kunde.getPasswort().equals("12345"));
		assertTrue(kunde.getEmail().equals("Agatha.b@gmail.com"));
		
	}
	
	/**
	 * Testet, ob die Methode persoenlicheDatenAnzeigen funktioniert
	 */
	@Test
	void testpersoenlicheDatenAnzeigen() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		assertTrue(kundensteuerung.persoenlicheDatenAnzeigen()
				.equals("Agi.123,12345,Agatha.b@gmail.com,Privet Drive 3,Agatha, Mueller,De414580364567893456"));
	}
	
	/**
	 * Testet ob die Methode persoenlicheDatenAendern funktioniert
	 */
	@Test
	void testpersoenlicheDatenAendern() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		kundensteuerung.persoenlicheDatenAendern("Agi.123","12345","Agatha.b@gmail.com","Privet Drive 3","Agatha","Mueller","De414580364567893456");
		String vorher = "Agi.123,12345,Agatha.b@gmail.com,Privet Drive 3,Agatha, Mueller,De414580364567893456";
		assertTrue(kundensteuerung.persoenlicheDatenAnzeigen().equals(vorher));
	}
	
	/**
	 * Testet ob die Methode suchen funktioniert
	 */
	@Test
	void testeSuchen() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		List<Produkt> produkte = kundensteuerung.suchen("Cola");
		assertTrue(produkte.equals(liste));
	}
	
	/**
	 * Testet, ob die Methode bestellen funktioniert
	 */
	@Test
	void testeBestellen() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		Bestellung bestellung1 = new Bestellung(IdZaehler.getBestellungsId(), LocalDateTime.now(),liste,
				kunde);
		bestellungen.add(bestellung1);
		kundensteuerung.bestellen();
		assertTrue(bestellungen.equals(kunde.getBestellungen()));
	}
	
	/**
	 * Testet, ob die Methode warenkorbAnsicht funktioniert
	 */
	@Test
	void testeWarenkorbAnsicht() {
	 Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
	 kunde.setWarenkorb(warenkorb);
	 assertTrue(warenkorb.equals(kundensteuerung.warenkorbAnsicht()));
	}
	
	/**
	 * Testet ob die Methode bestellungenAnzeigen funktioniert
	 */
	@Test
	void testeBestellungenAnzeigen() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		Bestellung bestellung1 = new Bestellung(IdZaehler.getBestellungsId(), LocalDateTime.now(),liste,
				kunde);
		Bestellung bestellung2 = new Bestellung(IdZaehler.getBestellungsId(), LocalDateTime.now(),liste2,
				kunde);
		bestellungen.add(bestellung1);
		bestellungen.add(bestellung2);
		kunde.bestellungHinzufuegen(bestellung1);
		kunde.bestellungHinzufuegen(bestellung2);
		assertTrue(bestellungen.equals(kundensteuerung.bestellungenAnzeigen()));
	}
	
	
	/**
	 * Testet, ob die Methode stornieren funktioniert
	 */
	@Test
	void testeStornieren() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		Bestellung bestellung1 = new Bestellung(IdZaehler.getBestellungsId(), LocalDateTime.now(),liste,
				kunde);
		Bestellung bestellung2 = new Bestellung(IdZaehler.getBestellungsId(), LocalDateTime.now(),liste2,
				kunde);
		bestellungen.add(bestellung1);
		kunde.bestellungHinzufuegen(bestellung1);
		kunde.bestellungHinzufuegen(bestellung2);
		kundensteuerung.stornieren(bestellung2);
		assertTrue(bestellungen.equals(kunde.getBestellungen()));
		
	}
	
	/**
	 * Testet, ob die Methode nachbestellen funktioniert
	 */
	@Test
	void testeNachbestellen() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		Bestellung bestellung1 = new Bestellung(IdZaehler.getBestellungsId(), LocalDateTime.now(),liste,
				kunde);
		Bestellung bestellung2 = new Bestellung(IdZaehler.getBestellungsId(), LocalDateTime.now(),liste2,
				kunde);
		Bestellung bestellung3 = new Bestellung(IdZaehler.getBestellungsId(), LocalDateTime.now(),liste2,
				kunde);
		bestellungen.add(bestellung1);
		bestellungen.add(bestellung2);
		bestellungen.add(bestellung3);
		kunde.bestellungHinzufuegen(bestellung1);
		kunde.bestellungHinzufuegen(bestellung2);
		kundensteuerung.nachbestellen(bestellung2);
		assertTrue(bestellungen.equals(kunde.getBestellungen()));
	}
	
	/**
	 * Testet, ob die Methode neueProduktliste funktioniert
	 */
	@Test
	void testeNeueProduktliste() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		List<Produkt> neueListe = kundensteuerung.neueProduktListe(liste2);
		assertTrue(liste2.equals(neueListe));
	}
}

package de.wwu.sopra.anwendung.kunde;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.benutzer.Kunde;

public class KundensteuerungTest {

	Kunde kunde;
	Kundensteuerung kundensteuerung;
	@BeforeAll
	void init() {
		Kunde kunde = new Kunde("Agi.123","12345","Agatha.b@gmail.com","Privet Drive 3",
				"Agatha", "Mueller","De414580364567893456");
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
		assertTrue(kundensteuerung.persoenlicheDatenAnzeigen()
				.equals("Agi.123,12345,Agatha.b@gmail.com,Privet Drive 3,Agatha, Mueller,De414580364567893456"));
	}
	
	
}

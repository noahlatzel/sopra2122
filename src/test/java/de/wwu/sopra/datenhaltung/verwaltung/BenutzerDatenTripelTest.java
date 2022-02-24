package de.wwu.sopra.datenhaltung.verwaltung;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.benutzer.Benutzer;
import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.management.Produkt;

/**
 * Testet die Klasse BenutzerDatenTripel
 * 
 * @author intruq
 *
 */
public class BenutzerDatenTripelTest {

	Benutzer benutzer;
	List<Bestellung> bestellungen;
	List<Produkt> warenkorb;
	BenutzerDatenTripel benutzerDatenTripel;

	@BeforeEach
	public void setup() {
		benutzer = new Kunde("einBenutzername", "einPasswort", "eineEmail", "eineAdresse", "einVorname", "einName",
				"eineBankverbindung");
		bestellungen = new ArrayList<Bestellung>();
		warenkorb = new ArrayList<Produkt>();
		benutzerDatenTripel = new BenutzerDatenTripel(benutzer);
	}

	@Test
	public void getterTest() {
		assertEquals(benutzerDatenTripel.getBenutzer(), benutzer);
		assertEquals(benutzerDatenTripel.getBestellungen(), bestellungen);
		assertEquals(benutzerDatenTripel.getWarenkorb(), warenkorb);
	}

}

package de.wwu.sopra.datenhaltung.bestellung;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.management.Produkt;

@TestInstance(Lifecycle.PER_CLASS)
class BestellungTest {
	ArrayList<Produkt> produkte;
	Kunde kunde;
	Bestellung bestellung;

	@BeforeAll
	void init() {
		produkte = new ArrayList<Produkt>();
		kunde = new Kunde("kunde", "666", "email69", "Kassel", "UnfassbarerVorname", "EinwandfreierNachname",
				"KapitalistenBankverbindung");
		bestellung = new Bestellung(1, 0.99, null, produkte, kunde);
	}

	/**
	 * Testet, ob der Konstruktor funktioniert.
	 */
	@Test
	void testKonstruktor() {
		assertTrue(bestellung.getStatus().equals(BestellStatus.OFFEN));
		assertTrue(bestellung.getDatum() == null);
		assertTrue(bestellung.getBestellnummer() == 1);
		assertTrue(bestellung.getBetrag() == 0.99);
		assertTrue(bestellung.getProdukte() == produkte);
		assertTrue(bestellung.getKunde() == kunde);
	}

	/**
	 * Testet getter und setter fuer die Rechnung einer Bestellung.
	 */
	@Test
	void testSetGetRechnung() {
		Rechnung rechnung = new Rechnung(1, 0.99, null, bestellung);
		bestellung.setRechnung(rechnung);
		assertTrue(bestellung.getRechnung().equals(rechnung));
	}

}

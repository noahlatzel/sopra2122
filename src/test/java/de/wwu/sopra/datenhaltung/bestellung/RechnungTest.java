package de.wwu.sopra.datenhaltung.bestellung;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.management.Produkt;

class RechnungTest {

	/**
	 * Testet den Konstruktor der Klasse Rechnung.
	 */
	@Test
	void testKonstruktor() {
		ArrayList<Produkt> produkte = new ArrayList<Produkt>();
		produkte.add(new Produkt("Cola", "Lecker", 0.99, 1.29));
		Kunde kunde = new Kunde("kunde", "666", "email69", "Kassel", "UnfassbarerVorname", "EinwandfreierNachname",
				"KapitalistenBankverbindung");
		Bestellung bestellung = new Bestellung(1, null, produkte, kunde);
		Rechnung rechnung = new Rechnung(1, 0.99, null, bestellung);
		assertTrue(rechnung.getBestellung().equals(bestellung));
		assertTrue(rechnung.getDatum() == null);
		assertTrue(rechnung.getEndbetrag() == 0.99);
		assertTrue(rechnung.getRechnungsnummer() == 1);
	}

}

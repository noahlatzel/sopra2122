package de.wwu.sopra.datenhaltung.bestellung;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.management.Produkt;

class WarenkorbTest {
	Warenkorb warenkorb;
	ArrayList<Produkt> produkte;
	Kunde kunde;

	@BeforeEach
	void init() {
		produkte = new ArrayList<Produkt>();
		kunde = new Kunde("kunde", "666", "email69", "Kassel", "UnfassbarerVorname", "EinwandfreierNachname",
				"KapitalistenBankverbindung");
		warenkorb = new Warenkorb(1.98, produkte, kunde);
	}

	/**
	 * Testet den Konstruktor der Klasse Warenkorb
	 */
	@Test
	void testKonstruktor() {
		produkte.add(new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29));
		produkte.add(new Produkt("Fanta", "Toller Geschmack", 0.99, 1.29));
		assertTrue(warenkorb.getBetrag() == 1.98);
		assertTrue(warenkorb.getKunde().equals(kunde));
		assertTrue(warenkorb.getProdukte().equals(produkte));
	}

	/**
	 * Testet das Hinzufuegen von Produkten zum Warenkorb.
	 */
	@Test
	void testProduktHinzufuegen() {
		Produkt produkt_1 = new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29);
		warenkorb.produkttHinzufuegen(produkt_1);
		assertTrue(warenkorb.getProdukte().contains(produkt_1));
		assertTrue(warenkorb.getProdukte().size() == 1);
	}

	/**
	 * Testet das Entfernen von Produkten vom Warenkorb.
	 */
	@Test
	void testProduktEntfernen() {
		Produkt produkt_1 = new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29);
		warenkorb.produkttHinzufuegen(produkt_1);
		warenkorb.produktEntfernen(produkt_1);
		assertFalse(warenkorb.getProdukte().contains(produkt_1));
		assertTrue(warenkorb.getProdukte().size() == 0);
	}

}

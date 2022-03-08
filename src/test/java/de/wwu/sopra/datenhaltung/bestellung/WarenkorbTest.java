package de.wwu.sopra.datenhaltung.bestellung;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Produkt;
import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;

class WarenkorbTest {
	Warenkorb warenkorb;
	ArrayList<Produkt> produkte;
	Kunde kunde;

	@AfterEach
	void cleanAfter() {
		Lager.reset();
		FahrzeugRegister.reset();
	}

	@BeforeEach
	void reset() {
		Lager.reset();
		FahrzeugRegister.reset();
	}

	@BeforeEach
	void init() {
		produkte = new ArrayList<Produkt>();
		kunde = new Kunde("kunde", "666", "email69", "Kassel", "UnfassbarerVorname", "EinwandfreierNachname",
				"KapitalistenBankverbindung");
		warenkorb = new Warenkorb(produkte, kunde);
	}

	/**
	 * Testet den Konstruktor der Klasse Warenkorb
	 */
	@Test
	void testKonstruktor() {
		produkte.add(new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29));
		produkte.add(new Produkt("Cola", "Toller Geschmack", 0.99, 1.29));
		warenkorb = new Warenkorb(produkte, kunde);
		assertTrue(warenkorb.getBetrag() == 2.58);
		assertTrue(warenkorb.getKunde().equals(kunde));
		assertTrue(warenkorb.getProdukte().equals(produkte));
		assertThrows(AssertionError.class, () -> {
			new Warenkorb(produkte, null);
		});
		assertThrows(AssertionError.class, () -> {
			new Warenkorb(null, kunde);
		});
	}

	/**
	 * Testet das Hinzufuegen von Produkten zum Warenkorb.
	 */
	@Test
	void testProduktHinzufuegen() {
		Produkt produkt_1 = new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29);
		warenkorb.produktHinzufuegen(produkt_1);
		assertTrue(warenkorb.getProdukte().contains(produkt_1));
		assertTrue(warenkorb.getProdukte().size() == 1);
	}

	/**
	 * Testet das Entfernen von Produkten vom Warenkorb.
	 */
	@Test
	void testProduktEntfernen() {
		Produkt produkt_1 = new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29);
		warenkorb.produktHinzufuegen(produkt_1);
		warenkorb.produktEntfernen(produkt_1);
		assertFalse(warenkorb.getProdukte().contains(produkt_1));
		assertTrue(warenkorb.getProdukte().size() == 0);
		assertTrue(warenkorb.getBetrag() == 0);
		warenkorb.produktEntfernen(produkt_1);
		assertFalse(warenkorb.getProdukte().contains(produkt_1));
		assertTrue(warenkorb.getProdukte().size() == 0);
		assertTrue(warenkorb.getBetrag() == 0);
	}

}

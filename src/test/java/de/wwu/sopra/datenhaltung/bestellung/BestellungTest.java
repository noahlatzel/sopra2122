package de.wwu.sopra.datenhaltung.bestellung;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Produkt;
import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;

@TestInstance(Lifecycle.PER_CLASS)
class BestellungTest {
	ArrayList<Produkt> produkte;
	Kunde kunde;
	Bestellung bestellung;
	Bestellung bestellung2;
	Rabatt rabatt;

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

	@BeforeAll
	void init() {
		produkte = new ArrayList<Produkt>();
		produkte.add(new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29));
		kunde = new Kunde("kunde", "666", "email69", "Kassel", "UnfassbarerVorname", "EinwandfreierNachname",
				"KapitalistenBankverbindung");
		Lager.produktZumSortimentHinzufuegen(new Produkt("Coca Cola", "Lecker", 0.49, 0.99));
		rabatt = new Rabatt("ABC", 50);
		bestellung = new Bestellung(null, produkte, kunde);
		bestellung2 = new Bestellung(null, produkte, kunde, rabatt);
	}

	/**
	 * Testet, ob der Konstruktor funktioniert.
	 */
	@Test
	void testKonstruktor() {
		assertThrows(AssertionError.class, () -> {
			new Bestellung(null, produkte, null);
		});
		assertTrue(bestellung.getStatus().equals(BestellStatus.OFFEN));
		assertTrue(bestellung.getDatum() == null);
		System.out.println(bestellung.getBetrag());
		assertTrue(bestellung.getBetrag() == 1.29);
		assertTrue(bestellung.getProdukte() == produkte);
		assertTrue(bestellung.getKunde() == kunde);
		assertTrue(bestellung.toString().equals("Bestellung " + bestellung.getBestellnummer()));
		assertTrue(bestellung.getAdresse().equals(bestellung.getKunde().getAdresse()));
	}

	/**
	 * Testet, ob der Konstruktor 2 funktioniert.
	 */
	@Test
	void testKonstruktor2() {
		assertThrows(AssertionError.class, () -> {
			new Bestellung(null, produkte, null, null);
		});
		assertTrue(bestellung2.getStatus().equals(BestellStatus.OFFEN));
		assertTrue(bestellung2.getDatum() == null);
		System.out.println(bestellung2.getBetrag());
		assertTrue(bestellung2.getBetrag() == 1.29 * 0.5);
		assertTrue(bestellung2.getProdukte() == produkte);
		assertTrue(bestellung2.getKunde() == kunde);
		assertTrue(bestellung2.toString().equals("Bestellung " + bestellung2.getBestellnummer()));
		assertTrue(bestellung2.getAdresse().equals(bestellung.getKunde().getAdresse()));
		assertTrue(bestellung2.getRabatt().equals(rabatt));
	}

	/**
	 * Testet getter und setter fuer die Rechnung einer Bestellung.
	 */
	@Test
	void testSetGetRechnung() {
		Rechnung rechnung = new Rechnung(null, bestellung);
		bestellung.setRechnung(rechnung);
		assertTrue(bestellung.getRechnung().equals(rechnung));
	}

	/**
	 * Testet den Konstruktor auf Ausnahmebehandlung.
	 */
	@Test
	void testThrowsKonstruktor() {
		assertThrows(AssertionError.class, () -> {
			new Bestellung(null, new ArrayList<Produkt>(), kunde);
		});
	}

	/**
	 * Testet die Verwaltung des Rabatts
	 */
	@Test
	void setRabattTest() {
		double before = bestellung.getBetrag();
		Rabatt rabatt = new Rabatt("ABC", 50);
		bestellung.setRabatt(rabatt);
		assertTrue(bestellung.getRabatt().equals(rabatt));
		double after = bestellung.calcBetrag();
		assertTrue(after == before * 0.5);
	}

}

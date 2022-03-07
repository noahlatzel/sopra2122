package de.wwu.sopra.datenhaltung.verwaltung;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Produkt;

/**
 * Diese Klasse testet das GrosshaendlerRegister.
 * 
 * @author NoahLatzel
 *
 */
public class GrosshaendlerRegisterTest {
	Produkt produkt1;

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

	/**
	 * Testet addPreis fuer Produkt.
	 */
	@Test
	void testAddPreis() {
		produkt1 = new Produkt("Orangensaft", "Aus frischen Orangen gepresst!", 0.99, 1.09);
		GrosshaendlerRegister.setEinkaufspreis(produkt1, 0.89);
		assertTrue(GrosshaendlerRegister.getEinkaufspreis(produkt1) == 0.89);
	}

	/**
	 * Testet fuer addPreis fuer String.
	 */
	@Test
	void testAddPreisString() {
		GrosshaendlerRegister.setEinkaufspreis("Orangensaft", 0.89);
		produkt1 = new Produkt("Orangensaft", "Aus frischen Orangen gepresst!", 0.99, 1.09);
		assertTrue(GrosshaendlerRegister.getEinkaufspreis("Orangensaft") == 0.89);
		assertTrue(GrosshaendlerRegister.getEinkaufspreis(produkt1) == 0.89);
	}

	/**
	 * Testet load und save.
	 */
	@Test
	void testLoad() {
		HashMap<String, Double> temp = GrosshaendlerRegister.getPreislisteIn();
		GrosshaendlerRegister.save();
		GrosshaendlerRegister.load();
		assertTrue(temp.equals(GrosshaendlerRegister.getPreislisteIn()));
	}

}

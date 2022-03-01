package de.wwu.sopra.datenhaltung.verwaltung;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.management.Produkt;

/**
 * Diese Klasse testet das GrosshaendlerRegister.
 * 
 * @author NoahLatzel
 *
 */
public class GrosshaendlerRegisterTest {
	Produkt produkt1;

	/**
	 * Testet addPreis fuer Produkt.
	 */
	@Test
	void testAddPreis() {
		produkt1 = new Produkt("Orangensaft", "Aus frischen Orangen gepresst!", 0.99, 1.09);
		GrosshaendlerRegister.setPreis(produkt1, 0.89);
		assertTrue(GrosshaendlerRegister.getPreis(produkt1) == 0.89);
	}

	/**
	 * Testet fuer addPreis fuer String.
	 */
	@Test
	void testAddPreisString() {
		GrosshaendlerRegister.setPreis("Orangensaft", 0.89);
		produkt1 = new Produkt("Orangensaft", "Aus frischen Orangen gepresst!", 0.99, 1.09);
		assertTrue(GrosshaendlerRegister.getPreis("Orangensaft") == 0.89);
		assertTrue(GrosshaendlerRegister.getPreis(produkt1) == 0.89);
	}

}

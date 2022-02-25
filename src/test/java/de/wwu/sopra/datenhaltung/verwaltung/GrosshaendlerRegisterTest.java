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
	GrosshaendlerRegister preisRegister;
	Produkt produkt1;

	/**
	 * Testet addPreis fuer Produkt.
	 */
	@Test
	void testAddPreis() {
		preisRegister = new GrosshaendlerRegister();
		produkt1 = new Produkt("Orangensaft", "Aus frischen Orangen gepresst!", 0.99, 1.09);
		preisRegister.setPreis(produkt1, 0.89);
		assertTrue(preisRegister.getPreis(produkt1) == 0.89);
	}

	/**
	 * Testet fuer addPreis fuer String.
	 */
	@Test
	void testAddPreisString() {
		preisRegister = new GrosshaendlerRegister();
		preisRegister.setPreis("Orangensaft", 0.89);
		produkt1 = new Produkt("Orangensaft", "Aus frischen Orangen gepresst!", 0.99, 1.09);
		assertTrue(preisRegister.getPreis("Orangensaft") == 0.89);
		assertTrue(preisRegister.getPreis(produkt1) == 0.89);
	}

}

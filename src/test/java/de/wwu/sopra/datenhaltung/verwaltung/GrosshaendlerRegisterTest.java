package de.wwu.sopra.datenhaltung.verwaltung;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.management.Produkt;

public class GrosshaendlerRegisterTest {
	GrosshaendlerRegister preisRegister;
	Produkt produkt1;

	@Test
	void testAddPreis() {
		preisRegister = new GrosshaendlerRegister();
		produkt1 = new Produkt("Orangensaft", "Aus frischen Orangen gepresst!", 0.99, 1.09);
		preisRegister.setPreis(produkt1, 0.89);
		assertTrue(preisRegister.getPreis(produkt1) == 0.89);
	}

}

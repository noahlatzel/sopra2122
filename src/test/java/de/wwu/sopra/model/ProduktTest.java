package de.wwu.sopra.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class ProduktTest {
	Produkt produkt_1;
	Kategorie kategorie_1;

	@BeforeAll
	public void init() {
		produkt_1 = new Produkt("Orangensaft", "Aus frischen Orangen gepresst!", 0.99, 1.09);
		kategorie_1 = new Kategorie("Saft");
	}

	/**
	 * Testet, ob setKategorie funktioniert.
	 */
	@Test
	public void testSetKategorie() {
		produkt_1.setKategorie(kategorie_1);
		assertTrue(produkt_1.getKategorie() == kategorie_1);
	}
}

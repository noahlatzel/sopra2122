package de.wwu.sopra.datenhaltung.management;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;

@TestInstance(Lifecycle.PER_CLASS)
public class ProduktTest {
	Produkt produkt_1;
	Kategorie kategorie_1;

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

	/**
	 * Testet, ob der Konstruktor mit falscher Eingabe einen Fehler wirft.
	 */
	@Test
	public void testThrowsKonstruktor() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Produkt("Orangensaft", "Aus frischen Orangen gepresst!", -0.99, 1.09);
		});
	}

	/**
	 * Testet setName fuer leeren String.
	 */
	@Test
	public void testThrowsSetName() {
		assertThrows(IllegalArgumentException.class, () -> {
			produkt_1.setName("");
		});
	}

	/**
	 * Testet getName.
	 */
	@Test
	public void testGetName() {
		produkt_1.setName("Fanta");
		assertTrue(produkt_1.getName().equals("Fanta"));
	}

	/**
	 * Testet getBeschreibung.
	 */
	@Test
	public void testGetBeschreibung() {
		produkt_1.setBeschreibung("Orangengetraenk");
		assertTrue(produkt_1.getBeschreibung().equals("Orangengetraenk"));
	}

	/**
	 * Testet getVerkaufspreis.
	 */
	@Test
	public void testGetVerkaufspreis() {
		assertTrue(produkt_1.getVerkaufspreis() == 1.09);
	}

	/**
	 * Testet getEinkaufspreis.
	 */
	@Test
	public void testGetEinkaufspreis() {
		assertTrue(produkt_1.getEinkaufspreis() == 0.99);
	}

	/**
	 * Testet, ob setBeschreibung leeren String akzeptiert.
	 */
	@Test
	public void testThrowsSetBeschreibung() {
		assertThrows(IllegalArgumentException.class, () -> {
			produkt_1.setBeschreibung("");
		});
	}

	/**
	 * Testet zu geringen Verkaufspreis fuer setVerkaufspreis.
	 */
	@Test
	public void testThrowsSetVerkaufspreis() {
		assertThrows(IllegalArgumentException.class, () -> {
			produkt_1.setVerkaufspreis(0);
		});
	}

	/**
	 * Testet klonen.
	 */
	@Test
	public void testClone() {
		produkt_1.setKategorie(kategorie_1);
		Produkt produkt_clone = produkt_1.clone(1.5);
		assertTrue(produkt_clone.getBeschreibung().equals(produkt_1.getBeschreibung()));
		assertTrue(produkt_clone.getName().equals(produkt_1.getName()));
		assertTrue(produkt_clone.getKategorie().equals(produkt_1.getKategorie()));
		assertTrue(produkt_clone.getEinkaufspreis() == 0.99);
		assertTrue(produkt_clone.getVerkaufspreis() == 1.5);
	}
}

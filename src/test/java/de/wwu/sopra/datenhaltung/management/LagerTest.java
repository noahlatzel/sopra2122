package de.wwu.sopra.datenhaltung.management;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class LagerTest {
	Lager lager;
	ArrayList<Produkt> produkte;

	@BeforeEach
	void init() {
		lager = new Lager();
		produkte = new ArrayList<Produkt>();
		produkte.add(new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29));
		produkte.add(new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29));
		produkte.add(new Produkt("Coca Cola", "Toller Geschmack", 1.09, 1.29));
		produkte.add(new Produkt("Fanta", "Toller Geschmack", 0.99, 1.29));
	}

	/**
	 * Testet addProdukt mit leerem Lager und einem Produkt.
	 */
	@Test
	void testAddProdukt() {
		lager.addProdukt(produkte.get(0));
		assertTrue(lager.getLager().contains(produkte.get(0)));
		assertTrue(lager.getLagerbestand().get(produkte.get(0).getName()) == 1);
	}

	/**
	 * Testet removeProdukt mit gefuelltem Lager.
	 */
	@Test
	void testRemoveProdukt() {
		lager.addProdukt(produkte.get(0));
		lager.removeProdukt(produkte.get(0));
		assertTrue(!(lager.getLager().contains(produkte.get(0))));
		assertTrue(lager.getLagerbestand().get(produkte.get(0).getName()) == 0);
	}

	/**
	 * Testet addProdukte mit leerem Lager und mehreren Produkten.
	 */
	@Test
	void testAddProdukte() {
		lager.addProdukte(produkte);
		for (Produkt p : produkte) {
			assertTrue(lager.getLager().contains(p));
		}
		assertTrue(lager.getLagerbestand().get(produkte.get(0).getName()) == 3);
		assertTrue(lager.getLagerbestand().get(produkte.get(3).getName()) == 1);
	}

	/**
	 * Testet removeProdukt mit gefuelltem Lager und mehreren Produkten.
	 */
	@Test
	void testRemoveProdukte() {
		lager.addProdukte(produkte);
		lager.removeProdukte(produkte);
		for (Produkt p : produkte) {
			assertFalse(lager.getLager().contains(p));
		}
		assertTrue(lager.getLagerbestand().get(produkte.get(0).getName()) == 0);
		assertTrue(lager.getLagerbestand().get(produkte.get(3).getName()) == 0);
	}

	/**
	 * Testet getProduktBestand
	 */
	@Test
	void testGetProduktBestand() {
		lager.addProdukte(produkte);
		assertTrue(lager.getProduktBestand(produkte.get(0)) == lager.getProduktBestand(produkte.get(0).getName()));
		assertTrue(lager.getProduktBestand(produkte.get(0)) == 3);
	}

	/**
	 * Testet Ausnahmeverhalten von getProduktBestand
	 */
	@Test
	void testThrowsGetProduktBestand() {
		assertThrows(IllegalArgumentException.class, () -> {
			lager.getProduktBestand("kein Produkt");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			lager.getProduktBestand(new Produkt("keinProdukt", "Toller Geschmack", 0.99, 1.29));
		});
	}
}

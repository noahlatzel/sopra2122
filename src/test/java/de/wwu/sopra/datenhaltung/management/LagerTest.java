package de.wwu.sopra.datenhaltung.management;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;

@TestInstance(Lifecycle.PER_CLASS)
public class LagerTest {
	Lager lager;
	ArrayList<Produkt> produkte;

	@BeforeEach
	void init() {
		Lager.reset();
		FahrzeugRegister.reset();
		produkte = new ArrayList<Produkt>();
		Lager.getLagerbestand().put("Coca Cola", 0);
		Lager.getLagerbestand().put("Fanta", 0);
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
		Lager.addProdukt(produkte.get(0));
		assertTrue(Lager.getLager().contains(produkte.get(0)));
	}

	/**
	 * Testet removeProdukt mit gefuelltem Lager.
	 */
	@Test
	void testRemoveProdukt() {
		Lager.addProdukt(produkte.get(0));
		int temp = Lager.getLagerbestand().get(produkte.get(0).getName());
		Lager.removeProdukt(produkte.get(0));
		assertTrue(!(Lager.getLager().contains(produkte.get(0))));
		assertTrue(Lager.getLagerbestand().get(produkte.get(0).getName()) == temp - 1);
	}

	/**
	 * Testet addProdukte mit leerem Lager und mehreren Produkten.
	 */
	@Test
	void testAddProdukte() {
		int anzahl = Lager.getLagerbestand().get(produkte.get(0).getName());
		int anzahl1 = Lager.getLagerbestand().get(produkte.get(3).getName());
		Lager.addProdukte(produkte);
		for (Produkt p : produkte) {
			assertTrue(Lager.getLager().contains(p));
		}
		assertTrue(Lager.getLagerbestand().get(produkte.get(0).getName()) == anzahl + 3);
		assertTrue(Lager.getLagerbestand().get(produkte.get(3).getName()) == anzahl1 + 1);
	}

	/**
	 * Testet removeProdukt mit gefuelltem Lager und mehreren Produkten.
	 */
	@Test
	void testRemoveProdukte() {
		Lager.addProdukte(produkte);
		int anzahl = Lager.getLagerbestand().get(produkte.get(0).getName());
		int anzahl1 = Lager.getLagerbestand().get(produkte.get(3).getName());
		Lager.removeProdukte(produkte);
		for (Produkt p : produkte) {
			assertFalse(Lager.getLager().contains(p));
		}
		assertTrue(Lager.getLagerbestand().get(produkte.get(0).getName()) == anzahl - 3);
		assertTrue(Lager.getLagerbestand().get(produkte.get(3).getName()) == anzahl1 - 1);
	}

	/**
	 * Testet getProduktBestand
	 */
	@Test
	void testGetProduktBestand() {
		int anzahl = Lager.getLagerbestand().get(produkte.get(0).getName());
		Lager.addProdukte(produkte);
		assertTrue(Lager.getProduktBestand(produkte.get(0)) == Lager.getProduktBestand(produkte.get(0).getName()));
		assertTrue(Lager.getProduktBestand(produkte.get(0)) == anzahl + 3);
	}

	/**
	 * Testet Ausnahmeverhalten von getProduktBestand
	 */
	@Test
	void testThrowsGetProduktBestand() {
		assertThrows(IllegalArgumentException.class, () -> {
			Lager.getProduktBestand("kein Produkt");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			Lager.getProduktBestand(new Produkt("keinProdukt", "Toller Geschmack", 0.99, 1.29));
		});
	}

	/**
	 * Testet load und save.
	 */
	@Test
	void testLoad() {
		HashSet<Produkt> temp_1 = Lager.getLager();
		Lager.save();
		Lager.load();
		assertTrue(temp_1.equals(Lager.getLager()));
	}

}

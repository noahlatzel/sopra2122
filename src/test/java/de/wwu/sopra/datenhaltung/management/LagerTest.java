package de.wwu.sopra.datenhaltung.management;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;

@TestInstance(Lifecycle.PER_CLASS)
public class LagerTest {
	Lager lager;
	ArrayList<Produkt> produkte;

	@AfterEach
	void cleanAfter() {
		Lager.reset();
		FahrzeugRegister.reset();
	}

	@BeforeEach
	void init() {
		Lager.reset();
		FahrzeugRegister.reset();
		produkte = new ArrayList<Produkt>();
		Lager.produktZumSortimentHinzufuegen(new Produkt("Coca Cola", "Lecker", 0.49, 0.99));
		Lager.produktZumSortimentHinzufuegen(new Produkt("Fanta", "Lecker", 0.49, 0.99));
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
		int temp = Lager.getLagerbestand().get(produkte.get(0));
		Lager.removeProdukt(produkte.get(0));
		assertTrue(!(Lager.getLager().contains(produkte.get(0))));
		assertTrue(Lager.getLagerbestand().get(produkte.get(0)) == temp - 1);
	}

	/**
	 * Testet addProdukte mit leerem Lager und mehreren Produkten.
	 */
	@Test
	void testAddProdukte() {
		int anzahl = Lager.getLagerbestand().get(produkte.get(0));
		int anzahl1 = Lager.getLagerbestand().get(produkte.get(3));
		Lager.addProdukte(produkte);
		for (Produkt p : produkte) {
			assertTrue(Lager.getLager().contains(p));
		}
		assertTrue(Lager.getLagerbestand().get(produkte.get(0)) == anzahl + 3);
		assertTrue(Lager.getLagerbestand().get(produkte.get(3)) == anzahl1 + 1);
	}

	/**
	 * Testet removeProdukt mit gefuelltem Lager und mehreren Produkten.
	 */
	@Test
	void testRemoveProdukte() {
		Lager.addProdukte(produkte);
		int anzahl = Lager.getLagerbestand().get(produkte.get(0));
		int anzahl1 = Lager.getLagerbestand().get(produkte.get(3));
		Lager.removeProdukte(produkte);
		for (Produkt p : produkte) {
			assertFalse(Lager.getLager().contains(p));
		}
		assertTrue(Lager.getLagerbestand().get(produkte.get(0)) == anzahl - 3);
		assertTrue(Lager.getLagerbestand().get(produkte.get(3)) == anzahl1 - 1);
	}

	/**
	 * Testet getProduktBestand
	 */
	@Test
	void testGetProduktBestand() {
		int anzahl = Lager.getLagerbestand().get(produkte.get(0));
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
		List<Produkt> temp_1 = Lager.getLager();
		Lager.save();
		Lager.load();
		assertTrue(temp_1.equals(Lager.getLager()));
	}

	/**
	 * Testet ProduktID
	 */
	@Test
	void testIDHash() {
		Produkt temp1 = new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29);
		Produkt temp2 = new Produkt("Coca Cola ", "Toller Geschmack", 0.99, 1.29);
		Produkt temp3 = new Produkt("Coca", "Toller Geschmack", 0.99, 1.29);
		assertTrue(temp1.getProduktID() == temp2.getProduktID());
		assertFalse(temp1.getProduktID() == temp3.getProduktID());

		HashMap<Produkt, Integer> testMap = new HashMap<Produkt, Integer>();
		testMap.put(temp1, 1);
		assertTrue(testMap.get(temp1) == 1);
		assertTrue(testMap.get(temp2) == 1);
		assertTrue(testMap.get(temp3) == null);
	}

	/**
	 * Testet ProduktAusDemSortimentEntfernen
	 */
	@Test
	void testProduktAusdemSortimentEntfernen() {
		Lager.getLagerbestand().clear();
		Produkt temp = new Produkt("Coca Cola", "Test", 0.49, 0.99);
		Lager.produktZumSortimentHinzufuegen(new Produkt("Coca Cola", "Test", 0.49, 0.99));
		Lager.addProdukt(temp);
		Lager.produktAusDemSortimentEntfernen(new Produkt("Coca Cola", "Test", 0.49, 0.99));
		assertTrue(Lager.sortimentAnzeigen().size() == 0);
	}

	/**
	 * Testet die Produktnamenliste
	 */
	@Test
	void testGetProduktNamenListe() {
		Lager.getProduktNamenListe().clear();
		Lager.produktZumSortimentHinzufuegen(new Produkt("Coca Cola", "Test", 0.49, 0.99));
		Lager.produktZumSortimentHinzufuegen(new Produkt("Fanta", "Test", 0.49, 0.99));
		assertTrue(Lager.getProduktNamenListe().contains("Coca Cola"));
		assertTrue(Lager.getProduktNamenListe().contains("Fanta"));
	}

}

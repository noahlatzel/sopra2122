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

import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;

public class LagerTest {
	ArrayList<Produkt> produkte;
	Produkt temp;
	Produkt temp1;
	Produkt temp2;
	Produkt temp3;

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
		temp = new Produkt("Coca Cola", "Toll", 0.49, 0.99);
		temp2 = new Produkt("Coca Cola", "Toll", 0.49, 0.99);
		temp1 = new Produkt("Coca Cola", "Toll", 0.49, 0.99);
		temp3 = new Produkt("Cola", "Toller G", 0.49, 0.99);
		Lager.produktZumSortimentHinzufuegen(temp);
		Lager.produktZumSortimentHinzufuegen(temp3);
		produkte.add(temp);
		produkte.add(temp1);
		produkte.add(temp2);
		produkte.add(temp3);
		Lager.addProdukt(temp3);
		Lager.addProdukt(temp);
		Lager.addProdukt(temp1);
		Lager.addProdukt(temp2);
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

		int temp_int = Lager.getProduktBestand(temp3);
		Lager.removeProdukt(temp3);
		assertTrue(!(Lager.getLager().contains(temp3)));
		assertTrue(Lager.getLagerbestand().get(temp3) == temp_int - 1);
		Lager.produktAusDemSortimentEntfernen(temp3);
		Lager.produktAusDemSortimentEntfernen(temp);
	}

	/**
	 * Testet addProdukte mit leerem Lager und mehreren Produkten.
	 */
	@Test
	void testAddProdukte() {
		for (Produkt p : produkte) {
			assertTrue(Lager.getLager().contains(p));
		}
		assertTrue(Lager.getLagerbestand().get(temp3) == 1);
		assertTrue(Lager.getLagerbestand().get(temp) == 3);
	}

	/**
	 * Testet removeProdukt mit gefuelltem Lager und mehreren Produkten.
	 */
	@Test
	void testRemoveProdukte() {
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
		int anzahl = Lager.getLagerbestand().get(temp);
		assertTrue(Lager.getProduktBestand(temp) == Lager.getProduktBestand(temp.getName()));
		assertTrue(Lager.getProduktBestand(temp) == anzahl);
		Lager.produktAusDemSortimentEntfernen(temp3);
		Lager.produktAusDemSortimentEntfernen(temp);
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
		Lager.produktAusDemSortimentEntfernen(new Produkt("Coca Cola", "Toll", 0.49, 0.99));
		Lager.produktAusDemSortimentEntfernen(new Produkt("Cola", "Toller G", 0.49, 0.99));
		Lager.save();
		Lager.load();
		assertTrue(temp_1.equals(Lager.getLager()));
	}

	/**
	 * Testet ProduktID
	 */
	@Test
	void testIDHash() {

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
		Lager.addProdukt(temp);
		Lager.produktAusDemSortimentEntfernen(new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29));
		assertTrue(!Lager.sortimentAnzeigen().contains(temp));
	}

	/**
	 * Testet die Produktnamenliste
	 */
	@Test
	void testGetProduktNamenListe() {
		assertTrue(Lager.getProduktNamenListe().contains("Coca Cola"));
		assertTrue(Lager.getProduktNamenListe().contains("Cola"));

		temp2.setName("Cola");
		temp2.setName("Coca Cola");
	}

}

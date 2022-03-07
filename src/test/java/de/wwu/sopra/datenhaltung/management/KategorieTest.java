package de.wwu.sopra.datenhaltung.management;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;

@TestInstance(Lifecycle.PER_CLASS)
public class KategorieTest {
	Kategorie kategorie_1;
	Kategorie kategorie_2;
	String name_1;
	String name_2;
	Produkt produkt_1;
	Produkt produkt_2;
	HashSet<Produkt> produkte;

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
		this.name_1 = "Softdrinks";
		this.name_2 = "Wasser";
		produkt_1 = new Produkt("Coca Cola", "Cola schmeckt lecker.", 0.99, 1.29);
		produkt_2 = new Produkt("Fanta", "Fanta schmeckt lecker", 0.99, 1.29);
		produkte = new HashSet<Produkt>();
		produkte.add(produkt_1);
		produkte.add(produkt_2);
		kategorie_1 = new Kategorie(name_1);
		kategorie_2 = new Kategorie(name_2);
	}

	/**
	 * Testet, ob der Konstruktor richtig funktioniert.
	 */
	@Test
	public void testKonstruktor() {
		assertTrue(kategorie_1.getName().equals(name_1));
	}

	/**
	 * Testet, ob addProdukt funktioniert.
	 */
	@Test
	public void testAddProdukt() {
		kategorie_1.addProdukt(produkt_1);
		assertTrue(kategorie_1.getProdukte().contains(produkt_1) && produkt_1.getKategorie() == kategorie_1);
	}

	/**
	 * Testet, ob removeProdukt funktioniert.
	 */
	@Test
	public void testRemoveProdukt() {
		kategorie_1.removeProdukt(produkt_1);
		assertTrue(!((kategorie_1.getProdukte().contains(produkt_1)) || (produkt_1.getKategorie() == kategorie_1)));
	}

	/**
	 * Testet, ob addProdukte funktioniert.
	 */
	@Test
	public void testAddProdukte() {
		kategorie_1.addProdukte(produkte);
		assertTrue(kategorie_1.getProdukte().equals(produkte));
		assertTrue(produkt_1.getKategorie() == kategorie_1);
		assertTrue(produkt_2.getKategorie() == kategorie_1);
	}

	/**
	 * Testet, ob removeProdukte funktioniert.
	 */
	@Test
	public void testRemoveProdukte() {
		kategorie_1.removeProdukte(produkte);
		assertTrue(kategorie_1.getProdukte().isEmpty());
		assertTrue(produkt_1.getKategorie() == null);
		assertTrue(produkt_2.getKategorie() == null);
	}

	/**
	 * Testet, ob die Methoden fuer Oberkategorie funktionieren.
	 */
	@Test
	public void testOberkategorie() {
		kategorie_1.setOberkategorie(kategorie_2);
		assertTrue(kategorie_1.getOberkategorie() == kategorie_2);
		assertTrue(kategorie_2.getUnterkategorien().contains(kategorie_1));
	}

	/**
	 * Testet, ob die Methoden fuer Unterkategorie funktionieren.
	 */
	@Test
	public void testUnterkategorie() {
		// Kategorien neu initialisieren
		kategorie_1 = new Kategorie(name_1);
		kategorie_2 = new Kategorie(name_2);
		kategorie_1.addUnterkategorie(kategorie_2);
		assertTrue(kategorie_1.getUnterkategorien().contains(kategorie_2));
		assertTrue(kategorie_2.getOberkategorie() == kategorie_1);
	}

	/**
	 * Testet, ob die Methoden fuer Mengen von Unterkategorien funktionieren.
	 */
	@Test
	public void testUnterkategorien() {
		// Kategorien neu initialisieren
		kategorie_1 = new Kategorie(name_1);
		kategorie_2 = new Kategorie(name_2);
		Kategorie kategorie_3 = new Kategorie("Saft");

		HashSet<Kategorie> kategorien = new HashSet<Kategorie>();
		kategorien.add(kategorie_2);
		kategorien.add(kategorie_3);

		kategorie_1.addUnterkategorien(kategorien);
		assertTrue(kategorie_1.getUnterkategorien().contains(kategorie_2));
		assertTrue(kategorie_1.getUnterkategorien().contains(kategorie_3));
		assertTrue(kategorie_2.getOberkategorie() == kategorie_1);
		assertTrue(kategorie_3.getOberkategorie() == kategorie_1);
	}

	/**
	 * Testet, ob eine Kategorie Unterkategorie von sich selbst sein kann.
	 */
	@Test
	public void testThrowAddUnterkategorie() {
		assertThrows(IllegalArgumentException.class, () -> {
			kategorie_1.addUnterkategorie(kategorie_1);
		});
	}

	/**
	 * Testet, ob eine Kategorie Unterkategorie von sich selbst sein kann.
	 */
	@Test
	public void testThrowAddUnterkategorien() {
		HashSet<Kategorie> kategorien = new HashSet<Kategorie>();
		kategorien.add(kategorie_1);
		assertThrows(IllegalArgumentException.class, () -> {
			kategorie_1.addUnterkategorien(kategorien);
		});
	}

	/**
	 * Testet, ob eine Kategorie Oberkategorie von sich selbst sein kann.
	 */
	@Test
	public void testThrowSetOberkategorie() {
		assertThrows(IllegalArgumentException.class, () -> {
			kategorie_1.setOberkategorie(kategorie_1);
		});
	}

	/**
	 * Testet, ob eine Kategorie einen leeren Namen haben kann.
	 */
	@Test
	public void testThrowsSetName() {
		assertThrows(IllegalArgumentException.class, () -> {
			kategorie_1.setName("");
		});
	}

	/**
	 * Testet, ob eine Kategorie einen leeren Namen gebaut werden kann.
	 */
	@Test
	public void testThrowsKonstruktorName() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Kategorie("");
		});
	}

	/**
	 * Testet null-Eingabe fuer setOberkategorie.
	 */
	@Test
	public void testNullSetOberkategorie() {
		kategorie_1.setOberkategorie(kategorie_2);
		kategorie_1.setOberkategorie(null);
		assertTrue(kategorie_1.getOberkategorie() == null);
	}

	/**
	 * Testet, ob removeUnterkategorien funktioniert
	 */
	@Test
	public void testRemoveUnterkategorien() {
		// Kategorien neu initialisieren
		kategorie_1 = new Kategorie(name_1);
		kategorie_2 = new Kategorie(name_2);
		Kategorie kategorie_3 = new Kategorie("Saft");

		HashSet<Kategorie> kategorien = new HashSet<Kategorie>();
		kategorien.add(kategorie_2);
		kategorien.add(kategorie_3);

		kategorie_1.addUnterkategorien(kategorien);
		kategorie_1.removeUnterkategorien(kategorien);

		assertTrue(kategorie_1.getUnterkategorien().isEmpty());
	}

}

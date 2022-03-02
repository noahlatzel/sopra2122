package de.wwu.sopra.datenhaltung.management;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;

class StatistikenTest {
	@BeforeEach
	void reset() {
		Lager.reset();
		FahrzeugRegister.reset();
	}

	/**
	 * Testet den Kontruktor.
	 */
	@Test
	void test() {
		Statistiken.setArbeitszeit(0);
		Statistiken.setAusgaben(0);
		Statistiken.setEinnahmen(0);
		Statistiken.setUmsatz(0);
		assertTrue(Statistiken.getArbeitszeit() == 0);
		assertTrue(Statistiken.getAusgaben() == 0);
		assertTrue(Statistiken.getEinnahmen() == 0);
		assertTrue(Statistiken.getUmsatz() == 0);
	}

	/**
	 * Testet addAusgaben
	 */
	@Test
	void testAddAusgaben() {
		Statistiken.setAusgaben(0);
		Statistiken.addAusgaben(1);
		Statistiken.addAusgaben(1);
		System.out.println(Statistiken.getAusgaben());
		assertTrue(Statistiken.getAusgaben() == 2);
	}

	/**
	 * Testet load und save.
	 */
	@Test
	void testLoad() {
		double temp_1 = Statistiken.getUmsatz();
		Statistiken.save();
		Statistiken.load();
		assertTrue(temp_1 == Statistiken.getUmsatz());
	}

}

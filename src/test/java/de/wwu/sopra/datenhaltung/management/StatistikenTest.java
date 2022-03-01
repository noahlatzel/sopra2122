package de.wwu.sopra.datenhaltung.management;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class StatistikenTest {

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

}

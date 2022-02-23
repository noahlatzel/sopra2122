package de.wwu.sopra.datenhaltung.management;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class StatistikenTest {

	/**
	 * Testet den Kontruktor.
	 */
	@Test
	void test() {
		Statistiken statistik = new Statistiken();
		assertTrue(statistik.getArbeitszeit() == 0);
		assertTrue(statistik.getAusgaben() == 0);
		assertTrue(statistik.getEinnahmen() == 0);
		assertTrue(statistik.getUmsatz() == 0);
	}

}

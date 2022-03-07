package de.wwu.sopra.datenhaltung.management;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;

class StatistikenTest {
	@BeforeEach
	void reset() {
		Lager.reset();
		FahrzeugRegister.reset();
		Statistiken.setAusgaben(1);
		Statistiken.setEinnahmen(0);
		Statistiken.setUmsatz(1);
	}

	@AfterEach
	void cleanAfter() {
		Lager.reset();
		FahrzeugRegister.reset();
	}

	/**
	 * Testet den Kontruktor.
	 */
	@Test
	void test() {
		Statistiken.setArbeitszeit(10);
		assertTrue(Statistiken.getArbeitszeit() == 10);
		assertTrue(Statistiken.getAusgaben() == 1);
		assertTrue(Statistiken.getEinnahmen() == 0);
		assertTrue(Statistiken.getUmsatz() == 1);
	}

	/**
	 * Testet addAusgaben
	 */
	@Test
	void testAddAusgaben() {
		Statistiken.setAusgaben(1);
		Statistiken.addAusgaben(1);
		Statistiken.addAusgaben(1);
		System.out.println(Statistiken.getAusgaben());
		assertTrue(Statistiken.getAusgaben() == 3);
		assertThrows(AssertionError.class, () -> {
			Statistiken.addAusgaben(-1);
		});
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

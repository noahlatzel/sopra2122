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
		Statistiken.setAusgaben(0);
		Statistiken.setEinnahmen(0);
		Statistiken.setUmsatz(0);
	}

	@AfterEach
	void cleanAfter() {
		Lager.reset();
		FahrzeugRegister.reset();
		Statistiken.setAusgaben(0);
		Statistiken.setEinnahmen(0);
		Statistiken.setUmsatz(0);
		Statistiken.setArbeitszeit(0);
	}

	/**
	 * Testet den Kontruktor.
	 */
	@Test
	void test() {
		Statistiken.setArbeitszeit(10);
		assertTrue(Statistiken.getArbeitszeit() == 10);
		assertTrue(Statistiken.getAusgaben() == 0);
		assertTrue(Statistiken.getEinnahmen() == 0);
		assertTrue(Statistiken.getUmsatz() == 0);
		assertThrows(AssertionError.class, () -> {
			Statistiken.setArbeitszeit(-1);
		});
		assertThrows(AssertionError.class, () -> {
			Statistiken.setUmsatz(-1);
		});
		assertThrows(AssertionError.class, () -> {
			Statistiken.setAusgaben(-1);
		});
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

	/**
	 * Teste Transaktion
	 */
	@Test
	void testTranskation() {
		Transaktion transaktion = new Transaktion("Test", 10);
		assertTrue(transaktion.getBeschreibung().equals("Test"));
		assertTrue(transaktion.getBetrag() == 10);
		assertTrue(transaktion.getDatum().equals(transaktion.getDatum()));
		Statistiken.addTransaktion(transaktion);
		assertTrue(Statistiken.getTransaktionshistorie().contains(transaktion));
	}

}

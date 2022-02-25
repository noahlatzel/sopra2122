package de.wwu.sopra.datenhaltung.verwaltung;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.management.Fahrzeug;

public class FahrzeugRegisterTest {
	FahrzeugRegister fahrzeugRegister = new FahrzeugRegister();
	Fahrzeug fahrzeug1;

	/**
	 * Testet die addFahrzeug Funktionalitaet.
	 */
	@Test
	void testAdd() {
		fahrzeug1 = new Fahrzeug(2603, 1);
		fahrzeugRegister.addFahrzeug(fahrzeug1);
		assertTrue(fahrzeugRegister.getFahrzeuge().contains(fahrzeug1));
		assertTrue(fahrzeugRegister.getFahrzeuge().size() == 1);
	}

	/**
	 * Testet die removeFahrzeug Funktionalitaet.
	 */
	@Test
	void testRemove() {
		fahrzeugRegister.removeFahrzeug(fahrzeug1);
		assertFalse(fahrzeugRegister.getFahrzeuge().contains(fahrzeug1));
		assertTrue(fahrzeugRegister.getFahrzeuge().size() == 0);
	}
}

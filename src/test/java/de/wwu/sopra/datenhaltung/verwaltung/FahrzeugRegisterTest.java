package de.wwu.sopra.datenhaltung.verwaltung;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.management.Fahrzeug;

public class FahrzeugRegisterTest {
	Fahrzeug fahrzeug1;

	// nach jedem Test wird die Liste geleert
	@AfterEach
	public void end() {
		FahrzeugRegister.removeFahrzeug(fahrzeug1);
	}

	/**
	 * Testet die addFahrzeug Funktionalitaet.
	 */
	@Test
	void testAdd() {
		fahrzeug1 = new Fahrzeug(22772, 1);
		FahrzeugRegister.addFahrzeug(fahrzeug1);

		assertTrue(FahrzeugRegister.getFahrzeuge().contains(fahrzeug1));
		// assertTrue(FahrzeugRegister.getFahrzeuge().size() == 1);

	}

	/**
	 * Testet die removeFahrzeug Funktionalitaet.
	 */
	@Test
	void testRemove() {
		fahrzeug1 = new Fahrzeug(22773, 1);
		FahrzeugRegister.addFahrzeug(fahrzeug1);
		FahrzeugRegister.removeFahrzeug(fahrzeug1);

		assertTrue(FahrzeugRegister.getFahrzeuge().contains(fahrzeug1) == false);
		// assertTrue(FahrzeugRegister.getFahrzeuge().size() == 0);
	}
}

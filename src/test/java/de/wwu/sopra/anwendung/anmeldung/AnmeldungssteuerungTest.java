package de.wwu.sopra.anwendung.anmeldung;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.benutzer.Fahrer;
import de.wwu.sopra.datenhaltung.benutzer.Inhaber;
import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.benutzer.Lagerist;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.verwaltung.BenutzerRegister;
import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;

/**
 * Testklasse zur Anmeldungssteuerung
 * 
 * @author Paul Dirksen
 *
 */
public class AnmeldungssteuerungTest {

	Anmeldungssteuerung anSt;
	Kunde kunde1;
	Fahrer fahrer1;
	Lagerist lagerist1;
	Inhaber inhaber1;

	@BeforeEach
	void reset() {
		Lager.reset();
		FahrzeugRegister.reset();
	}

	@AfterEach
	void cleanAfter() {
		Lager.reset();
		FahrzeugRegister.reset();
	}

	@BeforeEach
	public void setup() {
		kunde1 = new Kunde("Beton", "1234", "hart@test.de", "Abstiege 1", "Zementa", "test", "test");
		inhaber1 = new Inhaber("Bossbaby", "123", "iamnumberone", "Schlossalle Hotel", "Heribert", "Dietrich", "123");
		lagerist1 = new Lagerist("manni", "passwort", "123@online.de", "Elektrizitaetswerk", "Dieter", "Meiner", "123",
				inhaber1);
		fahrer1 = new Fahrer("killerman", "passwort", "123@online.de", "ostbad 1", "Herbert", "schulze", "123",
				inhaber1);

		BenutzerRegister.benutzerHinzufuegen(kunde1);
		BenutzerRegister.benutzerHinzufuegen(fahrer1);
		BenutzerRegister.benutzerHinzufuegen(lagerist1);
		BenutzerRegister.benutzerHinzufuegen(inhaber1);
		anSt = new Anmeldungssteuerung();
	}

	@Test
	public void anmeldenTest() {
		// Anmelden fuer jeden Benutzertyp
		anSt.anmelden("Beton", "1234");
		anSt.anmelden("Bossbaby", "123");
		anSt.anmelden("manni", "passwort");
		anSt.anmelden("killerman", "passwort");

		// Uebergeben eines nicht registrierten Benutzers
		anSt.anmelden("nicht registrierter", "Benutzer");

		// Uebergeben eines Benutzers mit einem falschen Passwort
		anSt.anmelden("Beton", "falschesPasswort");

		// Passwort und/oder Benutzername gleich null
		assertThrows(NullPointerException.class, () -> {
			anSt.anmelden("", null);
		});

		assertThrows(NullPointerException.class, () -> {
			anSt.anmelden(null, "");
		});

		assertThrows(NullPointerException.class, () -> {
			anSt.anmelden(null, null);
		});

	}

	/**
	 * Testet die Funktionalitaet der registrieren Funktion
	 */
	@Test
	public void registrieren() {

		// Fehlerhafte/Leere Eingabe
		assertThrows(NullPointerException.class, () -> {
			anSt.registrieren(null, null, null, null, null, null, null);
		});

		assertThrows(NullPointerException.class, () -> {
			anSt.registrieren(null, "Test2", "Test3", "Test4", "Test5", "Test6", "Test7");
		});

		assertThrows(NullPointerException.class, () -> {
			anSt.registrieren("Test1", null, "Test3", "Test4", "Test5", "Test6", "Test7");
		});

		assertThrows(NullPointerException.class, () -> {
			anSt.registrieren("Test1", "Test2", null, "Test4", "Test5", "Test6", "Test7");
		});

		assertThrows(NullPointerException.class, () -> {
			anSt.registrieren("Test1", "Test2", "Test3", null, "Test5", "Test6", "Test7");
		});

		assertThrows(NullPointerException.class, () -> {
			anSt.registrieren("Test1", "Test2", "Test3", "Test4", null, "Test6", "Test7");
		});

		assertThrows(NullPointerException.class, () -> {
			anSt.registrieren("Test1", "Test2", "Test3", "Test4", "Test5", null, "Test7");
		});

		assertThrows(NullPointerException.class, () -> {
			anSt.registrieren("Test1", "Test2", "Test3", "Test4", "Test5", "Test6", null);
		});

		// Registrieren eines neuen Benutzers
		anSt.registrieren("CoolerNutzer69", "passwort", "1", "2", "3", "4", "5");
		// Das Passwort ist hier zwar nicht eindeutig, aber reicht um zu bestaetigen
		// dass der Benutzer erfolgreich registriert worden ist
		assertTrue(BenutzerRegister.getBenutzerZuBenutzername("CoolerNutzer69").getPasswort().equals("passwort"));

		// Versucht einen Nutzer zu registrieren mit einem bereits vergebenen
		// Benutzernamen
		// Der neue Benutzer haette ein anderes Passwort, welche bei der Assertion
		// abgefragt wird
		anSt.registrieren("Beton", "doppelterNutzername", "hart@test.de", "Abstiege 1", "Zementa", "test", "test");
		assertFalse(BenutzerRegister.getBenutzerZuBenutzername("Beton").getPasswort().equals("doppelterNutzername"));

		// Versucht Nutzer mit einer leeren Eingabe zu registrieren.
		anSt.registrieren("", "Test", "Test", "Test", "Test", "Test", "Test");
		anSt.registrieren("TestB", "", "Test", "Test", "Test", "Test", "Test");
		anSt.registrieren("TestC", "Test", "", "Test", "Test", "Test", "Test");
		anSt.registrieren("TestD", "Test", "Test", "", "Test", "Test", "Test");
		anSt.registrieren("TestE", "Test", "Test", "Test", "", "Test", "Test");
		anSt.registrieren("TestF", "Test", "Test", "Test", "Test", "", "Test");
		anSt.registrieren("TestG", "Test", "Test", "Test", "Test", "Test", "");

		// Da das registrieren mit einer leeren Eingabe nicht erlaubt sein soll sollten
		// diese Benutzer nicht existieren
		assertNull(BenutzerRegister.getBenutzerZuBenutzername(""));
		assertNull(BenutzerRegister.getBenutzerZuBenutzername("TestB"));
		assertNull(BenutzerRegister.getBenutzerZuBenutzername("TestC"));
		assertNull(BenutzerRegister.getBenutzerZuBenutzername("TestD"));
		assertNull(BenutzerRegister.getBenutzerZuBenutzername("TestE"));
		assertNull(BenutzerRegister.getBenutzerZuBenutzername("TestF"));
		assertNull(BenutzerRegister.getBenutzerZuBenutzername("TestG"));
	}
}
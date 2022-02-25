package de.wwu.sopra.anwendung.anmeldung;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.benutzer.Fahrer;
import de.wwu.sopra.datenhaltung.benutzer.Inhaber;
import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.benutzer.Lagerist;
import de.wwu.sopra.datenhaltung.verwaltung.BenutzerRegister;

/**
 * Testklasse zur Anmeldungssteuerung
 * 
 * @author Paul Dirksen
 *
 */
public class AnmeldungssteuerungTest {

	BenutzerRegister benutzerReg = new BenutzerRegister();
	Anmeldungssteuerung anSt;
	Kunde kunde1;
	Fahrer fahrer1;
	Lagerist lagerist1;
	Inhaber inhaber1;

	@BeforeEach
	public void setup() {
		kunde1 = new Kunde("Beton", "1234", "hart@test.de", "Abstiege 1", "Zementa", "test", "test");
		inhaber1 = new Inhaber("Bossbaby", "123", "iamnumberone", "Schlossalle Hotel", "Heribert", "Dietrich", "123");
		lagerist1 = new Lagerist("manni", "passwort", "123@online.de", "Elektrizitaetswerk", "Dieter", "Meiner", "123",
				inhaber1);
		fahrer1 = new Fahrer("killerman", "passwort", "123@online.de", "ostbad 1", "Herbert", "schulze", "123",
				inhaber1);

		benutzerReg.benutzerHinzufuegen(kunde1);
		benutzerReg.benutzerHinzufuegen(fahrer1);
		benutzerReg.benutzerHinzufuegen(lagerist1);
		benutzerReg.benutzerHinzufuegen(inhaber1);
		anSt = new Anmeldungssteuerung(benutzerReg);
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

	@Test
	public void registrieren() {
		// Fehlerhafte/Leere Eingabe
		assertThrows(NullPointerException.class, () -> {
			anSt.registrieren(null, null, null, null, null, null, null);
		});
	}
}
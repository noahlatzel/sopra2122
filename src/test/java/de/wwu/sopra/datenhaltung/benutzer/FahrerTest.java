package de.wwu.sopra.datenhaltung.benutzer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import de.wwu.sopra.datenhaltung.management.FahrzeugStatus;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;

public class FahrerTest {

	// Atrribute fuer einen Beispiel Fahrer
	Fahrer fahrer;
	String benutzername;
	String passwort;
	String email;
	String adresse;
	String vorname;
	String name;
	String bankverbindung;
	Inhaber chef;

	@AfterEach
	void cleanAfter() {
		Lager.reset();
		FahrzeugRegister.reset();
	}

	// Beispielfahrer wird vor den Tests erstellt
	@BeforeEach
	void init() {
		benutzername = "testmaschine";
		passwort = "gutes Passwort";
		email = "hanswurst@online.de";
		adresse = "am arsch der Welt 1 ";
		vorname = "peter";
		name = "Pan";
		bankverbindung = "1234";
		chef = new Inhaber(benutzername, passwort, email, adresse, vorname, name, bankverbindung);
		fahrer = new Fahrer(benutzername, passwort, email, adresse, vorname, name, bankverbindung, chef);
	}

	// Der Constructor des Fahrers wird getestet
	@Test
	public void testFahrer() {

		assertTrue(fahrer.getBenutzername().equals(benutzername));
		assertTrue(fahrer.getPasswort().equals(passwort));
		assertTrue(fahrer.getEmail().equals(email));
		assertTrue(fahrer.getAdresse().equals(adresse));
		assertTrue(fahrer.getVorname().equals(vorname));
		assertTrue(fahrer.getVorname().equals(vorname));
		assertTrue(fahrer.getName().equals(name));
		assertTrue(fahrer.getBankverbindung().equals(bankverbindung));
		assertTrue(fahrer.getChef().equals(chef));

	}

	// Test von getRolle()
	@Test
	public void testGetRolle() {
		assertEquals(fahrer.getRolle(), Rolle.FAHRER);

	}

	// Test von get und set Fahrzeug
	@Test
	public void testSetGetFahrzeug() {
		// beispiel Fahrzeug
		float kapazitaet = 100;
		Fahrzeug fahrzeug = new Fahrzeug(kapazitaet);

		fahrer.setFahrzeug(fahrzeug);
		assertTrue(fahrer.getFahrzeug().equals(fahrzeug));

	}

	/**
	 * testet, dass sich der FahrzeugStatus aendert, sobald der Fahrer das Fahrzeug
	 * auswaehlt
	 */
	@Test
	void testFahrzeugStatusAenderungBeimZuordnen() {
		Fahrzeug fahrzeug = new Fahrzeug(300);
		FahrzeugStatus anfangStatus = fahrzeug.getStatus();
		fahrer.setFahrzeug(fahrzeug);
		FahrzeugStatus endeStatus = fahrzeug.getStatus();

		assertNotEquals(anfangStatus, endeStatus);
	}
}

package de.wwu.sopra.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LageristTest {

	// Atrribute fuer einen Beispiel Lageristen
	Lagerist lagerist;
	String benutzername;
	String passwort;
	String email;
	String adresse;
	String vorname;
	String name;
	String bankverbindung;
	Inhaber chef;

	// Beispiellagerist wird vor den Tests erstellt
	@BeforeEach
	void init() {
		String benutzername = "testmaschine";
		String passwort = "gutes Passwort";
		String email = "hanswurst@online.de";
		String adresse = "am arsch der Welt 1 ";
		String vorname = "peter";
		String name = "Pan";
		String bankverbindung = "1234";
		Inhaber chef = new Inhaber(benutzername, passwort, email, adresse, vorname, name, bankverbindung);
		lagerist = new Lagerist(benutzername, passwort, email, adresse, vorname, name, bankverbindung, chef);
	}

	// Der Constructor des Lageristen wird getestet
	@Test
	public void testLagerist() {

		assertTrue(lagerist.getBenutzername().equals(benutzername));
		assertTrue(lagerist.getPasswort().equals(passwort));
		assertTrue(lagerist.getEmail().equals(email));
		assertTrue(lagerist.getAdresse().equals(adresse));
		assertTrue(lagerist.getVorname().equals(vorname));
		assertTrue(lagerist.getVorname().equals(vorname));
		assertTrue(lagerist.getName().equals(name));
		assertTrue(lagerist.getBankverbindung().equals(bankverbindung));
		assertTrue(lagerist.getChef().equals(chef));

	}

	// Test von getRolle()
	@Test
	public void testGetRolle() {
		assertEquals(lagerist.getRolle(), Rolle.LAGERIST);

	}
}

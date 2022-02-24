package de.wwu.sopra.datenhaltung.verwaltung;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.benutzer.Benutzer;
import de.wwu.sopra.datenhaltung.benutzer.Inhaber;
import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.management.Produkt;

/**
 * Testet die Funktionalitaet des Benutzerregisters
 * 
 * @author Paul Dirksen
 *
 */
public class BenutzerRegisterTest {

	Kunde benutzer1;
	Benutzer benutzer2;
	Benutzer benutzer3;
	BenutzerRegister benutzerReg;
	Produkt produkt1;
	Produkt produkt2;
	Bestellung bestellung;
	List<Produkt> warenkorbTester;

	@BeforeEach
	public void setup() {
		benutzerReg = new BenutzerRegister();
		benutzer1 = new Kunde("Benutzername1", "Passwort1", "eineEmail", "eineAdresse", "einVorname", "einName",
				"eineBankverbindung");
		benutzer2 = new Kunde("Benutzername2", "Passwort2", "eineEmail", "eineAdresse", "einVorname", "einName",
				"eineBankverbindung");
		benutzer3 = new Inhaber("Benutzername3", "Passwort3", "eineEmail", "eineAdresse", "einVorname", "einName",
				"eineBankverbindung");
		produkt1 = new Produkt("Cola", "Softdrink", 1, 1);
		produkt2 = new Produkt("Fanta", "Softdrink", 2, 2);
		warenkorbTester = new ArrayList<Produkt>();
		warenkorbTester.add(produkt1);

	}

	/**
	 * Testet die Funktionalitaet des Hinzufuegen eines neuen Benutzers ins System.
	 */
	@Test
	public void benutzerHinzufuegenTest() {
		assertNull(benutzerReg.getBenutzerZuBenutzername("Benutzername1"));

		// Testet ob hinzugefuegter Benutzer in der Liste enthalten ist
		benutzerReg.benutzerHinzufuegen(benutzer1);
		assertTrue(benutzerReg.getBenutzerZuBenutzername("Benutzername1").equals(benutzer1));

		// Testet, ob Exception geworfen wird wenn null uebergeben wird.
		assertThrows(NullPointerException.class, () -> {
			benutzerReg.benutzerHinzufuegen(null);
		});
	}

	/**
	 * Testet die Funktionalitaet des Entfernens eines Benutzers aus dem System.
	 */
	@Test
	public void benutzerEntfernenTest() {
		// Registriert Kunden
		benutzerReg.benutzerHinzufuegen(benutzer1);

		// Entfernt Kunden
		benutzerReg.benutzerEntfernen(benutzer1);

		// Wird von der aufgerufenen Methode null zurueckgegeben, wurde der Benutzer
		// nicht gefunden
		assertFalse(benutzerReg.getBenutzerZuBenutzername("Benutzername1") != null);

		assertThrows(NullPointerException.class, () -> {
			benutzerReg.benutzerEntfernen(null);
		});
	}

	/**
	 * Testet die verschiedenen Funktionalitaeten des Warenkorbs
	 */
	@Test
	public void warenkorbFunktionalitaetTest() {

		// Registriert neuen Benutzer
		benutzerReg.benutzerHinzufuegen(benutzer1);

		// Fuegt produkt 1 zum Warenkorb des benutzer1 hinzu
		benutzerReg.produktZuWarenkorbHinzufuegen(benutzer1, produkt1);
		assertTrue(benutzerReg.getWarenkorb(benutzer1).contains(produkt1));

		// Vergleicht die Test Liste mit dem Warenkorb
		for (int i = 0; i < warenkorbTester.size(); i++) {
			assertEquals(warenkorbTester.get(i), benutzerReg.getWarenkorb(benutzer1).get(i));
		}

		// Enttfernt produkt 1 aus Warenkorb des benutzer1
		benutzerReg.produktAusWarenkorbEntfernen(benutzer1, produkt1);
		assertFalse(benutzerReg.getWarenkorb(benutzer1).contains(produkt1));

		// --------------------
		// Registriert Inhaber
		benutzerReg.benutzerHinzufuegen(benutzer3);
		benutzerReg.produktZuWarenkorbHinzufuegen(benutzer3, produkt1);

		// Ein Inhaber (und auch andere Mitarbeiter) haben keinen Warenkorb
		assertThrows(NullPointerException.class, () -> {
			benutzerReg.getWarenkorb(benutzer3).get(0);
		});

		// ---------------------
		// Bei leerem Benutzer und/oder leerem Produkt soll Exception geworfen werden
		assertThrows(NullPointerException.class, () -> {
			benutzerReg.produktZuWarenkorbHinzufuegen(null, produkt1);
		});

		assertThrows(NullPointerException.class, () -> {
			benutzerReg.produktZuWarenkorbHinzufuegen(benutzer1, null);
		});

		assertThrows(NullPointerException.class, () -> {
			benutzerReg.produktZuWarenkorbHinzufuegen(null, null);
		});

		assertThrows(NullPointerException.class, () -> {
			benutzerReg.produktAusWarenkorbEntfernen(null, produkt1);
		});

		assertThrows(NullPointerException.class, () -> {
			benutzerReg.produktAusWarenkorbEntfernen(benutzer1, null);
		});

		assertThrows(NullPointerException.class, () -> {
			benutzerReg.produktAusWarenkorbEntfernen(null, null);
		});
	}

	@Test
	public void bestellungenFunktionalitaetTest() {
		// Registrieren eines Kunden
		benutzerReg.benutzerHinzufuegen(benutzer1);

		// Hinzufuegen der neuen Bestellung zur Liste der Bestellungen des Kunden
		bestellung = new Bestellung(3, LocalDateTime.now(), warenkorbTester, benutzer1);
		benutzerReg.bestellungZuBestellungslisteHinzufuegen(benutzer1, bestellung);

		assertTrue(benutzerReg.getBestellungen(benutzer1).get(0).equals(bestellung));

		assertThrows(NullPointerException.class, () -> {
			benutzerReg.bestellungZuBestellungslisteHinzufuegen(benutzer1, null);
		});

		assertThrows(NullPointerException.class, () -> {
			benutzerReg.bestellungZuBestellungslisteHinzufuegen(null, bestellung);
		});

		assertThrows(NullPointerException.class, () -> {
			benutzerReg.bestellungZuBestellungslisteHinzufuegen(null, null);
		});
	}

}

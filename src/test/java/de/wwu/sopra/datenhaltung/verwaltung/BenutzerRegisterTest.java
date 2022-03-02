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
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.benutzer.Benutzer;
import de.wwu.sopra.datenhaltung.benutzer.Inhaber;
import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.management.Lager;
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
	Produkt produkt1;
	Produkt produkt2;
	Bestellung bestellung;
	List<Produkt> warenkorbTester;

	@BeforeEach
	void reset() {
		Lager.reset();
		FahrzeugRegister.reset();
	}

	@BeforeEach
	public void setup() {
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
	@Order(2)
	public void benutzerHinzufuegenTest() {

		assertNull(BenutzerRegister.getBenutzerZuBenutzername("Benutzername1"));

		// Testet ob hinzugefuegter Benutzer in der Liste enthalten ist
		BenutzerRegister.benutzerHinzufuegen(benutzer1);
		assertTrue(BenutzerRegister.getBenutzerZuBenutzername("Benutzername1").getBenutzername()
				.equals(benutzer1.getBenutzername()));

		// Testet, ob Exception geworfen wird wenn null uebergeben wird.
		assertThrows(NullPointerException.class, () -> {
			BenutzerRegister.benutzerHinzufuegen(null);
		});

		// Benutzerabfrage eines null-Benutzernamens
		assertThrows(NullPointerException.class, () -> {
			BenutzerRegister.getBenutzerZuBenutzername(null);
		});

		BenutzerRegister.benutzerEntfernen(benutzer1);
	}

	/**
	 * Testet die Funktionalitaet des Entfernens eines Benutzers aus dem System.
	 */
	@Test
	@Order(1)
	public void benutzerEntfernenTest() {
		// Registriert Kunden
		BenutzerRegister.benutzerHinzufuegen(benutzer1);

		// Entfernt Kunden
		BenutzerRegister.benutzerEntfernen(benutzer1);

		// Wird von der aufgerufenen Methode null zurueckgegeben, wurde der Benutzer
		// nicht gefunden
		assertNull(BenutzerRegister.getBenutzerZuBenutzername("Benutzername1"));

		assertThrows(NullPointerException.class, () -> {
			BenutzerRegister.benutzerEntfernen(null);
		});
	}

	/**
	 * Testet die verschiedenen Funktionalitaeten des Warenkorbs
	 */
	@Test
	@Order(3)
	public void warenkorbFunktionalitaetTest() {

		// Registriert neuen Benutzer
		BenutzerRegister.benutzerHinzufuegen(benutzer1);

		// Fuegt produkt 1 zum Warenkorb des benutzer1 hinzu
		BenutzerRegister.produktZuWarenkorbHinzufuegen(benutzer1, produkt1);
		assertTrue(BenutzerRegister.getWarenkorb(benutzer1).contains(produkt1));

		// Vergleicht die Test Liste mit dem Warenkorb
		for (int i = 0; i < warenkorbTester.size(); i++) {
			assertEquals(warenkorbTester.get(i), BenutzerRegister.getWarenkorb(benutzer1).get(i));
		}

		// Enttfernt produkt 1 aus Warenkorb des benutzer1
		BenutzerRegister.produktAusWarenkorbEntfernen(benutzer1, produkt1);
		assertFalse(BenutzerRegister.getWarenkorb(benutzer1).contains(produkt1));

		// --------------------
		// Registriert Inhaber
		BenutzerRegister.benutzerHinzufuegen(benutzer3);
		BenutzerRegister.produktZuWarenkorbHinzufuegen(benutzer3, produkt1);

		// Ein Inhaber (und auch andere Mitarbeiter) haben keinen Warenkorb
		assertThrows(NullPointerException.class, () -> {
			BenutzerRegister.getWarenkorb(benutzer3).get(0);
		});

		// ---------------------
		// Bei leerem Benutzer und/oder leerem Produkt soll Exception geworfen werden
		assertThrows(NullPointerException.class, () -> {
			BenutzerRegister.produktZuWarenkorbHinzufuegen(null, produkt1);
		});

		assertThrows(NullPointerException.class, () -> {
			BenutzerRegister.produktZuWarenkorbHinzufuegen(benutzer1, null);
		});

		assertThrows(NullPointerException.class, () -> {
			BenutzerRegister.produktZuWarenkorbHinzufuegen(null, null);
		});

		assertThrows(NullPointerException.class, () -> {
			BenutzerRegister.produktAusWarenkorbEntfernen(null, produkt1);
		});

		assertThrows(NullPointerException.class, () -> {
			BenutzerRegister.produktAusWarenkorbEntfernen(benutzer1, null);
		});

		assertThrows(NullPointerException.class, () -> {
			BenutzerRegister.produktAusWarenkorbEntfernen(null, null);
		});

		// Warenkorbabfrage eines null-Benutzers
		assertThrows(NullPointerException.class, () -> {
			BenutzerRegister.getWarenkorb(null);
		});

		BenutzerRegister.benutzerEntfernen(benutzer1);
	}

	@Test
	@Order(4)
	public void bestellungenFunktionalitaetTest() {
		// Registrieren eines Kunden
		BenutzerRegister.benutzerHinzufuegen(benutzer1);

		// Hinzufuegen der neuen Bestellung zur Liste der Bestellungen des Kunden
		bestellung = new Bestellung(LocalDateTime.now(), warenkorbTester, benutzer1);
		BenutzerRegister.bestellungZuBestellungslisteHinzufuegen(benutzer1, bestellung);

		assertTrue(BenutzerRegister.getBestellungen(benutzer1).get(0).equals(bestellung));

		assertThrows(NullPointerException.class, () -> {
			BenutzerRegister.bestellungZuBestellungslisteHinzufuegen(benutzer1, null);
		});

		assertThrows(NullPointerException.class, () -> {
			BenutzerRegister.bestellungZuBestellungslisteHinzufuegen(null, bestellung);
		});

		assertThrows(NullPointerException.class, () -> {
			BenutzerRegister.bestellungZuBestellungslisteHinzufuegen(null, null);
		});

		// Bestellungslistenabfrage eines null-Benutzers
		assertThrows(NullPointerException.class, () -> {
			BenutzerRegister.getBestellungen(null);
		});

		BenutzerRegister.benutzerEntfernen(benutzer1);

		// Ein nicht registrierter Benutzer sollte keine Liste mit Bestellungen haben.
		assertNull(BenutzerRegister.getBestellungen(benutzer1));
	}

	/**
	 * Testet load und save.
	 */
	@Test
	void testLoad() {
		List<BenutzerDatenTripel> temp = BenutzerRegister.getBenutzerListe();
		System.out.println(temp.toString());
		BenutzerRegister.save();
		BenutzerRegister.load();
		String temp_1 = BenutzerRegister.getBenutzerListe().toString();
		System.out.println(temp_1.toString());
		assertTrue(temp.toString().equals(temp_1));
	}

}

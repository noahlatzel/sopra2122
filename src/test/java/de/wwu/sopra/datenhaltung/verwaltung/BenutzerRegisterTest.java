package de.wwu.sopra.datenhaltung.verwaltung;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

	@AfterEach
	void cleanAfter() {
		Lager.reset();
		FahrzeugRegister.reset();
	}

	@BeforeEach
	void reset() {
		Lager.reset();
		FahrzeugRegister.reset();
		BenutzerRegister.reset();
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
	public void benutzerHinzufuegenTest() {

		assertNull(BenutzerRegister.getBenutzerZuBenutzername("Benutzername1"));

		// Testet ob hinzugefuegter Benutzer in der Liste enthalten ist
		BenutzerRegister.benutzerHinzufuegen(benutzer1);
		assertTrue(BenutzerRegister.getBenutzerZuBenutzername("Benutzername1").getBenutzername()
				.equals(benutzer1.getBenutzername()));

		BenutzerRegister.benutzerEntfernen(benutzer1);
	}

	/**
	 * Testet die Funktionalitaet des Entfernens eines Benutzers aus dem System.
	 */
	@Test
	public void benutzerEntfernenTest() {
		// Registriert Kunden
		BenutzerRegister.benutzerHinzufuegen(benutzer1);

		// Entfernt Kunden
		BenutzerRegister.benutzerEntfernen(benutzer1);

		// Wird von der aufgerufenen Methode null zurueckgegeben, wurde der Benutzer
		// nicht gefunden
		assertNull(BenutzerRegister.getBenutzerZuBenutzername("Benutzername1"));

	}

	/**
	 * Testet die verschiedenen Funktionalitaeten des Warenkorbs
	 */
	@Test
	public void warenkorbFunktionalitaetTest() {

		// Registriert neuen Benutzer
		BenutzerRegister.benutzerHinzufuegen(benutzer1);

		// Fuegt produkt 1 zum Warenkorb des benutzer1 hinzu
		BenutzerRegister.produktZuWarenkorbHinzufuegen(benutzer1, produkt1);
		assertTrue(BenutzerRegister.getWarenkorb(benutzer1).getProdukte().contains(produkt1));

		// Vergleicht die Test Liste mit dem Warenkorb
		for (int i = 0; i < warenkorbTester.size(); i++) {
			assertEquals(warenkorbTester.get(i), BenutzerRegister.getWarenkorb(benutzer1).getProdukte().get(i));
		}

		// Enttfernt produkt 1 aus Warenkorb des benutzer1
		BenutzerRegister.produktAusWarenkorbEntfernen(benutzer1, produkt1);
		assertFalse(BenutzerRegister.getWarenkorb(benutzer1).getProdukte().contains(produkt1));

		// --------------------
		// Registriert Inhaber
		BenutzerRegister.benutzerHinzufuegen(benutzer3);
		BenutzerRegister.produktZuWarenkorbHinzufuegen(benutzer3, produkt1);

		// Ein Inhaber (und auch andere Mitarbeiter) haben keinen Warenkorb
		assertThrows(NullPointerException.class, () -> {
			BenutzerRegister.getWarenkorb(benutzer3).getProdukte().get(0);
		});

		BenutzerRegister.benutzerEntfernen(benutzer1);
	}

	@Test
	public void bestellungenFunktionalitaetTest() {
		// Registrieren eines Kunden
		BenutzerRegister.benutzerHinzufuegen(benutzer1);

		Lager.produktZumSortimentHinzufuegen(new Produkt("Cola", "Lecker", 0.49, 0.99));
		Lager.produktZumSortimentHinzufuegen(new Produkt("Fanta", "Lecker", 0.49, 0.99));

		// Hinzufuegen der neuen Bestellung zur Liste der Bestellungen des Kunden
		bestellung = new Bestellung(LocalDateTime.now(), warenkorbTester, benutzer1);
		BenutzerRegister.bestellungZuBestellungslisteHinzufuegen(benutzer1, bestellung);

		assertTrue(BenutzerRegister.getBestellungen(benutzer1).get(0).equals(bestellung));

		BenutzerRegister.benutzerEntfernen(benutzer1);

		// Ein nicht registrierter Benutzer sollte keine Liste mit Bestellungen haben.
		assertNull(BenutzerRegister.getBestellungen(benutzer1));
	}

	/**
	 * Testet die Abfrage eines Benutzers anhand des Banutzernamens.
	 */
	@Test
	void getBenutzerZuBenutzername() {
		BenutzerRegister.benutzerHinzufuegen(benutzer1);

		assertEquals(BenutzerRegister.getBenutzerZuBenutzername("Benutzername1"), benutzer1);
		assertNull(BenutzerRegister.getBenutzerZuBenutzername("KeinBenutzername"));

		BenutzerRegister.benutzerEntfernen(benutzer1);
	}

	/**
	 * Testet load und save.
	 */
	@Test
	void testLoad() {
		List<Benutzer> temp = BenutzerRegister.getBenutzerListe();
		BenutzerRegister.save();
		BenutzerRegister.load();
		String temp_1 = BenutzerRegister.getBenutzerListe().toString();
		assertTrue(temp.toString().equals(temp_1));
	}

}

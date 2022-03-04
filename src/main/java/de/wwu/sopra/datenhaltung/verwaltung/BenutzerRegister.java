package de.wwu.sopra.datenhaltung.verwaltung;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.datenhaltung.benutzer.Benutzer;
import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.benutzer.Rolle;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.bestellung.Warenkorb;
import de.wwu.sopra.datenhaltung.management.Produkt;

/**
 * Die Klasse verwaltet die Speicherung aller Benutzerdaten. Dies geschieht
 * ueber ein Tripel (siehe BenutzerDatenTripel-Klasse), welches den Benutzer
 * sowie dessen Bestellungsliste und Warenkorb speichert.
 * 
 * @author Paul Dirksen
 *
 */
public class BenutzerRegister implements Serializable {

	/**
	 * SerialisierungsID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Liste der Benutzerdaten
	 */
	private static List<Benutzer> benutzerListe = new ArrayList<Benutzer>();
	/**
	 * Pfad zur Serialisierung
	 */
	private static String path = "benutzerReg.ser";
	/**
	 * Zaehler fuer die Bestellungen
	 */
	private static int zaehlerBestellung = 0;

	/**
	 * Singleton Konstruktor
	 */
	private BenutzerRegister() {

	}

	/**
	 * Fuegt einen neuen Benutzer der Liste der Benutzer hinzu
	 * 
	 * @param benutzer Hinzuzufuegender Benutzer.
	 * @pre Uebergebener Benutzer nicht null.
	 */
	public static void benutzerHinzufuegen(Benutzer benutzer) {
		assert benutzer != null : "Vorbedingung nicht erfuellt: Benutzer ist null";

		benutzerListe.add(benutzer);
	}

	/**
	 * Entfernt den uebergebenen Benutzer aus der Liste der Benutzer.
	 * 
	 * @param benutzer wird entfernt
	 * @pre Uebergebener Benutzer nicht null.
	 */
	public static void benutzerEntfernen(Benutzer benutzer) {
		assert benutzer != null : "Vorbedingung nicht erfuellt: Benutzer ist null";

		// Wenn Bedingung nicht erfuellt ist, ist der Benutzer nicht registriert.
		if (benutzerListe.contains(benutzer)) {
			benutzerListe.remove(benutzer);
			if (benutzer.getRolle() == Rolle.KUNDE) {
				Kunde kunde = (Kunde) benutzer;
				kunde.kundeEntfernen();
			}
			benutzer = null;
		}
	}

	/**
	 * Gibt einen Benutzer zurueck, falls der uebergebene benutzername zu einem
	 * Benutzer im System gehoert
	 * 
	 * @param benutzername benutzername
	 * @return Benutzer Benutzer
	 * @pre Uebergebener Benutzername nicht null.
	 */
	public static Benutzer getBenutzerZuBenutzername(String benutzername) {
		assert benutzername != null : "Vorbedingung nicht erfuellt: Benutzername ist null";

		Benutzer gesuchterBenutzer = null;

		// Lineare Suche durch die Liste der Benutzer nach einem Benutzer mit dem
		// uebergebenen Benutzernamen.
		for (int i = 0; i < benutzerListe.size(); i++) {
			if (benutzerListe.get(i).getBenutzername().equals(benutzername)) {
				gesuchterBenutzer = benutzerListe.get(i);
			}
		}

		return gesuchterBenutzer;
	}

	/**
	 * Fuegt der Liste der Bestellungen des uebergebenen Benutzers die uebergebene
	 * Bestellung hinzu, falls dieser ein registrierter Kunde ist.
	 * 
	 * @param benutzer   Uebergebene Bestellung wird der Liste der Bestellungen
	 *                   dieses Benutzers hinzugefuegt.
	 * @param bestellung Bestellung wird der Liste der Bestellungen des uebergebenen
	 *                   Benutzers hinzugefuegt.
	 * @pre Uebergebene Parameter nicht null.
	 */
	public static void bestellungZuBestellungslisteHinzufuegen(Benutzer benutzer, Bestellung bestellung) {
		assert (benutzer != null) && (bestellung != null)
				: "Vorbedingung nicht erfuellt: Benutzer ist null oder Bestellung ist null";

		// Wenn Bedingung nicht erfuellt ist, ist der Benutzer nicht als Kunde
		// registriert.
		if (benutzer.getRolle() == Rolle.KUNDE) {
			Kunde kunde = (Kunde) benutzer;
			kunde.bestellungHinzufuegen(bestellung);
		}
	}

	/**
	 * Fuegt das uebergebene Produkt dem Warenkorb des uebergebenen Benutzers hinzu,
	 * falls dieser als Kunde registriert ist.
	 * 
	 * @param benutzer Uebergebenes Produkt wird zum Warenkorb des Benutzers
	 *                 hinzugefuegt.
	 * @param produkt  Wird zum Warenkorb des uebergebenden Benutzers hinzugefuegt.
	 * @pre Uebergebene Parameter nicht null.
	 */
	public static void produktZuWarenkorbHinzufuegen(Benutzer benutzer, Produkt produkt) {
		assert (benutzer != null) && (produkt != null)
				: "Vorbedingung nicht erfuellt: Benutzer ist null oder Produkt ist null";

		// Wenn Bedingung nicht erfuellt ist, ist der Benutzer nicht als
		// Kunderegistriert.
		if (benutzer.getRolle() == Rolle.KUNDE) {
			Kunde kunde = (Kunde) benutzer;
			kunde.getWarenkorb().produktHinzufuegen(produkt);
		}
	}

	/**
	 * Entfernt das uebergebene Produkt aus dem Warenkorb des uebergebenen
	 * Benutzers, sofern dieser als Kunde registriert ist.
	 * 
	 * @param benutzer Uebergebenes Produkt wird aus dem Warenkorb des Benutzers
	 *                 enttfernt.
	 * @param produkt  Wird aus dem Warenkorb des uebergebenen Benutzers entfernt.
	 * @pre Uebergebene Parameter nicht null.
	 */
	public static void produktAusWarenkorbEntfernen(Benutzer benutzer, Produkt produkt) {
		assert (benutzer != null) && (produkt != null)
				: "Vorbedingung nicht erfuellt: Benutzer ist null oder Produkt ist null";

		// Wenn Bedingung nicht erfuellt ist, ist der Benutzer nicht als Kunde
		// registriert.
		if (benutzer.getRolle() == Rolle.KUNDE) {
			Kunde kunde = (Kunde) benutzer;
			kunde.getWarenkorb().produktEntfernen(produkt);
		}
	}

	/**
	 * Gibt den Warenkorb des uebergebenen Benutzers zurueck.
	 * 
	 * @param benutzer Warenkorb des Benutzers wird zurueckgegeben.
	 * @return Warenkorb des uebergebenen Benutzers, null falls der Benutzer nicht
	 *         als Kunde registriert ist.
	 */
	public static Warenkorb getWarenkorb(Benutzer benutzer) {
		assert benutzer != null : "Vorbedingung nicht erfuellt: Benutzer ist null";

		Warenkorb warenkorb = null;

		// Wenn Bedingung nicht erfuellt ist, ist der Benutzer kein registrierter Kunde.
		if (benutzer.getRolle() == Rolle.KUNDE) {
			Kunde kunde = (Kunde) benutzer;
			warenkorb = kunde.getWarenkorb();
		}

		return warenkorb;
	}

	/**
	 * Gibt die Liste der Bestellungen des uebergebenen Benutzers zurueck.
	 * 
	 * @param benutzer Liste der Bestellungen des Benutzers wird zurueckgegeben.
	 * @return Liste der Bestellungen des uebergebenen Benutzers, null falls der
	 *         Benutzer nicht als Kunde registriert ist.
	 * @pre Uebergebene Parameter nicht null
	 */
	public static List<Bestellung> getBestellungen(Benutzer benutzer) {
		assert benutzer != null : "Vorbedingung nicht erfuellt: Benutzer ist null";

		List<Bestellung> bestellungen = null;

		// Wenn Bedingung nicht erfuellt ist, ist der Benutzer kein registrierter Kunde.

		if (benutzer.getRolle() == Rolle.KUNDE) {
			Kunde kunde = (Kunde) benutzer;
			bestellungen = kunde.getBestellungen();
		}

		return bestellungen;
	}

	/**
	 * Gibt die Benutzerliste zurueck.
	 * 
	 * @return Die Benutzerliste
	 */
	public static List<Benutzer> getBenutzerListe() {
		return benutzerListe;
	}

	/**
	 * Deserialisiert das BenutzerRegister.
	 */
	public static void load() {
		SerialisierungPipeline<List<Benutzer>> sp = new SerialisierungPipeline<List<Benutzer>>();
		BenutzerRegister.benutzerListe = sp.deserialisieren(path);
	}

	/**
	 * Serialisiert das BenutzerRegister.
	 */
	public static void save() {
		SerialisierungPipeline<List<Benutzer>> sp = new SerialisierungPipeline<List<Benutzer>>();
		sp.serialisieren(BenutzerRegister.getBenutzerListe(), path);
	}

	/**
	 * Gibt den Zaehler fuer Bestellung zurueck.
	 * 
	 * @return Den Zaehler fuer Bestellung
	 */
	public static int getZaehlerBestellung() {
		return ++zaehlerBestellung;
	}

}
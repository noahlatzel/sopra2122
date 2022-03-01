package de.wwu.sopra.datenhaltung.verwaltung;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.datenhaltung.benutzer.Benutzer;
import de.wwu.sopra.datenhaltung.benutzer.Rolle;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
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
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static List<BenutzerDatenTripel> benutzerListe = new ArrayList<BenutzerDatenTripel>();

	/**
	 * Fuegt einen neuen Benutzer der Liste der Benutzer hinzu
	 * 
	 * @param benutzer
	 */
	public static void benutzerHinzufuegen(Benutzer benutzer) throws NullPointerException {
		if (!(benutzer == null)) {
			benutzerListe.add(new BenutzerDatenTripel(benutzer));
		} else {
			throw new NullPointerException("Leerer Benutzer uebergeben!");
		}
	}

	/**
	 * Entfernt den uebergebenen Benutzer aus der Liste der Benutzer.
	 * 
	 * @param benutzer wird entfernt
	 */
	public static void benutzerEntfernen(Benutzer benutzer) throws NullPointerException {
		if (!(benutzer == null)) {

			// Wenn Bedingung nicht erfuellt ist, ist der Benutzer nicht registriert.
			int i = getBenutzerId(benutzer);
			if (i >= 0) {
				benutzerListe.remove(i);
			}
		} else {
			throw new NullPointerException("Leerer Benutzer uebergeben!");
		}
	}

	/**
	 * Gibt einen Benutzer zurueck, falls der uebergebene benutzername zu einem
	 * Benutzer im System gehoert
	 * 
	 * @param benutzername
	 * @return Benutzer
	 */
	public static Benutzer getBenutzerZuBenutzername(String benutzername) throws NullPointerException {
		Benutzer gesuchterBenutzer = null;

		if (!(benutzername == null)) {

			// Lineare Suche durch die Liste der Benutzer nach einem Benutzer mit dem
			// uebergebenen Benutzernamen.
			for (int i = 0; i < benutzerListe.size(); i++) {
				if (benutzerListe.get(i).getBenutzer().getBenutzername().equals(benutzername)) {
					gesuchterBenutzer = benutzerListe.get(i).getBenutzer();
				}
			}
		} else {
			throw new NullPointerException("Leerer Benutzername uebergeben!");
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
	 * @throws NullPointerException Falls leerer Benutzer oder leere Bestellung
	 *                              uebergeben wird.
	 */
	public static void bestellungZuBestellungslisteHinzufuegen(Benutzer benutzer, Bestellung bestellung)
			throws NullPointerException {
		if (!(benutzer == null) && !(bestellung == null)) {

			// Wenn Bedingung nicht erfuellt ist, ist der Benutzer nicht als Kunde
			// registriert.
			int i = getBenutzerId(benutzer);
			if (i >= 0 && benutzer.getRolle() == Rolle.KUNDE) {
				benutzerListe.get(i).getBestellungen().add(bestellung);
			}
		} else {
			throw new NullPointerException("Leerer Benutzer und/oder leere Bestellung uebergeben!");
		}
	}

	/**
	 * Fuegt das uebergebene Produkt dem Warenkorb des uebergebenen Benutzers hinzu,
	 * falls dieser als Kunde registriert ist.
	 * 
	 * @param benutzer Uebergebenes Produkt wird zum Warenkorb des Benutzers
	 *                 hinzugefuegt.
	 * @param produkt  Wird zum Warenkorb des uebergebenden Benutzers hinzugefuegt.
	 * @throws NullPointerException Wenn
	 */
	public static void produktZuWarenkorbHinzufuegen(Benutzer benutzer, Produkt produkt) throws NullPointerException {
		if (!(benutzer == null) && !(produkt == null)) {

			// Wenn Bedingung nicht erfuellt ist, ist der Benutzer nicht als
			// Kunderegistriert.
			int i = getBenutzerId(benutzer);
			if (i >= 0 && benutzer.getRolle() == Rolle.KUNDE) {
				benutzerListe.get(i).getWarenkorb().add(produkt);
			}
		} else {
			throw new NullPointerException("Leerer Benutzer und/oder leeres Produkt uebergeben!");
		}
	}

	/**
	 * Entfernt das uebergebene Produkt aus dem Warenkorb des uebergebenen
	 * Benutzers, sofern dieser als Kunde registriert ist.
	 * 
	 * @param benutzer Uebergebenes Produkt wird aus dem Warenkorb des Benutzers
	 *                 enttfernt.
	 * @param produkt  Wird aus dem Warenkorb des uebergebenen Benutzers entfernt.
	 * @throws NullPointerException Wenn leerer Benutzers und/oder leeres Produkt
	 *                              uebergeben wird.
	 */
	public static void produktAusWarenkorbEntfernen(Benutzer benutzer, Produkt produkt) throws NullPointerException {
		if (!(benutzer == null) && !(produkt == null)) {

			// Wenn Bedingung nicht erfuellt ist, ist der Benutzer nicht als Kunde
			// registriert.
			int i = getBenutzerId(benutzer);
			if (i >= 0 && benutzer.getRolle() == Rolle.KUNDE) {
				benutzerListe.get(i).getWarenkorb().remove(produkt);
			}
		} else {
			throw new NullPointerException("Leerer Benutzer und/oder leeres Produkt uebergeben!");
		}
	}

	/**
	 * Gibt den Warenkorb des uebergebenen Benutzers zurueck.
	 * 
	 * @param benutzer Warenkorb des Benutzers wird zurueckgegeben.
	 * @return Warenkorb des uebergebenen Benutzers, null falls der Benutzer nicht
	 *         als Kunde registriert ist.
	 */
	public static List<Produkt> getWarenkorb(Benutzer benutzer) throws NullPointerException {
		List<Produkt> warenkorb = null;

		if (!(benutzer == null)) {

			// Wenn Bedingung nicht erfuellt ist, ist der Benutzer kein registrierter Kunde.
			int i = getBenutzerId(benutzer);
			if (i >= 0 && benutzer.getRolle() == Rolle.KUNDE) {
				warenkorb = benutzerListe.get(i).getWarenkorb();
			}
		} else {
			throw new NullPointerException("Leerer Benutzer uebergeben!");
		}
		return warenkorb;
	}

	/**
	 * Gibt die Liste der Bestellungen des uebergebenen Benutzers zurueck.
	 * 
	 * @param benutzer Liste der Bestellungen des Benutzers wird zurueckgegeben.
	 * @return Liste der Bestellungen des uebergebenen Benutzers, null falls der
	 *         Benutzer nicht als Kunde registriert ist.
	 */
	public static List<Bestellung> getBestellungen(Benutzer benutzer) throws NullPointerException {
		List<Bestellung> bestellungen = null;

		if (!(benutzer == null)) {

			// Wenn Bedingung nicht erfuellt ist, ist der Benutzer kein registrierter Kunde.
			int i = getBenutzerId(benutzer);
			if (i >= 0 && benutzer.getRolle() == Rolle.KUNDE) {
				bestellungen = benutzerListe.get(i).getBestellungen();
			}
		} else {
			throw new NullPointerException("Leerer Benutzer uebergeben!");
		}
		return bestellungen;
	}

	/**
	 * Gibt den Index der benutzerListe zurueck an welcher der uebergebene Benutzer
	 * steht. Wenn der Benutzer nicht in der Liste der Benutzer ist, wird -1
	 * zurueckgegeben.
	 * 
	 * @param benutzer Zu suchender Benutzer im System.
	 * @return -1, wenn Benutzer nicht registriert, sonst Index des Benutzers in der
	 *         Liste
	 * @pre benutzer ist nicht null
	 */
	private static int getBenutzerId(Benutzer benutzer) {
		int n = -1;

		assert benutzer != null : "Benutzer ist null!";
		// Sucht in der TripelListe nach dem Benutzer
		for (int i = 0; i < benutzerListe.size(); i++) {
			if (benutzerListe.get(i).getBenutzer().getBenutzername().equals(benutzer.getBenutzername())) {
				return i;
			}
		}
		return n;
	}

	/**
	 * Gibt die Benutzerliste zurueck.
	 * 
	 * @return Die Benutzerliste
	 */
	public static List<BenutzerDatenTripel> getBenutzerListe() {
		return benutzerListe;
	}
}
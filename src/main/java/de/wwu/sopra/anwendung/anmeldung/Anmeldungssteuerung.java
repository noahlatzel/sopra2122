package de.wwu.sopra.anwendung.anmeldung;

import de.wwu.sopra.datenhaltung.benutzer.Benutzer;
import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.verwaltung.BenutzerRegister;

/**
 * Die Klasse verwaltet den Zugriff auf die Startseite, den Anmeldeprozess sowie
 * die Registrierung neuer Kunden. Dazu werden die Grenzklassen Startseite,
 * Anmelden und Registrieren verwendet.
 * 
 * @author Paul Dirksen
 *
 */
public class Anmeldungssteuerung {
	/**
	 * Leerer Konstruktor
	 */
	public Anmeldungssteuerung() {

	}

	/**
	 * 
	 * @param benutzername Zu ueberpruefender Benutzername
	 * @param passwort     Zu ueberpruefendes Passwort.
	 * @return Der Benutzer, der angemeldet wurde.
	 * @throws NullPointerException Es wurde eine leere Eingabe uebergeben.
	 */
	public Benutzer anmelden(String benutzername, String passwort) throws NullPointerException {

		Benutzer benutzer = null;

		if (!(benutzername == null) && !(passwort == null)) {

			// Falls der Benutzername im System bekannt ist wird der zugehoerige Benutzer
			// zurueckgegeben.
			Benutzer temp = BenutzerRegister.getBenutzerZuBenutzername(benutzername);

			if (!(temp == null)) {
				// HIGH SECURITY PASSWORT CHECK
				if (temp.getPasswort().equals(passwort)) {
					return temp;
				} else {
					System.out.println("Falsches Passwort!");
					// TODO
				}
			} else {
				System.out.println("Benutzername nicht vorhanden!");
				// TODO
			}
		} else {
			throw new NullPointerException("Leere Eingabe!");
		}

		return benutzer;
	}

	/**
	 * 
	 * @param benutzername   Benutzername des neuen Kunden. Darf noch nicht vergeben
	 *                       sein.
	 * @param passwort       Passwort des neuen Kunden.
	 * @param email          Email des neuen Kunden.
	 * @param adresse        Adresse des neuen Kunden.
	 * @param vorname        Vorname des neuen Kunden.
	 * @param name           Name des neuen Kunden.
	 * @param bankverbindung Bankverbindung des neuen Kunden
	 * @throws NullPointerException Ein Null-Objekt wurde uebergeben
	 */
	public void registrieren(String benutzername, String passwort, String email, String adresse, String vorname,
			String name, String bankverbindung) throws NullPointerException {
		// Falls null uebergeben wurde:
		if ((!(benutzername == null || passwort == null || email == null || adresse == null || vorname == null
				|| name == null || bankverbindung == null))) {

			if (!(benutzername.isBlank() || passwort.isBlank() || email.isBlank() || adresse.isBlank()
					|| vorname.isBlank() || name.isBlank() || bankverbindung.isBlank())) {

				// Pruefe ob Benutzername im System vorhanden
				Benutzer benutzer = BenutzerRegister.getBenutzerZuBenutzername(benutzername);

				if (benutzer == null) {
					BenutzerRegister.benutzerHinzufuegen(
							new Kunde(benutzername, passwort, email, adresse, vorname, name, bankverbindung));
				} else {
					// Falls Benutzername schon vergeben: Fehlermeldung
					System.out.println("Benutzername schon vergeben!");
					// TODO Fehlermeldung in der Oberflaeche
				}
			} else {
				// Falls ein leerer String uebergeben wurde:
				System.out.println("Leere Eingabe!");
				// TODO Fehlermeldung in der Oberflaeche
			}
		} else {
			throw new NullPointerException("Fehlerhafte Eingabe!");
		}
	}

}
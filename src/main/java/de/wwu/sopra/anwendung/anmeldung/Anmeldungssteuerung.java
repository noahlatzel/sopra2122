package de.wwu.sopra.anwendung.anmeldung;

import de.wwu.sopra.datenhaltung.benutzer.Benutzer;

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
	 * Bearbeitet den Anmeldevorgang zu den eingegebenen Daten(Benutzername,
	 * Passwort) und leitet im Falle einer erfolgreichen Anmeldung an die der Rolle
	 * des Benutzers entsprechende Steuerungsklasse weiter
	 * 
	 * @param benutzername
	 * @param passwort
	 */
	public void anmelden(String benutzername, String passwort) {

	}

	/**
	 * Registriert bei korrekten Eingaben einen neuen Kunden im System
	 * 
	 * @param benutzername
	 * @param passwort
	 * @param email
	 * @param adresse
	 * @param vorname
	 * @param name
	 * @param bankverbindung
	 */
	public void registrieren(String benutzername, String passwort, String email, String adresse, String vorname,
			String name, String bankverbindung) {
		if (!(benutzername == null || passwort == null || email == null || adresse == null || vorname == null
				|| name == null || bankverbindung == null)) {
			System.out.println("Eingaben gueltig!");

			if (true) { // Falls Benutzername schon vergeben: Fehlermeldung

			}
		}
	}

	/**
	 * Bearbeitet die Weiterleitung von der Anmeldungssteuerung an die dem
	 * uebergebenen Benutzer zugehoerige Steuerungsklasse
	 * 
	 * @param benutzer
	 * @pre Der uebergebene Nutzer ist ein im System eingetragener Benutzer
	 */
	public void leiteWeiter(Benutzer benutzer) {
		switch (benutzer.getRolle()) {
		case KUNDE:
			System.out.println("Kunde anmelden!");
			break;
		case FAHRER:
			System.out.println("Fahrer anmelden!");
			break;
		case LAGERIST:
			System.out.println("Lagerist anmelden!");
			break;
		case INHABER:
			System.out.println("Inhaber anmelden!");
			break;
		}
	}

}
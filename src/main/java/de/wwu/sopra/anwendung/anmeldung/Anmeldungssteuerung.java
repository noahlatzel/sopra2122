package de.wwu.sopra.anwendung.anmeldung;

import de.wwu.sopra.anwendung.kunde.Kundensteuerung;
import de.wwu.sopra.anwendung.mitarbeiter.Fahrersteuerung;
import de.wwu.sopra.anwendung.mitarbeiter.Inhabersteuerung;
import de.wwu.sopra.anwendung.mitarbeiter.Lageristensteuerung;
import de.wwu.sopra.datenhaltung.benutzer.Benutzer;
import de.wwu.sopra.datenhaltung.benutzer.Fahrer;
import de.wwu.sopra.datenhaltung.benutzer.Inhaber;
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

	public Anmeldungssteuerung() {

	}

	/**
	 * Bearbeitet den Anmeldevorgang zu den eingegebenen Daten(Benutzername,
	 * Passwort) und leitet im Falle einer erfolgreichen Anmeldung an die der Rolle
	 * des Benutzers entsprechende Steuerungsklasse weiter
	 * 
	 * @param benutzername Zu ueberpruefender Benutzername
	 * @param passwort     Zu ueberpruefendes Passwort.
	 */
	public void anmelden(String benutzername, String passwort) throws NullPointerException {
		if (!(benutzername == null) && !(passwort == null)) {

			// Falls der Benutzername im System bekannt ist wird der zugehoerige Benutzer
			// zurueckgegeben.
			Benutzer benutzer = BenutzerRegister.getBenutzerZuBenutzername(benutzername);

			if (!(benutzer == null)) {
				// HIGH SECURITY PASSWORT CHECK
				if (benutzer.getPasswort().equals(passwort)) {
					leiteWeiter(benutzer);
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
	}

	/**
	 * Registriert bei korrekten Eingaben einen neuen Kunden im System
	 * 
	 * @param benutzername   Benutzername des neuen Kunden. Darf noch nicht vergeben
	 *                       sein.
	 * @param passwort       Passwort des neuen Kunden.
	 * @param email          Email des neuen Kunden.
	 * @param adresse        Adresse des neuen Kunden.
	 * @param vorname        Vorname des neuen Kunden.
	 * @param name           Name des neuen Kunden.
	 * @param bankverbindung Bankverbindung des neuen Kunden.
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

	/**
	 * Bearbeitet die Weiterleitung von der Anmeldungssteuerung an die dem
	 * uebergebenen Benutzer zugehoerige Steuerungsklasse
	 * 
	 * @param benutzer
	 * @pre Der uebergebene Nutzer ist ein im System eingetragener Benutzer
	 */
	@SuppressWarnings("unused")
	private void leiteWeiter(Benutzer benutzer) {
		assert BenutzerRegister.getBenutzerZuBenutzername(benutzer.getBenutzername()) != null
				: "Benutzer ist nicht im System registriert";

		switch (benutzer.getRolle()) {
		case KUNDE:
			Kundensteuerung ks = new Kundensteuerung(null); // TODO Fehlende Parameter
			// wechsleSzene(ks);
			System.out.println("Kunde angemeldet!");
			break;
		case FAHRER:
			Fahrersteuerung fs = new Fahrersteuerung((Fahrer) benutzer); // TODO Fehlende Parameter
			System.out.println("Fahrer angemeldet!");
			break;
		case LAGERIST:

			Lageristensteuerung ls = new Lageristensteuerung(); // TODO Fehlende
																// Parameter
			System.out.println("Lagerist angemeldet!");
			break;

		case INHABER:
			Inhabersteuerung is = new Inhabersteuerung((Inhaber) benutzer); // TODO Fehlende Parameter
			System.out.println("Inhaber angemeldet!");
			break;
		}
	}

	private void wechsleSzene(Object klasse) {

	}

}
package de.wwu.sopra.anwendung.mitarbeiter;

import java.time.LocalDateTime;

import de.wwu.sopra.datenhaltung.benutzer.Fahrer;
import de.wwu.sopra.datenhaltung.bestellung.BestellStatus;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.bestellung.Rechnung;
import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import de.wwu.sopra.datenhaltung.management.FahrzeugStatus;
import de.wwu.sopra.datenhaltung.management.Route;

/**
 * Steuerung der Anwendungen des Fahrers
 * 
 * @author Johannes Thiel
 *
 */
public class Fahrersteuerung {

	// Angemeldeter Fahrer
	private Fahrer fahrer;

	// index der bestellung in der Liste der Bestellungen in der Route, die der
	// Fahrer gerade bearbeitet
	private int aktuelleBestellung;

	/**
	 * Die Fahrzeugsteuerung wird erstellt
	 * 
	 * @param fahrer der Fahrer der aktuell auf die Steuerung zugreift
	 */
	public Fahrersteuerung(Fahrer fahrer) {
		this.fahrer = fahrer;

	}

	/**
	 * Der Fahrer ordnet sich einem fahrzeug zu
	 * 
	 * @param fahrzeug fahrzeug seiner wahl
	 * @throws IllegalArgumentException wenn fahrer oder Fahrzeug nicht leer sind
	 * @pre fahrer und Fahrzeug haben keinen Fahrer und Fahrzeug und das Fahrzeug
	 *      hat eine Route
	 */
	public void fahrzeugZuordnen(Fahrzeug fahrzeug) throws NullPointerException {
		if (fahrzeug.getStatus() != FahrzeugStatus.BELEGT | this.fahrer.getFahrzeug() != null) {
			throw new NullPointerException();
		}
		this.fahrer.setFahrzeug(fahrzeug);
		fahrzeug.setFahrer(this.fahrer);
		fahrzeug.setStatus(FahrzeugStatus.IN_ZUSTELLUNG);
		this.aktuelleBestellung = 0;
	}

	/**
	 * die route des Fahrzugs wird ausgegben
	 * 
	 * @return route des Fahrzeugs
	 */
	public Route routeAusgeben() {
		return fahrer.getFahrzeug().getRoute();
	}

	/**
	 * der Fahrer storniert die Bestellung an seiner aktuellen position
	 * 
	 * @throws NullPointerException Eine Bestellung auf dem Weg wurde nicht
	 *                              ausgeliefert.
	 */
	public void kundeNichtDa() throws NullPointerException {
		if (aktuelleBestellung < (this.routeAusgeben().getBestellungen().size())) {
			this.routeAusgeben().getBestellungen().get(this.aktuelleBestellung).setStatus(BestellStatus.STORNIERT);
			this.aktuelleBestellung++;
		} else
			throw new NullPointerException();
	}

	/**
	 * Die position des Fahrzeugs wird zurueckgegeben, in der Hinsicht, an welcher
	 * bestellung das Fahrzeug gerade ist
	 * 
	 * @return position des Fahrzeugs in der Liste der Bestellungen
	 */
	public String positionDesFahrzeugs() {

		return this.routeAusgeben().getBestellungen().get(aktuelleBestellung).getKunde().getAdresse();
	}

	/**
	 * Die persoenlichen Daten des Fahrers werden durch die neuen Daten
	 * ueberschieben
	 * 
	 * @param benutzername   benutzername des Fahrers
	 * @param passwort       passwort des Fahrers
	 * @param email          email des Fahrers
	 * @param adresse        adresse des Fahrers
	 * @param vorname        Vorname des Fahrers
	 * @param name           Name des Fahreres
	 * @param bankverbindung Bankverbindung des Fahrers
	 */
	public void persoenlicheDatenBearbeiten(String benutzername, String passwort, String email, String adresse,
			String vorname, String name, String bankverbindung) {
		this.fahrer.setBenutzername(benutzername);
		this.fahrer.setPasswort(passwort);
		this.fahrer.setEmail(email);
		this.fahrer.setAdresse(adresse);
		this.fahrer.setVorname(vorname);
		this.fahrer.setName(name);
		this.fahrer.setBankverbindung(bankverbindung);
	}

	/**
	 * persoenliche Daten werden in einem String der Form: benutzername;passwort;
	 * email;adresse;vorname;name;bankverbindung;
	 * 
	 * @return gibt den String aus
	 */
	public String persoenlicheDatenAnzeigen() {
		String returnstring = fahrer.getBenutzername() + ";" + fahrer.getPasswort() + ";" + fahrer.getEmail() + ";"
				+ fahrer.getAdresse() + ";" + fahrer.getVorname() + ";" + fahrer.getName() + ";"
				+ fahrer.getBankverbindung() + ";";
		return returnstring;

	}

	/**
	 * Der Fahrer meldet die Aktuelle Bestellung als abegeben, erstellt die
	 * dazugehoerige rechnung
	 * 
	 * @throws NullPointerException wenn eine Asuleiferung erfolgen soll wenn die
	 *                              Route schon abgearbeitet ist
	 * @pre Die aktuelle bestellung ist noch auf der Liste
	 */
	public void bestellungAusliefern() throws NullPointerException {
		if (aktuelleBestellung < (this.routeAusgeben().getBestellungen().size())) {
			Bestellung inbearbeitung = this.routeAusgeben().getBestellungen().get(aktuelleBestellung);
			inbearbeitung.setRechnung(new Rechnung(LocalDateTime.now(), inbearbeitung));
			inbearbeitung.setStatus(BestellStatus.ABGESCHLOSSEN);
			aktuelleBestellung++;
		} else
			throw new NullPointerException();

	}

	/**
	 * erlaibt es die abgeschlossene Route zu benden
	 * 
	 * @throws IllegalArgumentException wennn die Route noch nicht abgearbeitet ist
	 * @pre die Route muss abgeschollen sein
	 */

	public void routeAbschliesen() throws IllegalArgumentException {
		if (this.aktuelleBestellung >= this.routeAusgeben().getBestellungen().size()) {
			this.fahrer.getFahrzeug().entferneRoute();
			this.fahrer.getFahrzeug().setFahrer(null);
			this.fahrer.setFahrzeug(null);
		} else
			throw new IllegalArgumentException();
	}

	/**
	 * Gibt den Index der aktuellen Bestellung aus.
	 * 
	 * @return Index der aktuellen Bestellung aus.
	 */
	public int getAktuelleBestellung() {
		return aktuelleBestellung;
	}

}

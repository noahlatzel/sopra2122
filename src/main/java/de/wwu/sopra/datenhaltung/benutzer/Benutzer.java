package de.wwu.sopra.datenhaltung.benutzer;

import java.io.Serializable;

/**
 * Abstrakte Klasse Benutzer gibt Struktur fuer alle Unterklassen.
 * 
 * @author Paul Dirksen
 *
 */
public abstract class Benutzer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String benutzername;
	private String passwort;
	private String email;
	private String adresse;
	private String vorname;
	private String name;
	private String bankverbindung;
	private Rolle rolle;

	/**
	 * Erstellt einen neuen Benutzer mit den uebergebenen Eigenschaften.
	 * 
	 * @param benutzername
	 * @param passwort
	 * @param email
	 * @param adresse
	 * @param vorname
	 * @param name
	 * @param bankverbindung
	 */
	public Benutzer(String benutzername, String passwort, String email, String adresse, String vorname, String name,
			String bankverbindung) {
		this.setBenutzername(benutzername);
		this.setPasswort(passwort);
		this.setEmail(email);
		this.setAdresse(adresse);
		this.setVorname(vorname);
		this.setName(name);
		this.setBankverbindung(bankverbindung);
	}

	/**
	 * gibt den Benutzernamen aus
	 * 
	 * @return benutername
	 */

	public String getBenutzername() {
		return benutzername;
	}

	/**
	 * setzt den Benutzernamen
	 * 
	 * @param benutzername
	 */
	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	/**
	 * gibt das Passwort aus
	 * 
	 * @return aktuelles Passwort
	 */
	public String getPasswort() {
		return passwort;
	}

	/**
	 * setzt das Passwort
	 * 
	 * @param passwort
	 */
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	/**
	 * gibt die email aus
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * setzt die Email
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * gibt die adresse als String aus
	 * 
	 * @return adresse
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * setzt die Adresse
	 * 
	 * @param adresse
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * gibt den Vornamen aus
	 * 
	 * @return Vorname
	 */
	public String getVorname() {
		return vorname;
	}

	/**
	 * setzt den Vornamen
	 * 
	 * @param vorname
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	/**
	 * gibt den Namen aus
	 * 
	 * @return Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * setzt den Namen
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * gibt die Bankdaten als String aus
	 * 
	 * @return bankverbindung
	 */
	public String getBankverbindung() {
		return bankverbindung;
	}

	/**
	 * setzt die bankverbindung
	 * 
	 * @param bankverbindung
	 */
	public void setBankverbindung(String bankverbindung) {
		this.bankverbindung = bankverbindung;
	}

	/**
	 * bit die Rolle zurueck
	 * 
	 * @return rolle
	 */
	public abstract Rolle getRolle();

}

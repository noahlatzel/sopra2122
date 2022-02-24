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

	public String getBenutzername() {
		return benutzername;
	}

	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBankverbindung() {
		return bankverbindung;
	}

	public void setBankverbindung(String bankverbindung) {
		this.bankverbindung = bankverbindung;
	}

	public abstract Rolle getRolle();

}

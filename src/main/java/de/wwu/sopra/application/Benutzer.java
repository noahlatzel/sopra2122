package de.wwu.sopra.application;

public abstract class Benutzer {

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
		this.benutzername = benutzername;
		this.passwort = passwort;
		this.email = email;
		this.adresse = adresse;
		this.vorname = vorname;
		this.name = name;
		this.bankverbindung = bankverbindung;
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

	public Rolle getRolle() {
		return rolle;
	}

}

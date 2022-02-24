package de.wwu.sopra.datenhaltung.benutzer;

import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import de.wwu.sopra.datenhaltung.management.FahrzeugStatus;

/**
 * Die Entitaetsklasse Fahrer, dieser hat einen Chef und kann einem Fahrzeug
 * zugeordent werden.
 * 
 * @author Johannes Thiel
 *
 */

public class Fahrer extends Benutzer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Rolle des Fahrers
	private final Rolle rolle = Rolle.FAHRER;

	// Fahrzeug des Fahrers
	private Fahrzeug fahrzeug;

	// chef des Fahrers
	private Inhaber chef;

	/**
	 * Erstellt einen neuen Fahrer mit den uebergebenen Eigenschaften.
	 * 
	 * @param benutzername
	 * @param passwort
	 * @param email
	 * @param adresse
	 * @param vorname
	 * @param name
	 * @param bankverbindung
	 * @param chef
	 */
	public Fahrer(String benutzername, String passwort, String email, String adresse, String vorname, String name,
			String bankverbindung, Inhaber chef) {
		super(benutzername, passwort, email, adresse, vorname, name, bankverbindung);
		this.setChef(chef);
	}

	// gibt das Fahrzeug aus
	public Fahrzeug getFahrzeug() {
		return fahrzeug;
	}

	// setzt das Fahrzeug
	public void setFahrzeug(Fahrzeug fahrzeug) {
		if (fahrzeug != null) {
			this.fahrzeug = fahrzeug;
			this.fahrzeug.setStatus(FahrzeugStatus.BELEGT);
		} else if (this.fahrzeug != null) {
			this.fahrzeug.setStatus(FahrzeugStatus.FREI);
			this.fahrzeug = null;

		}

	}

	// gibt den Chef aus
	public Inhaber getChef() {
		return chef;
	}

	// setzt den Chef
	public void setChef(Inhaber chef) {
		this.chef = chef;
	}

	// gibt die Rolle aus
	public Rolle getRolle() {
		return rolle;
	}

}

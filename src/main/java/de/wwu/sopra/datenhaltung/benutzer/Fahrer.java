package de.wwu.sopra.datenhaltung.benutzer;

import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import de.wwu.sopra.datenhaltung.management.FahrzeugStatus;

/**
 * Die Entitaetsklasse Fahrer hat einen Chef und kann einem Fahrzeug zugeordnet
 * werden.
 * 
 * @author Johannes Thiel
 *
 */
public class Fahrer extends Benutzer {

	/**
	 * SerialisierungsID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Rolle des Fahrers
	 */
	private final Rolle rolle = Rolle.FAHRER;

	/**
	 * Fahrzeug des Fahrers
	 */
	private Fahrzeug fahrzeug;

	/**
	 * Chef des Fahrers
	 */
	private Inhaber chef;

	/**
	 * Erstellt einen neuen Fahrer mit den uebergebenen Eigenschaften.
	 * 
	 * @param benutzername   benutzername
	 * @param passwort       passwort
	 * @param email          email
	 * @param adresse        adresse
	 * @param vorname        vorname
	 * @param name           name
	 * @param bankverbindung bankverbindung
	 * @param chef           chef
	 */
	public Fahrer(String benutzername, String passwort, String email, String adresse, String vorname, String name,
			String bankverbindung, Inhaber chef) {
		super(benutzername, passwort, email, adresse, vorname, name, bankverbindung);
		this.setChef(chef);

	}

	/**
	 * Gibt das Fahrzeug des Fahrers zurueck.
	 * 
	 * @return Fahrzeug
	 */
	public Fahrzeug getFahrzeug() {
		return fahrzeug;
	}

	/**
	 * Setzt das Fahrzeug fuer den Lageristen.
	 * 
	 * @param fahrzeug fahrzeug
	 * @post Das uebergebene Fahrzeug ist nun dem Fahrer zugewiesen. Falls dem
	 *       Fahrer ein neues Fahrzeug zugewiesen wurde, ist dieses Fahrzeug BELEGT.
	 *       Falls das Fahrzeug vom Fahrer geloescht wird, muss es FREI weden.
	 */
	public void setFahrzeug(Fahrzeug fahrzeug) {
		if (this.getFahrzeug() != null) {
			this.getFahrzeug().setStatus(FahrzeugStatus.FREI);
		}

		if (fahrzeug != null) {
			this.fahrzeug = fahrzeug;
			this.fahrzeug.setStatus(FahrzeugStatus.BELEGT);
		} else if (this.fahrzeug != null) {
			this.fahrzeug.setStatus(FahrzeugStatus.FREI);
			this.fahrzeug = null;

		}

	}

	/**
	 * Gibt den Chef des Fahrers zurueck.
	 * 
	 * @return Chef des Fahrers
	 */
	public Inhaber getChef() {
		return chef;
	}

	/**
	 * Setzt den Chef des Fahrers.
	 * 
	 * @param chef Chef des Fahrers
	 */
	public void setChef(Inhaber chef) {
		this.chef = chef;

	}

	/**
	 * Gibt die Rolle des Fahrers aus.
	 * 
	 * @return die Rolle des Fahrer
	 */
	public Rolle getRolle() {
		return rolle;
	}

}

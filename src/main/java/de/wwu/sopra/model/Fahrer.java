package de.wwu.sopra.model;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Entitaetsklasse Fahrer, dieser hat einen Chef und kann einem Fahrzeug zugeordent werden.
 * @author Johanes Thiel
 *
 */

public class Fahrer extends Benutzer {
	
	//Rolle des Fahrers
	private final Rolle rolle = Rolle.FAHRER;
	
	//Fahrzeug des Fahrers
	private Fahrzeug fahrzeug;
	
	//chef des Fahrers
	private Inhaber chef;
	
	/**
	 * Erstellt einen neuen Fahrer mit den uebergebenen Eigenschaften.
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
	}
	
	//gibt das Fahrzeug aus
	public Fahrzeug getFahrzeug() {
		return fahrzeug;
	}

	//setzt das Fahrzeug
	public void setFahrzeug(Fahrzeug fahrzeug) {
		this.fahrzeug = fahrzeug;
	}

	//gibt den Chef aus
	public Inhaber getChef() {
		return chef;
	}

	//setzt den Chef
	public void setChef(Inhaber chef) {
		this.chef = chef;
	}

	//gibt die Rolle aus
	public Rolle getRolle() {
		return rolle;
	}


	
	
}

package de.wwu.sopra.application;

import java.util.ArrayList;
import java.util.List;

public class Fahrer extends Benutzer {

	private final Rolle rolle = Rolle.FAHRER;
	private Fahrzeug fahrzeug;
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
	 */
	public Fahrer(String benutzername, String passwort, String email, String adresse, String vorname, String name,
			String bankverbindung) {
		super(benutzername, passwort, email, adresse, vorname, name, bankverbindung);
	}
	
	public Fahrzeug getFahrzeug() {
		return fahrzeug;
	}


	public void setFahrzeug(Fahrzeug fahrzeug) {
		this.fahrzeug = fahrzeug;
	}


	public Inhaber getChef() {
		return chef;
	}


	public void setChef(Inhaber chef) {
		this.chef = chef;
	}


	public Rolle getRolle() {
		return rolle;
	}


	
	
}

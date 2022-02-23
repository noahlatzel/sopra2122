/**
 * 
 */
package de.wwu.sopra.model;

import java.io.Serializable;

/**
 * Erstellung der Fahrzeug-Klasse
 */
public class Fahrzeug implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int fahrzeugNummer;
	private float kapazitaet;
	private Route route;
	
	public Fahrzeug(int fahrzeugNummer, float kapazitaet) {
		this.fahrzeugNummer = fahrzeugNummer;
		this.kapazitaet = kapazitaet;
	}

	/**
	 * Fahrzeugnummer der Fahrzeug
	 * @return fahrzeugNummer
	 */
	public int getFahrzeugNummer() {
		return fahrzeugNummer;
	}

	/**
	 * Fahrzeugnummer der Fahrzeug aendern/setzen
	 * @param fahrzeugNummer zu setzen
	 */
	public void setFahrzeugNummer(int fahrzeugNummer) {
		this.fahrzeugNummer = fahrzeugNummer;
	}

	/**
	 * Kapazitaet der Fahrzeug
	 * @return kapazitaet
	 */
	public float getKapazitaet() {
		return kapazitaet;
	}

	/**
	 * Kapazitaet der Fahrzeug aendern/setzen
	 * @param kapazitaet zu setzen
	 */
	public void setKapazitaet(float kapazitaet) {
		this.kapazitaet = kapazitaet;
	}

	/**
	 * Route der Fahrzeug
	 * @return zugeordnete route
	 */
	public Route getRoute() {
		return route;
	}

	/**
	 * Route der Fahrzeug setzen
	 * "setRoute" wird beim Erstellen eines Route-Objekts aufgerufen
	 * @param route zu setzen
	 */
	public void setRoute(Route route) {
		if (this.route == null) this.route = route;
	}
}

/**
 * 
 */
package de.wwu.sopra.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Erstellung der Fahrzeug-Klasse
 */
public class Fahrzeug implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Set<Integer> fahrzeugNummern = new HashSet<Integer>();
	
	private int fahrzeugNummer;
	private float kapazitaet;
	private Route route;
	
	/**
	 * Neues Route-Objekt erstellen nur wenn angegebene fahrzeugNummer nicht auf der Liste existiert
	 * @param fahrzeugNummer
	 * @param kapazitaet
	 * @throws IllegalArgumentException
	 */
	public Fahrzeug(int fahrzeugNummer, float kapazitaet) throws IllegalArgumentException {
		if (!fahrzeugNummern.add(fahrzeugNummer)) {			
			throw new IllegalArgumentException();
		}
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
	 * Fahrzeugnummer der Fahrzeug aendern/setzen, nur wenn die neue fahrzeugNummer nicht in der Liste ist
	 * @param fahrzeugNummer zu setzen
	 */
	public void setFahrzeugNummer(int fahrzeugNummer) throws IllegalArgumentException {
		if (fahrzeugNummern.contains(fahrzeugNummer)) {			
			throw new IllegalArgumentException();
		}
		fahrzeugNummern.remove(this.fahrzeugNummer);
		fahrzeugNummern.add(fahrzeugNummer);
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

/**
 * 
 */
package de.wwu.sopra.model;

import java.io.Serializable;

/**
 * @author Valeria Vassallo
 *
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
	 * @return fahrzeugNummer
	 */
	public int getFahrzeugNummer() {
		return fahrzeugNummer;
	}

	/**
	 * @param fahrzeugNummer zu setzen
	 */
	public void setFahrzeugNummer(int fahrzeugNummer) {
		this.fahrzeugNummer = fahrzeugNummer;
	}

	/**
	 * @return kapazitaet
	 */
	public float getKapazitaet() {
		return kapazitaet;
	}

	/**
	 * @param kapazitaet zu setzen
	 */
	public void setKapazitaet(float kapazitaet) {
		this.kapazitaet = kapazitaet;
	}

	/**
	 * @return zugeordnete route
	 */
	public Route getRoute() {
		return route;
	}

	/**
	 * "setRoute" wird beim Erstellen eines Route-Objekts aufgerufen
	 * @param route zu setzen
	 */
	public void setRoute(Route route) {
		if (this.route == null) this.route = route;
	}
}

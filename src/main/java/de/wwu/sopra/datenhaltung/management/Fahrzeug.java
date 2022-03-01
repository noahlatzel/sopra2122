/**
 * 
 */
package de.wwu.sopra.datenhaltung.management;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import de.wwu.sopra.datenhaltung.benutzer.Fahrer;

/**
 * Erstellung der Fahrzeug-Klasse
 * 
 * @author Valeria
 */
public class Fahrzeug implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Set<Integer> fahrzeugNummern = new HashSet<Integer>();

	private int fahrzeugNummer;
	private float kapazitaet;
	private FahrzeugStatus status;
	private Route route;
	private Fahrer fahrer;

	/**
	 * Neues Route-Objekt erstellen nur wenn angegebene fahrzeugNummer nicht auf der
	 * Liste existiert
	 * 
	 * @param fahrzeugNummer fahrzeugNummer
	 * @param kapazitaet     kapazitaet
	 * @throws IllegalArgumentException IllegalArgumentException
	 */
	public Fahrzeug(int fahrzeugNummer, float kapazitaet) throws IllegalArgumentException {
		this.setFahrzeugNummer(fahrzeugNummer);
		this.kapazitaet = kapazitaet;
		this.status = FahrzeugStatus.FREI;
	}

	/**
	 * Fahrzeugnummer der Fahrzeug
	 * 
	 * @return fahrzeugNummer
	 */
	public int getFahrzeugNummer() {
		return fahrzeugNummer;
	}

	/**
	 * Fahrzeugnummer der Fahrzeug aendern/setzen, nur wenn die neue fahrzeugNummer
	 * nicht in der Liste ist
	 * 
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
	 * 
	 * @return kapazitaet
	 */
	public float getKapazitaet() {
		return kapazitaet;
	}

	/**
	 * Kapazitaet der Fahrzeug aendern/setzen
	 * 
	 * @param kapazitaet zu setzen
	 */
	public void setKapazitaet(float kapazitaet) {
		this.kapazitaet = kapazitaet;
	}

	/**
	 * Status der Fahrzeug
	 * 
	 * @return status
	 */
	public FahrzeugStatus getStatus() {
		return status;
	}

	/**
	 * Status der Fahrzeug aendern
	 * 
	 * @param status zu setzen
	 */
	public void setStatus(FahrzeugStatus status) {
		this.status = status;
	}

	/**
	 * Route der Fahrzeug
	 * 
	 * @return zugeordnete route
	 */
	public Route getRoute() {
		return route;
	}

	/**
	 * Route der Fahrzeug setzen "setRoute" wird beim Erstellen eines Route-Objekts
	 * aufgerufen
	 * 
	 * @param route zu setzen
	 */
	public void setRoute(Route route) {
		if (this.route == null) {
			this.route = route;
			this.setStatus(FahrzeugStatus.BELEGT);
		}
	}

	/**
	 * Setzt die Route wieder auf null und setzt das fahrzeug auf frei
	 */

	public void entferneRoute() {
		this.route = null;
		this.setStatus(FahrzeugStatus.FREI);
	}

	/**
	 * zugeordneter Fahrer
	 * 
	 * @return fahrer
	 */
	public Fahrer getFahrer() {
		return fahrer;
	}

	/**
	 * zugeordneter Fahrer setzen
	 * 
	 * @param fahrer fahrer
	 */
	public void setFahrer(Fahrer fahrer) {
		this.fahrer = fahrer;
	}

}

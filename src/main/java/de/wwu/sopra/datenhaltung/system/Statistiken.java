package de.wwu.sopra.datenhaltung.system;

import java.io.Serializable;

public class Statistiken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private float umsatz;
	private float ausgaben;
	private float einnahmen;
	private float arbeitszeit;

	/**
	 * Konstruktor der Klasse Statistiken
	 */
	public Statistiken() {
		this.setUmsatz(0);
		this.setAusgaben(0);
		this.setEinnahmen(0);
		this.setArbeitszeit(0);

	}

	/**
	 * Getter Methode fuer den Umsatz
	 * 
	 * @return Umsatz
	 */
	public float getUmsatz() {
		return umsatz;
	}

	/**
	 * Setter Methode fuer den Umsatz
	 * 
	 * @param umsatz Umsatz
	 */
	public void setUmsatz(float umsatz) {
		this.umsatz = umsatz;
	}

	/**
	 * Getter Methode fuer die Ausgaben
	 * 
	 * @return Ausgaben
	 */
	public float getAusgaben() {
		return ausgaben;
	}

	/**
	 * Setter Methode fuer die Ausgaben
	 * 
	 * @param ausgaben Ausgaben
	 */
	public void setAusgaben(float ausgaben) {
		this.ausgaben = ausgaben;
	}

	/**
	 * Getter Methode fuer die Einnahmen
	 * 
	 * @return Einnahmen
	 */
	public float getEinnahmen() {
		return einnahmen;
	}

	/**
	 * Setter Methode fuer die Einnahmen
	 * 
	 * @param einnahmen Einnahmen
	 */
	public void setEinnahmen(float einnahmen) {
		this.einnahmen = einnahmen;
	}

	/**
	 * Getter Methode fuer die Arbeitszeit
	 * 
	 * @return Arbeitszeit
	 */
	public float getArbeitszeit() {
		return arbeitszeit;
	}

	/**
	 * Setter Methode fuer die Arbeitszeit
	 * 
	 * @param arbeitszeit Arbeitszeit
	 */
	public void setArbeitszeit(float arbeitszeit) {
		this.arbeitszeit = arbeitszeit;
	}

}

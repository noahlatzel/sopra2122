package de.wwu.sopra.model;

import java.io.Serializable;

public class Statistiken implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	float umsatz;
	float ausgaben;
	float einnahmen;
	float arbeitszeit;

	/**
	 * Konstruktor der Klasse Statistiken
	 */
	public Statistiken() {
		this.umsatz = 0;
		this.ausgaben = 0;
		this.einnahmen = 0;
		this.arbeitszeit = 0;
		
	}

	/**
	 * Getter Methode fuer den Umsatz
	 * @return Umsatz
	 */
	public float getUmsatz() {
		return umsatz;
	}

	/**
	 * Setter Methode fuer den Umsatz
	 * @param umsatz Umsatz
	 */
	public void setUmsatz(float umsatz) {
		this.umsatz = umsatz;
	}

	/**
	 * Getter Methode fuer die Ausgaben
	 * @return Ausgaben
	 */
	public float getAusgaben() {
		return ausgaben;
	}

	/**
	 * Setter Methode fuer die Ausgaben
	 * @param ausgaben Ausgaben
	 */
	public void setAusgaben(float ausgaben) {
		this.ausgaben = ausgaben;
	}

	/**
	 * Getter Methode fuer die Einnahmen
	 * @return Einnahmen
	 */
	public float getEinnahmen() {
		return einnahmen;
	}

	/**
	 * Setter Methode fuer die Einnahmen
	 * @param einnahmen Einnahmen
	 */
	public void setEinnahmen(float einnahmen) {
		this.einnahmen = einnahmen;
	}

	/**
	 * Getter Methode fuer die Arbeitszeit
	 * @return Arbeitszeit
	 */
	public float getArbeitszeit() {
		return arbeitszeit;
	}

	/**
	 * Setter Methode fuer die Arbeitszeit
	 * @param arbeitszeit Arbeitszeit
	 */
	public void setArbeitszeit(float arbeitszeit) {
		this.arbeitszeit = arbeitszeit;
	}

	
}

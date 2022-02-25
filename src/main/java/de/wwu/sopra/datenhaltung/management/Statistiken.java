package de.wwu.sopra.datenhaltung.management;

import java.io.Serializable;

public class Statistiken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double umsatz;
	private double ausgaben;
	private double einnahmen;
	private double arbeitszeit;

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
	public double getUmsatz() {
		return umsatz;
	}

	/**
	 * Setter Methode fuer den Umsatz
	 * 
	 * @param umsatz Umsatz
	 */
	public void setUmsatz(double umsatz) {
		this.umsatz = umsatz;
	}

	/**
	 * Getter Methode fuer die Ausgaben
	 * 
	 * @return Ausgaben
	 */
	public double getAusgaben() {
		return ausgaben;
	}

	/**
	 * Setter Methode fuer die Ausgaben
	 * 
	 * @param ausgaben Ausgaben
	 */
	public void setAusgaben(double ausgaben) {
		this.ausgaben = ausgaben;
	}

	/**
	 * Getter Methode fuer die Einnahmen
	 * 
	 * @return Einnahmen
	 */
	public double getEinnahmen() {
		return einnahmen;
	}

	/**
	 * Setter Methode fuer die Einnahmen
	 * 
	 * @param einnahmen Einnahmen
	 */
	public void setEinnahmen(double einnahmen) {
		this.einnahmen = einnahmen;
	}

	/**
	 * Getter Methode fuer die Arbeitszeit
	 * 
	 * @return Arbeitszeit
	 */
	public double getArbeitszeit() {
		return arbeitszeit;
	}

	/**
	 * Setter Methode fuer die Arbeitszeit
	 * 
	 * @param arbeitszeit Arbeitszeit
	 */
	public void setArbeitszeit(double arbeitszeit) {
		this.arbeitszeit = arbeitszeit;
	}

	/**
	 * Fuegt einen Betrag zu den Ausgaben hinzu.
	 * 
	 * @param ausgaben Die Ausgaben, die hinzugekommen sind.
	 */
	public void addAusgaben(double ausgaben) {
		this.ausgaben += ausgaben;
	}

}

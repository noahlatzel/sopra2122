package de.wwu.sopra.datenhaltung.management;

import java.time.LocalDateTime;

/**
 * Darstellung einer Transaktion in einer Datenstruktur
 * 
 * @author Noah Latzel
 *
 */
public class Transaktion {
	private final LocalDateTime datum;
	private final double betrag;
	private final String beschreibung;

	public Transaktion(LocalDateTime datum, String beschreibung, double betrag) {
		this.datum = datum;
		this.beschreibung = beschreibung;
		this.betrag = betrag;
	}

	/**
	 * Gibt das Datum zurueck.
	 * 
	 * @return das Datum
	 */
	public LocalDateTime getDatum() {
		return datum;
	}

	/**
	 * Gibt den Betrag zurueck.
	 * 
	 * @return der Betrag
	 */
	public double getBetrag() {
		return betrag;
	}

	/**
	 * Gibt die Beschreibung zurueck.
	 * 
	 * @return die Beschreibung
	 */
	public String getBeschreibung() {
		return beschreibung;
	}
}

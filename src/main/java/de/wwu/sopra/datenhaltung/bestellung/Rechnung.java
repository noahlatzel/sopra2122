package de.wwu.sopra.datenhaltung.bestellung;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Die Klasse Rechnug
 * 
 * @author Jasmin
 *
 */
public class Rechnung implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final int rechnungsnummer;
	private final double endbetrag;
	private final LocalDateTime datum;
	private final Bestellung bestellung;
	private static int zaehler = 0;

	/**
	 * Konstruktor der Klasse Rechnung
	 * 
	 * @param endbetrag Endbetrag der Bestellung
	 * @param datum     Datum der Bestellung
	 */
	public Rechnung(double endbetrag, LocalDateTime datum, Bestellung bestellung) {

		this.rechnungsnummer = zaehler;
		zaehler++;
		this.endbetrag = endbetrag;
		this.datum = datum;
		this.bestellung = bestellung;
	}

	/**
	 * Getter Methode fuer die Rechnungsnummer
	 * 
	 * @return Rechnungsnummer
	 */
	public int getRechnungsnummer() {
		return rechnungsnummer;
	}

	/**
	 * Getter Methode fuer den Endbetrag
	 * 
	 * @return Endbetrag
	 */
	public double getEndbetrag() {
		return endbetrag;
	}

	/**
	 * Getter Methode fuer das Datum
	 * 
	 * @return Datums
	 */
	public LocalDateTime getDatum() {
		return datum;
	}

	/**
	 * Getter Methode fuer die Bestellung
	 * 
	 * @return Bestellung
	 */
	public Bestellung getBestellung() {
		return bestellung;
	}

}

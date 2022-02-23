package de.wwu.sopra.datenhaltung.bestellung;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Rechnung implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final int rechnungsnummer;
	private final int endbetrag;
	private final LocalDateTime datum;
	private final Bestellung bestellung;

	/**
     * Konstruktor der Klasse Rechnung
     * @param rechnungsnummer Rechnungsnummer
     * @param endbetrag  Endbetrag der Bestellung
     * @param datum Datum der Bestellung
     */
	public Rechnung(int rechnungsnummer,int endbetrag , LocalDateTime datum, Bestellung bestellung) {
		
		this.rechnungsnummer = rechnungsnummer;
		this.endbetrag = endbetrag;
		this.datum = datum;
		this.bestellung = bestellung;
	}

	/**
	 * Getter Methode fuer die Rechnungsnummer
	 * @return Rechnungsnummer
	 */
	public int getRechnungsnummer() {
		return rechnungsnummer;
	}

	/**
	 * Getter Methode fuer den Endbetrag
	 * @return Endbetrag
	 */
	public int getEndbetrag() {
		return endbetrag;
	}

	/**
	 * Getter Methode fuer das Datum
	 * @return Datums
	 */
	public LocalDateTime getDatum() {
		return datum;
	}
	
	/**
	 * Getter Methode fuer die Bestellung
	 * @return Bestellung
	 */
    public Bestellung getBestellung() {
		return bestellung;
	}
	

}

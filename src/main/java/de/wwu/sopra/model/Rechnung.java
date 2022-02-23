package de.wwu.sopra.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Rechnung implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final int rechnungsnummer;
	final int endbetrag;
	final LocalDate datum;
	
    /**
     * Konstruktor der Klasse Rechnung
     * @param rechnungsnummer Rechnungsnummer
     * @param endbetrag  Endbetrag der Bestellung
     * @param datum Datum der Bestellung
     */
	public Rechnung(int rechnungsnummer,int endbetrag , LocalDate datum) {
		
		this.rechnungsnummer = rechnungsnummer;
		this.endbetrag = endbetrag;
		this.datum = datum;
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
	public LocalDate getDatum() {
		return datum;
	}
	
	

}

package de.wwu.sopra.datenhaltung.bestellung;

import java.io.Serializable;
import java.time.LocalDateTime;

import de.wwu.sopra.datenhaltung.management.Statistiken;

/**
 * Die Klasse Rechnug
 * 
 * @author Jasmin Horstknepper
 *
 */
public class Rechnung implements Serializable {

	/**
	 * SerialisierungsID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Rechnungsnummer der Rechnung
	 */
	private final int rechnungsnummer;
	/**
	 * Endbetrag der Rechnung
	 */
	private final double endbetrag;
	/**
	 * Datum der Rechnung
	 */
	private final LocalDateTime datum;
	/**
	 * Bestellung, die der Rechnung zugeordnet ist
	 */
	private final Bestellung bestellung;

	/**
	 * Konstruktor der Klasse Rechnung
	 * 
	 * @param datum      Datum der Bestellung
	 * @param bestellung Bestellung fuer die Rechnung ist
	 */
	public Rechnung(LocalDateTime datum, Bestellung bestellung) {

		this.rechnungsnummer = bestellung.getBestellnummer();
		this.datum = datum;
		this.bestellung = bestellung;
		this.endbetrag = bestellung.calcBetrag();
		double umsatz_neu = Statistiken.getUmsatz() + this.endbetrag;
		Statistiken.setUmsatz(umsatz_neu);

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

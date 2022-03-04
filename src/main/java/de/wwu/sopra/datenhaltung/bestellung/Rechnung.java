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
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final int rechnungsnummer;
	private final double endbetrag;
	private final LocalDateTime datum;
	private final Bestellung bestellung;

	/**
	 * Konstruktor der Klasse Rechnung
	 * 
	 * @param endbetrag Endbetrag der Bestellung
	 * @param datum     Datum der Bestellung
	 * @post Die Einnahmen steigen um den Endbetrag der Rechnung
	 */
	public Rechnung(double endbetrag, LocalDateTime datum, Bestellung bestellung) {
		// Vorzustand zur Ueberpruefung der Nachbedingung retten
		double einnahmen = Statistiken.getEinnahmen();

		this.rechnungsnummer = bestellung.getBestellnummer();
		this.datum = datum;
		this.bestellung = bestellung;
		this.endbetrag = bestellung.calcBetrag();

		Statistiken.setEinnahmen(Statistiken.getEinnahmen() + endbetrag);

		// Nachbedingung pruefen
		assert Statistiken.getEinnahmen() == einnahmen + endbetrag
				: "Nachbedingung des Konstruktors der Rechnung verletzt: der Betrag der Rechnung wurde nicht den Einnahmen hinzugefuegt";
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

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
	 * @param endbetrag  Endbetrag der Bestellung
	 * @param datum      Datum der Bestellung
	 * @param bestellung Bestellung fuer die Rechnung ist
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

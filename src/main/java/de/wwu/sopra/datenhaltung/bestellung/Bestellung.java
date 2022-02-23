package de.wwu.sopra.datenhaltung.bestellung;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.management.Produkt;

public class Bestellung implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final int bestellnummer;
	private final double betrag;
	private BestellStatus status;
	private final List<Produkt> produkte;
	private final Kunde kunde;
	private LocalDateTime datum;
	private Rechnung rechnung;


	/**
	 * Konstruktor der Klasse Bestellung
	 * @param bestellnummer Bestellnummer
	 * @param betrag Betrag 
	 * @param produkte 
	 */
	public Bestellung(int bestellnummer, double betrag, LocalDateTime datum, List<Produkt> produkte, Kunde kunde) {
		
		this.bestellnummer = bestellnummer;
		this.betrag = betrag;
		this.status = BestellStatus.OFFEN;
		this.produkte = produkte;
		this.kunde = kunde;
	}
	
	/**
	 * Getter Methode fuer das Datum
	 * @return Datum
	 */
	public LocalDateTime getDatum() {
		return datum;
	}

	/**
	 * Getter Methode fuer den Status
	 * @return Status
	 */
	public BestellStatus getStatus() {
		return status;
	}

	/**
	 * Setter Methode fuer den Status
	 * @param status Status
	 */
	public void setStatus(BestellStatus status) {
		this.status = status;
	}

	/**
	 * Getter Methode fuer Bestellnummer
	 * @return Bestellnummer
	 */
	public int getBestellnummer() {
		return bestellnummer;
	}

	/**
	 * Getter Methode fuer den Betrag
	 * @return Betrag
	 */
	public double getBetrag() {
		return betrag;
	}

	/**
	 * Getter Methode fuer die Liste der Produkte
	 * @return Liste der Produkte
	 */
	public List<Produkt> getProdukte() {
		return produkte;
	}

	/**
	 * Getter Methode fuer den Kunden
	 * @return Kunden
	 */
	public Kunde getKunde() {
		return kunde;
	}
	
	/**
	 * Getter Methode fuer die Rechnungen
	 * @return Rechnungen
	 */
	public Rechnung getRechnung() {
		return rechnung;
	}

	/**
	 * Setteer Methode fuer die Rechnungen
	 * @param rechnung Rechnung
	 */
	public void setRechnung(Rechnung rechnung) {
		this.rechnung = rechnung;
	}
	
	
}
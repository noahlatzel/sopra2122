package de.wwu.sopra.model;

import java.io.Serializable;

public class Bestellung implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final int bestellnummer;
	final int betrag;
	BestellStatus status;
	final List<Produkt> produkte;


	/**
	 * Konstruktor der Klasse Bestellung
	 * @param bestellnummer Bestellnummer
	 * @param betrag Betrag 
	 * @param status Status der Bestellung
	 * @param produkte 
	 */
	public Bestellung(int bestellnummer , int betrag , BestellStatus status, List<Produkt> produkte) {
		
		this.bestellnummer = bestellnummer;
		this.betrag = betrag;
		this.status = status;
		this.produkte = produkte;
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
	public int getBetrag() {
		return betrag;
	}

	/**
	 * Getter Methode fuer die Liste der Produkte
	 * @return Liste der Produkte
	 */
	public List<Produkt> getProdukte() {
		return produkte;
	}
	
	
}

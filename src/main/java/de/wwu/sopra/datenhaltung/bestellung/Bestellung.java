package de.wwu.sopra.datenhaltung.bestellung;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.management.Produkt;

public class Bestellung implements Serializable {

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
	 * 
	 * @param bestellnummer Bestellnummer
	 * @param produkte      Produkte, die in der Bestellung enthalten sind
	 */
	public Bestellung(int bestellnummer, LocalDateTime datum, List<Produkt> produkte, Kunde kunde) {

		this.bestellnummer = bestellnummer;
		this.setStatus(BestellStatus.OFFEN);
		if (produkte.isEmpty()) {
			throw new IllegalArgumentException("Eine Bestellung kann nicht leer sein.");
		}
		this.produkte = produkte;
		this.betrag = calcBetrag();
		this.kunde = kunde;
	}

	/**
	 * Getter Methode fuer das Datum
	 * 
	 * @return Datum
	 */
	public LocalDateTime getDatum() {
		return datum;
	}

	/**
	 * Getter Methode fuer den Status
	 * 
	 * @return Status
	 */
	public BestellStatus getStatus() {
		return status;
	}

	public double calcBetrag() {
		double temp = 0;
		for (Produkt p : this.getProdukte()) {
			temp += p.getVerkaufspreis();
		}
		return temp;
	}

	/**
	 * Setter Methode fuer den Status
	 * 
	 * @param status Status
	 */
	public void setStatus(BestellStatus status) {
		this.status = status;
	}

	/**
	 * Getter Methode fuer Bestellnummer
	 * 
	 * @return Bestellnummer
	 */
	public int getBestellnummer() {
		return bestellnummer;
	}

	/**
	 * Getter Methode fuer den Betrag
	 * 
	 * @return Betrag
	 */
	public double getBetrag() {
		return betrag;
	}

	/**
	 * Getter Methode fuer die Liste der Produkte
	 * 
	 * @return Liste der Produkte
	 */
	public List<Produkt> getProdukte() {
		return produkte;
	}

	/**
	 * Getter Methode fuer den Kunden
	 * 
	 * @return Kunden
	 */
	public Kunde getKunde() {
		return kunde;
	}

	/**
	 * Getter Methode fuer die Rechnungen
	 * 
	 * @return Rechnungen
	 */
	public Rechnung getRechnung() {
		return rechnung;
	}

	/**
	 * Setteer Methode fuer die Rechnungen
	 * 
	 * @param rechnung Rechnung
	 */
	public void setRechnung(Rechnung rechnung) {
		this.rechnung = rechnung;
	}

	public int getKapazitaetBelegt() {
		return this.getProdukte().size();
	}

}

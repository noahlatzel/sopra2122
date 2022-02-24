package de.wwu.sopra.datenhaltung.bestellung;

import java.io.Serializable;
import java.util.List;

import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.system.Produkt;

public class Warenkorb implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double betrag;
	private final Kunde kunde;
	private List<Produkt> produkte;
	

	/**
	 * Konstruktor der Klasse Warenkorb
	 * @param betrag Betrag
	 * @param produkte Liste an Produkten
	 */
	public Warenkorb(double betrag, List<Produkt> produkte, Kunde kunde) {
		
		this.betrag = betrag;
		this.produkte = produkte;
		this.kunde = kunde;
		kunde.setWarenkorb(this);
	}
	
	/**
	 * Getter Methode fuer die Variable Betrag
	 * @return Betrag
	 */
	public double getBetrag() {
		return betrag;
	}

	/**
	 * Setter Methoder fuer die Variable Betrag
	 * @param betrag Betrag
	 */
	public void setBetrag(double betrag) {
		this.betrag = betrag;
	}

	/**
	 * Getter Methoode fuer die Liste mit Produkten
	 * @return Lste mit Produkten
	 */
	public List<Produkt> getProdukte() {
		return produkte;
	}

	/**
	 * Methode zum Hinzufuegen von Produkten
	 * @param produkt Produkt
	 */
    public void produkttHinzufuegen(Produkt produkt) {
    	this.produkte.add(produkt);
	}

    /**
	 * Methode zum Entfernen von Produkten
	 * @param produkt Produkt
	 */
    public void produktEntfernen(Produkt produkt) {
	 	this.produkte.remove(produkt);
	 }

    /**
     * Getter Methode fuer den Kunden
     * @return Kunden
     */
	public Kunde getKunde() {
		return kunde;
	}
	
	/**
     * Getter Methode fuer den Kunden
     */
	public void warenkorbLeeren() {
		this.produkte.clear();
	}
}

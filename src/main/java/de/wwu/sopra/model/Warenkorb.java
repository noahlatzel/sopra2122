package de.wwu.sopra.model;

import java.io.Serializable;

public class Warenkorb implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	float betrag;
	List<Produkt> produkte;

	/**
	 * Konstruktor der Klasse Warenkorb
	 * @param betrag Betrag
	 * @param produkte Liste an Produkten
	 */
	public Warenkorb(int betrag , List<Produkt> produkte) {
		
		this.betrag = betrag;
		this.produkte = produkte;
		
	}
	
	/**
	 * Getter Methode fuer die Variable Betrag
	 * @return Betrag
	 */
	public float getBetrag() {
		return betrag;
	}

	/**
	 * Setter Methoder fuer die Variable Betrag
	 * @param betrag Betrag
	 */
	public void setBetrag(float betrag) {
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
    
    

}

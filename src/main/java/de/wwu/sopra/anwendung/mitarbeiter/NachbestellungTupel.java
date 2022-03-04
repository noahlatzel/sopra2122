package de.wwu.sopra.anwendung.mitarbeiter;

import de.wwu.sopra.datenhaltung.management.Produkt;

/**
 * Die Klasse dient zur Realisierung der Nachbestellung. Das Tupel enthaelt ein
 * Produkt und eine Menge. Diese Tupel wird dann der Funktion bestelleNach() in
 * der LageristenSteuerung uebergbene.
 * 
 * @author Noah Latzel
 */
public class NachbestellungTupel {
	private final Produkt produkt;
	private final int menge;

	/**
	 * Erstellt ein neues NachbestellungsTupel. NachbestellungsTupel werden nur in
	 * der LageristenSteuerung verwendet, um die Nachbestellungen zu realisieren.
	 * 
	 * @param produkt Das nachzubestellende Produkt
	 * @param menge   Die Menge des nachzubestellenden Produkts
	 */
	public NachbestellungTupel(Produkt produkt, int menge) {
		this.produkt = produkt;
		this.menge = menge;
	}

	/**
	 * Gibt das Produkt der Nachbestellung zurueck.
	 * 
	 * @return Die Bestellung
	 */
	public Produkt getProdukt() {
		return this.produkt;
	}

	/**
	 * Gibt die Menge der Nachbestellung zurueck.
	 * 
	 * @return Die Menge
	 */
	public int getMenge() {
		return this.menge;
	}
}

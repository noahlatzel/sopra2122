package de.wwu.sopra.anwendungslogik;

import de.wwu.sopra.datenhaltung.management.Produkt;

/**
 * 
 *
 * Die Klasse dient zur Realisierung der Nachbestellung. Das Tupel enthaelt ein
 * Produkt und eine Menge. Diese Tupel wird dann der Funktion bestelleNach() in
 * der LageristenSteuerung uebergbene.
 * 
 * @author NoahLatzel
 */
public class NachbestellungTupel {
	private final Produkt produkt;
	private final int menge;

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

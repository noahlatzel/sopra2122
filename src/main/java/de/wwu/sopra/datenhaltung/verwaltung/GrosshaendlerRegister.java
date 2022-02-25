package de.wwu.sopra.datenhaltung.verwaltung;

import java.util.HashMap;

import de.wwu.sopra.datenhaltung.management.Produkt;

/**
 * Verwaltet die Preisliste des Grosshaendlers
 * 
 * @author NoahLatzel
 *
 */
public class GrosshaendlerRegister {
	private HashMap<String, Double> preisListe;

	/**
	 * Initialisiert das GrosshaendlerRegister mit einer neuen preisListe.
	 */
	public GrosshaendlerRegister() {
		preisListe = new HashMap<String, Double>();
	}

	/**
	 * Gibt den Preis eines Produkts zurueck.
	 * 
	 * @param produkt Das Produkt, fuer was der Preis abgefragt werden soll.
	 * @return Der Preis des Produkts, falls dieser gefuehrt wird.
	 */
	public double getPreis(Produkt produkt) {
		return preisListe.get(produkt.getName());
	}

	/**
	 * Setzt den Preis fuer ein Produkt.
	 * 
	 * @param produkt Das Produkt, fuer was ein neuer Preis festgelegt wird.
	 * @param preis   Der neue Preis.
	 */
	public void setPreis(Produkt produkt, double preis) {
		preisListe.put(produkt.getName(), preis);
	}

}

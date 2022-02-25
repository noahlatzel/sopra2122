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
	 * Gibt den Preis des Produkts zurueck.
	 * 
	 * @param produktname Name des Produkts, fuer welches der Preis abgefragt werden
	 *                    soll.
	 * @return Der Preis des Produkts, falls dieser gefuehrt wird.
	 */
	public double getPreis(String produktname) {
		return preisListe.get(produktname);
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

	/**
	 * Setzt den Preis fuer ein Produkt.
	 * 
	 * @param produktname Name des Produkts, fuer welches der Preis gesetzt werden
	 *                    soll.
	 * @param preis       Der neue Preis.
	 */
	public void setPreis(String produktname, double preis) {
		preisListe.put(produktname, preis);
	}

}

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
	private static HashMap<String, Double> preisListeIn = new HashMap<String, Double>();;

	/**
	 * Initialisiert das GrosshaendlerRegister mit einer neuen preisListe.
	 */
	public GrosshaendlerRegister() {
		preisListeIn.put("Coca Cola", 0.49);
		preisListeIn.put("Fanta", 0.49);
		preisListeIn.put("Sprite", 0.49);
		preisListeIn.put("Orangensaft", 0.49);
		preisListeIn.put("Milch", 0.49);
	}

	/**
	 * Gibt den Preis eines Produkts zurueck.
	 * 
	 * @param produkt Das Produkt, fuer was der Preis abgefragt werden soll.
	 * @return Der Preis des Produkts, falls dieser gefuehrt wird.
	 */
	public static double getPreis(Produkt produkt) {
		return preisListeIn.get(produkt.getName());
	}

	/**
	 * Gibt den Preis des Produkts zurueck.
	 * 
	 * @param produktname Name des Produkts, fuer welches der Preis abgefragt werden
	 *                    soll.
	 * @return Der Preis des Produkts, falls dieser gefuehrt wird.
	 */
	public static double getPreis(String produktname) {
		return preisListeIn.get(produktname);
	}

	/**
	 * Setzt den Preis fuer ein Produkt.
	 * 
	 * @param produkt Das Produkt, fuer was ein neuer Preis festgelegt wird.
	 * @param preis   Der neue Preis.
	 */
	public static void setPreis(Produkt produkt, double preis) {
		preisListeIn.put(produkt.getName(), preis);
	}

	/**
	 * Setzt den Preis fuer ein Produkt.
	 * 
	 * @param produktname Name des Produkts, fuer welches der Preis gesetzt werden
	 *                    soll.
	 * @param preis       Der neue Preis.
	 */
	public static void setPreis(String produktname, double preis) {
		preisListeIn.put(produktname, preis);
	}

}

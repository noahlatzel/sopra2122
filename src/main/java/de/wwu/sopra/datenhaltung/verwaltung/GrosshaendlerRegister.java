package de.wwu.sopra.datenhaltung.verwaltung;

import java.io.Serializable;
import java.util.HashMap;

import de.wwu.sopra.datenhaltung.management.Produkt;

/**
 * Verwaltet die Preisliste des Grosshaendlers
 * 
 * @author Noah Latzel
 *
 */
public class GrosshaendlerRegister implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static HashMap<String, Double> preisListeIn = new HashMap<String, Double>();
	// private static HashMap<String, Produkt> produktListe = new HashMap<String,
	// Produkt>();
	private static String path = "grosshaendlerReg.ser";

	/**
	 * Singleton Konstruktor
	 */
	private GrosshaendlerRegister() {
	}

	/**
	 * Gibt den Preis eines Produkts zurueck.
	 * 
	 * @param produkt Das Produkt, fuer was der Preis abgefragt werden soll.
	 * @return Der Preis des Produkts, falls dieser gefuehrt wird.
	 */
	public static double getEinkaufspreis(Produkt produkt) {
		return preisListeIn.get(produkt.getName());
		// return produktListe.get(produkt.getName()).getEinkaufspreis();
	}

	/**
	 * Gibt den Preis des Produkts zurueck.
	 * 
	 * @param produktname Name des Produkts, fuer welches der Preis abgefragt werden
	 *                    soll.
	 * @return Der Preis des Produkts, falls dieser gefuehrt wird.
	 */
	public static double getEinkaufspreis(String produktname) {
		return preisListeIn.get(produktname);
		// return produktListe.get(produktname).getEinkaufspreis();
	}

	/**
	 * Setzt den Preis fuer ein Produkt.
	 * 
	 * @param produkt Das Produkt, fuer was ein neuer Preis festgelegt wird.
	 * @param preis   Der neue Preis.
	 */
	public static void setEinkaufspreis(Produkt produkt, double preis) {
		preisListeIn.put(produkt.getName(), preis);
	}

	/**
	 * Setzt den Preis fuer ein Produkt.
	 * 
	 * @param produktname Name des Produkts, fuer welches der Preis gesetzt werden
	 *                    soll.
	 * @param preis       Der neue Preis.
	 */
	public static void setEinkaufspreis(String produktname, double preis) {
		preisListeIn.put(produktname, preis);
	}

	/**
	 * Gibt die Liste der Einkaufspreise zurueck.
	 * 
	 * @return Die Einkaufspreisliste.
	 */
	public static HashMap<String, Double> getPreislisteIn() {
		return preisListeIn;
	}

	/**
	 * Deserialisiert das FahrzeugRegister.
	 */
	public static void load() {
		SerialisierungPipeline<HashMap<String, Double>> sp = new SerialisierungPipeline<HashMap<String, Double>>();
		preisListeIn = sp.deserialisieren(path);
	}

	/**
	 * Serialisiert das FahrzeugRegister.
	 */
	public static void save() {
		SerialisierungPipeline<HashMap<String, Double>> sp = new SerialisierungPipeline<HashMap<String, Double>>();
		sp.serialisieren(GrosshaendlerRegister.preisListeIn, path);
	}

}

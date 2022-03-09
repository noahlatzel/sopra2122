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
	 * SerialisierungsID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Liste der Einkaufspreise
	 */
	private static HashMap<Integer, Double> preisListeIn = new HashMap<Integer, Double>();
	// private static HashMap<String, Produkt> produktListe = new HashMap<String,
	// Produkt>();
	/**
	 * Pfad zur Serialisierung
	 */
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
		return preisListeIn.get(produkt.getProduktID());
		// return produktListe.get(produkt.getName()).getEinkaufspreis();
	}

	/**
	 * Setzt den Preis fuer ein Produkt.
	 * 
	 * @param produkt Das Produkt, fuer was ein neuer Preis festgelegt wird.
	 * @param preis   Der neue Preis.
	 */
	public static void setEinkaufspreis(Produkt produkt, double preis) {
		preisListeIn.put(produkt.getProduktID(), preis);
	}

	/**
	 * Gibt die Liste der Einkaufspreise zurueck.
	 * 
	 * @return Die Einkaufspreisliste.
	 */
	public static HashMap<Integer, Double> getPreislisteIn() {
		return preisListeIn;
	}

	/**
	 * Deserialisiert das FahrzeugRegister.
	 */
	public static void load() {
		SerialisierungPipeline<HashMap<Integer, Double>> sp = new SerialisierungPipeline<HashMap<Integer, Double>>();
		preisListeIn = sp.deserialisieren(path, new HashMap<Integer, Double>());
	}

	/**
	 * Serialisiert das FahrzeugRegister.
	 */
	public static void save() {
		SerialisierungPipeline<HashMap<Integer, Double>> sp = new SerialisierungPipeline<HashMap<Integer, Double>>();
		sp.serialisieren(GrosshaendlerRegister.preisListeIn, path);
	}

}

package de.wwu.sopra.datenhaltung.management;

import java.io.Serializable;
import java.util.ArrayList;

import de.wwu.sopra.datenhaltung.verwaltung.SerialisierungPipeline;

/**
 * Klasse fuer die Statistiken
 * 
 * @author Valeria Vassalo
 *
 */
public class Statistiken implements Serializable {

	/**
	 * SerialisierungsID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Gesamtumsatz
	 */
	private static double umsatz = 0;
	/**
	 * Gesamtausgaben
	 */
	private static double ausgaben = 0;
	/**
	 * Gesamteinnahmen
	 */
	private static double einnahmen = 0;
	/**
	 * Gesamtarbeitszeit
	 */
	private static double arbeitszeit = 0;
	/**
	 * Pfad zur Serialisierung
	 */
	private static String path = "statistiken.ser";
	/**
	 * Pfad zur Serialisierung der Transaktionen
	 */
	private static String path_transaktion = "transaktionen.ser";
	/**
	 * Transaktionshistorie
	 */
	private static ArrayList<Transaktion> transaktionshistorie = new ArrayList<Transaktion>();

	/**
	 * Singleton Konstruktor
	 * 
	 * @inv Umsatz, Ausgaben und Arbeitszeit sind positiv
	 */
	private Statistiken() {

	}

	/**
	 * Getter Methode fuer den Umsatz
	 * 
	 * @return Umsatz
	 */
	public static double getUmsatz() {
		return umsatz;
	}

	/**
	 * Setter Methode fuer den Umsatz
	 * 
	 * @param umsatz Umsatz
	 */
	public static void setUmsatz(double umsatz) {
		Statistiken.umsatz = umsatz;

		// Klasseninvariante pruefen
		assert Statistiken.getUmsatz() >= 0 : "Klasseninvariante von Statistiken verletzt: Umsatz ist negativ";
	}

	/**
	 * Getter Methode fuer die Ausgaben
	 * 
	 * @return Ausgaben
	 */
	public static double getAusgaben() {
		return ausgaben;
	}

	/**
	 * Setter Methode fuer die Ausgaben
	 * 
	 * @param ausgaben Ausgaben
	 */
	public static void setAusgaben(double ausgaben) {
		Statistiken.ausgaben = ausgaben;

		// Klasseninvariante pruefen
		assert Statistiken.getAusgaben() >= 0 : "Klasseninvariante von Statistiken verletzt: Ausgaben sind negativ";
	}

	/**
	 * Getter Methode fuer die Einnahmen
	 * 
	 * @return Einnahmen
	 */
	public static double getEinnahmen() {
		return umsatz - ausgaben;
	}

	/**
	 * Setter Methode fuer die Einnahmen
	 * 
	 * @param einnahmen Einnahmen
	 */
	public static void setEinnahmen(double einnahmen) {
		Statistiken.einnahmen = einnahmen;
	}

	/**
	 * Getter Methode fuer die Arbeitszeit
	 * 
	 * @return Arbeitszeit
	 */
	public static double getArbeitszeit() {
		return arbeitszeit;
	}

	/**
	 * Setter Methode fuer die Arbeitszeit
	 * 
	 * @param arbeitszeit Arbeitszeit
	 */
	public static void setArbeitszeit(double arbeitszeit) {
		Statistiken.arbeitszeit = arbeitszeit;

		// Klasseninvariante pruefen
		assert Statistiken.getArbeitszeit() >= 0
				: "Klasseninvariante von Statistiken verletzt: Arbeitszeit ist negativ";
	}

	/**
	 * Fuegt einen Betrag zu den Ausgaben hinzu.
	 * 
	 * @param ausgaben Die Ausgaben, die hinzugekommen sind.
	 * @pre Der zu addierende Betrag muss positiv sein
	 */
	public static void addAusgaben(double ausgaben) {
		// Vorbedingung pruefen
		assert ausgaben >= 0 : "Vorbedingung von Ausgaben verletzt: die zu addierenden Ausgaben sind negativ";

		Statistiken.ausgaben += ausgaben;

	}

	/**
	 * Fuegt Zeit zu der Arbeitszeit hinzu.
	 * 
	 * @param zeit Die Zeit, die hinzugekommen ist.
	 * @pre Die zu addierende Zeit muss positiv sein
	 */
	public static void addArbeitszeit(double zeit) {
		// Vorbedingung pruefen
		assert zeit >= 0 : "Vorbedingung von Arbeitszeit verletzt: die zu addierenden Arbeitszeit ist negativ";

		Statistiken.arbeitszeit += zeit;

	}

	/**
	 * Gibt die Transaktionshistorie zurueck.
	 * 
	 * @return Transaktionshistorie
	 */
	public static ArrayList<Transaktion> getTransaktionshistorie() {
		return transaktionshistorie;
	}

	/**
	 * Fuegt eine Transaktion hinzu.
	 * 
	 * @param transaktion Transaktion
	 */
	public static void addTransaktion(Transaktion transaktion) {
		transaktionshistorie.add(transaktion);
	}

	/**
	 * Deserialisiert das FahrzeugRegister.
	 */
	public static void load() {
		SerialisierungPipeline<ArrayList<Double>> sp = new SerialisierungPipeline<ArrayList<Double>>();
		SerialisierungPipeline<ArrayList<Transaktion>> sp1 = new SerialisierungPipeline<ArrayList<Transaktion>>();
		transaktionshistorie = sp1.deserialisieren(path_transaktion, new ArrayList<Transaktion>());
		ArrayList<Double> raw_statistiken = sp.deserialisieren(path, null);
		if (raw_statistiken != null) {
			Statistiken.setUmsatz(raw_statistiken.get(0));
			Statistiken.setAusgaben(raw_statistiken.get(1));
			Statistiken.setEinnahmen(raw_statistiken.get(2));
			Statistiken.setArbeitszeit(raw_statistiken.get(3));
		} else {
			Statistiken.setUmsatz(0);
			Statistiken.setAusgaben(0);
			Statistiken.setEinnahmen(0);
			Statistiken.setArbeitszeit(0);
		}
	}

	/**
	 * Serialisiert das FahrzeugRegister.
	 */
	public static void save() {
		SerialisierungPipeline<ArrayList<Double>> sp = new SerialisierungPipeline<ArrayList<Double>>();
		SerialisierungPipeline<ArrayList<Transaktion>> sp1 = new SerialisierungPipeline<ArrayList<Transaktion>>();
		ArrayList<Double> raw_statistiken = new ArrayList<Double>();
		raw_statistiken.add(umsatz);
		raw_statistiken.add(ausgaben);
		raw_statistiken.add(einnahmen);
		raw_statistiken.add(arbeitszeit);
		sp.serialisieren(raw_statistiken, path);
		sp1.serialisieren(transaktionshistorie, path_transaktion);
	}

}

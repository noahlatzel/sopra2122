package de.wwu.sopra.datenhaltung.management;

import java.io.Serializable;
import java.util.ArrayList;

import de.wwu.sopra.datenhaltung.verwaltung.SerialisierungPipeline;

/**
 * Klasse fuer die Statistiken
 * 
 * @author valeria
 *
 */
public class Statistiken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static double umsatz;
	private static double ausgaben;
	private static double einnahmen;
	private static double arbeitszeit;
	private static String path = "statistiken.ser";

	/**
	 * Konstruktor der Klasse Statistiken
	 */
	public Statistiken() {
		setUmsatz(0);
		setAusgaben(0);
		setEinnahmen(0);
		setArbeitszeit(0);

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
	}

	/**
	 * Getter Methode fuer die Einnahmen
	 * 
	 * @return Einnahmen
	 */
	public static double getEinnahmen() {
		return einnahmen;
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
	}

	/**
	 * Fuegt einen Betrag zu den Ausgaben hinzu.
	 * 
	 * @param ausgaben Die Ausgaben, die hinzugekommen sind.
	 */
	public static void addAusgaben(double ausgaben) {
		Statistiken.ausgaben += ausgaben;
	}

	/**
	 * Deserialisiert das FahrzeugRegister.
	 */
	public static void load() {
		SerialisierungPipeline<ArrayList<Double>> sp = new SerialisierungPipeline<ArrayList<Double>>();
		ArrayList<Double> raw_statistiken = sp.deserialisieren(path);
		if (raw_statistiken != null) {
			Statistiken.setUmsatz(raw_statistiken.get(0));
			Statistiken.setAusgaben(raw_statistiken.get(1));
			Statistiken.setEinnahmen(raw_statistiken.get(2));
			Statistiken.setArbeitszeit(raw_statistiken.get(3));
		}
	}

	/**
	 * Serialisiert das FahrzeugRegister.
	 */
	public static void save() {
		SerialisierungPipeline<ArrayList<Double>> sp = new SerialisierungPipeline<ArrayList<Double>>();
		ArrayList<Double> raw_statistiken = new ArrayList<Double>();
		raw_statistiken.add(umsatz);
		raw_statistiken.add(ausgaben);
		raw_statistiken.add(einnahmen);
		raw_statistiken.add(arbeitszeit);
		sp.serialisieren(raw_statistiken, path);
	}

}

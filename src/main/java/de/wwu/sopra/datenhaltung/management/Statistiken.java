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
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static double umsatz = 0;
	private static double ausgaben = 0;
	private static double einnahmen = 0;
	private static double arbeitszeit = 0;
	private static String path = "statistiken.ser";

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
		assert Statistiken.getAusgaben() >= 0 : "Klasseninvariante von Statistiken verletzt: Ausgaben sind negativ";
		assert Statistiken.getUmsatz() >= 0 : "Klasseninvariante von Statistiken verletzt: Umsatz ist negativ";
		assert Statistiken.getArbeitszeit() >= 0
				: "Klasseninvariante von Statistiken verletzt: Arbeitszeit ist negativ";
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
		assert Statistiken.getUmsatz() >= 0 : "Klasseninvariante von Statistiken verletzt: Umsatz ist negativ";
		assert Statistiken.getArbeitszeit() >= 0
				: "Klasseninvariante von Statistiken verletzt: Arbeitszeit ist negativ";
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

		// Klasseninvariante pruefen
		assert Statistiken.getAusgaben() >= 0 : "Klasseninvariante von Statistiken verletzt: Ausgaben sind negativ";
		assert Statistiken.getUmsatz() >= 0 : "Klasseninvariante von Statistiken verletzt: Umsatz ist negativ";
		assert Statistiken.getArbeitszeit() >= 0
				: "Klasseninvariante von Statistiken verletzt: Arbeitszeit ist negativ";
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
		assert Statistiken.getAusgaben() >= 0 : "Klasseninvariante von Statistiken verletzt: Ausgaben sind negativ";
		assert Statistiken.getUmsatz() >= 0 : "Klasseninvariante von Statistiken verletzt: Umsatz ist negativ";
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
		assert ausgaben > 0 : "Vorbedingung von Ausgaben verletzt: die zu addierenden Ausgaben sind negativ";

		Statistiken.ausgaben += ausgaben;

		// Klasseninvariante pruefen
		assert Statistiken.getAusgaben() >= 0 : "Klasseninvariante von Statistiken verletzt: Ausgaben sind negativ";
		assert Statistiken.getUmsatz() >= 0 : "Klasseninvariante von Statistiken verletzt: Umsatz ist negativ";
		assert Statistiken.getArbeitszeit() >= 0
				: "Klasseninvariante von Statistiken verletzt: Arbeitszeit ist negativ";
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

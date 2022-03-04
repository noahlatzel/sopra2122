package de.wwu.sopra.datenhaltung.verwaltung;

import java.io.Serializable;
import java.util.HashSet;

import de.wwu.sopra.datenhaltung.management.Fahrzeug;

/**
 * Die Klasse verwaltet die Speicherung aller Fahrzeugdaten, also unter anderem
 * Routen und Fahrzeuge.
 * 
 * @author Paul Dirksen
 *
 */
public class FahrzeugRegister implements Serializable {
	/**
	 * SerialisierungsID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Menge der Fahrzeuge
	 */
	private static HashSet<Fahrzeug> fahrzeuge = new HashSet<Fahrzeug>();
	/**
	 * Pfad zur Serialisierung
	 */
	private static String path = "fahrzeugReg.ser";
	/**
	 * Zaehler fuer Fahrzeugnummer
	 */
	private static int zaehler = 0;
	/**
	 * Zaehler fuer Routennummer
	 */
	private static int zaehlerRoute = 0;

	/**
	 * Singleton Konstruktor
	 */
	private FahrzeugRegister() {

	}

	/**
	 * Fuegt ein Fahrzeug dem Register hinzu.
	 * 
	 * @param fahrzeug Das Fahrzeug, was dem Register hinzugefuegt werden soll.
	 */
	public static void addFahrzeug(Fahrzeug fahrzeug) {
		FahrzeugRegister.fahrzeuge.add(fahrzeug);
	}

	/**
	 * Entfernt ein Fahrzeug, falls vorhanden, aus dem Register.
	 * 
	 * @param fahrzeug Das Fahrzeug, was aus dem Register geloescht werden soll.
	 */
	public static void removeFahrzeug(Fahrzeug fahrzeug) {
		FahrzeugRegister.fahrzeuge.remove(fahrzeug);
	}

	/**
	 * Gibt die Liste aller registrierten Fahrzeuge zurueck.
	 * 
	 * @return Die Liste aller registrierten Fahrzeuge.
	 */
	public static HashSet<Fahrzeug> getFahrzeuge() {
		return FahrzeugRegister.fahrzeuge;
	}

	/**
	 * Gibt den Zaehler der Fahrzeug zurueck.
	 * 
	 * @return Den Zaehler
	 */
	public static int getZaehler() {
		for (Fahrzeug f : FahrzeugRegister.getFahrzeuge()) {
			if (f.getFahrzeugNummer() == FahrzeugRegister.zaehler) {
				FahrzeugRegister.zaehler++;
			}
		}
		return ++FahrzeugRegister.zaehler;
	}

	/**
	 * Deserialisiert das FahrzeugRegister.
	 */
	public static void load() {
		SerialisierungPipeline<HashSet<Fahrzeug>> sp = new SerialisierungPipeline<HashSet<Fahrzeug>>();
		FahrzeugRegister.fahrzeuge = sp.deserialisieren(path);
	}

	/**
	 * Serialisiert das FahrzeugRegister.
	 */
	public static void save() {
		SerialisierungPipeline<HashSet<Fahrzeug>> sp = new SerialisierungPipeline<HashSet<Fahrzeug>>();
		sp.serialisieren(FahrzeugRegister.getFahrzeuge(), path);
	}

	/**
	 * Setzt das FahrzeugRegister zurueck (fuer Tests).
	 */
	public static void reset() {
		HashSet<Fahrzeug> register_old = (HashSet<Fahrzeug>) FahrzeugRegister.getFahrzeuge().clone();
		for (Fahrzeug p : register_old) {
			FahrzeugRegister.removeFahrzeug(p);
		}
	}

	/**
	 * Gibt den Zaehler fuer Route zurueck.
	 * 
	 * @return Den Zaehler fuer Route
	 */
	public static int getZaehlerRoute() {
		for (Fahrzeug f : FahrzeugRegister.getFahrzeuge()) {
			if (f.getRoute() != null && f.getRoute().getRoutenNummer() == FahrzeugRegister.zaehlerRoute) {
				FahrzeugRegister.zaehlerRoute++;
			}
		}
		return ++zaehlerRoute;
	}
}

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
	private static int zaehler = 1;
	/**
	 * Liste fuer Fahrzeugnummer
	 */
	private static HashSet<Integer> fahrzeugNummerListe = new HashSet<Integer>();
	/**
	 * Zaehler fuer Routennummer
	 */
	private static int zaehlerRoute = 1;

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
		if (fahrzeug != null) {
			FahrzeugRegister.fahrzeuge.add(fahrzeug);
			FahrzeugRegister.fahrzeugNummerListe.add(fahrzeug.getFahrzeugNummer());
		}
	}

	/**
	 * Entfernt ein Fahrzeug, falls vorhanden, aus dem Register.
	 * 
	 * @param fahrzeug Das Fahrzeug, was aus dem Register geloescht werden soll.
	 */
	public static void removeFahrzeug(Fahrzeug fahrzeug) {
		if (fahrzeug != null) {
			FahrzeugRegister.fahrzeuge.remove(fahrzeug);
			FahrzeugRegister.fahrzeugNummerListe.remove(fahrzeug.getFahrzeugNummer());
			zaehler = 1;
		}
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
		for (Fahrzeug fahrzeug : fahrzeuge) {
			fahrzeugNummerListe.add(fahrzeug.getFahrzeugNummer());
		}
		while (fahrzeugNummerListe.contains(zaehler)) {
			zaehler++;
			System.out.println(zaehler);
		}
		fahrzeugNummerListe.add(zaehler);
		return FahrzeugRegister.zaehler;
	}

	/**
	 * Deserialisiert das FahrzeugRegister.
	 */
	public static void load() {
		SerialisierungPipeline<HashSet<Fahrzeug>> sp = new SerialisierungPipeline<HashSet<Fahrzeug>>();
		FahrzeugRegister.fahrzeuge = sp.deserialisieren(path, new HashSet<Fahrzeug>());

		// Null Objekte loeschen
		HashSet<Fahrzeug> temp = new HashSet<Fahrzeug>();

		for (Fahrzeug fzeug : FahrzeugRegister.getFahrzeuge()) {
			if (fzeug == null) {
				temp.add(fzeug);
			}
		}
		for (Fahrzeug fzeug : temp) {
			fahrzeuge.remove(fzeug);
		}
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
		zaehlerRoute = zaehlerRoute + 1;
		return zaehlerRoute;
	}
}

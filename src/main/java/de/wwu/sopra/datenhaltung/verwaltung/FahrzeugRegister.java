package de.wwu.sopra.datenhaltung.verwaltung;

import java.io.Serializable;
import java.util.HashSet;

import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Produkt;

/**
 * Die Klasse verwaltet die Speicherung aller Fahrzeugdaten, also unter anderem
 * Routen und Fahrzeuge.
 * 
 * @author Paul Dirksen
 *
 */
public class FahrzeugRegister implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static HashSet<Fahrzeug> fahrzeuge = new HashSet<Fahrzeug>();
	private static String path = "fahrzeugReg.ser";

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
	 * Deserialisiert das FahrzeugRegister.
	 */
	public static void load() {
		SerialisierungPipeline<HashSet<Fahrzeug>> sp = new SerialisierungPipeline<HashSet<Fahrzeug>>();
		FahrzeugRegister.fahrzeuge = sp.deserialisieren(path);
		if (FahrzeugRegister.fahrzeuge == null) {
			FahrzeugRegister.fahrzeuge = new HashSet<Fahrzeug>();
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
}

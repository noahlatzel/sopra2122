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
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static HashSet<Fahrzeug> fahrzeuge = new HashSet<Fahrzeug>();
	private static String path = "fahrzeugReg.ser";

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
	@SuppressWarnings("unchecked")
	public static void load() {
		SerialisierungPipeline sp = new SerialisierungPipeline();
		FahrzeugRegister.fahrzeuge = (HashSet<Fahrzeug>) sp.deserialisieren(path);
		if (FahrzeugRegister.fahrzeuge == null) {
			FahrzeugRegister.fahrzeuge = new HashSet<Fahrzeug>();
		}
	}

	/**
	 * Serialisiert das FahrzeugRegister.
	 */
	public static void save() {
		SerialisierungPipeline sp = new SerialisierungPipeline();
		sp.serialisieren(FahrzeugRegister.getFahrzeuge(), path);
	}
}

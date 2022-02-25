package de.wwu.sopra.datenhaltung.verwaltung;

import java.util.HashSet;

import de.wwu.sopra.datenhaltung.management.Fahrzeug;

/**
 * Die Klasse verwaltet die Speicherung aller Fahrzeugdaten, also unter anderem
 * Routen und Fahrzeuge.
 * 
 * @author Paul Dirksen
 *
 */
public class FahrzeugRegister {
	private HashSet<Fahrzeug> fahrzeuge = new HashSet<Fahrzeug>();

	/**
	 * Fuegt ein Fahrzeug dem Register hinzu.
	 * 
	 * @param fahrzeug Das Fahrzeug, was dem Register hinzugefuegt werden soll.
	 */
	public void addFahrzeug(Fahrzeug fahrzeug) {
		this.fahrzeuge.add(fahrzeug);
	}

	/**
	 * Entfernt ein Fahrzeug, falls vorhanden, aus dem Register.
	 * 
	 * @param fahrzeug Das Fahrzeug, was aus dem Register geloescht werden soll.
	 */
	public void removeFahrzeug(Fahrzeug fahrzeug) {
		this.fahrzeuge.remove(fahrzeug);
	}

	/**
	 * Gibt die Liste aller registrierten Fahrzeuge zurueck.
	 * 
	 * @return Die Liste aller registrierten Fahrzeuge.
	 */
	public HashSet<Fahrzeug> getFahrzeuge() {
		return this.fahrzeuge;
	}
}

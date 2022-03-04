package de.wwu.sopra.datenhaltung.benutzer;

import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.bestellung.Warenkorb;

/**
 * Implementiert die Klasse Kunde.
 * 
 * @author Paul Dirksen
 *
 */
public class Kunde extends Benutzer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Rolle rolle = Rolle.KUNDE;
	private List<Bestellung> bestellungen = new ArrayList<Bestellung>();
	private Warenkorb warenkorb;

	/**
	 * Erstellt einen neuen Kunden mit den uebergebenen Eigenschaften.
	 * 
	 * @param benutzername   benutzername
	 * @param passwort       passwort
	 * @param email          email
	 * @param adresse        adresse
	 * @param vorname        vorname
	 * @param name           name
	 * @param bankverbindung bankverbindung
	 */
	public Kunde(String benutzername, String passwort, String email, String adresse, String vorname, String name,
			String bankverbindung) {
		super(benutzername, passwort, email, adresse, vorname, name, bankverbindung);
	}

	/**
	 * Fuegt eine neue Bestellung zur Liste der Bestellungen hinzu
	 * 
	 * @param bestellung Bestellung
	 * @throws NullPointerException Die uebergebene Bestellung ist null
	 * @pre Die Bestellung ist nicht leer
	 * @post Die Bestellung wurde dem Kunden hinzugefuegt und ist so vermerkt
	 */
	public void bestellungHinzufuegen(Bestellung bestellung) throws NullPointerException {
		// Vorbedingung pruefen
		assert !bestellung.getProdukte().isEmpty()
				: "Vorbedingung von bestellungHinzufuegen verletzt: die uebergebene Bestellung ist leer";

		if (bestellung == null)
			throw new NullPointerException();
		if (!this.bestellungen.contains(bestellung)) {
			this.bestellungen.add(bestellung);
		}

		// Nachbedingung pruefen
		assert bestellung.getKunde().equals(this)
				: "Nachbedingung von bestellungHinzufuegen verletzt: die Bestellung enthaelt nicht den Kunden als Referenz";
		assert this.bestellungen.contains(bestellung)
				: "Nachbedingung von bestellungHinzufuegen verletzt: die Bestellung ist nicht in der Liste der Bestellungen des Kunden";
	}

	/**
	 * Gibt eine Liste der Bestellungen des Kunden zurueck
	 * 
	 * @return Liste von Bestellungen
	 */
	public List<Bestellung> getBestellungen() {
		return bestellungen;
	}

	/**
	 * Gibt den Warenkorb zurueck
	 * 
	 * @return warenkorb
	 */
	public Warenkorb getWarenkorb() {
		return warenkorb;
	}

	/**
	 * Setzt den Warenkorb
	 * 
	 * @param warenkorb warenkorb
	 * @post Der Warenkorb ist dem Kunden zugeordnet und andersherum
	 */
	public void setWarenkorb(Warenkorb warenkorb) {
		this.warenkorb = warenkorb;

		// Nachbedingung pruefen
		assert warenkorb.getKunde().equals(this)
				: "Nachbedingung von setWarenkorb() verletzt: der Kunde ist nicht dem Warenkorb zugeordnet";
		assert this.getWarenkorb().equals(warenkorb)
				: "Nachbedingung von setWarenkorb() verletzt: der Warenkorb ist nicht dem Kunden zugeordnet";
	}

	/**
	 * Gibt die Rolle zurueck
	 * 
	 * @return rolle Kunde
	 */
	public Rolle getRolle() {
		return rolle;
	}

}

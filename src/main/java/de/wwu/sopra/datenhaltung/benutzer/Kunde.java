package de.wwu.sopra.datenhaltung.benutzer;

import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.bestellung.Warenkorb;
import de.wwu.sopra.datenhaltung.management.Produkt;

/**
 * Implementiert die Klasse Kunde.
 * 
 * @author Paul Dirksen
 *
 */
public class Kunde extends Benutzer {

	/**
	 * SerialisierungsID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Rolle des Kunden
	 */
	private final Rolle rolle = Rolle.KUNDE;
	/**
	 * Liste der Bestellungen des Kunden
	 */
	private List<Bestellung> bestellungen = new ArrayList<Bestellung>();
	/**
	 * Warenkorb des Kunden
	 */
	private Warenkorb warenkorb = new Warenkorb(new ArrayList<Produkt>(), this);

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
	 * <<<<<<< HEAD Gibt den Warenkorb aus ======= Gibt den Warenkorb zurueck
	 * >>>>>>> 3c2912881e35c2a2a3bedf74d766fbab0240e668
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
	 * <<<<<<< HEAD Gibt die Rolle aus ======= Gibt die Rolle zurueck >>>>>>>
	 * 3c2912881e35c2a2a3bedf74d766fbab0240e668
	 * 
	 * @return rolle Kunde
	 */
	public Rolle getRolle() {
		return rolle;
	}

	/**
	 * Setzt die Liste der Bestellungen auf null;
	 */
	public void kundeEntfernen() {
		this.bestellungen = null;
	}
}

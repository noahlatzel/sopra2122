package de.wwu.sopra.datenhaltung.benutzer;

import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.bestellung.Rabatt;
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
	 * Liste mit Rabattcodes des Kunden
	 */
	private List<Rabatt> rabatte = new ArrayList<Rabatt>();

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
	public void bestellungHinzufuegen(Bestellung bestellung) {
		// Vorbedingung pruefen
		assert bestellung != null && !bestellung.getProdukte().isEmpty()
				: "Vorbedingung von bestellungHinzufuegen verletzt: die uebergebene Bestellung ist null";
		if (!this.bestellungen.contains(bestellung)) {
			this.bestellungen.add(bestellung);
		}

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

	}

	/**
	 * Gibt die Rolle zurueck
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

	/**
	 * Fuegt Rabattcode zu den fuer den Kunden verfuegbaren Rabattcodes hinzu.
	 * 
	 * @param rabattcode Wird zu den verfuegbaren Rabattcodes hinzugefuegt.
	 * @param prozent    wie viel prozent
	 */
	public void addRabatt(String rabattcode, int prozent) {
		assert prozent > 0 && prozent <= 100 : "Die Prozentzahl liegt nicht zwischen 0 und 100 Prozent.";
		rabatte.add(new Rabatt(rabattcode, prozent));
	}

	/**
	 * Entfernt Rabatt aus den fuer den Kunden verfuegbaren Rabatten.
	 * 
	 * @param rabattcode Wird aus den verfuegbaren Rabatten entfernt.
	 */
	private void removeRabatt(Rabatt rabatt) {
		rabatte.remove(rabatt);
	}

	/**
	 * Ermittelt, ob der uebergebene Rabattcode ein gueltiger, einloesbarer
	 * Rabattcode ist.
	 * 
	 * @param rabattcode Wird auf Gueltigkeit ueberprueft.
	 * @return Gibt Wahrheitswert zurueck, ob der Rabattcode gueltig ist, true, wenn
	 *         er gueltig ist.
	 */
	public boolean getRabattGueltig(String rabattcode) {
		Rabatt rabatt = getRabattZuRabattCode(rabattcode);
		return rabatt != null;
	}

	/**
	 * Entfernt den Rabatt aus den einloesbaren Rabatten.
	 * 
	 * @param rabattcode Einzuloesender Rabattcode.
	 * @return Gibt Rabatt zurueck.
	 */
	public Rabatt rabattEinloesen(String rabattcode) {
		Rabatt rabatt = getRabattZuRabattCode(rabattcode);
		removeRabatt(rabatt);
		return rabatt;
	}

	/**
	 * Gibt den zum Rabattcode gehoerenden Rabatt zurueck.
	 * 
	 * @param rabattcode Rabattcode zu dem der Rabatt ermittelt wird.
	 * @return Rabatt zum zugehoerigen Rabattcode.
	 */
	private Rabatt getRabattZuRabattCode(String rabattcode) {
		assert rabattcode != null : "Uebergebener Rabattcode ist leer!";

		Rabatt getRabatt = null;

		for (Rabatt rabatt : rabatte) {
			if (rabatt.getRabattcode().equals(rabattcode)) {
				getRabatt = rabatt;
			}
		}
		return getRabatt;
	}

	/**
	 * Gibt die Prozentzahl des Rabatts zum uebergebenen Rabattcodes zurueck.
	 * 
	 * @param rabattcode Prozentzahl des Rabatts wird zu diesem Rabattcode
	 *                   ermittelt.
	 * @return Prozentzahl des Rabatts zum uebergebenen Rabattcodes zurueck
	 */
	public int getRabattProzent(String rabattcode) {
		Rabatt rabatt = getRabattZuRabattCode(rabattcode);
		return rabatt.getProzent();
	}

	/**
	 * Gibt Liste mit Rabatten des Kunden zurueck.
	 * 
	 * @return Liste mit Rabatten des Kunden.
	 */
	public List<Rabatt> getRabatte() {
		return rabatte;
	}
}

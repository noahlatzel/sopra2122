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

	private static final long serialVersionUID = 1L;
	private final Rolle rolle = Rolle.KUNDE;
	private List<Bestellung> bestellungen = new ArrayList<Bestellung>();
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
	 * @param bestellung bestellung
	 */
	public void bestellungHinzufuegen(Bestellung bestellung) throws NullPointerException {
		if (bestellung == null)
			throw new NullPointerException();
		if (!this.bestellungen.contains(bestellung)) {
			this.bestellungen.add(bestellung);
		}
	}

	/**
	 * gibt eine Liste der Bestellungen des Kunden aus
	 * 
	 * @return Liste von Bestellungen
	 */
	public List<Bestellung> getBestellungen() {
		return bestellungen;
	}

	/**
	 * Gibt den Warenkorb aus
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
	 */
	public void setWarenkorb(Warenkorb warenkorb) {
		this.warenkorb = warenkorb;
	}

	/**
	 * Gibt die Rolle aus
	 * 
	 * @return rolle.Kunde
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

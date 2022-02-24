package de.wwu.sopra.datenhaltung.verwaltung;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.datenhaltung.benutzer.Benutzer;
import de.wwu.sopra.datenhaltung.benutzer.Rolle;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.management.Produkt;

/**
 * Klasse erstellt ein Tripel aus einem Benutzer und dessen Liste an
 * Bestellungen und Warenkorb.
 * 
 * @param benutzer
 * @author Paul Dirksen
 */
@SuppressWarnings("serial")
public class BenutzerDatenTripel implements Serializable {

	private Benutzer benutzer;
	private List<Bestellung> bestellungen;
	private List<Produkt> warenkorb;

	/**
	 * Erstellt ein neues Tripel fuer einen Kunden mit leerer Liste an Bestellungen
	 * und einem leeren Warenkorb. Fuer alle anderen Benutzer wird keine Liste
	 * erstellt.
	 * 
	 * @param benutzer
	 */
	public BenutzerDatenTripel(Benutzer benutzer) {
		this.benutzer = benutzer;
		if (benutzer.getRolle() == Rolle.KUNDE) {
			bestellungen = new ArrayList<Bestellung>();
			warenkorb = new ArrayList<Produkt>();
		} else {
			bestellungen = null;
			warenkorb = null;
		}
	}

	/**
	 * Gibt den Benutzer des Tripels zurueck.
	 * 
	 * @return
	 */
	public Benutzer getBenutzer() {
		return benutzer;
	}

	/**
	 * Gibt die Liste der Bestellungen zurueck.
	 * 
	 * @return
	 */
	public List<Bestellung> getBestellungen() {
		return bestellungen;
	}

	/**
	 * Gibt den Warenkorb zurueck.
	 * 
	 * @return
	 */
	public List<Produkt> getWarenkorb() {
		return warenkorb;
	}

}

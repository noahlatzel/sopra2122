package de.wwu.sopra.datenhaltung.bestellung;

import java.io.Serializable;

/**
 * Dient zur eerhaltung der Aktuellen idnummern fuer Rechnung und bestellung
 * 
 * @author Johannes Thiel
 *
 */

public class IdZaehler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// id der letzten Bestellung
	private static int bestellungsId = 1;

	// id der letzten Rechnung
	private static int rechnungsId = 1;

	/**
	 * Die BestellungsId wird erhoeht und dann ausgegeben
	 * 
	 * @return BestellungsId
	 */
	public static int getBestellungsId() {
		naechsteBestellungsId();
		return IdZaehler.bestellungsId;
	}

	/**
	 * Bestellungsid wird hoch gesetzt
	 */
	private static void naechsteBestellungsId() {
		IdZaehler.bestellungsId++;
	}

	/**
	 * Die rechungsid wird erhoeht und dann ausgegeben
	 * 
	 * @return rechnungsid
	 */
	public static int getRechnungsId() {
		naechsteRechnungsId();
		return IdZaehler.rechnungsId;
	}

	/**
	 * RechnungsId wird hoch gesetzt
	 */
	private static void naechsteRechnungsId() {
		IdZaehler.rechnungsId++;
	}

}

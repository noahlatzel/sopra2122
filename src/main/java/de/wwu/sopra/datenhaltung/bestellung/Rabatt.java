package de.wwu.sopra.datenhaltung.bestellung;

import java.io.Serializable;

/**
 * die klasse fuer die Rabatte
 * 
 * @author Paul Dirksen
 *
 */

public class Rabatt implements Serializable {

	/**
	 * SerialisierungsID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Rabattcode des Rabatts
	 */
	private String rabattcode;
	/**
	 * Prozente des Rabatts
	 */
	private int prozent;

	/**
	 * KOnsturktor fuer den Rabatt
	 * 
	 * @param rabattcode der code
	 * @param prozent    wie viele Prozente gibt es
	 */

	public Rabatt(String rabattcode, int prozent) {
		assert prozent > 0 && prozent <= 100 : "Die Prozentzahl liegt nicht zwischen 0 und 100 Prozent.";
		this.rabattcode = rabattcode;
		this.prozent = prozent;
	}

	/**
	 * Gibt den Rabattcode zurueck.
	 * 
	 * @return Gibt den Rabattcode zurueck.
	 */
	public String getRabattcode() {
		return rabattcode;
	}

	/**
	 * @return the prozent
	 */
	public int getProzent() {
		return prozent;
	}

}

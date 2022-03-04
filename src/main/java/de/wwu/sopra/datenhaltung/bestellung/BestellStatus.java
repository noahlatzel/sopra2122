package de.wwu.sopra.datenhaltung.bestellung;

/**
 * Moegliche Bestellstatus
 * 
 * @author Johannes Thiel
 */
public enum BestellStatus {
	/**
	 * Eine Bestellung, die noch nicht einer Route zugeteilt wurde
	 */
	OFFEN,
	/**
	 * Eine Bestellung, die storniert wurde
	 */
	STORNIERT,
	/**
	 * Eine Bestellung, die in einer Route liegt, die in einem Fahrzeug mit Fahrer
	 * ist
	 */
	IN_ZUSTELLUNG,
	/**
	 * Eine Bestellung, die zugestellt wurde
	 */
	ABGESCHLOSSEN,
	/**
	 * Eine Bestellung, die einer Route zugeordnet wurde
	 */
	IN_BEARBEITUNG

}

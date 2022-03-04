/**
 * 
 */
package de.wwu.sopra.datenhaltung.management;

/**
 * Die Zustaende des Fahrzeugs
 * 
 * @author Valeria Vassallo
 */
public enum FahrzeugStatus {
	/**
	 * Fahrzeug ist mit einer Route belegt
	 */
	BELEGT,
	/**
	 * Fahrzeug hat Route und Fahrer
	 */
	IN_ZUSTELLUNG,
	/**
	 * Fahrzeug hat weder Route noch Fahrer
	 */
	FREI
}

/**
 * 
 */
package de.wwu.sopra.model;

/**
 * Erstellung der Route-Klasse
 */
public class Route {
	private int routenNummer;
	private Fahrzeug fahrzeug;
	
	public Route(int routenNummer, Fahrzeug fahrzeug) {
		this.routenNummer = routenNummer;
		this.fahrzeug = fahrzeug;
		this.fahrzeug.setRoute(this);
	}

	/**
	 * Routennummer der Route
	 * @return routenNummer
	 */
	public int getRoutenNummer() {
		return routenNummer;
	}

	/**
	 * Routennummer der Route aendern/setzen
	 * @param routenNummer zu setzen
	 */
	public void setRoutenNummer(int routenNummer) {
		this.routenNummer = routenNummer;
	}

	/**
	 * Fahrzeug zugeordnet zu dieser Route
	 * @return zugeordnete fahrzeug
	 */
	public Fahrzeug getFahrzeug() {
		return fahrzeug;
	}
}

/**
 * 
 */
package de.wwu.sopra.model;

/**
 * @author Valeria Vassallo
 *
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
	 * @return routenNummer
	 */
	public int getRoutenNummer() {
		return routenNummer;
	}

	/**
	 * @param routenNummer zu setzen
	 */
	public void setRoutenNummer(int routenNummer) {
		this.routenNummer = routenNummer;
	}

	/**
	 * @return zugeordnete fahrzeug
	 */
	public Fahrzeug getFahrzeug() {
		return fahrzeug;
	}
}

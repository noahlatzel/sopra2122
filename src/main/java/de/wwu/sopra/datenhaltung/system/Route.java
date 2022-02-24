/**
 * 
 */
package de.wwu.sopra.datenhaltung.system;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.wwu.sopra.datenhaltung.bestellung.Bestellung;

/**
 * Erstellung der Route-Klasse
 */
public class Route implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Set<Integer> RoutenNummern = new HashSet<Integer>();

	private int routenNummer;
	private Fahrzeug fahrzeug;
	private List<Bestellung> bestellungen;

	/**
	 * Neues Route-Objekt erstellen nur wenn angegebene routenNummer nicht auf der
	 * Liste existiert
	 * 
	 * @param routenNummer
	 * @param fahrzeug
	 * @throws IllegalArgumentException
	 */
	public Route(int routenNummer, Fahrzeug fahrzeug) throws IllegalArgumentException {
		this.setRoutenNummer(routenNummer);
		this.routenNummer = routenNummer;
		this.fahrzeug = fahrzeug;
		this.fahrzeug.setRoute(this);
	}

	/**
	 * Routennummer der Route
	 * 
	 * @return routenNummer
	 */
	public int getRoutenNummer() {
		return routenNummer;
	}

	/**
	 * Routennummer der Route aendern/setzen, nur wenn die neue routenNummer nicht
	 * in der Liste ist
	 * 
	 * @param routenNummer zu setzen
	 */
	public void setRoutenNummer(int routenNummer) throws IllegalArgumentException {
		if (RoutenNummern.contains(routenNummer)) {
			throw new IllegalArgumentException();
		}
		RoutenNummern.remove(this.routenNummer);
		RoutenNummern.add(routenNummer);
		this.routenNummer = routenNummer;
	}

	/**
	 * Fahrzeug zugeordnet zu dieser Route
	 * 
	 * @return zugeordnete fahrzeug
	 */
	public Fahrzeug getFahrzeug() {
		return fahrzeug;
	}

	/**
	 * Bestellungen der Route
	 * 
	 * @return
	 */
	public List<Bestellung> getBestellungen() {
		return bestellungen;
	}

	/**
	 * Bestellungen der Route setzen
	 * 
	 * @param bestellungen
	 */
	public void setBestellungen(List<Bestellung> bestellungen) {
		this.bestellungen = bestellungen;
	}
}

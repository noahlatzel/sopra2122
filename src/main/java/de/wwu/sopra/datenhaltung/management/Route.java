/**
 * 
 */
package de.wwu.sopra.datenhaltung.management;

import java.io.Serializable;
import java.util.List;

import de.wwu.sopra.datenhaltung.bestellung.BestellStatus;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;

/**
 * Erstellung der Route-Klasse
 * 
 * @author valeria
 */
public class Route implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int routenNummer;
	private Fahrzeug fahrzeug;
	private List<Bestellung> bestellungen;

	/**
	 * Neues Route-Objekt erstellen nur wenn angegebene routenNummer nicht auf der
	 * Liste existiert
	 * 
	 * @param fahrzeug fahrzeug
	 * @throws IllegalArgumentException IllegalArgumentException
	 */
	public Route(Fahrzeug fahrzeug) throws IllegalArgumentException {
		this.setRoutenNummer(FahrzeugRegister.getZaehlerRoute());
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
		for (Fahrzeug f : FahrzeugRegister.getFahrzeuge()) {
			if (f.getRoute() != null && f.getRoute().getRoutenNummer() == routenNummer) {
				throw new IllegalArgumentException();
			}
		}
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
	 * @return die Lsite der Bestellungen
	 */
	public List<Bestellung> getBestellungen() {
		return bestellungen;
	}

	/**
	 * Bestellungen der Route setzen
	 * 
	 * @param bestellungen bestellungen
	 */
	public void setBestellungen(List<Bestellung> bestellungen) {
		for (Bestellung b : bestellungen) {
			b.setStatus(BestellStatus.IN_BEARBEITUNG);
		}
		this.bestellungen = bestellungen;
		for (Bestellung b : bestellungen) {
			b.setStatus(BestellStatus.IN_BEARBEITUNG);
		}
	}
}

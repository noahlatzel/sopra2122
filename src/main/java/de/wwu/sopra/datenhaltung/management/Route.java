/**
 * 
 */
package de.wwu.sopra.datenhaltung.management;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.datenhaltung.bestellung.BestellStatus;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;

/**
 * Erstellung der Route-Klasse
 * 
 * @author Valeria Vassallo
 */
public class Route implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int routenNummer;
	private Fahrzeug fahrzeug;
	private List<Bestellung> bestellungen = new ArrayList<Bestellung>();

	/**
	 * Neues Route-Objekt erstellen nur wenn angegebene routenNummer nicht auf der
	 * Liste existiert
	 * 
	 * @param fahrzeug Fahrzeug
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
	 * @param routenNummer Neue Routennummer
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
	 * Gibt das Fahrzeug zurueck, dass zu dieser Route zugeordnet ist
	 * 
	 * @return das zugeordnete Fahrzeug
	 */
	public Fahrzeug getFahrzeug() {
		return fahrzeug;
	}

	/**
	 * Bestellungen der Route
	 * 
	 * @return die Liste der Bestellungen
	 */
	public List<Bestellung> getBestellungen() {
		return bestellungen;
	}

	/**
	 * Bestellungen der Route setzen
	 * 
	 * @param bestellungen Bestellungen
	 * @pre Die Liste der Bestellungen darf nicht leer sein
	 * @post Die Bestellungen muessen IN_BEARBEITUNG sein. Die Bestellung darf nicht
	 *       zu gross fuer das Fahrzeug sein.
	 */
	public void setBestellungen(List<Bestellung> bestellungen) {
		// Vorbedingung pruefen
		assert !bestellungen.isEmpty()
				: "Vorbedingung von setBestellungen() verletzt: die Liste der Bestellungen ist leer";

		this.bestellungen = bestellungen;
		for (Bestellung b : bestellungen) {
			b.setStatus(BestellStatus.IN_BEARBEITUNG);
		}

		// Nachbedingung pruefen
		int routeBelegung = 0;
		for (Bestellung bestellung : bestellungen) {
			routeBelegung += bestellung.getKapazitaet();
			// Nachbedingung pruefen
			assert bestellung.getStatus().equals(BestellStatus.IN_BEARBEITUNG)
					: "Nachbedingung von setRoute() verletzt: die Bestellungen der Route sind nicht zu IN_BEARBEITUNG geaendert";
		}
		assert this.getFahrzeug().getKapazitaet() - routeBelegung > 0
				: "Nachbedingung von setRoute() verletzt: die freie Kapazitaet ist nicht positiv, da die Bestellungen auf der Route zu viel Platz belegen";

	}
}

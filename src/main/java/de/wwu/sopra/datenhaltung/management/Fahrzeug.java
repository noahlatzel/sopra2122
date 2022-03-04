/**
 * 
 */
package de.wwu.sopra.datenhaltung.management;

import java.io.Serializable;

import de.wwu.sopra.datenhaltung.benutzer.Fahrer;
import de.wwu.sopra.datenhaltung.bestellung.BestellStatus;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;

/**
 * Erstellung der Fahrzeug-Klasse
 * 
 * @author Valeria Vassallo
 */
public class Fahrzeug implements Serializable {
	/**
	 * SerialisierungsID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Fahrzeugnummer fuer das Fahrzeug
	 */
	private int fahrzeugNummer;
	/**
	 * Kapazitaet des Fahrzeugs
	 */
	private float kapazitaet;
	/**
	 * Status des Fahrzeugs
	 */
	private FahrzeugStatus status;
	/**
	 * Route des Fahrzeugs (kann null sein)
	 */
	private Route route;
	/**
	 * Fahrer des Fahrzeugs (kann null sein)
	 */
	private Fahrer fahrer;

	/**
	 * Neues Route-Objekt erstellen nur wenn angegebene fahrzeugNummer nicht auf der
	 * Liste existiert
	 * 
	 * @param kapazitaet Kapazitaet
	 * @throws IllegalArgumentException IllegalArgumentException
	 * @inv Die freie Kapazitaet des Fahrzeugs muss positiv sein
	 */
	public Fahrzeug(float kapazitaet) throws IllegalArgumentException {
		this.setFahrzeugNummer(FahrzeugRegister.getZaehler());
		this.kapazitaet = kapazitaet;
		this.status = FahrzeugStatus.FREI;

		// Klasseninvariante pruefen
		assert this.kapazitaet > 0 : "Klasseninvariante des Fahrzeugs verletzt: die freie Kapazitaet ist nicht positiv";
	}

	/**
	 * Fahrzeugnummer der Fahrzeug
	 * 
	 * @return fahrzeugNummer Fahrzeugnummer des Fahrzeugs
	 */
	public int getFahrzeugNummer() {
		return fahrzeugNummer;
	}

	/**
	 * Fahrzeugnummer der Fahrzeug aendern/setzen, nur wenn die neue fahrzeugNummer
	 * nicht in der Liste ist
	 * 
	 * @param fahrzeugNummer Fahrzeugnummer des Fahrzeugs
	 */
	public void setFahrzeugNummer(int fahrzeugNummer) throws IllegalArgumentException {
		for (Fahrzeug f : FahrzeugRegister.getFahrzeuge()) {
			if (f.getFahrzeugNummer() == fahrzeugNummer) {
				throw new IllegalArgumentException();
			}
		}
		this.fahrzeugNummer = fahrzeugNummer;
	}

	/**
	 * Kapazitaet der Fahrzeug
	 * 
	 * @return kapazitaet
	 */
	public float getKapazitaet() {
		return kapazitaet;
	}

	/**
	 * Kapazitaet der Fahrzeug aendern/setzen
	 * 
	 * @param kapazitaet zu setzen
	 */
	public void setKapazitaet(float kapazitaet) {
		this.kapazitaet = kapazitaet;

		// Klasseninvariante pruefen
		assert this.kapazitaet > 0 : "Klasseninvariante des Fahrzeugs verletzt: die freie Kapazitaet ist nicht positiv";
	}

	/**
	 * Status der Fahrzeug
	 * 
	 * @return status
	 */
	public FahrzeugStatus getStatus() {
		return status;
	}

	/**
	 * Status der Fahrzeug aendern
	 * 
	 * @param status zu setzen
	 */
	public void setStatus(FahrzeugStatus status) {
		this.status = status;
	}

	/**
	 * Route der Fahrzeug
	 * 
	 * @return zugeordnete route
	 */
	public Route getRoute() {
		return route;
	}

	/**
	 * Route der Fahrzeug setzen "setRoute" wird beim Erstellen eines Route-Objekts
	 * aufgerufen. Eine neue Route wird nur gesetzt, wenn das Fahrzeug FREI ist und
	 * anschliessend wird der Status auf BELEGT geaendert.
	 * 
	 * @param route Route
	 * @post Die Bestellungen der Route sind nun IN_BEARBEITUNG und das Fahrzeug ist
	 *       BELEGT
	 */
	public void setRoute(Route route) {
		if (this.getStatus().equals(FahrzeugStatus.FREI)) {
			this.route = route;
			this.setStatus(FahrzeugStatus.BELEGT);
		}

		// Klasseninvariante pruefen
		int routeBelegung = 0;
		for (Bestellung bestellung : route.getBestellungen()) {
			routeBelegung += bestellung.getKapazitaet();
			// Nachbedingung pruefen
			assert bestellung.getStatus().equals(BestellStatus.IN_BEARBEITUNG)
					: "Nachbedingung von setRoute() verletzt: die Bestellungen der Route sind nicht zu IN_BEARBEITUNG geaendert";
		}
		assert this.kapazitaet - routeBelegung > 0
				: "Klasseninvariante des Fahrzeugs verletzt: die freie Kapazitaet ist nicht positiv, da die Bestellungen auf der Route zu viel Platz belegen";

		// Nachbedingung pruefen
		assert this.getRoute().getFahrzeug().equals(this)
				: "Nachbedingung von setRoute() verletzt: die Route ist nicht dem Fahrzeug zugeordnet";
	}

	/**
	 * Setzt die Route wieder auf null und setzt das fahrzeug auf FREI
	 */
	public void entferneRoute() {
		this.route = null;
		this.setStatus(FahrzeugStatus.FREI);
	}

	/**
	 * zugeordneter Fahrer
	 * 
	 * @return fahrer
	 */
	public Fahrer getFahrer() {
		return fahrer;
	}

	/**
	 * Fahrer der Fahrzeugs wird gesetzt. Der Status des Fahrzeugs wird dann auf
	 * IN_ZUSTELLUNG geaendert und der Status der Bestellungen auf der Route des
	 * Fahrzeugs wird auf IN_ZUSTELLUNG geaendert.
	 * 
	 * @param fahrer Der Fahrer, der das Fahrzeug faehrt.
	 * @post Die Bestellungen der Route sind auf IN_ZUSTELLUNG gestellt. Der Fahrer
	 *       ist dem Fahrzeug zugeordnet.
	 */
	public void setFahrer(Fahrer fahrer) {
		if (fahrer == null) {
			this.fahrer = null;
		} else {
			this.fahrer = fahrer;
			fahrer.setFahrzeug(this);
			this.setStatus(FahrzeugStatus.IN_ZUSTELLUNG);

			for (Bestellung bestellung : this.getRoute().getBestellungen()) {
				bestellung.setStatus(BestellStatus.IN_ZUSTELLUNG);
			}
		}

		// Nachbedingung pruefen
		if (this.getRoute() != null) {
			for (Bestellung bestellung : this.getRoute().getBestellungen()) {
				assert bestellung.getStatus().equals(BestellStatus.IN_ZUSTELLUNG)
						: "Nachbedingung von setFahrer() verletzt: die Bestellung sind nicht auf IN_ZUSTELLUNG gestellt";
			}
		}
		if (fahrer != null) {
			assert fahrer.getFahrzeug().equals(this)
					: "Nachbedingung von setFahrer() verletzt: das Fahrzeug ist nicht dem Fahrer zugeordnet";
		}
		if (this.getFahrer() != null) {
			assert this.getFahrer().equals(fahrer)
					: "Nachbedingung von setFahrer() verletzt: der Fahrer ist nicht dem Fahrzeug zugeordnet";
		}
	}

	/**
	 * Implementiert die toString() Methode, die die Fahrzeugnummer zurueckgibt.
	 * 
	 * @return Fahrzeugnummer
	 */
	@Override
	public String toString() {
		return "" + this.fahrzeugNummer;
	}

}

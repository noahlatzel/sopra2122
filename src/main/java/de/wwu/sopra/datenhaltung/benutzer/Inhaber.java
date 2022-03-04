package de.wwu.sopra.datenhaltung.benutzer;

import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.datenhaltung.verwaltung.BenutzerDatenTripel;
import de.wwu.sopra.datenhaltung.verwaltung.BenutzerRegister;

/**
 * Implementiert die Klasse Inhaber.
 * 
 * @author Paul Dirksen
 *
 */
public class Inhaber extends Benutzer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Rolle rolle = Rolle.INHABER;
	private List<Fahrer> fahrer = new ArrayList<Fahrer>();
	private List<Lagerist> lageristen = new ArrayList<Lagerist>();

	/**
	 * Erstellt einen neuen Inhaber mit den uebergebenen Eigenschaften.
	 * 
	 * @param benutzername   Benutzername
	 * @param passwort       Passwort
	 * @param email          E-Mail
	 * @param adresse        Adresse
	 * @param vorname        Vorname
	 * @param name           Name
	 * @param bankverbindung Bankverbindung
	 * @post Alle Mitarbeiter aus dem BenutzerRegister sind in den lokalen Listen
	 *       des Inhabers gefuehrt
	 */
	public Inhaber(String benutzername, String passwort, String email, String adresse, String vorname, String name,
			String bankverbindung) {
		super(benutzername, passwort, email, adresse, vorname, name, bankverbindung);
		for (BenutzerDatenTripel benutzerDatenTripel : BenutzerRegister.getBenutzerListe()) {
			if (benutzerDatenTripel.getBenutzer().getRolle() == Rolle.FAHRER) {
				fahrer.add((Fahrer) benutzerDatenTripel.getBenutzer());
			} else if (benutzerDatenTripel.getBenutzer().getRolle() == Rolle.LAGERIST) {
				lageristen.add((Lagerist) benutzerDatenTripel.getBenutzer());
			}
		}

		// Nachbedingung pruefen
		for (BenutzerDatenTripel benutzerDatenTripel : BenutzerRegister.getBenutzerListe()) {
			if (benutzerDatenTripel.getBenutzer().getRolle() == Rolle.FAHRER) {
				assert fahrer.contains(benutzerDatenTripel.getBenutzer())
						: "Nachbedingung des Konstruktors von Inhaber verletzt: nicht alle Fahrer sind auch in der Fahrerliste des Inhabers gespeichert";
			} else if (benutzerDatenTripel.getBenutzer().getRolle() == Rolle.LAGERIST) {
				assert lageristen.contains(benutzerDatenTripel.getBenutzer())
						: "Nachbedingung des Konstruktors von Inhaber verletzt: nicht alle Lageristen sind auch in der Fahrerliste des Inhabers gespeichert";
			}
		}
	}

	/**
	 * Fuegt neuen Fahrer der Liste der Fahrer hinzu
	 * 
	 * @param fahrer neuer Fahrer
	 * @throws NullPointerException Der uebergebene Fahrer ist null
	 * @post Der uebergebene Fahrer hat diesen Inhaber als Chef und ist im
	 *       BenutzerRegister und in der Fahrerliste eds Inhabers gefuehrt
	 */
	public void fahrerHinzufuegen(Fahrer fahrer) throws NullPointerException {
		if (fahrer == null)
			throw new NullPointerException();
		if (!this.fahrer.contains(fahrer)) {
			this.fahrer.add(fahrer);
			fahrer.setChef(this);
		}

		// Nachbedingung pruefen
		assert fahrer.getChef().equals(this)
				: "Nachbedingung von fahrerHinzufuegen() verletzt: der Chef des Fahrers ist nicht dieser Inhaber";
		assert this.fahrer.contains(fahrer)
				: "Nachbedingung von fahrerHinzufuegen() verletzt: der Fahrer ist nicht in der Fahrerliste des Inhabers gefuehrt";
		// assert BenutzerRegister
	}

	/**
	 * Entfernt Fahrer aus der Liste der Fahrer
	 * 
	 * @param fahrer Fahrer
	 * @throws NullPointerException Der uebergebene Fahrer ist null
	 * @pre Der uebergebene Fahrer hat diesen Inhaber als Chef
	 * @post Der Chef des uebergebenen Fahrers ist null. Der uebergebene Fahrer ist
	 *       nicht mehr in der Fahrerliste des Inhabers
	 */
	public void fahrerEntfernen(Fahrer fahrer) throws NullPointerException {
		if (fahrer == null)
			throw new NullPointerException();

		if (fahrer.getChef() == null) {
			throw new IllegalArgumentException("Dieser Fahrer hat keinen Chef.");
		}

		if (!this.fahrer.contains(fahrer)) {
			throw new IllegalArgumentException("Dieser Fahrer ist nicht beim Inhaber beschaeftigt.");
		}
		// Vorbedingung pruefen
		assert fahrer.getChef().equals(this)
				: "Vorbedingung von fahrerEntfernen() verletzt: der Fahrer hat diesen Inhaber nicht als Chef";

		if (this.fahrer.contains(fahrer)) {
			this.fahrer.remove(fahrer);
			fahrer.setChef(null);
		}

		// Nachbedingung pruefen
		assert !this.fahrer.contains(fahrer)
				: "Nachbedingung von fahrerEntfernen() verletzt: Der uebergebene Fahrer ist noch in der Fahrerliste des Inhabers gefuehrt";
	}

	/**
	 * Fuegt neuen Lagerist der Liste der Lageristen hinzu
	 * 
	 * @param lagerist lagerist
	 * @throws NullPointerException Der uebergebene Lagerist ist null
	 * @post Der uebergebene Lagerist hat diesen Inhaber als Chef und ist im
	 *       BenutzerRegister und in der Fahrerliste eds Inhabers gefuehrt
	 */
	public void lageristHinzufuegen(Lagerist lagerist) throws NullPointerException {
		if (lagerist == null)
			throw new NullPointerException();
		if (!this.lageristen.contains(lagerist)) {
			this.lageristen.add(lagerist);
			lagerist.setChef(this);
		}

		// Nachbedingung pruefen
		assert lagerist.getChef().equals(this)
				: "Nachbedingung von lageristHinzufuegen() verletzt: der Chef des Lageristen ist nicht dieser Inhaber";
		assert this.lageristen.contains(lagerist)
				: "Nachbedingung von lageristHinzufuegen() verletzt: der Lagerist ist nicht in der Lageristenliste des Inhabers gefuehrt";
		// assert BenutzerRegister
	}

	/**
	 * Entfernt Lagerist aus der Liste der Lageristen
	 * 
	 * @param lagerist Lagerist
	 * @throws NullPointerException Der uebergebene Lagerist ist null
	 */
	public void lageristEntfernen(Lagerist lagerist) throws NullPointerException {
		if (lagerist == null)
			throw new NullPointerException();

		if (lagerist.getChef() == null) {
			throw new IllegalArgumentException("Dieser Lagerist hat keinen Chef.");
		}

		if (!this.lageristen.contains(lagerist)) {
			throw new IllegalArgumentException("Dieser Lagerist ist nicht beim Inhaber beschaeftigt.");
		}
		// Vorbedingung pruefen
		assert lagerist.getChef().equals(this)
				: "Vorbedingung von lageristEntfernen() verletzt: der Lagerist hat diesen Inhaber nicht als Chef";

		if (this.lageristen.contains(lagerist)) {
			this.lageristen.remove(lagerist);
			lagerist.setChef(null);
		}

		assert !this.lageristen.contains(lagerist)
				: "Nachbedingung von lageristEntfernen() verletzt: Der uebergebene Lagerist ist noch in der Lageristenliste des Inhabers gefuehrt";

	}

	/**
	 * Gibt eine Liste der Fahrer des Inhabers zurueck.
	 * 
	 * @return liste von Fahrern
	 */
	public List<Fahrer> getFahrer() {
		return fahrer;
	}

	/**
	 * Gibt eine Liste der Lageristen des Inhabers zurueck.
	 * 
	 * @return Liste von Lageristen
	 */
	public List<Lagerist> getLageristen() {
		return lageristen;
	}

	/**
	 * Die Rolle des Inhabers wird zurueckgegeben.
	 * 
	 * @return rolle Inhaber
	 */
	public Rolle getRolle() {
		return rolle;
	}

}

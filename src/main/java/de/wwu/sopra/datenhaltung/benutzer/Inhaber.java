package de.wwu.sopra.datenhaltung.benutzer;

import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.datenhaltung.verwaltung.BenutzerRegister;

/**
 * Implementiert die Klasse Inhaber.
 * 
 * @author Paul Dirksen
 *
 */
public class Inhaber extends Benutzer {

	/**
	 * SerialsierungsID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Rolle des Inhabers
	 */
	private final Rolle rolle = Rolle.INHABER;
	/**
	 * Liste der angestellten Fahrer
	 */
	private List<Fahrer> fahrer = new ArrayList<Fahrer>();
	/**
	 * Liste der angestellten Lageristen
	 */
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

		for (Benutzer i : BenutzerRegister.getBenutzerListe()) {
			if (i.getRolle() == Rolle.FAHRER) {
				fahrer.add((Fahrer) i);
			} else if (i.getRolle() == Rolle.LAGERIST) {
				lageristen.add((Lagerist) i);
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

		// Vorbedingung pruefen
		assert fahrer.getChef().equals(this)
				: "Vorbedingung von fahrerEntfernen() verletzt: der Fahrer hat diesen Inhaber nicht als Chef";

		if (this.fahrer.contains(fahrer)) {
			this.fahrer.remove(fahrer);
			fahrer.setChef(null);
		}

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

		// Vorbedingung pruefen
		assert lagerist.getChef().equals(this)
				: "Vorbedingung von lageristEntfernen() verletzt: der Lagerist hat diesen Inhaber nicht als Chef";

		if (this.lageristen.contains(lagerist)) {
			this.lageristen.remove(lagerist);
			lagerist.setChef(null);
		}
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

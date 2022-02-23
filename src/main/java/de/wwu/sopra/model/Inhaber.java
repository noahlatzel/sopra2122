package de.wwu.sopra.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementiert die Klasse Inhaber.
 * @author Paul Dirksen
 *
 */
public class Inhaber extends Benutzer {

	private final Rolle rolle = Rolle.INHABER;
	private List<Fahrer> fahrer = new ArrayList<Fahrer>();
	private List<Lagerist> lageristen = new ArrayList<Lagerist>();
	
	/**
	 * Erstellt einen neuen Inhaber mit den uebergebenen Eigenschaften.
	 * @param benutzername
	 * @param passwort
	 * @param email
	 * @param adresse
	 * @param vorname
	 * @param name
	 * @param bankverbindung
	 */
	public Inhaber(String benutzername, String passwort, String email, String adresse, String vorname, String name,
			String bankverbindung) {
		super(benutzername, passwort, email, adresse, vorname, name, bankverbindung);
	}

	/**
	 * Fuegt neuen Fahrer der Liste der Fahrer hinzu
	 * @param fahrer
	 */
	public void fahrerHinzufuegen(Fahrer fahrer) {
		if(!this.fahrer.contains(fahrer)) {
			this.fahrer.add(fahrer);
			fahrer.setChef(this);
		}
	}
	
	/**
	 * Entfernt Fahrer aus der Liste der Fahrer
	 * @param fahrer
	 */
	public void fahrerEntfernen(Fahrer fahrer) {
		if(this.fahrer.contains(fahrer)) {
			this.fahrer.remove(fahrer);
			fahrer.setChef(null);
		}
	}
	
	/**
	 * Fuegt neuen Lagerist der Liste der Lageristen hinzu
	 * @param lagerist
	 */
	public void lageristHinzufuegen(Lagerist lagerist) {
		if(!this.lageristen.contains(lagerist)) {
			this.lageristen.add(lagerist);
			lagerist.setChef(this);
		}
	}
	
	/**
	 * Entfernt Lagerist aus der Liste der Lageristen
	 * @param lagerist
	 */
	public void lageristEntfernen(Lagerist lagerist) {
		if(this.lageristen.contains(lagerist)) {
			this.lageristen.remove(lagerist);
			lagerist.setChef(null);
		}
	}
	
	public List<Fahrer> getFahrer() {
		return fahrer;
	}

	public List<Lagerist> getLageristen() {
		return lageristen;
	}

	public Rolle getRolle() {
		return rolle;
	}
	
}

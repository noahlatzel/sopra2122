package de.wwu.sopra.datenhaltung.benutzer;

/**
 * Diese Klasse Beschreibt den Lageristen. Dieser hat eine Chef und eine Rolle
 * 
 * @author Johannes Thiel
 *
 */
public class Lagerist extends Benutzer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Rolle rolle = Rolle.LAGERIST;
	private Inhaber chef;

	/**
	 * Erstellt einen neuen Lageristen mit den uebergebenen Eigenschaften.
	 * 
	 * @param benutzername
	 * @param passwort
	 * @param email
	 * @param adresse
	 * @param vorname
	 * @param name
	 * @param bankverbindung
	 * @param chef
	 */
	public Lagerist(String benutzername, String passwort, String email, String adresse, String vorname, String name,
			String bankverbindung, Inhaber chef) {
		super(benutzername, passwort, email, adresse, vorname, name, bankverbindung);
		this.setChef(chef);
	}

	// gibt Chef aus
	public Inhaber getChef() {
		return chef;
	}

	// setzt Chef aus
	public void setChef(Inhaber chef) {
		this.chef = chef;
	}

	// gibt rolle aus
	public Rolle getRolle() {
		return rolle;
	}

}

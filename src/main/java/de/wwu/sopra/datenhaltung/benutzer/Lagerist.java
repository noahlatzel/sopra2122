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
	 * @param benutzername   benutzername
	 * @param passwort       passwort
	 * @param email          email
	 * @param adresse        adresse
	 * @param vorname        vorname
	 * @param name           name
	 * @param bankverbindung bankverbindung
	 */
	public Lagerist(String benutzername, String passwort, String email, String adresse, String vorname, String name,
			String bankverbindung, Inhaber chef) {
		super(benutzername, passwort, email, adresse, vorname, name, bankverbindung);
		this.setChef(chef);
	}

	/**
	 * gibt Chef aus
	 * 
	 * @return Inhaber
	 */
	public Inhaber getChef() {
		return chef;
	}

	/**
	 * setzt den Chef
	 * 
	 * @param chef Inhaber
	 */
	public void setChef(Inhaber chef) {
		this.chef = chef;
	}

	/**
	 * gibt die Rolle aus
	 * 
	 * @return rolle.Lagerist
	 */
	public Rolle getRolle() {
		return rolle;
	}

}

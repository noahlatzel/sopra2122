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
	 * @param benutzername   Benutzername
	 * @param passwort       Passwort
	 * @param email          Email
	 * @param adresse        Adresse
	 * @param vorname        Vorname
	 * @param name           Name
	 * @param bankverbindung Bankverbindung
	 * @param chef           Der Chef des Lageristen
	 */
	public Lagerist(String benutzername, String passwort, String email, String adresse, String vorname, String name,
			String bankverbindung, Inhaber chef) {
		super(benutzername, passwort, email, adresse, vorname, name, bankverbindung);
		this.setChef(chef);

	}

	/**
	 * Gibt den Chef des Lageristen zurueck.
	 * 
	 * @return Inhaber
	 */
	public Inhaber getChef() {
		return chef;
	}

	/**
	 * Setzt den Chef fuer den Lageristen.
	 * 
	 * @param chef Inhaber
	 */
	public void setChef(Inhaber chef) {
		this.chef = chef;

	}

	/**
	 * Gibt die Rolle des Lageristen zurueck.
	 * 
	 * @return rolle.Lagerist
	 */
	public Rolle getRolle() {
		return rolle;
	}

}

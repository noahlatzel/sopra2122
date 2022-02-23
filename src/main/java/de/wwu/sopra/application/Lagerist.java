package de.wwu.sopra.application;

public class Lagerist extends Benutzer{

	private final Rolle rolle = Rolle.LAGERIST;
	private Inhaber chef;
	
	/**
	 * Erstellt einen neuen Lageristen mit den uebergebenen Eigenschaften.
	 * @param benutzername
	 * @param passwort
	 * @param email
	 * @param adresse
	 * @param vorname
	 * @param name
	 * @param bankverbindung
	 */
	public Lagerist(String benutzername, String passwort, String email, String adresse, String vorname, String name,
			String bankverbindung) {
		super(benutzername, passwort, email, adresse, vorname, name, bankverbindung);
	}

	public Inhaber getChef() {
		return chef;
	}

	public void setChef(Inhaber chef) {
		this.chef = chef;
	}

	public Rolle getRolle() {
		return rolle;
	}
	
}

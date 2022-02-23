package de.wwu.sopra.application;

import java.util.ArrayList;
import java.util.List;

public class Kunde extends Benutzer {

	private final Rolle rolle = Rolle.KUNDE;
	private List<Bestellung> bestellungen = new ArrayList<Bestellung>();
	private Warenkorb warenkorb;
	
	/**
	 * Erstellt einen neuen Kunden mit den uebergebenen Eigenschaften.
	 * @param benutzername
	 * @param passwort
	 * @param email
	 * @param adresse
	 * @param vorname
	 * @param name
	 * @param bankverbindung
	 */
	public Kunde(String benutzername, String passwort, String email, String adresse, String vorname, String name,
			String bankverbindung) {
		super(benutzername, passwort, email, adresse, vorname, name, bankverbindung);
		
	}

	/**
	 * Fuegt eine neue Bestellung zur Liste der Bestellungen hinzu
	 * @param bestellung
	 */
	public void bestellungHinzufuegen(Bestellung bestellung) {
		if(!this.bestellungen.contains(bestellung)) {
			this.bestellungen.add(bestellung);
		}
	}
	
	
	public List<Bestellung> getBestellungen() {
		return bestellungen;
	}

	public Warenkorb getWarenkorb() {
		return warenkorb;
	}

	public void setWarenkorb(Warenkorb warenkorb) {
		this.warenkorb = warenkorb;
	}
	
	

	
	
}

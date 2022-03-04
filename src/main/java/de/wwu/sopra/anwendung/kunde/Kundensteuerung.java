package de.wwu.sopra.anwendung.kunde;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.bestellung.BestellStatus;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.bestellung.Warenkorb;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Produkt;

/**
 * Die Klasse verwaltet den Zugriff der Kunden auf dessen Bestellungen, die
 * Artikel, die persoenlichen Daten, Rechnungen und den Warenkorb. Dies
 * geschieht ueber die Grenzklassen PersoenlicheDatenAendern,
 * PersoenlicheDatenAnzeigen, Suchen, Bestellen, Rechnung, WarenkorbAnsicht,
 * BestellungVerwalten und BestellungenAnzeigen.
 * 
 * @author Jasmin Horstknepper
 *
 */
public class Kundensteuerung {
	/**
	 * Kunde, der die Kundensteuerung bedient
	 */
	private Kunde kunde;

	/**
	 * Konstruktor der Klasse Kundensteuerung
	 * 
	 * @param kunde Kunde
	 */
	public Kundensteuerung(Kunde kunde) {
		this.kunde = kunde;
	}

	/**
	 * Methode zum Anzeigen der persoenlichen Daten
	 * 
	 * @return Die persoenlichen Daten separiert durch ;
	 */
	public String persoenlicheDatenAnzeigen() {
		String returnstring = kunde.getBenutzername() + ";" + kunde.getPasswort() + ";" + kunde.getEmail() + ";"
				+ kunde.getAdresse() + ";" + kunde.getVorname() + ";" + kunde.getName() + ";"
				+ kunde.getBankverbindung() + ";";
		return returnstring;

	}

	/**
	 * Methode zum aendern der persoenlichen Daten
	 * 
	 * @param benutzername   Benutzername
	 * @param passwort       Passwort
	 * @param email          Email-Adresse
	 * @param adresse        Adresse
	 * @param vorname        Vorname
	 * @param name           Name
	 * @param bankverbindung Bankverbindung
	 */
	public void persoenlicheDatenAendern(String benutzername, String passwort, String email, String adresse,
			String vorname, String name, String bankverbindung) {

		kunde.setBenutzername(benutzername);
		kunde.setPasswort(passwort);
		kunde.setEmail(email);
		kunde.setAdresse(adresse);
		kunde.setVorname(vorname);
		kunde.setName(name);
		kunde.setBankverbindung(bankverbindung);

	}

	/**
	 * Methode zum suchen eines Produktes
	 * 
	 * @param gesuchtesObjekt gesuchtes Produkt
	 * @return Liste mit allen Produkten die der Eingabe entsprechen
	 * @throws NullPointerException Ein Null-Objekt wurde uebergeben.
	 */
	public List<Produkt> suchen(String gesuchtesObjekt) throws NullPointerException {

		if (gesuchtesObjekt == null)
			throw new NullPointerException();
		if (!(Lager.getProduktBestand(gesuchtesObjekt) > 0)) {
			throw new IllegalArgumentException("Das Produkt ist nicht im Sortiment.");
		}
		return Lager.getProdukteAusLager(Lager.getLager(), gesuchtesObjekt);
	}

	/**
	 * Methode zum Abgeben einer Bestellung
	 */
	public void bestellen() {
		List<Produkt> produkte = new ArrayList<Produkt>();

		for (Produkt p : kunde.getWarenkorb().getProdukte()) {
			produkte.add(p);
		}

		Bestellung bestellung = new Bestellung(LocalDateTime.now(), produkte, kunde);
		kunde.bestellungHinzufuegen(bestellung);
		kunde.getWarenkorb().warenkorbLeeren();
	}

	/**
	 * Methode zur Ansicht des Warenkorbs des Kunden
	 * 
	 * @return Gibt den Warenkorb des Kunden zurueck
	 */
	public Warenkorb warenkorbAnsicht() {

		return kunde.getWarenkorb();
	}

	/**
	 * Methode zur Ansicht der Bestellungen des Kunden
	 * 
	 * @return Gibt die Bestellungen des Kunden zurueck.
	 */
	public List<Bestellung> bestellungenAnzeigen() {

		return kunde.getBestellungen();
	}

	/**
	 * Methode zum stornieren seiner Bestellung
	 * 
	 * @param bestellung Bestellung
	 * @throws NullPointerException Ein Null-Objekt wurde uebergeben.
	 */
	public void stornieren(Bestellung bestellung) throws NullPointerException {

		if (bestellung == null)
			throw new NullPointerException();
		if (!(bestellung.getStatus() == BestellStatus.IN_ZUSTELLUNG
				|| bestellung.getStatus() == BestellStatus.ABGESCHLOSSEN)) {

			bestellung.setStatus(BestellStatus.STORNIERT);
			Lager.addProdukte(bestellung.getProdukte());

		} else {

			throw new IllegalArgumentException("Eine Stornierung ist leider nicht mehr moeglich.");

		}
	}

	/**
	 * Methode zum Nachbestellen einer Bestellung
	 * 
	 * @param bestellung Bestellung
	 * @throws NullPointerException Ein Null-Objekt wurde uebergeben.
	 */
	public void nachbestellen(Bestellung bestellung) throws NullPointerException {

		if (bestellung == null)
			throw new NullPointerException();
		for (int i = 0; i < bestellung.getProdukte().size(); i++) {
			if (Lager.getProduktBestand(bestellung.getProdukte().get(i)) < bestellung
					.getProduktAnzahl(bestellung.getProdukte().get(i))) {
				throw new IllegalArgumentException("Eine Nachlieferung  ist leider nicht moeglich.");
			}
		}
		Bestellung nachbestellung = new Bestellung(LocalDateTime.now(), neueProduktListe(bestellung.getProdukte()),
				kunde);
		kunde.bestellungHinzufuegen(nachbestellung);
	}

	/**
	 * Methode zum kopieren einer Liste mit gleichen Produkten aber neuen
	 * Produkt-Objekten
	 * 
	 * @param produkte Produktliste
	 * @return neue kopierte Produktliste
	 */
	public List<Produkt> neueProduktListe(List<Produkt> produkte) {
		List<Produkt> neueProdukte = new ArrayList<Produkt>();
		for (int j = 0; j < produkte.size(); j++) {
			neueProdukte.add(new Produkt(produkte.get(j).getName(), produkte.get(j).getBeschreibung(),
					produkte.get(j).getEinkaufspreis(), produkte.get(j).getVerkaufspreis()));
		}
		return neueProdukte;
	}
}

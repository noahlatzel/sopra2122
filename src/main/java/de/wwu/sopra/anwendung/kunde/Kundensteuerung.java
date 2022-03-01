package de.wwu.sopra.anwendung.kunde;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.bestellung.BestellStatus;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.bestellung.IdZaehler;
import de.wwu.sopra.datenhaltung.bestellung.Warenkorb;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Produkt;

/**
 * Die Klasse verwaltet den Zugriff der Kunden auf dessen Bestellungen, die
 * Artikel, die pers�nlichen Daten, Rechnungen und den Warenkorb. Dies geschieht
 * �ber die Grenzklassen PersoenlicheDatenAendern, PersoenlicheDatenAnzeigen,
 * Suchen, Bestellen, Rechnung, WarenkorbAnsicht, BestellungVerwalten und
 * BestellungenAnzeigen.
 * 
 *
 */
public class Kundensteuerung {

	private Kunde kunde;
	private Lager lager;

	/**
	 * Konstruktor der Klasse Kundensteuerung
	 * 
	 * @param kunde Kunde
	 */
	public Kundensteuerung(Kunde kunde) {
		this.kunde = kunde;
		lager = new Lager();
	}

	/**
	 * Methode zum Anzeigen der persoenlichen Daten
	 * 
	 * @return
	 */
	public String persoenlicheDatenAnzeigen() {
		String returnstring = kunde.getBenutzername() + ";" + kunde.getPasswort() + ";" + kunde.getEmail() + ";"
				+ kunde.getAdresse() + ";" + kunde.getVorname() + ";" + kunde.getName() + ";"
				+ kunde.getBankverbindung() + ";";
		return returnstring;

	}

	/**
	 * Mehode zum aendern der persoenlichen Daten
	 * 
	 * @param benutzername   Benutzername
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
	 * @throws NullPointerException
	 */
	public List<Produkt> suchen(String gesuchtesObjekt) throws NullPointerException {

		if (gesuchtesObjekt == null)
			throw new NullPointerException();
		if (!(lager.getProduktBestand(gesuchtesObjekt) > 0)) {
			throw new IllegalArgumentException("Das Produkt ist nicht im Sortiment.");
		}
		return lager.getProdukteAusLager(lager.getLager(), gesuchtesObjekt);
	}

	/**
	 * Methode zum abgeben einer Bestellung
	 */
	public void bestellen() {
		List<Produkt> produkte = new ArrayList<Produkt>();

		for (Produkt p : kunde.getWarenkorb().getProdukte()) {
			produkte.add(p);
		}

		Bestellung bestellung = new Bestellung(IdZaehler.getBestellungsId(), LocalDateTime.now(), produkte, kunde);
		kunde.bestellungHinzufuegen(bestellung);
		kunde.getWarenkorb().warenkorbLeeren();
	}

	/**
	 * Methode zur Ansicht seines Warenkorbs
	 */
	public Warenkorb warenkorbAnsicht() {

		return kunde.getWarenkorb();
	}

	/**
	 * Methode zur Ansicht seiner Bestellungen
	 */
	public List<Bestellung> bestellungenAnzeigen() {

		return kunde.getBestellungen();
	}

	/**
	 * Methide zum stornieren seiner Bestellung
	 * 
	 * @param bestellung Bestellung
	 * @throws NullPointerException
	 */
	public void stornieren(Bestellung bestellung) throws NullPointerException {

		if (bestellung == null)
			throw new NullPointerException();
		if (!(bestellung.getStatus() == BestellStatus.IN_ZUSTELLUNG
				|| bestellung.getStatus() == BestellStatus.ABGESCHLOSSEN)) {

			bestellung.setStatus(BestellStatus.STORNIERT);
			lager.addProdukte(bestellung.getProdukte());

		} else {

			throw new IllegalArgumentException("Eine Stornierung ist leider nicht mehr moeglich.");

		}
	}

	/**
	 * Methode zum Nachbestellen einer Bestellung
	 * 
	 * @param bestellung Bestellung
	 * @throws NullPointerException
	 */
	public void nachbestellen(Bestellung bestellung) throws NullPointerException {

		if (bestellung == null)
			throw new NullPointerException();
		for (int i = 0; i <= bestellung.getProdukte().size(); i++) {
			if (lager.getProduktBestand(bestellung.getProdukte().get(i)) < bestellung
					.getProduktAnzahl(bestellung.getProdukte().get(i), bestellung.getProdukte())) {
				throw new IllegalArgumentException("Eine Nachlieferung  ist leider nicht moeglich.");
			}
		}
		Bestellung nachbestellung = new Bestellung(0, LocalDateTime.now(), neueProduktListe(bestellung.getProdukte()),
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

package de.wwu.sopra.anwendung.kunde;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.bestellung.BestellStatus;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.bestellung.Rabatt;
import de.wwu.sopra.datenhaltung.bestellung.Warenkorb;
import de.wwu.sopra.datenhaltung.management.Kategorie;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Produkt;
import de.wwu.sopra.datenhaltung.verwaltung.BenutzerRegister;

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
	 * Methode zum Abgeben einer Bestellung mit Rabatt
	 */
	public void bestellen(Rabatt rabatt) {
		List<Produkt> produkte = new ArrayList<Produkt>();

		for (Produkt p : kunde.getWarenkorb().getProdukte()) {
			produkte.add(p);
		}

		Bestellung bestellung = new Bestellung(LocalDateTime.now(), produkte, kunde, rabatt);
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

	/**
	 * Gibt eine Liste der Kategorien zurueck.
	 * 
	 * @return Liste mit allen Kategorien im Sortiment.
	 */
	public HashSet<Kategorie> getKategorien() {
		return Lager.getKategorien();
	}

	/**
	 * Gibt eine Produktliste aus dem Lager zurueck.
	 * 
	 * @return Produktliste aus dem Lager
	 */
	public Set<Produkt> getLager() {
		return Lager.sortimentAnzeigen();
	}

	/**
	 * Gibt den Produktbestand des uebergebenen Produkts zurueck.
	 * 
	 * @param p Produkt dessen Bestand abgefragt wird.
	 * @return Produktbestand des uebergebenen Produktes.
	 */
	public int getProduktBestand(Produkt p) {
		return Lager.getProduktBestand(p);
	}

	/**
	 * Fuegt das uebergebene Produkt dem Warenkorb des eingeloggten Kunden so oft
	 * hinzu, wie die uebergebene Anzahl
	 * 
	 * @param p      Produkt welches zum Warenkorb hinzugefuegt wird.
	 * @param anzahl Wie oft soll das uebergebene Produkt zum Warenkorb hinzugefuegt
	 *               werden.
	 */
	public void produktZuWarenkorbHinzufuegen(Produkt p, int anzahl) {
		List<Produkt> gleicheProdukte = new ArrayList<Produkt>();

		// Erstellt eine Liste der Laenge der uebergebenen Anzahl mit Produkten die
		// gleich dem uebergebenen Produkt sind
		for (Produkt produkt : Lager.getLager()) {
			if (anzahl > 0 && produkt.getName().equals(p.getName())) {
				gleicheProdukte.add(produkt);
				anzahl--;
			}
		}

		while (gleicheProdukte.size() > 0) {
			BenutzerRegister.produktZuWarenkorbHinzufuegen(kunde, gleicheProdukte.get(0));
			gleicheProdukte.remove(0);
		}
	}

	/**
	 * Filtert das uebergebene HashSet nach Produkten der uebergebenen Kategorie
	 * 
	 * @param produkte  Hashset das nach der uebergebenen Kategorie gefiltert wird.
	 * @param kategorie Kategorie nach der das uebergebene Hashset gefiltert wird.
	 * @return Gefiltertes HashSet.
	 */
	public Set<Produkt> filterProdukteNachKategorie(Set<Produkt> produkte, Kategorie kategorie) {
		Set<Produkt> filteredProdukte = new HashSet<Produkt>();

		Set<Kategorie> unterkategorien = kategorie.getUnterkategorien();
		if (unterkategorien != null) {
			Iterator<Kategorie> it = unterkategorien.iterator();
			while (it.hasNext()) {
				Kategorie kategorie2 = it.next();
				filteredProdukte.addAll(filterProdukteNachKategorie(produkte, kategorie2));
			}
		}
		for (Produkt produkt : produkte) {
			if (produkt.getKategorie() != null && produkt.getKategorie().equals(kategorie)) {
				filteredProdukte.add(produkt);
			}
		}

		for (Produkt produkt : getLager()) {
			if (produkt.getKategorie() != null && produkt.getKategorie().equals(kategorie)) {
				filteredProdukte.add(produkt);
			}
		}
		return filteredProdukte;
	}

	/**
	 * Erzeugt einen neuen Rabattcode zwischen 5 und 20 Prozent.
	 */
	public String addRabatt() {
		// Erzeugen eines neuen Codes aus dem Alphabet
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();

		int laenge = 6;

		for (int i = 0; i < laenge; i++) {

			int index = random.nextInt(alphabet.length());

			char randomChar = alphabet.charAt(index);

			sb.append(randomChar);
		}

		String rabattcode = sb.toString();

		// Erzeugt eine zufaellige Prozentzahl zwischen 5 und 20, gerundet auf 5er
		// Schritte
		int prozent = (int) (Math.random() * ((20 - 5) + 1)) + 5;
		prozent = 5 * (Math.round(prozent / 5));

		kunde.addRabatt(rabattcode, prozent);
		return rabattcode;
	}

	/**
	 * Ermittelt, ob der uebergebene Rabattcode ein gueltiger, einloesbarer
	 * Rabattcode ist.
	 * 
	 * @param rabattcode Wird auf Gueltigkeit ueberprueft.
	 * @return Gibt Wahrheitswert zurueck, ob der Rabattcode gueltig ist, true, wenn
	 *         er gueltig ist.
	 */
	public boolean getRabattGueltig(String rabattcode) {
		return kunde.getRabattGueltig(rabattcode);
	}

	/**
	 * Gibt die Prozentzahl des Rabatts zum uebergebenen Rabattcodes zurueck.
	 * 
	 * @param rabattcode Prozentzahl des Rabatts wird zu diesem Rabattcode
	 *                   ermittelt.
	 * @return Prozentzahl des Rabatts zum uebergebenen Rabattcodes zurueck
	 */
	public int getRabattProzent(String rabattcode) {
		return kunde.getRabattProzent(rabattcode);
	}

	/**
	 * Entfernt den Rabatt aus den einloesbaren Rabatten.
	 * 
	 * @param rabattcode Einzuloesender Rabattcode.
	 * @return Gibt einzuloesenden Rabatts zurueck.
	 */
	public Rabatt rabattEinloesen(String rabattcode) {
		return kunde.rabattEinloesen(rabattcode);
	}

	/**
	 * Gibt Liste der Rabatte des Kunden zurueck.
	 * 
	 * @return Liste der Rabatte des Kunden.
	 */
	public List<Rabatt> getRabatte() {
		return kunde.getRabatte();
	}
}

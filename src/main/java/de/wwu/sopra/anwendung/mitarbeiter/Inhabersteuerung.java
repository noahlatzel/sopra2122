package de.wwu.sopra.anwendung.mitarbeiter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import de.wwu.sopra.datenhaltung.benutzer.Benutzer;
import de.wwu.sopra.datenhaltung.benutzer.Fahrer;
import de.wwu.sopra.datenhaltung.benutzer.Inhaber;
import de.wwu.sopra.datenhaltung.benutzer.Lagerist;
import de.wwu.sopra.datenhaltung.benutzer.Rolle;
import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import de.wwu.sopra.datenhaltung.management.Kategorie;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Produkt;
import de.wwu.sopra.datenhaltung.management.Statistiken;
import de.wwu.sopra.datenhaltung.verwaltung.BenutzerRegister;
import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;

/**
 * Steuert die Aufgaben des Inhabers
 * 
 * @author Valeria Vassallo
 *
 */
public class Inhabersteuerung {
	/**
	 * Inhaber, der die Inhabersteuerung bedient
	 */
	private Inhaber inhaber;

	/**
	 * Die Inhabersteuerung zur Verbindung von GUI und Grenzklassen
	 * 
	 * 
	 * @param inhaber Der Inhaber, der eingeloggt ist.
	 * 
	 */
	public Inhabersteuerung(Inhaber inhaber) {
		this.inhaber = inhaber;
	}

	/**
	 * Ueberprueft, ob alle Eingaben gueltig sind - non null oder leer oder groesser
	 * als null wenn nummer
	 * 
	 * @param inputList Liste von Eingaben zu ueberpruefen
	 * @return result Boolean, true wenn gueltige Eingaben, false wenn nicht
	 */
	private boolean gueltigeEingaben(List<Object> inputList) {
		boolean result = true;

		for (Object input : inputList) {
			if (input instanceof String) {
				if (input == null || input == "")
					result = false;
			}
		}

		return result;
	}

	/**
	 * Moeglichkeit ein Produkt zu bearbeiten
	 * 
	 * @param produkt       Produkt, der bearbeitet wird
	 * @param name          Name des Produkts, kann nicht null oder leer sein
	 * @param beschreibung  Beschreibung des Produkts, kann nicht null oder leer
	 *                      sein
	 * @param verkaufspreis Verkaufspreis des Produkts, kann nicht negativ sein
	 * @throws IllegalArgumentException Die Eingaben sind ungueltig.
	 */
	public void produktBearbeiten(Produkt produkt, String name, String beschreibung, double verkaufspreis)
			throws IllegalArgumentException {
		if (!gueltigeEingaben(Arrays.asList(name, beschreibung, verkaufspreis)))
			throw new IllegalArgumentException();

		produkt.setName(name);
		produkt.setBeschreibung(beschreibung);
		produkt.setVerkaufspreis(verkaufspreis);
	}

	/**
	 * Eine Moeglichkeit Produkte hinzuzufuegen oder loeschen
	 * 
	 * @param produkte Produkte, die entweder hinzugefuegt oder geloescht werde
	 * @param action   String, entweder hinzufuegen oder loeschen, wenn nicht eine
	 *                 davon, new IllegalArgumentException
	 * @throws IllegalArgumentException Die Eingaben sind ungueltig.
	 */
	public void lagerVerwalten(Collection<Produkt> produkte, String action) throws IllegalArgumentException {
		if (action == "hinzufuegen") {
			for (Produkt produkt : produkte) {
				Lager.produktZumSortimentHinzufuegen(produkt);
			}
		} else if (action == "loeschen") {
			for (Produkt produkt : produkte) {
				Lager.produktAusDemSortimentEntfernen(produkt);
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Eine Moeglichkeit sich Produkte in Lager anzeigen zu lassen
	 * 
	 * @return lagerProdukte HashSet mit allen Produkten im Lager
	 */
	public HashSet<Produkt> sortimentAnzeigen() {
		return Lager.getLager();
	}

	/**
	 * Funktion zum Bearbeiten der in einer Kategorie enthaltenen Produkte
	 * 
	 * @param kategorie Kategorie, die bearbeitet wird
	 * @param produkte  Produkte, die zu der Kategorie hinzugefuegt oder geloescht
	 *                  werden
	 * @param action    String, entweder hinzufuegen oder loeschen, wenn nicht eine
	 *                  davon, new IllegalArgumentException
	 * @throws IllegalArgumentException Die Eingaben sind ungueltig.
	 */
	public void kategorieProdukteVerwalten(Kategorie kategorie, Collection<Produkt> produkte, String action)
			throws IllegalArgumentException {
		if (action == "hinzufuegen") {
			kategorie.addProdukte(produkte);
			for (Produkt i : produkte) {
				i.setKategorie(kategorie);
			}
		} else if (action == "loeschen") {
			kategorie.removeProdukte(produkte);
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Funktion zum Bearbeiten der Kategorie durch Aendern des Namens oder Aendern
	 * der Ober- oder Unterkategorie
	 * 
	 * @param kategorie1 Kategorie, die bearbeitet wird
	 * @param kategorie2 Neue Ober- oder Unterkategorie, kann null sein
	 * @param aenderung  String, entweder ober oder unter. Wenn "ober", kategorie2
	 *                   ist neue Oberkategorie, wenn "unter", Unterkategorie, kann
	 *                   null sein
	 * @param name       Neuer Name der Kategorie, kann null sein
	 * @throws IllegalArgumentException Die Eingaben sind ungueltig.
	 */
	public void kategorieBearbeiten(Kategorie kategorie1, Kategorie kategorie2, String aenderung, String name)
			throws IllegalArgumentException {
		if (kategorie1 == null || kategorie2 == null)
			throw new IllegalArgumentException();
		if (name != null) {
			kategorie1.setName(name);
		}

		if (aenderung == "ober") {
			kategorie1.setOberkategorie(kategorie2);
		} else if (aenderung == "unter") {
			kategorie1.addUnterkategorie(kategorie2);
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Funktion zum Registrieren neuer Mitarbeitern
	 * 
	 * @param benutzername   Benutzername des neuen Mitarbeiters
	 * @param passwort       Passwort des neuen Mitarbeiters
	 * @param email          Email des neuen Mitarbeiters
	 * @param adresse        Adresse des neuen Mitarbeiters
	 * @param vorname        Vorname des neuen Mitarbeiters
	 * @param nachname       Nachname des neuen Mitarbeiters
	 * @param bankverbindung Bankverbindung des neuen Mitarbeiters
	 * @param rolle          Rolle des neuen Mitarbeiters
	 * @throws IllegalArgumentException Die Eingaben sind ungueltig.
	 */
	public void mitarbeiterRegistrieren(String benutzername, String passwort, String email, String adresse,
			String vorname, String nachname, String bankverbindung, Rolle rolle) throws IllegalArgumentException {
		if (!gueltigeEingaben(Arrays.asList(benutzername, passwort, email, adresse, vorname, nachname, bankverbindung)))
			throw new IllegalArgumentException();

		if (rolle == Rolle.FAHRER) {
			Fahrer fahrer = new Fahrer(benutzername, passwort, email, adresse, vorname, nachname, bankverbindung,
					this.inhaber);
			this.inhaber.fahrerHinzufuegen(fahrer);
			BenutzerRegister.benutzerHinzufuegen(fahrer);
		} else if (rolle == Rolle.LAGERIST) {
			Lagerist lagerist = new Lagerist(benutzername, passwort, email, adresse, vorname, nachname, bankverbindung,
					this.inhaber);
			this.inhaber.lageristHinzufuegen(lagerist);
			BenutzerRegister.benutzerHinzufuegen(lagerist);
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Funktion zum Loeschen eines Mitarbeiters
	 * 
	 * @param mitarbeiter Mitarbeiter, der geloescht wird
	 * @throws NullPointerException Der zu loeschende Mitarbeiter ist null.
	 */
	public void mitarbeiterLoeschen(Benutzer mitarbeiter) throws NullPointerException {
		if (mitarbeiter == null)
			throw new NullPointerException();
		if (mitarbeiter.getRolle() == Rolle.FAHRER) {
			this.inhaber.fahrerEntfernen((Fahrer) mitarbeiter);
		} else if (mitarbeiter.getRolle() == Rolle.LAGERIST) {
			this.inhaber.lageristEntfernen((Lagerist) mitarbeiter);
		}
		BenutzerRegister.benutzerEntfernen(mitarbeiter);
	}

	/**
	 * 
	 * @param mitarbeiter    Mitarbeiter, der bearbeitet wird
	 * @param benutzername   Benutzername des Mitarbeiters
	 * @param passwort       Passwort des Mitarbeiters
	 * @param email          Email des Mitarbeiters
	 * @param adresse        Adresse des Mitarbeiters
	 * @param vorname        Vorname des Mitarbeiters
	 * @param nachname       Nachname des Mitarbeiters
	 * @param bankverbindung Bankverbindung des Mitarbeiters
	 * @throws IllegalArgumentException Ungueltige Eingabe
	 * @throws NullPointerException     Der zu bearbeitende Mitarbeiter ist null
	 */
	public void mitarbeiterDatenAendern(Benutzer mitarbeiter, String benutzername, String passwort, String email,
			String adresse, String vorname, String nachname, String bankverbindung)
			throws IllegalArgumentException, NullPointerException {
		if (!gueltigeEingaben(Arrays.asList(benutzername, passwort, email, adresse, vorname, nachname, bankverbindung)))
			throw new IllegalArgumentException();
		if (mitarbeiter == null)
			throw new NullPointerException();

		if (!mitarbeiter.getBenutzername().equals(benutzername))
			mitarbeiter.setBenutzername(benutzername);
		if (!mitarbeiter.getPasswort().equals(passwort))
			mitarbeiter.setPasswort(passwort);
		if (!mitarbeiter.getEmail().equals(email))
			mitarbeiter.setEmail(email);
		if (!mitarbeiter.getAdresse().equals(adresse))
			mitarbeiter.setAdresse(adresse);
		if (!mitarbeiter.getVorname().equals(vorname))
			mitarbeiter.setVorname(vorname);
		if (!mitarbeiter.getName().equals(nachname))
			mitarbeiter.setName(nachname);
		if (!mitarbeiter.getBankverbindung().equals(bankverbindung))
			mitarbeiter.setBankverbindung(bankverbindung);
	}

	/**
	 * Rueckgabeliste aller Lageristen und Fahrer
	 * 
	 * @return mitarbeitern Liste von allen Mitarbeitern
	 */
	public List<Benutzer> mitarbeiternAnzeigen() {
		List<Benutzer> mitarbeitern = new ArrayList<Benutzer>();
		mitarbeitern.addAll(inhaber.getFahrer());
		mitarbeitern.addAll(inhaber.getLageristen());
		return mitarbeitern;
	}

	/**
	 * Funktion zum Bearbeiten von Fahrzeugdaten
	 * 
	 * @param fahrzeug       fahrzeug
	 * @param fahrzeugNummer fahrzeugNummer
	 * @param kapazitaet     kapazitaet
	 */
	public void fahrzeugDatenAendern(Fahrzeug fahrzeug, int fahrzeugNummer, float kapazitaet) {
		if (fahrzeug != null) {
			if (fahrzeug.getFahrzeugNummer() != fahrzeugNummer)
				fahrzeug.setFahrzeugNummer(fahrzeugNummer);
			if (fahrzeug.getKapazitaet() != kapazitaet)
				fahrzeug.setKapazitaet(kapazitaet);
		}
	}

	/**
	 * Fuegt ein Fahrzeug dem FahrzeugRegister hinzu.
	 * 
	 * @param kapazitaet Die Kapazitaet des Fahrzeugs, das hinzugefuegt werden soll.
	 */
	public void fahrzeugHinzufuegen(int kapazitaet) {
		FahrzeugRegister.addFahrzeug(new Fahrzeug(kapazitaet));
	}

	/**
	 * Funktion zum Loeschen eines Fahrzeugs
	 * 
	 * @param fahrzeug Fahrzeug, das geloescht wird
	 */
	public void fahrzeugLoeschen(Fahrzeug fahrzeug) {
		FahrzeugRegister.removeFahrzeug(fahrzeug);
	}

	/**
	 * Funktion zum Auflisten aller Fahrzeuge
	 * 
	 * @return fahrzeugeHSet HashSet von allen Fahrzeuge in FahrzeugRegister
	 */
	public HashSet<Fahrzeug> fahrzeugeAnzeigen() {
		// HashSet<Fahrzeug> fahrzeugeHSet = FahrzeugRegister.getFahrzeuge();
		return FahrzeugRegister.getFahrzeuge();// fahrzeugeHSet;
	}

	/**
	 * Funktion zum Anzeigen der persönlichen Daten des Inhabers
	 * 
	 * @return daten als String, alle Daten durch Semikolon getrennt
	 */
	public String persoenlicheDatenAnzeigen() {
		String daten = this.inhaber.getBenutzername() + ";" + this.inhaber.getPasswort() + ";" + this.inhaber.getEmail()
				+ ";" + this.inhaber.getAdresse() + ";" + this.inhaber.getVorname() + ";" + this.inhaber.getName() + ";"
				+ this.inhaber.getBankverbindung();
		return daten;
	}

	/**
	 * Funktion zum Bearbeiten der persoenlichen Daten
	 * 
	 * @param benutzername   Benutzername des Inhabers
	 * @param passwort       Passwort des Inhabers
	 * @param email          Email des Inhabers
	 * @param adresse        Adresse des Inhabers
	 * @param vorname        Vorname des Inhabers
	 * @param nachname       Nachname des Inhabers
	 * @param bankverbindung Bankverbindung des Inhabers
	 * @throws IllegalArgumentException Die Eingabe ist ungueltig
	 */
	public void persoenlicheDatenAendern(String benutzername, String passwort, String email, String adresse,
			String vorname, String nachname, String bankverbindung) throws IllegalArgumentException {
		if (!this.gueltigeEingaben(
				Arrays.asList(benutzername, passwort, email, adresse, vorname, nachname, bankverbindung)))
			throw new IllegalArgumentException();

		if (!this.inhaber.getBenutzername().equals(benutzername))
			this.inhaber.setBenutzername(benutzername);
		if (!this.inhaber.getPasswort().equals(passwort))
			this.inhaber.setPasswort(passwort);
		if (!this.inhaber.getEmail().equals(email))
			this.inhaber.setEmail(email);
		if (!this.inhaber.getAdresse().equals(adresse))
			this.inhaber.setAdresse(adresse);
		if (!this.inhaber.getVorname().equals(vorname))
			this.inhaber.setVorname(vorname);
		if (!this.inhaber.getName().equals(nachname))
			this.inhaber.setName(nachname);
		if (!this.inhaber.getBankverbindung().equals(bankverbindung))
			this.inhaber.setBankverbindung(bankverbindung);
	}

	/**
	 * Alle Statistiken holen, um sie in der GUI darzustellen, damit der Inhaber
	 * einen besseren Ueberblick ueber das Unternehmen hat
	 * 
	 * @return statistikHashMap HashMap, keys sind Statistik Titel, value ist der
	 *         Wert
	 */
	public HashMap<String, Float> statistikenAusgeben() {
		HashMap<String, Float> statistikHashMap = new HashMap<String, Float>();
		statistikHashMap.put("umsatz", (float) Statistiken.getUmsatz());
		statistikHashMap.put("ausgaben", (float) Statistiken.getAusgaben());
		statistikHashMap.put("einnahmen", (float) Statistiken.getEinnahmen());
		statistikHashMap.put("arbeitszeit", (float) Statistiken.getArbeitszeit());

		return statistikHashMap;
	}
}

package de.wwu.sopra.anwendung.mitarbeiter;

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

public class Inhabersteuerung {
	// Angemeldeter Inhaber
	private Inhaber inhaber;
	private Statistiken statistiken;
	
	public Inhabersteuerung(Inhaber inhaber, Statistiken statistiken) {
		this.inhaber = inhaber;
		this.statistiken = statistiken;
	}
	
	/**
	 * Ueberprueft, ob alle Eingaben gueltig sind - non null oder leer oder groesser als null wenn nummer
	 * @param benutzername
	 * @param passwort
	 * @param email
	 * @param adresse
	 * @param vorname
	 * @param nachname
	 * @param bankverbindung
	 * @return boolean true wenn gueltig, false wenn nicht
	 */
	private boolean gueltigeEingaben(List<Object> inputList) {
		boolean result = true;
		
		for (Object input : inputList) {
			if (input instanceof String) {				
				if (input == null || input == "") result = false;
			} else if (input instanceof Number) {
				if ((Double) input < 0) result = false;
			}
		}
		
		return result;
	}
	
	/**
	 * Moeglichkeit ein Produkt zu bearbeiten
	 * @param produkt
	 * @param name
	 * @param beschreibung
	 * @param verkaufspreis
	 */
	public void produktBearbeiten(Produkt produkt, String name, String beschreibung, double verkaufspreis) {
		if (!gueltigeEingaben(Arrays.asList(name, beschreibung, verkaufspreis))) throw new IllegalArgumentException();
		
		produkt.setName(name);
		produkt.setBeschreibung(beschreibung);
		produkt.setVerkaufspreis(verkaufspreis);
	}
	
	/**
	 * Eine Moeglichkeit Produkte hinzuzufuegen oder loeschen
	 * @param lager
	 * @param produkte
	 * @param action
	 */
	public void lagerVerwalten(Lager lager, Collection<Produkt> produkte, String action) {
		if (action == "hinzufuegen") {
			lager.addProdukte(produkte);			
		} else if (action == "loeschen") {
			lager.removeProdukte(produkte);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Eine Moeglichkeit sich Produkte in Lager anzeigen zu lassen
	 * @param lager
	 * @return lagerProdukte
	 */
	public HashSet<Produkt> sortimentAnzeigen(Lager lager) {
		return lager.getLager();
	}
	
	/**
	 * Funktion zum Bearbeiten der in einer Kategorie enthaltenen Produkte
	 * @param kategorie
	 * @param produkte
	 * @param action
	 */
	public void kategorieProdukteVerwalten(Kategorie kategorie, Collection<Produkt> produkte, String action) {
		if (action == "hinzufuegen") {
			kategorie.addProdukte(produkte);			
		} else if (action == "loeschen") {
			kategorie.removeProdukte(produkte);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Funktion zum Bearbeiten der Kategorie durch Aendern des Namens oder Aendern der Ober- oder Unterkategorie
	 * @param kategorie1
	 * @param kategorie2
	 * @param aenderung
	 * @param name
	 */
	public void kategorieBearbeiten(Kategorie kategorie1, Kategorie kategorie2, String aenderung, String name) {
		if (name != null) {
			kategorie1.setName(name);
			return;
		}
		
		if (kategorie1 == null || kategorie2 == null) throw new IllegalArgumentException();
		
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
	 * @param benutzername
	 * @param passwort
	 * @param email
	 * @param adresse
	 * @param vorname
	 * @param nachname
	 * @param bankverbindung
	 * @param rolle
	 */
	public void mitarbeiterRegistrieren (
			String benutzername,
			String passwort,
			String email,
			String adresse,
			String vorname,
			String nachname,
			String bankverbindung,
			Rolle rolle) {
		if (!gueltigeEingaben(Arrays.asList(benutzername, passwort, email, adresse, vorname, nachname, bankverbindung))) throw new IllegalArgumentException();
		
		if (rolle == Rolle.FAHRER) {
			Fahrer fahrer = new Fahrer(benutzername, passwort, email, adresse, vorname, nachname, bankverbindung, this.inhaber);
			this.inhaber.fahrerHinzufuegen(fahrer);
		} else if (rolle == Rolle.LAGERIST) {
			Lagerist lagerist = new Lagerist(benutzername, passwort, email, adresse, vorname, nachname, bankverbindung, this.inhaber);
			this.inhaber.lageristHinzufuegen(lagerist);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Funktion zum Loeschen eines Mitarbeiters
	 * @param mitarbeiter
	 */
	public void mitarbeiterLoeschen(Benutzer mitarbeiter) {
		if (mitarbeiter == null) throw new NullPointerException();
		if (mitarbeiter.getRolle() == Rolle.FAHRER) {
			this.inhaber.fahrerEntfernen((Fahrer) mitarbeiter);
		} else if (mitarbeiter.getRolle() == Rolle.LAGERIST) {
			this.inhaber.lageristEntfernen((Lagerist) mitarbeiter);
		}
	}
	
	/**
	 * Funktion zum Bearbeiten von Mitarbeiterdaten
	 * @param mitarbeiter
	 * @param benutzername
	 * @param passwort
	 * @param email
	 * @param adresse
	 * @param vorname
	 * @param nachname
	 * @param bankverbindung
	 */
	public void mitarbeiterDatenAendern(
			Benutzer mitarbeiter,
			String benutzername,
			String passwort,
			String email,
			String adresse,
			String vorname,
			String nachname,
			String bankverbindung) {
		if (!gueltigeEingaben(Arrays.asList(benutzername, passwort, email, adresse, vorname, nachname, bankverbindung))) throw new IllegalArgumentException();
		if (mitarbeiter ==  null) throw new NullPointerException();
		
		if (!mitarbeiter.getBenutzername().equals(benutzername)) mitarbeiter.setBenutzername(benutzername);
		if (!mitarbeiter.getPasswort().equals(passwort)) mitarbeiter.setPasswort(passwort);
		if (!mitarbeiter.getEmail().equals(email)) mitarbeiter.setEmail(email);
		if (!mitarbeiter.getAdresse().equals(adresse)) mitarbeiter.setAdresse(adresse);
		if (!mitarbeiter.getVorname().equals(vorname)) mitarbeiter.setVorname(vorname);
		if (!mitarbeiter.getName().equals(nachname)) mitarbeiter.setName(nachname);
		if (!mitarbeiter.getBankverbindung().equals(bankverbindung)) mitarbeiter.setBankverbindung(bankverbindung);
	}
	
	/**
	 * Funktion zum Bearbeiten von Fahrzeugdaten
	 * @param fahrzeug
	 * @param fahrzeugNummer
	 * @param kapazitaet
	 */
	public void fahrzeugDatenAendern(Fahrzeug fahrzeug, int fahrzeugNummer, float kapazitaet) {
		if (fahrzeug.equals(null)) return;
		if (fahrzeug.getFahrzeugNummer() != fahrzeugNummer) fahrzeug.setFahrzeugNummer(fahrzeugNummer);
		if (fahrzeug.getKapazitaet() != kapazitaet) fahrzeug.setKapazitaet(kapazitaet);
	}
	
	/**
	 * Funktion zum Anzeigen der persönlichen Daten des Inhabers
	 * @return daten als String, alle Daten durch Semikolon getrennt
	 */
	public String persoenlicheDatenAnzeigen() {
		String daten = this.inhaber.getBenutzername() + ";" + this.inhaber.getPasswort() + ";" + this.inhaber.getEmail() + ";" + this.inhaber.getAdresse() + ";" + this.inhaber.getVorname() + ";" + this.inhaber.getName() + ";" + this.inhaber.getBankverbindung();
		return daten;
	}
	
	/**
	 * Funktion zum Bearbeiten der persoenlichen Daten
	 * @param benutzername
	 * @param passwort
	 * @param email
	 * @param adresse
	 * @param vorname
	 * @param nachname
	 * @param bankverbindung
	 */
	public void persoenlicheDatenAendern(
			String benutzername,
			String passwort,
			String email,
			String adresse,
			String vorname,
			String nachname,
			String bankverbindung) {
		if (!this.gueltigeEingaben(Arrays.asList(benutzername, passwort, email, adresse, vorname, nachname, bankverbindung))) throw new IllegalArgumentException();
		
		if (!this.inhaber.getBenutzername().equals(benutzername)) this.inhaber.setBenutzername(benutzername);
		if (!this.inhaber.getPasswort().equals(passwort)) this.inhaber.setPasswort(passwort);
		if (!this.inhaber.getEmail().equals(email)) this.inhaber.setEmail(email);
		if (!this.inhaber.getAdresse().equals(adresse)) this.inhaber.setAdresse(adresse);
		if (!this.inhaber.getVorname().equals(vorname)) this.inhaber.setVorname(vorname);
		if (!this.inhaber.getName().equals(nachname)) this.inhaber.setName(nachname);
		if (!this.inhaber.getBankverbindung().equals(bankverbindung)) this.inhaber.setBankverbindung(bankverbindung);
	}
	
	/**
	 * Alle Statistiken holen, um sie in der GUI darzustellen, damit der Inhaber einen besseren Ueberblick ueber das Unternehmen hat
	 */
	public HashMap<String, Float> statistikenAusgeben() {
		HashMap<String, Float> statistikHashMap = new HashMap<String, Float>();
		statistikHashMap.put("umsatz", (float) statistiken.getUmsatz());
		statistikHashMap.put("ausgaben", (float) statistiken.getAusgaben());
		statistikHashMap.put("einnahmen", (float) statistiken.getEinnahmen());
		statistikHashMap.put("arbeitszeit", (float) statistiken.getArbeitszeit());
		
		return statistikHashMap;
	}
}

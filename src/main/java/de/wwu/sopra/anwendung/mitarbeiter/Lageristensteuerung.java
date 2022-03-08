package de.wwu.sopra.anwendung.mitarbeiter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.wwu.sopra.datenhaltung.benutzer.Benutzer;
import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.benutzer.Lagerist;
import de.wwu.sopra.datenhaltung.benutzer.Rolle;
import de.wwu.sopra.datenhaltung.bestellung.BestellStatus;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import de.wwu.sopra.datenhaltung.management.FahrzeugStatus;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Produkt;
import de.wwu.sopra.datenhaltung.management.Route;
import de.wwu.sopra.datenhaltung.management.Statistiken;
import de.wwu.sopra.datenhaltung.verwaltung.BenutzerRegister;
import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;
import de.wwu.sopra.datenhaltung.verwaltung.GrosshaendlerRegister;

/**
 * Die LageristenSteuerung des Systems ist das Bindeglied zwischen
 * Grenzklassen/GUI und der Datenhaltungsschicht.
 * 
 * @author Noah Latzel
 *
 */
public class Lageristensteuerung {
	/**
	 * Lagerist, der die Lageristensteuerung bedient
	 */
	Lagerist lagerist;

	/**
	 * Der Konstruktor initialisiert die LageristenSteuerung mit einem Lageristen.
	 * 
	 * @param lagerist Der eingeloggte Lagerist.
	 */
	public Lageristensteuerung(Lagerist lagerist) {
		this.lagerist = lagerist;

	}

	/**
	 * Die Methode fuegt alle Produkte der Nachbestellungen in der gewuenschten
	 * Menge dem Lager hinzu. Dafuer ruft die Methode automatisch den Preis des
	 * Grosshaendlers ueber das GrosshaendlerRegister ab.
	 * 
	 * @param nachbestellungen Die Nachbestellungen, die verarbeitet werden sollen.
	 * @pre die Nachbestellung nachbestellungen ist nicht leer
	 * @post die Ausgaben werden um die Hoehe der Einkaeufe beim Grosshaendler
	 *       erhoeht, die Produkte wurden in der gewuenschten Menge dem Lager
	 *       zugefuegt
	 */
	public void bestelleNach(HashSet<NachbestellungTupel> nachbestellungen) {
		// Vorbedingung pruefen
		assert !(nachbestellungen.isEmpty()) : "Vorbedingung bei Aufruf von bestelleNach() verletzt";

		// Vorzustand zur Ueberpruefung der Nachbedingung retten
		double ausgaben = Statistiken.getAusgaben();

		for (NachbestellungTupel n : nachbestellungen) {
			for (int i = 0; i < n.getMenge(); i++) {
				if (n.getProdukt() != null) {
					double preis = GrosshaendlerRegister.getEinkaufspreis(n.getProdukt());
					Lager.addProdukt(n.getProdukt().clone(preis));
					Statistiken.addAusgaben((double) n.getProdukt().getEinkaufspreis());

				}
			}
		}

		// Nachbedingung pruefen
		double gesamtpreis = 0;
		for (NachbestellungTupel n : nachbestellungen) {
			for (int i = 0; i < n.getMenge(); i++) {
				if (n.getProdukt() != null) {
					gesamtpreis += GrosshaendlerRegister.getEinkaufspreis(n.getProdukt());
				}
			}
		}
	}

	/**
	 * Erstellt die Route aus offenen Bestellungen und einem Fahrzeug. Die Route
	 * wird dann dem Fahrzeug zugeordnet.
	 * 
	 * Die Bestellung darf nicht leer sein und die Bestellung darf nicht mehr
	 * Kapazitaet benoetigen als das uebergebene Fahrzeug hat. Das Fahrzeug muss
	 * frei sein.
	 * 
	 * @param bestellungen Die Bestellungen, die in eine Route eingeplant werden und
	 *                     vorher im GUI ausgewaehlt wurden.
	 * @param fahrzeug     Das Fahrzeug, dem Bestellungen zugeordnet werden.
	 * @pre die Liste der Bestellungen bestellungen ist nicht leer, das Fahrzeug hat
	 *      noch keine Route
	 * @post die Liste der Bestellungen wurde in einer Route gespeichert und die
	 *       Route dem Fahrzeug uebergeben, der Status der Bestellungen in der Route
	 *       muss jetzt IN_BEARBEITUNG sein und der Status des Fahrzeugs muss BELEGT
	 *       sein
	 */
	public void planeRoute(List<Bestellung> bestellungen, Fahrzeug fahrzeug) {
		// Vorbedingung pruefen
		assert !(bestellungen.isEmpty()) : "Vorbedingung von planeRoute verletzt: die Liste der Bestellungen ist leer";
		assert fahrzeug.getRoute() == null : "Vorbedingung von planeRoute verletzt: das Fahrzeug hat schon eine Route";

		int gesamtBelegung = 0;
		for (Bestellung b : bestellungen) {
			gesamtBelegung += b.getKapazitaet();
		}
		if (gesamtBelegung > fahrzeug.getKapazitaet()) {
			throw new IllegalArgumentException("Das Fahrzeug ist zu klein fuer die Bestellung. " + "\n" + gesamtBelegung
					+ " > " + fahrzeug.getKapazitaet());
		}
		Route route = new Route(fahrzeug);

		route.setBestellungen(bestellungen);

		// Nachbedingung pruefen
		assert fahrzeug.getRoute().getBestellungen() == bestellungen
				: "Nachbedingung von planeRoute verletzt: Die Route hat nicht die richtigen Bestellungen gespeichert";
		for (Bestellung bestellung : bestellungen) {
			assert bestellung.getStatus().equals(BestellStatus.IN_BEARBEITUNG)
					: "Nachbedingung von planeRoute verletzt: Die Bestellungen der Route sind nicht IN_BEARBEITUNG";
		}
		assert fahrzeug.getStatus().equals(FahrzeugStatus.BELEGT)
				: "Nachbedingung von planeRoute verletzt: Das Fahrzeug ist nicht BELEGT";
	}

	/**
	 * Zeigt die Route eines ausgewaehlten Fahrzeugs. Das Fahrzeug muss eine Route
	 * haben, sonst wird eine IllegalArgumentException geworfen.
	 * 
	 * @param fahrzeug Das Fahrzeug, dessen Route angezeigt werden soll.
	 * @return Die Route des Fahrzeugs, falls sie existiert.
	 * @pre das Fahrzeug muss eine Route haben
	 */
	public Route zeigeRouteVonFahrzeug(Fahrzeug fahrzeug) {
		// Vorbedingung pruefen
		assert fahrzeug.getRoute() != null
				: "Vorbedingung von zeigeRouteVonFahrzeug verletzt: Das uebergebene Fahrzeug hat keine Route";

		Route route = fahrzeug.getRoute();
		return route;
	}

	/**
	 * Diese Methode gibt die Menge aller belegten oder zustellenden Fahrzeuge im
	 * System zurueck.
	 * 
	 * @return Die Menge aller belegten / zustellenden Fahrzeuge.
	 */
	public HashSet<Fahrzeug> getFahrzeugeMitRoute() {
		HashSet<Fahrzeug> fahrzeuge = new HashSet<Fahrzeug>();
		HashSet<Fahrzeug> alleFahrzeuge = FahrzeugRegister.getFahrzeuge();
		for (Fahrzeug f : alleFahrzeuge) {
			if (f != null) {
				if (f.getRoute() != null) {
					fahrzeuge.add(f);
				}
			}
		}
		return fahrzeuge;
	}

	/**
	 * Oeffentliche Methode, ueber die die offenen Bestellungen abgerufen werden.
	 * 
	 * @return Alle offenen Bestellungen im System
	 */
	public HashSet<Bestellung> zeigeOffeneBestellungen() {
		HashSet<Bestellung> bestellungen = this.extractOffeneBestellungenRegister();
		return bestellungen;
	}

	/**
	 * Diese Methode gibt die Menge aller freien Fahrzeuge im System zurueck und
	 * wird fuer das GUI verwendet, wenn die Route geplant wird und der Lagerist
	 * sehen muss, welche Fahrzeuge frei sind.
	 * 
	 * @return Die Menge aller freien Fahrzeuge im System.
	 */
	public HashSet<Fahrzeug> zeigeFreieFahrzeuge() {
		HashSet<Fahrzeug> fahrzeuge = new HashSet<Fahrzeug>();
		HashSet<Fahrzeug> alleFahrzeuge = FahrzeugRegister.getFahrzeuge();
		for (Fahrzeug f : alleFahrzeuge) {
			if (f != null) {
				if (f.getStatus().equals(FahrzeugStatus.FREI)) {
					fahrzeuge.add(f);
				}
			}
		}
		return fahrzeuge;
	}

	/**
	 * Eine interne Methode, die alle offenen Bestellungen aus dem BenutzerRegister
	 * liest.
	 * 
	 * @return Alle offenen Bestellungen, die im BenutzerRegister gefuehrt sind.
	 */
	private HashSet<Bestellung> extractOffeneBestellungenRegister() {
		HashSet<Bestellung> bestellungen = new HashSet<Bestellung>();
		for (Benutzer benutzerDaten : BenutzerRegister.getBenutzerListe()) {

			// Falls der Benutzer ein Kunde ist:
			if (benutzerDaten.getRolle() == Rolle.KUNDE) {
				Kunde kunde = (Kunde) benutzerDaten;

				// Falls der Kunde Bestellungen hat:
				if (kunde.getBestellungen() != null) {
					List<Bestellung> tempBestellungen = kunde.getBestellungen();

					for (int i = 0; i < tempBestellungen.size(); i++) {
						if (tempBestellungen.get(i).getStatus().equals(BestellStatus.OFFEN)) {
							bestellungen.add(tempBestellungen.get(i));
						}
					}
				}
			}

		}
		return bestellungen;
	}

	/**
	 * Die persoenlichen Daten des Lageristen werden durch die neuen Daten
	 * ueberschieben
	 * 
	 * @param benutzername   benutzername des Lageristen
	 * @param passwort       passwort des Lageristen
	 * @param email          email des Lageristen
	 * @param adresse        adresse des Lageristen
	 * @param vorname        Vorname des Lageristen
	 * @param name           Name des Lageristen
	 * @param bankverbindung Bankverbindung des Lageristen
	 * @pre die uebermittelten Eingaben muessen gueltig sein
	 * @post die neuen Daten des Lageristen muessen mit den uebermittelten Eingaben
	 *       uebereinstimmen
	 */
	public void persoenlicheDatenBearbeiten(String benutzername, String passwort, String email, String adresse,
			String vorname, String name, String bankverbindung) {
		// Vorbedingung pruefen
		assert !benutzername.equals("")
				: "Vorbedingung von persoenlicheDatenBearbeiten verletzt: benutzername ist leer";
		assert !passwort.equals("") : "Vorbedingung von persoenlicheDatenBearbeiten verletzt: passwort ist leer";
		assert !email.equals("") : "Vorbedingung von persoenlicheDatenBearbeiten verletzt: email ist leer";
		assert !adresse.equals("") : "Vorbedingung von persoenlicheDatenBearbeiten verletzt: adresse ist leer";
		assert !vorname.equals("") : "Vorbedingung von persoenlicheDatenBearbeiten verletzt: vorname ist leer";
		assert !name.equals("") : "Vorbedingung von persoenlicheDatenBearbeiten verletzt: name ist leer";
		assert !bankverbindung.equals("")
				: "Vorbedingung von persoenlicheDatenBearbeiten verletzt: bankverbindung ist leer";

		this.lagerist.setBenutzername(benutzername);
		this.lagerist.setPasswort(passwort);
		this.lagerist.setEmail(email);
		this.lagerist.setAdresse(adresse);
		this.lagerist.setVorname(vorname);
		this.lagerist.setName(name);
		this.lagerist.setBankverbindung(bankverbindung);

		// Nachbedingung pruefen
		assert lagerist.getBenutzername().equals(benutzername) : "Nachbedingung verletzt: benutzername weicht ab";
		assert lagerist.getPasswort().equals(passwort) : "Nachbedingung verletzt: passwort weicht ab";
		assert lagerist.getEmail().equals(email) : "Nachbedingung verletzt: email weicht ab";
		assert lagerist.getAdresse().equals(adresse) : "Nachbedingung verletzt: adresse weicht ab";
		assert lagerist.getVorname().equals(vorname) : "Nachbedingung verletzt: vorname weicht ab";
		assert lagerist.getName().equals(name) : "Nachbedingung verletzt: name weicht ab";
		assert lagerist.getBankverbindung().equals(bankverbindung) : "Nachbedingung verletzt: bankverbindung weicht ab";
	}

	/**
	 * persoenliche Daten werden in einem String der Form: benutzername;passwort;
	 * email;adresse;vorname;name;bankverbindung; gespeichert
	 * 
	 * @return gibt den String aus
	 */
	public String persoenlicheDatenAnzeigen() {
		String returnstring = lagerist.getBenutzername() + ";" + lagerist.getPasswort() + ";" + lagerist.getEmail()
				+ ";" + lagerist.getAdresse() + ";" + lagerist.getVorname() + ";" + lagerist.getName() + ";"
				+ lagerist.getBankverbindung() + ";";
		return returnstring;

	}

	/**
	 * Gibt das Sortiment zurueck
	 * 
	 * @return das Sortiment
	 */
	public Set<Produkt> getSortiment() {
		return Lager.sortimentAnzeigen();
	}

}

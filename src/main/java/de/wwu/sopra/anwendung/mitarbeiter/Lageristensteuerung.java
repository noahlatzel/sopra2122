package de.wwu.sopra.anwendung.mitarbeiter;

import java.util.HashSet;
import java.util.List;

import de.wwu.sopra.datenhaltung.bestellung.BestellStatus;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import de.wwu.sopra.datenhaltung.management.FahrzeugStatus;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Route;
import de.wwu.sopra.datenhaltung.management.Statistiken;
import de.wwu.sopra.datenhaltung.verwaltung.BenutzerDatenTripel;
import de.wwu.sopra.datenhaltung.verwaltung.BenutzerRegister;
import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;
import de.wwu.sopra.datenhaltung.verwaltung.GrosshaendlerRegister;

/**
 * Die LageristenSteuerung des Systems ist das Bindeglied zwischen
 * Grenzklassen/GUI und der Datenhaltungsschicht.
 * 
 * @author NoahLatzel
 *
 */
public class Lageristensteuerung {
	private Lager lager;
	private BenutzerRegister benutzerRegister;

	private Statistiken statistiken;
	private GrosshaendlerRegister preisRegister;

	/**
	 * Initialisiert die LageristenSteuerung. Dafuer braucht sie Zugriff auf das
	 * Lager, das BenutzerRegister und das FahrzeugRegister.
	 * 
	 * @param lager            Das Lager des Systems, in welchem alle Produkte
	 *                         enthalten sind.
	 * @param benutzerRegister Das BenutzerRegister des Systems, in dem alle
	 *                         Benutzer mit Warenkorb und Bestellung gespeichert
	 *                         werden.
	 * @param fahrzeugRegister Das FahrzeugRegister des Systems, in dem alle
	 *                         Fahrzeuge gespeichert werden.
	 */
	public Lageristensteuerung(Lager lager, BenutzerRegister benutzerRegister, Statistiken statistiken,
			GrosshaendlerRegister preisRegister) {
		this.lager = lager;
		this.benutzerRegister = benutzerRegister;
		this.statistiken = statistiken;
		this.preisRegister = preisRegister;
	}

	/**
	 * Die Methode fuegt alle Produkte der Nachbestellungen in der gewuenschten
	 * Menge dem Lager hinzu. Dafuer ruft die Methode automatisch den Preis des
	 * Grosshaendlers ueber das GrosshaendlerRegister ab.
	 * 
	 * @param nachbestellungen Die Nachbestellungen, die verarbeitet werden sollen.
	 */
	public void bestelleNach(HashSet<NachbestellungTupel> nachbestellungen) {
		for (NachbestellungTupel n : nachbestellungen) {
			for (int i = 0; i < n.getMenge(); i++) {
				lager.addProdukt(n.getProdukt().clone(this.preisRegister.getPreis(n.getProdukt())));
				statistiken.addAusgaben((float) n.getProdukt().getEinkaufspreis());
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
	 */
	public void planeRoute(List<Bestellung> bestellungen, Fahrzeug fahrzeug) {
		int gesamtBelegung = 0;
		for (Bestellung b : bestellungen) {
			gesamtBelegung += b.getKapazitaetBelegt();
		}
		if (gesamtBelegung > fahrzeug.getKapazitaet()) {
			throw new IllegalArgumentException("Das Fahrzeug ist zu klein fuer die Bestellung. " + "\n" + gesamtBelegung
					+ " > " + fahrzeug.getKapazitaet());
		}
		// TODO Routennummer in der Route selbst berechnen.
		Route route = new Route(1, fahrzeug);

		route.setBestellungen(bestellungen);
	}

	/**
	 * Zeigt die Route eines ausgewaehlten Fahrzeugs. Das Fahrzeug muss eine Route
	 * haben, sonst wird eine IllegalArgumentException geworfen.
	 * 
	 * @param fahrzeug Das Fahrzeug, dessen Route angezeigt werden soll.
	 * @return Die Route des Fahrzeugs, falls sie existiert.
	 */
	public Route zeigeRouteVonFahrzeug(Fahrzeug fahrzeug) {
		Route route = fahrzeug.getRoute();
		if (route == null) {
			throw new IllegalArgumentException("Das Fahrzeug hat keine Route.");
		}
		return route;
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
			if (f.getStatus().equals(FahrzeugStatus.FREI)) {
				fahrzeuge.add(f);
			}
		}
		return fahrzeuge;
	}

	/**
	 * Gibt das Lager der LageristenSteuerung zurueck.
	 * 
	 * @return Das Lager, mit dem die LageristenSteuerung verbunden ist.
	 */
	public Lager getLager() {
		return this.lager;
	}

	/**
	 * Eine interne Methode, die alle offenen Bestellungen aus dem BenutzerRegister
	 * liest.
	 * 
	 * @param benutzerRegister Das BenutzerRegister aus dem die Daten gelesen
	 *                         werden.
	 * @return Alle offenen Bestellungen, die im BenutzerRegister gefuehrt sind.
	 */
	private HashSet<Bestellung> extractOffeneBestellungenRegister() {
		HashSet<Bestellung> bestellungen = new HashSet<Bestellung>();
		for (BenutzerDatenTripel benutzerDaten : benutzerRegister.getBenutzerListe()) {
			List<Bestellung> tempBestellungen = benutzerDaten.getBestellungen();
			for (Bestellung b : tempBestellungen) {
				if (b.getStatus().equals(BestellStatus.OFFEN)) {
					bestellungen.add(b);
				}
			}
		}
		return bestellungen;
	}
}

package de.wwu.sopra.anwendung.mitarbeiter;

import java.util.HashSet;
import java.util.List;

import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Route;

public class LageristenSteuerung {
	Lager lager;

	// TODO Preisliste importieren fuer Grosshaendler?
	// TODO Ausgaben mit Statistiken verrechnen
	/**
	 * Die Methode fuegt alle Produkte der Nachbestellungen in der gewuenschten
	 * Menge dem Lager hinzu
	 * 
	 * @param nachbestellungen Die Nachbestellungen, die verarbeitet werden sollen.
	 */
	public void bestelleNach(HashSet<NachbestellungTupel> nachbestellungen) {
		for (NachbestellungTupel n : nachbestellungen) {
			for (int i = 0; i < n.getMenge(); i++) {
				lager.addProdukt(n.getProdukt().clone());
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
	 * TODO Invariante fuer Bestellung: niemals leer sein TODO Erfolgsnachricht?
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

	public HashSet<Bestellung> zeigeOffeneBestellungen() {
		HashSet<Bestellung> bestellungen = new HashSet<Bestellung>();

		return bestellungen;
	}

	public HashSet<Fahrzeug> zeigeFreieFahrzeuge() {
		HashSet<Fahrzeug> fahrzeuge = new HashSet<Fahrzeug>();

		return fahrzeuge;
	}

	// TODO Wie wird die Position uebermittelt und wird sie auf einer Karte
	// angezeigt?
	public void zeigeFahrzeugPosition(Fahrzeug fahrzeug) {

	}

}

package de.wwu.sopra.datenhaltung.management;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import de.wwu.sopra.datenhaltung.verwaltung.GrosshaendlerRegister;
import de.wwu.sopra.datenhaltung.verwaltung.SerialisierungPipeline;

/**
 * Klasse fuer das Lager
 * 
 * @author Noah Latzel
 *
 */

public class Lager implements Serializable {
	/**
	 * SerialisierungsID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Pfad zum Speichern des Lagers
	 */
	private static final String path_set = "lager_set.ser";
	/**
	 * Pfad zum Speichern des Lagerbestands
	 */
	private static final String path_map = "lager_map.ser";
	/**
	 * Liste der Produkte im Lager
	 */
	private static HashSet<Produkt> lager = new HashSet<Produkt>();
	/**
	 * HashMap fuer den Lagerbestand
	 */
	private static HashMap<String, Integer> lagerbestand = new HashMap<String, Integer>();

	/**
	 * TODO Alternative fuer den Lagerbestand
	 */
	private static HashMap<Produkt, Integer> lagerBestand = new HashMap<Produkt, Integer>();

	/**
	 * Singleton Konstruktor
	 */
	private Lager() {
	}

	/**
	 * Fuegt ein neues Produkt ins Lager hinzu. Dafuer wird das Objekt selbst in das
	 * Lager gelegt und in Lagerbestand wird der Bestand des Produktes erhoeht. Der
	 * Lagerbestand eines Produktes orientiert sich am Namen des Produktes.
	 * 
	 * @param p Das neue Produkt.
	 *
	 * @post Der Bestand des Produkts muss um eins erhoeht werden.
	 */
	public static void addProdukt(Produkt p) {
		// Vorzustand zur Ueberpruefung der Nachbedingung retten
		int bestand_p = Lager.getProduktBestand(p);

		lager.add(p);
		Lager.addBestand(p);

		// Nachbedingung pruefen
		bestand_p += 1;
		assert Lager.getProduktBestand(p) == bestand_p
				: "Nachbedingung von addProdukt() verletzt: der Lagerbestand wurde nicht angepasst";
	}

	/**
	 * Fuegt neue Produkte ins Lager hinzu. Dafuer werden die Objekte selbst in das
	 * Lager gelegt und in Lagerbestand wird der Bestand des Produktes erhoeht. Der
	 * Lagerbestand eines Produktes orientiert sich am Namen des Produktes.
	 * 
	 * @param p Die neuen Produkte.
	 */
	public static void addProdukte(Collection<Produkt> p) {
		for (Produkt produkt : p) {
			addProdukt(produkt);
		}

	}

	/**
	 * Entfernt ein Produkt aus dem Lager. Dafuer wird das Objekt aus dem Lager
	 * entfernt und der Lagerbestand des Produkts um 1 verringert.
	 * 
	 * @param p Das zu entfernende Produkt.
	 * 
	 * @post Der Bestand des Produkts muss um eins verringert werden.
	 */
	public static void removeProdukt(Produkt p) {
		// Vorzustand zur Ueberpruefung der Nachbedingung retten
		int bestand_p = Lager.getProduktBestand(p);

		lager.remove(p);
		removeBestand(p);

		// Nachbedingung pruefen
		bestand_p -= 1;
		assert Lager.getProduktBestand(p) == bestand_p
				: "Nachbedingung von removeProdukt() verletzt: der Lagerbestand wurde nicht angepasst";
	}

	/**
	 * Entfernt Produkte aus dem Lager. Dafuer werden die Objekte aus dem Lager
	 * entfernt und der Lagerbestand des Produkts um 1 verringert.
	 * 
	 * @param p Die zu entfernenden Produkte.
	 */
	public static void removeProdukte(Collection<Produkt> p) {
		lager.removeAll(p);
		for (Produkt produkt : p) {
			removeBestand(produkt);
		}
	}

	/**
	 * Diese Methode gibt den Lagerbestand eines Produktes zurueck. Es kann sowohl
	 * nach dem Namen als auch nach dem Produkt selbst gesucht werden.
	 * 
	 * @param name Der gewuenschte Produktname
	 * @return Den Lagerbestand eines Produkts
	 */
	public static int getProduktBestand(String name) {
		if (Lager.getLagerbestand().get(name) == null) {
			throw new IllegalArgumentException("Das Produkt ist nicht im Sortiment.");
		} else {
			return Lager.getLagerbestand().get(name);
		}
	}

	/**
	 * Diese Methode gibt den Lagerbestand eines Produktes zurueck. Es kann sowohl
	 * nach dem Namen als auch nach dem Produkt selbst gesucht werden.
	 * 
	 * @param produkt Das gewuenschte Produkt
	 * @return Den Lagerbestand des Produktes
	 */
	public static int getProduktBestand(Produkt produkt) {
		if (Lager.getLagerbestand().get(produkt.getName()) == null) {
			throw new IllegalArgumentException("Das Produkt ist nicht im Sortiment.");
		} else {
			return Lager.getLagerbestand().get(produkt.getName());
		}
	}

	/**
	 * Erzeugen einer Liste mit allen Objekten mit dem selben Namen
	 * 
	 * @param produkte HashSet mit allen Produkten
	 * @param suche    Name des Produkts
	 * @return Liste mit allen Produkten aus dem Lager welche der uebergebenen Namen
	 *         tragen
	 */
	public static List<Produkt> getProdukteAusLager(HashSet<Produkt> produkte, String suche) {
		List<Produkt> liste = new ArrayList<Produkt>();
		for (Produkt p : produkte) {
			if (p.getName().equals(suche)) {
				liste.add(p);
			}
		}
		return liste;
	}

	/**
	 * Gibt den Lagerbestand zurueck.
	 * 
	 * @return Den Lagerbestand
	 */
	public static HashMap<String, Integer> getLagerbestand() {
		return lagerbestand;
	}

	/**
	 * Gibt das Lager zurueck.
	 * 
	 * @return Das Lager
	 */
	public static HashSet<Produkt> getLager() {
		return lager;
	}

	/**
	 * Fuegt 1 zum Bestand des Produktes hinzu.
	 * 
	 * @param p Produkt, was hinzugefuegt wird.
	 */
	private static void addBestand(Produkt p) {
		if (lagerbestand.get(p.getName()) == null) {
			lagerbestand.put(p.getName(), 1);
		} else {
			lagerbestand.put(p.getName(), lagerbestand.get(p.getName()) + 1);
		}
	}

	/**
	 * Entfernt 1 vom Bestand des Produktes.
	 * 
	 * @param p Produkt, was entfernt wird.
	 */
	private static void removeBestand(Produkt p) {
		lagerbestand.put(p.getName(), lagerbestand.get(p.getName()) - 1);
	}

	/**
	 * Deserialisiert das Lager.
	 */
	public static void load() {
		SerialisierungPipeline<HashMap<String, Integer>> sp = new SerialisierungPipeline<HashMap<String, Integer>>();
		SerialisierungPipeline<HashSet<Produkt>> sp1 = new SerialisierungPipeline<HashSet<Produkt>>();
		lagerbestand = sp.deserialisieren(path_map);
		lager = sp1.deserialisieren(path_set);
	}

	/**
	 * Gibt das Sortiment zurueck
	 * 
	 * @return Sortiment
	 */
	public static ArrayList<Produkt> sortimentAnzeigen() {
		return (ArrayList<Produkt>) Lager.lagerBestand.keySet();
	}

	/**
	 * Fuegt ein Produkt zum Sortiment hinzu.
	 * 
	 * @param produkt Produkt, das hinzugefuegt werden soll
	 */
	public static void produktZumSortimentHinzufuegen(Produkt produkt) {
		// Prueft, ob Produkt bereits im Sortiment
		boolean bereitsVorhanden = false;
		for (Produkt prod : Lager.lagerBestand.keySet()) {
			if (prod.getName().equals(produkt.getName())) {
				bereitsVorhanden = true;
			}
		}
		// Wenn nicht im Sortiment, dann wird es ins Sortiment hinzugefuegt
		if (!bereitsVorhanden) {
			Lager.lagerBestand.put(produkt, 0);
			GrosshaendlerRegister.getPreislisteIn().put(produkt.getName(), produkt.getEinkaufspreis());
		}
	}

	/**
	 * Entfernt ein Produkt aus dem Sortiment.
	 * 
	 * @param produkt Produkt, das entfernt werden soll
	 */
	public static void produktAusDemSortimentEntfernen(Produkt produkt) {
		// Entfernt das Produkt aus dem Sortiment
		for (Produkt prod : Lager.lagerBestand.keySet()) {
			if (prod.getName().equals(produkt.getName())) {
				Lager.lagerBestand.remove(prod);
			}
		}
		HashSet<Produkt> old_lager = (HashSet<Produkt>) Lager.getLager().clone();
		// Entfernt die restlichen Produkte des Sortiments aus dem Lager
		for (Produkt prod : old_lager) {
			if (prod.getName().equals(produkt.getName())) {
				Lager.removeProdukt(prod);
			}
		}
	}

	/**
	 * Serialisiert das Lager.
	 */
	public static void save() {
		SerialisierungPipeline<HashMap<String, Integer>> sp = new SerialisierungPipeline<HashMap<String, Integer>>();
		SerialisierungPipeline<HashSet<Produkt>> sp1 = new SerialisierungPipeline<HashSet<Produkt>>();
		sp.serialisieren(Lager.getLagerbestand(), path_map);
		sp1.serialisieren(Lager.getLager(), path_set);
	}

	/**
	 * Setzt das Lager zurueck (fuer Tests).
	 */
	public static void reset() {
		HashSet<Produkt> lager_old = (HashSet<Produkt>) Lager.getLager().clone();
		for (Produkt p : lager_old) {
			Lager.removeProdukt(p);
		}
	}
}

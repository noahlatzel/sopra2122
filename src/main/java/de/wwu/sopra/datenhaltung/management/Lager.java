package de.wwu.sopra.datenhaltung.management;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import de.wwu.sopra.datenhaltung.verwaltung.SerialisierungPipeline;

/**
 * Klasse fuer das Lager
 * 
 * @author Noah Latzel
 *
 */

public class Lager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String path_set = "lager_set.ser";
	private static final String path_map = "lager_map.ser";
	private static HashSet<Produkt> lager = new HashSet<Produkt>();
	private static HashMap<String, Integer> lagerbestand = new HashMap<String, Integer>();

	public Lager() {
		lager = new HashSet<Produkt>();
		lagerbestand = new HashMap<String, Integer>();
	}

	/**
	 * Fuegt ein neues Produkt ins Lager hinzu. Dafuer wird das Objekt selbst in das
	 * Lager gelegt und in Lagerbestand wird der Bestand des Produktes erhoeht. Der
	 * Lagerbestand eines Produktes orientiert sich am Namen des Produktes.
	 * 
	 * @param p Das neue Produkt.
	 */
	public static void addProdukt(Produkt p) {
		lager.add(p);
		Lager.addBestand(p);
	}

	/**
	 * Fuegt neue Produkte ins Lager hinzu. Dafuer werden die Objekte selbst in das
	 * Lager gelegt und in Lagerbestand wird der Bestand des Produktes erhoeht. Der
	 * Lagerbestand eines Produktes orientiert sich am Namen des Produktes.
	 * 
	 * @param p Die neuen Produkte.
	 */
	public static void addProdukte(Collection<Produkt> p) {
		lager.addAll(p);
		for (Produkt produkt : p) {
			addBestand(produkt);
		}

	}

	/**
	 * Entfernt ein Produkt aus dem Lager. Dafuer wird das Objekt aus dem Lager
	 * entfernt und der Lagerbestand des Produkts um 1 verringert.
	 * 
	 * @param p Das zu entfernende Produkt.
	 */
	public static void removeProdukt(Produkt p) {
		lager.remove(p);
		removeBestand(p);
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
		if (lagerbestand == null) {
			lagerbestand = new HashMap<String, Integer>();
		}
		if (lager == null) {
			lager = new HashSet<Produkt>();
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
}

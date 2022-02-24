package de.wwu.sopra.datenhaltung.management;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class Lager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashSet<Produkt> lager;
	private HashMap<String, Integer> lagerbestand;

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
	public void addProdukt(Produkt p) {
		lager.add(p);
		addBestand(p);
	}

	/**
	 * Fuegt neue Produkte ins Lager hinzu. Dafuer werden die Objekte selbst in das
	 * Lager gelegt und in Lagerbestand wird der Bestand des Produktes erhoeht. Der
	 * Lagerbestand eines Produktes orientiert sich am Namen des Produktes.
	 * 
	 * @param p Die neuen Produkte.
	 */
	public void addProdukte(Collection<Produkt> p) {
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
	public void removeProdukt(Produkt p) {
		lager.remove(p);
		removeBestand(p);
	}

	/**
	 * Entfernt Produkte aus dem Lager. Dafuer werden die Objekte aus dem Lager
	 * entfernt und der Lagerbestand des Produkts um 1 verringert.
	 * 
	 * @param p Die zu entfernenden Produkte.
	 */
	public void removeProdukte(Collection<Produkt> p) {
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
	public int getProduktBestand(String name) {
		return this.getLagerbestand().get(name);
	}

	/**
	 * Diese Methode gibt den Lagerbestand eines Produktes zurueck. Es kann sowohl
	 * nach dem Namen als auch nach dem Produkt selbst gesucht werden.
	 * 
	 * @param produkt Das gewuenschte Produkt
	 * @return Den Lagerbestand des Produktes
	 */
	public int getProduktBestand(Produkt produkt) {
		return this.getLagerbestand().get(produkt.getName());
	}

	/**
	 * Gibt den Lagerbestand zurueck.
	 * 
	 * @return Den Lagerbestand
	 */
	public HashMap<String, Integer> getLagerbestand() {
		return this.lagerbestand;
	}

	/**
	 * Gibt das Lager zurueck.
	 * 
	 * @return Das Lager
	 */
	public HashSet<Produkt> getLager() {
		return this.lager;
	}

	/**
	 * Fuegt 1 zum Bestand des Produktes hinzu.
	 * 
	 * @param p Produkt, was hinzugefuegt wird.
	 */
	private void addBestand(Produkt p) {
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
	private void removeBestand(Produkt p) {
		lagerbestand.put(p.getName(), lagerbestand.get(p.getName()) - 1);
	}
}

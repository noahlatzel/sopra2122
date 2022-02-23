package de.wwu.sopra.datenhaltung.management;

import java.io.Serializable;

public class Produkt implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Kategorie kategorie;
	private String name;
	private String beschreibung;
	private final double einkaufspreis;
	private double verkaufspreis;

	public Produkt(String name, String beschreibung, double einkaufspreis, double verkaufspreis) {
		if (einkaufspreis <= 0) {
			throw new IllegalArgumentException("Der Einkaufspreis kann nicht 0 oder negativ sein.");
		}
		this.setName(name);
		this.setBeschreibung(beschreibung);
		this.setVerkaufspreis(verkaufspreis);
		this.einkaufspreis = einkaufspreis;
	}

	/**
	 * Setzt die Kategorie fuer ein Produkt
	 * 
	 * @param k Kategorie des Produkts
	 */
	public void setKategorie(Kategorie k) {
		this.kategorie = k;
		if (k != null) {
			if (!(k.getProdukte().contains(this))) {
				k.addProdukt(this);
			}
		}
	}

	/**
	 * Gibt die Kategorie des Produkts zurueck.
	 * 
	 * @return Kategorie des Produkts
	 */
	public Kategorie getKategorie() {
		return this.kategorie;
	}

	/**
	 * Setzt den Namen fuer ein Produkt.
	 * 
	 * @param name neuer Name fuer Produkt
	 */
	public void setName(String name) {
		if (name == "") {
			throw new IllegalArgumentException("Der Name eines Produktes darf nicht leer sein.");
		}
		this.name = name;
	}

	/**
	 * Gibt den Namen des Produkts zurueck.
	 * 
	 * @return name Name des Produkts
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Setzt die Beschreibung fuer ein Produkt.
	 * 
	 * @param beschreibung neue Beschreibung fuer Produkt
	 */
	public void setBeschreibung(String beschreibung) {
		if (beschreibung == "") {
			throw new IllegalArgumentException("Die Beschreibung eines Produktes darf nicht leer sein.");
		}
		this.beschreibung = beschreibung;
	}

	/**
	 * Gibt die Beschreibung des Produkts zurueck.
	 * 
	 * @return name Beschreibung des Produkts
	 */
	public String getBeschreibung() {
		return this.beschreibung;
	}

	/**
	 * Gibt den Verkaufspreis eines Produkts zurueck.
	 * 
	 * @return der Verkaufspreis
	 */
	public double getVerkaufspreis() {
		return verkaufspreis;
	}

	/**
	 * Setzt den Verkaufspreis eines Produkts.
	 * 
	 * @param verkaufspreis neuer Verkaufspreis
	 */
	public void setVerkaufspreis(double verkaufspreis) {
		if (verkaufspreis < this.einkaufspreis) {
			throw new IllegalArgumentException("Der Verkaufspreis darf nicht niedriger als der Einkaufspreis sein: "
					+ "\n" + verkaufspreis + " < " + this.einkaufspreis);
		}
		this.verkaufspreis = verkaufspreis;
	}

	/**
	 * Gibt den Einkaufspreis des Produktes zurueck.
	 * 
	 * @return Der Einkaufspreis des Produktes
	 */
	public double getEinkaufspreis() {
		return einkaufspreis;
	}

}

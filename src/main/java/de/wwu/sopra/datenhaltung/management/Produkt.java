package de.wwu.sopra.datenhaltung.management;

import java.io.Serializable;

/**
 * Klasse der Produkte
 * 
 * @author Noah Latzel
 *
 */
public class Produkt implements Serializable {
	/**
	 * SerialisierungsID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Kategorie des Produkts
	 */
	private Kategorie kategorie;
	/**
	 * Name des Produkts
	 */
	private String name;
	/**
	 * Beschreibung des Produkts
	 */
	private String beschreibung;
	/**
	 * Einkaufspreis des Produkts
	 */
	private final double einkaufspreis;
	/**
	 * Verkaufspreis des Produkts
	 */
	private double verkaufspreis;
	/**
	 * ProduktID
	 */
	private final int produktID;

	/**
	 * Konstruktor
	 * 
	 * @param name          name
	 * @param beschreibung  beschreibung
	 * @param einkaufspreis einkaufspreis
	 * @param verkaufspreis verkaufspreis
	 */
	public Produkt(String name, String beschreibung, double einkaufspreis, double verkaufspreis) {
		if (einkaufspreis <= 0) {
			throw new IllegalArgumentException("Der Einkaufspreis kann nicht 0 oder negativ sein.");
		}
		this.setName(name);
		this.setBeschreibung(beschreibung);
		this.setVerkaufspreis(verkaufspreis);
		this.einkaufspreis = einkaufspreis;
		this.produktID = Lager.addProduktName(this.getName());
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
		this.name = name.strip();
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

	/**
	 * Klont ein Produkt fuer die Nachbestellung.
	 * 
	 * @param preis Der Preis des Grosshaendlers.
	 * @return Das neue Produkt.
	 */
	public Produkt clone(double preis) {
		Produkt temp = new Produkt(this.getName(), this.getBeschreibung(), preis, preis + 0.2);
		temp.setKategorie(this.getKategorie());
		return temp;

	}

	/**
	 * Gibt die ProduktID zurueck
	 * 
	 * @return die produktID
	 */
	public int getProduktID() {
		return produktID;
	}

	/**
	 * Gibt den HashCode zurueck.
	 * 
	 * @return hashCode
	 */
	@Override
	public int hashCode() {
		return this.getProduktID();
	}

	/**
	 * Zwei Produkte sind gleich, wenn sie die gleiche ProduktID haben.
	 * 
	 * @param obj Das zu vergleichende Objekt
	 * @return Ob die Produkte gleich sind
	 */
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Produkt && this.getProduktID() == ((Produkt) obj).getProduktID();
	}

}

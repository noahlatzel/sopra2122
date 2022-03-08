package de.wwu.sopra.datenhaltung.management;

import java.io.Serializable;
import java.net.URL;

import javafx.scene.image.Image;

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
	 * Pfad zum Produktbild
	 */
	private String produktBild = "stock.jpg";

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
		int index = Lager.getProduktNamenListe().indexOf(this.getName());
		if (index != -1) {
			Lager.getProduktNamenListe().set(index, name.strip());
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
		Produkt temp = new Produkt(this.getName(), this.getBeschreibung(), this.getEinkaufspreis(), preis);
		temp.setKategorie(this.getKategorie());
		temp.setProduktBild(this.getProduktBild());
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
	 * Laedt das Produktbild
	 * 
	 * @return das Produktbild
	 */
	public Image loadBild() {
		Image bild = new Image(getClass().getResource(this.getProduktBild()).toExternalForm());
		for (Produkt produkt : Lager.sortimentAnzeigen()) {
			if (produkt.equals(this)) {
				bild = new Image(getClass().getResource(produkt.getProduktBild()).toExternalForm());
			}
		}

		return bild;
	}

	/**
	 * Gibt den Pfad zum ProduktBild zurueck
	 * 
	 * @return den Pfad zum ProduktBild
	 */
	public String getProduktBild() {
		return this.produktBild;
	}

	/**
	 * Setzt einen neuen Bildpfad
	 * 
	 * @param bildPfad Pfad fuer das Bild
	 */
	public void setProduktBild(String bildPfad) {
		URL test = getClass().getResource(bildPfad);
		if (test != null) {
			this.produktBild = bildPfad;
		}
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

package de.wwu.sopra.datenhaltung.management;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

/**
 * Klasse der Kategorien
 * 
 * @author Noah Latzel
 */
public class Kategorie implements Serializable {
	/**
	 * SerialisierungsID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Name der Kategorie
	 */
	private String name;
	/**
	 * Liste der Produkte in der Kategorie
	 */
	private HashSet<Produkt> produkte;
	/**
	 * Liste der Unterkategorien
	 */
	private HashSet<Kategorie> unterkategorien;
	/**
	 * Oberkategorie
	 */
	private Kategorie oberkategorie;

	/**
	 * Konstruktor
	 * 
	 * @param name Name
	 * @inv Eine Kategorie darf nicht Ober- oder Unterkategorie von sich selbst
	 *      sein.
	 */
	public Kategorie(String name) {
		if (name == "") {
			throw new IllegalArgumentException("Der Name einer Kategorie darf nicht leer sein.");
		}
		this.setName(name);
		this.produkte = new HashSet<Produkt>();
		this.unterkategorien = new HashSet<Kategorie>();
		Lager.kategorieHinzufuegen(this);

		// Klasseninvariante pruefen
		if (this.getOberkategorie() != null) {
			assert !this.getOberkategorie().equals(this)
					: "Klasseninvariante von Kategorie verletzt: die Kategorie ist Oberkategorie von sich selbst";
		}
		for (Kategorie unterkategorie : this.getUnterkategorien()) {
			assert !unterkategorie.equals(this)
					: "Klasseninvariante von Kategorie verletzt: die Kategorie ist Unterkategorie von sich selbst";
		}
	}

	/**
	 * Gibt den Namen der Kategorie zurueck.
	 * 
	 * @return Der Name der Kategorie
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setzt einen neuen Namen fuer die Kategorie.
	 * 
	 * @param name Der neue Name der Kategorie
	 */
	public void setName(String name) {
		if (name == "") {
			throw new IllegalArgumentException("Der Name einer Kategorie darf nicht leer sein.");
		}
		this.name = name;
	}

	/**
	 * Fuegt der Kategorie ein neues Produkt hinzu.
	 * 
	 * @param p Neues Produkt fuer die Kategorie
	 */
	public void addProdukt(Produkt p) {
		this.produkte.add(p);
		if (p.getKategorie() != this) {
			p.setKategorie(this);
		}
	}

	/**
	 * Fuegt der Kategorie neue Produkte hinzu.
	 * 
	 * @param p Menge neuer Produkte fuer die Kategorie
	 */
	public void addProdukte(Collection<Produkt> p) {
		this.produkte.addAll(p);
		for (Produkt produkt : p) {
			if (produkt.getKategorie() != this) {
				produkt.setKategorie(this);
			}
		}
	}

	/**
	 * Gibt die Produkte der Kategorie zurueck.
	 * 
	 * @return Die Produkte der Kategorie.
	 */
	public HashSet<Produkt> getProdukte() {
		return this.produkte;
	}

	/**
	 * Entfernt ein Produkt aus den Produkten der Kategorie.
	 * 
	 * @param p Das Produkt, was entfernt werden soll
	 */
	public void removeProdukt(Produkt p) {
		this.produkte.remove(p);
		p.setKategorie(null);
	}

	/**
	 * Entfernt Produkte aus den Produkten der Kategorie.
	 * 
	 * @param p Die Produkt, die entfernt werden sollen.
	 */
	public void removeProdukte(Collection<Produkt> p) {
		this.produkte.removeAll(p);
		for (Produkt produkt : p) {
			produkt.setKategorie(null);
		}
	}

	/**
	 * Gibt die Oberkategorie der Kategorie zurueck.
	 * 
	 * @return Die Oberkategorie der Kategorie. Gibt null zurueck, falls es keine
	 *         Oberkategorie gibt.
	 */
	public Kategorie getOberkategorie() {
		return this.oberkategorie;
	}

	/**
	 * Setzt eine neue Oberkategorie fuer die Kategorie.Eine Kategorie kann nicht
	 * ihre eigene Oberkategorie sein.
	 * 
	 * @param k Die neue Oberkategorie der Kategorie
	 */
	public void setOberkategorie(Kategorie k) {
		if (k == this) {
			throw new IllegalArgumentException("Eine Kategorie kann nicht ihre eigene Oberkategorie sein.");
		}
		if (k == null) {
			if (this.getOberkategorie() != null) {
				this.oberkategorie.removeUnterkategorie(this);
			}
		} else {
			this.oberkategorie = k;
			if (!(k.getUnterkategorien().contains(k))) {
				k.addUnterkategorie(this);
			}
		}

		// Klasseninvariante pruefen
		if (this.getOberkategorie() != null) {
			assert !this.getOberkategorie().equals(this)
					: "Klasseninvariante von Kategorie verletzt: die Kategorie ist Oberkategorie von sich selbst";
		}
		for (Kategorie unterkategorie : this.getUnterkategorien()) {
			assert !unterkategorie.equals(this)
					: "Klasseninvariante von Kategorie verletzt: die Kategorie ist Unterkategorie von sich selbst";
		}
	}

	/**
	 * Gibt die Unterkategorien der Kategorie zurueck.
	 * 
	 * @return Die Unterkategorien der Kategorie.
	 */
	public HashSet<Kategorie> getUnterkategorien() {
		return this.unterkategorien;
	}

	/**
	 * Fuegt eine neue Unterkategorie zur Kategorie hinzu. Eine Kategorie kann nicht
	 * ihre eigene Unterkategorie sein.
	 * 
	 * @param k Die neue Unterkategorie
	 */
	public void addUnterkategorie(Kategorie k) {
		if (k == this) {
			throw new IllegalArgumentException("Eine Kategorie kann nicht ihre eigene Unterkategorie sein.");
		}
		this.unterkategorien.add(k);
		if (k.getOberkategorie() != this) {
			k.setOberkategorie(this);
		}

		// Klasseninvariante pruefen
		if (this.getOberkategorie() != null) {
			assert !this.getOberkategorie().equals(this)
					: "Klasseninvariante von Kategorie verletzt: die Kategorie ist Oberkategorie von sich selbst";
		}
		for (Kategorie unterkategorie : this.getUnterkategorien()) {
			assert !unterkategorie.equals(this)
					: "Klasseninvariante von Kategorie verletzt: die Kategorie ist Unterkategorie von sich selbst";
		}
	}

	/**
	 * Fuegt neue Unterkategorien zur Kategorie hinzu.Eine Kategorie kann nicht ihre
	 * eigene Unterkategorie sein.
	 * 
	 * @param k Die neue Unterkategorien
	 */
	public void addUnterkategorien(Collection<Kategorie> k) {
		if (k.contains(this)) {
			throw new IllegalArgumentException("Eine Kategorie kann nicht ihre eigene Unterkategorie sein.");
		}
		this.unterkategorien.addAll(k);
		for (Kategorie kategorie : k) {
			if (kategorie.getOberkategorie() != this) {
				kategorie.setOberkategorie(this);
			}
		}

		// Klasseninvariante pruefen
		if (this.getOberkategorie() != null) {
			assert !this.getOberkategorie().equals(this)
					: "Klasseninvariante von Kategorie verletzt: die Kategorie ist Oberkategorie von sich selbst";
		}
		for (Kategorie unterkategorie : this.getUnterkategorien()) {
			assert !unterkategorie.equals(this)
					: "Klasseninvariante von Kategorie verletzt: die Kategorie ist Unterkategorie von sich selbst";
		}
	}

	/**
	 * Entfernt eine Unterkategorie, falls existent, aus der Kategorie.
	 * 
	 * @param k Die zu entfernende Unterkategorie.
	 */
	public void removeUnterkategorie(Kategorie k) {
		this.unterkategorien.remove(k);
		if (k.getOberkategorie() != null) {
			k.oberkategorie = null;
		}
	}

	/**
	 * Entfernt Unterkategorien, falls existent, aus der Kategorie.
	 * 
	 * @param k Die zu entfernenden Unterkategorien.
	 */
	public void removeUnterkategorien(Collection<Kategorie> k) {
		this.unterkategorien.removeAll(k);
		for (Kategorie kategorie : k) {
			kategorie.setOberkategorie(null);
		}
	}

}

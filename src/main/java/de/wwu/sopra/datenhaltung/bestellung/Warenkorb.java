package de.wwu.sopra.datenhaltung.bestellung;

import java.io.Serializable;
import java.util.List;

import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.management.Produkt;

/**
 * Klasse Rechung
 * 
 * @author Jasmin Horstknepper
 *
 */
public class Warenkorb implements Serializable {

	/**
	 * SerialisierungsID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Betrag des Warenkorbs
	 */
	private double betrag;
	/**
	 * Kunde, dem der Warenkorb gehoert
	 */
	private final Kunde kunde;
	/**
	 * Liste der Produkte im Warenkorb
	 */
	private List<Produkt> produkte;

	/**
	 * Konstruktor der Klasse Warenkorb
	 * 
	 * @param produkte Liste an Produkten
	 * @param kunde    Kunde, dem der Warenkorb gehoert
	 * @inv Ein Warenkorb gehoert immer zu einem Kunden
	 */
	public Warenkorb(List<Produkt> produkte, Kunde kunde) {
		// Klasseninvariante pruefen
		assert kunde != null : "Klasseninvariante von Warenkorb verletzt: der Warenkorb gehoert zu keinem Kunden mehr";
		assert produkte != null : "Klasseninvariante von Warenkorb verletzt: die Liste der Produkte ist null";

		this.produkte = produkte;
		this.kunde = kunde;
		this.setBetrag();
		kunde.setWarenkorb(this);

	}

	/**
	 * Getter Methode fuer die Variable Betrag
	 * 
	 * @return Betrag
	 */
	public double getBetrag() {
		return betrag;
	}

	/**
	 * Setter Methoder fuer die Variable Betrag
	 * 
	 */
	public void setBetrag() {
		this.betrag = 0;
		for (Produkt produkt : this.produkte) {
			betrag += produkt.getVerkaufspreis();
		}
	}

	/**
	 * Getter Methoode fuer die Liste mit Produkten
	 * 
	 * @return Lste mit Produkten
	 */
	public List<Produkt> getProdukte() {
		return produkte;
	}

	/**
	 * Methode zum Hinzufuegen von Produkten
	 * 
	 * @param produkt Produkt
	 */
	public void produktHinzufuegen(Produkt produkt) {
		this.produkte.add(produkt);
		this.betrag += produkt.getVerkaufspreis();
	}

	/**
	 * Methode zum Entfernen von Produkten
	 * 
	 * @param produkt Produkt
	 */
	public void produktEntfernen(Produkt produkt) {
		if (this.produkte.contains(produkt)) {
			this.produkte.remove(produkt);
			this.betrag -= produkt.getVerkaufspreis();
		}

	}

	/**
	 * Getter Methode fuer den Kunden
	 * 
	 * @return Kunden
	 */
	public Kunde getKunde() {
		return kunde;
	}

	/**
	 * Getter Methode fuer den Kunden
	 */
	public void warenkorbLeeren() {
		this.produkte.clear();
	}
}

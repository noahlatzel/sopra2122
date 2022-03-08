package de.wwu.sopra.datenhaltung.bestellung;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Produkt;
import de.wwu.sopra.datenhaltung.verwaltung.BenutzerRegister;

/**
 * Die Klasse Bestellung
 * 
 * @author Jasmin Horstknepper
 *
 */
public class Bestellung implements Serializable {

	/**
	 * SerialisierungsID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Bestellnummer der Bestellung
	 */
	private final int bestellnummer;
	/**
	 * Betrag der Bestellung
	 */
	private final double betrag;
	/**
	 * Status der Bestellung
	 */
	private BestellStatus status;
	/**
	 * Liste der Produkte
	 */
	private final List<Produkt> produkte;
	/**
	 * Kunde
	 */
	private final Kunde kunde;
	/**
	 * Datum der Bestellung
	 */
	private LocalDateTime datum;
	/**
	 * Rechnung der Bestellung
	 */
	private Rechnung rechnung;
	/**
	 * Kapazitaetsbelegung im Fahrzeug
	 */
	private int kapazitaet;
	/**
	 * Adresse des Kunden
	 */
	private String adresse;
	/**
	 * Rabatt der Bestellung
	 */
	private Rabatt rabatt;

	/**
	 * Konstruktor fuer die Klasse Bestellung
	 * 
	 * @param datum    Datum
	 * @param produkte Liste von Produkten
	 * @param kunde    Kunde
	 * @inv Eine Bestellung muss immer Produkte enthalten und einem Kunden
	 *      zugeordnet sein
	 * @post Die Bestellung muss in der Liste der Bestellungen des Kunden gefuehrt
	 *       werden. Die Produkte der Bestellung sind nicht mehr im Lager.
	 */
	public Bestellung(LocalDateTime datum, List<Produkt> produkte, Kunde kunde) {
		// Klasseninvariante pruefen
		assert kunde != null
				: "Klasseninvariante des Konstruktors der Bestellung verletzt: die Bestellung gehoert zu keinem Kunden mehr";
		assert !produkte.isEmpty()
				: "Klasseninvariante des Konstruktors der Bestellung verletzt: die Liste der Bestellungen ist leer";

		this.bestellnummer = BenutzerRegister.getZaehlerBestellung();
		this.setStatus(BestellStatus.OFFEN);
		this.produkte = produkte;
		this.betrag = calcBetrag();
		this.kunde = kunde;
		this.adresse = kunde.getAdresse();
		this.kapazitaet = produkte.size();
		kunde.bestellungHinzufuegen(this);

		for (Produkt produkt : produkte) {
			Lager.removeProdukt(produkt);
		}

		// Nachbedingung pruefen
		assert this.getKunde().getBestellungen().contains(this)
				: "Nachbedingung des Konstruktors der Bestellung verletzt: die Bestellung ist nicht im Kunden festgehalten";
		assert this.getKunde() == kunde
				: "Nachbedingung des Konstruktors der Bestellung verletzt: der Bestellung wurde ein falscher Kunde zugewiesen";
	}

	/**
	 * Konstruktor fuer die Klasse Bestellung
	 * 
	 * @param datum    Datum
	 * @param produkte Liste von Produkten
	 * @param kunde    Kunde
	 * @param rabatt   Rabatt
	 * @inv Eine Bestellung muss immer Produkte enthalten und einem Kunden
	 *      zugeordnet sein
	 * @post Die Bestellung muss in der Liste der Bestellungen des Kunden gefuehrt
	 *       werden. Die Produkte der Bestellung sind nicht mehr im Lager.
	 */
	public Bestellung(LocalDateTime datum, List<Produkt> produkte, Kunde kunde, Rabatt rabatt) {
		// Klasseninvariante pruefen
		assert kunde != null
				: "Klasseninvariante des Konstruktors der Bestellung verletzt: die Bestellung gehoert zu keinem Kunden mehr";
		assert !produkte.isEmpty()
				: "Klasseninvariante des Konstruktors der Bestellung verletzt: die Liste der Bestellungen ist leer";

		this.bestellnummer = BenutzerRegister.getZaehlerBestellung();
		this.setStatus(BestellStatus.OFFEN);
		this.produkte = produkte;
		this.kunde = kunde;
		this.adresse = kunde.getAdresse();
		this.kapazitaet = produkte.size();
		this.rabatt = rabatt;
		this.betrag = calcBetrag();
		kunde.bestellungHinzufuegen(this);

		for (Produkt produkt : produkte) {
			Lager.removeProdukt(produkt);
		}

		// Nachbedingung pruefen
		assert this.getKunde().getBestellungen().contains(this)
				: "Nachbedingung des Konstruktors der Bestellung verletzt: die Bestellung ist nicht im Kunden festgehalten";
		assert this.getKunde() == kunde
				: "Nachbedingung des Konstruktors der Bestellung verletzt: der Bestellung wurde ein falscher Kunde zugewiesen";
	}

	/**
	 * Zaehlt die Anzahl eines Produkts
	 * 
	 * @param produkt Produkt
	 * @return Anzahl des Produkts im Warenkorb/Bestellung
	 */
	public int getProduktAnzahl(Produkt produkt) {

		int i = 0;
		for (Produkt p : this.produkte) {
			if (p.getName().equals(produkt.getName())) {
				i++;
			}
		}
		return i;
	}

	/**
	 * Getter Methode fuer das Datum
	 * 
	 * @return Datum
	 */
	public LocalDateTime getDatum() {
		return datum;
	}

	/**
	 * Getter Methode fuer den Status
	 * 
	 * @return Status
	 */
	public BestellStatus getStatus() {
		return status;
	}

	/**
	 * Berechnet den Betrag der Bestellung
	 * 
	 * @return Betrag der Bestellung
	 */
	public double calcBetrag() {
		double temp = 0;
		for (Produkt p : this.getProdukte()) {
			temp += p.getVerkaufspreis();
		}
		if (rabatt != null) {
			temp = temp * (1 - ((double) rabatt.getProzent() / 100));
		}
		return temp;
	}

	/**
	 * Setter Methode fuer den Status
	 * 
	 * @param status Status
	 * @post Ist der Status STORNIERT, sind die Produkte der Bestellung wieder im
	 *       Lager
	 */
	public void setStatus(BestellStatus status) {
		this.status = status;
		if (status.equals(BestellStatus.STORNIERT)) {
			for (Produkt produkt : this.getProdukte()) {
				Lager.addProdukt(produkt);
			}
		}
		// Nachbedingung pruefen
		if (this.getStatus().equals(BestellStatus.STORNIERT)) {
			for (Produkt produkt : this.getProdukte()) {
				assert Lager.getLager().contains(produkt)
						: "Nachbedingung von setStatus() verletzt: der Status ist STORNIERT und die Produkte der Bestellung sind nicht ins Lager zurueckgekehrt";
			}
		}
	}

	/**
	 * Getter Methode fuer Bestellnummer
	 * 
	 * @return Bestellnummer
	 */
	public int getBestellnummer() {
		return bestellnummer;
	}

	/**
	 * Getter Methode fuer den Betrag
	 * 
	 * @return Betrag
	 */
	public double getBetrag() {
		return betrag;
	}

	/**
	 * Getter Methode fuer die Liste der Produkte
	 * 
	 * @return Liste der Produkte
	 */
	public List<Produkt> getProdukte() {
		return produkte;
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
	 * Getter Methode fuer die Rechnungen
	 * 
	 * @return Rechnungen
	 */
	public Rechnung getRechnung() {
		return rechnung;
	}

	/**
	 * Setteer Methode fuer die Rechnungen
	 * 
	 * @param rechnung Rechnung
	 */
	public void setRechnung(Rechnung rechnung) {
		this.rechnung = rechnung;
	}

	/**
	 * Gibt die Kapazitaetsbelegung der Bestellung zurueck.
	 * 
	 * @return Die Kapazitaet, die durch die Bestellung belegt wird.
	 */
	public int getKapazitaet() {
		return this.kapazitaet;
	}

	/**
	 * gibt die Adresse aus
	 * 
	 * @return Die Adresse, an die die Bestellung geliefert werden soll.
	 */
	public String getAdresse() {
		return this.adresse;
	}

	/**
	 * overrides die toString() methode
	 * 
	 * @return Bestellungsnummer
	 */
	@Override
	public String toString() {
		return "Bestellung " + this.getBestellnummer();
	}

	/**
	 * Fuegt der Bestellung einen Rabatt hinzu.
	 * 
	 * @param rabatt
	 */
	public void setRabatt(Rabatt rabatt) {
		this.rabatt = rabatt;
	}

	/**
	 * Gibt den Rabatt der Bestellung zurueck.
	 * 
	 * @return Gibt den Rabatt der Bestellung zurueck.
	 */
	public Rabatt getRabatt() {
		return rabatt;
	}
}

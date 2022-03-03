package de.wwu.sopra.anwendung.kunde;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.bestellung.BestellStatus;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.bestellung.Warenkorb;
import de.wwu.sopra.datenhaltung.management.Kategorie;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Produkt;
import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;

public class KundensteuerungTest {

	Kunde kunde;
	List<Produkt> liste = new ArrayList<Produkt>();
	List<Produkt> liste2 = new ArrayList<Produkt>();
	List<Bestellung> bestellungen = new ArrayList<Bestellung>();
	Warenkorb warenkorb;

	// vor jedem Test
	@BeforeEach
	public void init() {
		Lager.reset();
		FahrzeugRegister.reset();
		kunde = new Kunde("MuellerAgi.123", "12345", "Agatha.b@gmail.com", "Privet Drive 3", "Agatha", "Mueller",
				"De414580364567893456");
		Produkt cola = new Produkt("Cola", "Toller Geschmack", 0.99, 1.29);
		Produkt cola2 = new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29);
		Produkt cola3 = new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29);
		Produkt cola4 = new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29);
		Lager.getLager().clear();
		Lager.addProdukt(cola);
		Lager.addProdukt(cola2);
		Lager.addProdukt(cola3);
		Lager.addProdukt(cola4);
		liste.add(cola);
		liste2.add(cola2);
		liste2.add(cola3);
		liste2.add(cola4);
		warenkorb = new Warenkorb(liste2, kunde);
	}

	/**
	 * Testet, ob der Konstruktor funktioniert.
	 */
	@Test
	void testKonstruktor() {
		assertTrue(kunde.getName().equals("Mueller"));
		assertTrue(kunde.getVorname().equals("Agatha"));
		assertTrue(kunde.getAdresse().equals("Privet Drive 3"));
		assertTrue(kunde.getBankverbindung().equals("De414580364567893456"));
		assertTrue(kunde.getBenutzername().equals("MuellerAgi.123"));
		assertTrue(kunde.getPasswort().equals("12345"));
		assertTrue(kunde.getEmail().equals("Agatha.b@gmail.com"));

	}

	/**
	 * Testet, ob die Methode persoenlicheDatenAnzeigen funktioniert
	 */
	@Test
	void testpersoenlicheDatenAnzeigen() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		assertTrue(kundensteuerung.persoenlicheDatenAnzeigen()
				.equals("MuellerAgi.123;12345;Agatha.b@gmail.com;Privet Drive 3;Agatha;Mueller;De414580364567893456;"));
	}

	/**
	 * Testet ob die Methode persoenlicheDatenAendern funktioniert
	 */
	@Test
	void testpersoenlicheDatenAendern() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		kundensteuerung.persoenlicheDatenAendern("MuellerAgi.123", "45678", "Agatha.b@gmail.com", "Privet Drive 3",
				"Agatha", "Mueller", "De414580364567893456");
		String vorher = "MuellerAgi.123;12345;Agatha.b@gmail.com;Privet Drive 3;Agatha;Mueller;De414580364567893456;";
		assertFalse(kundensteuerung.persoenlicheDatenAnzeigen().equals(vorher));
	}

	/**
	 * Testet ob die Methode suchen funktioniert
	 */
	@Test
	void testeSuchen() {

		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);

		List<Produkt> produkte = kundensteuerung.suchen("Coca Cola");

		assertTrue(produkte.containsAll(liste2));
	}

	/**
	 * Testet, ob die Methode bestellen funktioniert
	 */
	@Test
	void testeBestellen() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		Produkt fanta = new Produkt("Fanta", "Toller Geschmack", 0.99, 1.29);
		liste.add(fanta);
		Bestellung bestellung1 = new Bestellung(LocalDateTime.now(), liste, kunde);

		List<Bestellung> bestellungen = new ArrayList<Bestellung>();
		bestellungen.add(bestellung1);

		warenkorb.warenkorbLeeren();
		for (int i = 0; i < liste.size(); i++) {
			warenkorb.produktHinzufuegen(liste.get(i));
		}

		kundensteuerung.bestellen();

		for (int i = 0; i < kunde.getBestellungen().size(); i++) {
			for (int j = 0; j < kunde.getBestellungen().get(i).getProdukte().size(); j++) {

				assertTrue(bestellungen.get(i).getProdukte().get(j)
						.equals(kunde.getBestellungen().get(i).getProdukte().get(j)));
			}
		}
	}

	/**
	 * Testet, ob die Methode warenkorbAnsicht funktioniert
	 */
	@Test
	void testeWarenkorbAnsicht() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		kunde.setWarenkorb(warenkorb);
		assertTrue(warenkorb.equals(kundensteuerung.warenkorbAnsicht()));
	}

	/**
	 * Testet ob die Methode bestellungenAnzeigen funktioniert
	 */
	@Test
	void testeBestellungenAnzeigen() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		Bestellung bestellung1 = new Bestellung(LocalDateTime.now(), liste, kunde);
		Bestellung bestellung2 = new Bestellung(LocalDateTime.now(), liste2, kunde);
		bestellungen.add(bestellung1);
		bestellungen.add(bestellung2);
		kunde.bestellungHinzufuegen(bestellung1);
		kunde.bestellungHinzufuegen(bestellung2);
		assertTrue(bestellungen.equals(kundensteuerung.bestellungenAnzeigen()));
	}

	/**
	 * Testet, ob die Methode stornieren funktioniert
	 */
	@Test
	void testeStornieren() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		Bestellung bestellung1 = new Bestellung(LocalDateTime.now(), liste, kunde);
		Bestellung bestellung2 = new Bestellung(LocalDateTime.now(), liste2, kunde);
		bestellungen.add(bestellung1);
		kunde.bestellungHinzufuegen(bestellung1);
		kunde.bestellungHinzufuegen(bestellung2);
		kundensteuerung.stornieren(bestellung2);
		for (int i = 0; i < bestellungen.size(); i++) {
			assertTrue(bestellungen.get(i).getBestellnummer() == kunde.getBestellungen().get(i).getBestellnummer());
		}
		assertThrows(NullPointerException.class, () -> {
			kundensteuerung.stornieren(null);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			bestellung1.setStatus(BestellStatus.ABGESCHLOSSEN);
			kundensteuerung.stornieren(bestellung1);
		});

	}

	/**
	 * Testet, ob die Methode nachbestellen funktioniert
	 */
	@Test
	void testeNachbestellen() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		Bestellung bestellung1 = new Bestellung(LocalDateTime.now(), liste, kunde);
		Bestellung bestellung2 = new Bestellung(LocalDateTime.now(), liste2, kunde);
		Bestellung bestellung3 = new Bestellung(LocalDateTime.now(), liste2, kunde);
		bestellungen.add(bestellung1);
		bestellungen.add(bestellung2);
		bestellungen.add(bestellung3);
		kunde.bestellungHinzufuegen(bestellung1);
		kunde.bestellungHinzufuegen(bestellung2);
		kundensteuerung.nachbestellen(bestellung2);

		List<String> namen1 = new ArrayList<String>();
		List<String> namen2 = new ArrayList<String>();
		for (int i = 0; i < liste2.size(); i++) {
			namen1.add(liste2.get(i).getName());
			namen2.add(kunde.getBestellungen().get(2).getProdukte().get(i).getName());
		}

		assertTrue(namen1.containsAll(namen2));
		assertThrows(NullPointerException.class, () -> {
			kundensteuerung.nachbestellen(null);
		});

	}

	@Test
	void testeNachbestellenThrows() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		Produkt test = new Produkt("asd", "Toller Geschmack", 0.99, 1.29);
		Lager.addProdukt(test);
		liste.add(new Produkt("asd", "Toller Geschmack", 0.99, 1.29));
		liste.add(new Produkt("asd", "Toller Geschmack", 0.99, 1.29));
		System.out.println(Lager.getProduktBestand("asd"));
		Bestellung bestellung1 = new Bestellung(LocalDateTime.now(), liste, kunde);
		System.out.println(bestellung1.getProduktAnzahl(test));
		System.out.println(Lager.getProduktBestand("asd"));
		assertThrows(IllegalArgumentException.class, () -> {
			kundensteuerung.nachbestellen(bestellung1);
		});
	}

	/**
	 * Testet, ob die Methode neueProduktliste funktioniert
	 */
	@Test
	void testeNeueProduktliste() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		List<Produkt> neueListe = kundensteuerung.neueProduktListe(liste2);
		// Vergleicht, ob alle Produkte der beiden Listen die selben Namen haben und
		// somit das selbe Produkt sind
		for (int i = 0; i < liste2.size(); i++) {
			assertTrue(liste2.get(i).getName().equals(neueListe.get(i).getName()));
		}
	}

	@Test
	void getKategorienTest() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		HashSet<Kategorie> kategorien = new HashSet<Kategorie>();
		for (Produkt p : Lager.getLager()) {
			kategorien.add(p.getKategorie());
		}

		assertTrue(kundensteuerung.getKategorien().equals(kategorien));

	/**
	 * Testet suchenThrows
	 */
	@Test
	void testSuchenThrows() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		assertThrows(NullPointerException.class, () -> {
			kundensteuerung.suchen(null);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			Produkt test = new Produkt("asd", "Toller Geschmack", 0.99, 1.29);
			Lager.addProdukt(test);
			Lager.removeProdukt(test);
			kundensteuerung.suchen("asd");
		});

	}
}

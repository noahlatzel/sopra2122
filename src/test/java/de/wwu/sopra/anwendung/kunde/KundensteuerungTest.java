package de.wwu.sopra.anwendung.kunde;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.bestellung.BestellStatus;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.bestellung.Rabatt;
import de.wwu.sopra.datenhaltung.bestellung.Warenkorb;
import de.wwu.sopra.datenhaltung.management.Kategorie;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Produkt;
import de.wwu.sopra.datenhaltung.verwaltung.BenutzerRegister;
import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;

public class KundensteuerungTest {

	Kunde kunde;
	List<Produkt> liste = new ArrayList<Produkt>();
	List<Produkt> liste2 = new ArrayList<Produkt>();
	List<Bestellung> bestellungen = new ArrayList<Bestellung>();
	Warenkorb warenkorb;

	@AfterEach
	void cleanAfter() {
		Lager.reset();
		FahrzeugRegister.reset();
	}

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
		Lager.produktZumSortimentHinzufuegen(new Produkt("Cola", "Lecker", 0.49, 0.99));
		Lager.produktZumSortimentHinzufuegen(new Produkt("Coca Cola", "Lecker", 0.49, 0.99));
		Lager.produktZumSortimentHinzufuegen(new Produkt("Test", "Lecker", 0.49, 0.99));
		// Lager.produktZumSortimentHinzufuegen(new Produkt("Fanta", "Lecker", 0.49,
		// 0.99));
		Lager.produktZumSortimentHinzufuegen(new Produkt("asd", "Lecker", 0.49, 0.99));
		Lager.produktZumSortimentHinzufuegen(new Produkt("Fanta-stisch", "Lecker", 0.49, 0.99));
		Lager.produktZumSortimentHinzufuegen(new Produkt("Name", "Lecker", 0.49, 0.99));
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
		assertThrows(IllegalArgumentException.class, () -> {
			kundensteuerung.suchen("Test");
		});
	}

	/**
	 * Testet, ob die Methode bestellen funktioniert
	 */
	@Test
	void testeBestellen() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		Produkt fanta = new Produkt("Cola", "Toller Geschmack", 0.99, 1.29);
		liste.add(fanta);

		Bestellung bestellung1 = new Bestellung(LocalDateTime.now(), liste, kunde);

		List<Bestellung> bestellungen = new ArrayList<Bestellung>();
		bestellungen.add(bestellung1);

		warenkorb.warenkorbLeeren();
		for (int i = 0; i < liste.size(); i++) {
			warenkorb.produktHinzufuegen(liste.get(i));
		}

		kundensteuerung.bestellen();

		for (int i = 0; i < kunde.getBestellungen().size() - 1; i++) {
			for (int j = 0; j < kunde.getBestellungen().get(i).getProdukte().size(); j++) {

				assertTrue(bestellungen.get(i).getProdukte().get(j)
						.equals(kunde.getBestellungen().get(i).getProdukte().get(j)));
			}
		}
	}

	/**
	 * Testet, ob die Methode bestellen funktioniert
	 */
	@Test
	void testeBestellen2() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		Produkt fanta = new Produkt("Cola", "Toller Geschmack", 0.99, 1.29);
		liste.add(fanta);
		Rabatt rabatt = new Rabatt("ABC", 50);

		Bestellung bestellung1 = new Bestellung(LocalDateTime.now(), liste, kunde, rabatt);

		List<Bestellung> bestellungen = new ArrayList<Bestellung>();
		bestellungen.add(bestellung1);

		warenkorb.warenkorbLeeren();
		for (int i = 0; i < liste.size(); i++) {
			warenkorb.produktHinzufuegen(liste.get(i));
		}

		kundensteuerung.bestellen(rabatt);

		for (int i = 0; i < kunde.getBestellungen().size() - 1; i++) {
			for (int j = 0; j < kunde.getBestellungen().get(i).getProdukte().size(); j++) {

				assertTrue(bestellungen.get(i).getProdukte().get(j)
						.equals(kunde.getBestellungen().get(i).getProdukte().get(j)));
			}
		}

		assertTrue(bestellung1.getRabatt().equals(rabatt));
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
		bestellungen.add(bestellung1);
		bestellungen.add(bestellung2);
		kunde.bestellungHinzufuegen(bestellung1);
		kunde.bestellungHinzufuegen(bestellung2);

		Lager.addProdukt(new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29));
		Lager.addProdukt(new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29));
		Lager.addProdukt(new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29));
		Lager.addProdukt(new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29));
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

		Bestellung bestellung1 = new Bestellung(LocalDateTime.now(), liste, kunde);

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

	/**
	 * Testet getKategorien
	 */
	@Test
	void getKategorienTest() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		HashSet<Kategorie> kategorien = new HashSet<Kategorie>();

		Iterator<Produkt> iterator = Lager.getLager().iterator();
		while (iterator.hasNext()) {
			Produkt p = iterator.next();
			if (p.getKategorie() != null) {
				kategorien.add(p.getKategorie());
			}
		}

		assertTrue(kundensteuerung.getKategorien().equals(kategorien));

	}

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

	/**
	 * Testet produktZuWarenkorbHinzufuegen
	 */
	@Test
	void produktZuWarenkorbHinzufuegenTest() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		BenutzerRegister.benutzerHinzufuegen(kunde);

		while (BenutzerRegister.getWarenkorb(kunde).getProdukte().size() > 0) {
			BenutzerRegister.getWarenkorb(kunde).getProdukte().remove(0);
		}

		Produkt produkt = new Produkt("Fanta-stisch", "Beschreibung", 1, 2.99);
		Lager.addProdukt(produkt);

		kundensteuerung.produktZuWarenkorbHinzufuegen(produkt, 1);

		assertTrue(kundensteuerung.warenkorbAnsicht().getProdukte().get(0).getName().equals(produkt.getName()));
	}

	/**
	 * Testet getLager
	 */
	@Test
	void getLagerTest() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);

		assertEquals(kundensteuerung.getLager(), Lager.getLagerbestand().keySet());
	}

	/**
	 * Testet getProduktBestand
	 */
	@Test
	void getProduktBestandTest() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);

		Produkt produkt = new Produkt("Name", "Beschreibung", 1, 2);

		Lager.addProdukt(produkt);

		assertEquals(kundensteuerung.getProduktBestand(produkt), Lager.getProduktBestand(produkt));
	}

	@Test
	void testAddRabatt() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		String rabattcode = kundensteuerung.addRabatt();

		assertFalse(kunde.getRabatte().isEmpty());
		assertTrue(kunde.getRabattGueltig(rabattcode));
		assertTrue(kunde.getRabattProzent(rabattcode) <= 20);
		assertTrue(kunde.getRabattProzent(rabattcode) >= 5);
	}

	@Test
	void rabattFunktionenTest() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		String rabattcode = kundensteuerung.addRabatt();

		Rabatt rabatt = kunde.getRabatte().get(0);

		assertEquals(kundensteuerung.getRabattGueltig(rabattcode), kunde.getRabattGueltig(rabattcode));
		assertEquals(kundensteuerung.getRabattProzent(rabattcode), kunde.getRabattProzent(rabattcode));
		assertEquals(kundensteuerung.rabattEinloesen(rabattcode), rabatt);
	}

	@Test
	void filterKategorien() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		Set<Produkt> produkte = new HashSet<Produkt>();
		produkte.addAll(liste2);

		Kategorie getraenk = new Kategorie("Getraenk");
		Kategorie wasserKat = new Kategorie("wasserKat");
		Kategorie keinNestleKat = new Kategorie("keinNestleKat");
		Kategorie keinNestleKat2 = new Kategorie("keinNestleKat2");
		Produkt fanta = new Produkt("Fanta", "Fanta", 1, 2);
		Produkt wasser = new Produkt("Wasser", "Wasser", 1, 2);
		Produkt keinNestle = new Produkt("KeinNestle", "KeinNestle", 1, 2);
		Produkt keinNestle2 = new Produkt("KeinNestle2", "KeinNestle2", 1, 2);

		// Unterkategorien erstellen
		getraenk.addUnterkategorie(wasserKat);
		wasserKat.addUnterkategorie(keinNestleKat);
		keinNestleKat.addUnterkategorie(keinNestleKat2);

		// Produkte zu Kategorien hinzufuegen
		getraenk.addProdukt(fanta);
		wasserKat.addProdukt(wasser);
		keinNestleKat.addProdukt(keinNestle);
		keinNestleKat2.addProdukt(keinNestle2);

		produkte.add(fanta);
		produkte.add(wasser);
		produkte.add(keinNestle);
		produkte.add(keinNestle2);

		System.out.println();
		produkte = kundensteuerung.filterProdukteNachKategorie(produkte, getraenk);
		assertTrue(produkte.size() == 4);

		produkte = kundensteuerung.filterProdukteNachKategorie(produkte, wasserKat);
		assertTrue(produkte.size() == 3);

		produkte = kundensteuerung.filterProdukteNachKategorie(produkte, keinNestleKat);
		assertTrue(produkte.size() == 2);

		produkte = kundensteuerung.filterProdukteNachKategorie(produkte, keinNestleKat2);
		assertTrue(produkte.size() == 1);
	}

	@Test
	void testGetRabatte() {
		Kundensteuerung kundensteuerung = new Kundensteuerung(this.kunde);
		assertTrue(kundensteuerung.getRabatte().isEmpty());
	}

}

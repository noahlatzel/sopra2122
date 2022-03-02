package de.wwu.sopra.datenhaltung.verwaltung;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.datenhaltung.benutzer.Fahrer;
import de.wwu.sopra.datenhaltung.benutzer.Inhaber;
import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.bestellung.Rechnung;
import de.wwu.sopra.datenhaltung.bestellung.Warenkorb;
import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import de.wwu.sopra.datenhaltung.management.Produkt;

public class TestKoepfeAllgemein {

	public void testKoepfe() {
		// inhaber
		Inhaber inhaber = new Inhaber("Bossbaby", "123", "iamnumberone", "Schlossalle Hotel", "Heribert", "Dietrich",
				"123");

		// Fahrer
		Fahrer fahrer1 = new Fahrer("killerman", "passwort", "123@online.de", "ostbad 1", "Herbert", "schulze", "123",
				inhaber);
		Fahrer fahrer2 = new Fahrer("manni", "passwort", "123@online.de", "Elektrizitaetswerk", "Dieter", "Meiner",
				"123", inhaber);
		Fahrer fahrer3 = new Fahrer("Kalle", "passwort", "123@online.de", "Bahnhof", "Klaus", "kleber", "123", inhaber);

		// Kunden
		Kunde kunde1 = new Kunde("Beton", "1234", "hart@test.de", "Abstiege 1", "Zementa", "test", "test");
		Kunde kunde2 = new Kunde("Bierman", "1234", "hart@test.de", "Destille", "Maxi", "malvoll", "test");
		Kunde kunde3 = new Kunde("Eimer", "1234", "hart@test.de", "Davidwache", "p��", "Reh", "test");

		// Produkte
		Produkt cola = new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29);
		Produkt bier = new Produkt("Krombacher Pils", "Eine Perle der Natur", 0.99, 1.96);
		Produkt korn = new Produkt("Sasse Korn", "LEEEECKER", 4.20, 6.66);

		// fahrzeuge
		Fahrzeug porsche = new Fahrzeug(5);
		Fahrzeug bus = new Fahrzeug(200);
		Fahrzeug mini = new Fahrzeug(1);

		// Liste der Produkte
		List<Produkt> produkte = new ArrayList<Produkt>();
		produkte.add(bier);
		produkte.add(cola);
		produkte.add(korn);

		// bestellungen
		Bestellung testbestellung1 = new Bestellung(LocalDateTime.now(), produkte, kunde2);
		Bestellung testbestellung2 = new Bestellung(LocalDateTime.now(), produkte, kunde1);
		Bestellung testbestellung3 = new Bestellung(LocalDateTime.now(), produkte, kunde3);

		// Liste von Bestellungen
		List<Bestellung> bestellungen = new ArrayList<Bestellung>();
		bestellungen.add(testbestellung3);
		bestellungen.add(testbestellung2);
		bestellungen.add(testbestellung1);

		// Warenkoerbe
		Warenkorb warenkorb3 = new Warenkorb(produkte, kunde3);
		Warenkorb warenkorb2 = new Warenkorb(produkte, kunde2);
		Warenkorb warenkorb1 = new Warenkorb(produkte, kunde1);

		// Rechnug
		Rechnung rechnung1 = new Rechnung(testbestellung1.getBetrag(), LocalDateTime.now(), testbestellung1);
		Rechnung rechnung2 = new Rechnung(testbestellung2.getBetrag(), LocalDateTime.now(), testbestellung2);
		Rechnung rechnung3 = new Rechnung(testbestellung3.getBetrag(), LocalDateTime.now(), testbestellung3);

	}

}

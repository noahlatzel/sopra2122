package de.wwu.sopra.datenhaltung.verwaltung;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.bestellung.Rechnung;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Produkt;

public class SerialisierungPipelineTest {
	Rechnung rechnung1;

	@BeforeEach
	void reset() {
		Lager.reset();
		FahrzeugRegister.reset();

		Kunde kunde2 = new Kunde("Bierman", "1234", "hart@test.de", "Destille", "Maxi", "malvoll", "test");

		// Produkte
		Produkt cola = new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29);
		Produkt bier = new Produkt("Krombacher Pils", "Eine Perle der Natur", 0.99, 1.96);
		Produkt korn = new Produkt("Sasse Korn", "LEEEECKER", 4.20, 6.66);
		// Liste der Produkte
		List<Produkt> produkte = new ArrayList<Produkt>();
		produkte.add(bier);
		produkte.add(cola);
		produkte.add(korn);
		Bestellung testbestellung1 = new Bestellung(LocalDateTime.now(), produkte, kunde2);

		rechnung1 = new Rechnung(testbestellung1.getBetrag(), LocalDateTime.now(), testbestellung1);
	}

	@Test
	void testSerialisierung() {

		SerialisierungPipeline<Rechnung> sp = new SerialisierungPipeline<Rechnung>();
		sp.serialisieren(rechnung1, "produkt.ser");
		Rechnung temp = sp.deserialisieren("produkt.ser");
		assertTrue(rechnung1.getRechnungsnummer() == temp.getRechnungsnummer());
	}

}

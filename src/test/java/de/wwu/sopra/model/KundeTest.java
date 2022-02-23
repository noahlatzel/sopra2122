package de.wwu.sopra.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * Testklasse zur Klasse Kunde
 * @author Paul Dirksen
 *
 */
public class KundeTest {

	Kunde kunde;
	Bestellung bestellung;
	
	@BeforeEach
	public void setup() {
		kunde = new Kunde("kunde", "666", "email69", "Kassel", "UnfassbarerVorname", "EinwandfreierNachname", "KapitalistenBankverbindung");
		bestellung = new Bestellung( 1234, 3F, localDateTime, new ArrayList<Produkt>(), kunde);
	}
	
	/**
	 * Testet das Hinzufuegen einer neuen Bestellung zur Liste der Bestellungen und das Verhalten bei null-Uebergabe
	 */
	@Test
	public void bestellungHinzufuegenTest() {
		//Testen des Hinzufuegens einer Bestellung
		kunde.bestellungHinzufuegen(bestellung);
		assertTrue(kunde.getBestellungen().contains(bestellung));
		
		//Wenn Bestellung bereits enthalten ist wird sie nicht nochmal uebergeben
		int i = kunde.getBestellungen().size();
		kunde.bestellungHinzufuegen(bestellung);
		assertTrue(kunde.getBestellungen().size() == i);
		
		//Uebergeben von null erzeugt Exception
		assertThrows(IllegalArgumentException.class, () -> {
			kunde.bestellungHinzufuegen(null);
		});
	}
	
}

package de.wwu.sopra.datenhaltung.verwaltung;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.management.Lager;

public class SerialisierungPipelineTest {
	Kunde kunde2;

	@BeforeEach
	void reset() {
		Lager.reset();
		FahrzeugRegister.reset();
	}

	@Test
	void testSerialisierung() {

		SerialisierungPipeline<Integer> sp = new SerialisierungPipeline<Integer>();
		sp.serialisieren(1, "produkt.ser");
		Integer temp = sp.deserialisieren("produkt.ser");
		assertTrue(1 == temp);
	}

}

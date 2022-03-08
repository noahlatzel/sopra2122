package de.wwu.sopra.datenhaltung.verwaltung;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.management.Lager;

public class SerialisierungPipelineTest {
	Kunde kunde2;

	@AfterEach
	void cleanAfter() {
		Lager.reset();
		FahrzeugRegister.reset();
	}

	@BeforeEach
	void reset() {
		Lager.reset();
		FahrzeugRegister.reset();
	}

	@Test
	void testSerialisierung() {

		SerialisierungPipeline<Integer> sp = new SerialisierungPipeline<Integer>();
		sp.serialisieren(1, "produkt.ser");
		Integer temp = sp.deserialisieren("produkt.ser", 0);
		assertTrue(1 == temp);
	}

}

package de.wwu.sopra.datenhaltung.bestellung;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class RabattTest {

	@Test
	void neuerRabattTest() {
		Rabatt rabatt = new Rabatt("ABC", 50);

		assertEquals(rabatt.getRabattcode(), "ABC");
		assertEquals(rabatt.getProzent(), 50);

		assertThrows(AssertionError.class, () -> {
			Rabatt rabatt1 = new Rabatt("ABCD", -1);
		});

		assertThrows(AssertionError.class, () -> {
			Rabatt rabatt2 = new Rabatt("ABCD", 101);
		});
	}
}

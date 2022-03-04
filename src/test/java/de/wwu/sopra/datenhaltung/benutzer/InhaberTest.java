package de.wwu.sopra.datenhaltung.benutzer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.verwaltung.BenutzerRegister;

/**
 * Testklasse zur Klasse Inhaber
 * 
 * @author Paul Dirksen
 *
 */
public class InhaberTest {

	Inhaber inhaber;
	Fahrer fahrer;
	Lagerist lagerist;

	@BeforeEach
	public void setup() {
		BenutzerRegister.getBenutzerListe().clear();
		inhaber = new Inhaber("admin", "1234", "email", "Muenster", "SuperVorname", "KlasseNachname",
				"eineBankverbindung");
		fahrer = new Fahrer("fahrer", "4321", "email2", "Dortmund", "KrasserVorname", "CoolerNachname",
				"nochEineBankverbindung", inhaber);
		lagerist = new Lagerist("lagerist", "4321", "email2", "Dortmund", "KrasserVorname", "CoolerNachname",
				"nochEineBankverbindung", inhaber);
	}

	/**
	 * Test ob das BenutzerRegister richtig Kopiert wird
	 */
	@Test
	public void testInhaber() {
		assertTrue(inhaber.getFahrer().size() == 0);
		assertTrue(inhaber.getLageristen().size() == 0);
		Inhaber inhaber2;
		BenutzerRegister.benutzerHinzufuegen(new Fahrer("fahrer", "4321", "email2", "Dortmund", "KrasserVorname",
				"CoolerNachname", "nochEineBankverbindung", inhaber));
		BenutzerRegister.benutzerHinzufuegen(new Lagerist("lagerist", "4321", "email2", "Dortmund", "KrasserVorname",
				"CoolerNachname", "nochEineBankverbindung", inhaber));
		inhaber2 = new Inhaber("admin", "1234", "email", "Muenster", "SuperVorname", "KlasseNachname",
				"eineBankverbindung");
		assertTrue(inhaber2.getFahrer().size() == 1);
		assertTrue(inhaber2.getLageristen().size() == 1);
	}

	/**
	 * Test ob Fahrer hinzugefuegt wird, was passiert wenn der Fahrer schon
	 * hinzugefuegt worden ist und was bei einer null-Uebergabe passiert.
	 */
	@Test
	public void fahrerHinzufuegenTest() {
		// Fahrer wird hinzugefuegt und soll danach in der Liste der Fahrer sein
		inhaber.fahrerHinzufuegen(fahrer);
		assertTrue(inhaber.getFahrer().contains(fahrer));

		// Fahrer wird kein zweites Mal hinzugefuegt
		int i = inhaber.getFahrer().size();
		inhaber.fahrerHinzufuegen(fahrer);
		assertTrue(inhaber.getFahrer().size() == i);
		assertTrue(inhaber.getFahrer().contains(fahrer));

		// Wenn null uebergeben wird, wird eine Exception geworfen
		assertThrows(NullPointerException.class, () -> {
			inhaber.fahrerHinzufuegen(null);
		});
	}

	/**
	 * Test ob Fahrer aus leerer Liste entfernt wird, was passiert wenn er nicht
	 * enthalten war und was bei einer null-Uebergabe passiert.
	 */
	@Test
	public void fahrerEntfernenTest() {
		// Fahrer wird entfernt, obwohl er nicht in der Liste war

		assertThrows(IllegalArgumentException.class, () -> {
			inhaber.fahrerEntfernen(fahrer);
		});
		assertFalse(inhaber.getFahrer().contains(fahrer));

		inhaber.getFahrer().add(fahrer);
		int i = inhaber.getFahrer().size();

		// Fahrer wird entfernt
		inhaber.fahrerEntfernen(fahrer);
		assertFalse(inhaber.getFahrer().contains(fahrer));
		assertTrue(inhaber.getFahrer().size() == i - 1);

		// null-Uebergabe
		assertThrows(NullPointerException.class, () -> {
			inhaber.fahrerEntfernen(null);
		});
	}

	/**
	 * Test ob Lagerist hinzugefuegt wird, was passiert wenn der Lagerist schon
	 * hinzugefuegt worden ist und was bei einer null-Uebergabe passiert.
	 */
	@Test
	public void lageristHinzufuegenTest() {
		// Lagerist wird hinzugefuegt und soll danach in der Liste der Fahrer sein
		inhaber.lageristHinzufuegen(lagerist);
		assertTrue(inhaber.getLageristen().contains(lagerist));

		// Lagerist wird kein zweites Mal hinzugefuegt
		int i = inhaber.getLageristen().size();
		inhaber.lageristHinzufuegen(lagerist);
		assertTrue(inhaber.getLageristen().size() == i);
		assertTrue(inhaber.getLageristen().contains(lagerist));

		// Wenn null uebergeben wird, wird eine Exception geworfen
		assertThrows(NullPointerException.class, () -> {
			inhaber.lageristHinzufuegen(null);
		});
	}

	/**
	 * Test ob Lagerist aus leerer Liste entfernt wird, was passiert wenn er nicht
	 * enthalten war und was bei einer null-Uebergabe passiert.
	 */
	@Test
	public void lageristEntfernenTest() {
		// Lagerist wird entfernt, obwohl er nicht in der Liste war
		assertThrows(IllegalArgumentException.class, () -> {
			inhaber.lageristEntfernen(lagerist);
		});
		assertFalse(inhaber.getLageristen().contains(lagerist));

		inhaber.getLageristen().add(lagerist);
		int i = inhaber.getLageristen().size();

		// Lagerist wird entfernt
		inhaber.lageristHinzufuegen(lagerist);
		inhaber.lageristEntfernen(lagerist);
		assertFalse(inhaber.getLageristen().contains(lagerist));
		assertTrue(inhaber.getLageristen().size() == i - 1);

		// Bei null-Uebergabe soll Exception geworfen werden
		assertThrows(NullPointerException.class, () -> {
			inhaber.lageristEntfernen(null);
		});
	}

	/**
	 * Test von getLageristen() und getFahrer()
	 */
	@Test
	void testGetLageristenUndFahrer() {

		Lagerist lagerist2 = new Lagerist("almacenista", "4322", "email3", "Bochum", "Jane", "Doe", "LaBankverbindung",
				inhaber);
		Fahrer fahrer2 = new Fahrer("conductor", "4324", "email4", "Essen", "John", "Doe", "LaBankverbindung", inhaber);

		inhaber.fahrerHinzufuegen(fahrer);
		inhaber.fahrerHinzufuegen(fahrer2);

		inhaber.lageristHinzufuegen(lagerist);
		inhaber.lageristHinzufuegen(lagerist2);

		List<Lagerist> lageristenList = new ArrayList<Lagerist>();
		lageristenList.add(lagerist);
		lageristenList.add(lagerist2);
		List<Fahrer> fahrerList = new ArrayList<Fahrer>();
		fahrerList.add(fahrer);
		fahrerList.add(fahrer2);

		assertEquals(lageristenList, inhaber.getLageristen());
		assertEquals(fahrerList, inhaber.getFahrer());

	}

	/**
	 * Test fuer getRolle
	 */
	@Test
	public void testGetRolle() {
		assertTrue(inhaber.getRolle().equals(Rolle.INHABER));
	}
}

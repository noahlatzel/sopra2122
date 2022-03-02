package de.wwu.sopra.datenhaltung.benutzer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.wwu.sopra.datenhaltung.verwaltung.BenutzerDatenTripel;
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
		inhaber = new Inhaber("admin", "1234", "email", "Muenster", "SuperVorname", "KlasseNachname",
				"eineBankverbindung");
		fahrer = new Fahrer("fahrer", "4321", "email2", "Dortmund", "KrasserVorname", "CoolerNachname",
				"nochEineBankverbindung", inhaber);
		lagerist = new Lagerist("lagerist", "4321", "email2", "Dortmund", "KrasserVorname", "CoolerNachname",
				"nochEineBankverbindung", inhaber);
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
		inhaber.fahrerEntfernen(fahrer);
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
		inhaber.lageristEntfernen(lagerist);
		assertFalse(inhaber.getLageristen().contains(lagerist));

		inhaber.getLageristen().add(lagerist);
		int i = inhaber.getLageristen().size();

		// Lagerist wird entfernt
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
		List<BenutzerDatenTripel> temp = BenutzerRegister.getBenutzerListe();
		for (BenutzerDatenTripel i : temp) {
			BenutzerRegister.benutzerEntfernen(i.getBenutzer());
		}

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

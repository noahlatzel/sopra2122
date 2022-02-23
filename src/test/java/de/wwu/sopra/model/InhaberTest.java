package de.wwu.sopra.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class InhaberTest {

	Inhaber inhaber;
	Fahrer fahrer;
	Lagerist lagerist;
	
	@BeforeEach
	public void setup() {
		inhaber = new Inhaber("admin", "1234", "email", "Muenster", "SuperVorname", "KlasseNachname", "eineBankverbindung");
		fahrer = new Fahrer("fahrer", "4321", "email2", "Dortmund", "KrasserVorname", "CoolerNachname", "nochEineBankverbindung", inhaber);
		lagerist = new Lagerist("fahrer", "4321", "email2", "Dortmund", "KrasserVorname", "CoolerNachname", "nochEineBankverbindung", inhaber);
	}
	
	/**
	 * Test ob Fahrer hinzugefuegt wird, was passiert wenn der Fahrer schon hinzugefuegt worden ist
	 * und was bei einer null-Uebergabe passiert.
	 */
	@Test
	public void fahrerHinzufuegenTest() {
		//Fahrer wird hinzugefuegt und soll danach in der Liste der Fahrer sein
		inhaber.fahrerHinzufuegen(fahrer);
		assertTrue(inhaber.getFahrer().contains(fahrer));
		
		//Fahrer wird kein zweites Mal hinzugefuegt
		int i = inhaber.getFahrer().size();
		inhaber.fahrerHinzufuegen(fahrer);
		assertTrue(inhaber.getFahrer().size() == i);
		assertTrue(inhaber.getFahrer().contains(fahrer));
		
		//Wenn null uebergeben wird, wird eine Exception geworfen
		assertThrows(IllegalArgumentException.class, () -> {
			inhaber.fahrerHinzufuegen(null);
		});
	}
	
	/**
	 * Test ob Fahrer aus leerer Liste entfernt wird, was passiert wenn er nicht enthalten war und
	 * was bei einer null-Uebergabe passiert.
	 */
	@Test
	public void fahrerEntfernenTest() {
		//Fahrer wird entfernt, obwohl er nicht in der Liste war
		inhaber.fahrerEntfernen(fahrer);
		assertFalse(inhaber.getFahrer().contains(fahrer));
		
		inhaber.getFahrer().add(fahrer);
		int i = inhaber.getFahrer().size();
		
		//Fahrer wird entfernt
		inhaber.fahrerEntfernen(fahrer);
		assertFalse(inhaber.getFahrer().contains(fahrer));
		assertTrue(inhaber.getFahrer().size() == i-1);
		
		//null-Uebergabe
		assertThrows(IllegalArgumentException.class, () -> {
			inhaber.fahrerEntfernen(null);
		});
	}
	
	/**
	 * Test ob Lagerist hinzugefuegt wird, was passiert wenn der Lagerist schon hinzugefuegt worden ist
	 * und was bei einer null-Uebergabe passiert.
	 */
	@Test
	public void lageristHinzufuegenTest() {
		//Lagerist wird hinzugefuegt und soll danach in der Liste der Fahrer sein
		inhaber.lageristHinzufuegen(lagerist);
		assertTrue(inhaber.getLageristen().contains(lagerist));
		
		//Lagerist wird kein zweites Mal hinzugefuegt
		int i = inhaber.getLageristen().size();
		inhaber.lageristHinzufuegen(lagerist);
		assertTrue(inhaber.getLageristen().size() == i);
		assertTrue(inhaber.getLageristen().contains(lagerist));
		
		//Wenn null uebergeben wird, wird eine Exception geworfen
		assertThrows(IllegalArgumentException.class, () -> {
			inhaber.lageristHinzufuegen(null);
		});
	}
	
	/**
	 * Test ob Lagerist aus leerer Liste entfernt wird, was passiert wenn er nicht enthalten war und
	 * was bei einer null-Uebergabe passiert.
	 */
	@Test
	public void lageristEntfernenTest() {
		//Lagerist wird entfernt, obwohl er nicht in der Liste war
		inhaber.lageristEntfernen(lagerist);
		assertFalse(inhaber.getLageristen().contains(lagerist));
		
		inhaber.getLageristen().add(lagerist);
		int i = inhaber.getLageristen().size();
		
		//Lagerist wird entfernt
		inhaber.lageristEntfernen(lagerist);
		assertFalse(inhaber.getLageristen().contains(lagerist));
		assertTrue(inhaber.getLageristen().size() == i-1);
		
		//Bei null-Uebergabe soll Exception geworfen werden
		assertThrows(IllegalArgumentException.class, () -> {
			inhaber.lageristEntfernen(null);
		});
	}
}

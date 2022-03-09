package de.wwu.sopra.darstellung.fahrer;

import de.wwu.sopra.anwendung.mitarbeiter.Fahrersteuerung;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Oberflaeche zum anzeigen der position
 * 
 * @author Johannes Thiel
 *
 */
public class FahrzeugpositionAnzeigen extends OverviewFahrer {
	/**
	 * Erzeugt FahrzeugpositionAnzeigen
	 * 
	 * @param steuerung    FahrerSteuerung
	 * @param primaryStage PrimaryStage
	 * @param width        Breite des Fensters
	 * @param height       Hoehe des Fensters
	 */
	public FahrzeugpositionAnzeigen(Fahrersteuerung steuerung, Stage primaryStage, double width, double height) {
		super(steuerung, primaryStage, width, height);
		this.zeigePosition();
	}

	/**
	 * Position anzeigen auf Label
	 */
	private void zeigePosition() {
		try {
			root.setCenter(new Label(steuerung.positionDesFahrzeugs()));
		} catch (NullPointerException i) {
			System.out.println("Der Fahrer hat kein Fahrzeug oder keine Route");
		}
	}

}

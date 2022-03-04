package de.wwu.sopra.darstellung.kunde;

import de.wwu.sopra.anwendung.kunde.Kundensteuerung;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Darstellungsklasse fuer StartseiteKunde
 * 
 * @author Jasmin Horstknepper
 *
 */
public class StartseiteKunde extends KundeOverview {
	/**
	 * Konstruktor fuer die Startseite des Kunden
	 * 
	 * @param primaryStage    PrimaryStage
	 * @param width           Breite des Fensters
	 * @param height          Hoehe des Fensters
	 * @param kundensteuerung KundenSteuerung
	 */
	public StartseiteKunde(Stage primaryStage, double width, double height, Kundensteuerung kundensteuerung) {
		super(primaryStage, width, height, kundensteuerung);
		this.primaryStage = primaryStage;
		this.setRoot(root);
		this.kundensteuerung = kundensteuerung;
		root.setCenter(new Label("KUNDE STARTSEITE"));
	}

}

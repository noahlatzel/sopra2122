package de.wwu.sopra.darstellung.fahrer;

import de.wwu.sopra.anwendung.mitarbeiter.Fahrersteuerung;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FahrzeugpositionAnzeigen extends OverviewFahrer {

	public FahrzeugpositionAnzeigen(Fahrersteuerung steuerung, Stage primaryStage, double width, double height) {
		super(steuerung, primaryStage, width, height);
		this.zeigePosition();
	}

	public void zeigePosition() {
		try {
			root.setCenter(new Label(steuerung.positionDesFahrzeugs()));
		} catch (NullPointerException i) {
			System.out.println("Der Farhre hat kein Fahrzeug oder keine Route");
		}
	}

}

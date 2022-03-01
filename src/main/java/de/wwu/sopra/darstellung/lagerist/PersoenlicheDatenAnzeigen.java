package de.wwu.sopra.darstellung.lagerist;

import de.wwu.sopra.anwendung.mitarbeiter.Lageristensteuerung;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PersoenlicheDatenAnzeigen extends LageristOverview {

	public PersoenlicheDatenAnzeigen(Stage primaryStage, double width, double height,
			Lageristensteuerung lageristenSteuerung) {
		super(primaryStage, width, height, lageristenSteuerung);
		root.setCenter(new Label("Persoenliche Daten anzeigen..."));
	}

}

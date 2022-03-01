package de.wwu.sopra.darstellung.fahrer;

import de.wwu.sopra.anwendung.mitarbeiter.Fahrersteuerung;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * oberflaeche zum Stornieren der Bestellung, wenn der Kunde nicht da ist
 * 
 * @author Johannes Thiel
 *
 */
public class KundeNichtDa extends OverviewFahrer {

	public KundeNichtDa(Fahrersteuerung steuerung, Stage primaryStage, double width, double height) {
		super(steuerung, primaryStage, width, height);
		root.setCenter(new Label("this is Route"));
		this.nichtDA();
	}

	// button mit action stornieren
	private void nichtDA() {
		Button btStorniern = new Button("Stornieren");
		btStorniern.setOnAction(e -> {
			try {
				steuerung.kundeNichtDa();
			} catch (NullPointerException i) {
				btStorniern.setText("gerade keine Bestellung");
			}
		});
		root.setCenter(btStorniern);
	}

}

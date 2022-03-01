package de.wwu.sopra.darstellung.fahrer;

import de.wwu.sopra.anwendung.mitarbeiter.Fahrersteuerung;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class KundeNichtDa extends OverviewFahrer {

	public KundeNichtDa(Fahrersteuerung steuerung, Stage primaryStage, double width, double height) {
		super(steuerung, primaryStage, width, height);
		root.setCenter(new Label("this is Route"));
		this.nichtDA();
	}

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

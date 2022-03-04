package de.wwu.sopra.darstellung.fahrer;

import de.wwu.sopra.anwendung.mitarbeiter.Fahrersteuerung;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BestellungAbgeben extends OverviewFahrer {

	BorderPane border;
	Button btnAbgeben;

	/**
	 * Erzeugt FahrzeugAuswaehlen
	 * 
	 * @param steuerung    FahrerSteuerung
	 * @param primaryStage PrimaryStage
	 * @param width        Breite des Fensters
	 * @param height       Hoehe des Fensters
	 */
	public BestellungAbgeben(Fahrersteuerung steuerung, Stage primaryStage, double width, double height) {
		super(steuerung, primaryStage, width, height);
		root.setCenter(setContent());
	}

	private BorderPane setContent() {
		if (border == null) {
			border = new BorderPane();
			Label title = new Label("Bestellung abgeben");
			title.setStyle("-fx-font-weight: bold");
			title.setFont(new Font("Arial", 32));
			border.setTop(title);
			border.setCenter(getBestellung());
		}
		return border;
	}

	private Button getBestellung() {
		if (btnAbgeben == null) {
			btnAbgeben = new Button("Bestellung abgeben");
			btnAbgeben.setOnAction(e -> {
				try {
					steuerung.bestellungAusliefern();
				} catch (NullPointerException i) {
					border.setBottom(new Label("es sind schon alle bestellungen abgegeben worden"));
				}
			});
		}
		return btnAbgeben;
	}

}

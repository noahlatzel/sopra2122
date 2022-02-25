package de.wwu.sopra.darstellung.fahrer;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FahrzeugpositionAnzeigen extends Scene {

	BorderPane root = new BorderPane();
	Stage primaryStage;
	GridPane gridPane;
	Button btFahrzeugwahlen;
	Button btRouteAnzeigen;
	Button btFahrzeugpositionAnzeigen;
	Button btKundeNichtDa;
	Button btPersoenlicheDatenAnzeigen;
	Button btPersoenlicheDatenBearbeiten;

	public FahrzeugpositionAnzeigen(Stage primaryStage, double width, double height) {
		super(new BorderPane(), width, height);
		this.primaryStage = primaryStage;
		this.setRoot(root);
		root.setLeft(this.setGridPane());
		root.setCenter(new Label("this is FahrzeugpositionAnzeigen"));
	}

	private GridPane setGridPane() {
		if (gridPane == null) {
			gridPane = new GridPane();
			gridPane.add(setBtFahrzeugwahlen(), 1, 0);
			gridPane.add(setBtRouteAnzeigen(), 1, 1);
			gridPane.add(setBtFahrzeugpositionAnzeigen(), 1, 2);
			gridPane.add(setBtKundeNichtDa(), 1, 3);
			gridPane.add(setBtPersoenlicheDatenAnzeigen(), 1, 4);
			gridPane.add(setBtPersoenlicheDatenBearbeiten(), 1, 5);
		}
		return gridPane;
	}

	private Button setBtFahrzeugwahlen() {
		if (btFahrzeugwahlen == null) {
			btFahrzeugwahlen = new Button("Fahrzeug Auswaehlen");
			btFahrzeugwahlen.setOnAction(e -> {
				primaryStage.setScene(new FahrzeugAuswaehlen(primaryStage, getWidth(), getHeight()));
			});
		}
		return btFahrzeugwahlen;
	}

	private Button setBtRouteAnzeigen() {
		if (btRouteAnzeigen == null) {
			btRouteAnzeigen = new Button("Route Anzeigen");
			// btRouteAnzeigen.setOnAction(e -> {
			// primaryStage.setScene(new
			// FahrzeugAuswaehlen(primaryStage,getWidth(),getHeight()));

			// });
		}
		return btRouteAnzeigen;
	}

	private Button setBtFahrzeugpositionAnzeigen() {
		if (btFahrzeugpositionAnzeigen == null) {
			btFahrzeugpositionAnzeigen = new Button("Fahrzeugposition Anzeigen");
		}
		return btFahrzeugpositionAnzeigen;
	}

	private Button setBtKundeNichtDa() {
		if (btKundeNichtDa == null) {
			btKundeNichtDa = new Button("Kunde nicht da");
		}
		return btKundeNichtDa;
	}

	private Button setBtPersoenlicheDatenAnzeigen() {
		if (btPersoenlicheDatenAnzeigen == null) {
			btPersoenlicheDatenAnzeigen = new Button("Persoenliche Daten Anzeigen");
		}
		return btPersoenlicheDatenAnzeigen;
	}

	private Button setBtPersoenlicheDatenBearbeiten() {
		if (btPersoenlicheDatenBearbeiten == null) {
			btPersoenlicheDatenBearbeiten = new Button("Persoenliche Daten Bearbeiten");
		}
		return btPersoenlicheDatenBearbeiten;
	}

}

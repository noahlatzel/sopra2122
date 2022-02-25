package de.wwu.sopra.darstellung.fahrer;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FahrzeugAuswaehlen extends Scene {

	BorderPane root = new BorderPane();
	Stage primaryStage;
	VBox vbox;
	ScrollPane scrollPane = new ScrollPane();
	Button btFahrzeugwahlen;
	Button btRouteAnzeigen;
	Button btFahrzeugpositionAnzeigen;
	Button btKundeNichtDa;
	Button btPersoenlicheDatenAnzeigen;
	Button btPersoenlicheDatenBearbeiten;

	public FahrzeugAuswaehlen(Stage primaryStage, double width, double height) {
		super(new BorderPane(), width, height);
		this.primaryStage = primaryStage;
		this.setRoot(root);
		root.setLeft(this.setGridPane());
		root.setCenter(scrollPane);

	}
	// ObservableList<Fahrzeug> rezepte = FXCollections.observableArrayList(null);
	// ListView<Fahrzeug> listView = new ListView<Fahrzeug>(rezepte);

	private VBox setGridPane() {
		if (vbox == null) {
			vbox = new VBox();
			vbox.setSpacing(5);
			vbox.getChildren().add(setBtFahrzeugwahlen());
			vbox.getChildren().add(setBtRouteAnzeigen());
			vbox.getChildren().add(setBtFahrzeugpositionAnzeigen());
			vbox.getChildren().add(setBtKundeNichtDa());
			vbox.getChildren().add(setBtPersoenlicheDatenAnzeigen());
			vbox.getChildren().add(setBtPersoenlicheDatenBearbeiten());
		}
		return vbox;
	}

	private Button setBtFahrzeugwahlen() {
		if (btFahrzeugwahlen == null) {
			btFahrzeugwahlen = new Button("Fahrzeug Auswaehlen");
			btFahrzeugwahlen.setMinWidth(180);
			btFahrzeugwahlen.setOnAction(e -> {
				// primaryStage.setScene(new FahrzeugAuswaehlen(primaryStage, getWidth(),
				// getHeight()));
			});
		}
		return btFahrzeugwahlen;
	}

	private Button setBtRouteAnzeigen() {
		if (btRouteAnzeigen == null) {
			btRouteAnzeigen = new Button("Route Anzeigen");
			btRouteAnzeigen.setMinWidth(180);
			btRouteAnzeigen.setOnAction(e -> {
				primaryStage.setScene(new RouteAnzeigen(primaryStage, getWidth(), getHeight()));

			});
		}
		return btRouteAnzeigen;
	}

	private Button setBtFahrzeugpositionAnzeigen() {
		if (btFahrzeugpositionAnzeigen == null) {
			btFahrzeugpositionAnzeigen = new Button("Fahrzeugposition Anzeigen");
			btFahrzeugpositionAnzeigen.setMinWidth(180);
			btFahrzeugpositionAnzeigen.setOnAction(e -> {
				primaryStage.setScene(new FahrzeugpositionAnzeigen(primaryStage, getWidth(), getHeight()));
			});

		}
		return btFahrzeugpositionAnzeigen;
	}

	private Button setBtKundeNichtDa() {
		if (btKundeNichtDa == null) {
			btKundeNichtDa = new Button("Kunde nicht da");
			btKundeNichtDa.setMinWidth(180);
			btKundeNichtDa.setOnAction(e -> {
				primaryStage.setScene(new KundeNichtDa(primaryStage, getWidth(), getHeight()));
			});
		}
		return btKundeNichtDa;
	}

	private Button setBtPersoenlicheDatenAnzeigen() {
		if (btPersoenlicheDatenAnzeigen == null) {
			btPersoenlicheDatenAnzeigen = new Button("Persoenliche Daten Anzeigen");
			btPersoenlicheDatenAnzeigen.setMinWidth(180);
			btPersoenlicheDatenAnzeigen.setOnAction(e -> {
				primaryStage.setScene(new PersoenlicheDatenAnzeigen(primaryStage, getWidth(), getHeight()));
			});
		}
		return btPersoenlicheDatenAnzeigen;
	}

	private Button setBtPersoenlicheDatenBearbeiten() {
		if (btPersoenlicheDatenBearbeiten == null) {
			btPersoenlicheDatenBearbeiten = new Button("Persoenliche Daten Bearbeiten");
			btPersoenlicheDatenBearbeiten.setMinWidth(180);
			btPersoenlicheDatenBearbeiten.setOnAction(e -> {
				primaryStage.setScene(new PersoenlicheDatenBearbeiten(primaryStage, getWidth(), getHeight()));
			});
		}
		return btPersoenlicheDatenBearbeiten;
	}

}

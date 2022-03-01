package de.wwu.sopra.darstellung.fahrer;

import de.wwu.sopra.anwendung.mitarbeiter.Fahrersteuerung;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * basis oberflauche des Fahrers
 * 
 * @author Johannes Thiel
 *
 */
public class OverviewFahrer extends Scene {

	BorderPane root = new BorderPane();
	Stage primaryStage;
	VBox vbox;
	Fahrersteuerung steuerung;
	Button btFahrzeugwahlen;
	Button btRouteAnzeigen;
	Button btFahrzeugpositionAnzeigen;
	Button btKundeNichtDa;
	Button btPersoenlicheDatenAnzeigen;
	Button btPersoenlicheDatenBearbeiten;

	public OverviewFahrer(Fahrersteuerung steuerung, Stage primaryStage, double width, double height) {
		super(new BorderPane(), width, height);
		this.primaryStage = primaryStage;
		this.setRoot(root);
		this.steuerung = steuerung;
		root.setLeft(this.setGridPane());
	}

	// gridpane ist links gestezt
	private VBox setGridPane() {
		if (vbox == null) {
			vbox = new VBox();
			vbox.setSpacing(5);

			// buttons aufrufen
			vbox.getChildren().add(setBtFahrzeugwahlen());
			vbox.getChildren().add(setBtRouteAnzeigen());
			vbox.getChildren().add(setBtFahrzeugpositionAnzeigen());
			vbox.getChildren().add(setBtKundeNichtDa());
			vbox.getChildren().add(setBtPersoenlicheDatenAnzeigen());
			vbox.getChildren().add(setBtPersoenlicheDatenBearbeiten());
		}
		return vbox;
	}

	// buttons und actions erstellen
	// bt zum Fahrzeugwaehlen
	private Button setBtFahrzeugwahlen() {
		if (btFahrzeugwahlen == null) {
			btFahrzeugwahlen = new Button("Fahrzeug Auswaehlen");
			btFahrzeugwahlen.setMinWidth(180);
			btFahrzeugwahlen.setOnAction(e -> {
				primaryStage.setScene(new FahrzeugAuswaehlen(steuerung, primaryStage, getWidth(), getHeight()));
			});
		}
		return btFahrzeugwahlen;
	}

	// bt zum Route Anzeigen
	private Button setBtRouteAnzeigen() {
		if (btRouteAnzeigen == null) {
			btRouteAnzeigen = new Button("Route Anzeigen");
			btRouteAnzeigen.setMinWidth(180);
			btRouteAnzeigen.setOnAction(e -> {
				primaryStage.setScene(new RouteAnzeigen(steuerung, primaryStage, getWidth(), getHeight()));

			});
		}
		return btRouteAnzeigen;
	}

	// bt zum Fahrzeuganzeigen
	private Button setBtFahrzeugpositionAnzeigen() {
		if (btFahrzeugpositionAnzeigen == null) {
			btFahrzeugpositionAnzeigen = new Button("Fahrzeugposition Anzeigen");
			btFahrzeugpositionAnzeigen.setMinWidth(180);
			btFahrzeugpositionAnzeigen.setOnAction(e -> {
				primaryStage.setScene(new FahrzeugpositionAnzeigen(steuerung, primaryStage, getWidth(), getHeight()));
			});

		}
		return btFahrzeugpositionAnzeigen;
	}

	// bt zu kunde ist nicht da
	private Button setBtKundeNichtDa() {
		if (btKundeNichtDa == null) {
			btKundeNichtDa = new Button("Kunde nicht da");
			btKundeNichtDa.setMinWidth(180);
			btKundeNichtDa.setOnAction(e -> {
				primaryStage.setScene(new KundeNichtDa(steuerung, primaryStage, getWidth(), getHeight()));
			});
		}
		return btKundeNichtDa;
	}

	// bt persoenliche Daten Anzeigen
	private Button setBtPersoenlicheDatenAnzeigen() {
		if (btPersoenlicheDatenAnzeigen == null) {
			btPersoenlicheDatenAnzeigen = new Button("Persoenliche Daten Anzeigen");
			btPersoenlicheDatenAnzeigen.setMinWidth(180);
			btPersoenlicheDatenAnzeigen.setOnAction(e -> {
				primaryStage.setScene(new PersoenlicheDatenAnzeigen(steuerung, primaryStage, getWidth(), getHeight()));
			});
		}
		return btPersoenlicheDatenAnzeigen;
	}

	// bt Persoenliche Daten Bearbeiten
	private Button setBtPersoenlicheDatenBearbeiten() {
		if (btPersoenlicheDatenBearbeiten == null) {
			btPersoenlicheDatenBearbeiten = new Button("Persoenliche Daten Bearbeiten");
			btPersoenlicheDatenBearbeiten.setMinWidth(180);
			btPersoenlicheDatenBearbeiten.setOnAction(e -> {
				primaryStage
						.setScene(new PersoenlicheDatenBearbeiten(steuerung, primaryStage, getWidth(), getHeight()));
			});
		}
		return btPersoenlicheDatenBearbeiten;
	}

}

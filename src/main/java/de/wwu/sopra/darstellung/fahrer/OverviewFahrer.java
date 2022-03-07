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
	Button btBestellungAbgeben1;
	Button btLieferungabschliesen;
	Button btPersoenlicheDatenAnzeigen;
	Button btPersoenlicheDatenBearbeiten;

	/**
	 * Erzeugt OverviewFahrer
	 * 
	 * @param steuerung    FahrerSteuerung
	 * @param primaryStage PrimaryStage
	 * @param width        Breite des Fensters
	 * @param height       Hoehe des Fensters
	 */
	public OverviewFahrer(Fahrersteuerung steuerung, Stage primaryStage, double width, double height) {
		super(new BorderPane(), width, height);
		this.primaryStage = primaryStage;
		this.setRoot(root);
		this.steuerung = steuerung;
		root.setLeft(this.setGridPane());
	}

	/**
	 * Erzeugt VBox
	 * 
	 * @return VBox
	 */
	private VBox setGridPane() {
		if (vbox == null) {
			vbox = new VBox();
			vbox.setSpacing(5);

			// buttons aufrufen
			vbox.getChildren().add(setBtFahrzeugwahlen());
			vbox.getChildren().add(setBtRouteAnzeigen());
			vbox.getChildren().add(setBtFahrzeugpositionAnzeigen());
			vbox.getChildren().add(setBtKundeNichtDa());
			vbox.getChildren().add(setBestellungAbgeben1());
			vbox.getChildren().add(setLieferungabschielsen());
			vbox.getChildren().add(setBtPersoenlicheDatenAnzeigen());
			vbox.getChildren().add(setBtPersoenlicheDatenBearbeiten());
		}
		return vbox;
	}

	/**
	 * Erzeugt Button zum Fahrzeugwaehlen
	 * 
	 * @return Button zum Fahrzeugwaehlen
	 */
	private Button setBtFahrzeugwahlen() {
		if (btFahrzeugwahlen == null) {
			btFahrzeugwahlen = new Button("Fahrzeug Auswaehlen");
			btFahrzeugwahlen.setMinWidth(200);
			btFahrzeugwahlen.setOnAction(e -> {
				primaryStage.setScene(new FahrzeugAuswaehlen(steuerung, primaryStage, getWidth(), getHeight()));
			});
		}
		return btFahrzeugwahlen;
	}

	/**
	 * Erzeugt Button zum RouteAnzeigen
	 * 
	 * @return Button zum RouteAnzeigen
	 */
	private Button setBtRouteAnzeigen() {
		if (btRouteAnzeigen == null) {
			btRouteAnzeigen = new Button("Route Anzeigen");
			btRouteAnzeigen.setMinWidth(200);
			btRouteAnzeigen.setOnAction(e -> {
				primaryStage.setScene(new RouteAnzeigen(steuerung, primaryStage, getWidth(), getHeight()));

			});
		}
		return btRouteAnzeigen;
	}

	/**
	 * Erzeugt Button fuer FahrzeugpositionAnzeigen
	 * 
	 * @return Button fuer FahrzeugpositionAnzeigen
	 */
	private Button setBtFahrzeugpositionAnzeigen() {
		if (btFahrzeugpositionAnzeigen == null) {
			btFahrzeugpositionAnzeigen = new Button("Fahrzeugposition Anzeigen");
			btFahrzeugpositionAnzeigen.setMinWidth(200);
			btFahrzeugpositionAnzeigen.setOnAction(e -> {
				primaryStage.setScene(new FahrzeugpositionAnzeigen(steuerung, primaryStage, getWidth(), getHeight()));
			});

		}
		return btFahrzeugpositionAnzeigen;
	}

	/**
	 * Erzeugt Button fuer KundeNichtDa
	 * 
	 * @return Button fuer KundeNichtDa
	 */
	private Button setBtKundeNichtDa() {
		if (btKundeNichtDa == null) {
			btKundeNichtDa = new Button("Kunde nicht da");
			btKundeNichtDa.setMinWidth(200);
			btKundeNichtDa.setOnAction(e -> {
				primaryStage.setScene(new KundeNichtDa(steuerung, primaryStage, getWidth(), getHeight()));
			});
		}
		return btKundeNichtDa;
	}

	private Button setBestellungAbgeben1() {
		if (btBestellungAbgeben1 == null) {
			btBestellungAbgeben1 = new Button("Bestellung abgeben");
			btBestellungAbgeben1.setMinWidth(200);
			btBestellungAbgeben1.setOnAction(e -> {
				primaryStage.setScene(new BestellungAbgeben(steuerung, primaryStage, getWidth(), getHeight()));
			});
		}
		return btBestellungAbgeben1;
	}

	private Button setLieferungabschielsen() {
		if (btLieferungabschliesen == null) {
			btLieferungabschliesen = new Button("Lieferung abschliesen");
			btLieferungabschliesen.setMinWidth(200);
			btLieferungabschliesen.setOnAction(e -> {
				primaryStage.setScene(new LieferungAbschlisen(steuerung, primaryStage, getWidth(), getHeight()));
			});
		}
		return btLieferungabschliesen;
	}

	/**
	 * Erzeugt Button fuer PersoenlicheDatenAnzeigen
	 * 
	 * @return Button fuer PersoenlicheDatenAnzeigen
	 */
	private Button setBtPersoenlicheDatenAnzeigen() {
		if (btPersoenlicheDatenAnzeigen == null) {
			btPersoenlicheDatenAnzeigen = new Button("Persoenliche Daten Anzeigen");
			btPersoenlicheDatenAnzeigen.setMinWidth(200);
			btPersoenlicheDatenAnzeigen.setOnAction(e -> {
				primaryStage.setScene(new PersoenlicheDatenAnzeigen(steuerung, primaryStage, getWidth(), getHeight()));
			});
		}
		return btPersoenlicheDatenAnzeigen;
	}

	/**
	 * Erzeugt Button fuer PersoenlicheDatenBearbeiten
	 * 
	 * @return Button fuer PersoenlicheDatenBearbeiten
	 */
	private Button setBtPersoenlicheDatenBearbeiten() {
		if (btPersoenlicheDatenBearbeiten == null) {
			btPersoenlicheDatenBearbeiten = new Button("Persoenliche Daten Bearbeiten");
			btPersoenlicheDatenBearbeiten.setMinWidth(200);
			btPersoenlicheDatenBearbeiten.setOnAction(e -> {
				primaryStage
						.setScene(new PersoenlicheDatenBearbeiten(steuerung, primaryStage, getWidth(), getHeight()));
			});
		}
		return btPersoenlicheDatenBearbeiten;
	}

}

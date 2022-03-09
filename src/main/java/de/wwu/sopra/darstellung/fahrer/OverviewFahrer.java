package de.wwu.sopra.darstellung.fahrer;

import java.io.File;

import de.wwu.sopra.anwendung.mitarbeiter.Fahrersteuerung;
import de.wwu.sopra.darstellung.anmeldung.Startseite;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * basis oberflauche des Fahrers
 * 
 * @author Johannes Thiel
 *
 */
public class OverviewFahrer extends Scene {

	BorderPane root = new BorderPane();
	BorderPane header;
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
	// Button btAbmelden;
	MenuButton userMenu;
	MenuItem btAbmelden;

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

		File f = new File("resources/stylesheet.css");
		this.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

		this.primaryStage = primaryStage;
		this.steuerung = steuerung;
		root.setTop(this.setHeader());
		root.setLeft(this.setVBox());
		this.setRoot(root);
	}

	/**
	 * Erzeugt VBox
	 * 
	 * @return VBox
	 */
	private VBox setVBox() {
		if (vbox == null) {
			vbox = new VBox();

			// buttons aufrufen
			vbox.getChildren().add(setBtFahrzeugwahlen());
			vbox.getChildren().add(setBtRouteAnzeigen());
			vbox.getChildren().add(setBtFahrzeugpositionAnzeigen());
			vbox.getChildren().add(setBtKundeNichtDa());
			vbox.getChildren().add(setBestellungAbgeben1());
			vbox.getChildren().add(setLieferungabschielsen());
			vbox.getChildren().add(setBtPersoenlicheDatenAnzeigen());
			vbox.getChildren().add(setBtPersoenlicheDatenBearbeiten());
			vbox.getStyleClass().add("mitarbeiter-sidemenu-wrapper");
		}
		return vbox;
	}

	/**
	 * Erzeugt Header
	 * 
	 * @return Header
	 */
	private BorderPane setHeader() {
		// Main Header, konstant
		if (this.header == null) {
			header = new BorderPane();
			header.getStyleClass().add("mitarbeiter-header");
			// Left side
			Label logoLabel = new Label("Logo");
			logoLabel.setTextFill(Color.web("#000000"));
			header.setLeft(logoLabel);

			// Right side
			header.setRight(this.setMenuButton());
		}

		return this.header;
	}

	/**
	 * Erstellung eines MenuButton fuer den Benutzer
	 * 
	 * @return userMenu Button, der die Option zum Abmelden anzeigt
	 */
	private MenuButton setMenuButton() {
		if (userMenu == null) {
			MenuItem abmeldenOption = new MenuItem("Abmelden");
			abmeldenOption.setOnAction(e -> {
				primaryStage.setScene(new Startseite(primaryStage, getWidth(), getHeight()));
			});
			abmeldenOption.getStyleClass().add("mitarbeiter-menu-item");
			userMenu = new MenuButton("", null, abmeldenOption);
			userMenu.getStyleClass().add("mitarbeiter-menu");

			ImageView view = new ImageView(getClass().getResource("user-icon.png").toExternalForm());
			view.setFitWidth(35);
			view.setFitHeight(35);
			userMenu.setGraphic(view);
		}
		return this.userMenu;
	}

	/**
	 * Erzeugt Button zum Fahrzeugwaehlen
	 * 
	 * @return Button zum Fahrzeugwaehlen
	 */
	private Button setBtFahrzeugwahlen() {
		if (btFahrzeugwahlen == null) {
			btFahrzeugwahlen = new Button("Fahrzeug Auswaehlen");
			btFahrzeugwahlen.getStyleClass().add("mitarbeiter-sidemenu-button");
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
			btRouteAnzeigen.getStyleClass().add("mitarbeiter-sidemenu-button");
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
			btFahrzeugpositionAnzeigen.getStyleClass().add("mitarbeiter-sidemenu-button");
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
			btKundeNichtDa.getStyleClass().add("mitarbeiter-sidemenu-button");
			btKundeNichtDa.setOnAction(e -> {
				primaryStage.setScene(new KundeNichtDa(steuerung, primaryStage, getWidth(), getHeight()));
			});
		}
		return btKundeNichtDa;
	}

	private Button setBestellungAbgeben1() {
		if (btBestellungAbgeben1 == null) {
			btBestellungAbgeben1 = new Button("Bestellung abgeben");
			btBestellungAbgeben1.getStyleClass().add("mitarbeiter-sidemenu-button");
			btBestellungAbgeben1.setOnAction(e -> {
				primaryStage.setScene(new BestellungAbgeben(steuerung, primaryStage, getWidth(), getHeight()));
			});
		}
		return btBestellungAbgeben1;
	}

	private Button setLieferungabschielsen() {
		if (btLieferungabschliesen == null) {
			btLieferungabschliesen = new Button("Lieferung abschliesen");
			btLieferungabschliesen.getStyleClass().add("mitarbeiter-sidemenu-button");
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
			btPersoenlicheDatenAnzeigen.getStyleClass().add("mitarbeiter-sidemenu-button");
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
			btPersoenlicheDatenBearbeiten.getStyleClass().add("mitarbeiter-sidemenu-button");
			btPersoenlicheDatenBearbeiten.setOnAction(e -> {
				primaryStage
						.setScene(new PersoenlicheDatenBearbeiten(steuerung, primaryStage, getWidth(), getHeight()));
			});
		}
		return btPersoenlicheDatenBearbeiten;
	}
}

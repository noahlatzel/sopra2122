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
	Button btRouteAnzeigen;
	Button btPersoenlicheDatenAnzeigen;
	Button btAlternative;
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

		this.primaryStage = primaryStage;
		this.steuerung = steuerung;
		root.setTop(this.setHeader());
		root.setLeft(this.setVBox());
		this.setRoot(root);
		
		File f = new File("resources/stylesheet.css");
		this.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
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
			vbox.getChildren().add(setBtPersoenlicheDatenAnzeigen());
			vbox.getChildren().add(setBtRouteAnzeigen());
			vbox.getChildren().add(setBtAlternative());
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
	 * Erzeugt Button fuer PersoenlicheDatenAnzeigen
	 * 
	 * @return Button fuer PersoenlicheDatenAnzeigen
	 */
	private Button setBtAlternative() {
		if (btAlternative == null) {
			btAlternative = new Button("Route abarbeiten");
			btAlternative.getStyleClass().add("mitarbeiter-sidemenu-button");
			btAlternative.setOnAction(e -> {
				primaryStage.setScene(new FahrerInZustellung(steuerung, primaryStage, getWidth(), getHeight()));
			});
		}
		return btAlternative;
	}

}

package de.wwu.sopra.darstellung.lagerist;

import java.io.File;

import de.wwu.sopra.anwendung.mitarbeiter.Lageristensteuerung;
import de.wwu.sopra.darstellung.anmeldung.Startseite;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * GUI Klasse fuer LageristOverview. Die anderen Lageristen GUIs erben von
 * dieser Klasse.
 * 
 * @author Noah Latzel
 */
public class LageristOverview extends Scene {
	BorderPane root = new BorderPane();
	BorderPane header;
	Stage primaryStage;
	Image image;
	ImageView logo;
	VBox vbox;
	Button btRoutePlanen;
	Button btBestelleNach;
	Button btPersDatenAnzeigen;
	Button btPersDatenBearbeiten;
	Button btZeigeRouteVonFahrzeug;
	Lageristensteuerung lageristenSteuerung;
	MenuButton userMenu;
	MenuItem btAbmelden;

	/**
	 * Color constant fuer Button-Background
	 */
	protected static final String STANDARD_BUTTON_STYLE = "-fx-background-color: #FF6868;";
	/**
	 * Color constant fuer Button-Background
	 */
	protected static final String HOVERED_BUTTON_STYLE = "-fx-background-color: #C14343;";

	/**
	 * Erzeugt das Fenster fuer den Lageristen
	 * 
	 * @param primaryStage        PrimaryStage
	 * @param width               Breite des Fensters
	 * @param height              Hoehe des Fensters
	 * @param lageristenSteuerung LageristenSteuerung
	 */
	public LageristOverview(Stage primaryStage, double width, double height, Lageristensteuerung lageristenSteuerung) {
		super(new BorderPane(), width, height);

		File f = new File("resources/stylesheet.css");
		this.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

		this.primaryStage = primaryStage;
		this.lageristenSteuerung = lageristenSteuerung;

		this.setRoot(root);

		root.setTop(this.setHeader());
		root.setLeft(this.setVBox());
		root.setCenter(new Label("Startseite..."));

	}

	/**
	 * Erzeugt die Ansicht aller Buttons
	 * 
	 * @return Ansicht aller Buttons
	 */
	private VBox setVBox() {
		if (this.vbox == null) {
			vbox = new VBox();
			vbox.getChildren().add(this.setBtRoutePlanen());
			vbox.getChildren().add(this.setBtBestelleNach());
			vbox.getChildren().add(this.setBtPersDatenAnzeigen());
			vbox.getChildren().add(this.setBtZeigeRouteVonFahrzeug());
			vbox.getStyleClass().add("mitarbeiter-sidemenu-wrapper");
		}
		return this.vbox;
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
	 * Erzeugt die Ansicht fuer RoutePlanen
	 * 
	 * @return Ansicht fuer Route Planen
	 */
	private Button setBtRoutePlanen() {
		if (this.btRoutePlanen == null) {
			btRoutePlanen = new Button("Route planen");
			btRoutePlanen.getStyleClass().add("mitarbeiter-sidemenu-button");
			btRoutePlanen.setOnAction(a -> {
				primaryStage.setScene(new RoutePlanen(primaryStage, getWidth(), getHeight(), lageristenSteuerung));
			});
		}
		return this.btRoutePlanen;
	}

	/**
	 * Erzeugt Button fuer BestelleNach
	 * 
	 * @return Button fuer BestelleNach
	 */
	private Button setBtBestelleNach() {
		if (this.btBestelleNach == null) {
			btBestelleNach = new Button("Nachbestellen");
			btBestelleNach.getStyleClass().add("mitarbeiter-sidemenu-button");
			btBestelleNach.setOnAction(a -> {
				primaryStage.setScene(new BestelleNach(primaryStage, getWidth(), getHeight(), lageristenSteuerung));
			});
		}
		return this.btBestelleNach;
	}

	/**
	 * Erzeugt Button fuer persoenlicheDatenAnzeigen
	 * 
	 * @return Button fuer persoenlicheDatenAnzeigen
	 */
	private Button setBtPersDatenAnzeigen() {
		if (this.btPersDatenAnzeigen == null) {
			btPersDatenAnzeigen = new Button("Persoenliche Daten");
			btPersDatenAnzeigen.getStyleClass().add("mitarbeiter-sidemenu-button");
			btPersDatenAnzeigen.setOnAction(a -> {
				primaryStage.setScene(
						new PersoenlicheDatenAnzeigen(primaryStage, getWidth(), getHeight(), lageristenSteuerung));
			});
		}
		return this.btPersDatenAnzeigen;
	}

	/**
	 * Erzeugt Button fuer ZeigeRouteVonFahrzeug
	 * 
	 * @return Button fuer ZeigeRouteVonFahrzeug
	 */
	private Button setBtZeigeRouteVonFahrzeug() {
		if (this.btZeigeRouteVonFahrzeug == null) {
			btZeigeRouteVonFahrzeug = new Button("Routen anzeigen");
			btZeigeRouteVonFahrzeug.getStyleClass().add("mitarbeiter-sidemenu-button");
			btZeigeRouteVonFahrzeug.setOnAction(a -> {
				primaryStage.setScene(
						new ZeigeRouteVonFahrzeug(primaryStage, getWidth(), getHeight(), lageristenSteuerung));
			});
		}
		return this.btZeigeRouteVonFahrzeug;
	}

}
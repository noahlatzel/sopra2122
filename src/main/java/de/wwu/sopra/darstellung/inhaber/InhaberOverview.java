/**
 * 
 */
package de.wwu.sopra.darstellung.inhaber;

import java.io.File;

import de.wwu.sopra.anwendung.mitarbeiter.Inhabersteuerung;
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
 * @author Valeria Vassallo
 *
 */
public class InhaberOverview extends Scene {
	// Erstellung von Variablen
	BorderPane root = new BorderPane();
	Stage primaryStage;
	VBox vbox;
	BorderPane header;
	Button btStatistiken;
	Button btMitarbeiterRegistrieren;
	Button btMitarbeiterVerwalten;
	Button btSortimentBearbeiten;
	Button btFahrzeugdatenAendern;
	Button btPersoenlicheDatenAnzeigen;
	Button btPersoenlicheDatenBearbeiten;
	Button btProduktHinzufuegen;
	Button btKategorienVerwaltung;
	Inhabersteuerung inhaberSteuerung;
	MenuButton userMenu;
	MenuItem btAbmelden;


	/**
	 * Zeigt die Overview fuer den Inhaber
	 * 
	 * @param primaryStage     PrimaryStage
	 * @param width            Breite des Fensters
	 * @param height           Hoehe des Fensters
	 * @param inhaberSteuerung InhaberSteuerung
	 */
	public InhaberOverview(Stage primaryStage, double width, double height, Inhabersteuerung inhaberSteuerung) {
		super(new BorderPane(), width, height);

		// Stylesheet Import
		File f = new File("resources/stylesheet.css");
		this.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

		this.primaryStage = primaryStage;
		this.setRoot(root);
		this.inhaberSteuerung = inhaberSteuerung;
		root.setTop(this.setHeader());
		root.setLeft(this.setVBox());
		root.setCenter(new Label("INHABER STARTSEITE"));
	}

	/**
	 * Erzeugt VBox mit allen Buttons
	 * 
	 * @return VBox mit allen Buttons
	 */
	private VBox setVBox() {
		// Erstellung von SideBar mit allen Buttons
		if (this.vbox == null) {
			vbox = new VBox();
			vbox.getChildren().add(this.setBtStatistiken());
			vbox.getChildren().add(this.setBtMitarbeiterRegistrieren());
			vbox.getChildren().add(this.setBtMitarbeiterVerwalten());
			vbox.getChildren().add(this.setBtSortimentBearbeiten());
			vbox.getChildren().add(this.setBtProduktHinzufuegen());
			vbox.getChildren().add(this.setBtKategorienVerwaltung());
			vbox.getChildren().add(this.setBtFahrzeugdatenAendern());
			vbox.getChildren().add(this.setBtPersoenlicheDatenBearbeiten());
			vbox.getChildren().add(this.setBtPersoenlicheDatenAnzeigen());
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
	 * @return userMenu		Button, der die Option zum Abmelden anzeigt
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


	// Erstellungen von Buttons, die auf andere Websites weiterleiten
	/**
	 * Erzeugt Button fuer Statistiken
	 * 
	 * @return Button fuer Statistiken
	 */
	private Button setBtStatistiken() {
		if (this.btStatistiken == null) {
			btStatistiken = new Button("Statistiken");
			btStatistiken.getStyleClass().add("mitarbeiter-sidemenu-button");
			btStatistiken.setOnAction(action -> {
				primaryStage.setScene(new StatistikenAnzeigen(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
			});
		}

		return this.btStatistiken;
	}

	/**
	 * Erzeugt Button fuer MitarbeiterRegistrieren
	 * 
	 * @return Button fuer MitarbeiterRegistrieren
	 */
	private Button setBtMitarbeiterRegistrieren() {
		if (this.btMitarbeiterRegistrieren == null) {
			btMitarbeiterRegistrieren = new Button("Mitarbeiter Registrieren");
			btMitarbeiterRegistrieren.getStyleClass().add("mitarbeiter-sidemenu-button");
			btMitarbeiterRegistrieren.setOnAction(action -> {
				primaryStage
						.setScene(new MitarbeiterRegistrieren(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
			});
		}

		return this.btMitarbeiterRegistrieren;
	}

	/**
	 * Erzeugt Button fuer MitarbeiterVerwalten
	 * 
	 * @return Button fuer MitarbeiterVerwalten
	 */
	private Button setBtMitarbeiterVerwalten() {
		if (this.btMitarbeiterVerwalten == null) {
			btMitarbeiterVerwalten = new Button("Mitarbeiter Verwalten");
			btMitarbeiterVerwalten.getStyleClass().add("mitarbeiter-sidemenu-button");
			btMitarbeiterVerwalten.setOnAction(action -> {
				primaryStage
						.setScene(new MitarbeiterVerwalten(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
			});
		}

		return this.btMitarbeiterVerwalten;
	}

	/**
	 * Erzeugt Button fuer SortimentBearbeiten
	 * 
	 * @return Button fuer SortimentBearbeiten
	 */
	private Button setBtSortimentBearbeiten() {
		if (this.btSortimentBearbeiten == null) {
			btSortimentBearbeiten = new Button("Sortiment Bearbeiten");
			btSortimentBearbeiten.getStyleClass().add("mitarbeiter-sidemenu-button");
			btSortimentBearbeiten.setOnAction(action -> {
				primaryStage.setScene(new SortimentBearbeiten(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
			});
		}

		return this.btSortimentBearbeiten;
	}

	/**
	 * Erzeugt Button fuer FahrzeugdatenAendern
	 * 
	 * @return Button fuer FahrzeugdatenAendern
	 */
	private Button setBtFahrzeugdatenAendern() {
		if (this.btFahrzeugdatenAendern == null) {
			btFahrzeugdatenAendern = new Button("Fahrzeugdaten Aendern");
			btFahrzeugdatenAendern.getStyleClass().add("mitarbeiter-sidemenu-button");
			btFahrzeugdatenAendern.setOnAction(action -> {
				primaryStage
						.setScene(new FahrzeugdatenAendern(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
			});
		}

		return this.btFahrzeugdatenAendern;
	}

	/**
	 * Erzeugt Button fuer PersoenlicheDatenBearbeiten
	 * 
	 * @return Button fuer PersoenlicheDatenBearbeiten
	 */
	private Button setBtPersoenlicheDatenBearbeiten() {
		if (this.btPersoenlicheDatenBearbeiten == null) {
			btPersoenlicheDatenBearbeiten = new Button("Persoenliche Daten Bearbeiten");
			btPersoenlicheDatenBearbeiten.getStyleClass().add("mitarbeiter-sidemenu-button");
			btPersoenlicheDatenBearbeiten.setOnAction(action -> {
				primaryStage.setScene(
						new PersoenlicheDatenBearbeiten(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
			});
		}

		return this.btPersoenlicheDatenBearbeiten;
	}

	/**
	 * Erzeugt Button fuer PersoenlicheDatenAnzeigen
	 * 
	 * @return Button fuer PersoenlicheDatenAnzeigen
	 */
	private Button setBtPersoenlicheDatenAnzeigen() {
		if (this.btPersoenlicheDatenAnzeigen == null) {
			btPersoenlicheDatenAnzeigen = new Button("Persoenliche Daten Anzeigen");
			btPersoenlicheDatenAnzeigen.getStyleClass().add("mitarbeiter-sidemenu-button");
			btPersoenlicheDatenAnzeigen.setOnAction(action -> {
				primaryStage.setScene(
						new PersoenlicheDatenAnzeigen(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
			});
		}

		return this.btPersoenlicheDatenAnzeigen;
	}

	/**
	 * 
	 * Erzeugt Button fuer KategorienVerwaltung
	 * 
	 * @return Button fuer KategorienVerwaltung
	 */
	private Button setBtKategorienVerwaltung() {
		if (this.btKategorienVerwaltung == null) {
			btKategorienVerwaltung = new Button("Kategorien Verwaltung");
			btKategorienVerwaltung.getStyleClass().add("mitarbeiter-sidemenu-button");
			btKategorienVerwaltung.setOnAction(e -> {
				primaryStage
						.setScene(new KategorienVerwaltung(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
			});
		}
		return this.btKategorienVerwaltung;
	}
	
	/**
	 * 
	 * Erzeugt Button fuer ProduktHinzufuegen
	 * 
	 * @return Button fuer ProduktHinzufuegen
	 */
	private Button setBtProduktHinzufuegen() {
		if (this.btProduktHinzufuegen == null) {
			btProduktHinzufuegen = new Button("Produkt Hinzufuegen");
			btProduktHinzufuegen.getStyleClass().add("mitarbeiter-sidemenu-button");
			btProduktHinzufuegen.setOnAction(e -> {
				primaryStage
						.setScene(new ProduktHinzufuegen(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
			});
		}
		return this.btProduktHinzufuegen;
	}
}

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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
	Button btProduktKategorieHinzu;
	Inhabersteuerung inhaberSteuerung;
	Button btAbmelden;

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
			vbox.getChildren().add(this.setBtProduktKategorieHinzu());
			vbox.getChildren().add(this.setBtFahrzeugdatenAendern());
			vbox.getChildren().add(this.setBtPersoenlicheDatenBearbeiten());
			vbox.getChildren().add(this.setBtPersoenlicheDatenAnzeigen());
			vbox.getChildren().add(this.setAbmelden());
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
			Menu userMenu = new Menu("User");
			userMenu.getStyleClass().add("mitarbeiter-menu");

			MenuItem abmeldenOption = new MenuItem("Abmelden");
			abmeldenOption.setOnAction(e -> {
				primaryStage.setScene(new Startseite(primaryStage, getWidth(), getHeight()));
			});
			userMenu.getItems().add(abmeldenOption);

			MenuBar menuBar = new MenuBar();
			menuBar.getStyleClass().add("mitarbeiter-menubar");
			menuBar.getMenus().add(userMenu);
			header.setRight(menuBar);
		}

		return this.header;
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
	 * Erzeugt Button fuer Abmelden
	 * 
	 * @return Button fuer Abmelden
	 */
	private Button setAbmelden() {
		if (btAbmelden == null) {
			btAbmelden = new Button("Abmelden");
			btAbmelden.getStyleClass().add("mitarbeiter-sidemenu-button");
			btAbmelden.setOnAction(a -> {
				primaryStage.setScene(new Startseite(primaryStage, getWidth(), getHeight()));
			});
		}
		return this.btAbmelden;
	}

	/**
	 * 
	 * Erzeugt Button fuer ProduktKategorieHinzu
	 * 
	 * @return Button fuer ProduktKategorieHinzu
	 */
	private Button setBtProduktKategorieHinzu() {
		if (this.btProduktKategorieHinzu == null) {
			btProduktKategorieHinzu = new Button("Produkt/Kategorie Hinzu");
			btProduktKategorieHinzu.getStyleClass().add("mitarbeiter-sidemenu-button");
			btProduktKategorieHinzu.setOnAction(e -> {
				primaryStage
						.setScene(new ProduktKategorieHinzu(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
			});
		}
		return this.btProduktKategorieHinzu;
	}
}

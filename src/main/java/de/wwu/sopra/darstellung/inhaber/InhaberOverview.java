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
	Inhabersteuerung inhaberSteuerung;
	Button btAbmelden;

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

	private VBox setVBox() {
		// Erstellung von SideBar mit allen Buttons
		if (this.vbox == null) {
			vbox = new VBox();
			vbox.getChildren().add(this.setBtStatistiken());
			vbox.getChildren().add(this.setBtMitarbeiterRegistrieren());
			vbox.getChildren().add(this.setBtMitarbeiterVerwalten());
			vbox.getChildren().add(this.setBtSortimentBearbeiten());
			vbox.getChildren().add(this.setBtFahrzeugdatenAendern());
			vbox.getChildren().add(this.setBtPersoenlicheDatenBearbeiten());
			vbox.getChildren().add(this.setBtPersoenlicheDatenAnzeigen());
			vbox.getStyleClass().add("mitarbeiter-sidemenu-wrapper");
		}
		
		return this.vbox;
	}

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
}

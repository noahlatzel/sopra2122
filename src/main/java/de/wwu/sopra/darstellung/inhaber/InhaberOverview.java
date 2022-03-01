/**
 * 
 */
package de.wwu.sopra.darstellung.inhaber;

import de.wwu.sopra.anwendung.mitarbeiter.Inhabersteuerung;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Valeria Vassallo
 *
 */
public class InhaberOverview extends Scene {
	BorderPane root = new BorderPane();
	Stage primaryStage;
	Image image;
	VBox vbox;
	Button btStatistiken;
	Button btMitarbeiterRegistrieren;
	Button btSortimentBearbeiten;
	Button btFahrzeugdatenAendern;
	Button btPersoenlicheDatenAnzeigen;
	Button btPersoenlicheDatenBearbeiten;
	Inhabersteuerung inhaberSteuerung;

	public InhaberOverview(Stage primaryStage, double width, double height, Inhabersteuerung inhaberSteuerung) {
		super(new BorderPane(), width, height);
		this.primaryStage = primaryStage;
		this.setRoot(root);
		this.inhaberSteuerung = inhaberSteuerung;
		root.setLeft(this.setVBox());
		root.setCenter(new Label("INHABER STARTSEITE"));
	}
	
	private VBox setVBox() {
		if (this.vbox == null) {
			vbox = new VBox(6);
			vbox.getChildren().add(this.setBtStatistiken());
			vbox.getChildren().add(this.setBtMitarbeiterRegistrieren());
			vbox.getChildren().add(this.setBtSortimentBearbeiten());
			vbox.getChildren().add(this.setBtFahrzeugdatenAendern());
			vbox.getChildren().add(this.setBtPersoenlicheDatenBearbeiten());
			vbox.getChildren().add(this.setBtPersoenlicheDatenAnzeigen());
		}
		return this.vbox;
	}
	
	private Button setBtStatistiken() {
		if (this.btStatistiken == null) {
			btStatistiken = new Button("Statistiken");
			btStatistiken.setMinWidth(250);
			btStatistiken.setOnAction(action -> {
				primaryStage.setScene(new Statistiken(primaryStage, 1280, 720, inhaberSteuerung));
			});
		}

		return this.btStatistiken;
	}
	
	private Button setBtMitarbeiterRegistrieren() {
		if (this.btMitarbeiterRegistrieren == null) {
			btMitarbeiterRegistrieren = new Button("Mitarbeiter Registrieren");
			btMitarbeiterRegistrieren.setMinWidth(250);
			btMitarbeiterRegistrieren.setOnAction(action -> {
				primaryStage.setScene(new MitarbeiterRegistrieren(primaryStage, 1280, 720, inhaberSteuerung));
			});
		}
		
		return this.btMitarbeiterRegistrieren;
	}
	
	private Button setBtSortimentBearbeiten() {
		if (this.btSortimentBearbeiten == null) {
			btSortimentBearbeiten = new Button("Sortiment Bearbeiten");
			btSortimentBearbeiten.setMinWidth(250);
			btSortimentBearbeiten.setOnAction(action -> {
				primaryStage.setScene(new SortimentBearbeiten(primaryStage, 1280, 720, inhaberSteuerung));
			});
		}
		
		return this.btSortimentBearbeiten;
	}

	private Button setBtFahrzeugdatenAendern() {
		if (this.btFahrzeugdatenAendern == null) {
			btFahrzeugdatenAendern = new Button("Fahrzeugdaten Aendern");
			btFahrzeugdatenAendern.setMinWidth(250);
			btFahrzeugdatenAendern.setOnAction(action -> {
				primaryStage.setScene(new FahrzeugdatenAendern(primaryStage, 1280, 720, inhaberSteuerung));
			});
		}
		
		return this.btFahrzeugdatenAendern;
	}
	
	private Button setBtPersoenlicheDatenBearbeiten() {
		if (this.btPersoenlicheDatenBearbeiten == null) {
			btPersoenlicheDatenBearbeiten = new Button("Persoenliche Daten Bearbeiten");
			btPersoenlicheDatenBearbeiten.setMinWidth(250);
			btPersoenlicheDatenBearbeiten.setOnAction(action -> {
				primaryStage.setScene(new PersoenlicheDatenBearbeiten(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
			});
		}
		
		return this.btPersoenlicheDatenBearbeiten;
	}
	
	private Button setBtPersoenlicheDatenAnzeigen() {
		if (this.btPersoenlicheDatenAnzeigen == null) {
			btPersoenlicheDatenAnzeigen = new Button("Persoenliche Daten Anzeigen");
			btPersoenlicheDatenAnzeigen.setMinWidth(250);
			btPersoenlicheDatenAnzeigen.setOnAction(action -> {
				primaryStage.setScene(new PersoenlicheDatenAnzeigen(primaryStage, 1280, 720, inhaberSteuerung));
			});
		}
		
		return this.btPersoenlicheDatenAnzeigen;
	}
}

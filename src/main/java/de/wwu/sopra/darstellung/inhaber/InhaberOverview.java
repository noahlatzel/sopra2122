/**
 * 
 */
package de.wwu.sopra.darstellung.inhaber;

import de.wwu.sopra.anwendung.mitarbeiter.Inhabersteuerung;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
	BorderPane header;
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
		root.setTop(this.setHeader());
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
	
	private BorderPane setHeader() {
		if (this.header == null) {
			header = new BorderPane();
			header.minHeight(300);
			header.setPadding(new Insets(10, 20, 10, 20));
			header.setBackground(new Background(new BackgroundFill(Color.web("#c4c4c4"), new CornerRadii(0), Insets.EMPTY)));
			Label logoLabel = new Label("Logo");
			logoLabel.setTextFill(Color.web("#000000"));
			Label userLabel = new Label("User");
			userLabel.setTextFill(Color.web("#000000"));
			header.setLeft(logoLabel);
			header.setRight(userLabel);
		}
		
		return this.header;
	}
	
	private Button setBtStatistiken() {
		if (this.btStatistiken == null) {
			btStatistiken = new Button("Statistiken");
			btStatistiken.setMinWidth(250);
			btStatistiken.setOnAction(action -> {
				primaryStage.setScene(new StatistikenAnzeigen(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
			});
		}

		return this.btStatistiken;
	}
	
	private Button setBtMitarbeiterRegistrieren() {
		if (this.btMitarbeiterRegistrieren == null) {
			btMitarbeiterRegistrieren = new Button("Mitarbeiter Registrieren");
			btMitarbeiterRegistrieren.setMinWidth(250);
			btMitarbeiterRegistrieren.setOnAction(action -> {
				primaryStage.setScene(new MitarbeiterRegistrieren(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
			});
		}
		
		return this.btMitarbeiterRegistrieren;
	}
	
	private Button setBtSortimentBearbeiten() {
		if (this.btSortimentBearbeiten == null) {
			btSortimentBearbeiten = new Button("Sortiment Bearbeiten");
			btSortimentBearbeiten.setMinWidth(250);
			btSortimentBearbeiten.setOnAction(action -> {
				primaryStage.setScene(new SortimentBearbeiten(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
			});
		}
		
		return this.btSortimentBearbeiten;
	}

	private Button setBtFahrzeugdatenAendern() {
		if (this.btFahrzeugdatenAendern == null) {
			btFahrzeugdatenAendern = new Button("Fahrzeugdaten Aendern");
			btFahrzeugdatenAendern.setMinWidth(250);
			btFahrzeugdatenAendern.setOnAction(action -> {
				primaryStage.setScene(new FahrzeugdatenAendern(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
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
				primaryStage.setScene(new PersoenlicheDatenAnzeigen(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
			});
		}
		
		return this.btPersoenlicheDatenAnzeigen;
	}
}

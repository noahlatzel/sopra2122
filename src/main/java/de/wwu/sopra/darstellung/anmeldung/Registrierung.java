package de.wwu.sopra.darstellung.anmeldung;

import java.io.File;

import de.wwu.sopra.anwendung.anmeldung.Anmeldungssteuerung;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Grenzklasse Registrierung
 * 
 * @author Paul Dirksen
 *
 */
public class Registrierung extends Scene {
	Stage primaryStage;
	BorderPane root = new BorderPane();
	GridPane gridpane;
	FlowPane flowpane;

	Button buttonRegistrieren = new Button("Registrieren");
	TextField textFeldBenutzername = new TextField();
	TextField textFeldPasswort = new TextField();
	TextField textFeldEmail = new TextField();
	TextField textFeldAdresse = new TextField();
	TextField textFeldVorname = new TextField();
	TextField textFeldName = new TextField();;
	TextField textFeldBankverbindung = new TextField();
	Button buttonZurueck = new Button("Zurueck");
	Label labelBenutzername = new Label("Benutzername");
	Label labelPasswort = new Label("Passwort");
	Label labelEmail = new Label("Email");
	Label labelAdresse = new Label("Adresse");
	Label labelVorname = new Label("Vorname");
	Label labelName = new Label("Name");
	Label labelBankverbindung = new Label("Bankverbindung");
	Text title;

	/**
	 * Erzeugt eine neue Anmeldungsseite.
	 * 
	 * @param primaryStage PrimaryStage
	 * @param width        Breite des Fensters.
	 * @param height       Hoehe des Fensters.
	 */
	public Registrierung(Stage primaryStage, double width, double height) {
		super(new BorderPane(), width, height);
		this.primaryStage = primaryStage;
		this.setRoot(root);
		root.setCenter(setGridPane());

		File f = new File("resources/stylesheet.css");
		this.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
	}

	private GridPane setGridPane() {
		if (gridpane == null) {
			gridpane = new GridPane();

			title = new Text("Registrierung");

			// Styling von Komponenten
			GridPane.setMargin(title, new Insets(0, 0, 30, 0));
			GridPane.setMargin(setButtonRegistrieren(), new Insets(40, 10, 0, 0));
			title.getStyleClass().add("anmeldung-registrierung-title");
			textFeldBenutzername.getStyleClass().add("anmeldung-registrierung-textfield");
			textFeldPasswort.getStyleClass().add("anmeldung-registrierung-textfield");
			textFeldEmail.getStyleClass().add("anmeldung-registrierung-textfield");
			textFeldAdresse.getStyleClass().add("anmeldung-registrierung-textfield");
			textFeldVorname.getStyleClass().add("anmeldung-registrierung-textfield");
			textFeldName.getStyleClass().add("anmeldung-registrierung-textfield");
			textFeldBankverbindung.getStyleClass().add("anmeldung-registrierung-textfield");
			labelBenutzername.getStyleClass().add("anmeldung-registrierung-label");
			labelPasswort.getStyleClass().add("anmeldung-registrierung-label");
			labelEmail.getStyleClass().add("anmeldung-registrierung-label");
			labelAdresse.getStyleClass().add("anmeldung-registrierung-label");
			labelVorname.getStyleClass().add("anmeldung-registrierung-label");
			labelName.getStyleClass().add("anmeldung-registrierung-label");
			labelBankverbindung.getStyleClass().add("anmeldung-registrierung-label");
			gridpane.getStyleClass().add("registrierung-wrapper");

			// Auf GridPane setzen
			gridpane.add(title, 1, 0, 2, 1);
			gridpane.add(labelVorname, 1, 1);
			gridpane.add(textFeldVorname, 1, 2);
			gridpane.add(labelName, 2, 1);
			gridpane.add(textFeldName, 2, 2);
			gridpane.add(labelBenutzername, 1, 4);
			gridpane.add(textFeldBenutzername, 1, 5);
			gridpane.add(labelPasswort, 2, 4);
			gridpane.add(textFeldPasswort, 2, 5);
			gridpane.add(labelEmail, 1, 7);
			gridpane.add(textFeldEmail, 1, 8);
			gridpane.add(labelAdresse, 2, 7);
			gridpane.add(textFeldAdresse, 2, 8);
			gridpane.add(labelBankverbindung, 1, 10);
			gridpane.add(textFeldBankverbindung, 1, 11);
			gridpane.add(setButtonRegistrieren(), 2, 12, 2, 1);
			gridpane.add(setButtonZurueck(), 1, 12, 4, 5);
			gridpane.setAlignment(Pos.CENTER);
		}
		return gridpane;
	}

	/**
	 * Erstellt einen Registrierungsbutton der bei Betaetigung einen Kunden
	 * registriert.
	 * 
	 * @return Gibt den konfigurierten Button zurueck
	 */
	private Button setButtonRegistrieren() {
		buttonRegistrieren.getStyleClass().add("registrierung-button");

		// Knopfdruckfunktionalitaet
		buttonRegistrieren.setOnAction(e -> {
			Anmeldungssteuerung anSt = new Anmeldungssteuerung();
			anSt.registrieren(textFeldBenutzername.getText(), textFeldPasswort.getText(), textFeldEmail.getText(),
					textFeldAdresse.getText(), textFeldVorname.getText(), textFeldName.getText(),
					textFeldBankverbindung.getText());
			primaryStage.setScene(new Anmeldung(primaryStage, getWidth(), getHeight()));
		});

		return buttonRegistrieren;
	}

	/**
	 * Erzeugt den Zurueck-Button
	 * 
	 * @return Zurueck-Button
	 */
	private Button setButtonZurueck() {
		buttonZurueck.getStyleClass().add("registrierung-zurueck-button");

		// Knopfdruckfunktionalitaet
		buttonZurueck.setOnAction(e -> {
			primaryStage.setScene(new Startseite(primaryStage, getWidth(), getHeight()));
		});

		return buttonZurueck;
	}
}

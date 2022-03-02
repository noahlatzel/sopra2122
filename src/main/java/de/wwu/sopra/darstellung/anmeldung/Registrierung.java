package de.wwu.sopra.darstellung.anmeldung;

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
import javafx.scene.layout.RowConstraints;
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
	TextField textFeldBenutzername = new TextField();;
	TextField textFeldPasswort = new TextField();;
	TextField textFeldEmail = new TextField();;
	TextField textFeldAdresse = new TextField();;
	TextField textFeldVorname = new TextField();;
	TextField textFeldName = new TextField();;
	TextField textFeldBankverbindung = new TextField();;
	Label labelBenutzername = new Label("Benutzername");
	Label labelPasswort = new Label("Passwort");
	Label labelEmail = new Label("Email");
	Label labelAdresse = new Label("Adresse");
	Label labelVorname = new Label("Vorname");
	Label labelName = new Label("Name");
	Label labelBankverbindung = new Label("Bankverbindung");

	/**
	 * Erzeugt eine neue Anmeldungsseite.
	 * 
	 * @param primaryStage
	 * @param width        Breite des Fensters.
	 * @param height       Hoehe des Fensters.
	 */
	public Registrierung(Stage primaryStage, double width, double height) {
		super(new BorderPane(), width, height);
		this.primaryStage = primaryStage;
		this.setRoot(root);
		root.setCenter(setGridPane());

	}

	private GridPane setGridPane() {
		if (gridpane == null) {
			gridpane = new GridPane();

			gridpane.setHgap(20);

			// Fuegt Platz zwischen den Zeilen hinzu
			for (int i = 0; i < 7; i++) {
				gridpane.getRowConstraints().add(new RowConstraints(-16));
				gridpane.getRowConstraints().add(new RowConstraints(57));
			}

			gridpane.add(labelVorname, 1, 0);
			gridpane.add(textFeldVorname, 1, 1);
			gridpane.add(labelName, 2, 0);
			gridpane.add(textFeldName, 2, 1);
			gridpane.add(labelBenutzername, 1, 3);
			gridpane.add(textFeldBenutzername, 1, 4);
			gridpane.add(labelPasswort, 2, 3);
			gridpane.add(textFeldPasswort, 2, 4);
			gridpane.add(labelEmail, 1, 6);
			gridpane.add(textFeldEmail, 1, 7);
			gridpane.add(labelAdresse, 2, 6);
			gridpane.add(textFeldAdresse, 2, 7);
			gridpane.add(labelBankverbindung, 1, 9);
			gridpane.add(textFeldBankverbindung, 1, 10);
			gridpane.add(setButtonRegistrieren(), 1, 12, 1, 2);
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
		buttonRegistrieren.setAlignment(Pos.TOP_CENTER);
		buttonRegistrieren.setPadding(new Insets(10));

		// Knopfdruckfunktionalitaet
		buttonRegistrieren.setOnAction(e -> {
			Anmeldungssteuerung anSt = new Anmeldungssteuerung();
			anSt.registrieren(textFeldBenutzername.getText(), textFeldPasswort.getText(), textFeldEmail.getText(),
					textFeldAdresse.getText(), textFeldVorname.getText(), textFeldName.getText(),
					textFeldBankverbindung.getText());
			primaryStage.setScene(new Anmeldung(primaryStage, 800, 600)); // TODO
		});

		return buttonRegistrieren;
	}
}

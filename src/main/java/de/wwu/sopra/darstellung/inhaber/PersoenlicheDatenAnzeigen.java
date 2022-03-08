/**
 * 
 */
package de.wwu.sopra.darstellung.inhaber;

import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.anwendung.mitarbeiter.Inhabersteuerung;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author Valeria Vassallo
 *
 */
public class PersoenlicheDatenAnzeigen extends InhaberOverview {
	// Erstellung von Variablen
	BorderPane contentWrapper;
	GridPane gridPane;

	TextField textFeldBenutzername = new TextField();
	TextField textFeldPasswort = new TextField();
	TextField textFeldEmail = new TextField();
	TextField textFeldAdresse = new TextField();
	TextField textFeldVorname = new TextField();
	TextField textFeldName = new TextField();;
	TextField textFeldBankverbindung = new TextField();
	Label labelBenutzername = new Label("Benutzername");
	Label labelPasswort = new Label("Passwort");
	Label labelEmail = new Label("Email");
	Label labelAdresse = new Label("Adresse");
	Label labelVorname = new Label("Vorname");
	Label labelName = new Label("Name");
	Label labelBankverbindung = new Label("Bankverbindung");

	/**
	 * Zeigt die persoenlichen Daten
	 * 
	 * @param primaryStage     PrimaryStage
	 * @param width            Breite des Fensters
	 * @param height           Hoehe des Fensters
	 * @param inhaberSteuerung InhaberSteuerung
	 */
	public PersoenlicheDatenAnzeigen(Stage primaryStage, double width, double height,
			Inhabersteuerung inhaberSteuerung) {
		super(primaryStage, width, height, inhaberSteuerung);
		root.setCenter(this.setContentWrapper());
	}

	/**
	 * Erzeugt ContentWrapper fuer Titel
	 * 
	 * @return ContentWrapper fuer Titel
	 */
	private BorderPane setContentWrapper() {
		// ContentWrapper, um den Titel einzuschliessen
		if (this.contentWrapper == null) {
			contentWrapper = new BorderPane();
			contentWrapper.setPadding(new Insets(10, 30, 10, 30));
			Label title = new Label("Persoenliche Daten");
			title.getStyleClass().add("mitarbeiter-content-title");
			contentWrapper.setTop(title);
			contentWrapper.setCenter(this.setContent());
		}

		return this.contentWrapper;
	}

	/**
	 * Setzt den Inhalt
	 * 
	 * @return GridPane mit Inhalt
	 */
	private GridPane setContent() {
		if (gridPane == null) {
			gridPane = new GridPane();
			String gesamtString = inhaberSteuerung.persoenlicheDatenAnzeigen();

			// Label
			Label lbBenutzername = new Label("Benutzername:");
			Label lbPasswort = new Label("Passwort:");
			Label lbEmail = new Label("Email:");
			Label lbAdresse = new Label("Adresse:");
			Label lbVorname = new Label("Vorname:");
			Label lbNachname = new Label("Nachname:");
			Label lbBankverbindung = new Label("Bankverbindung:");

			// Setzen in GridPane

			gridPane.add(lbBankverbindung, 0, 7);
			gridPane.add(lbNachname, 0, 6);
			gridPane.add(lbVorname, 0, 5);
			gridPane.add(lbAdresse, 0, 4);
			gridPane.add(lbEmail, 0, 3);
			gridPane.add(lbPasswort, 0, 2);
			gridPane.add(lbBenutzername, 0, 1);

			// TextFelder
			TextField tfBenutzername = new TextField();
			TextField tfPasswort = new TextField();
			TextField tfEmail = new TextField();
			TextField tfAdresse = new TextField();
			TextField tfVorname = new TextField();
			TextField tfNachname = new TextField();
			TextField tfBankverbindung = new TextField();

			// Grid setzen
			gridPane.add(tfBankverbindung, 1, 7);
			gridPane.add(tfNachname, 1, 6);
			gridPane.add(tfVorname, 1, 5);
			gridPane.add(tfAdresse, 1, 4);
			gridPane.add(tfEmail, 1, 3);
			gridPane.add(tfPasswort, 1, 2);
			gridPane.add(tfBenutzername, 1, 1);

			// Text setzen
			String[] aufgeteilt = gesamtString.split(";");
			tfBenutzername.setText(aufgeteilt[0]);
			tfPasswort.setText(aufgeteilt[1]);
			tfEmail.setText(aufgeteilt[2]);
			tfAdresse.setText(aufgeteilt[3]);
			tfVorname.setText(aufgeteilt[4]);
			tfNachname.setText(aufgeteilt[5]);
			tfBankverbindung.setText(aufgeteilt[6]);

			// Label Style
			lbBenutzername.getStyleClass().add("anmeldung-registrierung-label");
			lbPasswort.getStyleClass().add("anmeldung-registrierung-label");
			lbEmail.getStyleClass().add("anmeldung-registrierung-label");
			lbAdresse.getStyleClass().add("anmeldung-registrierung-label");
			lbVorname.getStyleClass().add("anmeldung-registrierung-label");
			lbNachname.getStyleClass().add("anmeldung-registrierung-label");
			lbBankverbindung.getStyleClass().add("anmeldung-registrierung-label");

			// Text Style
			tfBenutzername.getStyleClass().add("anmeldung-registrierung-textfield");
			tfPasswort.getStyleClass().add("anmeldung-registrierung-textfield");
			tfEmail.getStyleClass().add("anmeldung-registrierung-textfield");
			tfAdresse.getStyleClass().add("anmeldung-registrierung-textfield");
			tfVorname.getStyleClass().add("anmeldung-registrierung-textfield");
			tfNachname.getStyleClass().add("anmeldung-registrierung-textfield");
			tfBankverbindung.getStyleClass().add("anmeldung-registrierung-textfield");

			// Textfeld nicht editierbar
			tfBenutzername.setDisable(true);
			tfPasswort.setDisable(true);
			tfEmail.setDisable(true);
			tfAdresse.setDisable(true);
			tfVorname.setDisable(true);
			tfNachname.setDisable(true);
			tfBankverbindung.setDisable(true);

			// Buttons setzen
			Button bearbeiten = new Button("Bearbeiten");
			bearbeiten.getStyleClass().add("mitarbeiter-registrierung-button");
			bearbeiten.setOnAction(a -> {
				tfBenutzername.setDisable(false);
				tfPasswort.setDisable(false);
				tfEmail.setDisable(false);
				tfAdresse.setDisable(false);
				tfVorname.setDisable(false);
				tfNachname.setDisable(false);
				tfBankverbindung.setDisable(false);
			});
			Button speichern = new Button("Speichern");
			speichern.getStyleClass().add("mitarbeiter-registrierung-button");
			speichern.setOnAction(a -> {

				// test auf blank stellen
				boolean korrekteEingabe = true;

				List<TextField> inputs = new ArrayList<TextField>();
				inputs.add(tfBankverbindung);
				inputs.add(tfNachname);
				inputs.add(tfVorname);
				inputs.add(tfAdresse);
				inputs.add(tfEmail);
				inputs.add(tfPasswort);
				inputs.add(tfBenutzername);
				for (TextField i : inputs) {
					if (i.getText().isBlank())
						korrekteEingabe = false;
				}

				// Wenn nicht leer
				if (korrekteEingabe == true) {
					inhaberSteuerung.persoenlicheDatenAendern(tfBenutzername.getText(), tfPasswort.getText(),
							tfEmail.getText(), tfAdresse.getText(), tfVorname.getText(), tfNachname.getText(),
							tfBankverbindung.getText());
					tfBenutzername.setDisable(true);
					tfPasswort.setDisable(true);
					tfEmail.setDisable(true);
					tfAdresse.setDisable(true);
					tfVorname.setDisable(true);
					tfNachname.setDisable(true);
					tfBankverbindung.setDisable(true);
				} else {
					gridPane.add(new Label("Ueberpruefe deine Eingaben!"), 2, 7);
				}

			});

			// Grid setzen
			gridPane.add(bearbeiten, 0, 8);
			gridPane.add(speichern, 1, 8);

			// Abstand setzen
			gridPane.setHgap(5);
			gridPane.setVgap(5);
		}
		return gridPane;
	}
}

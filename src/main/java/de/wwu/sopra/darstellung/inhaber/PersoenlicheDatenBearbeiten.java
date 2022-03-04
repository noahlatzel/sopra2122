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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author Valeria Vassallo
 *
 */
public class PersoenlicheDatenBearbeiten extends InhaberOverview {
	// Erstellung von Variablen
	BorderPane contentWrapper;
	GridPane gridPane;

	/**
	 * Zeigt die persoenlichen Daten zum bearbeiten
	 * 
	 * @param primaryStage     PrimaryStage
	 * @param width            Breite des Fensters
	 * @param height           Hoehe des Fensters
	 * @param inhaberSteuerung InhaberSteuerung
	 */
	public PersoenlicheDatenBearbeiten(Stage primaryStage, double width, double height,
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
			Label title = new Label("Persoenliche Daten Bearbeiten");
			title.setStyle("-fx-font-weight: bold");
			title.setFont(new Font("Arial", 32));
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
		if (this.gridPane == null) {
			gridPane = new GridPane();
			// Get alle Daten vom Inhaber als String
			String alleDatenAlsString = inhaberSteuerung.persoenlicheDatenAnzeigen();

			// Erstellung von Labeln und TextFields
			Label lblBenutzername = new Label("Benutzername");
			Label lblPasswort = new Label("Passwort");
			Label lblEmail = new Label("Email");
			Label lblAdresse = new Label("Adresse");
			Label lblVorname = new Label("Vorname");
			Label lblNachname = new Label("Nachname");
			Label lblBankverbindung = new Label("Bankverbindung");

			TextField tfBenutzername = new TextField();
			PasswordField tfPasswort = new PasswordField();
			TextField tfEmail = new TextField();
			TextField tfAdresse = new TextField();
			TextField tfVorname = new TextField();
			TextField tfNachname = new TextField();
			TextField tfBankverbindung = new TextField();

			// in organisierten Komponenten zeigen
			VBox vboxBenutzerName = new VBox(2);
			vboxBenutzerName.getChildren().add(lblBenutzername);
			vboxBenutzerName.getChildren().add(tfBenutzername);

			VBox vboxPasswort = new VBox(2);
			vboxPasswort.getChildren().add(lblPasswort);
			vboxPasswort.getChildren().add(tfPasswort);

			VBox vboxEmail = new VBox(2);
			vboxEmail.getChildren().add(lblEmail);
			vboxEmail.getChildren().add(tfEmail);

			VBox vboxAdresse = new VBox(2);
			vboxAdresse.getChildren().add(lblAdresse);
			vboxAdresse.getChildren().add(tfAdresse);

			VBox vboxVorname = new VBox(2);
			vboxVorname.getChildren().add(lblVorname);
			vboxVorname.getChildren().add(tfVorname);

			VBox vboxNachname = new VBox(2);
			vboxNachname.getChildren().add(lblNachname);
			vboxNachname.getChildren().add(tfNachname);

			VBox vboxBankverbindung = new VBox(2);
			vboxBankverbindung.getChildren().add(lblBankverbindung);
			vboxBankverbindung.getChildren().add(tfBankverbindung);

			// Alles auf Grid
			gridPane.add(vboxBenutzerName, 0, 0);
			gridPane.add(vboxVorname, 0, 1);
			gridPane.add(vboxPasswort, 0, 2);
			gridPane.add(vboxAdresse, 0, 3);
			gridPane.add(vboxEmail, 1, 0);
			gridPane.add(vboxNachname, 1, 1);
			gridPane.add(vboxBankverbindung, 1, 2);

			// Lange String durch ";" teilen und Daten einsetzen
			String[] getrennteDatenString = alleDatenAlsString.split(";");
			tfBenutzername.setText(getrennteDatenString[0]);
			tfPasswort.setText(getrennteDatenString[1]);
			tfEmail.setText(getrennteDatenString[2]);
			tfAdresse.setText(getrennteDatenString[3]);
			tfVorname.setText(getrennteDatenString[4]);
			tfNachname.setText(getrennteDatenString[5]);
			tfBankverbindung.setText(getrennteDatenString[6]);

			// Erstellung von Speicher-Button
			Button btSpeichern = new Button("Speichern");
			gridPane.add(btSpeichern, 1, 4);
			btSpeichern.setOnAction(e -> {

				// Ueberprueft, dass alle Eingaben nicht leer sing
				boolean gueltigeEinngaben = true;
				List<TextField> felder = new ArrayList<TextField>();
				felder.add(tfBankverbindung);
				felder.add(tfNachname);
				felder.add(tfVorname);
				felder.add(tfAdresse);
				felder.add(tfEmail);
				felder.add(tfPasswort);
				felder.add(tfBenutzername);
				for (TextField feld : felder) {
					if (feld.getText().isBlank())
						gueltigeEinngaben = false;
				}

				// Erstellung von neuem roten Error-Label
				Label errorLabel = new Label();
				errorLabel.setTextFill(Color.web("#ff0000"));

				if (gueltigeEinngaben == true) {
					inhaberSteuerung.persoenlicheDatenAendern(tfBenutzername.getText(), tfPasswort.getText(),
							tfEmail.getText(), tfAdresse.getText(), tfVorname.getText(), tfNachname.getText(),
							tfBankverbindung.getText());
					errorLabel.setText("");
				} else {
					errorLabel.setText("Keine leere Angaben erlaubt");
				}

				gridPane.add(errorLabel, 1, 5);
			});

			gridPane.setHgap(10);
			gridPane.setVgap(10);

		}

		return this.gridPane;
	}
}

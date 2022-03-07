/**
 * 
 */
package de.wwu.sopra.darstellung.inhaber;

import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.anwendung.mitarbeiter.Inhabersteuerung;
import de.wwu.sopra.datenhaltung.benutzer.Rolle;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author Valeria Vassallo
 *
 */
public class MitarbeiterRegistrieren extends InhaberOverview {
	// Erstellung von Variablen
	BorderPane contentWrapper;
	GridPane grid;
	
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
	Label labelRolle = new Label("Rolle");

	/**
	 * Zeigt das Fenster zur Registierung von Mitarbeitern
	 * 
	 * @param primaryStage     PrimaryStage
	 * @param width            Breite des Fensters
	 * @param height           Hoehe des Fensters
	 * @param inhaberSteuerung InhaberSteuerung
	 */
	public MitarbeiterRegistrieren(Stage primaryStage, double width, double height, Inhabersteuerung inhaberSteuerung) {
		super(primaryStage, width, height, inhaberSteuerung);
		root.setCenter(this.setContentWrapper());
	}

	/**
	 * Erzeugt ContentWrapper
	 * 
	 * @return ContentWrapper
	 */
	private BorderPane setContentWrapper() {
		// ContentWrapper, um den Titel einzuschliessen
		if (this.contentWrapper == null) {
			contentWrapper = new BorderPane();
			contentWrapper.setPadding(new Insets(10, 30, 10, 30));
			Label title = new Label("Mitarbeiter-Registrierung");
			title.setStyle("-fx-font-weight: bold");
			title.setFont(new Font("Arial", 32));
			contentWrapper.setTop(title);
			contentWrapper.setCenter(this.setRegistrierungFields());
		}

		return this.contentWrapper;
	}

	/**
	 * Setzt Inhalt
	 * 
	 * @return GridPane mit Inhalt
	 */
	private GridPane setRegistrierungFields() {
		// GridPane als Main Content Wrapper
		if (this.grid == null) {
			grid = new GridPane();
			
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
			labelRolle.getStyleClass().add("anmeldung-registrierung-label");
			grid.getStyleClass().add("mitarbeiter-registrierung-wrapper");
			
			// ComboBox/Dropdown fuer Rollen
			ComboBox<Rolle> rollenCB = new ComboBox<>();
			rollenCB.getStyleClass().add("mitarbeiter-registrierung-combobox");
			rollenCB.getItems().add(Rolle.FAHRER);
			rollenCB.getItems().add(Rolle.LAGERIST);

			// Erstellung von Registrierung-Button
			Button btRegistrieren = new Button("Registrieren");
			btRegistrieren.getStyleClass().add("mitarbeiter-registrierung-button");
			GridPane.setMargin(btRegistrieren, new Insets(24, 0, 0, 0));

			// Auf GridPane setzen
			grid.add(labelVorname, 1, 1);
			grid.add(textFeldVorname, 1, 2);
			grid.add(labelName, 2, 1);
			grid.add(textFeldName, 2, 2);
			grid.add(labelBenutzername, 1, 4);
			grid.add(textFeldBenutzername, 1, 5);
			grid.add(labelPasswort, 2, 4);
			grid.add(textFeldPasswort, 2, 5);
			grid.add(labelEmail, 1, 7);
			grid.add(textFeldEmail, 1, 8);
			grid.add(labelAdresse, 2, 7);
			grid.add(textFeldAdresse, 2, 8);
			grid.add(labelBankverbindung, 1, 10);
			grid.add(textFeldBankverbindung, 1, 11);
			grid.add(labelRolle, 2, 10);
			grid.add(rollenCB, 2, 11);
			grid.add(btRegistrieren, 2, 12);

			// Funktion/Listener zu Registrieren-Button hinzufuegen
			btRegistrieren.setOnAction(action -> {
				boolean validInputs = true;

				List<TextField> inputs = new ArrayList<TextField>();
				inputs.add(textFeldBankverbindung);
				inputs.add(textFeldName);
				inputs.add(textFeldVorname);
				inputs.add(textFeldAdresse);
				inputs.add(textFeldEmail);
				inputs.add(textFeldPasswort);
				inputs.add(textFeldBenutzername);

				for (TextField i : inputs) {
					if (i.getText().isBlank())
						validInputs = false;
				}

				Rolle rolleValue = rollenCB.getValue();
				if (rolleValue == null) {
					validInputs = false;
				}

				if (validInputs == true) {
					inhaberSteuerung.mitarbeiterRegistrieren(textFeldBenutzername.getText(), textFeldPasswort.getText(),
							textFeldEmail.getText(), textFeldAdresse.getText(), textFeldVorname.getText(), textFeldName.getText(),
							textFeldBankverbindung.getText(), rolleValue);

					for (TextField tf : inputs) {
						tf.clear();
						rollenCB.valueProperty().set(null);
					}

				} else {
					Label errorLeerLabel = new Label("Es gibt noch leere Angaben");
					errorLeerLabel.getStyleClass().add("registrierung-error-label");
					grid.add(errorLeerLabel, 2, 13);
				}

			});
		}

		return this.grid;
	}
}

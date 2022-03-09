package de.wwu.sopra.darstellung.fahrer;

import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.anwendung.mitarbeiter.Fahrersteuerung;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * 
 * Oberflaeche zeigt Persoenliche Daten an
 * 
 * @author Johannes Thiel
 *
 */
public class PersoenlicheDatenAnzeigen extends OverviewFahrer {
	BorderPane contentWrapper;
	GridPane grid;

	/**
	 * Erzeugt PersoenlicheDatenAnzeigen
	 * 
	 * @param steuerung    FahrerSteuerung
	 * @param primaryStage PrimaryStage
	 * @param width        Breite des Fensters
	 * @param height       Hoehe des Fensters
	 */
	public PersoenlicheDatenAnzeigen(Fahrersteuerung steuerung, Stage primaryStage, double width, double height) {
		super(steuerung, primaryStage, width, height);
		root.setCenter(setContentWrapper());

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
		if (grid == null) {
			grid = new GridPane();
			grid.getStyleClass().add("fahrer-persoenliche-daten-wrapper");
			String gesamtString = steuerung.persoenlicheDatenAnzeigen();

			// Label
			Label lbBenutzername = new Label("Benutzername:");
			Label lbPasswort = new Label("Passwort:");
			Label lbEmail = new Label("Email:");
			Label lbAdresse = new Label("Adresse:");
			Label lbVorname = new Label("Vorname:");
			Label lbNachname = new Label("Nachname:");
			Label lbBankverbindung = new Label("Bankverbindung:");

			// Setzen in GridPane

			grid.add(lbBankverbindung, 0, 7);
			grid.add(lbNachname, 0, 6);
			grid.add(lbVorname, 0, 5);
			grid.add(lbAdresse, 0, 4);
			grid.add(lbEmail, 0, 3);
			grid.add(lbPasswort, 0, 2);
			grid.add(lbBenutzername, 0, 1);

			// TextFelder
			TextField tfBenutzername = new TextField();
			TextField tfPasswort = new TextField();
			TextField tfEmail = new TextField();
			TextField tfAdresse = new TextField();
			TextField tfVorname = new TextField();
			TextField tfNachname = new TextField();
			TextField tfBankverbindung = new TextField();

			// Grid setzen
			grid.add(tfBankverbindung, 1, 7);
			grid.add(tfNachname, 1, 6);
			grid.add(tfVorname, 1, 5);
			grid.add(tfAdresse, 1, 4);
			grid.add(tfEmail, 1, 3);
			grid.add(tfPasswort, 1, 2);
			grid.add(tfBenutzername, 1, 1);

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
			lbBenutzername.getStyleClass().add("fahrer-persoenliche-daten-label");
			lbPasswort.getStyleClass().add("fahrer-persoenliche-daten-label");
			lbEmail.getStyleClass().add("fahrer-persoenliche-daten-label");
			lbAdresse.getStyleClass().add("fahrer-persoenliche-daten-label");
			lbVorname.getStyleClass().add("fahrer-persoenliche-daten-label");
			lbNachname.getStyleClass().add("fahrer-persoenliche-daten-label");
			lbBankverbindung.getStyleClass().add("fahrer-persoenliche-daten-label");

			// Textfeld nicht editierbar
			tfBenutzername.setDisable(true);
			tfPasswort.setDisable(true);
			tfEmail.setDisable(true);
			tfAdresse.setDisable(true);
			tfVorname.setDisable(true);
			tfNachname.setDisable(true);
			tfBankverbindung.setDisable(true);

			// Textfield Style
			tfBenutzername.getStyleClass().add("fahrer-persoenliche-daten-textfield");
			tfPasswort.getStyleClass().add("fahrer-persoenliche-daten-textfield");
			tfEmail.getStyleClass().add("fahrer-persoenliche-daten-textfield");
			tfAdresse.getStyleClass().add("fahrer-persoenliche-daten-textfield");
			tfVorname.getStyleClass().add("fahrer-persoenliche-daten-textfield");
			tfNachname.getStyleClass().add("fahrer-persoenliche-daten-textfield");
			tfBankverbindung.getStyleClass().add("fahrer-persoenliche-daten-textfield");

			// Buttons setzen
			Button bearbeiten = new Button("Bearbeiten");
			bearbeiten.getStyleClass().add("inhaber-form-button");
			GridPane.setMargin(bearbeiten, new Insets(24, 0, 0, 0));
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
			speichern.getStyleClass().add("inhaber-form-button");
			GridPane.setMargin(speichern, new Insets(24, 0, 0, 0));
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
					steuerung.persoenlicheDatenBearbeiten(tfBenutzername.getText(), tfPasswort.getText(),
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
					grid.add(new Label("Ueberpruefe deine Eingaben!"), 2, 7);
				}

			});

			// Grid setzen
			grid.add(bearbeiten, 0, 8);
			grid.add(speichern, 1, 8);
		}
		return grid;
	}

}

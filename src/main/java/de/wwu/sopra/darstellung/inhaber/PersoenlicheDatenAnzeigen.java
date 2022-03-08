/**
 * 
 */
package de.wwu.sopra.darstellung.inhaber;

import de.wwu.sopra.anwendung.mitarbeiter.Inhabersteuerung;
import javafx.geometry.Insets;
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
	 * Setzt Inhalt
	 * 
	 * @return GridPane mit Inhalt
	 */
	private GridPane setContent() {
		if (this.gridPane == null) {
			gridPane = new GridPane();
			// Get alle Daten vom Inhaber als String
			String alleDatenAlsString = inhaberSteuerung.persoenlicheDatenAnzeigen();

			// Styling TextFelder, Labeln, GridPane
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
			gridPane.getStyleClass().add("mitarbeiter-registrierung-wrapper");

			// Alles auf Grid
			gridPane.add(labelVorname, 1, 1);
			gridPane.add(textFeldVorname, 1, 2);
			gridPane.add(labelName, 2, 1);
			gridPane.add(textFeldName, 2, 2);
			gridPane.add(labelBenutzername, 1, 4);
			gridPane.add(textFeldBenutzername, 1, 5);
			gridPane.add(labelPasswort, 2, 4);
			gridPane.add(textFeldPasswort, 2, 5);
			gridPane.add(labelEmail, 1, 7);
			gridPane.add(textFeldEmail, 1, 8);
			gridPane.add(labelAdresse, 2, 7);
			gridPane.add(textFeldAdresse, 2, 8);
			gridPane.add(labelBankverbindung, 1, 10);
			gridPane.add(textFeldBankverbindung, 1, 11);

			// Lange String durch ";" teilen und Daten einsetzen
			String[] getrennteDatenString = alleDatenAlsString.split(";");
			textFeldBenutzername.setText(getrennteDatenString[0]);
			textFeldPasswort.setText(getrennteDatenString[1]);
			textFeldEmail.setText(getrennteDatenString[2]);
			textFeldAdresse.setText(getrennteDatenString[3]);
			textFeldVorname.setText(getrennteDatenString[4]);
			textFeldName.setText(getrennteDatenString[5]);
			textFeldBankverbindung.setText(getrennteDatenString[6]);

			// TextFields nicht bearbeitbar zu machen
			textFeldBenutzername.setEditable(false);
			textFeldPasswort.setEditable(false);
			textFeldEmail.setEditable(false);
			textFeldAdresse.setEditable(false);
			textFeldVorname.setEditable(false);
			textFeldName.setEditable(false);
			textFeldBankverbindung.setEditable(false);
		}

		return this.gridPane;
	}
}

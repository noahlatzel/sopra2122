package de.wwu.sopra.darstellung.lagerist;

import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.anwendung.mitarbeiter.Lageristensteuerung;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * GUI Klasse fuer persoenliche Daten anzeigen
 * 
 * @author Noah Latzel
 */
public class PersoenlicheDatenAnzeigen extends LageristOverview {

	public PersoenlicheDatenAnzeigen(Stage primaryStage, double width, double height,
			Lageristensteuerung lageristenSteuerung) {
		super(primaryStage, width, height, lageristenSteuerung);
		root.setCenter(setPersoenlicheDatenAnzeigen());

	}

	// Daten zeigen
	private GridPane setPersoenlicheDatenAnzeigen() {

		// GridPane erstellen
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(20));
		root.setCenter(grid);

		String gesamtString = lageristenSteuerung.persoenlicheDatenAnzeigen();

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
		lbBenutzername.setStyle("-fx-background-radius: 16px; -fx-font-weight: bold; -fx-font-size: 18;");
		lbPasswort.setStyle("-fx-background-radius: 16px; -fx-font-weight: bold; -fx-font-size: 18;");
		lbEmail.setStyle("-fx-background-radius: 16px; -fx-font-weight: bold; -fx-font-size: 18;");
		lbAdresse.setStyle("-fx-background-radius: 16px; -fx-font-weight: bold; -fx-font-size: 18;");
		lbVorname.setStyle("-fx-background-radius: 16px; -fx-font-weight: bold; -fx-font-size: 18;");
		lbNachname.setStyle("-fx-background-radius: 16px; -fx-font-weight: bold; -fx-font-size: 18;");
		lbBankverbindung.setStyle("-fx-background-radius: 16px; -fx-font-weight: bold; -fx-font-size: 18;");

		// Textfeld nicht editierbar
		tfBenutzername.setEditable(false);
		tfPasswort.setEditable(false);
		tfEmail.setEditable(false);
		tfAdresse.setEditable(false);
		tfVorname.setEditable(false);
		tfNachname.setEditable(false);
		tfBankverbindung.setEditable(false);

		// Buttons setzen
		Button bearbeiten = new Button("Bearbeiten");
		bearbeiten.setEffect(dropShadow);
		changeButtonStyleOnHover(bearbeiten);
		bearbeiten.setOnAction(a -> {
			tfBenutzername.setEditable(true);
			tfPasswort.setEditable(true);
			tfEmail.setEditable(true);
			tfAdresse.setEditable(true);
			tfVorname.setEditable(true);
			tfNachname.setEditable(true);
			tfBankverbindung.setEditable(true);
		});
		Button speichern = new Button("Speichern");
		speichern.setEffect(dropShadow);
		changeButtonStyleOnHover(speichern);
		speichern.setOnAction(a -> {
			// test auf blank stellen
			boolean istallesvoll = true;
			List<TextField> felder = new ArrayList<TextField>();
			felder.add(tfBankverbindung);
			felder.add(tfNachname);
			felder.add(tfVorname);
			felder.add(tfAdresse);
			felder.add(tfEmail);
			felder.add(tfPasswort);
			felder.add(tfBenutzername);
			for (TextField i : felder) {
				if (i.getText().isBlank())
					istallesvoll = false;
			}

			// wen nicht blanc
			if (istallesvoll == true) {
				lageristenSteuerung.persoenlicheDatenBearbeiten(tfBenutzername.getText(), tfPasswort.getText(),
						tfEmail.getText(), tfAdresse.getText(), tfVorname.getText(), tfNachname.getText(),
						tfBankverbindung.getText());
			} else {
				grid.add(new Label("es gibt Leere Angaben"), 2, 7);
			}
			tfBenutzername.setEditable(false);
			tfPasswort.setEditable(false);
			tfEmail.setEditable(false);
			tfAdresse.setEditable(false);
			tfVorname.setEditable(false);
			tfNachname.setEditable(false);
			tfBankverbindung.setEditable(false);
		});

		// Grid setzen
		grid.add(bearbeiten, 0, 8);
		grid.add(speichern, 1, 8);

		// Abstand setzen
		grid.setHgap(5);
		grid.setVgap(5);

		return grid;
	}

}

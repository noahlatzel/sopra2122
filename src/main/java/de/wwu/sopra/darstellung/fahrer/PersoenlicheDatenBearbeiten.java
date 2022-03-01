package de.wwu.sopra.darstellung.fahrer;

import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.anwendung.mitarbeiter.Fahrersteuerung;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PersoenlicheDatenBearbeiten extends OverviewFahrer {

	public PersoenlicheDatenBearbeiten(Fahrersteuerung steuerung, Stage primaryStage, double width, double height) {
		super(steuerung, primaryStage, width, height);
		root.setCenter(new Label("this is Route"));
		this.DatenBearbeiten();
	}

	private void DatenBearbeiten() {
		GridPane grid = new GridPane();
		root.setCenter(grid);

		String gesamtString = steuerung.persoenlicheDatenAnzeigen();

		// Label
		Label lbBenutzername = new Label("Benutzername:");
		Label lbPasswort = new Label("Passwort:");
		Label lbEmail = new Label("Email:");
		Label lbAdresse = new Label("Adresse:");
		Label lbVorname = new Label("Vorname:");
		Label lbNachname = new Label("Nachname:");
		Label lbBankverbindung = new Label("Bankverbindung:");

		// setzten in GridPane

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

		// setzten in Grid
		grid.add(tfBankverbindung, 1, 7);
		grid.add(tfNachname, 1, 6);
		grid.add(tfVorname, 1, 5);
		grid.add(tfAdresse, 1, 4);
		grid.add(tfEmail, 1, 3);
		grid.add(tfPasswort, 1, 2);
		grid.add(tfBenutzername, 1, 1);

		// stzeen des Textes
		String[] aufgeteilt = gesamtString.split(";");
		tfBenutzername.setText(aufgeteilt[0]);
		tfPasswort.setText(aufgeteilt[1]);
		tfEmail.setText(aufgeteilt[2]);
		tfAdresse.setText(aufgeteilt[3]);
		tfVorname.setText(aufgeteilt[4]);
		tfNachname.setText(aufgeteilt[5]);
		tfBankverbindung.setText(aufgeteilt[6]);

		// erstelle Speicherbutton
		Button btSpeichern = new Button("Speichern");
		grid.add(btSpeichern, 2, 8);
		btSpeichern.setOnAction(e -> {

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
				steuerung.persoenlicheDatenBearbeiten(tfBenutzername.getText(), tfPasswort.getText(), tfEmail.getText(),
						tfAdresse.getText(), tfVorname.getText(), tfNachname.getText(), tfBankverbindung.getText());
			} else {
				grid.add(new Label("es gibt Leere Angaben"), 2, 7);
			}
		});

		// abstand setzten
		grid.setHgap(5);
		grid.setVgap(5);
	}

}

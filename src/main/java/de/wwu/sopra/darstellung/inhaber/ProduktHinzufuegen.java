package de.wwu.sopra.darstellung.inhaber;

import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.anwendung.mitarbeiter.Inhabersteuerung;
import de.wwu.sopra.datenhaltung.management.Kategorie;
import de.wwu.sopra.datenhaltung.management.Produkt;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Darstellungsklasse fuer StartseiteKunde
 * 
 * @author Valeria Vassallo
 *
 */
public class ProduktHinzufuegen extends InhaberOverview {
	// Erstellung von Variable
	BorderPane contentWrapper;
	GridPane content;
	List<Kategorie> kategorien;

	/**
	 * Zeigt das Fenster zur Bearbeitung von Produktkategorien
	 * 
	 * @param primaryStage     PrimaryStage
	 * @param width            Breite des Fensters
	 * @param height           Hoehe des Fensters
	 * @param inhaberSteuerung InhaberSteuerung
	 */
	public ProduktHinzufuegen(Stage primaryStage, double width, double height, Inhabersteuerung inhaberSteuerung) {
		super(primaryStage, width, height, inhaberSteuerung);
		root.setCenter(this.setContentWrapper());
	}

	/**
	 * Gibt den ContentWrapper fuer Titel zurueck
	 * 
	 * @return ContentWrapper fuer Titel
	 */
	private BorderPane setContentWrapper() {
		// ContentWrapper, um den Titel einzuschliessen
		if (this.contentWrapper == null) {
			contentWrapper = new BorderPane();
			contentWrapper.setPadding(new Insets(10, 30, 10, 30));
			Label title = new Label("Produkte Hinzufuegen");
			title.getStyleClass().add("mitarbeiter-content-title");
			contentWrapper.setTop(title);
			contentWrapper.setCenter(this.getContent());
		}

		return this.contentWrapper;
	}

	/**
	 * Setzt den Inhalt
	 * 
	 * @return GridPane mit Inhalt
	 */
	private GridPane getContent() {
		// grundstrucktur
		content = new GridPane();

		// labels und tf und button felder fuer Produkt
		Label lbName = new Label("Name");
		TextField tfName = new TextField();
		Label lbBeschreibung = new Label("Beschreibung");
		TextField tfBeschreibung = new TextField();
		Label lbEinkaufspreis = new Label("Einkaufspreis");
		TextField tfEinkaufspreis = new TextField();
		Label lbVerkaufspreis = new Label("Verkaufspreis");
		TextField tfVerkaufspreis = new TextField();

		Button btProdukt = new Button("Produkt Hinzufuegen");
		Label fehler = new Label("");
		
		// styling
		lbName.getStyleClass().add("inhaber-hinzufuegen-form-label");
		lbBeschreibung.getStyleClass().add("inhaber-hinzufuegen-form-label");
		lbEinkaufspreis.getStyleClass().add("inhaber-hinzufuegen-form-label");
		lbVerkaufspreis.getStyleClass().add("inhaber-hinzufuegen-form-label");
		tfName.getStyleClass().add("inhaber-hinzufuegen-form-textfield");
		tfBeschreibung.getStyleClass().add("inhaber-hinzufuegen-form-textfield");
		tfEinkaufspreis.getStyleClass().add("inhaber-hinzufuegen-form-textfield");
		tfVerkaufspreis.getStyleClass().add("inhaber-hinzufuegen-form-textfield");
		btProdukt.getStyleClass().add("inhaber-form-button");
		fehler.getStyleClass().add("registrierung-error-label");
		content.getStyleClass().add("inhaber-produkt-hinzufuegen-wrapper");
		GridPane.setMargin(btProdukt, new Insets(24, 0, 0, 0));

		// setzen auf Grid produkt
		content.add(lbName, 0, 0);
		content.add(tfName, 0, 1);
		content.add(lbBeschreibung, 0, 2);
		content.add(tfBeschreibung, 0, 3);
		content.add(lbEinkaufspreis, 0, 4);
		content.add(tfEinkaufspreis, 0, 5);
		content.add(lbVerkaufspreis, 0, 6);
		content.add(tfVerkaufspreis, 0, 7);
		content.add(btProdukt, 0, 8);
		content.add(fehler, 0, 9);

		// action von buttons
		btProdukt.setOnAction(e -> {

			boolean validInputs = true;
			List<TextField> inputs = new ArrayList<TextField>();
			inputs.add(tfName);
			inputs.add(tfBeschreibung);
			inputs.add(tfEinkaufspreis);
			inputs.add(tfVerkaufspreis);

			for (TextField i : inputs) {
				if (i.getText().isBlank())
					validInputs = false;
			}

			if (validInputs == true) {
				Produkt produkt;
				try {
					produkt = new Produkt(tfName.getText(), tfBeschreibung.getText(),
							Double.parseDouble(tfEinkaufspreis.getText()),
							Double.parseDouble(tfVerkaufspreis.getText()));
					List<Produkt> produkte = new ArrayList<Produkt>();
					produkte.add(produkt);
					inhaberSteuerung.lagerVerwalten(produkte, "hinzufuegen");
					
					if (fehler.getText().isBlank() == false) {
						fehler.setText(" ");
					}
				} catch (NumberFormatException i) {
					fehler.setText("Die Preise muessen Nummern sein");
				}

			} else {
				fehler.setText("Mindestens ein Feld ist Leer");

			}
			
			// Clear textfields
			tfName.clear();
			tfBeschreibung.clear();
			tfEinkaufspreis.clear();
			tfVerkaufspreis.clear();
		});
		
		return this.content;
	}
}

package de.wwu.sopra.darstellung.inhaber;

import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.anwendung.mitarbeiter.Inhabersteuerung;
import de.wwu.sopra.datenhaltung.management.Kategorie;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Produkt;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class ProduktKategorieHinzu extends InhaberOverview {
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
	public ProduktKategorieHinzu(Stage primaryStage, double width, double height, Inhabersteuerung inhaberSteuerung) {
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
			Label title = new Label("Produkte und Kategorien Hinzufuegen");
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
		Label lbName = new Label("Name:");
		TextField tfName = new TextField();
		Label lbBeschreibung = new Label("Beschreibung:");
		TextField tfBeschreibung = new TextField();
		Label lbEinkaufspreis = new Label("Einkaufspreis:");
		TextField tfEinkaufspreis = new TextField();
		Label lbVerkaufspreis = new Label("Verkaufspreis:");
		TextField tfVerkaufspreis = new TextField();

		Button btProdukt = new Button("Produkt Hinzufuegen");
		Label fehler = new Label("");

		// label button tf fuer Kategorie
		Label lbNamekat = new Label("Name:");
		TextField tfNamekat = new TextField();
		Label lbOberunter = new Label("Ober/Unterkategie(Optional)");
		ComboBox<String> chOberUNter = new ComboBox<>();
		Label lbOberunterwahl = new Label("Ober/Unterkategorie (pflicht wenn Kathegorie gewahlt)");
		ComboBox<String> chOberUNterwahl = new ComboBox<>();

		Button btKategorie = new Button("kategorie Hinzufuegen");
		Label fehler1 = new Label("");

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

		// setzen auf grid kategorie
		content.add(lbNamekat, 1, 0);
		content.add(tfNamekat, 1, 1);
		content.add(lbOberunter, 1, 2);
		content.add(chOberUNter, 1, 3);
		content.add(lbOberunterwahl, 1, 4);
		content.add(chOberUNterwahl, 1, 5);
		content.add(btKategorie, 1, 8);
		content.add(fehler1, 1, 9);

		// Comboboxen fuellen
		try {
			kategorien = new ArrayList<Kategorie>();
			for (Produkt i : Lager.getLager()) {
				if (!kategorien.contains(i.getKategorie())) {
					kategorien.add(i.getKategorie());
					Kategorie n = i.getKategorie();
					while (n.getOberkategorie() != null) {
						if (!kategorien.contains(n.getOberkategorie())) {
							kategorien.add(n.getOberkategorie());
							n = n.getOberkategorie();
						}
					}
				}
			}

			for (Kategorie i : kategorien) {
				chOberUNter.getItems().add(i.getName());
			}

		} catch (NullPointerException j) {
			fehler1.setText("Die Produkte haben keine Kategorie");

		}

		chOberUNterwahl.getItems().add("ober");
		chOberUNterwahl.getItems().add("unter");

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
					List<Produkt> produkte = new ArrayList<>();
					produkte.add(produkt);
					inhaberSteuerung.lagerVerwalten(produkte, "hinzufuegen");
					if (fehler.getText().isBlank() == false) {
						fehler.setText(" ");
					}
				} catch (NumberFormatException i) {
					fehler.setText("Die Preise muessen ein Double sein");
				}

			} else {
				fehler.setText("Mindestens ein Feld ist Leer");

			}
		});

		btKategorie.setOnAction(e -> {
			Kategorie kat = null;
			String name = tfNamekat.getText();
			if (name.isBlank()) {
				fehler1.setText("kategorie hat keinen Namen");
			} else {
				if (chOberUNter.getValue() == null) {
					inhaberSteuerung.kategorieBearbeiten(new Kategorie(name), kat, null, name);
				} else {
					if (chOberUNterwahl.getValue() == null) {
						fehler1.setText(
								"wenn ein kategorie Gewahlt ist muss entschieden werden, /n ob es eine Ober oder unterkategorie ist.");
					} else {
						for (Kategorie m : kategorien) {
							if (chOberUNter.getValue().equals(m.getName()))
								kat = m;
						}
						inhaberSteuerung.kategorieBearbeiten(new Kategorie(name), kat, chOberUNterwahl.getValue(),
								name);
					}
				}

			}
		});
		return this.content;
	}
}

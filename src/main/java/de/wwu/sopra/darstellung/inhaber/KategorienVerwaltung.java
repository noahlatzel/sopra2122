package de.wwu.sopra.darstellung.inhaber;

import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.anwendung.mitarbeiter.Inhabersteuerung;
import de.wwu.sopra.datenhaltung.management.Kategorie;
import de.wwu.sopra.datenhaltung.management.Lager;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Darstellungsklasse fuer StartseiteKunde
 * 
 * @author Valeria Vassallo
 *
 */
public class KategorienVerwaltung extends InhaberOverview {
	// Erstellung von Variable
	BorderPane contentWrapper;
	HBox content;
	VBox kategorieHinzufuegen;
	VBox kategorieLoeschen;
	List<Kategorie> kategorien;

	/**
	 * Zeigt das Fenster zur Bearbeitung von Produktkategorien
	 * 
	 * @param primaryStage     PrimaryStage
	 * @param width            Breite des Fensters
	 * @param height           Hoehe des Fensters
	 * @param inhaberSteuerung InhaberSteuerung
	 */
	public KategorienVerwaltung(Stage primaryStage, double width, double height, Inhabersteuerung inhaberSteuerung) {
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
			Label title = new Label("Kategorien Verwaltung");
			title.getStyleClass().add("mitarbeiter-content-title");
			contentWrapper.setTop(title);
			contentWrapper.setCenter(this.getContent());
		}

		return this.contentWrapper;
	}

	/**
	 * Setzt den Inhalt
	 * 
	 * @return HBox mit Inhalt
	 */
	private HBox getContent() {
		// grundstrucktur
		content = new HBox(2);
		content.getStyleClass().add("inhaber-kategorie-verwaltung-content");

		content.getChildren().add(this.setKategorieHinzufuegen());
		content.getChildren().add(this.setKategorieLoeschen());

		return this.content;
	}

	private VBox setKategorieHinzufuegen() {
		if (this.kategorieHinzufuegen == null) {
			kategorieHinzufuegen = new VBox();

			Label lbKategorieHinzufuegenTitel = new Label("Kategorie Hinzufuegen");

			// label button tf fuer Kategorie
			Label lbNamekat = new Label("Name:");
			TextField tfNamekat = new TextField();
			Label lbOberunter = new Label("Ober/Unterkategie(Optional)");
			ComboBox<String> chOberUNter = new ComboBox<>();
			Label lbOberunterwahl = new Label("Ober/Unterkategorie (pflicht wenn Kategorie gewahlt)");
			ComboBox<String> chOberUNterwahl = new ComboBox<>();

			Button btKategorie = new Button("Kategorie Hinzufuegen");
			Label fehler1 = new Label("");

			// Styling
			kategorieHinzufuegen.getStyleClass().add("inhaber-kategorie-verwaltung-inner-hinzufuegen");
			lbKategorieHinzufuegenTitel.getStyleClass().add("inhaber-kategorie-verwaltung-inner-title");
			lbNamekat.getStyleClass().add("inhaber-kategorie-verwaltung-label");
			lbOberunter.getStyleClass().add("inhaber-kategorie-verwaltung-label");
			lbOberunterwahl.getStyleClass().add("inhaber-kategorie-verwaltung-label");
			tfNamekat.getStyleClass().add("inhaber-kategorie-verwaltung-textfield");
			chOberUNter.getStyleClass().add("inhaber-kategorie-verwaltung-inner-combobox");
			chOberUNterwahl.getStyleClass().add("inhaber-kategorie-verwaltung-inner-combobox");
			btKategorie.getStyleClass().add("inhaber-form-button");
			fehler1.getStyleClass().add("registrierung-error-label");
			VBox.setMargin(btKategorie, new Insets(8, 0, 0, 0));

			// In VBox setzen
			kategorieHinzufuegen.getChildren().add(lbKategorieHinzufuegenTitel);
			kategorieHinzufuegen.getChildren().add(lbNamekat);
			kategorieHinzufuegen.getChildren().add(tfNamekat);
			kategorieHinzufuegen.getChildren().add(lbOberunter);
			kategorieHinzufuegen.getChildren().add(chOberUNter);
			kategorieHinzufuegen.getChildren().add(lbOberunterwahl);
			kategorieHinzufuegen.getChildren().add(chOberUNterwahl);
			kategorieHinzufuegen.getChildren().add(btKategorie);
			kategorieHinzufuegen.getChildren().add(fehler1);

			// Comboboxen fuellen
			try {
				kategorien = new ArrayList<Kategorie>();

				for (Kategorie kategorie : Lager.getKategorien()) {

					kategorien.add(kategorie);

				}

				for (Kategorie kategorie : kategorien) {
					chOberUNter.getItems().add(kategorie.getName());
				}

			} catch (NullPointerException exception) {
				fehler1.setText("Es existieren keine Kategorien");
			}

			chOberUNterwahl.getItems().add("ober");
			// chOberUNterwahl.getItems().add("unter");

			// action von buttons
			btKategorie.setOnAction(e -> {
				Kategorie kategorie = null;
				String name = tfNamekat.getText();
				if (name.isBlank()) {
					fehler1.setText("Kategorie hat keinen Namen");
				} else {
					if (chOberUNter.getValue() == null) {
						inhaberSteuerung.kategorieBearbeiten(new Kategorie(name), kategorie, null, name);
					} else {
						if (chOberUNterwahl.getValue() == null) {
							fehler1.setText(
									"Wenn eine Kategorie gewaehlt ist muss entschieden werden, /n ob es eine Ober oder Unterkategorie ist.");
						} else {
							for (Kategorie kategorie2 : kategorien) {
								if (chOberUNter.getValue().equals(kategorie2.getName()))
									kategorie = kategorie2;
							}
							inhaberSteuerung.kategorieBearbeiten(new Kategorie(name), kategorie,
									chOberUNterwahl.getValue(), name);
						}
					}

				}

				// Clear textfield
				tfNamekat.clear();
				primaryStage
						.setScene(new KategorienVerwaltung(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
			});
		}

		return this.kategorieHinzufuegen;
	}

	private VBox setKategorieLoeschen() {
		if (this.kategorieLoeschen == null) {
			kategorieLoeschen = new VBox();

			Label lbKategorieLoeschenTitel = new Label("Kategorie Loeschen");

			// combobox und button zum Loeschen
			Button btkategorieLoeschen = new Button("Loeschen");
			ComboBox<String> cbLoeschen = new ComboBox<String>();
			Label fehler2 = new Label();

			// Styling
			kategorieLoeschen.getStyleClass().add("inhaber-kategorie-verwaltung-inner-loeschen");
			lbKategorieLoeschenTitel.getStyleClass().add("inhaber-kategorie-verwaltung-inner-title");
			cbLoeschen.getStyleClass().add("inhaber-kategorie-verwaltung-inner-combobox");
			btkategorieLoeschen.getStyleClass().add("inhaber-form-button");
			fehler2.getStyleClass().add("registrierung-error-label");
			VBox.setMargin(btkategorieLoeschen, new Insets(8, 0, 0, 0));

			// setzen von Loeschen
			kategorieLoeschen.getChildren().add(lbKategorieLoeschenTitel);
			kategorieLoeschen.getChildren().add(cbLoeschen);
			kategorieLoeschen.getChildren().add(btkategorieLoeschen);
			kategorieLoeschen.getChildren().add(fehler2);

			// Comboboxen fuellen
			try {
				kategorien = new ArrayList<Kategorie>();

				for (Kategorie kategorie : Lager.getKategorien()) {

					kategorien.add(kategorie);

				}

				for (Kategorie kategorie : kategorien) {
					cbLoeschen.getItems().add(kategorie.getName());
				}

			} catch (NullPointerException excpt) {
				fehler2.setText("Es existieren keine Kategorien");
			}

			btkategorieLoeschen.setOnAction(e -> {
				Kategorie kategorie = null;
				for (Kategorie kategorie2 : kategorien) {
					if (cbLoeschen.getValue() != null && cbLoeschen.getValue().equals(kategorie2.getName()))
						kategorie = kategorie2;
				}
				try {
					inhaberSteuerung.kategorieLoeschen(kategorie);
					primaryStage.setScene(
							new KategorienVerwaltung(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
				} catch (IllegalArgumentException k) {
					fehler2.setText("Es existieren noch Unterkategorien \noder Produkte aus dieser Kategorie");
				} catch (AssertionError a) {
					fehler2.setText("Bitte Kategorie waehlen.");
				}
			});
		}

		return this.kategorieLoeschen;
	}
}

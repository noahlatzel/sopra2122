/**
 * 
 */
package de.wwu.sopra.darstellung.inhaber;

import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.anwendung.mitarbeiter.Inhabersteuerung;
import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Valeria Vassallo
 *
 */
public class FahrzeugdatenAendern extends InhaberOverview {
	// Erstellung von Variablen
	BorderPane contentWrapper;
	GridPane gridPane;
	ScrollPane scrollPaneFahrzeug;
	Button loeschenButton;
	VBox form;
	ObservableList<Integer> fahrzeuge;
	ListView<Integer> listView;
	List<Fahrzeug> fahrzeugeListe = new ArrayList<Fahrzeug>();
	List<Integer> fahrzeugeid;
	Label lblFahrzeugnummer = new Label("Fahrzeugnummer");
	TextField tfFahrzeugNummer = new TextField();
	Label lblKapazitaet = new Label("Kapazitaet");
	TextField tfKapazitaet = new TextField();
	Button btnBearbeiten = new Button("Fahrzeug Bearbeiten");
	Button btnFahrzeugHinzufuegen = new Button("Fahrzeug Hinzufuegen");
	Label errorText = new Label("");

	/**
	 * Zeigt das Fenster zur Aenderung von Fahrzeugdaten
	 * 
	 * @param primaryStage     PrimaryStage
	 * @param width            Breite des Fensters
	 * @param height           Hoehe des Fensters
	 * @param inhaberSteuerung InhaberSteuerung
	 */
	public FahrzeugdatenAendern(Stage primaryStage, double width, double height, Inhabersteuerung inhaberSteuerung) {
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
			Label title = new Label("Fahrzeugdaten Aendern");
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
		// GridPane als Main Content Wrapper
		if (this.gridPane == null) {
			gridPane = new GridPane();
			gridPane.add(this.setFahrzeugListeScrollPane(), 0, 0);
			gridPane.add(this.setLoeschenButton(), 0, 1);
			gridPane.add(this.setForm(), 1, 0);

			gridPane.getStyleClass().add("inhaber-fahrzeugdaten-content-wrapper");
		}

		return this.gridPane;
	}

	/**
	 * Erzeugt ScrollPane fuer Fahrzeuge
	 * 
	 * @return ScrollPane fuer Fahrzeuge
	 */
	private ScrollPane setFahrzeugListeScrollPane() {
		ScrollPane scrollPane = new ScrollPane();
		// Get Fahrzeugen Liste
		for (Fahrzeug i : inhaberSteuerung.fahrzeugeAnzeigen()) {
			fahrzeugeListe.add(i);
		}
		// Neue Liste mit den IDs von den Fahrzeugen
		fahrzeugeid = new ArrayList<Integer>();
		try {
			for (Fahrzeug i : fahrzeugeListe) {
				fahrzeugeid.add(i.getFahrzeugNummer());
			}
		} catch (NullPointerException k) {
			errorText.setText("es sind noch keine Fahrzeuge vorhanden");
		}

		// Styling
		lblFahrzeugnummer.getStyleClass().add("inhaber-hinzufuegen-form-label");
		lblKapazitaet.getStyleClass().add("inhaber-hinzufuegen-form-label");
		tfFahrzeugNummer.getStyleClass().add("inhaber-hinzufuegen-form-textfield");
		tfKapazitaet.getStyleClass().add("inhaber-hinzufuegen-form-textfield");
		btnBearbeiten.getStyleClass().add("inhaber-form-button");
		btnFahrzeugHinzufuegen.getStyleClass().add("inhaber-form-button");

		// Erstellung einer ListView, um Fahrzeugen zu zeigen
		fahrzeuge = (ObservableList<Integer>) FXCollections.observableArrayList(fahrzeugeid);
		listView = new ListView<Integer>(fahrzeuge);
		scrollPane.setContent(listView);
		listView.setMinWidth(600);
		scrollPane.setMinWidth(600);

		listView.setOnMouseClicked(e -> {
			try {
				// Data TextFields fuellen
				int index = listView.getSelectionModel().getSelectedIndex();

				tfFahrzeugNummer.setText(String.valueOf(fahrzeugeListe.get(index).getFahrzeugNummer()));
				tfKapazitaet.setText(String.valueOf(fahrzeugeListe.get(index).getKapazitaet()));

				// Button, um Fahrzeug zu bearbeiten
				btnBearbeiten.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						if (tfFahrzeugNummer.getText() != String
								.valueOf(fahrzeugeListe.get(index).getFahrzeugNummer())) {
							if (tfFahrzeugNummer.getText().isBlank())
								errorText.setText("Angabe kann nicht leer sein");
							int neueFahrzeugnummer = Integer.parseInt(tfFahrzeugNummer.getText());
							inhaberSteuerung.fahrzeugDatenAendern(fahrzeugeListe.get(index), neueFahrzeugnummer,
									fahrzeugeListe.get(index).getKapazitaet());
						}

						if (tfKapazitaet.getText() != String.valueOf(fahrzeugeListe.get(index).getKapazitaet())) {
							if (tfKapazitaet.getText().isBlank())
								errorText.setText("Angabe kann nicht leer sein");
							float neueKapazitaet = Float.parseFloat(tfKapazitaet.getText());
							inhaberSteuerung.fahrzeugDatenAendern(fahrzeugeListe.get(index),
									fahrzeugeListe.get(index).getFahrzeugNummer(), neueKapazitaet);
						}
					}
				});

			} catch (IndexOutOfBoundsException E) {
				E.printStackTrace();
			}

		});
		return scrollPane;
	}

	/**
	 * Erzeugt VBox mit Daten
	 * 
	 * @return VBox mit Daten
	 */
	public VBox setForm() {
		// Temp!!! TextFields, um Fahrzeug zu sehen und bearbeiten
		if (this.form == null) {
			form = new VBox();
			form.getChildren().add(lblFahrzeugnummer);
			form.getChildren().add(tfFahrzeugNummer);
			form.getChildren().add(lblKapazitaet);
			form.getChildren().add(tfKapazitaet);
			form.getChildren().add(btnBearbeiten);
			form.getChildren().add(btnFahrzeugHinzufuegen);
			form.getChildren().add(errorText);

			form.getStyleClass().add("inhaber-fahrzeugdaten-form");

			// speichern eines neuen Fahrzeugs
			this.btnFahrzeugHinzufuegen.setOnAction(e -> {
				if (!tfKapazitaet.getText().isBlank()) {
					if (tfFahrzeugNummer.getText().isBlank()) {
						try {
							this.inhaberSteuerung.fahrzeugHinzufuegen(Integer.parseInt(tfKapazitaet.getText()));
							this.primaryStage.setScene(
									new FahrzeugdatenAendern(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
						} catch (NumberFormatException i) {
							errorText.setText("Die kapazitaet muss ine Ganzzahl sein");
						}
					} else
						errorText.setText("Die Nummer wird automatishc generiert \n und muss deshalb frei bleiben");
				} else
					errorText.setText("Das neue fahrzeug braucht eine Kapazitaet");
			});
		}

		return this.form;
	}

	/**
	 * Erzeugt Loeschen Button
	 * 
	 * @return Loeschen Button
	 */
	public Button setLoeschenButton() {
		// Erstellung von Loeschen-Button, um ein Fahrzeug zu loeschen
		if (this.loeschenButton == null) {
			loeschenButton = new Button("Fahrzeug Loeschen");
			loeschenButton.getStyleClass().add("inhaber-form-button");
			loeschenButton.setOnAction(e -> {
				int fzeugIdx = listView.getSelectionModel().getSelectedIndex();
				inhaberSteuerung.fahrzeugLoeschen(fahrzeugeListe.get(fzeugIdx));
				fahrzeuge.remove(fzeugIdx);
			});
		}
		return this.loeschenButton;
	}

}

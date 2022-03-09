package de.wwu.sopra.darstellung.fahrer;

import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.anwendung.mitarbeiter.Fahrersteuerung;
import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import de.wwu.sopra.datenhaltung.management.FahrzeugStatus;
import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * oberflaeche zum wahlendes Fahrzeugs
 * 
 * @author Johannes Thiel
 *
 */

public class FahrzeugAuswaehlen extends OverviewFahrer {

	// Erstellung von Variablen
	BorderPane contentWrapper;
	VBox content;
	Label error = new Label();
	Text infoText = new Text();
	Button btnFahrzeugAuswaehlen;

	/**
	 * Erzeugt FahrzeugAuswaehlen
	 * 
	 * @param steuerung    FahrerSteuerung
	 * @param primaryStage PrimaryStage
	 * @param width        Breite des Fensters
	 * @param height       Hoehe des Fensters
	 */
	public FahrzeugAuswaehlen(Fahrersteuerung steuerung, Stage primaryStage, double width, double height) {
		super(steuerung, primaryStage, width, height);
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
			Label title = new Label("Fahrzeug Auswaehlen");
			title.getStyleClass().add("mitarbeiter-content-title");
			contentWrapper.setTop(title);
			contentWrapper.setCenter(this.setContent());
		}

		return this.contentWrapper;
	}

	/**
	 * Erstellt Oberflaeche
	 * 
	 * @return TilePane mit Oberflaeche
	 */
	private VBox setContent() {
		if (this.content == null) {			
			// Pane wird erstellt
			content = new VBox();
			content.getStyleClass().add("fahrer-fahrzeug-auswaehlen-wrapper");
			btnFahrzeugAuswaehlen = new Button("Fahrzeug Auswaehlen");
			btnFahrzeugAuswaehlen.getStyleClass().add("inhaber-form-button");
			ScrollPane scrollPane = new ScrollPane();
			scrollPane.getStyleClass().add("fahrer-fahrzeug-auswaehlen-scrollpane");
			
			// listen zur verwaltung
			List<Fahrzeug> fahrzeug = new ArrayList<Fahrzeug>(FahrzeugRegister.getFahrzeuge());
			List<Fahrzeug> fahrzeugebeleget = new ArrayList<Fahrzeug>();
			
			// fuellen der Listen
			// belegte Fahrzeuge
			try {
				for (Fahrzeug i : fahrzeug) {
					if (i.getStatus() == FahrzeugStatus.BELEGT)
						fahrzeugebeleget.add(i);
				}
			} catch (NullPointerException l) {
				scrollPane.setVisible(false);
				infoText.setText("Es existieren keine Fahrzeuge im System");
				content.getChildren().add(infoText);
			}
			
			if (fahrzeugebeleget.isEmpty()) {
				scrollPane.setVisible(false);
				btnFahrzeugAuswaehlen.setVisible(false);
				infoText.setText("Es stehen keine Fahrzeuge zur auswahl");
				infoText.getStyleClass().add("fahrer-keine-fahrzeuge-info-text");
				content.getChildren().add(infoText);
			}
			
			// id der belegten Fahrzeuge
			List<Integer> fahrzeugeid = new ArrayList<Integer>();
			for (int i = 0; i < fahrzeugebeleget.size(); i++)
				fahrzeugeid.add(fahrzeugebeleget.get(i).getFahrzeugNummer());
			
			// observable list erstellen
			ObservableList<Integer> fahrzeuge = (ObservableList<Integer>) FXCollections.observableArrayList(fahrzeugeid);
			ListView<Integer> listView = new ListView<Integer>(fahrzeuge);
			
			// liste auf die Scrollpane
			scrollPane.setContent(listView);
			listView.setMinWidth(600);
			scrollPane.setMinWidth(600);
			
			// action zum auswahlen der Fahrzeuge
			btnFahrzeugAuswaehlen.setOnAction(e -> {
				int index = listView.getSelectionModel().getSelectedIndex();
				steuerung.fahrzeugZuordnen(fahrzeugebeleget.get(index));
				
				infoText.setText("Fahrzeug gewaehlt!");
				infoText.getStyleClass().add("fahrer-fahrzeug-ausgewaehlt-info-text");
				content.getChildren().add(infoText);
			});
			
			content.getChildren().add(scrollPane);
			content.getChildren().add(btnFahrzeugAuswaehlen);
		}
		
		return content;
	}

}

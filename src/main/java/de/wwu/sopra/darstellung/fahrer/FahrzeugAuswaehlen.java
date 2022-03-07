package de.wwu.sopra.darstellung.fahrer;

import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.anwendung.mitarbeiter.Fahrersteuerung;
import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import de.wwu.sopra.datenhaltung.management.FahrzeugStatus;
import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

/**
 * oberflaeche zum wahlendes Fahrzeugs
 * 
 * @author Johannes Thiel
 *
 */

public class FahrzeugAuswaehlen extends OverviewFahrer {

	Label error = new Label();

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
		root.setCenter(setScrollPane());

		root.setBottom(error);
	}

	/**
	 * Erstellt Oberflaeche
	 * 
	 * @return ScrollPane mit Oberflaeche
	 */
	private ScrollPane setScrollPane() {

		// Pane wird erstellt
		ScrollPane scrollPane = new ScrollPane();

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
			error.setText("Es existieren keine Fahrzeuge im System");
			scrollPane.setVisible(false);
		}

		if (fahrzeugebeleget.isEmpty()) {
			scrollPane.setVisible(false);
			error.setText("Es stehen keine Fahrzeuge zur auswahl");

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
		scrollPane.setMinWidth(000);

		// action zum auswahlen der Fahrzeuge
		listView.setOnMouseClicked(e -> {
			try {
				int index = listView.getSelectionModel().getSelectedIndex();
				steuerung.fahrzeugZuordnen(fahrzeugebeleget.get(index));

				error.setText("fahrzeug gewaehlt");
			} catch (IndexOutOfBoundsException k) {

			}

		});
		return scrollPane;

	}

}

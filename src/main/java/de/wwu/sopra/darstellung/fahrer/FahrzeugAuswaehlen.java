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

public class FahrzeugAuswaehlen extends OverviewFahrer {

	public FahrzeugAuswaehlen(Fahrersteuerung steuerung, Stage primaryStage, double width, double height) {
		super(steuerung, primaryStage, width, height);
		root.setCenter(setScrollPane());
	}

	public ScrollPane setScrollPane() {
		ScrollPane scrollPane = new ScrollPane();
		List<Fahrzeug> fahrzeug = new ArrayList<Fahrzeug>(FahrzeugRegister.getFahrzeuge());
		List<Fahrzeug> fahrzeugebeleget = new ArrayList<Fahrzeug>();
		for (Fahrzeug i : fahrzeug) {
			if (i.getStatus() == FahrzeugStatus.BELEGT)
				fahrzeugebeleget.add(i);
		}
		List<Integer> fahrzeugeid = new ArrayList<Integer>();
		for (int i = 0; i < fahrzeugebeleget.size(); i++)
			fahrzeugeid.add(fahrzeugebeleget.get(i).getFahrzeugNummer());

		ObservableList<Integer> fahrzeuge = (ObservableList<Integer>) FXCollections.observableArrayList(fahrzeugeid);
		ListView<Integer> listView = new ListView<Integer>(fahrzeuge);
		scrollPane.setContent(listView);
		listView.setMinWidth(600);
		scrollPane.setMinWidth(000);
		listView.setOnMouseClicked(e -> {
			try {
				int index = listView.getSelectionModel().getSelectedIndex();
				steuerung.fahrzeugZuordnen(fahrzeugebeleget.get(index));
				root.setRight(new Label("wagen :" + fahrzeugeid.get(index) + "ist jetzt belegt"));
			} catch (IndexOutOfBoundsException k) {
				System.out.println("kein objekt an dieser Stelle, bitte waehle ein Rezept");
			}

		});
		return scrollPane;

	}

}

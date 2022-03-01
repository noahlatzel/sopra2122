package de.wwu.sopra.darstellung.fahrer;

import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.anwendung.mitarbeiter.Fahrersteuerung;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class RouteAnzeigen extends OverviewFahrer {

	public RouteAnzeigen(Fahrersteuerung steuerung, Stage primaryStage, double width, double height) {
		super(steuerung, primaryStage, width, height);
		this.zeigeRoute();
	}

	public void zeigeRoute() {
		ScrollPane tabelle = new ScrollPane();
		root.setCenter(tabelle);
		List<String> ausgabeListe = new ArrayList<String>();
		try {
			List<Bestellung> bestellungen = steuerung.routeAusgeben().getBestellungen();

			for (Bestellung i : bestellungen) {
				ausgabeListe.add("Adresse: " + i.getKunde().getAdresse() + " Bestellnummer: " + i.getBestellnummer());
			}
		} catch (NullPointerException k) {
			System.out.println("Keine Route im Fahrzeug oder kein Fahrzeug");
		}

		ObservableList<String> stopps = (ObservableList<String>) FXCollections.observableArrayList(ausgabeListe);
		ListView<String> listView = new ListView<String>(stopps);
		tabelle.setContent(listView);
		listView.setMinWidth(600);
	}
}

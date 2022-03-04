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

/**
 * Oberflaeche zum anzeigen der Route des Fahrzeugs
 * 
 * @author Johannes Thiel
 *
 */
public class RouteAnzeigen extends OverviewFahrer {
	/**
	 * Erzeugt RouteAnzeigen
	 * 
	 * @param steuerung    FahrerSteuerung
	 * @param primaryStage PrimaryStage
	 * @param width        Breite des Fensters
	 * @param height       Hoehe des Fensters
	 */
	public RouteAnzeigen(Fahrersteuerung steuerung, Stage primaryStage, double width, double height) {
		super(steuerung, primaryStage, width, height);
		this.zeigeRoute();
	}

	/**
	 * Zeigt Route
	 */
	private void zeigeRoute() {

		// pane wird erstellt
		ScrollPane tabelle = new ScrollPane();
		root.setCenter(tabelle);

		// liste der bestellungen im format
		List<String> ausgabeListe = new ArrayList<String>();

		// fuellen der Liste
		try {
			List<Bestellung> bestellungen = steuerung.routeAusgeben().getBestellungen();

			for (Bestellung i : bestellungen) {
				ausgabeListe.add("Adresse: " + i.getKunde().getAdresse() + " Bestellnummer: " + i.getBestellnummer());
			}
		} catch (NullPointerException k) {
			System.out.println("Keine Route im Fahrzeug oder kein Fahrzeug");
		}

		// Liste wird angeszeigt
		ObservableList<String> stopps = (ObservableList<String>) FXCollections.observableArrayList(ausgabeListe);
		ListView<String> listView = new ListView<String>(stopps);
		tabelle.setContent(listView);
		listView.setMinWidth(600);
	}
}

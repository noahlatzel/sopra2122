package de.wwu.sopra.darstellung.fahrer;

import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.anwendung.mitarbeiter.Fahrersteuerung;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Oberflaeche zum anzeigen der Route des Fahrzeugs
 * 
 * @author Johannes Thiel
 *
 */
public class RouteAnzeigen extends OverviewFahrer {
	// Erstellung von Variable
	BorderPane contentWrapper;
	VBox content;
	Label infoText;
	
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
			Label title = new Label("Route Anzeigen");
			title.getStyleClass().add("mitarbeiter-content-title");
			contentWrapper.setTop(title);
			contentWrapper.setCenter(this.setContent());
		}

		return this.contentWrapper;
	}

	/**
	 * Zeigt Tabelle mit Adressen
	 * @return content	VBox mit Tabelle von Adressen oder mit InfoText wenn leer
	 */
	private VBox setContent() {
		if (this.content == null) {
			content = new VBox();
			content.getStyleClass().add("fahrer-route-anzeigen-content-wrapper");
			infoText = new Label();
			infoText.getStyleClass().add("fahrer-route-anzeigen-info-text");
			
			// pane wird erstellt
			ScrollPane tabelle = new ScrollPane();
			tabelle.getStyleClass().add("fahrer-route-anzeigen-scrollpane");
			
			// liste der bestellungen im format
			List<String> ausgabeListe = new ArrayList<String>();
			
			// fuellen der Liste
			try {
				List<Bestellung> bestellungen = steuerung.routeAusgeben().getBestellungen();
				
				for (Bestellung bestellung : bestellungen) {
					ausgabeListe.add("Bestellung#" + bestellung.getBestellnummer() + "-Adresse: " + bestellung.getKunde().getAdresse());
				}
			} catch (NullPointerException k) {
				// Wenn leer, Label und ImageView werden gezeigt
				Label keineRoute = new Label("Es existiert aktuell keine Route");
				keineRoute.getStyleClass().add("fahrer-keine-route-label");
				ImageView view = new ImageView(getClass().getResource("sweat-smiley-face.png").toExternalForm());
				view.setFitWidth(60);
				view.setFitHeight(60);
				content.getChildren().add(view);
				content.getChildren().add(keineRoute);
				content.setAlignment(Pos.CENTER);
				tabelle.setVisible(false);
				infoText.setVisible(false);
			}
			
			// Liste wird angezeigt
			ObservableList<String> stopps = (ObservableList<String>) FXCollections.observableArrayList(ausgabeListe);
			ListView<String> listView = new ListView<String>(stopps);
			listView.setMinWidth(600);
			tabelle.setContent(listView);
			
			// Set Text
			infoText.setText("Adressen aller Bestellungen");

			// Elemente auf VBox setzen
			content.getChildren().add(infoText);
			content.getChildren().add(tabelle);
		}

		return this.content;
	}
}

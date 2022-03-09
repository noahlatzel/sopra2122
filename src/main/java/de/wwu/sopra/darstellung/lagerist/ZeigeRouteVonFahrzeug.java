package de.wwu.sopra.darstellung.lagerist;

import de.wwu.sopra.anwendung.mitarbeiter.Lageristensteuerung;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import de.wwu.sopra.datenhaltung.management.Route;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * GUI Klasse fuer ZeigeRouteVonFahrzeug
 * 
 * @author Noah Latzel
 */
public class ZeigeRouteVonFahrzeug extends LageristOverview {
	TilePane tilePane;
	BorderPane contentWrapper;
	ScrollPane scrollPaneFahrzeug;
	TableView<Fahrzeug> tableViewFahrzeug;
	ScrollPane scrollPaneBestellung;
	TableView<Bestellung> tableViewBestellung;
	ObservableList<Fahrzeug> fahrzeuge;
	ObservableList<Bestellung> bestellungen;

	/**
	 * Erzeugt das Fenster, um die Route eines Fahrzeugs anzuzeigen
	 * 
	 * @param primaryStage        PrimaryStage
	 * @param width               Breite des Fensters
	 * @param height              Hoehe des Fensters
	 * @param lageristenSteuerung LageristenSteuerung
	 */
	public ZeigeRouteVonFahrzeug(Stage primaryStage, double width, double height,
			Lageristensteuerung lageristenSteuerung) {
		super(primaryStage, width, height, lageristenSteuerung);

		root.setCenter(this.setContentWrapper());
	}

	/**
	 * Erzeugt ContentWrapper fuer Titel
	 * 
	 * @return ContentWrapper fuer Titel
	 */
	private BorderPane setContentWrapper() {
		// ContentWrapper, um den Titel einzuschliessen
		if (this.contentWrapper == null) {
			contentWrapper = new BorderPane();
			contentWrapper.setPadding(new Insets(10, 30, 10, 30));
			Label title = new Label("Routen anzeigen");
			title.getStyleClass().add("mitarbeiter-content-title");
			contentWrapper.setTop(title);
			contentWrapper.setCenter(this.setContent());
		}

		return this.contentWrapper;
	}

	private TilePane setContent() {
		if (tilePane == null) {
			tilePane = new TilePane();
			tilePane.setPadding(new Insets(20));
			tilePane.getChildren().add(this.setScrollPaneFahrzeug());
			tilePane.getChildren().add(this.setScrollPaneRoute());
		}
		return tilePane;
	}

	/**
	 * Generiert das ScrollPane
	 * 
	 * @return ScrollPane fuer das Setzen des Fahrzeugs
	 */
	public ScrollPane setScrollPaneFahrzeug() {
		if (scrollPaneFahrzeug == null) {
			fahrzeuge = FXCollections.observableArrayList();
			scrollPaneFahrzeug = new ScrollPane();
			for (Fahrzeug f : lageristenSteuerung.getFahrzeugeMitRoute()) {
				fahrzeuge.add(f);
			}
			TableColumn<Fahrzeug, String> fahrzeugSpalte = new TableColumn<>("Fahrzeugnummer");
			fahrzeugSpalte.setCellValueFactory(new PropertyValueFactory<>("fahrzeugNummer"));
			TableColumn<Fahrzeug, String> kapazitaetSpalte = new TableColumn<>("Status");
			kapazitaetSpalte.setCellValueFactory(new PropertyValueFactory<>("status"));
			tableViewFahrzeug = new TableView<Fahrzeug>();
			tableViewFahrzeug.getColumns().add(fahrzeugSpalte);
			tableViewFahrzeug.getColumns().add(kapazitaetSpalte);
			tableViewFahrzeug.setItems(fahrzeuge);
			scrollPaneFahrzeug.setContent(tableViewFahrzeug);
			tableViewFahrzeug.setOnMouseClicked(a -> {
				if (this.tableViewFahrzeug.getSelectionModel().getSelectedItem() != null) {
					this.setScrollPaneRouteDaten();
				}
			});
			tableViewFahrzeug.getStyleClass().add("inhaber-mitarbeitern-tableview");
			tableViewFahrzeug.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		}
		return scrollPaneFahrzeug;
	}

	/**
	 * Setzt die Route fuer das ScrollPane
	 * 
	 * @return Route fuer das ScrollPane
	 */
	public ScrollPane setScrollPaneRoute() {
		if (scrollPaneBestellung == null) {
			scrollPaneBestellung = new ScrollPane();
			TableColumn<Bestellung, String> bestellnrSpalte = new TableColumn<>("Bestellnummer");
			bestellnrSpalte.setCellValueFactory(new PropertyValueFactory<>("bestellnummer"));
			TableColumn<Bestellung, String> adressSpalte = new TableColumn<>("Adresse");
			adressSpalte.setCellValueFactory(new PropertyValueFactory<>("adresse"));
			TableColumn<Bestellung, String> statusSpalte = new TableColumn<>("Status");
			statusSpalte.setCellValueFactory(new PropertyValueFactory<>("status"));
			tableViewBestellung = new TableView<Bestellung>();
			tableViewBestellung.getColumns().add(bestellnrSpalte);
			tableViewBestellung.getColumns().add(adressSpalte);
			tableViewBestellung.getColumns().add(statusSpalte);
			tableViewBestellung.getStyleClass().add("inhaber-mitarbeitern-tableview");
			tableViewBestellung.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			scrollPaneBestellung.setContent(tableViewBestellung);
		}
		return scrollPaneBestellung;
	}

	/**
	 * Setzt die Daten fuer das ScrollPane
	 */
	public void setScrollPaneRouteDaten() {
		if (scrollPaneBestellung != null) {
			bestellungen = FXCollections.observableArrayList();
			Fahrzeug fahrzeug = this.tableViewFahrzeug.getSelectionModel().getSelectedItem();
			Route route = fahrzeug.getRoute();
			for (Bestellung b : route.getBestellungen()) {
				bestellungen.add(b);
			}
			tableViewBestellung.setItems(bestellungen);
			scrollPaneBestellung.setContent(tableViewBestellung);
		}
	}
}

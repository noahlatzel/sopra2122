package de.wwu.sopra.darstellung.lagerist;

import de.wwu.sopra.anwendung.mitarbeiter.Lageristensteuerung;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import de.wwu.sopra.datenhaltung.management.Route;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class ZeigeRouteVonFahrzeug extends LageristOverview {
	TilePane tilePane;
	ScrollPane scrollPaneFahrzeug;
	TableView<Fahrzeug> tableViewFahrzeug;
	ScrollPane scrollPaneBestellung;
	TableView<Bestellung> tableViewBestellung;
	ObservableList<Fahrzeug> fahrzeuge;
	ObservableList<Bestellung> bestellungen;

	public ZeigeRouteVonFahrzeug(Stage primaryStage, double width, double height,
			Lageristensteuerung lageristenSteuerung) {
		super(primaryStage, width, height, lageristenSteuerung);
		root.setCenter(new Label("Route von Fahrzeugen anzeigen..."));
		tilePane = new TilePane();
		tilePane.getChildren().add(this.setScrollPaneFahrzeug());

		root.setCenter(tilePane);
	}

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
				if (!(tilePane.getChildren().contains(this.setScrollPaneRoute()))
						& this.tableViewFahrzeug.getSelectionModel().getSelectedItem() != null) {
					tilePane.getChildren().add(this.setScrollPaneRoute());
				}
			});
		}
		return scrollPaneFahrzeug;
	}

	public ScrollPane setScrollPaneRoute() {
		if (scrollPaneBestellung == null) {
			bestellungen = FXCollections.observableArrayList();
			scrollPaneBestellung = new ScrollPane();
			Fahrzeug fahrzeug = this.tableViewFahrzeug.getSelectionModel().getSelectedItem();
			Route route = fahrzeug.getRoute();
			for (Bestellung b : route.getBestellungen()) {
				bestellungen.add(b);
			}
			TableColumn<Bestellung, String> bestellnrSpalte = new TableColumn<>("Bestellnummer");
			bestellnrSpalte.setCellValueFactory(new PropertyValueFactory<>("bestellnummer"));
			TableColumn<Bestellung, String> adressSpalte = new TableColumn<>("Adresse");
			adressSpalte.setCellValueFactory(new PropertyValueFactory<>("adresse"));
			tableViewBestellung = new TableView<Bestellung>();
			tableViewBestellung.getColumns().add(bestellnrSpalte);
			tableViewBestellung.getColumns().add(adressSpalte);
			tableViewBestellung.setItems(bestellungen);
			scrollPaneBestellung.setContent(tableViewBestellung);
		}
		return scrollPaneBestellung;
	}
}

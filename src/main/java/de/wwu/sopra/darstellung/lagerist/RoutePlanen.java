package de.wwu.sopra.darstellung.lagerist;

import de.wwu.sopra.anwendung.mitarbeiter.Lageristensteuerung;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * Grenzklasse RoutePlanen
 * 
 * @author intruq
 *
 */
public class RoutePlanen extends LageristOverview {
	TilePane tilePane;
	ScrollPane scrollPaneFahrzeug;
	TableView<Fahrzeug> tableViewFahrzeug;
	ScrollPane scrollPaneProdukt;
	TableView<Bestellung> tableViewBestellung;
	ObservableList<Fahrzeug> fahrzeuge;
	ObservableList<Bestellung> bestellungen;
	Button btRouteAbschicken;

	public RoutePlanen(Stage primaryStage, double width, double height, Lageristensteuerung lageristenSteuerung) {
		super(primaryStage, width, height, lageristenSteuerung);
		root.setCenter(new Label("Route planen..."));
		tilePane = new TilePane();
		tilePane.getChildren().add(this.setScrollPaneFahrzeug());
		tilePane.getChildren().add(this.setScrollPaneProdukt());
		tilePane.getChildren().add(this.setBtRouteAbschicken());
		root.setCenter(tilePane);
	}

	private ScrollPane setScrollPaneFahrzeug() {
		fahrzeuge = FXCollections.observableArrayList();
		if (scrollPaneFahrzeug == null) {
			scrollPaneFahrzeug = new ScrollPane();
			for (Fahrzeug f : lageristenSteuerung.zeigeFreieFahrzeuge()) {
				fahrzeuge.add(f);
			}
			TableColumn<Fahrzeug, String> fahrzeugSpalte = new TableColumn<>("Fahrzeugnummer");
			fahrzeugSpalte.setCellValueFactory(new PropertyValueFactory<>("fahrzeugNummer"));
			TableColumn<Fahrzeug, String> kapazitaetSpalte = new TableColumn<>("Kapazitaet");
			kapazitaetSpalte.setCellValueFactory(new PropertyValueFactory<>("kapazitaet"));
			tableViewFahrzeug = new TableView<Fahrzeug>();
			tableViewFahrzeug.getColumns().add(fahrzeugSpalte);
			tableViewFahrzeug.getColumns().add(kapazitaetSpalte);
			tableViewFahrzeug.setItems(fahrzeuge);
			scrollPaneFahrzeug.setContent(tableViewFahrzeug);
		}
		return scrollPaneFahrzeug;
	}

	private ScrollPane setScrollPaneProdukt() {
		bestellungen = FXCollections.observableArrayList();
		if (scrollPaneProdukt == null) {
			scrollPaneProdukt = new ScrollPane();
			for (Bestellung b : lageristenSteuerung.zeigeOffeneBestellungen()) {
				bestellungen.add(b);
			}
			TableColumn<Bestellung, String> bestellNr = new TableColumn<>("Bestellnummer");
			bestellNr.setCellValueFactory(new PropertyValueFactory<>("bestellnummer"));
			TableColumn<Bestellung, String> kapazitaetSpalte = new TableColumn<>("Kapazitaet");
			kapazitaetSpalte.setCellValueFactory(new PropertyValueFactory<>("kapazitaet"));

			tableViewBestellung = new TableView<Bestellung>();
			tableViewBestellung.getColumns().add(bestellNr);
			tableViewBestellung.getColumns().add(kapazitaetSpalte);
			tableViewBestellung.setItems(bestellungen);
			scrollPaneProdukt.setContent(tableViewBestellung);
			tableViewBestellung.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		}
		return scrollPaneProdukt;
	}

	private Button setBtRouteAbschicken() {
		if (this.btRouteAbschicken == null) {
			btRouteAbschicken = new Button("Route planen");
			btRouteAbschicken.setMinWidth(200);
			btRouteAbschicken.setOnAction(a -> {
				if (!(tableViewBestellung.getSelectionModel().getSelectedItems().isEmpty())
						&& tableViewFahrzeug.getSelectionModel().getSelectedItem() != null) {
					lageristenSteuerung.planeRoute(tableViewBestellung.getSelectionModel().getSelectedItems(),
							tableViewFahrzeug.getSelectionModel().getSelectedItem());
					primaryStage.setScene(new RoutePlanen(primaryStage, 1280, 720, lageristenSteuerung));
				}
			});
		}
		return this.btRouteAbschicken;
	}

}

package de.wwu.sopra.darstellung.lagerist;

import java.util.List;

import de.wwu.sopra.anwendung.mitarbeiter.Lageristensteuerung;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * GUI Klasse fuer RoutePlanen
 * 
 * @author Noah Latzel
 */
public class RoutePlanen extends LageristOverview {
	GridPane gridPane;
	ScrollPane scrollPaneFahrzeug;
	TableView<Fahrzeug> tableViewFahrzeug;
	ScrollPane scrollPaneProdukt;
	TableView<Bestellung> tableViewBestellung;
	ObservableList<Fahrzeug> fahrzeuge;
	ObservableList<Bestellung> bestellungen;
	Label errorLabel;
	Button btRouteAbschicken;
	BorderPane contentWrapper;

	/**
	 * Erzeugt das Fenster, um eine Route zu planen
	 * 
	 * @param primaryStage        PrimaryStage
	 * @param width               Breite des Fensters
	 * @param height              Hoehe des Fensters
	 * @param lageristenSteuerung LageristenSteuerung
	 */
	public RoutePlanen(Stage primaryStage, double width, double height, Lageristensteuerung lageristenSteuerung) {
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
			Label title = new Label("Routen planen");
			title.setStyle("-fx-font-weight: bold");
			title.setFont(new Font("Arial", 32));
			contentWrapper.setTop(title);
			contentWrapper.setCenter(this.setContent());
		}

		return this.contentWrapper;
	}

	private GridPane setContent() {
		if (gridPane == null) {
			gridPane = new GridPane();
			gridPane.setPadding(new Insets(20));
			gridPane.add(setScrollPaneFahrzeug(), 0, 0);
			gridPane.add(setScrollPaneProdukt(), 1, 0);
			gridPane.add(setBtRouteAbschicken(), 0, 1);
			gridPane.add(setErrorLabel(""), 1, 1);
		}
		return gridPane;
	}

	private Label setErrorLabel(String text) {
		if (errorLabel == null) {
			errorLabel = new Label();
		}
		errorLabel.setText(text);
		return errorLabel;
	}

	/**
	 * Erzeugt die Ansicht fuer die Fahrzeuge
	 * 
	 * @return ScrollPane fuer Fahrzeuge
	 */
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

	/**
	 * Erzeugt die Ansicht fuer die Produkte
	 * 
	 * @return ScrollPane fuer Produkte
	 */
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

	/**
	 * Erzeugt Button, um Route abzuschicken
	 * 
	 * @return Button, um Route abzuschicken
	 */
	private Button setBtRouteAbschicken() {
		if (this.btRouteAbschicken == null) {
			btRouteAbschicken = new Button("Route planen");
			btRouteAbschicken.setMinWidth(200);
			btRouteAbschicken.setOnAction(a -> {
				if (!(tableViewBestellung.getSelectionModel().getSelectedItems().isEmpty())
						&& tableViewFahrzeug.getSelectionModel().getSelectedItem() != null) {
					if (!this.pruefeKapazitaet(tableViewFahrzeug.getSelectionModel().getSelectedItem(),
							tableViewBestellung.getSelectionModel().getSelectedItems())) {
						setErrorLabel("Die Bestellung passt nicht in das Fahrzeug.");
					} else {
						lageristenSteuerung.planeRoute(tableViewBestellung.getSelectionModel().getSelectedItems(),
								tableViewFahrzeug.getSelectionModel().getSelectedItem());
						primaryStage
								.setScene(new RoutePlanen(primaryStage, getWidth(), getHeight(), lageristenSteuerung));
					}
				} else {
					setErrorLabel("Ueberpruefe deine Eingaben!");
				}
			});
		}
		return this.btRouteAbschicken;
	}

	/**
	 * Prueft, ob die Bestellungen in das Fahrzeug passen.
	 * 
	 * @param fahrzeug     Fahrzeug
	 * @param bestellungen Liste der Bestellungen
	 * @return ob die Route fuer das Fahrzeug passt
	 */
	private boolean pruefeKapazitaet(Fahrzeug fahrzeug, List<Bestellung> bestellungen) {
		boolean gueltig = true;
		int bestellungKapazitaet = 0;
		for (Bestellung bestellung : bestellungen) {
			bestellungKapazitaet += bestellung.getKapazitaet();
		}
		if (bestellungKapazitaet > fahrzeug.getKapazitaet()) {
			gueltig = false;
		}
		return gueltig;
	}

}

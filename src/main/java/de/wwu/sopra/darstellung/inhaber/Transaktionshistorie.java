package de.wwu.sopra.darstellung.inhaber;

import java.util.Collections;

import de.wwu.sopra.anwendung.mitarbeiter.Inhabersteuerung;
import de.wwu.sopra.datenhaltung.management.Transaktion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Ueerbick ueber die Transaktionen
 * 
 * @author Noah Latzel
 *
 */
public class Transaktionshistorie extends InhaberOverview {
	BorderPane contentWrapper;
	ScrollPane scrollPane;
	TableView<Transaktion> tableView;
	ObservableList<Transaktion> transaktionen;

	/**
	 * Zeigt die Transaktionshistorie des Geschaefts.
	 * 
	 * @param primaryStage     PrimaryStage
	 * @param width            Breite des Fensters
	 * @param height           Hoehe des Fensters
	 * @param inhaberSteuerung InhaberSteuerung
	 */
	public Transaktionshistorie(Stage primaryStage, double width, double height, Inhabersteuerung inhaberSteuerung) {
		super(primaryStage, width, height, inhaberSteuerung);
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
			Label title = new Label("Mitarbeiter-Registrierung");
			title.getStyleClass().add("mitarbeiter-content-title");
			contentWrapper.setTop(title);
			contentWrapper.setCenter(this.setContent());
		}

		return this.contentWrapper;
	}

	/**
	 * Erzeugt das ScrollPane fuer die Transaktionshistorie
	 * 
	 * @return ScrollPane fuer die Transaktionshistorie
	 */
	private ScrollPane setContent() {
		if (this.scrollPane == null) {
			scrollPane = new ScrollPane();
			scrollPane.setContent(this.setTransaktionshistorie());
			scrollPane.setFitToWidth(true);
			scrollPane.setFitToHeight(true);
		}
		return this.scrollPane;
	}

	private TableView<Transaktion> setTransaktionshistorie() {
		if (this.tableView == null) {
			transaktionen = FXCollections.observableArrayList();
			for (Transaktion transaktion : inhaberSteuerung.getTransaktionshistorie()) {
				transaktionen.add(transaktion);
			}
			Collections.reverse(transaktionen);
			TableColumn<Transaktion, String> datumSpalte = new TableColumn<>("Datum");
			datumSpalte.setCellValueFactory(new PropertyValueFactory<>("datum"));
			TableColumn<Transaktion, String> beschreibungSpalte = new TableColumn<>("Beschreibung");
			beschreibungSpalte.setCellValueFactory(new PropertyValueFactory<>("beschreibung"));
			TableColumn<Transaktion, Float> betragSpalte = new TableColumn<>("Betrag");
			betragSpalte.setCellValueFactory(new PropertyValueFactory<>("betrag"));

			tableView = new TableView<Transaktion>();
			tableView.getColumns().add(datumSpalte);
			tableView.getColumns().add(beschreibungSpalte);
			tableView.getColumns().add(betragSpalte);

			tableView.getStyleClass().add("inhaber-mitarbeitern-tableview");
			tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

			tableView.setItems(transaktionen);
		}
		return this.tableView;
	}

}

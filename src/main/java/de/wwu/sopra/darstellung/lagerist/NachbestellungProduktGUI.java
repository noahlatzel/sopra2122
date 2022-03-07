package de.wwu.sopra.darstellung.lagerist;

import java.util.HashSet;

import de.wwu.sopra.anwendung.mitarbeiter.Lageristensteuerung;
import de.wwu.sopra.anwendung.mitarbeiter.NachbestellungTupel;
import de.wwu.sopra.datenhaltung.management.Produkt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * GUI Klasse fuer die Produkte in BestelleNach GUI
 * 
 * @author Noah Latzel
 */
public class NachbestellungProduktGUI extends LageristOverview {
	/**
	 * Erzeugt das Objekt, das Produkt anzeigt
	 * 
	 * @param primaryStage        PrimaryStage
	 * @param width               Breite des Fensters
	 * @param height              Hoehe des Fensters
	 * @param lageristenSteuerung LageristenSteuerung
	 */
	public NachbestellungProduktGUI(Stage primaryStage, double width, double height,
			Lageristensteuerung lageristenSteuerung) {
		super(primaryStage, width, height, lageristenSteuerung);
	}

	/**
	 * Erzeugt eine Produktansicht
	 * 
	 * @param produkt Name des Produkts
	 * @param anzahl  Anzahl des Produkts im Bestand
	 * @return Erzeugt die Produktansicht
	 */
	public GridPane setProduktAnsicht(Produkt produkt, Integer anzahl) {
		ObservableList<Integer> options = FXCollections.observableArrayList();
		Tooltip t = new Tooltip("Waehle eine Menge aus.");

		FlowPane bestand = new FlowPane();
		for (int i = 0; i < 100; i++) {
			options.add(i);
		}
		final ComboBox<Integer> comboBox = new ComboBox<Integer>(options);
		comboBox.setEffect(dropShadow);
		Button nachbestellen = new Button("Nachbestellen");
		nachbestellen.setEffect(dropShadow);
		changeButtonStyleOnHover(nachbestellen);
		nachbestellen.setOnAction(a -> {
			if (comboBox.getSelectionModel().getSelectedItem() != null) {
				HashSet<NachbestellungTupel> neueNachbestellung = new HashSet<NachbestellungTupel>();
				neueNachbestellung
						.add(new NachbestellungTupel(produkt, comboBox.getSelectionModel().getSelectedItem()));
				lageristenSteuerung.bestelleNach(neueNachbestellung);
				primaryStage.setScene(new BestelleNach(primaryStage, getWidth(), getHeight(), lageristenSteuerung));
			}

		});
		Tooltip.install(nachbestellen, t);
		Label menge = new Label("Lager: " + anzahl.toString());
		menge.setStyle("-fx-background-radius: 16px; -fx-font-weight: bold; -fx-font-size: 16;");
		menge.setPadding(new Insets(0, 0, 0, 30));

		bestand.getChildren().add(comboBox);
		bestand.getChildren().add(menge);

		Label name = new Label(produkt.getName());
		name.setStyle("-fx-background-radius: 16px; -fx-font-weight: bold; -fx-font-size: 18;");
		GridPane.setConstraints(name, 0, 0);
		GridPane.setConstraints(bestand, 0, 1);
		GridPane.setConstraints(nachbestellen, 0, 2);

		GridPane produktTile = new GridPane();
		produktTile.getChildren().addAll(name, bestand, nachbestellen);
		produktTile
				.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		produktTile.setPrefWidth(190);
		produktTile.setPadding(new Insets(10));
		produktTile.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 8px;");
		produktTile.setVgap(10);
		return produktTile;
	}

}

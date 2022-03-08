package de.wwu.sopra.darstellung.lagerist;

import java.util.HashSet;

import de.wwu.sopra.anwendung.mitarbeiter.Lageristensteuerung;
import de.wwu.sopra.anwendung.mitarbeiter.NachbestellungTupel;
import de.wwu.sopra.datenhaltung.management.Produkt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
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
	public VBox setProduktAnsicht(Produkt produkt, Integer anzahl) {
		VBox produktPanel = new VBox();
		ObservableList<Integer> options = FXCollections.observableArrayList();
		Tooltip t = new Tooltip("Waehle eine Menge aus.");

		FlowPane bestand = new FlowPane();
		for (int i = 0; i < 100; i++) {
			options.add(i);
		}
		final ComboBox<Integer> comboBox = new ComboBox<Integer>(options);
		Button nachbestellen = new Button("Nachbestellen");
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

		// Temporary product image
		ImageView produktImg = new ImageView(produkt.loadBild());
		produktImg.setPreserveRatio(true);
		produktImg.setFitHeight(140);

		Label name = new Label(produkt.getName());
		name.setStyle("-fx-background-radius: 16px; -fx-font-weight: bold; -fx-font-size: 18;");
		produktPanel.setAlignment(Pos.CENTER);
		produktPanel.getChildren().add(name);
		produktPanel.getChildren().add(produktImg);
		produktPanel.getChildren().add(bestand);
		produktPanel.getChildren().add(nachbestellen);

		produktPanel.setPrefWidth(190);
		produktPanel.setPadding(new Insets(10));
		produktPanel.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 8px;");

		// Erstellung von DropShadows fuer Komponenten
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(3.0);
		dropShadow.setOffsetX(3.0);
		dropShadow.setOffsetY(3.0);
		dropShadow.setColor(Color.color(0.4, 0.5, 0.5));

		produktPanel.setEffect(dropShadow);

		produktPanel.setSpacing(10);

		return produktPanel;
	}

}

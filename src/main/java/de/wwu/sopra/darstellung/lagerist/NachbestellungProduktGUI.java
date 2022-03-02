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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class NachbestellungProduktGUI extends LageristOverview {

	public NachbestellungProduktGUI(Stage primaryStage, double width, double height,
			Lageristensteuerung lageristenSteuerung) {
		super(primaryStage, width, height, lageristenSteuerung);
	}

	public GridPane setProduktAnsicht(String nameProdukt, Integer anzahl) {
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
				neueNachbestellung.add(new NachbestellungTupel(new Produkt(nameProdukt, "Test", 0.1, 5),
						comboBox.getSelectionModel().getSelectedItem()));
				lageristenSteuerung.bestelleNach(neueNachbestellung);
				primaryStage.setScene(new BestelleNach(primaryStage, 1280, 720, lageristenSteuerung));
			}

		});
		Tooltip.install(nachbestellen, t);
		Label menge = new Label(anzahl.toString());
		menge.setPadding(new Insets(0, 0, 0, 10));

		bestand.getChildren().add(comboBox);
		bestand.getChildren().add(menge);

		Image image = new Image("C:\\Users\\Admin\\gruppenprojekt\\images\\logo.jpeg");
		ImageView logo = new ImageView();
		logo.setImage(image);
		logo.setFitWidth(90);
		logo.setPreserveRatio(true);

		Label name = new Label(nameProdukt);
		GridPane.setConstraints(name, 0, 0);
		GridPane.setConstraints(logo, 0, 1);
		GridPane.setConstraints(bestand, 0, 2);
		GridPane.setConstraints(nachbestellen, 0, 3);

		GridPane produkt = new GridPane();
		produkt.getChildren().addAll(name, logo, bestand, nachbestellen);
		produkt.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		produkt.setPrefWidth(100);
		return produkt;
	}
}
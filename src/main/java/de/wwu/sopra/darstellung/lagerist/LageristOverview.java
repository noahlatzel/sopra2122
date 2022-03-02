package de.wwu.sopra.darstellung.lagerist;

import de.wwu.sopra.anwendung.mitarbeiter.Lageristensteuerung;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Grenzklasse LageristOverview
 * 
 * @author Paul Dirksen
 *
 */
public class LageristOverview extends Scene {
	BorderPane root = new BorderPane();
	Stage primaryStage;
	Image image;
	ImageView logo;
	VBox vbox;
	Button btRoutePlanen;
	Button btBestelleNach;
	Button btPersDatenAnzeigen;
	Button btPersDatenBearbeiten;
	Button btZeigeRouteVonFahrzeug;
	Lageristensteuerung lageristenSteuerung;

	public LageristOverview(Stage primaryStage, double width, double height, Lageristensteuerung lageristenSteuerung) {
		super(new BorderPane(), width, height);

		image = new Image("C:\\Users\\Admin\\gruppenprojekt\\images\\logo.jpeg");
		logo = new ImageView();
		logo.setImage(image);
		logo.setFitWidth(200);
		logo.setPreserveRatio(true);

		this.primaryStage = primaryStage;
		this.setRoot(root);
		this.lageristenSteuerung = lageristenSteuerung;
		root.setLeft(this.setVBox());
		root.setCenter(new Label("Startseite..."));

	}

	private VBox setVBox() {
		if (this.vbox == null) {
			vbox = new VBox(5);
			vbox.getChildren().add(this.logo);
			vbox.getChildren().add(this.setBtRoutePlanen());
			vbox.getChildren().add(this.setBtBestelleNach());
			vbox.getChildren().add(this.setBtPersDatenAnzeigen());
			vbox.getChildren().add(this.setBtZeigeRouteVonFahrzeug());

		}
		return this.vbox;
	}

	private Button setBtRoutePlanen() {
		if (this.btRoutePlanen == null) {
			btRoutePlanen = new Button("Route planen");
			btRoutePlanen.setMinWidth(200);
			btRoutePlanen.setOnAction(a -> {
				primaryStage.setScene(new RoutePlanen(primaryStage, 1280, 720, lageristenSteuerung));
			});
		}
		return this.btRoutePlanen;
	}

	private Button setBtBestelleNach() {
		if (this.btBestelleNach == null) {
			btBestelleNach = new Button("Nachbestellen");
			btBestelleNach.setMinWidth(200);
			btBestelleNach.setOnAction(a -> {
				primaryStage.setScene(new BestelleNach(primaryStage, 1280, 720, lageristenSteuerung));
			});
		}
		return this.btBestelleNach;
	}

	private Button setBtPersDatenAnzeigen() {
		if (this.btPersDatenAnzeigen == null) {
			btPersDatenAnzeigen = new Button("Persoenliche Daten anzeigen");
			btPersDatenAnzeigen.setMinWidth(200);
			btPersDatenAnzeigen.setOnAction(a -> {
				primaryStage.setScene(new PersoenlicheDatenAnzeigen(primaryStage, 1280, 720, lageristenSteuerung));
			});
		}
		return this.btPersDatenAnzeigen;
	}

	private Button setBtZeigeRouteVonFahrzeug() {
		if (this.btZeigeRouteVonFahrzeug == null) {
			btZeigeRouteVonFahrzeug = new Button("Routen anzeigen");
			btZeigeRouteVonFahrzeug.setMinWidth(200);
			btZeigeRouteVonFahrzeug.setOnAction(a -> {
				primaryStage.setScene(new ZeigeRouteVonFahrzeug(primaryStage, 1280, 720, lageristenSteuerung));
			});
		}
		return this.btZeigeRouteVonFahrzeug;
	}
}
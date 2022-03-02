package de.wwu.sopra.darstellung.anmeldung;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Grenzklasse Startseite
 * 
 * @author Paul Dirksen
 *
 */
public class Startseite extends Scene {

	Stage primaryStage;
	BorderPane root = new BorderPane();
	Button buttonAnmelden;
	Button buttonRegistrieren;
	VBox vbox;

	/**
	 * Erzeugt eine neue Startseite.
	 * 
	 * @param primaryStage
	 * @param width        Breite des Fensters.
	 * @param height       Hoehe des Fensters.
	 */
	public Startseite(Stage primaryStage, double width, double height) {
		super(new BorderPane(), width, height);
		this.primaryStage = primaryStage;
		this.setRoot(root);
		root.setCenter(setVBox());

	}

	/**
	 * Erstellt, falls noch nicht vorhanden, eine VBox mit den passenden
	 * Komponenten.
	 * 
	 * @return VBox mit Komponenten
	 */
	public VBox setVBox() {
		if (vbox == null) {
			vbox = new VBox();

			buttonAnmelden = new Button("Anmelden");
			buttonRegistrieren = new Button("Registrieren");

			vbox.getChildren().add(buttonAnmelden);
			vbox.getChildren().add(buttonRegistrieren);

			buttonAnmelden.setPrefHeight(50);
			buttonRegistrieren.setPrefHeight(50);
			buttonAnmelden.setPrefWidth(170);
			buttonRegistrieren.setPrefWidth(170);

			buttonAnmelden.setOnAction(e -> primaryStage.setScene(new Anmeldung(primaryStage, 800, 600)));
			buttonRegistrieren.setOnAction(e -> primaryStage.setScene(new Registrierung(primaryStage, 800, 600)));

			vbox.setAlignment(Pos.CENTER);

			vbox.setSpacing(50);
		}
		return vbox;
	}

}

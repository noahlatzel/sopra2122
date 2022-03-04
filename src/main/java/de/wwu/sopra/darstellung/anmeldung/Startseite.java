package de.wwu.sopra.darstellung.anmeldung;

import java.io.File;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	Label title;

	/**
	 * Erzeugt eine neue Startseite.
	 * 
	 * @param primaryStage PrimaryStage
	 * @param width        Breite des Fensters.
	 * @param height       Hoehe des Fensters.
	 */
	public Startseite(Stage primaryStage, double width, double height) {
		super(new BorderPane(), width, height);
		this.primaryStage = primaryStage;
		this.setRoot(root);
		root.setCenter(setVBox());
		root.setStyle("-fx-background-color: #C4C4C4");
		
		File f = new File("resources/stylesheet.css");
		this.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
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
			title = new Label("Willkommen!");

			vbox.getChildren().add(title);
			vbox.getChildren().add(buttonAnmelden);
			vbox.getChildren().add(buttonRegistrieren);

			// Styling
			title.getStyleClass().add("welcome-title");
			vbox.getStyleClass().add("startseite-wrapper");
			buttonAnmelden.getStyleClass().add("startpage-button");
			buttonRegistrieren.getStyleClass().add("startpage-button");

			// Button-Funktionen
			buttonAnmelden
					.setOnAction(e -> primaryStage.setScene(new Anmeldung(primaryStage, getWidth(), getHeight())));
			buttonRegistrieren
					.setOnAction(e -> primaryStage.setScene(new Registrierung(primaryStage, getWidth(), getHeight())));
		}
		return vbox;
	}

}

package de.wwu.sopra.darstellung.main;

import de.wwu.sopra.darstellung.anmeldung.Startseite;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Statistiken;
import de.wwu.sopra.datenhaltung.verwaltung.BenutzerRegister;
import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;
import de.wwu.sopra.datenhaltung.verwaltung.GrosshaendlerRegister;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Main-Klasse
 * 
 * @author Johannes Thiel
 *
 */
public class MainJavaFx extends Application {

	/**
	 * Breite des Fensters
	 */
	private final int WIDTH = 1280;
	/**
	 * Hoehe des Fensters
	 */
	private final int HEIGHT = 720;

	/**
	 * Basislayout
	 */
	BorderPane layout;

	@Override
	public void start(Stage primaryStage) {

		layout = new BorderPane();

		BenutzerRegister.load();
		FahrzeugRegister.load();
		Lager.load();
		Statistiken.load();
		GrosshaendlerRegister.load();

		// BenutzerRegister.reset();
		// FahrzeugRegister.reset();
		// Lager.reset();
		primaryStage.setTitle("Jasmins Epische Harry Potter Traenke");
		Image img = new Image(getClass().getResource("logo3.png").toExternalForm());
		primaryStage.getIcons().add(img);
		primaryStage.setScene(new Startseite(primaryStage, WIDTH, HEIGHT));
		primaryStage.setResizable(true);
		primaryStage.sizeToScene();
		primaryStage.show();

	}

	/**
	 * Startmethode fuer JavaFX GUI
	 * 
	 * @param args Argumente
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void stop() {
		BenutzerRegister.save();
		FahrzeugRegister.save();
		Lager.save();
		Statistiken.save();
		GrosshaendlerRegister.save();
	}
}

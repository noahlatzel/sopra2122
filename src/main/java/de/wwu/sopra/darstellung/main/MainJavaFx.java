package de.wwu.sopra.darstellung.main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import de.wwu.sopra.darstellung.anmeldung.Startseite;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Statistiken;
import de.wwu.sopra.datenhaltung.verwaltung.BenutzerRegister;
import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;
import de.wwu.sopra.datenhaltung.verwaltung.GrosshaendlerRegister;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Main-Klasse
 * 
 * @author Johannes Thiel
 *
 */
public class MainJavaFx extends Application {
	private static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

	/**
	 * Breite des Fensters
	 */
	public static final int WIDTH = gd.getDisplayMode().getWidth() / 4 * 3;
	/**
	 * Hoehe des Fensters
	 */
	public static final int HEIGHT = gd.getDisplayMode().getHeight() / 4 * 3;

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

		primaryStage.setTitle("Jasmins Epische Harry Potter Traenke");

		primaryStage.setScene(new Startseite(primaryStage, WIDTH, HEIGHT));

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

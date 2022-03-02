package de.wwu.sopra.darstellung.main;

import de.wwu.sopra.anwendung.mitarbeiter.Lageristensteuerung;
import de.wwu.sopra.darstellung.lagerist.LageristOverview;
import de.wwu.sopra.datenhaltung.benutzer.Lagerist;
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
 * @author Johannes
 *
 */
public class MainJavaFx extends Application {
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

		primaryStage.setScene(
				new LageristOverview(primaryStage, 800, 600, new Lageristensteuerung(new Lagerist("noahlatzel", "123",
						"nlatzel@uni-muenster.de", "Muenster", "Noah", "Latzel", "GuteBank", null))));

		primaryStage.show();

	}

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

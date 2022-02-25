package de.wwu.sopra.darstellung.main;

import de.wwu.sopra.anwendung.mitarbeiter.Fahrersteuerung;
import de.wwu.sopra.darstellung.fahrer.OverviewFahrer;
import de.wwu.sopra.datenhaltung.benutzer.Fahrer;
import de.wwu.sopra.datenhaltung.benutzer.Inhaber;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainJavaFx extends Application {

	@Override
	public void start(Stage primaryStage) {
		Inhaber inhaber = new Inhaber("Bossbaby", "123", "iamnumberone", "Schlossalle Hotel", "Heribert", "Dietrich",
				"123");
		Fahrer fahrer1 = new Fahrer("killerman", "passwort", "123@online.de", "ostbad 1", "Herbert", "schulze", "123",
				inhaber);
		Fahrersteuerung steuerung = new Fahrersteuerung(fahrer1);
		primaryStage.setTitle("Jasmins Epische Harry Potter Traenke");
		primaryStage.setScene(new OverviewFahrer(steuerung, primaryStage, 1000, 300));
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}

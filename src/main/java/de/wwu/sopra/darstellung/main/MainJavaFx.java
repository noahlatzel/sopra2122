package de.wwu.sopra.darstellung.main;

import de.wwu.sopra.darstellung.fahrer.FahrzeugAuswaehlen;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainJavaFx extends Application {

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("Jasmins Epische Harry Potter Traenke");
		primaryStage.setScene(new FahrzeugAuswaehlen(primaryStage, 600, 300));
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}

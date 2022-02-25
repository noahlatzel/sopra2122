package de.wwu.sopra.darstellung.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainJavaFx extends Application {

	@Override
	public void start(Stage primaryStage) {
		Scene scene = new Scene(null, 400, 400);
		primaryStage.setTitle("Jasmins Epische Harry Potter Traenke");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}

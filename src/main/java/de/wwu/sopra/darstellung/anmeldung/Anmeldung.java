package de.wwu.sopra.darstellung.anmeldung;

import de.wwu.sopra.anwendung.anmeldung.Anmeldungssteuerung;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Grenzklasse Anmeldung
 * 
 * @author Paul Dirksen
 *
 */
public class Anmeldung extends Scene {

	Stage primaryStage;
	BorderPane root = new BorderPane();
	Button buttonAnmelden;
	TextField textFeldBenutzername;
	TextField textFeldPasswort;
	Label labelBenutzername;
	Label labelPasswort;
	VBox vbox;

	/**
	 * Erzeugt eine neue Anmeldungsseite.
	 * 
	 * @param primaryStage
	 * @param width        Breite des Fensters.
	 * @param height       Hoehe des Fensters.
	 */
	public Anmeldung(Stage primaryStage, double width, double height) {
		super(new BorderPane(), width, height);
		this.primaryStage = primaryStage;
		this.setRoot(root);
		root.setCenter(setVBox());

	}

	/**
	 * Einrichten der Verticalbox
	 * 
	 * @return Gibt eine fertig eingerichtete VBox zurueck.
	 */
	private VBox setVBox() {
		if (vbox == null) {
			vbox = new VBox();

			labelBenutzername = new Label("Benutzername");
			labelPasswort = new Label("Passwort");

			textFeldBenutzername = new TextField();
			textFeldPasswort = new TextField();

			buttonAnmelden = new Button("Anmelden");

			// Hinzufuegen der Buttons und Label
			vbox.getChildren().add(labelBenutzername);
			vbox.getChildren().add(textFeldBenutzername);
			vbox.getChildren().add(labelPasswort);
			vbox.getChildren().add(textFeldPasswort);
			vbox.getChildren().add(buttonAnmelden);

			textFeldBenutzername.setMaxWidth(170);
			textFeldPasswort.setMaxWidth(170);

			buttonAnmelden.setOnAction(e -> anmelden(textFeldBenutzername.getText(), textFeldPasswort.getText()));

			vbox.setSpacing(10);
			vbox.setAlignment(Pos.CENTER);
		}
		return vbox;
	}

	/**
	 * Stellt Anmeldeanfrage an die Anmeldungssteuerung mit den in die Textfelder
	 * eingegebenen Daten.
	 * 
	 * @param benutzername Ins Textfeld eingegebener Benutzername.
	 * @param passwort     Ins Textfeld eingegebenes Passwort.
	 */
	private void anmelden(String benutzername, String passwort) {
		Anmeldungssteuerung anSt = new Anmeldungssteuerung();
		anSt.anmelden(benutzername, passwort);
	}
}

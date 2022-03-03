package de.wwu.sopra.darstellung.anmeldung;

import de.wwu.sopra.anwendung.anmeldung.Anmeldungssteuerung;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Grenzklasse Registrierung
 * 
 * @author Paul Dirksen
 *
 */
public class Registrierung extends Scene {
	Stage primaryStage;
	BorderPane root = new BorderPane();
	GridPane gridpane;
	FlowPane flowpane;

	Button buttonRegistrieren = new Button("Registrieren");
	TextField textFeldBenutzername = new TextField();;
	TextField textFeldPasswort = new TextField();;
	TextField textFeldEmail = new TextField();;
	TextField textFeldAdresse = new TextField();;
	TextField textFeldVorname = new TextField();;
	TextField textFeldName = new TextField();;
	TextField textFeldBankverbindung = new TextField();;
	Label labelBenutzername = new Label("Benutzername");
	Label labelPasswort = new Label("Passwort");
	Label labelEmail = new Label("Email");
	Label labelAdresse = new Label("Adresse");
	Label labelVorname = new Label("Vorname");
	Label labelName = new Label("Name");
	Label labelBankverbindung = new Label("Bankverbindung");
	Text title;
	
	// Color constants fuer Buttons-Background
	private static final String STANDARD_BUTTON_STYLE = "-fx-background-color: #FF6868;";
	private static final String HOVERED_BUTTON_STYLE  = "-fx-background-color: #C14343;";

	/**
	 * Erzeugt eine neue Anmeldungsseite.
	 * 
	 * @param primaryStage
	 * @param width        Breite des Fensters.
	 * @param height       Hoehe des Fensters.
	 */
	public Registrierung(Stage primaryStage, double width, double height) {
		super(new BorderPane(), width, height);
		this.primaryStage = primaryStage;
		this.setRoot(root);
		root.setCenter(setGridPane());

	}

	private GridPane setGridPane() {
		if (gridpane == null) {
			gridpane = new GridPane();
			
			// Styles fuer TextFields und Labels
			String textFieldStyles = "-fx-padding: 12";
			String labelStyles = "-fx-font-weight: bold; -fx-font-size: 18";
			
			title = new Text("Registrierung");
			
			// Erstellung von DropShadows fuer TextField
			DropShadow dropShadowTextField = new DropShadow();
			dropShadowTextField.setRadius(5.0);
			dropShadowTextField.setOffsetX(2.0);
			dropShadowTextField.setOffsetY(2.0);
			dropShadowTextField.setColor(Color.color(0.4, 0.5, 0.5));
			
			// Styling von Komponenten
			GridPane.setMargin(title, new Insets(0, 0, 30, 0));
			GridPane.setMargin(setButtonRegistrieren(), new Insets(40, 10, 0, 0));
			title.setStyle("-fx-font-weight: bold; -fx-font-size: 48");
			textFeldBenutzername.setStyle(textFieldStyles);
			textFeldPasswort.setStyle(textFieldStyles);
			textFeldEmail.setStyle(textFieldStyles);
			textFeldAdresse.setStyle(textFieldStyles);
			textFeldVorname.setStyle(textFieldStyles);
			textFeldName.setStyle(textFieldStyles);
			textFeldBankverbindung.setStyle(textFieldStyles);
			textFeldBenutzername.setEffect(dropShadowTextField);
			textFeldPasswort.setEffect(dropShadowTextField);
			textFeldEmail.setEffect(dropShadowTextField);
			textFeldAdresse.setEffect(dropShadowTextField);
			textFeldVorname.setEffect(dropShadowTextField);
			textFeldName.setEffect(dropShadowTextField);
			textFeldBankverbindung.setEffect(dropShadowTextField);
			labelBenutzername.setStyle(labelStyles);
			labelPasswort.setStyle(labelStyles);
			labelEmail.setStyle(labelStyles);
			labelAdresse.setStyle(labelStyles);
			labelVorname.setStyle(labelStyles);
			labelName.setStyle(labelStyles);
			labelBankverbindung.setStyle(labelStyles);

			gridpane.setHgap(20);
			gridpane.setVgap(10);

			// Auf GridPane setzen
			gridpane.add(title, 1, 0, 2, 1);
			gridpane.add(labelVorname, 1, 1);
			gridpane.add(textFeldVorname, 1, 2);
			gridpane.add(labelName, 2, 1);
			gridpane.add(textFeldName, 2, 2);
			gridpane.add(labelBenutzername, 1, 4);
			gridpane.add(textFeldBenutzername, 1, 5);
			gridpane.add(labelPasswort, 2, 4);
			gridpane.add(textFeldPasswort, 2, 5);
			gridpane.add(labelEmail, 1, 7);
			gridpane.add(textFeldEmail, 1, 8);
			gridpane.add(labelAdresse, 2, 7);
			gridpane.add(textFeldAdresse, 2, 8);
			gridpane.add(labelBankverbindung, 1, 10);
			gridpane.add(textFeldBankverbindung, 1, 11);
			gridpane.add(setButtonRegistrieren(), 1, 12, 2, 1);
			gridpane.setAlignment(Pos.CENTER);
		}
		return gridpane;
	}

	/**
	 * Erstellt einen Registrierungsbutton der bei Betaetigung einen Kunden
	 * registriert.
	 * 
	 * @return Gibt den konfigurierten Button zurueck
	 */
	private Button setButtonRegistrieren() {
		buttonRegistrieren.setAlignment(Pos.TOP_CENTER);
		buttonRegistrieren.setPadding(new Insets(10));
		
		// Erstellung von DropShadows fuer Button
		DropShadow dropShadowButton = new DropShadow();
		dropShadowButton.setRadius(5.0);
		dropShadowButton.setOffsetX(4.0);
		dropShadowButton.setOffsetY(4.0);
		dropShadowButton.setColor(Color.color(0.4, 0.5, 0.5));
		
		buttonRegistrieren.setEffect(dropShadowButton);

		// Knopfdruckfunktionalitaet
		buttonRegistrieren.setOnAction(e -> {
			Anmeldungssteuerung anSt = new Anmeldungssteuerung();
			anSt.registrieren(textFeldBenutzername.getText(), textFeldPasswort.getText(), textFeldEmail.getText(),
					textFeldAdresse.getText(), textFeldVorname.getText(), textFeldName.getText(),
					textFeldBankverbindung.getText());
			primaryStage.setScene(new Anmeldung(primaryStage, 800, 600)); // TODO
		});
		
		changeButtonStyleOnHover(buttonRegistrieren);

		return buttonRegistrieren;
	}
	
	/**
	 * Funktion zum Aendern des Buttonsstils beim Hover
	 * @param button	Button, der gestylt wird
	 */
	private void changeButtonStyleOnHover(final Button button) {
		String moreStyles = "; -fx-background-radius: 16px; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 16";
		button.setStyle(STANDARD_BUTTON_STYLE + moreStyles);
		// Button onHover
		button.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent mouseEvent) {
				button.setStyle(HOVERED_BUTTON_STYLE + moreStyles + "; -fx-cursor: hand;");
			}
	    });
		// Button not onHover
		button.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent mouseEvent) {
				button.setStyle(STANDARD_BUTTON_STYLE + moreStyles);
			}
	    });
	}
}

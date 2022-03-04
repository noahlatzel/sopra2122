package de.wwu.sopra.darstellung.anmeldung;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
	 * Color constant fuer Button-Background
	 */
	protected static final String STANDARD_BUTTON_STYLE = "-fx-background-color: #FF6868;";
	/**
	 * Color constant fuer Button-Background
	 */
	protected static final String HOVERED_BUTTON_STYLE = "-fx-background-color: #C14343;";

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

			// Erstellung von DropShadows fuer Komponenten
			DropShadow dropShadow = new DropShadow();
			dropShadow.setRadius(5.0);
			dropShadow.setOffsetX(4.0);
			dropShadow.setOffsetY(4.0);
			dropShadow.setColor(Color.color(0.4, 0.5, 0.5));

			vbox.getChildren().add(title);
			vbox.getChildren().add(buttonAnmelden);
			vbox.getChildren().add(buttonRegistrieren);

			// Styling
			title.setStyle("-fx-font-weight: bold; -fx-font-size: 40");
			vbox.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 8px;");
			vbox.setEffect(dropShadow);
			vbox.setMaxSize(340, 380);

			buttonAnmelden.setPrefHeight(50);
			buttonRegistrieren.setPrefHeight(50);
			buttonAnmelden.setPrefWidth(170);
			buttonRegistrieren.setPrefWidth(170);

			buttonAnmelden.setEffect(dropShadow);
			buttonRegistrieren.setEffect(dropShadow);
			changeButtonStyleOnHover(buttonAnmelden);
			changeButtonStyleOnHover(buttonRegistrieren);

			// Button-Funktionen
			buttonAnmelden
					.setOnAction(e -> primaryStage.setScene(new Anmeldung(primaryStage, getWidth(), getHeight())));
			buttonRegistrieren
					.setOnAction(e -> primaryStage.setScene(new Registrierung(primaryStage, getWidth(), getHeight())));

			vbox.setAlignment(Pos.CENTER);

			vbox.setSpacing(30);
		}
		return vbox;
	}

	/**
	 * Funktion zum Aendern des Buttonsstils beim Hover
	 * 
	 * @param button Button, der gestylt wird
	 */
	private void changeButtonStyleOnHover(final Button button) {
		String moreStyles = "; -fx-background-radius: 16px; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 18";
		button.setStyle(STANDARD_BUTTON_STYLE + moreStyles);
		button.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				button.setStyle(HOVERED_BUTTON_STYLE + moreStyles + "; -fx-cursor: hand;");
			}
		});
		button.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				button.setStyle(STANDARD_BUTTON_STYLE + moreStyles);
			}
		});
	}

}

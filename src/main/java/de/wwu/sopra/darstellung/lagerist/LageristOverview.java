package de.wwu.sopra.darstellung.lagerist;

import de.wwu.sopra.anwendung.mitarbeiter.Lageristensteuerung;
import de.wwu.sopra.darstellung.anmeldung.Startseite;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * GUI Klasse fuer LageristOverview. Die anderen Lageristen GUIs erben von
 * dieser Klasse.
 * 
 * @author Noah Latzel
 */
public class LageristOverview extends Scene {
	BorderPane root = new BorderPane();
	Stage primaryStage;
	Image image;
	ImageView logo;
	VBox vbox;
	Button btRoutePlanen;
	Button btBestelleNach;
	Button btPersDatenAnzeigen;
	Button btPersDatenBearbeiten;
	Button btZeigeRouteVonFahrzeug;
	Button btAbmelden;
	Lageristensteuerung lageristenSteuerung;

	// Erstellung von DropShadows fuer Komponenten
	DropShadow dropShadow = new DropShadow();

	// Color constants fuer Buttons-Background
	private static final String STANDARD_BUTTON_STYLE = "-fx-background-color: #FF6868;";
	private static final String HOVERED_BUTTON_STYLE = "-fx-background-color: #C14343;";

	public LageristOverview(Stage primaryStage, double width, double height, Lageristensteuerung lageristenSteuerung) {
		super(new BorderPane(), width, height);

		dropShadow.setRadius(5.0);
		dropShadow.setOffsetX(4.0);
		dropShadow.setOffsetY(4.0);
		dropShadow.setColor(Color.color(0.4, 0.5, 0.5));

		image = new Image("C:\\Users\\Admin\\gruppenprojekt\\images\\logo.jpeg");
		logo = new ImageView();
		logo.setImage(image);
		logo.setFitWidth(200);
		logo.setPreserveRatio(true);

		this.primaryStage = primaryStage;
		this.setRoot(root);
		this.lageristenSteuerung = lageristenSteuerung;
		root.setLeft(this.setVBox());
		root.setCenter(new Label("Startseite..."));

	}

	private VBox setVBox() {
		if (this.vbox == null) {
			vbox = new VBox(5);
			vbox.getChildren().add(this.logo);
			vbox.getChildren().add(this.setBtRoutePlanen());
			vbox.getChildren().add(this.setBtBestelleNach());
			vbox.getChildren().add(this.setBtPersDatenAnzeigen());
			vbox.getChildren().add(this.setBtZeigeRouteVonFahrzeug());
			vbox.getChildren().add(this.setAbmelden());
			vbox.setEffect(dropShadow);
			vbox.setAlignment(Pos.CENTER);

			vbox.setSpacing(20);
			vbox.setPadding(new Insets(10));
			vbox.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 8px;");
		}
		return this.vbox;
	}

	private Button setBtRoutePlanen() {
		if (this.btRoutePlanen == null) {
			btRoutePlanen = new Button("Route planen");
			btRoutePlanen.setMinWidth(200);
			btRoutePlanen.setEffect(dropShadow);
			changeButtonStyleOnHover(btRoutePlanen);
			btRoutePlanen.setOnAction(a -> {
				primaryStage.setScene(new RoutePlanen(primaryStage, getWidth(), getHeight(), lageristenSteuerung));
			});
		}
		return this.btRoutePlanen;
	}

	private Button setBtBestelleNach() {
		if (this.btBestelleNach == null) {
			btBestelleNach = new Button("Nachbestellen");
			btBestelleNach.setMinWidth(200);
			btBestelleNach.setEffect(dropShadow);
			changeButtonStyleOnHover(btBestelleNach);
			btBestelleNach.setOnAction(a -> {
				primaryStage.setScene(new BestelleNach(primaryStage, getWidth(), getHeight(), lageristenSteuerung));
			});
		}
		return this.btBestelleNach;
	}

	private Button setBtPersDatenAnzeigen() {
		if (this.btPersDatenAnzeigen == null) {
			btPersDatenAnzeigen = new Button("Persoenliche Daten anzeigen");
			btPersDatenAnzeigen.setMinWidth(200);
			btPersDatenAnzeigen.setEffect(dropShadow);
			changeButtonStyleOnHover(btPersDatenAnzeigen);
			btPersDatenAnzeigen.setOnAction(a -> {
				primaryStage.setScene(
						new PersoenlicheDatenAnzeigen(primaryStage, getWidth(), getHeight(), lageristenSteuerung));
			});
		}
		return this.btPersDatenAnzeigen;
	}

	private Button setBtZeigeRouteVonFahrzeug() {
		if (this.btZeigeRouteVonFahrzeug == null) {
			btZeigeRouteVonFahrzeug = new Button("Routen anzeigen");
			btZeigeRouteVonFahrzeug.setMinWidth(200);
			btZeigeRouteVonFahrzeug.setEffect(dropShadow);
			changeButtonStyleOnHover(btZeigeRouteVonFahrzeug);
			btZeigeRouteVonFahrzeug.setOnAction(a -> {
				primaryStage.setScene(
						new ZeigeRouteVonFahrzeug(primaryStage, getWidth(), getHeight(), lageristenSteuerung));
			});
		}
		return this.btZeigeRouteVonFahrzeug;
	}

	private Button setAbmelden() {
		if (btAbmelden == null) {
			btAbmelden = new Button("Abmelden");
			btAbmelden.setMinWidth(200);
			btAbmelden.setEffect(dropShadow);
			changeButtonStyleOnHover(btAbmelden);
			btAbmelden.setOnAction(a -> {
				primaryStage.setScene(new Startseite(primaryStage, getWidth(), getHeight()));
			});
		}
		return this.btAbmelden;
	}

	/**
	 * Funktion zum Aendern des Buttonsstils beim Hover
	 * 
	 * @param button Button, der gestylt wird
	 */
	public void changeButtonStyleOnHover(final Button button) {
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
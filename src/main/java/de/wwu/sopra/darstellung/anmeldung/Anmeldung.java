package de.wwu.sopra.darstellung.anmeldung;

import de.wwu.sopra.anwendung.anmeldung.Anmeldungssteuerung;
import de.wwu.sopra.anwendung.kunde.Kundensteuerung;
import de.wwu.sopra.anwendung.mitarbeiter.Fahrersteuerung;
import de.wwu.sopra.anwendung.mitarbeiter.Inhabersteuerung;
import de.wwu.sopra.anwendung.mitarbeiter.Lageristensteuerung;
import de.wwu.sopra.darstellung.fahrer.OverviewFahrer;
import de.wwu.sopra.darstellung.lagerist.LageristOverview;
import de.wwu.sopra.datenhaltung.benutzer.Benutzer;
import de.wwu.sopra.datenhaltung.benutzer.Fahrer;
import de.wwu.sopra.datenhaltung.benutzer.Inhaber;
import de.wwu.sopra.datenhaltung.verwaltung.BenutzerRegister;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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
	PasswordField textFeldPasswort;
	Label labelBenutzername;
	Label labelPasswort;
	VBox vbox;
	Text title;
	
	private static final String STANDARD_BUTTON_STYLE = "-fx-background-color: #FF6868;";
	private static final String HOVERED_BUTTON_STYLE  = "-fx-background-color: #C14343;";

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
			textFeldPasswort = new PasswordField();
			
			buttonAnmelden = new Button("Anmelden");
			
			title = new Text("Anmeldung");
			title.setStyle("-fx-font-weight: bold; -fx-font-size: 48");

			DropShadow dropShadowButton = new DropShadow();
			dropShadowButton.setRadius(5.0);
			dropShadowButton.setOffsetX(4.0);
			dropShadowButton.setOffsetY(4.0);
			dropShadowButton.setColor(Color.color(0.4, 0.5, 0.5));
			DropShadow dropShadowTextField = new DropShadow();
			dropShadowTextField.setRadius(5.0);
			dropShadowTextField.setOffsetX(2.0);
			dropShadowTextField.setOffsetY(2.0);
			dropShadowTextField.setColor(Color.color(0.4, 0.5, 0.5));
			
			labelBenutzername.setStyle("-fx-font-weight: bold; -fx-font-size: 18");
			labelPasswort.setStyle("-fx-font-weight: bold; -fx-font-size: 18");
			textFeldBenutzername.setStyle("-fx-padding: 12");
			textFeldPasswort.setStyle("-fx-padding: 12");
			textFeldBenutzername.setEffect(dropShadowTextField);
			textFeldPasswort.setEffect(dropShadowTextField);
			buttonAnmelden.setEffect(dropShadowButton);
			changeButtonStyleOnHover(buttonAnmelden);
			buttonAnmelden.setPrefHeight(40);
			buttonAnmelden.setPrefWidth(120);
			VBox.setMargin(title, new Insets(0, 0, 30, 0));
			VBox.setMargin(buttonAnmelden, new Insets(40, 10, 0, 0));

			// Hinzufuegen der Buttons und Label
			vbox.getChildren().add(title);
			vbox.getChildren().add(labelBenutzername);
			vbox.getChildren().add(textFeldBenutzername);
			vbox.getChildren().add(labelPasswort);
			vbox.getChildren().add(textFeldPasswort);
			vbox.getChildren().add(buttonAnmelden);

			textFeldBenutzername.setMaxWidth(240);
			textFeldPasswort.setMaxWidth(240);

			buttonAnmelden.setOnAction(e -> {
				Benutzer benutzer = anmelden(textFeldBenutzername.getText(), textFeldPasswort.getText());
				if (benutzer != null) {
					leiteWeiter(benutzer);
				}
			});

			vbox.setSpacing(16);
			vbox.setAlignment(Pos.CENTER);
		}
		return vbox;
	}
	
	private void changeButtonStyleOnHover(final Button button) {
		String moreStyles = "; -fx-background-radius: 16px; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 16";
		button.setStyle(STANDARD_BUTTON_STYLE + moreStyles);
		button.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent mouseEvent) {
				button.setStyle(HOVERED_BUTTON_STYLE + moreStyles + "; -fx-cursor: hand;");
			}
	    });
		button.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent mouseEvent) {
				button.setStyle(STANDARD_BUTTON_STYLE + moreStyles);
			}
	    });
	}

	/**
	 * Stellt Anmeldeanfrage an die Anmeldungssteuerung mit den in die Textfelder
	 * eingegebenen Daten.
	 * 
	 * @param benutzername Ins Textfeld eingegebener Benutzername.
	 * @param passwort     Ins Textfeld eingegebenes Passwort.
	 */
	private Benutzer anmelden(String benutzername, String passwort) {
		Anmeldungssteuerung anSt = new Anmeldungssteuerung();
		return anSt.anmelden(benutzername, passwort);
	}

	/**
	 * Bearbeitet die Weiterleitung an die dem uebergebenen Benutzer zugehoerige
	 * GrenzklassenOverview
	 * 
	 * @param benutzer Benutzer
	 * @pre Der uebergebene Nutzer ist ein im System eingetragener Benutzer
	 */
	private void leiteWeiter(Benutzer benutzer) {
		assert BenutzerRegister.getBenutzerZuBenutzername(benutzer.getBenutzername()) != null
				: "Benutzer ist nicht im System registriert";

		switch (benutzer.getRolle()) {
		case KUNDE:
			Kundensteuerung ks = new Kundensteuerung(null); // TODO Fehlende Parameter
			System.out.println("Kunde angemeldet!");
			break;
		case FAHRER:
			Fahrersteuerung fs = new Fahrersteuerung((Fahrer) benutzer);
			OverviewFahrer fo = new OverviewFahrer(fs, primaryStage, 800, 600);
			primaryStage.setScene(fo);
			System.out.println("Fahrer angemeldet!");
			break;
		case LAGERIST:
			Lageristensteuerung ls = new Lageristensteuerung();
			LageristOverview lo = new LageristOverview(primaryStage, 800, 600, ls);
			primaryStage.setScene(lo);
			System.out.println("Lagerist angemeldet!");
			break;

		case INHABER:
			Inhabersteuerung is = new Inhabersteuerung((Inhaber) benutzer); // TODO Fehlende Parameter

			System.out.println("Inhaber angemeldet!");
			break;
		}
	}
}

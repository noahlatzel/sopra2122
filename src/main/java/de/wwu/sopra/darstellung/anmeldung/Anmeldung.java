package de.wwu.sopra.darstellung.anmeldung;

import java.io.File;

import de.wwu.sopra.anwendung.anmeldung.Anmeldungssteuerung;
import de.wwu.sopra.anwendung.kunde.Kundensteuerung;
import de.wwu.sopra.anwendung.mitarbeiter.Fahrersteuerung;
import de.wwu.sopra.anwendung.mitarbeiter.Inhabersteuerung;
import de.wwu.sopra.anwendung.mitarbeiter.Lageristensteuerung;
import de.wwu.sopra.darstellung.fahrer.OverviewFahrer;
import de.wwu.sopra.darstellung.inhaber.InhaberOverview;
import de.wwu.sopra.darstellung.kunde.KundeOverview;
import de.wwu.sopra.darstellung.lagerist.LageristOverview;
import de.wwu.sopra.datenhaltung.benutzer.Benutzer;
import de.wwu.sopra.datenhaltung.benutzer.Fahrer;
import de.wwu.sopra.datenhaltung.benutzer.Inhaber;
import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.benutzer.Lagerist;
import de.wwu.sopra.datenhaltung.verwaltung.BenutzerRegister;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
		Inhaber inhaber = new Inhaber("admin", "admin", "123@onlin.de", "breul 23", "boss", "Baby", "hallo");
		BenutzerRegister.benutzerHinzufuegen(inhaber);
		
		File f = new File("resources/stylesheet.css");
		this.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
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
			
			// Set und Style Titel der Seite
			title = new Text("Anmeldung");
			title.getStyleClass().add("anmeldung-registrierung-title");
			
			// Styling von Komponenten
			vbox.getStyleClass().add("anmeldung-wrapper");
			labelBenutzername.getStyleClass().add("anmeldung-registrierung-label");
			labelPasswort.getStyleClass().add("anmeldung-registrierung-label");
			textFeldBenutzername.getStyleClass().add("anmeldung-textfield");
			textFeldPasswort.getStyleClass().add("anmeldung-textfield");
			buttonAnmelden.getStyleClass().add("anmeldung-button");
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

			buttonAnmelden.setOnAction(e -> {
				Benutzer benutzer = anmelden(textFeldBenutzername.getText(), textFeldPasswort.getText());
				if (benutzer != null) {
					leiteWeiter(benutzer);
				}
			});
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
			Kundensteuerung ks = new Kundensteuerung((Kunde) benutzer);
			KundeOverview ko = new KundeOverview(primaryStage, getWidth(), getHeight(), ks);
			primaryStage.setScene(ko);
			System.out.println("Kunde angemeldet!");
			break;
		case FAHRER:
			Fahrersteuerung fs = new Fahrersteuerung((Fahrer) benutzer);
			OverviewFahrer fo = new OverviewFahrer(fs, primaryStage, getWidth(), getHeight());
			primaryStage.setScene(fo);
			System.out.println("Fahrer angemeldet!");
			break;
		case LAGERIST:
			Lageristensteuerung ls = new Lageristensteuerung((Lagerist) benutzer);
			LageristOverview lo = new LageristOverview(primaryStage, getWidth(), getHeight(), ls);
			primaryStage.setScene(lo);
			System.out.println("Lagerist angemeldet!");
			break;
		case INHABER:
			Inhabersteuerung is = new Inhabersteuerung((Inhaber) benutzer);
			InhaberOverview io = new InhaberOverview(primaryStage, getWidth(), getHeight(), is);
			primaryStage.setScene(io);
			System.out.println("Inhaber angemeldet!");
			break;
		}
	}
}

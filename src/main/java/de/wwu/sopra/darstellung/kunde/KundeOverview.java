package de.wwu.sopra.darstellung.kunde;

import de.wwu.sopra.anwendung.kunde.Kundensteuerung;
import de.wwu.sopra.darstellung.anmeldung.Startseite;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * @author Jasmin Horstknepper
 *
 */
public class KundeOverview extends Scene {

	BorderPane root = new BorderPane();
	Stage primaryStage;
	Image image;
	VBox vbox;
	FlowPane flowpane;
	Button btLogo;
	MenuItem btProfil;
	MenuItem btBestellungen;
    MenuItem btAbmelden;
	MenuButton menubutton;
	Button btWarenkorb;
	Kundensteuerung kundensteuerung;

	/**
	 * Konstruktor fuer die Uebersicht des Kunden
	 * 
	 * @param primaryStage    PrimaryStage
	 * @param width           Breite des Fensters
	 * @param height          Hoehe des Fensters
	 * @param kundensteuerung KundenSteuerung
	 */
	public KundeOverview(Stage primaryStage, double width, double height, Kundensteuerung kundensteuerung) {

		super(new BorderPane(), width, height);
		this.primaryStage = primaryStage;
		this.setRoot(root);
		this.kundensteuerung = kundensteuerung;
		root.setTop(this.setFlowpane());
		root.setCenter(new Label("KUNDE OVERVIEW"));
	}

	/**
	 * Erzeugt das FlowPane fuer den Header
	 * 
	 * @return FlowPane
	 */
	public FlowPane setFlowpane() {

		if (this.flowpane == null) {
			flowpane = new FlowPane();
			flowpane.getChildren().add(setBtLogo());
			flowpane.getChildren().add(setBtWarenkorb());
			flowpane.getChildren().add(setMenuButton());
			flowpane.setHgap(20);
			flowpane.setVgap(20);
		}
		return this.flowpane;
	}

	/**
	 * Gibt die Ansicht fuer den Warenkorb zurueck
	 * 
	 * @return Ansicht fuer Warenkorb
	 */
	private Button setBtWarenkorb() {
		if (this.btWarenkorb == null) {
			btWarenkorb = new Button("Warenkorb");
			btWarenkorb.setMinWidth(250);
			btWarenkorb.setOnAction(action -> {
				primaryStage.setScene(new WarenkorbAnsicht(primaryStage, getWidth(), getHeight(), kundensteuerung));
			});
		}

		return this.btWarenkorb;
	}

	/**
	 * Setzt das Logo als Button
	 * 
	 * @return Logo
	 */
	private Button setBtLogo() {
		if (this.btLogo == null) {
			btLogo = new Button("Logo");
			btLogo.setMinWidth(250);
			btLogo.setOnAction(action -> {
				primaryStage.setScene(new StartseiteKunde(primaryStage, getWidth(), getHeight(), kundensteuerung,
						kundensteuerung.getLager()));
			});
		}

		return this.btLogo;

	}

	private MenuItem setBtProfil() {

		if (this.btProfil == null) {
			btProfil = new MenuItem("Profil");
			btProfil.setOnAction(action -> {
				primaryStage.setScene(new PersoenlicheDatenAnzeigen(primaryStage, getWidth(), getHeight(), kundensteuerung));

			});
		}

		return this.btProfil;

	}


	private MenuItem setBtAbmelden() {

		if (this.btAbmelden == null) {
			btAbmelden = new MenuItem("Abmelden");
			btAbmelden.setOnAction(action -> {
				primaryStage.setScene(new Startseite(primaryStage , getWidth(), getHeight()));

			});
		}

		return this.btAbmelden;

	}


	private MenuItem setBtBestellungen() {

		if (this.btBestellungen == null) {
			btBestellungen = new MenuItem("Bestellungen");
			btBestellungen.setOnAction(action -> {
				primaryStage.setScene(new UebersichtBestellungen(primaryStage, getWidth(), getHeight(), kundensteuerung));

			});
		}

		return this.btBestellungen;

	}


  /**
   * Setzt die Auswahlbox fuer die verschiedenen Buttons
   * 
   * @return Auswahlbox
   */
	private MenuButton setMenuButton() {
		if (menubutton == null) {
			menubutton = new MenuButton("Konto" ,null ,setBtProfil() ,setBtBestellungen() ,setBtAbmelden());

		}
		return this.menubutton;
	}
}

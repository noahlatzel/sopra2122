package de.wwu.sopra.darstellung.kunde;

import java.io.File;

import de.wwu.sopra.anwendung.kunde.Kundensteuerung;
import de.wwu.sopra.darstellung.anmeldung.Startseite;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
	HBox hbox;
	Button btLogo;
	MenuItem btProfil;
	MenuItem btBestellungen;
	MenuItem btAbmelden;
	MenuButton menubutton;
	Button btWarenkorb;
	Kundensteuerung kundensteuerung;
	File stylesheet;

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
		root.setTop(this.setHeaderBorderPane());
		root.setCenter(new Label("KUNDE OVERVIEW"));
		root.getStyleClass().add("kunde-root");

		stylesheet = new File("resources/stylesheet.css");
		this.getStylesheets().add("file:///" + stylesheet.getAbsolutePath().replace("\\", "/"));
	}

	/**
	 * Erzeugt eine Borderpane fuer den Header
	 * 
	 * @return es wird die Borderpane uebergaben
	 */
	public BorderPane setHeaderBorderPane() {
		BorderPane headerBP = new BorderPane();
		headerBP.getStyleClass().add("kunde-header-wrapper");

		headerBP.setLeft(setBtLogo());
		headerBP.setRight(setHBox());

		return headerBP;
	}

	/**
	 * Erzeugt das HBox fuer den Header
	 * 
	 * @return HBox
	 */
	public HBox setHBox() {

		if (this.hbox == null) {
			hbox = new HBox();
			hbox.getStyleClass().add("kunde-header-right-side-wrapper");
			hbox.getChildren().add(setBtWarenkorb());
			hbox.getChildren().add(setMenuButton());
		}
		return this.hbox;
	}

	/**
	 * Gibt die Ansicht fuer den Warenkorb zurueck
	 * 
	 * @return Ansicht fuer Warenkorb
	 */
	private Button setBtWarenkorb() {
		if (this.btWarenkorb == null) {
			btWarenkorb = new Button();
			btWarenkorb.getStyleClass().add("kunde-warenkorb-button");

			ImageView view = new ImageView(getClass().getResource("warenkorb.png").toExternalForm());
			view.setFitWidth(35);
			view.setFitHeight(35);
			btWarenkorb.setGraphic(view);

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
			btLogo = new Button();
			btLogo.getStyleClass().add("kunde-logo-button");
			
			btLogo.setOnAction(action -> {
				primaryStage.setScene(new StartseiteKunde(primaryStage, getWidth(), getHeight(), kundensteuerung,
						kundensteuerung.getLager(), null));
			});

			ImageView view = new ImageView(getClass().getResource("home-button.png").toExternalForm());
			view.setFitWidth(35);
			view.setFitHeight(35);
			btLogo.setGraphic(view);
		}

		return this.btLogo;

	}

	private MenuItem setBtProfil() {

		if (this.btProfil == null) {
			btProfil = new MenuItem("Profil");

			btProfil.setOnAction(action -> {
				primaryStage.setScene(
						new PersoenlicheDatenAnzeigen(primaryStage, getWidth(), getHeight(), kundensteuerung));

			});
		}

		return this.btProfil;

	}

	private MenuItem setBtAbmelden() {

		if (this.btAbmelden == null) {
			btAbmelden = new MenuItem("Abmelden");
			btAbmelden.setOnAction(action -> {
				primaryStage.setScene(new Startseite(primaryStage, getWidth(), getHeight()));

			});
		}

		return this.btAbmelden;

	}

	private MenuItem setBtBestellungen() {

		if (this.btBestellungen == null) {
			btBestellungen = new MenuItem("Bestellungen");
			btBestellungen.setOnAction(action -> {
				primaryStage
						.setScene(new UebersichtBestellungen(primaryStage, getWidth(), getHeight(), kundensteuerung));
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
			menubutton = new MenuButton("", null, setBtProfil(), setBtBestellungen(), setBtAbmelden());
			menubutton.getStyleClass().add("kunde-menu-button");

			ImageView view = new ImageView(getClass().getResource("user-icon.png").toExternalForm());
			view.setFitWidth(40);
			view.setFitHeight(40);
			menubutton.setGraphic(view);
		}
		return this.menubutton;
	}
}

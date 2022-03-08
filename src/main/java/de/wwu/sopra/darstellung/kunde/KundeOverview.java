package de.wwu.sopra.darstellung.kunde;

import java.io.File;

import de.wwu.sopra.anwendung.kunde.Kundensteuerung;
import de.wwu.sopra.darstellung.anmeldung.Startseite;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
		root.setStyle(" -fx-background-color: white");

		stylesheet = new File("resources/stylesheet.css");
		this.getStylesheets().add("file:///" + stylesheet.getAbsolutePath().replace("\\", "/"));
	}

	/**
	 * Erzeugt eine Borderpane fuer den Header
	 * 
	 * @return
	 */
	public BorderPane setHeaderBorderPane() {
		BorderPane headerBP = new BorderPane();

		headerBP.setLeft(setBtLogo());
		headerBP.setRight(setHBox());

		headerBP.setPadding(new Insets(0, 10, 0, 10));

		headerBP.setStyle(" -fx-background-color: #ffb6b6");

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
			hbox.getChildren().add(setBtWarenkorb());
			hbox.getChildren().add(setMenuButton());
			hbox.setSpacing(10);
			hbox.setAlignment(Pos.CENTER_LEFT);
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
			btWarenkorb.setMinWidth(50);

			ImageView view = new ImageView(getClass().getResource("warenkorb.png").toExternalForm());
			view.setFitWidth(35);
			view.setFitHeight(35);
			btWarenkorb.setGraphic(view);

			btWarenkorb.setStyle(" -fx-color: #ffb6b6; -fx-background-color: #ffb6b6");

			btWarenkorb.setOnMouseEntered(e -> {
				btWarenkorb.setStyle(" -fx-cursor: hand;  -fx-color: #ffb6b6; -fx-background-color: #ffb6b6");
			});

			btWarenkorb.setOnMouseExited(e -> {
				btWarenkorb.setStyle(" -fx-cursor: default;  -fx-color: #ffb6b6; -fx-background-color: #ffb6b6");
			});

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
			btLogo.setMinWidth(50);

			btLogo.setAlignment(Pos.CENTER_LEFT);
			btLogo.setPadding(new Insets(8, 0, 5, 10));

			String css = "-fx-background-color: #ffb6b6; -fx-font-weight: bold; -fx-mark-color: #ffb6b6; -fx-font-size: 15; -fx-focus-color: #ffb6b6; -fx-border-color: #ffb6b6";
			btLogo.setStyle(css);

			btLogo.setOnMouseEntered(e -> {
				btLogo.setStyle(" -fx-cursor: hand;" + css);
			});

			btLogo.setOnMouseExited(e -> {
				btLogo.setStyle(" -fx-cursor: default;" + css);
			});

			btLogo.setOnAction(action -> {
				primaryStage.setScene(new StartseiteKunde(primaryStage, getWidth(), getHeight(), kundensteuerung,
						kundensteuerung.getLager()));
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

			ImageView view = new ImageView(getClass().getResource("user-icon.png").toExternalForm());
			view.setFitWidth(40);
			view.setFitHeight(40);
			menubutton.setGraphic(view);

			String css = " -fx-color: #ffb6b6; -fx-background-color: #ffb6b6; -fx-mark-color: #ffb6b6; -fx-font-size: 15;";
			menubutton.setStyle(css);

			menubutton.setOnMouseEntered(e -> {
				menubutton.setStyle(" -fx-cursor: hand;" + css);
			});

			menubutton.setOnMouseExited(e -> {
				menubutton.setStyle(" -fx-cursor: default;" + css);
			});
		}
		return this.menubutton;
	}
}

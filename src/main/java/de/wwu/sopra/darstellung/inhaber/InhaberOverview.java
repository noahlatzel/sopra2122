/**
 * 
 */
package de.wwu.sopra.darstellung.inhaber;

import de.wwu.sopra.anwendung.mitarbeiter.Inhabersteuerung;
import de.wwu.sopra.darstellung.anmeldung.Startseite;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Valeria Vassallo
 *
 */
public class InhaberOverview extends Scene {
	// Erstellung von Variablen
	BorderPane root = new BorderPane();
	Stage primaryStage;
	VBox vbox;
	BorderPane header;
	Button btStatistiken;
	Button btMitarbeiterRegistrieren;
	Button btMitarbeiterVerwalten;
	Button btSortimentBearbeiten;
	Button btFahrzeugdatenAendern;
	Button btPersoenlicheDatenAnzeigen;
	Button btPersoenlicheDatenBearbeiten;
	Inhabersteuerung inhaberSteuerung;
	Button btAbmelden;
	Image image;
	ImageView logo;
	
	// Color constants fuer Buttons-Background
	private static final String STANDARD_BUTTON_STYLE = "-fx-background-color: #888888;";
	private static final String HOVERED_BUTTON_STYLE  = "-fx-background-color: #595959;";

	public InhaberOverview(Stage primaryStage, double width, double height, Inhabersteuerung inhaberSteuerung) {
		super(new BorderPane(), width, height);
		
		image = new Image("file:///C:/Users/Admin/gruppenprojekt/images/logo.jpeg");
		logo = new ImageView();
		logo.setImage(image);
		logo.setFitWidth(20);
		
		this.primaryStage = primaryStage;
		this.setRoot(root);
		this.inhaberSteuerung = inhaberSteuerung;
		root.setTop(this.setHeader());
		root.setLeft(this.setVBox());
		root.setCenter(new Label("INHABER STARTSEITE"));
	}

	private VBox setVBox() {
		// Erstellung von SideBar mit allen Buttons
		if (this.vbox == null) {
			vbox = new VBox();
			vbox.getChildren().add(this.setBtStatistiken());
			vbox.getChildren().add(this.setBtMitarbeiterRegistrieren());
			vbox.getChildren().add(this.setBtMitarbeiterVerwalten());
			vbox.getChildren().add(this.setBtSortimentBearbeiten());
			vbox.getChildren().add(this.setBtFahrzeugdatenAendern());
			vbox.getChildren().add(this.setBtPersoenlicheDatenBearbeiten());
			vbox.getChildren().add(this.setBtPersoenlicheDatenAnzeigen());
			vbox.setBackground(new Background(new BackgroundFill(Color.web("#C4C4C4"), CornerRadii.EMPTY, Insets.EMPTY)));
		}
		
		return this.vbox;
	}

	private BorderPane setHeader() {
		// Main Header, konstant
		if (this.header == null) {
			header = new BorderPane();
			header.minHeight(300);
			header.setPadding(new Insets(30, 40, 30, 40));
			header.setBackground(
					new Background(new BackgroundFill(Color.web("#c4c4c4"), new CornerRadii(0), Insets.EMPTY)));
			// Left side
			Label logoLabel = new Label("Logo");
			logoLabel.setTextFill(Color.web("#000000"));
			header.setLeft(logoLabel);
			
			// Right side
			Menu userMenu = new Menu("User");
			userMenu.setStyle("-fx-background: #C4C4C4");
			
			MenuItem abmeldenOption = new MenuItem("Abmelden");
			abmeldenOption.setOnAction(e -> {
				primaryStage.setScene(new Startseite(primaryStage, getWidth(), getHeight()));
			});
			userMenu.getItems().add(abmeldenOption);
			
			MenuBar menuBar = new MenuBar();
			menuBar.setStyle("-fx-background: #C4C4C4");
			menuBar.getMenus().add(userMenu);
			header.setRight(menuBar);
		}

		return this.header;
	}

	// Erstellungen von Buttons, die auf andere Websites weiterleiten
	private Button setBtStatistiken() {
		if (this.btStatistiken == null) {
			btStatistiken = new Button("Statistiken");
			btStatistiken.setMinWidth(300);
			this.changeButtonStyleOnHover(btStatistiken);
			btStatistiken.setOnAction(action -> {
				primaryStage.setScene(new StatistikenAnzeigen(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
			});
		}

		return this.btStatistiken;
	}

	private Button setBtMitarbeiterRegistrieren() {
		if (this.btMitarbeiterRegistrieren == null) {
			btMitarbeiterRegistrieren = new Button("Mitarbeiter Registrieren");
			btMitarbeiterRegistrieren.setMinWidth(300);
			this.changeButtonStyleOnHover(btMitarbeiterRegistrieren);
			btMitarbeiterRegistrieren.setOnAction(action -> {
				primaryStage
						.setScene(new MitarbeiterRegistrieren(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
			});
		}

		return this.btMitarbeiterRegistrieren;
	}

	private Button setBtMitarbeiterVerwalten() {
		if (this.btMitarbeiterVerwalten == null) {
			btMitarbeiterVerwalten = new Button("Mitarbeiter Verwalten");
			btMitarbeiterVerwalten.setMinWidth(300);
			this.changeButtonStyleOnHover(btMitarbeiterVerwalten);
			btMitarbeiterVerwalten.setOnAction(action -> {
				primaryStage
						.setScene(new MitarbeiterVerwalten(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
			});
		}

		return this.btMitarbeiterVerwalten;
	}

	private Button setBtSortimentBearbeiten() {
		if (this.btSortimentBearbeiten == null) {
			btSortimentBearbeiten = new Button("Sortiment Bearbeiten");
			btSortimentBearbeiten.setMinWidth(300);
			this.changeButtonStyleOnHover(btSortimentBearbeiten);
			btSortimentBearbeiten.setOnAction(action -> {
				primaryStage.setScene(new SortimentBearbeiten(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
			});
		}

		return this.btSortimentBearbeiten;
	}

	private Button setBtFahrzeugdatenAendern() {
		if (this.btFahrzeugdatenAendern == null) {
			btFahrzeugdatenAendern = new Button("Fahrzeugdaten Aendern");
			btFahrzeugdatenAendern.setMinWidth(300);
			this.changeButtonStyleOnHover(btFahrzeugdatenAendern);
			btFahrzeugdatenAendern.setOnAction(action -> {
				primaryStage
						.setScene(new FahrzeugdatenAendern(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
			});
		}

		return this.btFahrzeugdatenAendern;
	}

	private Button setBtPersoenlicheDatenBearbeiten() {
		if (this.btPersoenlicheDatenBearbeiten == null) {
			btPersoenlicheDatenBearbeiten = new Button("Persoenliche Daten Bearbeiten");
			btPersoenlicheDatenBearbeiten.setMinWidth(300);
			this.changeButtonStyleOnHover(btPersoenlicheDatenBearbeiten);
			btPersoenlicheDatenBearbeiten.setOnAction(action -> {
				primaryStage.setScene(
						new PersoenlicheDatenBearbeiten(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
			});
		}

		return this.btPersoenlicheDatenBearbeiten;
	}

	private Button setBtPersoenlicheDatenAnzeigen() {
		if (this.btPersoenlicheDatenAnzeigen == null) {
			btPersoenlicheDatenAnzeigen = new Button("Persoenliche Daten Anzeigen");
			btPersoenlicheDatenAnzeigen.setMinWidth(300);
			this.changeButtonStyleOnHover(btPersoenlicheDatenAnzeigen);
			btPersoenlicheDatenAnzeigen.setOnAction(action -> {
				primaryStage.setScene(
						new PersoenlicheDatenAnzeigen(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
			});
		}

		return this.btPersoenlicheDatenAnzeigen;
	}
	
	/**
	 * Funktion zum Aendern des Buttonsstils beim Hover
	 * @param button	Button, der gestylt wird
	 */
	private void changeButtonStyleOnHover(final Button button) {
		String moreStyles = "; -fx-text-fill: #ffffff; -fx-font-size: 16; -fx-padding: 16 12 16 12; -fx-background-radius: 0; -fx-border-color: #000000; -fx-border-width: 0 0 1 0";
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

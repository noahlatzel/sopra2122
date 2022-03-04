package de.wwu.sopra.darstellung.kunde;

import de.wwu.sopra.anwendung.kunde.Kundensteuerung;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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
	Button btProfil;
	Button btBestellungen;
	Button btAbmelden;
	ChoiceBox<Button> choicebox;
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
			flowpane.getChildren().add(setChoiceBox());
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
				primaryStage.setScene(new StartseiteKunde(primaryStage, getWidth(), getHeight(), kundensteuerung));
			});
		}

		return this.btLogo;

	}

	/**
	 * Setzt den Profilbutton
	 * 
	 * @return Profilbutton
	 */
	private Button setBtProfil() {
		if (this.btProfil == null) {
			btProfil = new Button("Profil");
			btProfil.setMinWidth(250);
			btProfil.setOnAction(action -> {
				primaryStage.setScene(new StartseiteKunde(primaryStage, getWidth(), getHeight(), kundensteuerung));
			});
		}

		return this.btProfil;

	}

	/**
	 * Setzt den Abmelden Button
	 * 
	 * @return Abmelden Button
	 */
	private Button setBtAbmelden() {
		if (this.btAbmelden == null) {
			btAbmelden = new Button("Abmelden");
			btAbmelden.setMinWidth(250);
			btAbmelden.setOnAction(action -> {
				primaryStage.setScene(new StartseiteKunde(primaryStage, getWidth(), getHeight(), kundensteuerung));
			});
		}

		return this.btAbmelden;

	}

	/**
	 * Setzt den Bestellungen Button
	 * 
	 * @return Bestellungen Button
	 */
	private Button setBtBestellungen() {
		if (this.btBestellungen == null) {
			btBestellungen = new Button("Bestellungen");
			btBestellungen.setMinWidth(250);
			btBestellungen.setOnAction(action -> {
				primaryStage.setScene(new StartseiteKunde(primaryStage, getWidth(), getHeight(), kundensteuerung));
			});
		}

		return this.btBestellungen;

	}

	/**
	 * Setzt die Auswahlbox fuer die verschiedenen Buttons
	 * 
	 * @return Auswahlbox
	 */
	private ChoiceBox<Button> setChoiceBox() {
		if (choicebox == null) {
			choicebox = new ChoiceBox<Button>();
			choicebox.getItems().add(setBtProfil());
			choicebox.getItems().add(setBtBestellungen());
			choicebox.getItems().add(setBtAbmelden());
		}
		return this.choicebox;
	}
}

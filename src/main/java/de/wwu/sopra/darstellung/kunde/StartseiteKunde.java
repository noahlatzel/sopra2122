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

public class StartseiteKunde extends Scene{


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
	
	public StartseiteKunde(Stage primaryStage, double width, double height, Kundensteuerung kundensteuerung) {
		super(new BorderPane(), width, height);
		this.primaryStage = primaryStage;
		this.setRoot(root);
		this.kundensteuerung = kundensteuerung;
		root.setCenter(new Label("KUNDE STARTSEITE"));
	}	
		
}

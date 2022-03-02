package de.wwu.sopra.darstellung.kunde;

import de.wwu.sopra.anwendung.kunde.Kundensteuerung;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class WarenkorbAnsicht extends KundeOverview {

	public WarenkorbAnsicht(Stage primaryStage, double width, double height, Kundensteuerung kundensteuerung) {
		super(primaryStage, width, height, kundensteuerung);
		this.primaryStage = primaryStage;
		this.setRoot(root);
		this.kundensteuerung = kundensteuerung;
		root.setCenter(new Label("KUNDE WARENKORB"));
	}
}

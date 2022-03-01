/**
 * 
 */
package de.wwu.sopra.darstellung.inhaber;

import de.wwu.sopra.anwendung.mitarbeiter.Inhabersteuerung;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author Valeria Vassallo
 *
 */
public class FahrzeugdatenAendern extends InhaberOverview {
	BorderPane contentWrapper;

	public FahrzeugdatenAendern(Stage primaryStage, double width, double height, Inhabersteuerung inhaberSteuerung) {
		super(primaryStage, width, height, inhaberSteuerung);
		root.setCenter(this.setContentWrapper());
	}
	
	private BorderPane setContentWrapper() {
		if (this.contentWrapper == null) {
			contentWrapper = new BorderPane();
			contentWrapper.setPadding(new Insets(10, 30, 10, 30));
			Label title = new Label("Fahrzeugdaten Aendern");
			title.setStyle("-fx-font-weight: bold");
			title.setFont(new Font("Arial", 32));
			contentWrapper.setTop(title);
		}
		
		return this.contentWrapper;
	}

}

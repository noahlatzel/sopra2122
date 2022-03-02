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
public class SortimentBearbeiten extends InhaberOverview {
	// Erstellung von Variable
	BorderPane contentWrapper;

	public SortimentBearbeiten(Stage primaryStage, double width, double height, Inhabersteuerung inhaberSteuerung) {
		super(primaryStage, width, height, inhaberSteuerung);
		root.setCenter(this.setContentWrapper());
	}
	
	private BorderPane setContentWrapper() {
		// ContentWrapper, um den Titel einzuschliessen
		if (this.contentWrapper == null) {
			contentWrapper = new BorderPane();
			contentWrapper.setPadding(new Insets(10, 30, 10, 30));
			Label title = new Label("Alle Produkte");
			title.setStyle("-fx-font-weight: bold");
			title.setFont(new Font("Arial", 32));
			contentWrapper.setTop(title);
		}
		
		return this.contentWrapper;
	}
}

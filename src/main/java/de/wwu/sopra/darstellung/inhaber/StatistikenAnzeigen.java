/**
 * 
 */
package de.wwu.sopra.darstellung.inhaber;

import java.util.HashMap;

import de.wwu.sopra.anwendung.mitarbeiter.Inhabersteuerung;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Valeria Vassallo
 *
 */
public class StatistikenAnzeigen extends InhaberOverview {
	// Erstellung von Variablen
	BorderPane contentWrapper;
	TilePane tilePane;
	HashMap<String,Float> stats;

	public StatistikenAnzeigen(Stage primaryStage, double width, double height, Inhabersteuerung inhaberSteuerung) {
		super(primaryStage, width, height, inhaberSteuerung);
		
		this.stats = inhaberSteuerung.statistikenAusgeben();
		
		root.setCenter(this.setContentWrapper());
	}
	
	private BorderPane setContentWrapper() {
		// ContentWrapper, um den Titel einzuschliessen
		if (this.contentWrapper == null) {
			contentWrapper = new BorderPane();
			contentWrapper.setPadding(new Insets(10, 30, 10, 30));
			Label title = new Label("Statistiken");
			title.setStyle("-fx-font-weight: bold");
			title.setFont(new Font("Arial", 32));
			contentWrapper.setTop(title);
			contentWrapper.setCenter(this.setStatistikenOverviews());
		}
		
		return this.contentWrapper;
	}
	
	private TilePane setStatistikenOverviews() {
		// TilePane als Main Content Wrapper
		if (this.tilePane == null) {
			tilePane = new TilePane();
			for (String key : stats.keySet()) {
				tilePane.getChildren().add(this.setStatistikComponent(key, stats.get(key)));
			}
		}
		return this.tilePane;
	}

	private VBox setStatistikComponent(String title, float value) {
		VBox wrapper = new VBox(2);
		String titleCapitalized = title.substring(0, 1).toUpperCase() + title.substring(1);
		
		Label titleLabel = new Label(titleCapitalized);
		Text valueAsText = new Text(Float.toString(value));
		
		wrapper.getChildren().add(titleLabel);
		wrapper.getChildren().add(valueAsText);
		
		return wrapper;
	}	
}

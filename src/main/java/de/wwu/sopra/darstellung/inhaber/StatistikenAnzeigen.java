/**
 * 
 */
package de.wwu.sopra.darstellung.inhaber;

import java.util.HashMap;

import de.wwu.sopra.anwendung.mitarbeiter.Inhabersteuerung;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Valeria Vassallo
 *
 */
public class StatistikenAnzeigen extends InhaberOverview {
	// Erstellung von Variablen
	BorderPane contentWrapper;
	GridPane gridPane;
	HashMap<String, Float> stats;

	/**
	 * Zeigt die Statistiken an
	 * 
	 * @param primaryStage     PrimaryStage
	 * @param width            Breite des Fensters
	 * @param height           Hoehe des Fensters
	 * @param inhaberSteuerung InhaberSteuerung
	 */
	public StatistikenAnzeigen(Stage primaryStage, double width, double height, Inhabersteuerung inhaberSteuerung) {
		super(primaryStage, width, height, inhaberSteuerung);

		this.stats = inhaberSteuerung.statistikenAusgeben();

		root.setCenter(this.setContentWrapper());
	}

	/**
	 * Gibt den ContentWrapper fuer Titel zurueck
	 * 
	 * @return ContentWrapper fuer Titel
	 */
	private BorderPane setContentWrapper() {
		// ContentWrapper, um den Titel einzuschliessen
		if (this.contentWrapper == null) {
			contentWrapper = new BorderPane();
			contentWrapper.setPadding(new Insets(10, 30, 10, 30));
			Label title = new Label("Statistiken");
			title.getStyleClass().add("mitarbeiter-content-title");
			contentWrapper.setTop(title);
			contentWrapper.setCenter(this.setStatistikenOverviews());
		}

		return this.contentWrapper;
	}

	/**
	 * Erzeugt StatistikenOverview
	 * 
	 * @return StatistikenOverview
	 */
//	private TilePane setStatistikenOverviews() {
//		// TilePane als Main Content Wrapper
//		if (this.tilePane == null) {
//			tilePane = new TilePane();
//			for (String key : stats.keySet()) {
//				tilePane.getChildren().add(this.setStatistikComponent(key, stats.get(key)));
//			}
//		}
//		return this.tilePane;
//	}
	
	private GridPane setStatistikenOverviews() {
		// GridPane als Main Content Wrapper
		if (this.gridPane == null) {
			gridPane = new GridPane();
			
			gridPane.add(this.setStatistikComponent("umsatz", stats.get("umsatz")), 0, 0);
			gridPane.add(this.setStatistikComponent("ausgaben", stats.get("ausgaben")), 1, 0);
			gridPane.add(this.setStatistikComponent("einnahmen", stats.get("einnahmen")), 0, 1);
			gridPane.add(this.setStatistikComponent("arbeitszeit", stats.get("arbeitszeit")), 1, 1);
			
			gridPane.getStyleClass().add("inhaber-statistiken-content-wrapper");
		}
		return this.gridPane;
	}

	/**
	 * Erzeugt die einzelnen Statistiken als VBox
	 * 
	 * @param title Name der Statistik
	 * @param value Wert
	 * @return VBox
	 */
	private VBox setStatistikComponent(String title, float value) {
		VBox wrapper = new VBox(2);
		String titleCapitalized = title.substring(0, 1).toUpperCase() + title.substring(1);

		Label titleLabel = new Label(titleCapitalized);
		Text valueAsText = new Text(Float.toString(value));
		
		// Styling
		titleLabel.getStyleClass().add("inhaber-statistiken-item-label");
		valueAsText.getStyleClass().add("inhaber-statistiken-item-text");

		wrapper.getChildren().add(titleLabel);
		wrapper.getChildren().add(valueAsText);

		return wrapper;
	}
}

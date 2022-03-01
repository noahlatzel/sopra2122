/**
 * 
 */
package de.wwu.sopra.darstellung.inhaber;

import de.wwu.sopra.anwendung.mitarbeiter.Inhabersteuerung;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * @author Valeria Vassallo
 *
 */
public class Statistiken extends InhaberOverview {

	public Statistiken(Stage primaryStage, double width, double height, Inhabersteuerung inhaberSteuerung) {
		super(primaryStage, width, height, inhaberSteuerung);
		root.setCenter(new Label("Mostrar estadisticas"));
	}
}

package de.wwu.sopra.darstellung.lagerist;

import de.wwu.sopra.anwendung.mitarbeiter.Lageristensteuerung;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Produkt;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * GUI Klasse fuer BestelleNach
 * 
 * @author Noah Latzel
 */
public class BestelleNach extends LageristOverview {
	TilePane tilePane;
	NachbestellungProduktGUI produktGUI;

	/**
	 * Diese Methode zeigt alle Produkte, die nachbestellbar sind, da der
	 * Grosshaendler sie auf Lager hat, und im Sortiment sind.
	 * 
	 * @param primaryStage        primaryStage uebergeben aus LageristOverview
	 * @param width               Breite des Fensters
	 * @param height              Hoehe des Fensters
	 * @param lageristenSteuerung Die Lageristensteuerung, die uebergeben werden
	 *                            soll.
	 */
	public BestelleNach(Stage primaryStage, double width, double height, Lageristensteuerung lageristenSteuerung) {
		super(primaryStage, width, height, lageristenSteuerung);
		produktGUI = new NachbestellungProduktGUI(primaryStage, width, height, lageristenSteuerung);
		root.setCenter(new Label("Nachbestellen..."));
		tilePane = new TilePane();
		tilePane.setPadding(new Insets(20));
		tilePane.setHgap(5);
		tilePane.setVgap(5);

		for (Produkt produkt : lageristenSteuerung.getSortiment()) {
			if (Lager.getLagerbestand().get(produkt) != null) {
				tilePane.getChildren()
						.add(produktGUI.setProduktAnsicht(produkt, Lager.getProduktBestand(produkt.getName())));
			}
		}
		root.setCenter(tilePane);
	}
}
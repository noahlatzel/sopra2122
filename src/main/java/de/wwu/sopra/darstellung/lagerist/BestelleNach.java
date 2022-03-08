package de.wwu.sopra.darstellung.lagerist;

import de.wwu.sopra.anwendung.mitarbeiter.Lageristensteuerung;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Produkt;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * GUI Klasse fuer BestelleNach
 * 
 * @author Noah Latzel
 */
public class BestelleNach extends LageristOverview {
	TilePane tilePane;
	BorderPane contentWrapper;
	NachbestellungProduktGUI produktGUI;
	ScrollPane scrollPane;

	/**
	 * Diese Methode zeigt alle Produkte, die im Sortiment sind.
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

		root.setCenter(this.setContentWrapper());

	}

	/**
	 * Erzeugt ContentWrapper
	 * 
	 * @return ContentWrapper
	 */
	private BorderPane setContentWrapper() {
		// ContentWrapper, um den Titel einzuschliessen
		if (this.contentWrapper == null) {
			contentWrapper = new BorderPane();
			contentWrapper.setPadding(new Insets(10, 30, 10, 30));
			Label title = new Label("Nachbestellungen");
			title.setStyle("-fx-font-weight: bold");
			title.setFont(new Font("Arial", 32));
			contentWrapper.setTop(title);
			contentWrapper.setCenter(this.setScrollPane());
		}

		return this.contentWrapper;
	}

	private TilePane setTilePane() {
		if (tilePane == null) {
			tilePane = new TilePane();
			tilePane.setPadding(new Insets(20));
			tilePane.setHgap(10);
			tilePane.setVgap(10);
			tilePane.setPrefColumns(4);
			for (Produkt produkt : lageristenSteuerung.getSortiment()) {
				if (Lager.getLagerbestand().get(produkt) != null) {
					tilePane.getChildren().add(produktGUI.setProduktAnsicht(produkt, Lager.getProduktBestand(produkt)));
				}
			}
		}
		return tilePane;
	}

	private ScrollPane setScrollPane() {
		if (scrollPane == null) {
			scrollPane = new ScrollPane();
			scrollPane.setContent(setTilePane());
		}
		return scrollPane;
	}
}
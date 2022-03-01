package de.wwu.sopra.darstellung.lagerist;

import de.wwu.sopra.anwendung.mitarbeiter.Lageristensteuerung;
import de.wwu.sopra.datenhaltung.management.Produkt;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class BestelleNach extends LageristOverview {
	TilePane tilePane;
	NachbestellungProduktGUI produktGUI;

	public BestelleNach(Stage primaryStage, double width, double height, Lageristensteuerung lageristenSteuerung) {
		super(primaryStage, width, height, lageristenSteuerung);
		produktGUI = new NachbestellungProduktGUI(primaryStage, height, height, lageristenSteuerung);
		root.setCenter(new Label("Nachbestellen..."));
		tilePane = new TilePane();
		tilePane.setPadding(new Insets(20));
		tilePane.setHgap(5);
		tilePane.setVgap(5);

		// Produkte hinzufuegen zum Testen
		Produkt p1 = new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29);
		Produkt p2 = new Produkt("Fanta", "Toller Geschmack", 0.99, 1.29);
		Produkt p3 = new Produkt("Sprite", "Toller Geschmack", 0.99, 1.29);
		lageristenSteuerung.getLager().addProdukt(p1);
		lageristenSteuerung.getLager().addProdukt(p2);
		lageristenSteuerung.getLager().addProdukt(p3);
		lageristenSteuerung.getLager().removeProdukt(p3);
		lageristenSteuerung.getLager().removeProdukt(p2);
		lageristenSteuerung.getLager().removeProdukt(p1);

		for (String s : lageristenSteuerung.getLager().getLagerbestand().keySet()) {
			tilePane.getChildren()
					.add(produktGUI.setProduktAnsicht(s, lageristenSteuerung.getLager().getProduktBestand(s)));
		}
		root.setCenter(tilePane);
	}
}
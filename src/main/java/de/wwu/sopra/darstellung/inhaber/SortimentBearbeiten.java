/**
 * 
 */
package de.wwu.sopra.darstellung.inhaber;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import de.wwu.sopra.anwendung.mitarbeiter.Inhabersteuerung;
import de.wwu.sopra.datenhaltung.management.Kategorie;
import de.wwu.sopra.datenhaltung.management.Produkt;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Valeria Vassallo
 *
 */
public class SortimentBearbeiten extends InhaberOverview {
	// Erstellung von Variable
	BorderPane contentWrapper;
	TilePane content;

	/**
	 * Zeigt das Sortiment
	 * 
	 * @param primaryStage     PrimaryStage
	 * @param width            Breite des Fensters
	 * @param height           Hoehe des Fensters
	 * @param inhaberSteuerung InhaberSteuerung
	 */
	public SortimentBearbeiten(Stage primaryStage, double width, double height, Inhabersteuerung inhaberSteuerung) {
		super(primaryStage, width, height, inhaberSteuerung);
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
			Label title = new Label("Alle Produkte");
			title.setStyle("-fx-font-weight: bold");
			title.setFont(new Font("Arial", 32));
			contentWrapper.setTop(title);
			contentWrapper.setCenter(this.setContent());

		}

		return this.contentWrapper;
	}

	/**
	 * Setzt den Inhalt
	 * 
	 * @return TilePane mit Inhalt
	 */
	private TilePane setContent() {
		// TilePane als Main Component
		if (this.content == null) {
			content = new TilePane();
			// Get Produkte Im Lager
			HashSet<Produkt> produkteImLager = inhaberSteuerung.sortimentAnzeigen();

			// Text, wenn keine Produkte
			if (produkteImLager.isEmpty()) {
				Text keineProdukte = new Text("Keine Produkte Im Lager");
				keineProdukte.setStyle("-fx-font-weight: bold");
				keineProdukte.setFont(new Font("Arial", 32));
				content.getChildren().add(keineProdukte);
			}

			// Erstellung von ProduktKomponente
			for (Produkt p : produkteImLager) {
				content.getChildren().add(this.setProduktKomponente(p));
			}

			content.setHgap(40);
			content.setVgap(40);
		}

		return this.content;
	}

	/**
	 * Erzeugt Produktkomponente
	 * 
	 * @param produkt Produkt
	 * @return Produktkomponente
	 */
	private GridPane setProduktKomponente(Produkt produkt) {
		GridPane produktGP = new GridPane();

		// Temporary product image
		Rectangle produktImg = new Rectangle(140, 140, 140, 140);
		produktImg.setArcHeight(4);
		produktImg.setArcWidth(4);
		produktImg.setFill(Color.web("#ed4b00"));

		// Erstellung von TextFields und Buttons
		TextField tfName = new TextField();
		TextField tfBeschreibung = new TextField();
		TextField tfPreis = new TextField();
		TextField tfKategorie = new TextField();

		Button btnAktualisieren = new Button("Speichern");
		Button btnLoeschen = new Button("Loeschen");

		tfName.setText(produkt.getName());
		tfBeschreibung.setText(produkt.getBeschreibung());
		tfPreis.setText(String.valueOf(produkt.getVerkaufspreis()));
		try {

			tfKategorie.setText(String.valueOf(produkt.getKategorie().getName()));
		} catch (NullPointerException l) {
			tfKategorie.setText(String.valueOf(produkt.getKategorie()));
		}
		// Auf Grid setzen
		produktGP.add(produktImg, 0, 0, 2, 1);
		produktGP.add(tfName, 0, 1, 2, 1);
		produktGP.add(tfBeschreibung, 0, 2, 2, 1);
		produktGP.add(tfPreis, 0, 3, 2, 1);
		produktGP.add(tfKategorie, 0, 4, 2, 1);
		produktGP.add(btnAktualisieren, 0, 5);
		produktGP.add(btnLoeschen, 1, 5);

		// Center Elemente
		produktGP.setAlignment(Pos.CENTER);
		produktGP.setHgap(10);
		produktGP.setVgap(10);
		ColumnConstraints col = new ColumnConstraints();
		col.setHalignment(HPos.CENTER);
		produktGP.getColumnConstraints().add(col);

		// es wie eine Komponente aussehen lassen
		produktGP.setPadding(new Insets(16, 16, 16, 16));
		produktGP.setBackground(
				new Background(new BackgroundFill(Color.web("#c4c4c4"), CornerRadii.EMPTY, Insets.EMPTY)));

		// Funktionalitaet von Buttons
		btnAktualisieren.setOnAction(e -> {

			// Ueberprueft, dass alle Eingaben nicht leer sing
			boolean gueltigeEinngaben = true;
			List<TextField> felder = new ArrayList<TextField>();
			felder.add(tfName);
			felder.add(tfBeschreibung);
			felder.add(tfPreis);

			for (TextField feld : felder) {
				if (feld.getText().isBlank())
					gueltigeEinngaben = false;
			}

			// Erstellung von neuem roten Error-Label
			Label errorLabel = new Label();
			errorLabel.setTextFill(Color.web("#ff0000"));

			if (gueltigeEinngaben == true) {
				Double preisDbl = Double.parseDouble(tfPreis.getText());
				inhaberSteuerung.produktBearbeiten(produkt, tfName.getText(), tfBeschreibung.getText(), preisDbl);
				List<Produkt> produkteZurKategorie = new ArrayList<Produkt>();
				produkteZurKategorie.add(produkt);

				if (!tfKategorie.getText().isBlank()) {
					Kategorie kategorie = new Kategorie(tfKategorie.getText());
					inhaberSteuerung.kategorieProdukteVerwalten(kategorie, produkteZurKategorie, "hinzufuegen");
				}
			} else {
				errorLabel.setText("Keine leere Angaben erlaubt");
				produktGP.add(errorLabel, 0, 6);
			}

		});

		btnLoeschen.setOnAction(e -> {
			List<Produkt> produkte = new ArrayList<>();
			produkte.add(produkt);
			inhaberSteuerung.lagerVerwalten(produkte, "loeschen");
			primaryStage.setScene(new SortimentBearbeiten(primaryStage, getWidth(), getHeight(), inhaberSteuerung));
		});

		return produktGP;
	}

}

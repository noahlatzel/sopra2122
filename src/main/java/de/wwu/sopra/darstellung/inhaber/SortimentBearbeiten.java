/**
 * 
 */
package de.wwu.sopra.darstellung.inhaber;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import de.wwu.sopra.anwendung.mitarbeiter.Inhabersteuerung;
import de.wwu.sopra.datenhaltung.management.Kategorie;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Produkt;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author Valeria Vassallo
 *
 */
public class SortimentBearbeiten extends InhaberOverview {
	// Erstellung von Variable
	BorderPane contentWrapper;
	TilePane content;
	String bildPfad = "";
	final FileChooser fileChooser = new FileChooser();

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
			title.getStyleClass().add("mitarbeiter-content-title");
			contentWrapper.setTop(title);
			ScrollPane scrollPane = new ScrollPane();
			scrollPane.setContent(this.setContent());
			scrollPane.getStyleClass().add("inhaber-sortiment-scrollpane");
			contentWrapper.setCenter(scrollPane);
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
			content.getStyleClass().add("inhaber-sortiment-wrapper");
			// Get Produkte Im Lager
			Set<Produkt> produkteImLager = inhaberSteuerung.sortimentAnzeigen();

			content.setPrefColumns(2);

			// Text, wenn keine Produkte
			if (produkteImLager.isEmpty()) {
				Text keineProdukte = new Text("Keine Produkte Im Lager");
				keineProdukte.getStyleClass().add("inhaber-sortiment-leer-text");
				content.getChildren().add(keineProdukte);
			}

			// Erstellung von ProduktKomponente
			for (Produkt p : produkteImLager) {
				content.getChildren().add(this.setProduktKomponente(p));
			}
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
		Image image = produkt.loadBild();
		ImageView produktImg;
		produktImg = new ImageView();
		produktImg.setImage(image);
		produktImg.setPreserveRatio(true);
		produktImg.setFitHeight(147);

		// Erstellung von TextFields und Buttons
		TextField tfName = new TextField();
		TextField tfBeschreibung = new TextField();
		TextField tfPreis = new TextField();
		ComboBox<String> cbKategorie = new ComboBox<String>();

		Button btnAktualisieren = new Button("Speichern");
		Button btnLoeschen = new Button("Loeschen");

		Button btnProduktBildAendern = new Button("Bild aendern");

		// Styling
		produktGP.getStyleClass().add("inhaber-sortiment-produkt-wrapper");
		tfName.getStyleClass().add("inhaber-sortiment-produkt-textfield");
		tfBeschreibung.getStyleClass().add("inhaber-sortiment-produkt-textfield");
		tfPreis.getStyleClass().add("inhaber-sortiment-produkt-textfield");
		cbKategorie.getStyleClass().add("inhaber-sortiment-produkt-combobox");
		btnAktualisieren.getStyleClass().add("inhaber-sortiment-produkt-button");
		btnLoeschen.getStyleClass().add("inhaber-sortiment-produkt-button");

		// Textfeldern fuellen
		tfName.setText(produkt.getName());
		tfBeschreibung.setText(produkt.getBeschreibung());
		tfPreis.setText(String.valueOf(produkt.getVerkaufspreis()));

		List<Kategorie> kategorien;
		try {
			int index;
			kategorien = new ArrayList<Kategorie>();

			for (Kategorie i : Lager.getKategorien()) {
				kategorien.add(i);
			}

			for (Kategorie i : kategorien) {
				cbKategorie.getItems().add(i.getName());
			}
			for (Kategorie i : kategorien) {
				if (i.getName().equals(produkt.getKategorie().getName())) {

					index = kategorien.indexOf(i);
					cbKategorie.getSelectionModel().select(index);
				}
			}

		} catch (NullPointerException l) {
			// cbKategorie.setText(String.valueOf("Keine Kategorie"));
		}

		// Auf Grid setzen
		produktGP.add(produktImg, 0, 0, 2, 1);
		produktGP.add(tfName, 0, 1, 2, 1);
		produktGP.add(tfBeschreibung, 0, 2, 2, 1);
		produktGP.add(tfPreis, 0, 3, 2, 1);
		produktGP.add(cbKategorie, 0, 4);
		produktGP.add(btnProduktBildAendern, 1, 4);
		produktGP.add(btnAktualisieren, 0, 5);
		produktGP.add(btnLoeschen, 1, 5);

		// Center Elemente
		ColumnConstraints col = new ColumnConstraints();
		col.setHalignment(HPos.CENTER);
		produktGP.getColumnConstraints().add(col);

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
			errorLabel.getStyleClass().add("registrierung-error-label");

			if (gueltigeEinngaben == true) {
				Double preisDbl = Double.parseDouble(tfPreis.getText());
				inhaberSteuerung.produktBearbeiten(produkt, tfName.getText(), tfBeschreibung.getText(), preisDbl,
						bildPfad);
				List<Produkt> produkteZurKategorie = new ArrayList<Produkt>();
				produkteZurKategorie.add(produkt);
				Kategorie kat = produkt.getKategorie();
				try {
					for (Kategorie i : Lager.getKategorien()) {
						if (cbKategorie.getValue().equals(i.getName()))
							kat = i;
					}

					if (produkt.getKategorie() == null) {
						produkt.setKategorie(kat);
						kat.addProdukt(produkt);
					} else {
						produkt.getKategorie().removeProdukt(produkt);
						produkt.setKategorie(kat);
						kat.addProdukt(produkt);
					}
				} catch (NullPointerException l) {

				}
				/*
				 * if (!tfKategorie.getText().isBlank()) { Kategorie kategorie = new
				 * Kategorie(tfKategorie.getText());
				 * inhaberSteuerung.kategorieProdukteVerwalten(kategorie, produkteZurKategorie,
				 * "hinzufuegen"); }
				 */

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

		btnProduktBildAendern.setOnAction(e -> {
			configureFileChooser(fileChooser);
			File bild = fileChooser.showOpenDialog(primaryStage);
			if (bild != null) {
				bildPfad = bild.getName();
				produktImg.setImage(new Image(bild.getAbsolutePath()));
				produktGP.getChildren().remove(0);
				produktGP.getChildren().add(0, produktImg);
			}
		});

		return produktGP;
	}

	private static void configureFileChooser(final FileChooser fileChooser) {
		fileChooser.setTitle("Bild auswaehlen");
		fileChooser.setInitialDirectory(new File("src\\main\\resources\\de\\wwu\\sopra\\datenhaltung\\management"));
		fileChooser.getExtensionFilters()
				.add(new FileChooser.ExtensionFilter("All Images", "*.jpeg", "*.jpg", "*.png", "*.gif", "*.bmp"));
	}

}

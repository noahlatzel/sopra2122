package de.wwu.sopra.darstellung.kunde;

import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.anwendung.kunde.Kundensteuerung;
import de.wwu.sopra.datenhaltung.bestellung.Warenkorb;
import de.wwu.sopra.datenhaltung.management.Produkt;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Darstellungsklasse fuer WarenkorbAnsicht
 * 
 * @author Paul Dirksen
 *
 */
public class WarenkorbAnsicht extends KundeOverview {

	Button btBestellen;

	/**
	 * Konstruktor fuer die Warenkorbansicht
	 * 
	 * @param primaryStage    PrimaryStage
	 * @param width           Breite des Fensters
	 * @param height          Hoehe des Fensters
	 * @param kundensteuerung KundenSteuerung
	 */
	public WarenkorbAnsicht(Stage primaryStage, double width, double height, Kundensteuerung kundensteuerung) {

		super(primaryStage, width, height, kundensteuerung);
		root.setCenter(setOuterBorderPane());
	}

	/**
	 * Enthaelt den Titel der Szene, hier Warenkorb und im Center wird der Rest der
	 * Funktionlitaet erzeugt.
	 * 
	 * @return Gibt Borderpane zurueck.
	 */
	public BorderPane setOuterBorderPane() {
		BorderPane borderpane = new BorderPane();

		borderpane.setTop(setTitle());
		borderpane.setCenter(setInnerBorderPane());

		return borderpane;
	}

	/**
	 * Erstellt eine HBox mit dem Titel "Warenkorb"
	 * 
	 * @return HBox mit Label "Warenkorb"
	 */
	public HBox setTitle() {
		HBox hbox = new HBox();

		Label warenkorb = new Label("Warenkorb");
		warenkorb.setStyle(" -fx-font-size: 20; -fx-font-weight: bold");
		hbox.getChildren().add(warenkorb);

		hbox.setPadding(new Insets(10));

		return hbox;
	}

	/**
	 * Stellt links einen Auflistung der Produkte des Warenkorbs dar, rechts die
	 * Summe und einen Bestellbutton
	 * 
	 * @return Borderpane mit Warenkorbfunktionalitaeten
	 */
	public BorderPane setInnerBorderPane() {
		BorderPane borderpane = new BorderPane();

		Line line = new Line(50, 0, 50, getHeight() / 1.3);
		line.setStroke(Color.GRAY);

		borderpane.setRight(setVBoxBestellen());
		borderpane.setCenter(line);
		borderpane.setLeft(setScrollPane());

		borderpane.setStyle("-fx-background-color: white");

		return borderpane;
	}

	public ScrollPane setScrollPane() {
		VBox vbox = setVBoxProdukte();
		ScrollPane scrollpane = new ScrollPane(vbox);
		vbox.setFillWidth(true);

		scrollpane.setMinWidth(getWidth() / 1.4);
		scrollpane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);

		scrollpane.setStyle(
				" -fx-focus-color: white; -fx-border-style: none; -fx-border-color: white; -fx-background-insets: 0, 0, 0, 0; -fx-color: white; -fx-background-color: transparent; -fx-control-inner-background: white");
		scrollpane.setFitToWidth(true);
		scrollpane.setFitToHeight(true);

		return scrollpane;
	}

	public VBox setVBoxProdukte() {
		VBox vbox = new VBox();

		Warenkorb warenkorb = kundensteuerung.warenkorbAnsicht();

		if (!(warenkorb.getProdukte().isEmpty())) {

			// Liste mit den Namen der verschiedenen Produkten
			List<String> strings = new ArrayList<String>();

			for (Produkt produkt : warenkorb.getProdukte()) {
				List<Produkt> produkte = new ArrayList<Produkt>();

				if (!(strings.contains(produkt.getName()))) {

					String name = produkt.getName();
					for (Produkt prod : warenkorb.getProdukte()) {
						if (prod.getName().equals(name)) {
							produkte.add(prod);
						}
					}

					vbox.getChildren().add(setProduktPanel(produkte));

					strings.add(produkt.getName());
				}

			}

			vbox.setSpacing(30);
		} else {
			Label warenkorbLeerLabel = new Label("Warenkorb leer!");
			vbox.getChildren().add(warenkorbLeerLabel);
			warenkorbLeerLabel.setAlignment(Pos.CENTER);
		}

		vbox.setPadding(new Insets(10));
		vbox.setStyle(" -fx-background-color: white");

		return vbox;
	}

	public VBox setVBoxBestellen() {
		VBox vbox = new VBox();

		VBox summeVbox = setSummeVBox();
		vbox.getChildren().add(summeVbox);
		vbox.getChildren().add(setBtBestellen());

		vbox.setPadding(new Insets(10));
		vbox.setMinWidth(getWidth() / 4);
		VBox.setMargin(btBestellen, new Insets(0, 40, 40, 40));
		VBox.setMargin(summeVbox, new Insets(40, 40, 0, 40));

		return vbox;
	}

	public VBox setSummeVBox() {
		VBox vbox = new VBox();

		Label subtotal = new Label("Subtotal");
		Label preis = new Label();

		double betrag = 0;
		betrag = kundensteuerung.warenkorbAnsicht().getBetrag();
		betrag = (Math.floor(betrag * 100)) / 100;

		preis.setText(betrag + " EUR");

		vbox.getChildren().add(subtotal);
		vbox.getChildren().add(preis);

		subtotal.setStyle(" -fx-font-size: 24; -fx-font-weight: bold");
		preis.setStyle(" -fx-font-size: 20");

		vbox.setPadding(new Insets(100, 40, 160, 5));

		return vbox;
	}

	/**
	 * Bestellbuttonkonfiguration
	 * 
	 * @return Bestellbutton
	 */
	public Button setBtBestellen() {
		if (btBestellen == null) {
			btBestellen = new Button("Bestellen");

			String css = "-fx-background-color: #dadada; -fx-font-weight: bold; -fx-font-size: 30px; -fx-focus-color: #dadada; -fx-border-color: #dadada";

			btBestellen.setOnAction(e -> {
				if (!(kundensteuerung.warenkorbAnsicht().getProdukte().isEmpty())) {
					kundensteuerung.bestellen();
					primaryStage.setScene(new WarenkorbAnsicht(primaryStage, getWidth(), getHeight(), kundensteuerung));
				}
			});

			btBestellen.setOnMouseEntered(e -> {
				btBestellen.setStyle(" -fx-cursor: hand;" + css);
			});

			btBestellen.setOnMouseExited(e -> {
				btBestellen.setStyle(" -fx-cursor: default;" + css);
			});

			btBestellen.setStyle(css);
			btBestellen.setPadding(new Insets(20, 40, 20, 40));
		}
		return btBestellen;
	}

	/**
	 * Erstellt ein Produktpanel fuer das uebergebene Produkt.
	 * 
	 * @param produkte Methode erstellt Produktpanel fuer dieses Produkt.
	 * @return Gibt konfiguriertes Produktpanel zurueck.
	 */
	public HBox setProduktPanel(List<Produkt> produkte) {
		HBox hbox = new HBox();

		Rectangle rect = new Rectangle();
		rect.setFill(Color.LIGHTGRAY);
		rect.setHeight(65);
		rect.setWidth(90);

		VBox vbox = setProduktnameVBox(produkte);
		HBox.setMargin(vbox, new Insets(5, 0, 0, 0));

		Button button = setLoeschenButton(produkte);
		HBox.setMargin(button, new Insets(8, 5, 22, 5));

		hbox.getChildren().add(rect);
		hbox.getChildren().add(vbox);
		hbox.getChildren().add(setPreisLabelVBox(produkte));
		hbox.getChildren().add(button);

		hbox.setPadding(new Insets(0, 15, 0, 30));

		return hbox;
	}

	public VBox setProduktnameVBox(List<Produkt> produkte) {
		VBox vbox = new VBox();

		Label nameLabel = new Label(produkte.get(0).getName());
		Label anzahlLabel = new Label("Anzahl: " + produkte.size());

		nameLabel.setStyle(" -fx-font-weight: bold; -fx-font-size: 14;");
		anzahlLabel.setStyle(" -fx-font-weight: bold; -fx-font-size: 12;");

		vbox.getChildren().add(nameLabel);
		vbox.getChildren().add(anzahlLabel);

		vbox.setPadding(new Insets(5, getWidth() / 3, 5, 15));
		vbox.setSpacing(13);
		vbox.setStyle(" -fx-background-color: white");

		return vbox;
	}

	public VBox setPreisLabelVBox(List<Produkt> produkte) {
		VBox vbox = new VBox();
		Label preisLabel = new Label("Preis");

		// Summieren der Preise der Produkte
		double verkaufspreis = 0;
		for (Produkt produkt : produkte) {
			verkaufspreis = verkaufspreis + produkt.getVerkaufspreis();
		}
		verkaufspreis = (Math.floor(verkaufspreis * 100)) / 100;
		Label preis = new Label(verkaufspreis + " EUR");

		preisLabel.setStyle(" -fx-font-weight: bold;");
		preis.setStyle("-fx-font-size: 14;");

		vbox.getChildren().add(preisLabel);
		vbox.getChildren().add(preis);

		vbox.setPadding(new Insets(10));
		vbox.setSpacing(2);

		return vbox;
	}

	public Button setLoeschenButton(List<Produkt> produkte) {
		Button loeschenBt = new Button();

		ImageView view = new ImageView(getClass().getResource("cross.png").toExternalForm());
		view.setFitWidth(10);
		view.setFitHeight(10);
		loeschenBt.setGraphic(view);

		loeschenBt.setAlignment(Pos.CENTER);

		loeschenBt.setStyle(" -fx-border: none; -fx-background-color: transparent");

		loeschenBt.setOnMouseClicked(e -> {
			kundensteuerung.warenkorbAnsicht().getProdukte().removeAll(produkte);
			primaryStage.setScene(new WarenkorbAnsicht(primaryStage, getWidth(), getHeight(), kundensteuerung));
		});

		loeschenBt.setOnMouseEntered(e -> {
			loeschenBt.setStyle(" -fx-cursor: hand; -fx-border: none; -fx-background-color: transparent");
		});

		loeschenBt.setOnMouseExited(e -> {
			loeschenBt.setStyle(" -fx-cursor: default; -fx-border: none; -fx-background-color: transparent");
		});

		return loeschenBt;
	}

}

package de.wwu.sopra.darstellung.kunde;

import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.anwendung.kunde.Kundensteuerung;
import de.wwu.sopra.datenhaltung.bestellung.Rabatt;
import de.wwu.sopra.datenhaltung.bestellung.Warenkorb;
import de.wwu.sopra.datenhaltung.management.Produkt;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
	ComboBox<String> comboboxRabatt;

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

		double chance = Math.random() * 100;
		if (chance < 3) {
			kundensteuerung.addRabatt();
		}
	}

	/**
	 * Enthaelt den Titel der Szene, hier Warenkorb und im Center wird der Rest der
	 * Funktionlitaet erzeugt.
	 * 
	 * @return Gibt Borderpane zurueck.
	 */
	public BorderPane setOuterBorderPane() {
		BorderPane borderpane = new BorderPane();
		borderpane.getStyleClass().add("kunde-content-wrapper");

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
		warenkorb.getStyleClass().add("kunde-warenkorb-label");
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

		borderpane.getStyleClass().add("kunde-bestellung-ansicht-inner-wrapper");

		return borderpane;
	}

	/**
	 * scrollpane
	 * 
	 * @return ScrollPane
	 */
	public ScrollPane setScrollPane() {
		VBox vbox = setVBoxProdukte();
		ScrollPane scrollpane = new ScrollPane(vbox);
		vbox.setFillWidth(true);

		scrollpane.setMinWidth(getWidth() / 1.4);
		scrollpane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);

		scrollpane.getStyleClass().add("kunde-bestellung-warenkorb-scrollpane");
		scrollpane.setFitToWidth(true);
		scrollpane.setFitToHeight(true);

		return scrollpane;
	}

	/**
	 * Erzeugt eine VBox mit einer Auflistung von Produkten
	 * 
	 * @return VBox mit Auflistung von Produkten
	 */
	public VBox setVBoxProdukte() {
		VBox vbox = new VBox();
		vbox.getStyleClass().add("kunde-bestellung-warenkorb-vbox-produkte");

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

		return vbox;
	}

	/**
	 * VBox zum bestellen
	 * 
	 * @return vBox bstellen
	 */
	public VBox setVBoxBestellen() {
		VBox vbox = new VBox();

		if (kundensteuerung.getRabatte().size() > 0) {
			VBox rabattVBox = setRabattcodeVBox();
			vbox.getChildren().add(rabattVBox);
			VBox.setMargin(rabattVBox, new Insets(0, 40, 0, 40));
		}

		VBox summeVbox = setSummeVBox();
		vbox.getChildren().add(summeVbox);
		vbox.getChildren().add(setBtBestellen());

		vbox.setPadding(new Insets(10));
		vbox.setMinWidth(getWidth() / 4);
		VBox.setMargin(btBestellen, new Insets(0, 40, 40, 40));
		VBox.setMargin(summeVbox, new Insets(0, 40, 0, 40));

		return vbox;
	}

	/**
	 * Vbox zum setzen des Rabattcodes
	 * 
	 * @return Vbox rabattcode
	 */
	public VBox setRabattcodeVBox() {
		VBox vbox = new VBox();

		Label rabattLabel = new Label("Rabattcode:");
		rabattLabel.getStyleClass().add("kunde-warenkorb-rabatt-label");

		comboboxRabatt = new ComboBox<String>();
		for (Rabatt rabatt : kundensteuerung.getRabatte()) {
			comboboxRabatt.getItems().add(rabatt.getRabattcode());
		}
		vbox.getChildren().add(rabattLabel);
		vbox.getChildren().add(comboboxRabatt);

		comboboxRabatt.getStyleClass().add("kunde-combobox");

		return vbox;
	}

	/**
	 * Vbox der summe
	 * 
	 * @return Vboc der summe
	 */
	public VBox setSummeVBox() {
		VBox vbox = new VBox();

		Label subtotal = new Label("Subtotal");
		Label preis = new Label();

		double betrag = 0;
		kundensteuerung.warenkorbAnsicht().setBetrag();
		betrag = kundensteuerung.warenkorbAnsicht().getBetrag();
		betrag = (Math.floor(betrag * 100)) / 100;

		preis.setText(betrag + " EUR");

		vbox.getChildren().add(subtotal);
		vbox.getChildren().add(preis);

		subtotal.getStyleClass().add("kunde-warenkorb-summe-box-subtotal");
		preis.getStyleClass().add("kunde-warenkorb-summe-box-preis");

		vbox.getStyleClass().add("kunde-warenkorb-summe-box");

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

			btBestellen.setOnAction(e -> {
				if (!(kundensteuerung.warenkorbAnsicht().getProdukte().isEmpty())) {

					if (comboboxRabatt != null && comboboxRabatt.getValue() != null) {
						kundensteuerung.bestellen(kundensteuerung.rabattEinloesen(comboboxRabatt.getValue()));
					} else {
						kundensteuerung.bestellen();
					}

					primaryStage.setScene(new WarenkorbAnsicht(primaryStage, getWidth(), getHeight(), kundensteuerung));
				}
			});

			btBestellen.getStyleClass().add("startpage-button");

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
	public BorderPane setProduktPanel(List<Produkt> produkte) {
		BorderPane borderpane = new BorderPane();

		Rectangle rect = new Rectangle();
		rect.setFill(Color.LIGHTGRAY);
		rect.setHeight(65);
		rect.setWidth(90);

		ImageView produktImg = new ImageView(produkte.get(0).loadBild());
		produktImg.setFitHeight(65);
		produktImg.setPreserveRatio(true);

		VBox vbox = setProduktnameVBox(produkte);
		BorderPane.setMargin(vbox, new Insets(5, 0, 0, 0));

		Button button = setLoeschenButton(produkte);
		BorderPane.setMargin(button, new Insets(8, 5, 22, 5));

		HBox hbox = new HBox();

		hbox.getChildren().add(setPreisLabelVBox(produkte));
		hbox.getChildren().add(button);
		hbox.setAlignment(Pos.CENTER);

		borderpane.setLeft(produktImg);
		borderpane.setCenter(vbox);
		borderpane.setRight(hbox);

		borderpane.setPadding(new Insets(0, 15, 0, 30));

		return borderpane;
	}

	/**
	 * Vbox fuer den namen
	 * 
	 * @param produkte aktuelles Produkt
	 * @return Vbox mit namen
	 */
	public VBox setProduktnameVBox(List<Produkt> produkte) {
		VBox vbox = new VBox();

		Label nameLabel = new Label(produkte.get(0).getName());
		Label anzahlLabel = new Label("Anzahl: " + produkte.size());

		nameLabel.getStyleClass().add("kunde-bestellung-warenkorb-produkt-name");
		anzahlLabel.getStyleClass().add("kunde-bestellung-warenkorb-produkt-quantity");

		vbox.getChildren().add(nameLabel);
		vbox.getChildren().add(anzahlLabel);

		vbox.setPadding(new Insets(5, getWidth() / 3, 5, 15));
		vbox.getStyleClass().add("kunde-bestellung-warenkorb-produkt-name-vbox");

		return vbox;
	}

	/**
	 * Vbox fuer das Preislabel
	 * 
	 * @param produkte Aktuelles Produkt
	 * @return Vbox preislabel
	 */
	public VBox setPreisLabelVBox(List<Produkt> produkte) {
		VBox vbox = new VBox();
		vbox.getStyleClass().add("kunde-warenkorb-price-label-box");
		Label preisLabel = new Label("Preis");

		// Summieren der Preise der Produkte
		double verkaufspreis = 0;
		for (Produkt produkt : produkte) {
			verkaufspreis = verkaufspreis + produkt.getVerkaufspreis();
		}
		verkaufspreis = (Math.floor(verkaufspreis * 100)) / 100;
		Label preis = new Label(verkaufspreis + " EUR");

		preisLabel.getStyleClass().add("kunde-bestellung-warenkorb-price-label");
		preis.getStyleClass().add("kunde-bestellung-warenkorb-price");

		vbox.getChildren().add(preisLabel);
		vbox.getChildren().add(preis);

		return vbox;
	}

	/**
	 * Erstellt einen Button der das betreffende Produkt vollstaendig aus dem
	 * Warenkorb entfernt, also zum Beispiel werden alle Cola-Produkte entfernt, die
	 * im Warenkorb sind.
	 * 
	 * @param produkte Liste mit Proudkten einer Art.
	 * @return Gibt einen Button zurueck, der alle Produkte des uebergebenen
	 *         Produkts aus dem Warenkorb entfernt.
	 */
	public Button setLoeschenButton(List<Produkt> produkte) {
		Button loeschenBt = new Button();
		loeschenBt.getStyleClass().add("kunde-warenkorb-loeschen-botton");

		ImageView view = new ImageView(getClass().getResource("cross.png").toExternalForm());
		view.setFitWidth(10);
		view.setFitHeight(10);
		loeschenBt.setGraphic(view);

		loeschenBt.setOnMouseClicked(e -> {
			kundensteuerung.warenkorbAnsicht().getProdukte().removeAll(produkte);
			primaryStage.setScene(new WarenkorbAnsicht(primaryStage, getWidth(), getHeight(), kundensteuerung));
		});

		return loeschenBt;
	}

}

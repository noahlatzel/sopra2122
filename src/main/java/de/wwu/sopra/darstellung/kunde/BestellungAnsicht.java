package de.wwu.sopra.darstellung.kunde;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.anwendung.kunde.Kundensteuerung;
import de.wwu.sopra.datenhaltung.bestellung.BestellStatus;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
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
 * Darstellungsklasse fuer BestellungAnsicht
 * 
 * @author Jasmin Horstknepper
 *
 */
public class BestellungAnsicht extends KundeOverview {

	Bestellung bestellung;
	Button btStornieren;
	Button btNachbestellen;
	Button btRechnung;
	Label fehlerLabel;

	/**
	 * Konstruktor fuer die Bestellansicht
	 * 
	 * @param primaryStage    PrimaryStage
	 * @param width           Breite des Fensters
	 * @param height          Hoehe des Fensters
	 * @param kundensteuerung KundenSteuerung
	 */
	public BestellungAnsicht(Stage primaryStage, double width, double height, Kundensteuerung kundensteuerung,
			Bestellung bestellung) {
		super(primaryStage, width, height, kundensteuerung);
		this.primaryStage = primaryStage;
		this.setRoot(root);
		this.kundensteuerung = kundensteuerung;
		this.bestellung = bestellung;
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
		borderpane.getStyleClass().add("kunde-content-wrapper");

		borderpane.setTop(setTitle());
		borderpane.setCenter(setInnerBorderPane());

		return borderpane;
	}

	/**
	 * Erstellt eine VBox mit dem Titel "Warenkorb"
	 * 
	 * @return VBox mit Label "Warenkorb"
	 */
	public VBox setTitle() {
		VBox vbox = new VBox();

		Label bestellung = new Label("Bestellung Nr." + this.bestellung.getBestellnummer());
		bestellung.getStyleClass().add("kunde-bestellung-ansicht-title");

		Label datum = new Label("Bestellt am " + this.bestellung.getDatum().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT)));

		vbox.getChildren().add(bestellung);
		vbox.getChildren().add(datum);

		vbox.getStyleClass().add("kunde-bestellung-ansicht-title-wrapper");

		return vbox;
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

		borderpane.setRight(setVBoxRechts());
		borderpane.setCenter(line);
		borderpane.setLeft(setScrollPane());

		borderpane.getStyleClass().add("kunde-bestellung-warenkorb-inner-wrapper");

		return borderpane;
	}

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

	public VBox setVBoxProdukte() {
		VBox vbox = new VBox();
		vbox.getStyleClass().add("kunde-bestellung-warenkorb-vbox-produkte");

		List<Produkt> produkteBest = bestellung.getProdukte();

		if (!(produkteBest.isEmpty())) {

			// Liste mit den Namen der verschiedenen Produkten
			List<String> strings = new ArrayList<String>();

			for (Produkt produkt : produkteBest) {
				List<Produkt> produkte = new ArrayList<Produkt>();

				if (!(strings.contains(produkt.getName()))) {

					String name = produkt.getName();
					for (Produkt prod : produkteBest) {
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

	public VBox setVBoxRechts() {
		VBox vbox = new VBox();
		vbox.getStyleClass().add("kunde-bestellung-ansicht-right-side");

		VBox summeVbox = setSummeVBox();
		VBox statusVBox = setStatusVBox();
		fehlerLabel = new Label();
		fehlerLabel.setMaxWidth(180);

		if (bestellung.getRabatt() != null) {
			VBox rabattVBox = setRabattcodeVBox();
			vbox.getChildren().add(rabattVBox);
			VBox.setMargin(rabattVBox, new Insets(0, 40, 0, 40));
		}
		vbox.getChildren().add(summeVbox);
		vbox.getChildren().add(statusVBox);
		if (this.bestellung.getStatus() == BestellStatus.OFFEN) {
			vbox.getChildren().add(setBtStornieren());
			VBox.setMargin(btStornieren, new Insets(0, 40, 10, 40));
		}
		vbox.getChildren().add(setBtNachbestellen());
		if (this.bestellung.getStatus() == BestellStatus.ABGESCHLOSSEN) {
			vbox.getChildren().add(setBtRechnung());
			VBox.setMargin(btRechnung, new Insets(10, 40, 10, 40));
		}
		vbox.getChildren().add(fehlerLabel);

		vbox.setMinWidth(getWidth() / 4);

		VBox.setMargin(fehlerLabel, new Insets(20, 40, 10, 40));
		VBox.setMargin(btNachbestellen, new Insets(0, 40, 0, 40));
		VBox.setMargin(summeVbox, new Insets(0, 40, 0, 40));
		VBox.setMargin(statusVBox, new Insets(0, 40, 0, 40));

		return vbox;
	}

	public VBox setRabattcodeVBox() {
		VBox vbox = new VBox();

		Label rabattLabel = new Label(" Rabattcode:");
		Label rabatt = new Label(" " + bestellung.getRabatt().getRabattcode() + " - "
				+ bestellung.getRabatt().getProzent() + "% Rabatt");
		rabattLabel.getStyleClass().add("kunde-bestellung-ansicht-rabattcode-status-preis-label");
		rabatt.getStyleClass().add("kunde-bestellung-ansicht-rabattcode-status-preis");

		vbox.getChildren().add(rabattLabel);
		vbox.getChildren().add(rabatt);

		return vbox;
	}

	/**
	 * Rechnungsbuttonkonfiguration
	 * 
	 * @return Rechnungsbutton
	 */
	public Button setBtRechnung() {
		if (btRechnung == null) {
			btRechnung = new Button("Rechnung");

			btRechnung.getStyleClass().add("startpage-button");

			btRechnung.setOnAction(e -> {
				RechnungAnsicht.display(bestellung.getRechnung(), getWidth(), getHeight());
			});

			btRechnung.setPadding(new Insets(15, 20, 15, 20));
		}

		return btRechnung;
	}

	public VBox setStatusVBox() {
		VBox vbox = new VBox();

		Label statusLabel = new Label("Status");
		Label status = new Label();

		status.setText(convertEnumToString(this.bestellung.getStatus()));

		vbox.getChildren().add(statusLabel);
		vbox.getChildren().add(status);

		statusLabel.getStyleClass().add("kunde-bestellung-ansicht-rabattcode-status-preis-label");
		status.getStyleClass().add("kunde-bestellung-ansicht-rabattcode-status-preis");

		vbox.setPadding(new Insets(0, 40, 20, 5));

		return vbox;
	}

	public VBox setSummeVBox() {
		VBox vbox = new VBox();

		Label subtotal = new Label("Subtotal");
		Label preis = new Label();

		double betrag = 0;
		betrag = bestellung.getBetrag();
		betrag = (Math.floor(betrag * 100)) / 100;

		preis.setText(betrag + " EUR");

		vbox.getChildren().add(subtotal);
		vbox.getChildren().add(preis);

		subtotal.getStyleClass().add("kunde-bestellung-ansicht-rabattcode-status-preis-label");
		preis.getStyleClass().add("kunde-bestellung-ansicht-rabattcode-status-preis");

		vbox.setPadding(new Insets(20, 40, 20, 5));

		return vbox;
	}

	/**
	 * Stornierbuttonkonfiguration
	 * 
	 * @return Stornierbutton
	 */
	public Button setBtNachbestellen() {
		if (btNachbestellen == null) {
			btNachbestellen = new Button("Nachbestellen");

			btNachbestellen.getStyleClass().add("startpage-button");

			btNachbestellen.setOnAction(e -> {
				try {
					kundensteuerung.nachbestellen(this.bestellung);
				} catch (IllegalArgumentException e1) {
					fehlerLabel.setText(
							"Nachbestellung nicht moeglich: Mindestens eines der Produkte  nicht ausreichend verfuegbar im Sortiment!");
					fehlerLabel.setWrapText(true);
				}
			});

			btNachbestellen.setPadding(new Insets(15, 20, 15, 20));
		}

		return btNachbestellen;
	}

	/**
	 * Stornierbuttonkonfiguration
	 * 
	 * @return Stornierbutton
	 */
	public Button setBtStornieren() {
		if (btStornieren == null) {
			btStornieren = new Button("Stornieren");

			btStornieren.getStyleClass().add("startpage-button");

			btStornieren.setOnAction(e -> {
				this.bestellung.setStatus(BestellStatus.STORNIERT);
				primaryStage.setScene(
						new BestellungAnsicht(primaryStage, getWidth(), getHeight(), kundensteuerung, this.bestellung));
			});

			btStornieren.setPadding(new Insets(15, 30, 15, 30));
		}

		return btStornieren;
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

		ImageView produktBild = new ImageView(produkte.get(0).loadBild());
		produktBild.setPreserveRatio(true);
		produktBild.setFitHeight(65);

		VBox vbox = setProduktnameVBox(produkte);
		HBox.setMargin(vbox, new Insets(5, 0, 0, 0));

		hbox.getChildren().add(produktBild);
		hbox.getChildren().add(vbox);
		hbox.getChildren().add(setPreisLabelVBox(produkte));

		hbox.setPadding(new Insets(0, 15, 0, 30));

		return hbox;
	}

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

	public VBox setPreisLabelVBox(List<Produkt> produkte) {
		VBox vbox = new VBox();
		vbox.getStyleClass().add("kunde-bestellung-ansicht-price-label-box");
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
	 * Verarbeitet einen Bestellstatus in einen String.
	 * 
	 * @param status Wird in String umgewandelt.
	 * @return String, welcher Bestellstatus repraesentiert.
	 */
	private String convertEnumToString(BestellStatus status) {
		switch (status) {
		case OFFEN:
			return "Offen";
		case STORNIERT:
			return "Storniert";
		case IN_ZUSTELLUNG:
			return "In Zustellung";
		case ABGESCHLOSSEN:
			return "Abgeschlossen";
		case IN_BEARBEITUNG:
			return "In Bearbeitung";
		}
		return null;
	}

}

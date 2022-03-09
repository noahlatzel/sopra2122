package de.wwu.sopra.darstellung.kunde;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.datenhaltung.bestellung.Rechnung;
import de.wwu.sopra.datenhaltung.management.Produkt;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class RechnungAnsicht {

	private static Rechnung rechnung;
	private static Stage primaryStage;
	private static double WIDTH;
	private static double HEIGHT;

	/**
	 * Erzeugt ein Fenster mit der uebergebenen Rechnung
	 * 
	 * @param rechnung Rechnung wird im Fenster angezeigt.
	 * @param width    Breite des Fensters.
	 * @param height   Hoehe des Fensters.
	 */
	public static void display(Rechnung rechnung, double width, double height) {
		primaryStage = new Stage();
		RechnungAnsicht.rechnung = rechnung;
		WIDTH = width;
		HEIGHT = height;

		primaryStage.setTitle("Rechnung Nr." + rechnung.getRechnungsnummer());

		Scene scene = new Scene(setOuterBorderPane(), WIDTH, HEIGHT);

		primaryStage.setScene(scene);
		primaryStage.setResizable(true);
		primaryStage.sizeToScene();
		primaryStage.show();
	}

	/**
	 * Enthaelt den Titel der Szene, hier Warenkorb und im Center wird der Rest der
	 * Funktionlitaet erzeugt.
	 * 
	 * @return Gibt Borderpane zurueck.
	 */
	public static BorderPane setOuterBorderPane() {
		BorderPane borderpane = new BorderPane();

		borderpane.setTop(setTitle());
		borderpane.setCenter(setInnerBorderPane());

		return borderpane;
	}

	/**
	 * Erstellt eine VBox mit dem Titel "Warenkorb"
	 * 
	 * @return VBox mit Label "Warenkorb"
	 */
	public static VBox setTitle() {
		VBox vbox = new VBox();

		Label bestellung = new Label("Rechnung-Nr." + RechnungAnsicht.rechnung.getRechnungsnummer());
		bestellung.setStyle(" -fx-font-size: 24; -fx-font-weight: bold");

		Label datum = new Label("Bestellt am " + RechnungAnsicht.rechnung.getDatum()
				.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT)));

		vbox.getChildren().add(bestellung);
		vbox.getChildren().add(datum);

		vbox.setPadding(new Insets(10));

		return vbox;
	}

	/**
	 * Stellt links einen Auflistung der Produkte des Warenkorbs dar, rechts die
	 * Summe und einen Bestellbutton
	 * 
	 * @return Borderpane mit Warenkorbfunktionalitaeten
	 */
	public static BorderPane setInnerBorderPane() {
		BorderPane borderpane = new BorderPane();

		Line line = new Line(50, 0, 50, HEIGHT / 1.2);
		line.setStroke(Color.GRAY);

		borderpane.setRight(setVBoxRechts());
		borderpane.setCenter(line);
		borderpane.setLeft(setScrollPane());

		borderpane.setStyle("-fx-background-color: white");

		return borderpane;
	}

	/**
	 * Erzeugt Scrollpane mit den Produkten der Bestellung der Rechnung.
	 * 
	 * @return Scrollpane mit den Produkten der Bestellung der Rechnung
	 */
	public static ScrollPane setScrollPane() {
		VBox vbox = setVBoxProdukte();
		ScrollPane scrollpane = new ScrollPane(vbox);
		vbox.setFillWidth(true);

		scrollpane.setMinWidth(WIDTH / 1.4);
		scrollpane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);

		scrollpane.setStyle(
				" -fx-focus-color: white; -fx-border-style: none; -fx-border-color: white; -fx-background-insets: 0, 0, 0, 0; -fx-color: white; -fx-background-color: transparent; -fx-control-inner-background: white");
		scrollpane.setFitToWidth(true);
		scrollpane.setFitToHeight(true);

		return scrollpane;
	}

	/**
	 * Erzeugt VBox mit den Produkten der Bestellung der Rechnung
	 * 
	 * @return VBox mit den Produkten der Bestellung der Rechnung
	 */
	public static VBox setVBoxProdukte() {
		VBox vbox = new VBox();

		List<Produkt> produkteBest = RechnungAnsicht.rechnung.getBestellung().getProdukte();

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

		vbox.setPadding(new Insets(10));
		vbox.setStyle(" -fx-background-color: white");

		return vbox;
	}

	/**
	 * Erzeugt VBox mit dem Gesamtbetrag der Rechnung
	 * 
	 * @return VBox mit dem Gesamtbetrag der Rechnung in einer VBox
	 */
	public static VBox setVBoxRechts() {
		VBox vbox = new VBox();

		if (rechnung.getBestellung().getRabatt() != null) {
			VBox rabattVBox = setRabattcodeVBox();
			vbox.getChildren().add(rabattVBox);
			VBox.setMargin(rabattVBox, new Insets(20, 40, 0, 40));
		}

		VBox summeVbox = setSummeVBox();
		vbox.getChildren().add(summeVbox);

		vbox.setPadding(new Insets(10));
		vbox.setMinWidth(WIDTH / 4);

		VBox.setMargin(summeVbox, new Insets(0, 40, 0, 40));

		return vbox;
	}

	/**
	 * Erzeugt, falls benoetigt, eine VBox zum Anzeigen des Rabatts auf die
	 * Bestellung.
	 * 
	 * @return Gibt eine VBox zurueck, die den Rabatt anzeigt.
	 */
	public static VBox setRabattcodeVBox() {
		VBox vbox = new VBox();

		Label rabattLabel = new Label(" Rabattcode:");
		Label rabatt = new Label(" " + rechnung.getBestellung().getRabatt().getRabattcode() + " - "
				+ rechnung.getBestellung().getRabatt().getProzent() + "% Rabatt");
		rabattLabel.setStyle(" -fx-font-size: 20; -fx-font-weight: bold");
		rabatt.setStyle(" -fx-font-size: 16;");

		rabatt.setAlignment(Pos.CENTER_RIGHT);

		vbox.getChildren().add(rabattLabel);
		vbox.getChildren().add(rabatt);

		return vbox;
	}

	/**
	 * Erzeugt VBox mit dem Gesamtbetrag der Rechnung
	 * 
	 * @return VBox mit dem Gesamtbetrag der Rechnung als Label
	 */
	public static VBox setSummeVBox() {
		VBox vbox = new VBox();

		Label subtotal = new Label("Rechnungsbetrag");
		Label preis = new Label();

		double betrag = 0;
		betrag = rechnung.getEndbetrag();
		betrag = (Math.floor(betrag * 100)) / 100;

		preis.setText(betrag + " EUR");

		vbox.getChildren().add(subtotal);
		vbox.getChildren().add(preis);

		subtotal.setStyle(" -fx-font-size: 20; -fx-font-weight: bold");
		preis.setStyle(" -fx-font-size: 16");

		vbox.setPadding(new Insets(30, 40, 20, 5));

		return vbox;
	}

	/**
	 * Erstellt ein Produktpanel fuer das uebergebene Produkt.
	 * 
	 * @param produkte Methode erstellt Produktpanel fuer dieses Produkt.
	 * @return Gibt konfiguriertes Produktpanel zurueck.
	 */
	public static BorderPane setProduktPanel(List<Produkt> produkte) {
		BorderPane borderpane = new BorderPane();

		Rectangle rect = new Rectangle();
		rect.setFill(Color.LIGHTGRAY);
		rect.setHeight(65);
		rect.setWidth(90);

		VBox vbox1 = setProduktnameVBox(produkte);
		HBox.setMargin(vbox1, new Insets(5, 0, 0, 0));

		VBox vbox2 = setPreisLabelVBox(produkte);
		HBox.setMargin(vbox2, new Insets(5, 0, 0, 0));

		borderpane.setLeft(rect);
		borderpane.setCenter(vbox1);
		borderpane.setRight(vbox2);

		vbox1.setMinWidth(WIDTH / 2);

		vbox1.setAlignment(Pos.CENTER_LEFT);
		vbox2.setAlignment(Pos.CENTER_RIGHT);
		borderpane.setPadding(new Insets(0, 15, 0, 30));

		return borderpane;
	}

	/**
	 * Erzeugt VBox mit einem Label fuer den Namen des Produkts und einem Label fuer
	 * die Anzahl des Produkts in der Bestellung der Rechnung
	 * 
	 * @param produkte Liste mit Produkten einer Art
	 * @return VBox mit Label fuer Namen und Anzahl des uebergebenen Produkts
	 */
	public static VBox setProduktnameVBox(List<Produkt> produkte) {
		VBox vbox = new VBox();

		Label nameLabel = new Label(produkte.get(0).getName());
		Label anzahlLabel = new Label("Anzahl: " + produkte.size());

		nameLabel.setStyle(" -fx-font-weight: bold; -fx-font-size: 14;");
		anzahlLabel.setStyle(" -fx-font-weight: bold; -fx-font-size: 12;");

		vbox.getChildren().add(nameLabel);
		vbox.getChildren().add(anzahlLabel);

		vbox.setPadding(new Insets(5, WIDTH / 3, 5, 15));
		vbox.setSpacing(13);
		vbox.setStyle(" -fx-background-color: white");

		return vbox;
	}

	/**
	 * Erzeugt VBox mit einem Label fuer den Preis des Produkts in der Bestellung
	 * der Rechnung
	 * 
	 * @param produkte Liste mit Produkten einer Art
	 * @return VBox mit Label fuer summierten Preis des uebergebenen Produkts
	 */
	public static VBox setPreisLabelVBox(List<Produkt> produkte) {
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
}

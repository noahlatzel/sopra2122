package de.wwu.sopra.darstellung.kunde;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashSet;
import java.util.List;

import de.wwu.sopra.anwendung.kunde.Kundensteuerung;
import de.wwu.sopra.datenhaltung.bestellung.BestellStatus;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.management.Produkt;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * 
 * @author Paul Dirksen
 *
 */
public class UebersichtBestellungen extends KundeOverview {

	private static final String BACKGROUND = " -fx-background-color: white;";
	private static final String STANDARD_BESTELLUNG_PANEL = " -fx-border-color: grey;";

	/**
	 * Konstruktor fuer die Uebersicht der Bestellungen
	 * 
	 * @param primaryStage    PrimaryStage
	 * @param width           Breite des Fensters
	 * @param height          Hoehe des Fensters
	 * @param kundensteuerung KundenSteuerung
	 */
	public UebersichtBestellungen(Stage primaryStage, double width, double height, Kundensteuerung kundensteuerung) {
		super(primaryStage, width, height, kundensteuerung);
		this.primaryStage = primaryStage;
		this.setRoot(root);
		this.kundensteuerung = kundensteuerung;
		root.setCenter(setBorderPane());
	}

	/**
	 * eine Boderpane wird erstellt
	 * 
	 * @return eine BorderPane
	 */
	public BorderPane setBorderPane() {
		BorderPane borderpane = new BorderPane();

		borderpane.setTop(setTitle());
		borderpane.setCenter(setScrollPane());

		borderpane.setStyle(BACKGROUND);

		return borderpane;
	}

	/**
	 * Erstellt eine HBox mit dem Titel "Meine Bestellungen"
	 * 
	 * @return HBox mit Label "Meine Bestellungen"
	 */
	private HBox setTitle() {
		HBox hbox = new HBox();

		Label meineBestellungenLabel = new Label("Meine Bestellungen");
		hbox.getChildren().add(meineBestellungenLabel);

		hbox.setStyle(BACKGROUND + " -fx-font-size: 24; -fx-font-weight: bold");
		hbox.setPadding(new Insets(10));

		return hbox;
	}

	/**
	 * eine Scrollpane wird erstellt
	 * 
	 * @return eine Scrollpane
	 */
	private ScrollPane setScrollPane() {
		ScrollPane scrollpane = new ScrollPane(setBestellungenVBox());

		scrollpane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);

		scrollpane.setStyle(BACKGROUND);
		scrollpane.setFitToHeight(true);
		scrollpane.setFitToWidth(true);

		scrollpane.setPadding(new Insets(20));

		return scrollpane;
	}

	/**
	 * eine Vbox wird erstellt
	 * 
	 * @return eine Vbox
	 */
	private VBox setBestellungenVBox() {
		VBox vbox = new VBox();

		int n = kundensteuerung.bestellungenAnzeigen().size();

		if (n > 0) {
			for (Bestellung bestellung : kundensteuerung.bestellungenAnzeigen()) {
				vbox.getChildren().add(setBestellungPanel(bestellung));
			}
		}

		vbox.setStyle(BACKGROUND);
		vbox.setSpacing(30);
		vbox.setAlignment(Pos.TOP_CENTER);

		return vbox;

	}

	/**
	 * Das Panell fuer die Bestellungen wird erstellt
	 * 
	 * @param bestellung bestellung
	 * @return das Panel der Bestellung
	 */
	private BorderPane setBestellungPanel(Bestellung bestellung) {
		BorderPane borderpane = new BorderPane();

		borderpane.setTop(setBestellungTitelHBox(bestellung));
		borderpane.setCenter(setProdukteFeldVBox(bestellung));

		borderpane.setMinWidth(getWidth() / 1.5);
		borderpane.setMaxWidth(getWidth() / 1.5);

		String css = BACKGROUND + " -fx-border-style: solid; -fx-border-color: gray;";
		borderpane.setStyle(css);

		borderpane.setOnMouseClicked(e -> {
			primaryStage.setScene(
					new BestellungAnsicht(primaryStage, getWidth(), getHeight(), kundensteuerung, bestellung));
		});

		borderpane.setOnMouseEntered(e -> {
			borderpane.setStyle(" -fx-cursor: hand;" + css);
		});

		borderpane.setOnMouseExited(e -> {
			borderpane.setStyle(" -fx-cursor: default;" + css);
		});

		return borderpane;
	}

	/**
	 * Erstellt eine Titelbox mit den passenden Werten zur uebergebenen Bestellung.
	 * 
	 * @param bestellung Titelbox zeigt Werte dieser Bestellung.
	 * @return Gibt konfigurierte Titelbox zurueck.
	 */
	private HBox setBestellungTitelHBox(Bestellung bestellung) {
		HBox hbox = new HBox();

		String status = convertEnumToString(bestellung.getStatus());
		double preis = bestellung.getBetrag();
		preis = (Math.floor(preis * 100)) / 100;
		Label ueberschriftLabel = new Label(bestellung.getDatum()
				.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT)) + " - " + preis
				+ " EUR - " + status);

		hbox.getChildren().add(ueberschriftLabel);

		hbox.setPadding(new Insets(10));
		hbox.setStyle(
				" -fx-background-color: #FF6868; -fx-font-weight: bold; -fx-font-size: 15; -fx-border-bottom-style: solid; -fx-border-bottom-color: gray");

		return hbox;
	}

	/**
	 * Die VBox der Produktefelder
	 * 
	 * @param bestellung bestellung
	 * @return vbox der produkte
	 */
	private VBox setProdukteFeldVBox(Bestellung bestellung) {
		VBox vbox = new VBox();

		// Findet alle verschiedenen Namen bzw Produkte in der Bestellungsliste
		HashSet<String> namen = new HashSet<String>();
		for (Produkt produkt : bestellung.getProdukte()) {
			namen.add(produkt.getName());
		}

		vbox.getChildren().add(setProduktHBox(bestellung, 0));
		String nameZwei = namen.iterator().next();
		boolean found = false;

		for (int i = 0; i < bestellung.getProdukte().size(); i++) {
			if (bestellung.getProdukte().get(i).getName().equals(nameZwei) && namen.size() == 2 && !found) {
				vbox.getChildren().add(setProduktHBox(bestellung, i));
				found = true;
			}
		}

		return vbox;
	}

	/**
	 * erstellt die Produktanele
	 * 
	 * @param bestellung bestellung
	 * @param index      aktuelles Produkt
	 * @return die Produkt vBox
	 */
	private HBox setProduktHBox(Bestellung bestellung, int index) {
		HBox hbox = new HBox();

		Rectangle rect = new Rectangle();
		rect.setFill(Color.LIGHTGRAY);
		rect.setHeight(65);
		rect.setWidth(90);

		ImageView produktBild = new ImageView(bestellung.getProdukte().get(0).loadBild());
		produktBild.setPreserveRatio(true);
		produktBild.setFitHeight(65);

		VBox vbox1 = setProduktnameVBox(bestellung, index);
		HBox.setMargin(vbox1, new Insets(5, 0, 0, 0));

		VBox vbox2 = setPreisLabelVBox(bestellung.getProdukte(), index);
		vbox2.setAlignment(Pos.CENTER_RIGHT);

		hbox.getChildren().add(produktBild);
		hbox.getChildren().add(vbox1);
		hbox.getChildren().add(vbox2);

		hbox.setPadding(new Insets(10, 15, 10, 30));

		return hbox;
	}

	/**
	 * Vbox fuer den Namen
	 * 
	 * @param bestellung Bestllung
	 * @param index      aktuelles Proukt
	 * @return vBoxName
	 */
	public VBox setProduktnameVBox(Bestellung bestellung, int index) {
		VBox vbox = new VBox();

		Label nameLabel = new Label(bestellung.getProdukte().get(index).getName());
		Label anzahlLabel = new Label("Anzahl: " + bestellung.getProduktAnzahl(bestellung.getProdukte().get(index)));

		nameLabel.setStyle(" -fx-font-weight: bold; -fx-font-size: 14;");
		anzahlLabel.setStyle(" -fx-font-weight: bold; -fx-font-size: 12;");

		vbox.getChildren().add(nameLabel);
		vbox.getChildren().add(anzahlLabel);

		vbox.setPadding(new Insets(5, getWidth() / 3, 5, 15));
		vbox.setSpacing(13);
		vbox.setStyle(" -fx-background-color: white");

		return vbox;
	}

	/**
	 * Label fuer den Preis
	 * 
	 * @param produkte Produkte
	 * @param index    aktuelles Produkt
	 * @return das Preis Label
	 */
	public VBox setPreisLabelVBox(List<Produkt> produkte, int index) {
		VBox vbox = new VBox();
		Label preisLabel = new Label("Preis");

		// Summieren der Preise der Produkte
		double verkaufspreis = 0;
		String name = produkte.get(index).getName();
		for (Produkt produkt : produkte) {
			if (produkt.getName().equals(name)) {
				verkaufspreis = verkaufspreis + produkt.getVerkaufspreis();
			}
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
package de.wwu.sopra.darstellung.kunde;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import de.wwu.sopra.anwendung.kunde.Kundensteuerung;
import de.wwu.sopra.datenhaltung.management.Kategorie;
import de.wwu.sopra.datenhaltung.management.Produkt;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Darstellungsklasse fuer StartseiteKunde
 * 
 * @author Jasmin Horstknepper
 *
 */
public class StartseiteKunde extends KundeOverview {

	ScrollPane scrollpane;
	GridPane gridpane;
	BorderPane borderpane;
	BorderPane searchBarBP;
	MenuItem kategorie1;
	TextField textFeldSuche;
	Button btSuche;
	MenuButton menuButton;
	HBox hbox;
	Set<Produkt> produkte;
	Set<Kategorie> kategorien;
	/**
	 * CSS fuer Produkt Panel
	 */
	private static final String STANDARD_PRODUKT_PANEL = "-fx-border-color: grey; -fx-background-color: white; -fx-background-insets: 0, 2;";

	/**
	 * Konstruktor fuer die Startseite des Kunden
	 * 
	 * @param primaryStage    PrimaryStage
	 * @param width           Breite des Fensters
	 * @param height          Hoehe des Fensters
	 * @param kundensteuerung KundenSteuerung
	 * @param produkte        Produkte
	 */
	public StartseiteKunde(Stage primaryStage, double width, double height, Kundensteuerung kundensteuerung,
			Set<Produkt> produkte, Set<Kategorie> kategorien) {
		super(primaryStage, width, height, kundensteuerung);
		this.primaryStage = primaryStage;
		this.setRoot(root);
		this.kundensteuerung = kundensteuerung;
		this.produkte = produkte;

		// Fuegt alle Oberkategorien der Liste der auswaehlbaren Kategorien hinzu.
		if (kategorien == null) {

			this.kategorien = new HashSet<Kategorie>();
			Iterator<Kategorie> it = kundensteuerung.getKategorien().iterator();

			while (it.hasNext()) {
				Kategorie temp = it.next();
				if (temp.getOberkategorie() == null) {
					this.kategorien.add(temp);
				}
			}
		} else {
			this.kategorien = kategorien;
		}

		root.setCenter(setBorderPane());

	}

	/**
	 * Erzeugt aeussere BorderPane, in deren Center die Produkte angezeigt werden
	 * und deren Top die Searchbar ist
	 * 
	 * @return Borderpane fuer den Inhalt der Szene
	 */
	public BorderPane setBorderPane() {
		if (borderpane == null) {
			borderpane = new BorderPane();

			borderpane.setTop(setSearchBarBP());
			borderpane.setCenter(setScrollPane());
		}

		return borderpane;
	}

	/**
	 * Erzeugt eine Leiste mit MenuButton, Suchfeld und Suchbutton.
	 * 
	 * @return Gibt eine Leiste mit MenuButton, Suchfeld und Suchbutton zurueck.
	 */
	public BorderPane setSearchBarBP() {
		if (searchBarBP == null) {
			searchBarBP = new BorderPane();

			searchBarBP.setStyle("-fx-background-color: #FF6868;");

			searchBarBP.setLeft(setKategorienHBox());
			searchBarBP.setRight(setSearchBar());
			searchBarBP.setPadding(new Insets(10));
		}

		return searchBarBP;
	}

	/**
	 * Erstellt eine HBox mit dem Kategorienverzeichnis.
	 * 
	 * @return Eine HBox mit dem Kategorienverzeichnis.
	 */
	public HBox setKategorienHBox() {
		HBox hbox = new HBox();

		Button btZurueck = new Button();

		btZurueck.setOnAction(e -> {
			Iterator<Kategorie> it = this.kategorien.iterator();
			Iterator<Produkt> it2 = produkte.iterator();
			Kategorie kategorie = null;

			// Falls es Produkte auf dieser Seite gibt:
			if (produkte.size() > 0) {

				Produkt tempProd = it2.next();
				if (kategorien.size() <= 0 && tempProd.getKategorie() != null) {
					// Es wird aktuell eine Unterkategorie angezeigt.
					kategorie = tempProd.getKategorie();
					zurueckButton(kategorie);

				} else if (kategorien.size() > 0 && tempProd.getKategorie() != null) {
					// Es wird aktuell eine Oberkategorie oder die Startseite angezeigt.
					kategorie = it.next();

					if (kategorie.getOberkategorie() != null) {
						zurueckButton(kategorie.getOberkategorie());
					}

				} // Ansonsten existieren keine Kategorien.

			} else { // Ansonsten leitet der Button auf die Startseite zurueck.
				primaryStage.setScene(new StartseiteKunde(primaryStage, getWidth(), getHeight(), kundensteuerung,
						kundensteuerung.getLager(), null));
			}

		});

		btZurueck.setMinHeight(30);
		btZurueck.setMinWidth(50);
		btZurueck.setMaxHeight(30);
		btZurueck.setMaxWidth(50);

		String css = "-fx-background-color: #FF6868; -fx-font-weight: bold; -fx-mark-color: #FF6868; -fx-font-size: 18; -fx-focus-color: #FF6868; -fx-border-color: #FF6868";
		btZurueck.setStyle(css);

		btZurueck.setOnMouseEntered(e -> {
			btZurueck.setStyle(" -fx-cursor: hand;" + css);
		});

		btZurueck.setOnMouseExited(e -> {
			btZurueck.setStyle(" -fx-cursor: default;" + css);
		});

		ImageView view = new ImageView(getClass().getResource("return-arrow.png").toExternalForm());
		view.setFitHeight(25);
		view.setFitWidth(25);
		view.setPreserveRatio(true);
		btZurueck.setGraphic(view);
		HBox.setMargin(btZurueck, new Insets(3, 0, 0, 0));

		hbox.getChildren().add(setMenuButton());
		hbox.getChildren().add(btZurueck);

		hbox.setSpacing(10);

		return hbox;
	}

	/**
	 * Weiterfuerung der Konfiguration des zurueck-Buttons
	 * 
	 * @param kategorie
	 */
	private void zurueckButton(Kategorie kategorie) {
		if (kategorie.getOberkategorie() != null) {
			// Die angezeigte Kategorie ist eine Oberkategorie.

			primaryStage.setScene(new StartseiteKunde(
					primaryStage, getWidth(), getHeight(), kundensteuerung, kundensteuerung
							.filterProdukteNachKategorie(kundensteuerung.getLager(), kategorie.getOberkategorie()),
					kategorie.getOberkategorie().getUnterkategorien()));

		} else { // Zeige die Startseite an.
			Set<Kategorie> kategorien = new HashSet<Kategorie>();
			Iterator<Kategorie> it3 = kundensteuerung.getKategorien().iterator();

			while (it3.hasNext()) {
				Kategorie temp = it3.next();
				if (temp.getOberkategorie() == null) {
					kategorien.add(temp);
				}
			}

			primaryStage.setScene(new StartseiteKunde(primaryStage, getWidth(), getHeight(), kundensteuerung,
					kundensteuerung.getLager(), kategorien));
		} // Ansonsten wird die Startseite angezeigt.
	}

	/**
	 * Konfiguriert den MenuButton mit den Kategorien
	 * 
	 * @return MenuButton zur Auswahl von Kategorien
	 */
	public MenuButton setMenuButton() {
		if (menuButton == null) {
			menuButton = new MenuButton("Kategorien");

			menuButton.setMinHeight(30);
			String css = "-fx-background-color: #FF6868; -fx-font-weight: bold; -fx-mark-color: #FF6868; -fx-font-size: 18; -fx-focus-color: #FF6868; -fx-border-color: #FF6868";
			menuButton.setStyle(css);

			Iterator<Kategorie> iterator = this.kategorien.iterator();
			while (iterator.hasNext()) {
				Kategorie kategorie = iterator.next();
				MenuItem temp = new MenuItem(kategorie.getName());

				menuButton.getItems().add(temp);

				temp.setOnAction(e -> {
					primaryStage.setScene(new StartseiteKunde(primaryStage, getWidth(), getHeight(), kundensteuerung,
							kundensteuerung.filterProdukteNachKategorie(this.produkte, kategorie),
							kategorie.getUnterkategorien()));
				});
				temp.setStyle(" -fx-font-weight: bold; -fx-focus-color: transparent; -fx-font-size: 15");

			}

			menuButton.setOnMouseEntered(e -> {
				menuButton.setStyle(" -fx-cursor: hand;" + css);
			});

			menuButton.setOnMouseExited(e -> {
				menuButton.setStyle(" -fx-cursor: default;" + css);
			});
		}

		return menuButton;
	}

	/**
	 * Erzeugt eine HBox mit der Suchleiste und dem Suchbutton
	 * 
	 * @return Hbox mit Suchleiste und Suchbutton
	 */
	public HBox setSearchBar() {
		if (hbox == null) {
			hbox = new HBox();

			textFeldSuche = new TextField();

			textFeldSuche.setMinHeight(40);
			textFeldSuche.setMinWidth(250);

			textFeldSuche.setPromptText("Suche...");
			textFeldSuche.setStyle("-fx-border-color: #C14343; -fx-border: gone; -fx-focus-color: white");
			textFeldSuche.setAlignment(Pos.CENTER_LEFT);

			hbox.getChildren().add(textFeldSuche);
			hbox.getChildren().add(setButtonSuche());

			hbox.setSpacing(10);
		}

		return hbox;
	}

	/**
	 * Erzeugt Scrollpane mit Uebersicht ueber alle Produkte des uebergebenen
	 * HashSets
	 * 
	 * @return Gibt fertig konfigurierte ScrollPane zurueck.
	 */
	public ScrollPane setScrollPane() {
		if (scrollpane == null) {

			scrollpane = new ScrollPane();
			scrollpane.setContent(setGridPane());
			scrollpane.setStyle(
					"-fx-border-color: white; -fx-border: none; -fx-focus-color: white; -fx-background-color: white");
			scrollpane.setPadding(new Insets(50, 50, 50, 50));
		}

		scrollpane.setFitToWidth(true);
		scrollpane.setFitToHeight(true);

		return scrollpane;
	}

	/**
	 * Erzeugt ein GridPane mit Panels fuer alle Produkte.
	 * 
	 * @return GridPane
	 */
	public GridPane setGridPane() {
		if (gridpane == null) {
			gridpane = new GridPane();

			gridpane.setHgap(50);
			gridpane.setVgap(50);

			int a = 0;
			int b = 0;

			for (Produkt produkt : produkte) {

				// Produkt prod = iterator.next();
				gridpane.add(setProduktPanel(produkt), b, a);
				b++;
				if (b % 6 == 0) {
					a++;
					b = b % 6;
				}
			}

			gridpane.setStyle(" -fx-background-color: white");
		}
		return gridpane;
	}

	/**
	 * Erzeugt eine VBox die ein Produkt repraesentiert welches dann in den
	 * Warenkorb hinzugefuegt werden kann.
	 * 
	 * @param p Darzustellendes Produkt
	 * @return VBox das ein Produkt repraesentiert
	 */
	public VBox setProduktPanel(Produkt p) {
		VBox produktPanel = new VBox();

		Rectangle rect = new Rectangle();

		rect.setFill(Color.LIGHTGRAY);
		rect.setWidth(130);
		rect.setHeight(105);

		ImageView produktBild = new ImageView(p.loadBild());
		produktBild.setPreserveRatio(true);
		produktBild.setFitHeight(105);

		Label produktName = new Label(p.getName());
		produktName.setMinWidth(110);
		Label produktPreis = new Label("Preis: " + p.getVerkaufspreis() + "€");
		produktPreis.setMinWidth(110);

		produktName.setStyle("-fx-font-weight: bold");

		produktPanel.setAlignment(Pos.CENTER);
		produktPanel.getChildren().add(produktBild);
		produktPanel.getChildren().add(produktName);
		produktPanel.getChildren().add(produktPreis);
		produktPanel.getChildren().add(setHBox(p));

		produktPanel.setPadding(new Insets(10));
		produktPanel.setMinHeight(192);
		produktPanel.setMinWidth(150);
		VBox.setMargin(produktName, new Insets(2, 0, 0, 0));

		produktPanel.setStyle(STANDARD_PRODUKT_PANEL);

		// Erstellung von DropShadows fuer Komponenten
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(3.0);
		dropShadow.setOffsetX(3.0);
		dropShadow.setOffsetY(3.0);
		dropShadow.setColor(Color.color(0.4, 0.5, 0.5));

		produktPanel.setEffect(dropShadow);

		// Animation fuer Komponenten
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(produktPanel);
		translate.setDuration(Duration.millis(1500));
		translate.setFromY(10);
		translate.setByY(-10);
		translate.play();

		return produktPanel;
	}

	/**
	 * HBox die einen Button und eine Choicebox erzeugt mit welcher der Kunde das
	 * Produkt zum Warenkorb hinzufuegen kann.
	 * 
	 * @param p Darzustellendes Produkt
	 * @return HBox mit einer Auswahl fuer die Anzahl und einem Button zum
	 *         Hinzufuegen jener des Produkts zum Warenkorb
	 */
	public HBox setHBox(Produkt p) {
		HBox hbox = new HBox();

		ComboBox<Integer> combobox = new ComboBox<Integer>();
		Button addProdukt = new Button("Add");
		int n = kundensteuerung.getProduktBestand(p);
		int i = 0;

		while (n >= i) {
			combobox.getItems().add(i);
			i++;
		}

		addProdukt.getStyleClass().add("kunde-add-button");
		combobox.getStyleClass().add("kunde-combobox");

		combobox.setValue(0);

		addProdukt.setOnAction(e -> {
			if (combobox.getValue() > 0) {
				kundensteuerung.produktZuWarenkorbHinzufuegen(p, combobox.getSelectionModel().getSelectedItem());
				combobox.setPromptText("0");
			}
			// TODO Else-Fall: Fehlermeldung an Kunden ausgeben!
		});

		hbox.getChildren().add(combobox);
		hbox.getChildren().add(addProdukt);

		hbox.setSpacing(20);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(5, 5, 0, 5));

		return hbox;
	}

	/**
	 * Erzeugt den Suchbutton.
	 * 
	 * @return Gibt Suchbutton zurueck
	 */
	public Button setButtonSuche() {
		if (btSuche == null) {
			btSuche = new Button();

			ImageView view = new ImageView(getClass().getResource("lupe.png").toExternalForm());
			view.setFitWidth(30);
			view.setFitHeight(30);
			btSuche.setGraphic(view);

			btSuche.setMinWidth(40);
			btSuche.setMinHeight(40);

			String css = "-fx-background-color: #bcbcbc; -fx-font-weight: bold; -fx-border: none";
			btSuche.setStyle(css);
			btSuche.setAlignment(Pos.CENTER);

			btSuche.setOnAction(e -> {
				if (textFeldSuche.getText() != null && !(textFeldSuche.getText().isBlank())) {
					HashSet<Produkt> produkte = new HashSet<Produkt>();
					HashSet<String> produktnamen = new HashSet<String>();
					for (Produkt produkt : kundensteuerung.suchen(textFeldSuche.getText())) {
						if (!(produktnamen.contains(produkt.getName()))) {
							produktnamen.add(produkt.getName());
							produkte.add(produkt);
						}
					}
					primaryStage.setScene(new StartseiteKunde(primaryStage, getWidth(), getHeight(), kundensteuerung,
							produkte, null));
				}
			});

			btSuche.setOnMouseEntered(e -> {
				btSuche.setStyle(" -fx-cursor: hand;" + css);
			});

			btSuche.setOnMouseExited(e -> {
				btSuche.setStyle(" -fx-cursor: default;" + css);
			});
		}
		return btSuche;
	}
}

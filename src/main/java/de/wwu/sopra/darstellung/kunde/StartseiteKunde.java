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
			searchBarBP.getStyleClass().add("kunde-startseite-searchbar-wrapper");

			searchBarBP.setLeft(setMenuButton());
			searchBarBP.setRight(setSearchBar());
		}

		return searchBarBP;
	}

	/**
	 * Konfiguriert den MenuButton mit den Kategorien
	 * 
	 * @return MenuButton zur Auswahl von Kategorien
	 */
	public MenuButton setMenuButton() {
		if (menuButton == null) {
			menuButton = new MenuButton("Kategorien");
			menuButton.getStyleClass().add("kunde-startseite-kategorien-menubutton");

			Iterator<Kategorie> iterator = this.kategorien.iterator();
			while (iterator.hasNext()) {
				Kategorie kategorie = iterator.next();
				MenuItem temp = new MenuItem(kategorie.getName());

				menuButton.getItems().add(temp);

				temp.setOnAction(e -> {
					primaryStage.setScene(new StartseiteKunde(primaryStage, getWidth(), getHeight(), kundensteuerung,
							kundensteuerung.filterProdukteNachKategorie(produkte, kategorie),
							kategorie.getUnterkategorien()));
				});
				temp.getStyleClass().add("kunde-startseite-kategorien-menuitem");

			}
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
			textFeldSuche.getStyleClass().add("kunde-startseite-searchbar-textfield");
			textFeldSuche.setPromptText("Suche...");

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
			scrollpane.getStyleClass().add("kunde-startseite-produkte-scrollpane");
			scrollpane.setContent(setGridPane());
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
			gridpane.getStyleClass().add("kunde-startseite-produkte-gridpane");

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
		produktPanel.getStyleClass().add("kunde-startseite-produkt-panel");

		Rectangle rect = new Rectangle();

		rect.setFill(Color.LIGHTGRAY);
		rect.setWidth(130);
		rect.setHeight(105);

		ImageView produktBild = new ImageView(p.loadBild());
		produktBild.setPreserveRatio(true);
		produktBild.setFitHeight(105);

		Label produktName = new Label(p.getName());
		produktName.getStyleClass().add("kunde-startseite-produkt-panel-label");
		produktName.getStyleClass().add("kunde-startseite-produkt-panel-label-bold");
		Label produktPreis = new Label("Preis: " + p.getVerkaufspreis() + "€");
		produktPreis.getStyleClass().add("kunde-startseite-produkt-panel-label");

		produktPanel.getChildren().add(produktBild);
		produktPanel.getChildren().add(produktName);
		produktPanel.getChildren().add(produktPreis);
		produktPanel.getChildren().add(setHBox(p));

		VBox.setMargin(produktName, new Insets(2, 0, 0, 0));

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
			btSuche.getStyleClass().add("kunde-startseite-search-button");

			ImageView view = new ImageView(getClass().getResource("lupe.png").toExternalForm());
			view.setFitWidth(30);
			view.setFitHeight(30);
			btSuche.setGraphic(view);

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
		}
		return btSuche;
	}
}

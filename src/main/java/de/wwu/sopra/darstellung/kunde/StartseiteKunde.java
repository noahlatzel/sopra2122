package de.wwu.sopra.darstellung.kunde;

import java.util.HashSet;
import java.util.Iterator;

import de.wwu.sopra.anwendung.kunde.Kundensteuerung;
import de.wwu.sopra.datenhaltung.management.Kategorie;
import de.wwu.sopra.datenhaltung.management.Produkt;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
	HashSet<Produkt> produkte;

	private static final String STANDARD_PRODUKT_PANEL = "-fx-border-color: grey; -fx-background-color: white; -fx-background-insets: 0, 2;";

	/**
	 * Konstruktor fuer die Startseite des Kunden
	 * 
	 * @param primaryStage    PrimaryStage
	 * @param width           Breite des Fensters
	 * @param height          Hoehe des Fensters
	 * @param kundensteuerung KundenSteuerung
	 */
	public StartseiteKunde(Stage primaryStage, double width, double height, Kundensteuerung kundensteuerung,
			HashSet<Produkt> produkte) {
		super(primaryStage, width, height, kundensteuerung);
		this.primaryStage = primaryStage;
		this.setRoot(root);
		this.kundensteuerung = kundensteuerung;
		this.produkte = produkte;
		root.setCenter(setBorderPane(produkte));
	}

	/**
	 * Erzeugt aeussere BorderPane, in deren Center die Produkte angezeigt werden
	 * und deren Top die Searchbar ist
	 * 
	 * @return Borderpane fuer den Inhalt der Szene
	 */
	public BorderPane setBorderPane(HashSet<Produkt> produkte) {
		if (borderpane == null) {
			borderpane = new BorderPane();
			kundensteuerung.getKategorien();
			borderpane.setTop(setSearchBarBP());
			borderpane.setCenter(setScrollPane(produkte));
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

			searchBarBP.setLeft(setMenuButton());
			searchBarBP.setRight(setSearchBar());
			searchBarBP.setPadding(new Insets(10));
		}

		return searchBarBP;
	}

	public MenuButton setMenuButton() {
		if (menuButton == null) {
			menuButton = new MenuButton("Kategorien");

			menuButton.setMinHeight(30);
			menuButton.setStyle(
					"-fx-background-color: #FF6868; -fx-font-weight: bold; -fx-mark-color: #FF6868; -fx-font-size: 18; -fx-focus-color: #FF6868; -fx-border-color: #FF6868");

			Iterator<Kategorie> iterator = kundensteuerung.getKategorien().iterator();
			while (iterator.hasNext()) {
				Kategorie kategorie = iterator.next();
				MenuItem temp = new MenuItem(kategorie.getName());

				menuButton.getItems().add(temp);

				temp.setOnAction(e -> {
					primaryStage.setScene(new StartseiteKunde(primaryStage, getWidth(), getHeight(), kundensteuerung,
							kundensteuerung.filterProdukteNachKategorie(produkte, kategorie)));
				});
				temp.setStyle(
						"-fx-background-color: white; -fx-font-weight: bold; -fx-focus-color: transparent; -fx-font-size: 15");
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

			textFeldSuche.setMinHeight(40);
			textFeldSuche.setPromptText("Suche...");
			textFeldSuche.setStyle("-fx-border-color: #C14343; -fx-border: gone; -fx-focus-color: white");
			textFeldSuche.setAlignment(Pos.CENTER);

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
	 * @param produkte Alle in dem Set enthaltenen Produkte werden angezeigt.
	 * @return Gibt fertig konfigurierte ScrollPane zurueck.
	 */
	public ScrollPane setScrollPane(HashSet<Produkt> produkte) {
		if (scrollpane == null) {

			scrollpane = new ScrollPane();
			scrollpane.setContent(setGridPane(produkte));
			scrollpane.setStyle("-fx-border-color: white; -fx-border: gone; -fx-focus-color: white");
			scrollpane.setPadding(new Insets(50));
		}

		return scrollpane;
	}

	/**
	 * Erzeugt ein GridPane mit Panels fuer alle Produkte.
	 * 
	 * @param produkte HashSet mit Produkten die dargestellt werden sollen.
	 * @return
	 */
	public GridPane setGridPane(HashSet<Produkt> produkte) {
		if (gridpane == null) {
			gridpane = new GridPane();

			gridpane.setHgap(50);
			gridpane.setVgap(50);

			Iterator<Produkt> iterator = produkte.iterator();

			int a = 0;
			int b = 0;

			while (iterator.hasNext()) {

				Produkt prod = iterator.next();
				gridpane.add(setProduktPanel(prod), b, a);
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

		Rectangle rect = new Rectangle();

		rect.setFill(Color.LIGHTGRAY);
		rect.setWidth(130);
		rect.setHeight(105);

		Label produktName = new Label(p.getName());
		Label produktPreis = new Label("Preis: " + p.getVerkaufspreis() + "€");

		produktName.setStyle("-fx-font-weight: bold");

		produktPanel.getChildren().add(rect);
		produktPanel.getChildren().add(produktName);
		produktPanel.getChildren().add(produktPreis);
		produktPanel.getChildren().add(setHBox(p));

		produktPanel.setPadding(new Insets(10));
		produktPanel.setMinHeight(180);
		produktPanel.setMinWidth(100);

		produktPanel.setStyle(STANDARD_PRODUKT_PANEL);

		// Erstellung von DropShadows fuer Komponenten
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(3.0);
		dropShadow.setOffsetX(3.0);
		dropShadow.setOffsetY(3.0);
		dropShadow.setColor(Color.color(0.4, 0.5, 0.5));

		produktPanel.setEffect(dropShadow);

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

		ChoiceBox<Integer> choicebox = new ChoiceBox<Integer>();
		Button addProdukt = new Button("Add");
		int n = kundensteuerung.getProduktBestand(p);
		int i = 0;

		while (n >= i) {
			choicebox.getItems().add(i);
			i++;
		}

		choicebox.setValue(0);

		addProdukt.setOnAction(e -> {
			if (choicebox.getValue() > 0) {
				kundensteuerung.produktZuWarenkorbHinzufuegen(p, choicebox.getValue());
			}
			// TODO Else-Fall: Fehlermeldung an Kunden ausgeben!
		});

		hbox.getChildren().add(choicebox);
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

			//ImageView view = new ImageView(getClass().getResource("lupe.png").toExternalForm());
			//view.setFitWidth(30);
			//view.setFitHeight(30);
			//btSuche.setGraphic(view);

			btSuche.setMinWidth(40);
			btSuche.setMinHeight(40);

			btSuche.setStyle("-fx-background-color: #818083; -fx-font-weight: bold; -fx-border: none");
			btSuche.setAlignment(Pos.CENTER);

			btSuche.setOnAction(e -> {
				HashSet<Produkt> produkte = new HashSet<Produkt>();
				HashSet<String> produktnamen = new HashSet<String>();
				for (Produkt produkt : kundensteuerung.suchen(textFeldSuche.getText())) {
					if (!(produktnamen.contains(produkt.getName()))) {
						produktnamen.add(produkt.getName());
						produkte.add(produkt);
					}
				}
				primaryStage.setScene(
						new StartseiteKunde(primaryStage, getWidth(), getHeight(), kundensteuerung, produkte));
			});
		}
		return btSuche;
	}
}

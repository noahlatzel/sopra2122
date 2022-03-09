package de.wwu.sopra.darstellung.fahrer;

import de.wwu.sopra.anwendung.mitarbeiter.Fahrersteuerung;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Eine alternative GUI Klasse fuer den Fahrer. Die verschiedenen
 * Funktionalitaeten werden moeglichst in einem GUI zusammengefasst.
 * 
 * @author Noah Latzel
 *
 */
public class FahrerInZustellung extends OverviewFahrer {
	BorderPane contentWrapper;
	GridPane gridPane;
	TilePane tilePane;
	ScrollPane scrollPane;
	Integer index;
	VBox aktuelleBestellung;
	VBox naechsteBestellung;
	Bestellung zweiteBestellung;
	VBox uebernaechsteBestellung;

	public FahrerInZustellung(Fahrersteuerung steuerung, Stage primaryStage, double width, double height) {
		super(steuerung, primaryStage, width, height);
		index = 2;
		if (steuerung.getFahrer().getFahrzeug() == null) {
			root.setCenter(this.setContentWrapper(false));
		} else {
			root.setCenter(this.setContentWrapper(true));
		}
	}

	/**
	 * Erzeugt ContentWrapper
	 * 
	 * @return ContentWrapper
	 */
	private BorderPane setContentWrapper(boolean fahrzeugVorhanden) {
		// ContentWrapper, um den Titel einzuschliessen
		if (this.contentWrapper == null) {
			if (fahrzeugVorhanden) {
				contentWrapper = new BorderPane();
				contentWrapper.setPadding(new Insets(10, 30, 10, 30));
				Label title = new Label("In Zustellung");
				title.getStyleClass().add("mitarbeiter-content-title");
				contentWrapper.setTop(title);
				contentWrapper.setCenter(setContent());
			} else {
				contentWrapper = new BorderPane();
				contentWrapper.setPadding(new Insets(10, 30, 10, 30));
				Label title = new Label("Waehle ein Fahrzeug!");
				title.getStyleClass().add("mitarbeiter-content-title");
				contentWrapper.setTop(title);
				contentWrapper.setCenter(setScrollPane());
			}

		}

		return this.contentWrapper;
	}

	/**
	 * Erzeugt ein GridPane
	 * 
	 * @return GridPane
	 */
	private GridPane setContent() {
		if (gridPane == null) {
			gridPane = new GridPane();
			Bestellung ersteBestellung = steuerung.routeAusgeben().getBestellungen().get(0);
			zweiteBestellung = null;
			aktuelleBestellung = setBestellungPanel(ersteBestellung, true);
			naechsteBestellung = null;
			if (1 < steuerung.routeAusgeben().getBestellungen().size()) {
				zweiteBestellung = steuerung.routeAusgeben().getBestellungen().get(1);
				naechsteBestellung = setBestellungPanel(zweiteBestellung, false);
				gridPane.add(naechsteBestellung, 1, 0);
			}

			uebernaechsteBestellung = null;
			gridPane.add(aktuelleBestellung, 0, 0);

			Button btBestellungAbgeben = new Button("Bestellung abgeben");
			Button btKundeNichtDa = new Button("Kunde ist nicht da");
			Button btLieferungAbschliessen = new Button("Zustellung beenden");

			btBestellungAbgeben.getStyleClass().add("inhaber-form-button");
			btKundeNichtDa.getStyleClass().add("inhaber-form-button");
			btLieferungAbschliessen.getStyleClass().add("inhaber-form-button");
			btLieferungAbschliessen.setDisable(true);
			btLieferungAbschliessen.setOnAction(a -> {
				steuerung.routeAbschliesen();
				primaryStage.setScene(new FahrerInZustellung(steuerung, primaryStage, getWidth(), getHeight()));
			});
			gridPane.add(btBestellungAbgeben, 0, 1);
			gridPane.add(btKundeNichtDa, 0, 2);
			gridPane.add(btLieferungAbschliessen, 0, 3);

			gridPane.setVgap(20);
			btKundeNichtDa.setOnAction(a -> {
				// Animation
				if (index < steuerung.routeAusgeben().getBestellungen().size()) {
					Bestellung naechste = steuerung.routeAusgeben().getBestellungen().get(index);
					zweiteBestellung = steuerung.routeAusgeben().getBestellungen().get(index - 1);
					gridPane.getChildren().remove(aktuelleBestellung);
					gridPane.getChildren().remove(naechsteBestellung);
					gridPane.getChildren().remove(uebernaechsteBestellung);
					aktuelleBestellung = setBestellungPanel(zweiteBestellung, true);
					naechsteBestellung = removeBestellungPanel(ersteBestellung, false);
					uebernaechsteBestellung = setBestellungPanel(naechste, false);
					gridPane.add(aktuelleBestellung, 0, 0);
					gridPane.add(naechsteBestellung, 0, 0);
					gridPane.add(uebernaechsteBestellung, 1, 0);
					index++;
				} else if (index <= steuerung.routeAusgeben().getBestellungen().size()) {
					zweiteBestellung = steuerung.routeAusgeben().getBestellungen().get(index - 1);
					gridPane.getChildren().remove(aktuelleBestellung);
					gridPane.getChildren().remove(naechsteBestellung);
					gridPane.getChildren().remove(uebernaechsteBestellung);
					aktuelleBestellung = setBestellungPanel(zweiteBestellung, true);
					naechsteBestellung = removeBestellungPanel(ersteBestellung, false);
					gridPane.add(aktuelleBestellung, 0, 0);
					gridPane.add(naechsteBestellung, 0, 0);
					index++;
				} else if (index - 1 <= steuerung.routeAusgeben().getBestellungen().size()) {
					gridPane.getChildren().remove(aktuelleBestellung);
					gridPane.getChildren().remove(naechsteBestellung);
					naechsteBestellung = removeBestellungPanel(ersteBestellung, false);
					gridPane.add(naechsteBestellung, 0, 0);
					index++;
					btBestellungAbgeben.setDisable(true);
					btKundeNichtDa.setDisable(true);
					btLieferungAbschliessen.setDisable(false);
				}
				steuerung.kundeNichtDa();
			});

			btBestellungAbgeben.setOnAction(a -> {
				// Animation
				if (index < steuerung.routeAusgeben().getBestellungen().size()) {
					Bestellung naechste = steuerung.routeAusgeben().getBestellungen().get(index);
					zweiteBestellung = steuerung.routeAusgeben().getBestellungen().get(index - 1);
					gridPane.getChildren().remove(aktuelleBestellung);
					gridPane.getChildren().remove(naechsteBestellung);
					gridPane.getChildren().remove(uebernaechsteBestellung);
					aktuelleBestellung = setBestellungPanel(zweiteBestellung, true);
					naechsteBestellung = removeBestellungPanel(ersteBestellung, true);
					uebernaechsteBestellung = setBestellungPanel(naechste, false);
					gridPane.add(aktuelleBestellung, 0, 0);
					gridPane.add(naechsteBestellung, 0, 0);
					gridPane.add(uebernaechsteBestellung, 1, 0);
					index++;
				} else if (index <= steuerung.routeAusgeben().getBestellungen().size()) {
					zweiteBestellung = steuerung.routeAusgeben().getBestellungen().get(index - 1);
					gridPane.getChildren().remove(aktuelleBestellung);
					gridPane.getChildren().remove(naechsteBestellung);
					gridPane.getChildren().remove(uebernaechsteBestellung);
					aktuelleBestellung = setBestellungPanel(zweiteBestellung, true);
					naechsteBestellung = removeBestellungPanel(ersteBestellung, true);
					gridPane.add(aktuelleBestellung, 0, 0);
					gridPane.add(naechsteBestellung, 0, 0);
					index++;
				} else if (index - 1 <= steuerung.routeAusgeben().getBestellungen().size()) {
					gridPane.getChildren().remove(aktuelleBestellung);
					gridPane.getChildren().remove(naechsteBestellung);
					naechsteBestellung = removeBestellungPanel(ersteBestellung, true);
					gridPane.add(naechsteBestellung, 0, 0);
					index++;
					btBestellungAbgeben.setDisable(true);
					btKundeNichtDa.setDisable(true);
					btLieferungAbschliessen.setDisable(false);
				}
				steuerung.bestellungAusliefern();
			});

			gridPane.setHgap(300);
		}
		return gridPane;
	}

	/**
	 * Erzeugt ein GridPane
	 * 
	 * @return GridPane
	 */
	private TilePane setContentAlt() {
		if (tilePane == null) {
			tilePane = new TilePane();
			tilePane.setPadding(new Insets(20));
			tilePane.setHgap(10);
			tilePane.setVgap(10);
			tilePane.setPrefColumns(4);
			for (Fahrzeug fahrzeug : steuerung.getBelegteFahrzeuge()) {
				tilePane.getChildren().add(setFahrzeug(fahrzeug));
			}
		}
		return tilePane;
	}

	private ScrollPane setScrollPane() {
		if (scrollPane == null) {
			scrollPane = new ScrollPane();
			scrollPane.setContent(setContentAlt());
		}
		return scrollPane;
	}

	private VBox setBestellungPanel(Bestellung aktuell, boolean first) {
		VBox bestellungPanel = new VBox();

		ImageView lieferungPic = new ImageView();
		lieferungPic.setImage(new Image(getClass().getResource("lieferung.jpg").toExternalForm()));
		lieferungPic.setPreserveRatio(true);
		lieferungPic.setFitHeight(200);

		Label kunde = new Label("Name:	" + aktuell.getKunde().getName());
		kunde.setMinWidth(200);
		kunde.getStyleClass().add("anmeldung-registrierung-label");
		Label adresse = new Label("Adresse:	" + aktuell.getAdresse());
		adresse.setMinWidth(200);
		adresse.getStyleClass().add("anmeldung-registrierung-label");

		adresse.setStyle("-fx-font-weight: bold");

		bestellungPanel.setAlignment(Pos.CENTER);
		bestellungPanel.getChildren().add(lieferungPic);
		bestellungPanel.getChildren().add(kunde);
		bestellungPanel.getChildren().add(adresse);
		bestellungPanel.setSpacing(40);

		bestellungPanel.setPadding(new Insets(10));
		bestellungPanel.setMinHeight(180);
		bestellungPanel.setMinWidth(100);
		VBox.setMargin(kunde, new Insets(2, 0, 0, 0));

		bestellungPanel.getStyleClass().add("fahrer-fahrzeug-background");

		// Animation fuer Komponenten
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(bestellungPanel);
		translate.setDuration(Duration.millis(1500));
		translate.setFromX(500);
		translate.setByX(-500);
		translate.play();

		if (!first) {
			bestellungPanel.setOpacity(0.5);
		}
		return bestellungPanel;
	}

	private VBox removeBestellungPanel(Bestellung aktuell, boolean kundeDa) {
		VBox bestellungPanel = new VBox();

		ImageView lieferungPic = new ImageView();
		lieferungPic.setImage(new Image(getClass().getResource("lieferung.jpg").toExternalForm()));
		lieferungPic.setPreserveRatio(true);
		lieferungPic.setFitHeight(200);

		Label kunde = new Label("Name:	" + aktuell.getKunde().getName());
		kunde.setMinWidth(200);
		kunde.getStyleClass().add("anmeldung-registrierung-label");
		Label adresse = new Label("Adresse:	" + aktuell.getAdresse());
		adresse.setMinWidth(200);
		adresse.getStyleClass().add("anmeldung-registrierung-label");

		adresse.setStyle("-fx-font-weight: bold");

		bestellungPanel.setAlignment(Pos.CENTER);
		bestellungPanel.getChildren().add(lieferungPic);
		bestellungPanel.getChildren().add(kunde);
		bestellungPanel.getChildren().add(adresse);
		bestellungPanel.setSpacing(40);

		bestellungPanel.setPadding(new Insets(10));
		bestellungPanel.setMinHeight(180);
		bestellungPanel.setMinWidth(100);
		VBox.setMargin(kunde, new Insets(2, 0, 0, 0));

		bestellungPanel.getStyleClass().add("fahrer-fahrzeug-background");

		// Animation fuer Komponenten
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(bestellungPanel);
		translate.setDuration(Duration.millis(1500));

		if (!kundeDa) {
			translate.setByY(-800);
		} else {
			translate.setByY(800);
		}
		translate.play();
		return bestellungPanel;
	}

	private VBox setFahrzeug(Fahrzeug fahrzeug) {
		VBox bestellungPanel = new VBox();

		ImageView lieferungPic = new ImageView();
		lieferungPic.setImage(new Image(getClass().getResource("car.png").toExternalForm()));
		lieferungPic.setPreserveRatio(true);
		lieferungPic.setFitWidth(180);

		Label fahrzeugNummer = new Label("Fahrzeugnummer: " + fahrzeug.getFahrzeugNummer());
		fahrzeugNummer.setMinWidth(180);
		fahrzeugNummer.getStyleClass().add("anmeldung-registrierung-label");
		Label lieferungen = new Label("Lieferungen:		" + fahrzeug.getRoute().getBestellungen().size());
		lieferungen.setMinWidth(180);
		lieferungen.getStyleClass().add("anmeldung-registrierung-label");

		lieferungen.setStyle("-fx-font-weight: bold");

		Button auswaehlen = new Button("Waehlen");
		auswaehlen.getStyleClass().add("inhaber-form-button");
		auswaehlen.setOnAction(a -> {
			steuerung.fahrzeugZuordnen(fahrzeug);
			primaryStage.setScene(new FahrerInZustellung(steuerung, primaryStage, getWidth(), getHeight()));
		});

		bestellungPanel.setAlignment(Pos.CENTER);
		bestellungPanel.getChildren().add(lieferungPic);
		bestellungPanel.getChildren().add(fahrzeugNummer);
		bestellungPanel.getChildren().add(lieferungen);
		bestellungPanel.getChildren().add(auswaehlen);
		bestellungPanel.setSpacing(40);

		bestellungPanel.getStyleClass().add("fahrer-fahrzeug-background");

		bestellungPanel.setPadding(new Insets(10));
		bestellungPanel.setMinHeight(180);
		bestellungPanel.setMinWidth(100);

		return bestellungPanel;
	}

}

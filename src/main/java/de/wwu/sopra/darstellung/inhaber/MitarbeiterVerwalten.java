/**
 * 
 */
package de.wwu.sopra.darstellung.inhaber;

import de.wwu.sopra.anwendung.mitarbeiter.Inhabersteuerung;
import de.wwu.sopra.datenhaltung.benutzer.Benutzer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author Valeria Vassallo
 *
 */
public class MitarbeiterVerwalten extends InhaberOverview {
	// Erstellung von Variablen
	BorderPane contentWrapper;
	GridPane gridPane;
	ScrollPane spMitarbeitern;
	TableView<Benutzer> tableViewMitarbeitern;
	ObservableList<Benutzer> mitarbeitern;
	Button loeschenButton;

	/**
	 * Zeigt das Fenster zur Verwaltung von Mitarbeitern
	 * 
	 * @param primaryStage     PrimaryStage
	 * @param width            Breite des Fensters
	 * @param height           Hoehe des Fensters
	 * @param inhaberSteuerung InhaberSteuerung
	 */
	public MitarbeiterVerwalten(Stage primaryStage, double width, double height, Inhabersteuerung inhaberSteuerung) {
		super(primaryStage, width, height, inhaberSteuerung);
		root.setCenter(this.setContentWrapper());
	}

	/**
	 * Erzeugt ContentWrapper
	 * 
	 * @return ContentWrapper
	 */
	private BorderPane setContentWrapper() {
		// ContentWrapper, um den Titel einzuschliessen
		if (this.contentWrapper == null) {
			contentWrapper = new BorderPane();
			contentWrapper.setPadding(new Insets(10, 30, 10, 30));
			Label title = new Label("Mitarbeiter Verwalten");
			title.getStyleClass().add("mitarbeiter-content-title");
			contentWrapper.setTop(title);
			contentWrapper.setCenter(setContent());
		}

		return this.contentWrapper;
	}

	/**
	 * Setzt Inhalt
	 * 
	 * @return GridPane mit Inhalt
	 */
	private GridPane setContent() {
		// GridPane als Main Content Wrapper
		if (this.gridPane == null) {
			gridPane = new GridPane();
			gridPane.add(this.setMitarbeiternTableView(), 0, 0);
			gridPane.add(this.setLoeschenButton(), 0, 1);

			gridPane.getStyleClass().add("inhaber-mitarbeiter-tableview-wrapper");
		}

		return this.gridPane;
	}

	/**
	 * Erzeugt TableView fuer Mitarbeiter
	 * 
	 * @return TableView mit Mitarbeitern
	 */
	private ScrollPane setMitarbeiternTableView() {
		// Erstellung von TableView, um eine bessere Overview von allen Mitarbeitern zu
		// haben
		if (this.spMitarbeitern == null) {
			mitarbeitern = FXCollections.observableArrayList();
			spMitarbeitern = new ScrollPane();

			// Get alle Mitarbeitern
			for (Benutzer mitarbeiter : inhaberSteuerung.mitarbeiternAnzeigen()) {
				mitarbeitern.add(mitarbeiter);
			}

			tableViewMitarbeitern = new TableView<Benutzer>();
			tableViewMitarbeitern.getStyleClass().add("inhaber-mitarbeitern-tableview");
			// Tabelle beartbeitbar zu machen
			tableViewMitarbeitern.setEditable(true);

			// Spalten zur Tabelle hinzufuegen
			TableColumn<Benutzer, String> benutzerBenutzernameSpalte = new TableColumn<>("Benutzername");
			benutzerBenutzernameSpalte.setCellValueFactory(new PropertyValueFactory<>("benutzername"));
			// Table Cell bearbeitbar zu machen (Benuztername-Aenderung)
			benutzerBenutzernameSpalte.setCellFactory(TextFieldTableCell.forTableColumn());
			benutzerBenutzernameSpalte.setOnEditCommit(new EventHandler<CellEditEvent<Benutzer, String>>() {
				@Override
				public void handle(CellEditEvent<Benutzer, String> t) {
					Benutzer mitarbeiter = ((Benutzer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
					if (t.getNewValue().isBlank()) {
						return;
					}
					String neuerBenutzername = t.getNewValue();
					inhaberSteuerung.mitarbeiterDatenAendern(mitarbeiter, neuerBenutzername, mitarbeiter.getPasswort(),
							mitarbeiter.getEmail(), mitarbeiter.getAdresse(), mitarbeiter.getVorname(),
							mitarbeiter.getName(), mitarbeiter.getBankverbindung());
				}
			});

			TableColumn<Benutzer, String> benutzerVornameSpalte = new TableColumn<>("Vorname");
			benutzerVornameSpalte.setCellValueFactory(new PropertyValueFactory<>("vorname"));
			// Table Cell bearbeitbar zu machen (Vorname-Aenderung)
			benutzerVornameSpalte.setCellFactory(TextFieldTableCell.forTableColumn());
			benutzerVornameSpalte.setOnEditCommit(new EventHandler<CellEditEvent<Benutzer, String>>() {
				@Override
				public void handle(CellEditEvent<Benutzer, String> t) {
					Benutzer mitarbeiter = ((Benutzer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
					if (t.getNewValue().isBlank()) {
						return;
					}
					String neuerVorname = t.getNewValue();
					inhaberSteuerung.mitarbeiterDatenAendern(mitarbeiter, mitarbeiter.getBenutzername(),
							mitarbeiter.getPasswort(), mitarbeiter.getEmail(), mitarbeiter.getAdresse(), neuerVorname,
							mitarbeiter.getName(), mitarbeiter.getBankverbindung());
				}
			});

			TableColumn<Benutzer, String> benutzerNameSpalte = new TableColumn<>("Name");
			benutzerNameSpalte.setCellValueFactory(new PropertyValueFactory<>("name"));
			// Table Cell bearbeitbar zu machen (Nachname-Aenderung)
			benutzerNameSpalte.setCellFactory(TextFieldTableCell.forTableColumn());
			benutzerNameSpalte.setOnEditCommit(new EventHandler<CellEditEvent<Benutzer, String>>() {
				@Override
				public void handle(CellEditEvent<Benutzer, String> t) {
					Benutzer mitarbeiter = ((Benutzer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
					if (t.getNewValue().isBlank()) {
						return;
					}
					String neuerName = t.getNewValue();
					inhaberSteuerung.mitarbeiterDatenAendern(mitarbeiter, mitarbeiter.getBenutzername(),
							mitarbeiter.getPasswort(), mitarbeiter.getEmail(), mitarbeiter.getAdresse(),
							mitarbeiter.getVorname(), neuerName, mitarbeiter.getBankverbindung());
				}
			});

			TableColumn<Benutzer, String> benutzerEmailSpalte = new TableColumn<>("E-Mail");
			benutzerEmailSpalte.setCellValueFactory(new PropertyValueFactory<>("email"));
			// Table Cell bearbeitbar zu machen (EMail-Aenderung)
			benutzerEmailSpalte.setCellFactory(TextFieldTableCell.forTableColumn());
			benutzerEmailSpalte.setOnEditCommit(new EventHandler<CellEditEvent<Benutzer, String>>() {
				@Override
				public void handle(CellEditEvent<Benutzer, String> t) {
					Benutzer mitarbeiter = ((Benutzer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
					if (t.getNewValue().isBlank()) {
						return;
					}
					String neuesEmail = t.getNewValue();
					inhaberSteuerung.mitarbeiterDatenAendern(mitarbeiter, mitarbeiter.getBenutzername(),
							mitarbeiter.getPasswort(), neuesEmail, mitarbeiter.getAdresse(), mitarbeiter.getVorname(),
							mitarbeiter.getName(), mitarbeiter.getBankverbindung());
				}
			});

			TableColumn<Benutzer, String> benutzerAdresseSpalte = new TableColumn<>("Adresse");
			benutzerAdresseSpalte.setCellValueFactory(new PropertyValueFactory<>("adresse"));
			// Table Cell bearbeitbar zu machen (Adresse-Aenderung)
			benutzerAdresseSpalte.setCellFactory(TextFieldTableCell.forTableColumn());
			benutzerAdresseSpalte.setOnEditCommit(new EventHandler<CellEditEvent<Benutzer, String>>() {
				@Override
				public void handle(CellEditEvent<Benutzer, String> t) {
					Benutzer mitarbeiter = ((Benutzer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
					if (t.getNewValue().isBlank()) {
						return;
					}
					String neueAdresse = t.getNewValue();
					inhaberSteuerung.mitarbeiterDatenAendern(mitarbeiter, mitarbeiter.getBenutzername(),
							mitarbeiter.getPasswort(), mitarbeiter.getEmail(), neueAdresse, mitarbeiter.getVorname(),
							mitarbeiter.getName(), mitarbeiter.getBankverbindung());
				}
			});

			TableColumn<Benutzer, String> benutzerBankverbindungSpalte = new TableColumn<>("Bankverbindung");
			benutzerBankverbindungSpalte.setCellValueFactory(new PropertyValueFactory<>("bankverbindung"));
			// Table Cell bearbeitbar zu machen (Bankverbindung-Aenderung)
			benutzerBankverbindungSpalte.setCellFactory(TextFieldTableCell.forTableColumn());
			benutzerBankverbindungSpalte.setOnEditCommit(new EventHandler<CellEditEvent<Benutzer, String>>() {
				@Override
				public void handle(CellEditEvent<Benutzer, String> t) {
					Benutzer mitarbeiter = ((Benutzer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
					if (t.getNewValue().isBlank()) {
						return;
					}
					String neueBankverbindung = t.getNewValue();
					inhaberSteuerung.mitarbeiterDatenAendern(mitarbeiter, mitarbeiter.getBenutzername(),
							mitarbeiter.getPasswort(), mitarbeiter.getEmail(), mitarbeiter.getAdresse(),
							mitarbeiter.getVorname(), mitarbeiter.getName(), neueBankverbindung);
				}
			});

			TableColumn<Benutzer, String> benutzerPasswortSpalte = new TableColumn<>("Passwort");
			benutzerPasswortSpalte.setCellValueFactory(new PropertyValueFactory<>("passwort"));
			// Table Cell bearbeitbar zu machen (Passwort-Aenderung)
			benutzerPasswortSpalte.setCellFactory(TextFieldTableCell.forTableColumn());
			benutzerPasswortSpalte.setOnEditCommit(new EventHandler<CellEditEvent<Benutzer, String>>() {
				@Override
				public void handle(CellEditEvent<Benutzer, String> t) {
					Benutzer mitarbeiter = ((Benutzer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
					if (t.getNewValue().isBlank()) {
						return;
					}
					String neuesPasswort = t.getNewValue();
					inhaberSteuerung.mitarbeiterDatenAendern(mitarbeiter, mitarbeiter.getBenutzername(), neuesPasswort,
							mitarbeiter.getEmail(), mitarbeiter.getAdresse(), mitarbeiter.getVorname(),
							mitarbeiter.getName(), mitarbeiter.getBankverbindung());
				}
			});

			// Rolle sollte nicht bearbeitbar sein
			TableColumn<Benutzer, String> benutzerRolleSpalte = new TableColumn<>("Rolle");
			benutzerRolleSpalte.setCellValueFactory(new PropertyValueFactory<>("rolle"));

			// Spalten zur Tabelle hinzufuegen
			tableViewMitarbeitern.getColumns().add(benutzerRolleSpalte);
			tableViewMitarbeitern.getColumns().add(benutzerBenutzernameSpalte);
			tableViewMitarbeitern.getColumns().add(benutzerVornameSpalte);
			tableViewMitarbeitern.getColumns().add(benutzerNameSpalte);
			tableViewMitarbeitern.getColumns().add(benutzerEmailSpalte);
			tableViewMitarbeitern.getColumns().add(benutzerPasswortSpalte);
			tableViewMitarbeitern.getColumns().add(benutzerAdresseSpalte);
			tableViewMitarbeitern.getColumns().add(benutzerBankverbindungSpalte);
			tableViewMitarbeitern.setItems(mitarbeitern);
			tableViewMitarbeitern.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			spMitarbeitern.setContent(tableViewMitarbeitern);
		}

		return this.spMitarbeitern;
	}

	/**
	 * Erzeugt den Loeschen Button
	 * 
	 * @return Loeschen Button
	 */
	public Button setLoeschenButton() {
		// Erstellung des Loeschen-Buttons
		if (this.loeschenButton == null) {
			loeschenButton = new Button("Benutzer Loeschen");
			loeschenButton.getStyleClass().add("inhaber-form-button");
			loeschenButton.setOnAction(e -> {
				Benutzer mitarbeiterZuLoeschen = tableViewMitarbeitern.getSelectionModel().getSelectedItem();
				inhaberSteuerung.mitarbeiterLoeschen(mitarbeiterZuLoeschen);
				mitarbeitern.remove(mitarbeiterZuLoeschen);
			});
		}
		return this.loeschenButton;
	}
}

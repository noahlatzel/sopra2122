/**
 * 
 */
package de.wwu.sopra.darstellung.inhaber;

import de.wwu.sopra.anwendung.mitarbeiter.Inhabersteuerung;
import de.wwu.sopra.datenhaltung.benutzer.Benutzer;
import javafx.event.EventHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author Valeria Vassallo
 *
 */
public class MitarbeiterVerwalten extends InhaberOverview {
	BorderPane contentWrapper;
	GridPane gridPane;
	ScrollPane spMitarbeitern;
	TableView<Benutzer> tableViewMitarbeitern;
	ObservableList<Benutzer> mitarbeitern;
	Button loeschenButton;
	
	public MitarbeiterVerwalten(Stage primaryStage, double width, double height, Inhabersteuerung inhaberSteuerung) {
		super(primaryStage, width, height, inhaberSteuerung);
		root.setCenter(this.setContentWrapper());
	}
	
	private BorderPane setContentWrapper() {
		if (this.contentWrapper == null) {
			contentWrapper = new BorderPane();
			contentWrapper.setPadding(new Insets(10, 30, 10, 30));
			Label title = new Label("Mitarbeiter Verwalten");
			title.setStyle("-fx-font-weight: bold");
			title.setFont(new Font("Arial", 32));
			contentWrapper.setTop(title);
			contentWrapper.setCenter(setContent());
		}
		
		return this.contentWrapper;
	}
	
	private GridPane setContent() {
		if (this.gridPane == null) {
			gridPane = new GridPane();
			gridPane.add(this.setMitarbeiternTableView(), 0, 0);
			gridPane.add(this.setLoeschenButton(), 0, 1);
		}
		
		return this.gridPane;
	}
	
	private ScrollPane setMitarbeiternTableView() {
		if (this.spMitarbeitern == null) {
			mitarbeitern = FXCollections.observableArrayList();
			spMitarbeitern = new ScrollPane();
			
			for (Benutzer mitarbeiter : inhaberSteuerung.mitarbeiternAnzeigen()) {
				mitarbeitern.add(mitarbeiter);
			}

			tableViewMitarbeitern = new TableView<Benutzer>();
			tableViewMitarbeitern.setEditable(true);
			
			TableColumn<Benutzer, String> benutzerBenutzernameSpalte = new TableColumn<>("Benutzername");
			benutzerBenutzernameSpalte.setCellValueFactory(new PropertyValueFactory<>("benutzername"));
			benutzerBenutzernameSpalte.setCellFactory(TextFieldTableCell.forTableColumn());
			benutzerBenutzernameSpalte.setOnEditCommit(
	            new EventHandler<CellEditEvent<Benutzer, String>>() {
	                @Override
	                public void handle(CellEditEvent<Benutzer, String> t) {
	                	Benutzer mitarbeiter = ((Benutzer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
	                	if (t.getNewValue().isBlank()) {
	                		return;
	                	}
	                	String neuerBenutzername = t.getNewValue();
	                	inhaberSteuerung.mitarbeiterDatenAendern(mitarbeiter, neuerBenutzername, mitarbeiter.getPasswort(), mitarbeiter.getEmail(), mitarbeiter.getAdresse(), mitarbeiter.getVorname(), mitarbeiter.getName(), mitarbeiter.getBankverbindung());
	                }
	            }
	        );
	        
			TableColumn<Benutzer, String> benutzerVornameSpalte = new TableColumn<>("Vorname");
			benutzerVornameSpalte.setCellValueFactory(new PropertyValueFactory<>("vorname"));
			benutzerVornameSpalte.setCellFactory(TextFieldTableCell.forTableColumn());
			benutzerVornameSpalte.setOnEditCommit(
	            new EventHandler<CellEditEvent<Benutzer, String>>() {
	                @Override
	                public void handle(CellEditEvent<Benutzer, String> t) {
	                	Benutzer mitarbeiter = ((Benutzer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
	                	if (t.getNewValue().isBlank()) {
	                		return;
	                	}
	                	String neuerVorname = t.getNewValue();
	                	inhaberSteuerung.mitarbeiterDatenAendern(mitarbeiter, mitarbeiter.getBenutzername(), mitarbeiter.getPasswort(), mitarbeiter.getEmail(), mitarbeiter.getAdresse(), neuerVorname, mitarbeiter.getName(), mitarbeiter.getBankverbindung());
	                }
	            }
	        );
			
			TableColumn<Benutzer, String> benutzerNameSpalte = new TableColumn<>("Name");
			benutzerNameSpalte.setCellValueFactory(new PropertyValueFactory<>("name"));
			benutzerNameSpalte.setCellFactory(TextFieldTableCell.forTableColumn());
			benutzerNameSpalte.setOnEditCommit(
	            new EventHandler<CellEditEvent<Benutzer, String>>() {
	                @Override
	                public void handle(CellEditEvent<Benutzer, String> t) {
	                	Benutzer mitarbeiter = ((Benutzer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
	                	if (t.getNewValue().isBlank()) {
	                		return;
	                	}
	                	String neuerName = t.getNewValue();
	                	inhaberSteuerung.mitarbeiterDatenAendern(mitarbeiter, mitarbeiter.getBenutzername(), mitarbeiter.getPasswort(), mitarbeiter.getEmail(), mitarbeiter.getAdresse(), mitarbeiter.getVorname(), neuerName, mitarbeiter.getBankverbindung());
	                }
	            }
	        );
			
			TableColumn<Benutzer, String> benutzerEmailSpalte = new TableColumn<>("E-Mail");
			benutzerEmailSpalte.setCellValueFactory(new PropertyValueFactory<>("email"));
			benutzerEmailSpalte.setCellFactory(TextFieldTableCell.forTableColumn());
			benutzerEmailSpalte.setOnEditCommit(
	            new EventHandler<CellEditEvent<Benutzer, String>>() {
	                @Override
	                public void handle(CellEditEvent<Benutzer, String> t) {
	                	Benutzer mitarbeiter = ((Benutzer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
	                	if (t.getNewValue().isBlank()) {
	                		return;
	                	}
	                	String neuesEmail = t.getNewValue();
	                	inhaberSteuerung.mitarbeiterDatenAendern(mitarbeiter, mitarbeiter.getBenutzername(), mitarbeiter.getPasswort(), neuesEmail, mitarbeiter.getAdresse(), mitarbeiter.getVorname(), mitarbeiter.getName(), mitarbeiter.getBankverbindung());
	                }
	            }
	        );
			
			TableColumn<Benutzer, String> benutzerAdresseSpalte = new TableColumn<>("Adresse");
			benutzerAdresseSpalte.setCellValueFactory(new PropertyValueFactory<>("adresse"));
			benutzerAdresseSpalte.setCellFactory(TextFieldTableCell.forTableColumn());
			benutzerAdresseSpalte.setOnEditCommit(
	            new EventHandler<CellEditEvent<Benutzer, String>>() {
	                @Override
	                public void handle(CellEditEvent<Benutzer, String> t) {
	                	Benutzer mitarbeiter = ((Benutzer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
	                	if (t.getNewValue().isBlank()) {
	                		return;
	                	}
	                	String neueAdresse = t.getNewValue();
	                	inhaberSteuerung.mitarbeiterDatenAendern(mitarbeiter, mitarbeiter.getBenutzername(), mitarbeiter.getPasswort(), mitarbeiter.getEmail(), neueAdresse, mitarbeiter.getVorname(), mitarbeiter.getName(), mitarbeiter.getBankverbindung());
	                }
	            }
	        );
			
			TableColumn<Benutzer, String> benutzerBankverbindungSpalte = new TableColumn<>("Bankverbindung");
			benutzerBankverbindungSpalte.setCellValueFactory(new PropertyValueFactory<>("bankverbindung"));
			benutzerBankverbindungSpalte.setCellFactory(TextFieldTableCell.forTableColumn());
			benutzerBankverbindungSpalte.setOnEditCommit(
	            new EventHandler<CellEditEvent<Benutzer, String>>() {
	                @Override
	                public void handle(CellEditEvent<Benutzer, String> t) {
	                	Benutzer mitarbeiter = ((Benutzer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
	                	if (t.getNewValue().isBlank()) {
	                		return;
	                	}
	                	String neueBankverbindung = t.getNewValue();
	                	inhaberSteuerung.mitarbeiterDatenAendern(mitarbeiter, mitarbeiter.getBenutzername(), mitarbeiter.getPasswort(), mitarbeiter.getEmail(), mitarbeiter.getAdresse(), mitarbeiter.getVorname(), mitarbeiter.getName(), neueBankverbindung);
	                }
	            }
	        );
			
			TableColumn<Benutzer, String> benutzerPasswortSpalte = new TableColumn<>("Passwort");
			benutzerPasswortSpalte.setCellValueFactory(new PropertyValueFactory<>("passwort"));
			benutzerPasswortSpalte.setCellFactory(TextFieldTableCell.forTableColumn());
			benutzerPasswortSpalte.setOnEditCommit(
	            new EventHandler<CellEditEvent<Benutzer, String>>() {
	                @Override
	                public void handle(CellEditEvent<Benutzer, String> t) {
	                	Benutzer mitarbeiter = ((Benutzer) t.getTableView().getItems().get(t.getTablePosition().getRow()));
	                	if (t.getNewValue().isBlank()) {
	                		return;
	                	}
	                	String neuesPasswort = t.getNewValue();
	                	inhaberSteuerung.mitarbeiterDatenAendern(mitarbeiter, mitarbeiter.getBenutzername(), neuesPasswort, mitarbeiter.getEmail(), mitarbeiter.getAdresse(), mitarbeiter.getVorname(), mitarbeiter.getName(), mitarbeiter.getBankverbindung());
	                }
	            }
	        );
			
			TableColumn<Benutzer, String> benutzerRolleSpalte = new TableColumn<>("Rolle");
			benutzerRolleSpalte.setCellValueFactory(new PropertyValueFactory<>("rolle"));
			
			tableViewMitarbeitern.getColumns().add(benutzerRolleSpalte);
			tableViewMitarbeitern.getColumns().add(benutzerBenutzernameSpalte);
			tableViewMitarbeitern.getColumns().add(benutzerVornameSpalte);
			tableViewMitarbeitern.getColumns().add(benutzerNameSpalte);
			tableViewMitarbeitern.getColumns().add(benutzerEmailSpalte);
			tableViewMitarbeitern.getColumns().add(benutzerPasswortSpalte);
			tableViewMitarbeitern.getColumns().add(benutzerAdresseSpalte);
			tableViewMitarbeitern.getColumns().add(benutzerBankverbindungSpalte);
			tableViewMitarbeitern.setItems(mitarbeitern);
			spMitarbeitern.setContent(tableViewMitarbeitern);
		}
		
		return this.spMitarbeitern;
	}
	
	public Button setLoeschenButton() {
		if (this.loeschenButton == null) {
			loeschenButton = new Button("Benutzer Loeschen");
			loeschenButton.setOnAction(e -> {
				Benutzer mitarbeiterZuLoeschen = tableViewMitarbeitern.getSelectionModel().getSelectedItem();
				inhaberSteuerung.mitarbeiterLoeschen(mitarbeiterZuLoeschen);
				mitarbeitern.remove(mitarbeiterZuLoeschen);
			});
		}
		return this.loeschenButton; 
	}
}

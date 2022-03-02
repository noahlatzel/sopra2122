/**
 * 
 */
package de.wwu.sopra.darstellung.inhaber;

import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.anwendung.mitarbeiter.Inhabersteuerung;
import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author Valeria Vassallo
 *
 */
public class FahrzeugdatenAendern extends InhaberOverview {
	BorderPane contentWrapper;
	GridPane gridPane;
	ScrollPane scrollPaneFahrzeug;
	Button loeschenButton;
	VBox form;
	ObservableList<Integer> fahrzeuge;
	ListView<Integer> listView;
	List<Fahrzeug> fahrzeugeListe;
	Label lblFahrzeugnummer = new Label("Fahrzeugnummer");
	TextField tfFahrzeugNummer = new TextField();
	Label lblKapazitaet = new Label("Kapazitaet");
	TextField tfKapazitaet = new TextField();
	Button btnBearbeiten = new Button("Fahrzeug Bearbeiten");
	Label errorText = new Label("");

	public FahrzeugdatenAendern(Stage primaryStage, double width, double height, Inhabersteuerung inhaberSteuerung) {
		super(primaryStage, width, height, inhaberSteuerung);
		root.setCenter(this.setContentWrapper());
	}
	
	private BorderPane setContentWrapper() {
		if (this.contentWrapper == null) {
			contentWrapper = new BorderPane();
			contentWrapper.setPadding(new Insets(10, 30, 10, 30));
			Label title = new Label("Fahrzeugdaten Aendern");
			title.setStyle("-fx-font-weight: bold");
			title.setFont(new Font("Arial", 32));
			contentWrapper.setTop(title);
			contentWrapper.setCenter(this.setContent());
		}
		
		return this.contentWrapper;
	}
	
	private GridPane setContent() {
		if (this.gridPane == null) {
			gridPane = new GridPane();
			gridPane.add(this.setFahrzeugListeScrollPane(), 0, 0);
			gridPane.add(this.setLoeschenButton(), 0, 1);
			gridPane.add(this.setForm(), 1, 0);
			
			gridPane.setHgap(20);
			gridPane.setVgap(20);
		}
		
		return this.gridPane;
	}
	
	private ScrollPane setFahrzeugListeScrollPane() {
		ScrollPane scrollPane = new ScrollPane();
		fahrzeugeListe = new ArrayList<Fahrzeug>(inhaberSteuerung.fahrzeugeAnzeigen());
		
		List<Integer> fahrzeugeid = new ArrayList<Integer>();
		for (int i = 0; i < fahrzeugeListe.size(); i++)
			fahrzeugeid.add(fahrzeugeListe.get(i).getFahrzeugNummer());

		fahrzeuge = (ObservableList<Integer>) FXCollections.observableArrayList(fahrzeugeid);
		listView = new ListView<Integer>(fahrzeuge);
		scrollPane.setContent(listView);
		listView.setMinWidth(600);
		scrollPane.setMinWidth(600);
		listView.setOnMouseClicked(e -> {
			try {
				int index = listView.getSelectionModel().getSelectedIndex();
				
				tfFahrzeugNummer.setText(String.valueOf(fahrzeugeListe.get(index).getFahrzeugNummer()));
				tfKapazitaet.setText(String.valueOf(fahrzeugeListe.get(index).getKapazitaet()));
				
				btnBearbeiten.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
			        public void handle(MouseEvent event) {
			            if (tfFahrzeugNummer.getText() != String.valueOf(fahrzeugeListe.get(index).getFahrzeugNummer())) {
			            	if (tfFahrzeugNummer.getText().isBlank()) errorText.setText("Angabe kann nicht leer sein");
			            	int neueFahrzeugnummer = Integer.parseInt(tfFahrzeugNummer.getText());
			            	inhaberSteuerung.fahrzeugDatenAendern(fahrzeugeListe.get(index), neueFahrzeugnummer, fahrzeugeListe.get(index).getKapazitaet());
			            }
			            
			            if (tfKapazitaet.getText() != String.valueOf(fahrzeugeListe.get(index).getKapazitaet())) {
			            	if (tfKapazitaet.getText().isBlank()) errorText.setText("Angabe kann nicht leer sein");
			            	float neueKapazitaet = Float.parseFloat(tfKapazitaet.getText());
			            	inhaberSteuerung.fahrzeugDatenAendern(fahrzeugeListe.get(index), fahrzeugeListe.get(index).getFahrzeugNummer(), neueKapazitaet);
			            }
					}
			    });
				
			} catch (IndexOutOfBoundsException k) {
				System.out.println("kein objekt an dieser Stelle, bitte waehle ein Rezept");
			}

		});
		return scrollPane;
	}
	
	public VBox setForm() {
		if (this.form == null) {
			form = new VBox(6);
			form.getChildren().add(lblFahrzeugnummer);
			form.getChildren().add(tfFahrzeugNummer);
			form.getChildren().add(lblKapazitaet);
			form.getChildren().add(tfKapazitaet);
			form.getChildren().add(btnBearbeiten);
			form.getChildren().add(errorText);
		}
		
		return this.form;
	}
	
	public Button setLoeschenButton() {
		if (this.loeschenButton == null) {
			loeschenButton = new Button("Fahrzeug Loeschen");
			loeschenButton.setOnAction(e -> {
				int fzeugIdx = listView.getSelectionModel().getSelectedIndex();
				inhaberSteuerung.fahrzeugLoeschen(fahrzeugeListe.get(fzeugIdx));
				fahrzeuge.remove(fzeugIdx);
			});
		}
		return this.loeschenButton; 
	}

}

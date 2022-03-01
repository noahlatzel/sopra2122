/**
 * 
 */
package de.wwu.sopra.darstellung.inhaber;

import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.anwendung.mitarbeiter.Inhabersteuerung;
import de.wwu.sopra.datenhaltung.benutzer.Rolle;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author Valeria Vassallo
 *
 */
public class MitarbeiterRegistrieren extends InhaberOverview {
	BorderPane contentWrapper;
	GridPane grid;
	
	public MitarbeiterRegistrieren(Stage primaryStage, double width, double height, Inhabersteuerung inhaberSteuerung) {
		super(primaryStage, width, height, inhaberSteuerung);
		root.setCenter(this.setContentWrapper());
	}
	
	private BorderPane setContentWrapper() {
		if (this.contentWrapper == null) {
			contentWrapper = new BorderPane();
			contentWrapper.setPadding(new Insets(10, 30, 10, 30));
			Label title = new Label("Mitarbeiter-Registrierung");
			title.setStyle("-fx-font-weight: bold");
			title.setFont(new Font("Arial", 32));
			contentWrapper.setTop(title);
			contentWrapper.setCenter(this.setRegistrierungFields());
		}
		
		return this.contentWrapper;
	}
	
	private GridPane setRegistrierungFields() {
		if (this.grid == null) {
			grid = new GridPane();
			
			Label lblBenutzername = new Label("Benutzername");
			Label lblPasswort = new Label("Passwort");
			Label lblEmail = new Label("Email");
			Label lblAdresse = new Label("Adresse");
			Label lblVorname = new Label("Vorname");
			Label lblNachname = new Label("Nachname");
			Label lblBankverbindung = new Label("Bankverbindung");
			Label lblRolle = new Label("Rolle");
			
			TextField tfBenutzername = new TextField();
			PasswordField tfPasswort = new PasswordField();
			TextField tfEmail = new TextField();
			TextField tfAdresse = new TextField();
			TextField tfVorname = new TextField();
			TextField tfNachname = new TextField();
			TextField tfBankverbindung = new TextField();

			VBox vboxBenutzerName = new VBox(2);
			vboxBenutzerName.getChildren().add(lblBenutzername);
			vboxBenutzerName.getChildren().add(tfBenutzername);
			
			VBox vboxPasswort = new VBox(2);
			vboxPasswort.getChildren().add(lblPasswort);
			vboxPasswort.getChildren().add(tfPasswort);
			
			VBox vboxEmail = new VBox(2);
			vboxEmail.getChildren().add(lblEmail);
			vboxEmail.getChildren().add(tfEmail);
			
			VBox vboxAdresse = new VBox(2);
			vboxAdresse.getChildren().add(lblAdresse);
			vboxAdresse.getChildren().add(tfAdresse);
			
			VBox vboxVorname = new VBox(2);
			vboxVorname.getChildren().add(lblVorname);
			vboxVorname.getChildren().add(tfVorname);
			
			VBox vboxNachname = new VBox(2);
			vboxNachname.getChildren().add(lblNachname);
			vboxNachname.getChildren().add(tfNachname);
			
			VBox vboxBankverbindung = new VBox(2);
			vboxBankverbindung.getChildren().add(lblBankverbindung);
			vboxBankverbindung.getChildren().add(tfBankverbindung);
			
			ComboBox<Rolle> rollenCB = new ComboBox<>();
			rollenCB.getItems().add(Rolle.FAHRER);
			rollenCB.getItems().add(Rolle.LAGERIST);
			
			VBox vboxRolle = new VBox(2);
			vboxRolle.getChildren().add(lblRolle);
			vboxRolle.getChildren().add(rollenCB);

			Button btRegistrieren = new Button("Registrieren");
			
			grid.add(vboxBenutzerName, 0, 0);
			grid.add(vboxVorname, 0, 1);
			grid.add(vboxPasswort, 0, 2);
			grid.add(vboxAdresse, 0, 3);
			grid.add(vboxEmail, 1, 0);
			grid.add(vboxNachname, 1, 1);
			grid.add(vboxBankverbindung, 1, 2);
			grid.add(vboxRolle, 1, 3);
			grid.add(btRegistrieren, 1, 4);
			
			btRegistrieren.setOnAction(action -> {
				boolean validInputs = true;
				
				List<TextField> inputs = new ArrayList<TextField>();
				inputs.add(tfBankverbindung);
				inputs.add(tfNachname);
				inputs.add(tfVorname);
				inputs.add(tfAdresse);
				inputs.add(tfEmail);
				inputs.add(tfPasswort);
				inputs.add(tfBenutzername);
				
				for (TextField i : inputs) {
					if (i.getText().isBlank())
						validInputs = false;
				}
				
				Rolle rolleValue = rollenCB.getValue();
				if (rolleValue == null) {
					validInputs = false;
				}

				if (validInputs == true) {
					inhaberSteuerung.mitarbeiterRegistrieren(tfBenutzername.getText(), tfPasswort.getText(), tfEmail.getText(),
							tfAdresse.getText(), tfVorname.getText(), tfNachname.getText(), tfBankverbindung.getText(), rolleValue);
					
					for (TextField tf : inputs) {
						tf.clear();
						rollenCB.valueProperty().set(null);
					}
					
				} else {
					Label errorLeerLabel = new Label("Es gibt noch leere Angaben");
					errorLeerLabel.setTextFill(Color.web("#ff0000"));
					grid.add(errorLeerLabel, 1, 5);
				}

			});
			
			grid.setHgap(10);
			grid.setVgap(10);
		}
		
		return this.grid;
	}
}

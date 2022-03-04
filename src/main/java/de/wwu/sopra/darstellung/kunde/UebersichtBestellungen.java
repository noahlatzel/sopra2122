package de.wwu.sopra.darstellung.kunde;

import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.anwendung.kunde.Kundensteuerung;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.stage.Stage;

public class UebersichtBestellungen extends KundeOverview{

	public UebersichtBestellungen(Stage primaryStage, double width, double height, Kundensteuerung kundensteuerung) {
		super(primaryStage, width, height, kundensteuerung);
		root.setCenter(setBestellungen());
	}
	
	public ScrollPane setBestellungen(){
		
		ScrollPane scrollPane = new ScrollPane(setTableView());
		   scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		   root.setCenter(scrollPane);
		   
		return scrollPane;
		
	}
	
	public TableView<Bestellung> setTableView(){
		
        TableView<Bestellung> tableview = new TableView<Bestellung>();
		
		
		if(!kundensteuerung.bestellungenAnzeigen().isEmpty()) {
			
		    List<Bestellung> bestellungen = new ArrayList<Bestellung>();
	        bestellungen = kundensteuerung.bestellungenAnzeigen();
	        
	        tableview.getItems().addAll(bestellungen);
		}
		
		return tableview;
	
	}
}

package de.wwu.sopra.darstellung.kunde;

import java.util.ArrayList;
import java.util.List;

import de.wwu.sopra.anwendung.kunde.Kundensteuerung;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.management.Produkt;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class WarenkorbAnsicht extends KundeOverview {

	FlowPane flowpane;
	TableView<Produkt> tableView;
	Button btBestellen;
	TextField textfield;
	
	public WarenkorbAnsicht(Stage primaryStage, double width, double height, Kundensteuerung kundensteuerung) {
		
		super(primaryStage, width, height, kundensteuerung);
		root.setCenter(setWarenkorbAnzeigen());
		root.setBottom(setFlowPane());
	}
	
	public ScrollPane setWarenkorbAnzeigen() {
		
		ScrollPane scrollPane = new ScrollPane(setTableView());
	    scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
	    root.setCenter(scrollPane);
		   
		return scrollPane;
	}
	
	public TableView<Produkt> setTableView() {
		
		tableView = new TableView<Produkt>();
		root.setCenter(tableView);
		
        if(!kundensteuerung.warenkorbAnsicht().getProdukte().isEmpty()) {
			
		    List<Produkt> produkte = new ArrayList<Produkt>();
	        produkte = kundensteuerung.warenkorbAnsicht().getProdukte();
	        
	        tableView.getItems().addAll(produkte);
		}
		
		return tableView;
	}
	
	public FlowPane setFlowPane() {
		
		 flowpane = new FlowPane();
		 root.setBottom(flowpane);
		
		 String betrag = String.valueOf(kundensteuerung.warenkorbAnsicht().getBetrag());
	     textfield = new TextField(betrag);
	     
	     btBestellen = new Button("Bestellen");
		 btBestellen.setMinWidth(250);
		 btBestellen.setOnAction(action -> {
			 if(!kundensteuerung.warenkorbAnsicht().getProdukte().isEmpty()) {
				 
				kundensteuerung.bestellen();
				
			  }
		    });
		 
		 flowpane.getChildren().add(textfield);
		 flowpane.getChildren().add(btBestellen);
	     
		return flowpane;
		
	}

	
		
		
	
}

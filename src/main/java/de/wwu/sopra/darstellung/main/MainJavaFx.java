package de.wwu.sopra.darstellung.main;

import java.util.ArrayList;

import de.wwu.sopra.anwendung.mitarbeiter.Lageristensteuerung;
import de.wwu.sopra.darstellung.lagerist.LageristOverview;
import de.wwu.sopra.datenhaltung.benutzer.Kunde;
import de.wwu.sopra.datenhaltung.bestellung.Bestellung;
import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Produkt;
import de.wwu.sopra.datenhaltung.management.Statistiken;
import de.wwu.sopra.datenhaltung.verwaltung.BenutzerRegister;
import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;
import de.wwu.sopra.datenhaltung.verwaltung.GrosshaendlerRegister;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainJavaFx extends Application {
	BorderPane layout;

	@Override
	public void start(Stage primaryStage) {
		initDependencies();
		Lageristensteuerung lageristenSteuerung = new Lageristensteuerung(new Lager(), new Statistiken(),
				new GrosshaendlerRegister());

		layout = new BorderPane();

		primaryStage.setTitle("Jasmins Epische Harry Potter Traenke");

		primaryStage.setScene(new LageristOverview(primaryStage, 1280, 720, lageristenSteuerung));

		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void initDependencies() {
		FahrzeugRegister.addFahrzeug(new Fahrzeug(0, 1));
		FahrzeugRegister.addFahrzeug(new Fahrzeug(1, 10));
		FahrzeugRegister.addFahrzeug(new Fahrzeug(2, 14));

		Kunde kunde = new Kunde("Beton", "1234", "hart@test.de", "Abstiege 1", "Zementa", "test", "test");

		ArrayList<Produkt> produkte = new ArrayList<Produkt>();
		produkte.add(new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29));
		produkte.add(new Produkt("Coca Cola", "Toller Geschmack", 0.99, 1.29));
		produkte.add(new Produkt("Coca Cola", "Toller Geschmack", 1.09, 1.29));
		produkte.add(new Produkt("Fanta", "Toller Geschmack", 0.99, 1.29));

		Bestellung b1 = new Bestellung(0, null, produkte, kunde);
		Bestellung b2 = new Bestellung(1, null, produkte, kunde);
		Bestellung b3 = new Bestellung(2, null, produkte, kunde);

		BenutzerRegister.benutzerHinzufuegen(kunde);
		BenutzerRegister.bestellungZuBestellungslisteHinzufuegen(kunde, b1);
		BenutzerRegister.bestellungZuBestellungslisteHinzufuegen(kunde, b2);
		BenutzerRegister.bestellungZuBestellungslisteHinzufuegen(kunde, b3);
	}
}

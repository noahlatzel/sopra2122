package de.wwu.sopra.darstellung.main;

import de.wwu.sopra.anwendung.mitarbeiter.Inhabersteuerung;
import de.wwu.sopra.darstellung.inhaber.InhaberOverview;
import de.wwu.sopra.datenhaltung.benutzer.Fahrer;
import de.wwu.sopra.datenhaltung.benutzer.Inhaber;
import de.wwu.sopra.datenhaltung.benutzer.Lagerist;
import de.wwu.sopra.datenhaltung.management.Fahrzeug;
import de.wwu.sopra.datenhaltung.management.Lager;
import de.wwu.sopra.datenhaltung.management.Statistiken;
import de.wwu.sopra.datenhaltung.verwaltung.BenutzerRegister;
import de.wwu.sopra.datenhaltung.verwaltung.FahrzeugRegister;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainJavaFx extends Application {
	BenutzerRegister benutzerReg = new BenutzerRegister();

	@Override
	public void start(Stage primaryStage) {
		Inhaber inhaber = new Inhaber("Buzz", "superpasswortyupyup", "boss@empresa.de", "Casa 123", "Jose", "Quispe", "DE00 0000 0000 0000 0000 00");
		
		Fahrer fahrer1 = new Fahrer("driver", "passwort", "sam@online.de", "ostbad 1", "Sam", "Winchester", "1234", inhaber);
		Fahrer fahrer2 = new Fahrer("conductor", "passwort", "dean@online.de", "ostbad 1", "Dean", "Winchester", "1235", inhaber);
		inhaber.fahrerHinzufuegen(fahrer1);
		inhaber.fahrerHinzufuegen(fahrer2);
		
		Lagerist lagerist1 = new Lagerist("lagerista", "passwort", "cas@online.de", "ostbad 1", "Castiel", "Angel", "1235", inhaber);
		inhaber.lageristHinzufuegen(lagerist1);
		
		FahrzeugRegister fzReg = new FahrzeugRegister();
		Fahrzeug fzeug1 = new Fahrzeug(3212, 218);
		Fahrzeug fzeug2 = new Fahrzeug(8498261, 2148);
		Fahrzeug fzeug3 = new Fahrzeug(987465, 4251);
		
		fzReg.addFahrzeug(fzeug1);
		fzReg.addFahrzeug(fzeug2);
		fzReg.addFahrzeug(fzeug3);
		
		Inhabersteuerung inhaberSteuerung = new Inhabersteuerung(inhaber, new Statistiken(), benutzerReg, new Lager(), fzReg);
		
		primaryStage.setTitle("Jasmins Epische Harry Potter Traenke");
		primaryStage.setScene(new InhaberOverview(primaryStage, 1280, 720, inhaberSteuerung));
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}

package de.wwu.sopra.datenhaltung.verwaltung;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Klasse zur Serialisierung der Objekte.
 * 
 * @author Noah Latzel
 *
 * @param <T> Objekttyp, mit dem die Pipeline arbeiten soll
 */
public class SerialisierungPipeline<T> {

	/**
	 * Serialisiert ein gegebens Objekt in /resources/(name)
	 * 
	 * @param obj  Das zu serialisierende Objekt.
	 * @param name Der Name, unter welchem das Objekt gespeichert werden soll.
	 */
	public void serialisieren(T obj, String name) {
		File f = new File("resources/" + name);

		try (FileOutputStream outputStream = new FileOutputStream(f);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);) {
			objectOutputStream.writeObject(obj);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deserialisiert ein gegebenes Objekt. Am Ende muss das Objekt noch gecastet
	 * werden. z.B.: Produkt p1 = (Produkt) sp.deserialisieren('produkt.ser');
	 * 
	 * @param name Der Dateiname des serialisierten Objekts.
	 * @return Das deserialisierte Objekt. null, wenn nicht vorhanden.
	 */
	@SuppressWarnings("unchecked")
	public T deserialisieren(String name) {
		T obj = null;
		File f = new File("resources/" + name);
		if (!(f.exists() && !f.isDirectory())) {
			return obj;
		}
		try (FileInputStream inputStream = new FileInputStream(f);
				ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);) {
			obj = (T) objectInputStream.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj;
	}
}

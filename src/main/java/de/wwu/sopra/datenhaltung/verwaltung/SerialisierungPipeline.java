package de.wwu.sopra.datenhaltung.verwaltung;

import java.io.File;
import java.io.FileInputStream;
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
		f.delete();
		try (FileOutputStream outputStream = new FileOutputStream(f);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);) {
			objectOutputStream.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deserialisiert ein gegebenes Objekt. Am Ende muss das Objekt noch gecastet
	 * werden. z.B.: Produkt p1 = (Produkt) sp.deserialisieren('produkt.ser');
	 * 
	 * @param name   Der Dateiname des serialisierten Objekts.
	 * @param newObj das neue objekt
	 * @return Das deserialisierte Objekt. null, wenn nicht vorhanden.
	 */
	@SuppressWarnings("unchecked")
	public T deserialisieren(String name, T newObj) {
		T obj = null;
		File f = new File("resources/" + name);
		try (FileInputStream inputStream = new FileInputStream(f);
				ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);) {
			obj = (T) objectInputStream.readObject();
		} catch (IOException e) {
			obj = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (obj == null) {
			return newObj;
		}
		return obj;
	}
}

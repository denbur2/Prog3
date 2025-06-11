package bankprojekt.verarbeitung;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Random;


/**
 * Eine Aktie, die ständig ihren Kurs verändert
 * @author Doro
 *
 */
public class Aktie {

	private static Map<String, Aktie> alleAktien = new HashMap<>();
	private String wkn;
	private Geldbetrag kurs;

	/**
	 * gibt die Aktie mit der gewünschten Wertpapierkennnummer zurück
	 * @param wkn Wertpapierkennnummer
	 * @return Aktie mit der angegebenen Wertpapierkennnummer oder null, wenn es diese WKN
	 * 			nicht gibt.
	 */
	public static Aktie getAktie(String wkn)
	{
		return alleAktien.get(wkn);
	}

	/**
	 * erstellt eine neu Aktie mit den angegebenen Werten
	 * @param wkn Wertpapierkennnummer
	 * @param k aktueller Kurs
	 * @throws IllegalArgumentException wenn einer der Parameter null bzw. negativ ist
	 * 		                            oder es eine Aktie mit dieser WKN bereits gibt
	 */
	private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);
	private static final Random random = new Random();

	public Aktie(String wkn, Geldbetrag k) {
		if(wkn == null || k == null || k.isNegativ() || alleAktien.containsKey(wkn))
			throw new IllegalArgumentException();

		this.wkn = wkn;
		this.kurs = k;
		System.out.println("Erstelle Aktie: " + wkn + " mit Kurs: " + k);
		alleAktien.put(wkn, this);


		// Simuliere Kursänderungen alle 5 Sekunden
		executor.scheduleAtFixedRate(() -> {
			double change = (random.nextDouble() * 0.06) - 0.03; // +/- 3% Änderung
			this.kurs = this.kurs.multiplizieren(1 + change);
			//ausgabe der kursänderung mit zwei nachkommastellen
			System.out.printf("Kursänderung für %s: %.2f%% \n", wkn, change * 100);
			System.out.println("Aktualisierter Kurs für " + wkn + ": " + this.kurs);
		}, 0, 5, TimeUnit.SECONDS);

	}

	/**
	 * Wertpapierkennnummer
	 * @return WKN der Aktie
	 */
	public String getWkn() {
		return wkn;
	}

	/**
	 * aktueller Kurs
	 * @return Kurs der Aktie
	 */
	public Geldbetrag getKurs() {
		return kurs;
	}
}

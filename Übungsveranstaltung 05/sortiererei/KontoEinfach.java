package sortiererei;
/**
 * stellt ein ganz einfaches Konto dar
 */
public class KontoEinfach implements Comparable<KontoEinfach>
{
	/**
	 * der Name des Kontoinhabers
	 */
	private String inhaber;
	/**
	 * die Kontonummer
	 */
	private long nummer;

	/**
	 * der aktuelle Kontostand
	 */
	private double kontostand;

	/**
	 * Setzt die beiden Eigenschaften kontoinhaber und kontonummer auf die angegebenen Werte,
	 * der anfängliche Kontostand wird auf 0 gesetzt.
	 *
	 * @param inhaber Kontoinhaber
	 * @param kontonummer Kontonummer
	 * @param betrag anfänglicher Kontostand
	 * @throws IllegalArgumentException wenn der Inhaber null oder betrag kleiner 0
	 */
	public KontoEinfach(String inhaber, long kontonummer, double betrag) {
		if(inhaber == null)
			throw new IllegalArgumentException("Inhaber darf nicht null sein!");
		if(betrag < 0)
			throw new IllegalArgumentException("Betrag darf nicht negativ sein!");
		this.inhaber = inhaber;
		this.nummer = kontonummer;
		this.kontostand = betrag;
	}
	
	/**
	 * setzt alle Eigenschaften des Kontos auf Standardwerte
	 */
	public KontoEinfach() {
		this("Unbekannt", 1234567, 0);
	}

	/**
	 * liefert den Kontoinhaber zurück
	 * @return   Kontoinhaber
	 */
	public String getInhaber() {
		return this.inhaber;
	}
	
	/**
	 * liefert den aktuellen Kontostand
	 * @return   Kontostand
	 */
	public double getKontostand() {
		return kontostand;
	}

	/**
	 * liefert die Kontonummer zurück
	 * @return   Kontonummer
	 */
	public long getKontonummer() {
		return nummer;
	}
	
	@Override
	public String toString() {
		String ausgabe;
		ausgabe = "Kontonummer: " + this.getKontonummerFormatiert()
				+ System.getProperty("line.separator");
		ausgabe += "Inhaber: " + this.inhaber;
		ausgabe += "Aktueller Kontostand: " + this.kontostand + " €"
			+ System.getProperty("line.separator");
		return ausgabe;
	}
	
	/**
	 * liefert die ordentlich formatierte Kontonummer
	 * @return auf 10 Stellen formatierte Kontonummer
	 */
	public String getKontonummerFormatiert()
	{
		return String.format("%10d", this.nummer);
	}
	
	@Override
	public int compareTo(KontoEinfach anderes)
	{
		if(this.getKontostand() < anderes.getKontostand())
			return -6543;
		else if (this.getKontostand() > anderes.getKontostand())
			return 423567;
		return 0;
	}

}

package bankprojekt.verarbeitung;

import bankprojekt.geld.Waehrung;

/**
 * Ein Geldbetrag mit Währung
 */
public class Geldbetrag implements Comparable<Geldbetrag>{
	/**
	 * Betrag in der in waehrung angegebenen Währung
	 */
	private double betrag;
	private double betragInEUR;
	/**
	 * Die Währung
	 */
	private Waehrung waehrung = Waehrung.EUR;
	
	/**
	 * 0 €
	 */
	public static final Geldbetrag NULL_EURO = new Geldbetrag(0);

	
	/**
	 * erstellt einen Geldbetrag in der Währung Euro
	 * @param betrag Betrag in €
	 * @throws IllegalArgumentException wenn betrag unendlich oder NaN ist
	 */
	public Geldbetrag(double betrag)
	{
		if(!Double.isFinite(betrag))
			throw new IllegalArgumentException();
		this.betrag = betrag;
		this.betragInEUR = betrag;
	}

	public Geldbetrag(double betrag, Waehrung w){
		if(!Double.isFinite(betrag))
			throw new IllegalArgumentException();
		this.betrag = betrag;
		this.betragInEUR = w.inEUR(betrag);
		this.waehrung = w;
	}

	public Geldbetrag umrechnen(Waehrung zielwaehrung){
		this.betrag = zielwaehrung.EURZuThis(this.betragInEUR);
		this.waehrung = zielwaehrung;
		return this;
	}

	/**
	 * Betrag von this
	 * @return Betrag in der Währung von this
	 */
	public double getBetrag() {
		return betrag;
	}
	
	/**
	 * rechnet this + summand
	 * @param summand zu addierender Betrag
	 * @return this + summand in der Währung von this
	 * @throws IllegalArgumentException wenn summand null ist
	 */
	public Geldbetrag plus(Geldbetrag summand)
	{
		if(summand == null)
			throw new IllegalArgumentException();
		System.out.println(this.betragInEUR + " + " + summand.betragInEUR);
		System.out.println(this.betrag + " + " + summand.betrag);

		return new Geldbetrag(this.betragInEUR + summand.betragInEUR).umrechnen(this.waehrung);
	}
	
	/**
	 * rechnet this - divisor
	 * @param subtrahend abzuziehender Betrag
	 * @return this - subtrahend in der Währung von this
	 * @throws IllegalArgumentException wenn divisor null ist
	 */
	public Geldbetrag minus(Geldbetrag subtrahend)
	{
		if(subtrahend == null)
			throw new IllegalArgumentException();
		return new Geldbetrag(this.betragInEUR - subtrahend.betragInEUR).umrechnen(this.waehrung);
	}

	/**
	 * multipliziert this mit faktor
	 * @param faktor Faktor der Multiplikation
	 * @return das faktor-Fache von this
	 * @throws IllegalArgumentException wenn faktor nicht finit ist
	 */
	public Geldbetrag mal(double faktor)
	{
		if(!Double.isFinite(faktor))
			throw new IllegalArgumentException();
		return new Geldbetrag(this.betrag * faktor);
	}

	@Override
	public int compareTo(Geldbetrag o) {
		return Double.compare(this.betrag, o.betrag);
	}

	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof Geldbetrag)) return false;
		if(o == this) return true;
		return this.compareTo((Geldbetrag) o) == 0;
	}
	
	/**
	 * prüft, ob this einen negativen Betrag darstellt
	 * @return true, wenn this negativ ist
	 */
	public boolean isNegativ()
	{
		return this.betrag < 0;
	}
	
	@Override
	public String toString()
	{
		return String.format("%1$,.2f %2$s", this.betrag, this.waehrung);
	}
}

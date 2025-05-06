package sortiererei;

/**
 * Interface für den Vergleich zweier Objekte
 * @author Doro 
 *
 */
public interface Vergleicher {
	/**
	 * vergleicht a und b
	 * @param a zu vergleichendes Objekt
	 * @param b zu vergleichendes Objekt
	 * @return positiver Wert, wenn a größer als b, 
	 *         negativer Wert, wenn a kleiner als b
	 *         und 0, wenn a == b 
	 * @throws IllegalArgumentException wenn a und b nicht verglichen werden können
	 */
	public int vergleichen(Object a, Object b);
	
	/**
	 * gibt zurück, ob a > b
	 * @param a zu vergleichendes Objekt
	 * @param b zu vergleichendes Objekt
	 * @return a > b
	 * @throws IllegalArgumentException wenn a und b nicht verglichen werden können
	 */
	public default boolean isGroesser(Object a, Object b)
	{
		return vergleichen(a, b) > 0;
	}
	
	
	/**
	 * prüft, ob a kleiner b
	 * @param a erstes zu vergleichesdes Objekt
	 * @param b zweites zu vergleichendes Objekt
	 * @return a kleiner b
	 * @throws IllegalArgumentException wenn a und b nicht verglichen werden können
	 */
	default public boolean isKleiner(Object a, Object b)
	{
		return this.vergleichen(a,  b) < 0;
	}
	
	/**
	 * prüft, ob a == b
	 * @param a erstes zu vergleichesdes Objekt
	 * @param b zweites zu vergleichendes Objekt
	 * @return a == b
	 * @throws IllegalArgumentException wenn a und b nicht verglichen werden können
	 */
	default public boolean isGleich(Object a, Object b)
	{
		return this.vergleichen(a,  b) == 0;
	}
	
}

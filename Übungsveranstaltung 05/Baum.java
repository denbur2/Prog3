/**
 * stellt eine Baumstruktur dar, in der in jedem Knoten ein Wert 
 * gespeichert ist
 *
 */
public class Baum {
	private int wert;
	private Baum links;
	private Baum rechts;
	
	/**
	 * erstellt einen Baum der Höhe 1 mit dem angegebenen 
	 * Wert im Wurzelknoten
	 * @param wert Wert des Wurzelknotens
	 */
	public Baum(int wert)
	{
		this.wert = wert;
	}
	
	/**
	 * fügt links als linken Teilbaum in this ein
	 * @param links der einzufügende linke Teilbaum. Ist links null
	 * 		        gibt es in this auf der linken Seite keine weiteren
	 * 			Knoten
	 */
	public void setLinks(Baum links)
	{
		this.links = links;
	}

	/**
	* liefert den linken Teilbaum zurück
	* @return linker Teilbaum, null, wenn keine existiert
	public Baum getLinks() {
		return links;
	} 

	/**
	 * fügt rechts als rechten Teilbaum in this ein
	 * @param rechts der einzufügende rechte Teilbaum. Ist rechts null
	 * 		        gibt es in this auf der rechten Seite keine weiteren
	 * 			Knoten
	 */
	public void setRechts(Baum rechts)
	{
		this.rechts = rechts;
	}


	/**
	* liefert den rechten Teilbaum zurück
	* @return rechter Teilbaum, null, wenn keine existiert
	public Baum getRechts() {
		return rechts;
	} 
	
	/**
	 * liefert den Wert des Wurzelknotens von this
	 * @return Wert des aktuellen Knotens
	 */
	public int getWert()
	{
		return wert;
	}
	
	@Override
	public String toString()
	{
		String ausgabe = wert + System.lineSeparator();
		if(links != null)
			ausgabe+= 	"-- " + links;
		if(rechts != null)
			ausgabe += 	"-- " + rechts;
		return ausgabe;
	}
	
	/**
	 * erstellt ein paar Bäume
	 * @param args wird nicht verwendet
	 */
	public static void main(String[] args)
	{
/*		Baum<String> woerter = new Baum<>("Hallo");
		Baum<String> lw = new Baum<>("Welt");
		Baum<String> rw = new Baum<>("!");
		woerter.setLinks(lw);
		woerter.setRechts(rw);
		System.out.println(woerter);
*/
/*
		Baum<Number> zahlen = new Baum<>(5);
		Baum<Integer> lz = new Baum<>(7);
		Baum<Double> rz = new Baum<>(3.4);
		zahlen.setLinks(lz);
		zahlen.setRechts(rz);
		System.out.println(zahlen);
*/

		//woerter.setLinks(zahlen);
		
		//Baum<Double> irgendwieFalsch = new Baum("Hallo");
		//double wert = irgendwieFalsch.getWert();

	}

}

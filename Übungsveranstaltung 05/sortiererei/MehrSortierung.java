package sortiererei;

/**
 * Mehr oder eher weniger sinnvolle Sortierungen
 */
public class MehrSortierung {
	/**
	 * probiert ungewöhnliche Sortierungen aus
	 * @param args wird nicht verwendet.
	 */
	public static void main(String[] args)
	{
		Komisch[] probe = {new Komisch(4), new Komisch(27), new Komisch(1)};
		Sortieren.sortiere(probe);
		Allgemein[] probe2 = { new Allgemein(), new Allgemein(), new Allgemein()};
		Sortieren.sortiere(probe2);
	}
}

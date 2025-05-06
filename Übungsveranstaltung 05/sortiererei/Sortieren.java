package sortiererei;
import java.math.BigDecimal;

/**
 * enthält den Sortieralgorithmus BubbleSort
 * @author Doro
 * 
 */
public class Sortieren {

	/**
	 * sortiert das Array x aufsteigend
	 * @param x das zu sortierende Array
	 * @throws NullPointerException wenn x == null
	 */
	public static void sortiere(int[] x) {
		boolean unsortiert = true;
		int temp;
		while (unsortiert) 
		{
			unsortiert = false;          
			for (int i = 0; i < x.length - 1; i++) 
			{
				if (x[i] > x[i + 1]) 
				{
					temp = x[i];
					x[i] = x[i + 1];
					x[i + 1] = temp;
					unsortiert = true; 
				}
			}
		}
	}
	
	/**
	 * sortiert das Array x aufsteigend
	 * @param x das zu sortierende Array
	 * @throws NullPointerException wenn x == null
	 */
	public static void sortiere(Comparable[] x) {
		boolean unsortiert = true;
		Comparable temp;
		while (unsortiert) 
		{
			unsortiert = false;          
			for (int i = 0; i < x.length - 1; i++) 
			{
				if (x[i].compareTo(x[i + 1]) > 0) 
				{
					temp = x[i];
					x[i] = x[i + 1];
					x[i + 1] = temp;
					unsortiert = true; 
				}
			}
		}
	}
	
	/**
	 * sortiert das Array x aufsteigend
	 * @param x das zu sortierende Array
	 * @param vergleicher Träger der Vergleichsmethode
	 * @throws NullPointerException wenn x == null
	 */
	public static void sortiere(Object[] x, Vergleicher vergleicher) {
		boolean unsortiert = true;
		Object temp;
		while (unsortiert) 
		{
			unsortiert = false;          
			for (int i = 0; i < x.length - 1; i++) 
			{
				if (vergleicher.isGroesser(x[i], x[i+1])) 
				{
					temp = x[i];
					x[i] = x[i + 1];
					x[i + 1] = temp;
					unsortiert = true; 
				}
			}
		}
	}

	/**
	 * sortiert einige Arrays
	 * @param args wird nicht benutzt
	 */
	public static void main(String[] args) {
		int[] liste = { 0, 9, 4, 6, 2, 8, 5, 1, 7, 3 };
		sortiere(liste);
		for (int i = 0; i < liste.length; i++)
			System.out.print(liste[i] + " ");
		System.out.println();
		
		String[] liste2 = { "Physalis", "Apfel", "Orange", "Birne", "Ananas" };
		sortiere(liste2);
		for (int i = 0; i < liste2.length; i++)
			System.out.print(liste2[i] + " ");
		System.out.println();
		
		BigDecimal[] liste3 = { new BigDecimal("123.45"), new BigDecimal("666"),
				              new BigDecimal("100.00"), new BigDecimal("345.67"),
				              new BigDecimal("9999.99")};
		sortiere(liste3);
		for (int i = 0; i < liste3.length; i++)
			System.out.print(liste3[i] + " ");
		System.out.println();
		
		KontoEinfach[] liste4 = { new KontoEinfach("hans", 1246734, 9999),
						   new KontoEinfach("Otto", 895975696, 456), 
						   new KontoEinfach(),
						   new KontoEinfach("Eva", 773377448, 100) };
		sortiere(liste4);
		for (int i = 0; i < liste4.length; i++)
			System.out.print(liste4[i] + " ");
		System.out.println();
		
		System.out.println("----------------------");
		Vergleicher x = new KontoVergleicher();
		sortiere(liste4, x);
		for (int i = 0; i < liste4.length; i++)
			System.out.print(liste4[i] + " ");
		System.out.println();
	}

}

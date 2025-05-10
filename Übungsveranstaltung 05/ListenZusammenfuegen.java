import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.Temporal;

/**
 * Übung zu Bounds
 * @author Doro
 */
public class ListenZusammenfuegen {
	/**
	 * fügt l1 und l2 zu einer neuen gemeinsamen List zusammen
	 * @param l1 erste Liste
	 * @param l2 zweite Liste
	 * @return die neue gemeinsame Liste aus l1 und l2
	 */
	public static <A>
	List<A> zusammenfuegen(List<? extends A> l1, List<? extends A> l2)
	{
		List<A> zusammen = new ArrayList<>(l1);
		zusammen.addAll(l2);


		return zusammen;
	}

	/**
	 * fügt zwei Listen zusammen
	 * @param args wird nicht gebraucht
	 */
	public static void main(String[] args) {
		List<LocalDate> date1 = List.of(LocalDate.now(), LocalDate.of(1990,1,1));
		List<LocalDate> date2 = List.of(LocalDate.of(2000,4,5), LocalDate.of(2023,12,24));
		List<LocalDateTime> time = List.of(LocalDateTime.now(), LocalDateTime.of(LocalDate.now(), LocalTime.of(10,35, 0)));
		List<LocalDate>	drei = zusammenfuegen(date1, date2);
		List<Temporal> vier = zusammenfuegen(date1, time);		

		List<Integer> ganzeZahlen = new LinkedList<>();
		ganzeZahlen.add(1);
		ganzeZahlen.add(2);
		ganzeZahlen.add(3);
		
		List<Double> kommaZahlen = new LinkedList<>();
		kommaZahlen.add(1.1);
		kommaZahlen.add(2.2);
		kommaZahlen.add(3.3);
		
		List<Number> zusammen;
		zusammen = zusammenfuegen(ganzeZahlen, kommaZahlen);

		System.out.println(zusammen);

		//Das sollte nicht gehen:
		//List<String> z1 = zusammenfuegen(ganzeZahlen, kommaZahlen);
		//List<Integer> z2 = zusammenfuegen(ganzeZahlen, kommaZahlen);
		//List<Double> z3 = zusammenfuegen(ganzeZahlen, kommaZahlen);

		//System.out.println(z1);
	}
}

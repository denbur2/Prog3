import java.net.SocketOption;
import java.util.Date;
import java.sql.Time;

public class Test_Intervall {
    public static void main(String[] args) {
        Intervall<Integer> intIntervall = new Intervall<>(1, 10);
        System.out.println("Untere Grenze: " + intIntervall.getUntereGrenze());
        System.out.println("Obere Grenze: " + intIntervall.getObereGrenze());
        System.out.println("Ist leer: " + intIntervall.isLeer());
        System.out.println("Enthaelt 5: " + intIntervall.enthaelt(5));


        Intervall<Double> doubleIntervall = new Intervall<>(1.5, 10.5);
        System.out.println("Untere Grenze: " + doubleIntervall.getUntereGrenze());
        System.out.println("Obere Grenze: " + doubleIntervall.getObereGrenze());
        System.out.println("Ist leer: " + doubleIntervall.isLeer());

        Intervall<String> stringIntervall = new Intervall<>("A", "Z");
        System.out.println("Untere Grenze: " + stringIntervall.getUntereGrenze());
        System.out.println("Obere Grenze: " + stringIntervall.getObereGrenze());
        System.out.println("Ist leer: " + stringIntervall.isLeer());

        Intervall<Integer> intIntervall2 = new Intervall<>(5, 15);
        Intervall<Integer> schnittIntervall = intIntervall.schnitt(intIntervall2);
        System.out.println("Schnitt Intervall: " + schnittIntervall.getUntereGrenze() + " - " + schnittIntervall.getObereGrenze());


        Intervall<Date> dateIntervall = new Intervall<>(new Date(1), new Date(System.currentTimeMillis()));
        Intervall<Time> timeIntervall = new Intervall<>(new Time(0), new Time(System.currentTimeMillis()));



        Intervall<Date> dateschnitt = dateIntervall.schnitt(timeIntervall);
        System.out.println("Schnitt Intervall unten: " + dateschnitt.untereGrenze);
        System.out.println("Schnitt Intervall oben: " + dateschnitt.obereGrenze);

        //Intervall<Object> falsch; //hier kommt der Compilerfehler
        Intervall<String> richtig = new Intervall<>("A", "B");
        //richtig.enthaelt(3.5); //hier kommt der Compilerfehler
        Intervall<Double> zahlen = new Intervall<>(1.2, 3.4);
        Intervall<String> texte = new Intervall<>("a", "b");
        //zahlen.schnitt(texte); //hier kommt der Compilerfehle

    }

}

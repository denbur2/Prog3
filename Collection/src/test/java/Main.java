import java.time.LocalDate;

import bankprojekt.verarbeitung.*;
import bankprojekt.geld.Waehrung;


public class Main {
    public static void main(String[] args){

        Waehrung eineWaehrung = Waehrung.DOBRA;
        System.out.println(eineWaehrung.inEUR(20000000));

        Geldbetrag Gelt = new Geldbetrag(100, Waehrung.FRANC);
        Gelt.umrechnen(Waehrung.DOBRA);
        System.out.println(Gelt.toString());


        LocalDate datum = LocalDate.of(2002, 2, 23);
        Kunde Hendrik = new Kunde("Hendrik", "Armbrecht", "Schloss NeuSchwarnStein", datum);

        Geldbetrag Vielgelt = new Geldbetrag(100000, Waehrung.EUR);
        Girokonto kontox = new Girokonto(Hendrik, 69420, Vielgelt);

        System.out.println(Vielgelt);

        kontox.waehrungswechsel(Waehrung.ESCUDO);
        System.out.println(kontox.getDispo());







    }
}

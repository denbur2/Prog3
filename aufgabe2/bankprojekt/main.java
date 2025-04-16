package bankprojekt;

import bankprojekt.geld.Waehrung;
import bankprojekt.verarbeitung.*;

import java.time.LocalDate;
import java.util.Locale;

public class main {
    public static void main(String[] args){

        Waehrung eineWaehrung = Waehrung.DOBRA;
        System.out.println(eineWaehrung.inEUR(20000000));

        Geldbetrag Gelt = new Geldbetrag(100, Waehrung.FRANC);
        Gelt.umrechnen(Waehrung.DOBRA);
        System.out.println(Gelt.toString());


        LocalDate datum = LocalDate.of(2002, 2, 23);
        Kunde Hendrik = new Kunde("Hendrik", "Armbrecht", "Schloss NeuSchwarnStein", datum);

        Geldbetrag VielGelt = new Geldbetrag(100000, Waehrung.EUR);
        Girokonto kontox = new Girokonto(Hendrik, 69420, VielGelt);

        kontox.waehrungswechsel(Waehrung.ESCUDO);
        System.out.println(kontox.getDispo());







    }
}

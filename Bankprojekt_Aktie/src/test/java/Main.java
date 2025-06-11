import java.time.LocalDate;

import bankprojekt.verarbeitung.*;
import bankprojekt.geld.Waehrung;

import static java.lang.Thread.sleep;


public class Main {
    public static void main(String[] args){

        Aktie aktie1 = new Aktie("DeineMutter", new Geldbetrag(100, Waehrung.EUR));
        Aktie aktie2 = new Aktie("VW", new Geldbetrag(200, Waehrung.EUR));
        Aktie aktie3 = new Aktie("Bitcoin", new Geldbetrag(30000, Waehrung.EUR));
        Aktienkonto aktienKonto = new Aktienkonto(new Kunde(), 0);
        aktienKonto.einzahlen(new Geldbetrag(10000, Waehrung.EUR));

        try {
            aktienKonto.kaufauftrag("DeineMutter", 10, new Geldbetrag(100, Waehrung.EUR)).get();
            aktienKonto.kaufauftrag("VW", 5, new Geldbetrag(200, Waehrung.EUR)).get();
            aktienKonto.kaufauftrag("Bitcoin", 1, new Geldbetrag(30000, Waehrung.EUR)).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        aktienKonto.kaufauftrag("DeineMutter", 7,new Geldbetrag(90, Waehrung.EUR));
        aktienKonto.kaufauftrag("VW", 7,new Geldbetrag(180, Waehrung.EUR));
        aktienKonto.kaufauftrag("Bitcoin", 7, new Geldbetrag(29000, Waehrung.EUR));
        aktienKonto.verkaufauftrag("Bitcoin", new Geldbetrag(31000, Waehrung.EUR));
        aktienKonto.verkaufauftrag("VW", new Geldbetrag(220, Waehrung.EUR));
        aktienKonto.verkaufauftrag("DeineMutter", new Geldbetrag(110, Waehrung.EUR));


    }
}

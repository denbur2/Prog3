package bankprojekt;

import bankprojekt.geld.Waehrung;

public class main {
    public static void main(String[] args){

        Waehrung eineWaehrung = Waehrung.DOBRA;

        System.out.println(eineWaehrung.inEUR(20000000));

    }
}

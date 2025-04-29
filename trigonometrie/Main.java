import trigonometrie.Winkel;
import mathematik.Vektor;

import java.util.Arrays;

public class Main{
    public static void main(String[] args) {
        Winkel rechter_winkel = new Winkel(90);
        Winkel kein_rechter_winkel = new Winkel(69);

        Vektor VektorU = Vektor.ausLengeWinkel(5,45);
        Vektor Vektor1 = new Vektor(10,10);
        Vektor Vektor2 = new Vektor(5,10);
        Vektor Vektor3 = Vektor2.addieren(Vektor1);

        Vektor Vektorx = new Vektor(5, 0);

        Vektor[] vektoren = new Vektor[3];
        vektoren[0] = Vektor1;
        vektoren[1] = Vektor2;
        vektoren[2] = Vektor3;

        System.out.println("Original:");
        for (Vektor v : vektoren) {
            System.out.println(v.toString() + "  " + v.winkel);
        }
        //Arrays.sort(vektoren, new Betrag());
        Arrays.sort(vektoren, new winkel());

        System.out.println("sortiert:");
        for (Vektor v : vektoren) {
            System.out.println(v.toString() + "  " + v.winkel);
        }




        System.out.println(Vektor1.x);
        System.out.println("addition: " + Vektor3.toString());
        System.out.println("skalar: "+Vektor3.skalarprodukt(Vektor3));
        System.out.println("Inverse: " + Vektor3.inverserVektor().toString());
        System.out.println("orthogonal: " + Vektorx.orthogonalerEinheitsvektor().toString());






        System.out.println(rechter_winkel.getWinkelInGrad());
        System.out.println(rechter_winkel.getWinkelImBogenmass());
        System.out.println(rechter_winkel.toString());

        System.out.println(rechter_winkel.addieren(kein_rechter_winkel));
        System.out.println(rechter_winkel.multiplizieren(0.91));
        System.out.println(rechter_winkel.subtrahieren(kein_rechter_winkel));


    }

}
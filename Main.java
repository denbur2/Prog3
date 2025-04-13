import trigonometrie.Winkel;
import mathematik.Vektor;

public class Main{
    public static void main(String[] args) {
        Winkel rechter_winkel = new Winkel(90);
        Winkel kein_rechter_winkel = new Winkel(69);

        Vektor VektorU = Vektor.ausLengeWinkel(5,45);
        Vektor Vektor1 = Vektor.ausKoordinate(10,10);
        Vektor Vektor2 = Vektor.ausKoordinate(5,5);

        Vektor Vektor3 = Vektor2.addieren(Vektor1);


        System.out.println(Vektor1.x);
        System.out.println("addition: " + Vektor3.x + " " + Vektor3.y);
        System.out.println("skalar: "+Vektor3.skalarprodukt(Vektor3));
        System.out.println("Inverse: " + Vektor3.inverserVektor().y);



        System.out.println(rechter_winkel.getWinkelInGrad());
        System.out.println(rechter_winkel.getWinkelImBogenmass());
        System.out.println(rechter_winkel.toString());

        System.out.println(rechter_winkel.addieren(kein_rechter_winkel));
        System.out.println(rechter_winkel.multiplizieren(0.91));
        System.out.println(rechter_winkel.subtrahieren(kein_rechter_winkel));


    }

}
import trigonometrie.Winkel;

public class Main{
    public static void main(String[] args) {
        Winkel rechter_winkel = new Winkel(90);
        Winkel kein_rechter_winkel = new Winkel(69);

        System.out.println(rechter_winkel.getWinkelInGrad());
        System.out.println(rechter_winkel.getWinkelImBogenmass());
        System.out.println(rechter_winkel.toString());

        System.out.println(rechter_winkel.addieren(kein_rechter_winkel));
        System.out.println(rechter_winkel.multiplizieren(0.91));
        System.out.println(rechter_winkel.subtrahieren(kein_rechter_winkel));


    }

}
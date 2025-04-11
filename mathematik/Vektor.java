package mathematik;

public class Vektor {
    public double x;
    public double y;
    public double lenge;
    public double winkel;
    public static double ursprung = 0;

    public Vektor (double a, double b){

    }

    public Vektor ausKoordinate(double x, double y){
        this.x = x;
        this.y = y;
        return new Vektor(x, y);
    }
    public Vektor ausLengeWinkel(double lenge, double winkel){
        this.lenge = lenge;
        this.winkel = winkel;
        this.x = lenge * Math.cos(winkel);
        this.y = lenge * Math.sin(winkel);

        return new Vektor(x, y);

    }
}

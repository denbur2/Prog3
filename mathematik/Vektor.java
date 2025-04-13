package mathematik;

public class Vektor {
    public double x;
    public double y;
    public double lenge;
    public double winkel;
    public static double ursprung = 0;

    public Vektor (double a, double b){
        this.x = a;
        this.y = b;
    }
    public Vektor (double a, double b, double c, double d){
        this.x = a;
        this.y = b;
        this.lenge = c;
        this.winkel = d;
    }

    public static Vektor ausKoordinate(double x, double y){
        return new Vektor(x, y);
    }
    public static Vektor ausLengeWinkel(double lenge, double winkel){
        return new Vektor(lenge * Math.cos(winkel)  , lenge * Math.sin(winkel), lenge, winkel);
    }

    public Vektor addieren(Vektor v){
        double a = this.x + v.x;
        double b = this.y + v.y;
        return new Vektor(a, b);
    }
    public double skalarprodukt(Vektor v){
        return  ((this.x * v.x) + (this.y * v.y));
    }

    public Vektor inverserVektor(){
        return new Vektor(-this.x, -this.y);
    }
    public Vektor orthogonalerEinheitsvektor(){

    }
}

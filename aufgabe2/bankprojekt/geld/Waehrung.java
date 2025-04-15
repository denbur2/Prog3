package bankprojekt.geld;

public enum Waehrung {
    EUR(1.0),
    ESCUDO( 109.8269),
    DOBRA(24304.7429),
    FRANC(490.92);

    private final double kursVonEUR;

    Waehrung(double kursVonEUR) {
        this.kursVonEUR = kursVonEUR;
    }
    public double inEUR(double a){
        return a/kursVonEUR;
    }
}
import mathematik.Vektor;
import java.util.Comparator;

public class winkel implements Comparator<Vektor> {
    public int compare(Vektor v1, Vektor v2) {
        return Double.compare(v2.winkel, v1.winkel);
    }
}

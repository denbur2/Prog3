import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;

public class Gross {
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(args[0]);
        BigDecimal b = new BigDecimal(args[1]);
        System.out.println(a + " " + b);
        System.out.println(a.divide(b, 19, RoundingMode.DOWN));





        LocalDate z1 = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);

        String text = z1.format(formatter);
        LocalDate parsedDate = LocalDate.parse(text, formatter);

        System.out.println(z1);


    }
}

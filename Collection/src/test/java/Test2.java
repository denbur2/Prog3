import bankprojekt.verwaltung.*;
import bankprojekt.verarbeitung.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class Test2 {

    @Test
    public void testGetAlleKonten() {
        Bank bank = new Bank(12345678);

        Kunde kunde = new Kunde("Max", "Mustermann", "Musterstraße 1", LocalDate.of(1990, 5, 15));

        long kontoNr1 = bank.girokontoErstellen(kunde);

        long kontoNr2 = bank.sparbuchErstellen(kunde);

        bank.geldEinzahlen(kontoNr1, new Geldbetrag(100.00));
        bank.geldEinzahlen(kontoNr2, new Geldbetrag(50.00));

        String alleKonten = bank.getAlleKonten();
        System.out.println(alleKonten);

    }
}

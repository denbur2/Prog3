import bankprojekt.verarbeitung.*;
import bankprojekt.verwaltung.Bank;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UnitTest {
    Bank bank = new Bank(12345678);
    Kunde kunde = new Kunde("Max", "Mustermann", "Musterstraße 1", LocalDate.of(1990, 5, 15));
    Kunde kunde2 = new Kunde("Lilly", "Musterfrau", "Musterstraße 2", LocalDate.of(1991, 6, 14));
    long kontoNr1 = bank.girokontoErstellen(kunde);
    long kontoNr2 = bank.girokontoErstellen(kunde2);


    @Test
    void testGeldUeberweisen() throws GesperrtException {
        Kunde kundeMock = mock(Kunde.class);
        when(kundeMock.getName()).thenReturn("Max Mustermann");

        UeberweisungsfaehigesKonto mock1 = mock(UeberweisungsfaehigesKonto.class);
        when(mock1.getInhaber()).thenReturn(kundeMock);
        when(mock1.ueberweisungAbsenden(any(Geldbetrag.class), any(String.class), anyLong(), anyLong(), any(String.class)))
                .thenReturn(true);

        UeberweisungsfaehigesKonto mock2 = mock(UeberweisungsfaehigesKonto.class);
        when(mock2.getInhaber()).thenReturn(kundeMock);

        long mockKonto = bank.mockEinfuegen(mock1);
        long mockKonto2 = bank.mockEinfuegen(mock2);

        bank.geldEinzahlen(mockKonto, new Geldbetrag(100.00));
        bank.geldEinzahlen(mockKonto2, new Geldbetrag(50.00));
        boolean erfolg = bank.geldUeberweisen(mockKonto, mockKonto2, new Geldbetrag(20.00), "test");

        assertTrue(erfolg);
    }


    @Test
    void testUeberweisungMitZuWenigGeld() {
        bank.geldEinzahlen(kontoNr1, new Geldbetrag(10.00));
        bank.geldEinzahlen(kontoNr2, new Geldbetrag(1000.00));
        boolean erfolg = bank.geldUeberweisen(kontoNr1, kontoNr2, new Geldbetrag(50.00), "zu viel");

        assertFalse(erfolg);
        assertEquals(new Geldbetrag(10.00), bank.getKontostand(kontoNr1));
        assertEquals(new Geldbetrag(1000.00), bank.getKontostand(kontoNr2));
    }

    @Test
    void testUeberweisungAnSichSelbst() {
        bank.geldEinzahlen(kontoNr1, new Geldbetrag(100.00));
        boolean erfolg = bank.geldUeberweisen(kontoNr1, kontoNr1, new Geldbetrag(10.00), "unsinnig");

        assertTrue(erfolg);
        assertEquals(new Geldbetrag(100.00), bank.getKontostand(kontoNr1));
    }

    @Test
    void testKontostandOhneEinzahlung() {
        assertEquals(new Geldbetrag(0.00), bank.getKontostand(kontoNr1));
    }

    @Test
    void testUeberweisungMitNegativemBetrag() {
        bank.geldEinzahlen(kontoNr1, new Geldbetrag(100.00));
        boolean erfolg = bank.geldUeberweisen(kontoNr1, kontoNr2, new Geldbetrag(-10.00), "negativ");

        assertFalse(erfolg);
        assertEquals(new Geldbetrag(100.00), bank.getKontostand(kontoNr1));
        assertEquals(new Geldbetrag(0.00), bank.getKontostand(kontoNr2));
    }

    @Test
    void testUeberweisungAnUnbekanntesKonto() {
        bank.geldEinzahlen(kontoNr1, new Geldbetrag(100.00));
        boolean erfolg = bank.geldUeberweisen(kontoNr1, 99999999L, new Geldbetrag(10.00), "ungültiges Ziel");

        assertFalse(erfolg);
        assertEquals(new Geldbetrag(100.00), bank.getKontostand(kontoNr1));
    }

    @Test
    void testUeberweisungVonGesperrtemKonto() {
        bank.geldEinzahlen(kontoNr1, new Geldbetrag(100.00));
        // Konto sperren
        bank.kontoSperren(kontoNr1);
        boolean erfolg = bank.geldUeberweisen(kontoNr1, kontoNr2, new Geldbetrag(50.00), "Test Sperre");

        assertFalse(erfolg);
        assertEquals(new Geldbetrag(100.00), bank.getKontostand(kontoNr1));
        assertEquals(new Geldbetrag(0.00), bank.getKontostand(kontoNr2));
    }









}

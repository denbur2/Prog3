import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import bankprojekt.verarbeitung.Geldbetrag;
import bankprojekt.verarbeitung.GesperrtException;
import bankprojekt.verarbeitung.Kunde;
import bankprojekt.verarbeitung.UeberweisungsfaehigesKonto;
import bankprojekt.verwaltung.Bank;

public class UnitTest {
    
    @Test
    void testGeldUeberweisen() throws GesperrtException {
        System.out.println("UnitTest");
        Bank bank = new Bank(12345678);

        Kunde kundeMock = mock(Kunde.class);
        when(kundeMock.getName()).thenReturn("Max Mustermann");

        UeberweisungsfaehigesKonto mock1 = mock(UeberweisungsfaehigesKonto.class);
        when(mock1.getInhaber()).thenReturn(kundeMock);
        when(mock1.ueberweisungAbsenden(any(Geldbetrag.class), any(String.class), anyLong(), anyLong(), any(String.class))).thenReturn(true);

        UeberweisungsfaehigesKonto mock2 = mock(UeberweisungsfaehigesKonto.class);
        when(mock2.getInhaber()).thenReturn(kundeMock);

        long mockKonto = bank.mockEinfuegen(mock1);
        long mockKonto2 = bank.mockEinfuegen(mock2);

        bank.geldEinzahlen(mockKonto, new Geldbetrag(100.00));
        bank.geldEinzahlen(mockKonto2, new Geldbetrag(50.00));
        boolean erfolg = bank.geldUeberweisen(mockKonto, mockKonto2, new Geldbetrag(20.00), "test");

        assertEquals(true, erfolg);
    }
}

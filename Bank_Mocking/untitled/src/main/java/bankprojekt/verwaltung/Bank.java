package bankprojekt.verwaltung;
import bankprojekt.verarbeitung.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Bank {
    private final long bankleitzahl;
    private long naechsteKontonummer = 1;
    private Map<Long, Konto> konten = new HashMap<>();

    public Bank(long bankleitzahl) {
        this.bankleitzahl = bankleitzahl;
    }

    public long getBankleitzahl() {
        return bankleitzahl;
    }

    public long girokontoErstellen(Kunde inhaber) {
        long nummer = naechsteKontonummer++;
        Girokonto gk = new Girokonto(inhaber, nummer, Geldbetrag.NULL_EURO);
        konten.put(nummer, gk);
        return nummer;
    }

    public long sparbuchErstellen(Kunde inhaber) {
        long nummer = naechsteKontonummer++;
        Sparbuch sb = new Sparbuch(inhaber, nummer);
        konten.put(nummer, sb);
        return nummer;
    }

    public String getAlleKonten() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Long, Konto> eintrag : konten.entrySet()) {
            sb.append("KontoNr: ").append(eintrag.getKey()).append(", Kontostand: ").append(eintrag.getValue().getKontostand()).append("\n");
        }
        return sb.toString();
    }

    public List<Long> getAlleKontonummern() {
        return new ArrayList<>(konten.keySet());
    }

    public boolean geldAbheben(long von, Geldbetrag betrag) {
        Konto konto = konten.get(von);
        if (konto != null) {
            try {
                return konto.abheben(betrag);
            } catch (GesperrtException e) {
                return false;
            }
        }
        return false;
    }


    public void geldEinzahlen(long auf, Geldbetrag betrag) {
        Konto konto = konten.get(auf);
        if (konto != null) {
            konto.einzahlen(betrag);
        }
    }


    public boolean kontoLoeschen(long nummer) {
        return konten.remove(nummer) != null;
    }

    public Geldbetrag getKontostand(long nummer) {
        Konto konto = konten.get(nummer);
        return konto != null ? konto.getKontostand() : null;
    }

    public boolean geldUeberweisen(long vonKontonr, long nachKontonr, Geldbetrag betrag, String verwendungszweck) {
        Konto von = konten.get(vonKontonr);
        Konto nach = konten.get(nachKontonr);

        if (von instanceof UeberweisungsfaehigesKonto && nach instanceof UeberweisungsfaehigesKonto) {
            UeberweisungsfaehigesKonto sender = (UeberweisungsfaehigesKonto) von;
            UeberweisungsfaehigesKonto empfaenger = (UeberweisungsfaehigesKonto) nach;

            try {
                boolean erfolgreich = sender.ueberweisungAbsenden(
                        betrag,
                        empfaenger.getInhaber().getName(),
                        nachKontonr,
                        bankleitzahl,
                        verwendungszweck
                );

                if (erfolgreich) {
                    empfaenger.ueberweisungEmpfangen(
                            betrag,
                            sender.getInhaber().getName(),
                            vonKontonr,
                            bankleitzahl,
                            verwendungszweck
                    );
                    return true;
                }
            } catch (GesperrtException e) {
                // Konto ist gesperrt → Überweisung gescheitert
            }
        }

        return false;
    }

}

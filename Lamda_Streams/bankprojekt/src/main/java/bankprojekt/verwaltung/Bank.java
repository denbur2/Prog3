package bankprojekt.verwaltung;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bankprojekt.verarbeitung.Geldbetrag;
import bankprojekt.verarbeitung.GesperrtException;
import bankprojekt.verarbeitung.Girokonto;
import bankprojekt.verarbeitung.Konto;
import bankprojekt.verarbeitung.Kunde;
import bankprojekt.verarbeitung.Sparbuch;
import bankprojekt.verarbeitung.UeberweisungsfaehigesKonto;


public class Bank {
    private final long bankleitzahl;
    private long naechsteKontonummer = 1;
    private final Map<Long, Konto> konten = new HashMap<>();

    public Bank(long bankleitzahl) {
        this.bankleitzahl = bankleitzahl;
    }

    public long mockEinfuegen(Konto k){
        long nummer = naechsteKontonummer++;
        konten.put(nummer, k);
        return nummer;
    }

    public void schenkungAnNeuerwachsene(Geldbetrag betrag) {
        konten.values().stream()
                .filter(konto -> konto.getInhaber().getAlter() >= 18)
                .forEach(konto -> konto.einzahlen(betrag));
    }
    public List<Kunde> getKundenMitLeeremKonto() {
        List<Kunde> kunden = new ArrayList<>();
        for (Map.Entry<Long, Konto> eintrag : konten.entrySet()) {
            if (eintrag.getValue().getKontostand().equals(Geldbetrag.NULL_EURO)) {
                kunden.add(eintrag.getValue().getInhaber());
            }
        }
        return kunden;
    }
    public String getKundengeburtstage() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Long, Konto> eintrag : konten.entrySet()) {
            sb.append(eintrag.getValue().getInhaber().getName()).append(": ").append(eintrag.getValue().getInhaber().getGeburtsdatum()).append("\n");
        }
        return sb.toString();
    }
    public long getAnzahlSenioren() {
        return (int) konten.values().stream()
                .filter(konto -> konto.getInhaber().getAlter() >= 65)
                .count();
    }
    public List<Kunde> getAlleArmenKunden(Geldbetrag maxium){
        List<Kunde> armeKunden = new ArrayList<>();
        for (Map.Entry<Long, Konto> eintrag : konten.entrySet()) {
            if (eintrag.getValue().getKontostand().compareTo(maxium) < 0) {
                armeKunden.add(eintrag.getValue().getInhaber());
            }
        }
        return armeKunden;
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
            } catch (IllegalArgumentException e){
                return false;
            }
        }

        return false;
    }
    public void kontoSperren(long kontoNr){
        konten.get(kontoNr).sperren();
    }
    public void kontoEntsperren(long kontoNr){
        konten.get(kontoNr).entsperren();
    }

}

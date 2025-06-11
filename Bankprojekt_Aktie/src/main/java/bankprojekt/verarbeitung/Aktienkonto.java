package bankprojekt.verarbeitung;

import bankprojekt.geld.Waehrung;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.Future;


public class Aktienkonto extends Konto{
    private Map<Aktie, Integer> aktienBestand;

    public Aktienkonto(Kunde inhaber, long kontonummer) {
        super(inhaber, kontonummer);
        this.aktienBestand = new java.util.HashMap<>();
    }


    public Future<Geldbetrag> kaufauftrag(String wkn, int anzahl, Geldbetrag hoechstpreis) {
        System.out.println("Kaufauftrag für " + anzahl + " Aktien der WKN " + wkn + " mit Höchstpreis " + hoechstpreis);
        Aktie aktie = Aktie.getAktie(wkn);
        if (aktie == null) {
            throw new IllegalArgumentException("Aktie mit WKN " + wkn + " existiert nicht.");
        }
        if (anzahl <= 0) {
            throw new IllegalArgumentException("Anzahl muss positiv sein.");
        }
        if (hoechstpreis.isNegativ()) {
            throw new IllegalArgumentException("Höchstpreis darf nicht negativ sein.");
        }
        // Asynchroner Kaufauftrag: Warte, bis Kurs <= Höchstpreis, dann kaufe
        return java.util.concurrent.CompletableFuture.supplyAsync(() -> {
            while (true) {
                try {
                    Thread.sleep(1000); // 1 Sekunde warten
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
                Geldbetrag aktuellerKurs = aktie.getKurs();
                System.out.println("aktueller kontostand: " + getKontostand());
                if (aktuellerKurs.compareTo(hoechstpreis) <= 0) {
                    Geldbetrag gesamtpreis = aktuellerKurs.multiplizieren(anzahl);
                    synchronized (this) {
                        if (getKontostand().compareTo(gesamtpreis) >= 0) {
                            setKontostand(getKontostand().minus(gesamtpreis));
                            aktienBestand.put(aktie, aktienBestand.getOrDefault(aktie, 0) + anzahl);
                            System.out.println("Kauf erfolgreich: " + anzahl + " Aktien der WKN " + wkn + " zu einem Preis von " + aktuellerKurs + ". Neuer Kontostand: " + getKontostand());
                            return gesamtpreis;
                        } else {
                            System.out.println("Nicht genügend Guthaben für den Kauf.");
                            System.out.println("Aktueller Kontostand: " + getKontostand());
                            return null;
                        }
                    }
                }
            }
        });
    }

    public Future<Geldbetrag> verkaufauftrag(String wkn, Geldbetrag minimalpreis) {
        System.out.println("Verkaufauftrag für Aktien der WKN " + wkn + " mit Minimalpreis " + minimalpreis);
        Aktie aktie = Aktie.getAktie(wkn);
        if (aktie == null || !aktienBestand.containsKey(aktie) || aktienBestand.get(aktie) <= 0) {
            System.out.println("Keine Aktien der WKN " + wkn + " im Bestand.");
            return java.util.concurrent.CompletableFuture.completedFuture(new Geldbetrag(0, Waehrung.EUR));
        }
        int anzahl = aktienBestand.get(aktie);
        return java.util.concurrent.CompletableFuture.supplyAsync(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
                Geldbetrag aktuellerKurs = aktie.getKurs();
                if (aktuellerKurs.compareTo(minimalpreis) >= 0) {
                    Geldbetrag gesamterloes = aktuellerKurs.multiplizieren(anzahl);
                    synchronized (this) {
                        setKontostand(getKontostand().plus(gesamterloes));
                        aktienBestand.remove(aktie);
                        System.out.println("Verkauf erfolgreich: " + anzahl + " Aktien der WKN " + wkn + " zu " + aktuellerKurs + ". Neuer Kontostand: " + getKontostand());
                    }
                    return gesamterloes;
                }
            }
        });
    }

        // Hier könnte}

    /*@Override
    public void einzahlen(Geldbetrag betrag) {
        setKontostand(getKontostand().plus(betrag));
        System.out.println("Einzahlung von " + betrag + " auf das Aktienkonto. Neuer Kontostand: " + getKontostand());
    }*/

    @Override
    public boolean abheben(Geldbetrag betrag) throws GesperrtException {
        return false;
    }


}

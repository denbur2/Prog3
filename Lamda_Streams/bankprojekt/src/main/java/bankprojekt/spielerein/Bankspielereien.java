package bankprojekt.spielerein;


import java.time.LocalDate;

import bankprojekt.verarbeitung.Geldbetrag;
import bankprojekt.verarbeitung.GesperrtException;
import bankprojekt.verarbeitung.Kunde;
import bankprojekt.verwaltung.Bank;

/**
 * probiert die Stream-Methoden der Bank-Klasse aus
 */
public class Bankspielereien {
	/**
	 * Methoden zur Stream-Aufgabe ausprobieren
	 * @param args wird nicht verwendet
	 * @throws GesperrtException tritt nicht auf
	 */
	public static void main(String[] args) throws GesperrtException {
		Bank bank = new Bank(12345);
		Kunde opa = new Kunde("Opa", "Otto", "Altersheim", LocalDate.of(1950, 3, 1));
		Kunde oma = new Kunde("Oma", "Emma", "Altersheim", LocalDate.of(1957, 11, 5));
		Kunde kind = new Kunde("Kind", "Klein", "zuhause", LocalDate.of(2016, 2, 29));
		Kunde teenager = new Kunde("Teenager", "Mittel", "zuhause", LocalDate.of(2010, 6, 18));
		Kunde geradeErwachsen = new Kunde("Erwachsen", "Neu", "zuhause", LocalDate.of(2006, 1, 1));
		Kunde nochNichtGanzErwachsen = new Kunde("Erwachsen", "Fast", "zuhause", LocalDate.of(2006, 12, 31));
		Kunde mama = new Kunde("Mama", "Erna", "zuhause", LocalDate.of(1975, 3, 5));
		Kunde papa = new Kunde("Papa", "Hugo", "zuhause", LocalDate.of(1976, 7, 15));
		Kunde senior = new Kunde("Uropa", "Heinz", "Neben dem Friedhof", LocalDate.of(1935, 2, 28));
		bank.girokontoErstellen(opa);
		bank.girokontoErstellen(opa);
		bank.girokontoErstellen(opa);
		bank.girokontoErstellen(oma);
		bank.girokontoErstellen(kind);
		bank.girokontoErstellen(kind);
		bank.girokontoErstellen(teenager);
		bank.girokontoErstellen(geradeErwachsen);
		bank.girokontoErstellen(geradeErwachsen);
		bank.girokontoErstellen(nochNichtGanzErwachsen);
		bank.girokontoErstellen(mama);
		bank.girokontoErstellen(mama);
		bank.girokontoErstellen(mama);
		bank.girokontoErstellen(mama);
		bank.girokontoErstellen(papa);
		bank.girokontoErstellen(senior);
		
		System.out.println(bank.getKundengeburtstage());
		System.out.println("-------------");
		System.out.println("Senioren: " + bank.getAnzahlSenioren());
		System.out.println("-------------");
		bank.schenkungAnNeuerwachsene(new Geldbetrag(100));
		System.out.println("Nach Schenkung an 3 Konten:");
		System.out.println(bank.getAlleKonten());
		System.out.println("-------------");
		System.out.println(bank.getKundenMitLeeremKonto());
		bank.geldAbheben(16, new Geldbetrag(20));
		bank.geldAbheben(14, new Geldbetrag(20));
		bank.geldAbheben(13, new Geldbetrag(20));
		System.out.println("Nach Abhebung (2 x bei Mama, einmal bei Uropa: ");
		System.out.println(bank.getKundenMitLeeremKonto());
		System.out.println("-------------");
		//System.out.println("Alle mit weniger als 20 Euro: ");
		//System.out.println(bank.getAlleArmenKunden(new Geldbetrag(-20)));
	}
}

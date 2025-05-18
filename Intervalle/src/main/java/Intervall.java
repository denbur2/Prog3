
//jede klasse A muss Comparable implementieren (A extends Comparable<A>)
public class Intervall<A extends Comparable<? super A>> {
    A untereGrenze;
    A obereGrenze;

    public Intervall(A untereGrenze, A obereGrenze) {
        this.untereGrenze = untereGrenze;
        this.obereGrenze = obereGrenze;
    }
    public A getUntereGrenze() {
        return untereGrenze;
    }
    public A getObereGrenze() {
        return obereGrenze;
    }
    public boolean isLeer() {
        if(obereGrenze.compareTo(untereGrenze) <= 0){
            return true;
        }
        return false;
    }
    public boolean enthaelt(A wert){
        if(wert.compareTo(untereGrenze)>=0 && wert.compareTo(obereGrenze)<=0){
            return true;
        }
        return false;
    }
    public <T extends A> Intervall<A> schnitt(Intervall<T> anders) {
        A schnittUntereGrenze = untereGrenze.compareTo((A) anders.getUntereGrenze()) > 0
                ? untereGrenze
                : anders.getUntereGrenze();
        A schnittObereGrenze = obereGrenze.compareTo((A) anders.getObereGrenze()) < 0
                ? obereGrenze
                : anders.getObereGrenze();
        return new Intervall<>(schnittUntereGrenze, schnittObereGrenze);
    }



}
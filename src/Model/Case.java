package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lfbarreto on 02/02/16.
 */
public class Case implements Comparable<Case>{

    private List<Chemin> chemins;

    public double minDistance = Double.POSITIVE_INFINITY;
    public Case previous=null;

    private int i,j;

    public Case(int i,int j){
        this.i=i;
        this.j=j;
        chemins = new ArrayList<Chemin>();
    }

    public List<Chemin> getChemins() {
        return chemins;
    }

    public boolean removeChemin(Case target){
        Chemin c = new Chemin(target);
        return chemins.remove(c);
    }

    public void addChemin(Case target){
        chemins.add(new Chemin(target));
    }

    public void resetChemins(){
        chemins.clear();
    }
    public void reset() {
        this.minDistance = Double.POSITIVE_INFINITY;
        this.previous = null;
    }

    public void setChemins(List<Chemin> chemins) {
        this.chemins = chemins;
    }

    public int getI() {
        return this.i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return this.j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public Case getPrevious() {
        return previous;
    }

    public void setPrevious(Case previous) {
        this.previous = previous;
    }

    @Override
    public int compareTo(Case other)
    {
        return Double.compare(minDistance, other.minDistance);
    }

    public boolean equals(Object obj){
        if(obj == null)return false;
        if(obj == this)return true;
        if(!(obj instanceof Case ))return false;
        return (((Case) obj).getI()==this.i && ((Case) obj).getJ()==this.j );
    }
}

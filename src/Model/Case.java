package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lfbarreto on 02/02/16.
 */
public class Case {

    private List<Chemin> chemins;

    private int i,j;

    public Case(int i,int j,int size){
        this.i=i;
        this.j=j;
        chemins = new ArrayList<Chemin>();
        if(i>0)chemins.add(new Chemin(this.i,this.j,i-1,j));
        if(i<size-1)chemins.add(new Chemin(this.i,this.j,i+1,j));
        if(j>0)chemins.add(new Chemin(this.i,this.j,i,j-1));
        if(j<size-1)chemins.add(new Chemin(this.i,this.j,i,j+1));
    }

    public List<Chemin> getChemins() {
        return chemins;
    }

    public void resetChemins(int size){
        chemins.clear();
        if(i>0)chemins.add(new Chemin(this.i,this.j,i-1,j));
        if(i<size-1)chemins.add(new Chemin(this.i,this.j,i+1,j));
        if(j>0)chemins.add(new Chemin(this.i,this.j,i,j-1));
        if(j<size-1)chemins.add(new Chemin(this.i,this.j,i,j+1));
    }

    public void setChemins(List<Chemin> chemins) {
        this.chemins = chemins;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}

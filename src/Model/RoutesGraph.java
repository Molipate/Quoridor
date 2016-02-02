package Model;

import java.util.List;

/**
 * Created by lfbarreto on 02/02/16.
 */
public class RoutesGraph {

    private int size;
    private Case[][] cases;

    public RoutesGraph(int size){
        this.size=size;
        cases=new Case[size][size];

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                cases[i][j]=new Case(i,j,size);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Case getCase(int i,int j){
        return this.cases[i][j];
    }

    public boolean disablePath( int i1, int j1, int i2, int j2 ){
         return (this.cases[i1][j1].getChemins().remove(new Chemin(i1,j1,i2,j2))&&
                 this.cases[i2][j2].getChemins().remove(new Chemin(i2,j2,i1,j1)));
    }

    public void resetChemins(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                cases[i][j].resetChemins(size);
            }
        }
    }

    public Case[][] getCases() {
        return cases;
    }

    public void setCases(Case[][] cases) {
        this.cases = cases;
    }
}

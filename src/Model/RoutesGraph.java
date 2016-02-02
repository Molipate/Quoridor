package Model;

import java.lang.reflect.Array;
import java.util.*;

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
        System.out.println("#00-"+cases[0][0].getChemins().size());
        System.out.println("#33-"+cases[3][3].getChemins().size());
        System.out.println("#88-"+cases[8][8].getChemins().size());
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
        return (cases[i1][j1].removeChemin(i2,j2) && cases[i2][j2].removeChemin(i1,j1));
    }

    public void enablePath(int i1, int j1, int i2, int j2 ){

        this.cases[i1][j1].addChemin(i2,j2);
        this.cases[i2][j2].addChemin(i1,j1);
    }

    public void resetChemins(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                cases[i][j].resetChemins(size);
            }
        }
    }

    public boolean findChemin(int i1,int j1,int i2,int j2){
            if (i1==i2 && j1==j2)
                return true;

            boolean[][] visited = new boolean[size][size];
            for (int i = 0; i < size; i++)
                Arrays.fill(visited[i],false);

            // Create a queue for BFS
            Queue<Case> queue = new ArrayDeque<Case>();

            // Mark the current node as visited and enqueue it
            visited[i1][j1] = true;
            queue.offer(cases[i1][j1]);

            for(Chemin c:cases[i1][j1].getChemins()) {
                if(c.getJ2()==j2 && c.getI2()==i2)return true;
                if(!visited[c.getI2()][c.getJ2()]) {
                    queue.offer(cases[c.getI2()][c.getJ2()]);
                    visited[c.getI2()][c.getJ2()]=true;
                }
            }

            Case cas;
            while (!queue.isEmpty())
            {
                // Dequeue a vertex from queue and print it
                cas = queue.poll();
                for(Chemin c:cas.getChemins()) {
                    if(c.getJ2()==j2 && c.getI2()==i2)return true;
                    if(!visited[c.getI2()][c.getJ2()]) {
                        queue.offer(cases[c.getI2()][c.getJ2()]);
                        visited[c.getI2()][c.getJ2()]=true;
                    }
                }
            }

            return false;

    }
    public Case[][] getCases() {
        return cases;
    }

    public void setCases(Case[][] cases) {
        this.cases = cases;
    }
}

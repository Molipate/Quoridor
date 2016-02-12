package Model;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by lfbarreto on 02/02/16.
 */
public class RoutesGraph {

    private int size;
    private Case[][] cases;
    private double score=Double.NEGATIVE_INFINITY;
    private RoutesGraph parent=null;
    private List<RoutesGraph> childs = new ArrayList<RoutesGraph>();
    private int IJ1,JJ1,IJ2,JJ2;

    public RoutesGraph(int size){
        this.size=size;

        cases=new Case[size][size];

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                cases[i][j]=new Case(i,j);
            }
        }


    }

    public RoutesGraph(Case[][] cases, int ij1,int jj1,int ij2,int jj2){
        this.IJ1 = ij1;
        this.JJ1 = jj1;
        this.IJ2 = ij2;
        this.JJ2 = jj2;
        this.cases=cases;
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
        return (cases[i1][j1].removeChemin(cases[i2][j2]) && cases[i2][j2].removeChemin(cases[i1][j1]));
    }

    public boolean placeWall(Case c1,Case c2){
        return (c2.removeChemin(c1) && c1.removeChemin(c2));
    }

    public void enablePath(int i1, int j1, int i2, int j2 ){

        this.cases[i1][j1].addChemin(cases[i2][j2]);
        this.cases[i2][j2].addChemin(cases[i1][j1]);
    }

    public void resetChemins(){
        this.IJ1 = 0;
        this.JJ1 = 3;
        this.IJ2 = 7;
        this.JJ2 = 3;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                this.cases[i][j].resetChemins();
                if(i>0)this.cases[i][j].addChemin(this.cases[i-1][j]);
                if(i<size-1)this.cases[i][j].addChemin(this.cases[i+1][j]);
                if(j>0)this.cases[i][j].addChemin(this.cases[i][j-1]);
                if(j<size-1)this.cases[i][j].addChemin(this.cases[i][j+1]);
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
                if(c.target.getI()==i2 && c.target.getJ()==j2){
                    return true;
                }
                if(!visited[c.target.getI()][c.target.getJ()]) {
                    queue.offer(c.target);
                    visited[c.target.getI()][c.target.getJ()]=true;
                }
            }

            Case cas;
            while (!queue.isEmpty())
            {
                // Dequeue a vertex from queue and print it
                cas = queue.poll();
                for(Chemin c:cas.getChemins()) {
                    if(c.target.getI()==i2 && c.target.getJ()==j2){
                        return true;
                    }
                    if(!visited[c.target.getI()][c.target.getJ()]) {
                        queue.offer(c.target);
                        visited[c.target.getI()][c.target.getJ()]=true;
                    }
                }
            }

            return false;

    }



    /**
     * dikjstra init distances
     * @param source
     */
    public void computePaths(Case source)
    {
        source.minDistance = 0.;
        PriorityQueue<Case> CaseQueue = new PriorityQueue<Case>();
        CaseQueue.add(source);

        while (!CaseQueue.isEmpty()) {
            Case u = CaseQueue.poll();

            // Visit each edge exiting u
            for (Chemin e : u.getChemins())
            {
                Case v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance) {
                    CaseQueue.remove(v);

                    v.minDistance = distanceThroughU ;
                    v.previous = u;
                    CaseQueue.add(v);
                }
            }
        }
    }


    /**
     * dijkstra get path
     * @param target
     * @return
     */
    public List<Case> getShortestPathTo(Case target)
    {
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                System.out.print("["+cases[i][j].minDistance+"]");
            }
            System.out.println();
        }
        List<Case> path = new ArrayList<Case>();
        for (Case cas = target; cas != null; cas = cas.previous){
            System.out.println("["+cas.getI()+"-"+cas.getJ()+"]");
            path.add(cas);

        }

        Collections.reverse(path);

            System.out.println(path);


        return path;
    }

    public void resetAllCases() {
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++){
                cases[i][j].reset();
            }
        }
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setCases(Case[][] cases) {
        this.cases = cases;
    }

    public Case[][] getCases() {
        return cases;
    }

    public List<RoutesGraph> getChilds() {
        return childs;
    }

    public void setChilds(List<RoutesGraph> childs) {
        this.childs = childs;
    }

    public void addChild(RoutesGraph routesgraph){
        routesgraph.setParent(this);
        this.childs.add(routesgraph);
    }

    public void destroyTree(){
        this.parent=null;
        for(RoutesGraph r:this.childs){
            r.destroyTree();
        }
        this.childs.clear();
    }

    public RoutesGraph getParent() {
        return parent;
    }

    public void setParent(RoutesGraph parent) {
        this.parent = parent;
    }

    public int getIJ1() {
        return IJ1;
    }

    public void setIJ1(int IJ1) {
        this.IJ1 = IJ1;
    }

    public int getJJ1() {
        return JJ1;
    }

    public void setJJ1(int JJ1) {
        this.JJ1 = JJ1;
    }

    public int getIJ2() {
        return IJ2;
    }

    public void setIJ2(int IJ2) {
        this.IJ2 = IJ2;
    }

    public int getJJ2() {
        return JJ2;
    }

    public void setJJ2(int JJ2) {
        this.JJ2 = JJ2;
    }

}

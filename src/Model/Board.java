package Model;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import sun.security.provider.certpath.Vertex;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by molipate on 17/12/15.
 */
public class Board {

    private Model model;

    private int[][] plateau;
    private boolean[][] moves_Available,walls_Available;
    public final static int SIZE=17,UP=1,DOWN=2,LEFT=3,RIGHT=4,VERTICAL=1,HORIZONTAL=2;
    private static int J1X,J1Y,J2X,J2Y;
    private RoutesGraph routegraph;

    public Board(Model m){
        this.model=m;
        this.plateau = new int[SIZE][SIZE];
        moves_Available= new boolean[SIZE][SIZE];
        walls_Available= new boolean[SIZE][SIZE];

        routegraph = new RoutesGraph(SIZE);

    }

    public void initPlateau() {
        //vide le tableau et init à 0
        for(int i=0;i<SIZE;i++) Arrays.fill(plateau[i],0);
        //place les pions j1 et j2
        J1Y=0;
        J1X=(SIZE-1)/2;
        J2Y=SIZE-1;
        J2X=(SIZE-1)/2;
        plateau[J1Y][J1X]=model.J1;
        plateau[J2Y][J2X]=model.J2;
        routegraph.resetChemins();
    }

    /**
     * move the player if possible or return false
     * @param joueur
     * @param i j
     * @return
     */
    public boolean move_Player(int joueur,int i,int j){
        if(joueur==model.J1){
            plateau[J1Y][J1X]=0;
            J1X=j;
            J1Y=i;
            plateau[i][j]=1;
            System.out.println(i+"/"+j);
            return true;
        }
        if(joueur==model.J2){
            plateau[J2Y][J2X]=0;
            J2X=j;
            J2Y=i;
            plateau[i][j]=2;
            return true;
        }
        return false;
    }

    public void verifWin(){
        if(J2Y==0)model.setWin(true);
        if(J1Y==SIZE-1)model.setWin(true);
    }

    public int getActualPlayerI(){
        if(model.getActive_Player()==model.J1)return J1Y;
        else return J2Y;
    }

    public int getActualPlayerJ(){
        if(model.getActive_Player()==model.J1)return J1X;
        else return J2X;
    }

    /**
     * return the moves possibles
     * @param Joueur
     * @return
     */
    public boolean[][] getMovePossibles(int Joueur){
        for(int i=0;i<SIZE;i++)Arrays.fill(moves_Available[i],false);
        if(Joueur==model.J1){

            if(J1X-2>=0)moves_Available[J1Y][J1X-2]=isMovePossible(J1Y,J1X,LEFT);
            if(J1X+2<SIZE)moves_Available[J1Y][J1X+2]=isMovePossible(J1Y,J1X,RIGHT);
            if(J1Y-2>=0)moves_Available[J1Y-2][J1X]=isMovePossible(J1Y,J1X,UP);
            if(J1Y+2<SIZE)moves_Available[J1Y+2][J1X]=isMovePossible(J1Y,J1X,DOWN);
        }
        if(Joueur==model.J2){
            if(J2X-2>=0)moves_Available[J2Y][J2X-2]=isMovePossible(J2Y,J2X,LEFT);
            if(J2X+2<SIZE)moves_Available[J2Y][J2X+2]=isMovePossible(J2Y,J2X,RIGHT);
            if(J2Y-2>=0)moves_Available[J2Y-2][J2X]=isMovePossible(J2Y,J2X,UP);
            if(J2Y+2<SIZE)moves_Available[J2Y+2][J2X]=isMovePossible(J2Y,J2X,DOWN);
        }
        return moves_Available;
    }

    /**
     * true if move is possible
     * @param i
     * @param j
     * @param direction
     * @return
     */
    public boolean isMovePossible(int i,int j,int direction){
        switch(direction){
            case UP:
                System.out.println("mPU");
                return(i-2>=0 && plateau[i-2][j]==0 && plateau[i-1][j]<model.WALLH);
            case DOWN:
                System.out.println("mPD");
                return(i+2<SIZE && plateau[i+2][j]==0 && plateau[i+1][j]<model.WALLH);
            case LEFT:
                System.out.println("mPL");
                return(j-2>=0 && plateau[i][j-2]==0 && plateau[i][j-1]<model.WALLH);
            case RIGHT:
                System.out.println("mPR");
                return(j+2<SIZE && plateau[i][j+2]==0 && plateau[i][j+1]<model.WALLH);
            default:
                return false;
        }
    }

    /**
     * return the possible placement for walls
     * @param orientation
     * @return
     */
    public boolean[][] getWallPossibles(int orientation){

        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                walls_Available[i][j]=false;
            }
        }
        for(int i=1;i<SIZE-1;i+=2){
            for(int j=1;j<SIZE-1;j+=2){
                if(plateau[i][j]<model.WALLH){

                    walls_Available[i][j]=((orientation==HORIZONTAL && plateau[i][j-1]<model.WALLH && plateau[i][j+1]<model.WALLH)||
                                           (orientation==VERTICAL && plateau[i-1][j]<model.WALLH && plateau[i+1][j]<model.WALLH));
                    if(walls_Available[i][j])walls_Available[i][j]=wallNotBlocking(i,j,orientation);
                }
            }

        }
        return walls_Available;
    }

    public boolean wallNotBlocking(int i,int j,int orientation){
        boolean ret=false;
        if(orientation==HORIZONTAL){
            plateau[i][j-1]=model.WALLH;
            plateau[i][j]=model.WALLH;
            plateau[i][j+1]=model.WALLH;
        }else if(orientation==VERTICAL){
            plateau[i-1][j]=model.WALLV;
            plateau[i][j]=model.WALLV;
            plateau[i+1][j]=model.WALLV;
        }

            ret = findPath();

        if(orientation==HORIZONTAL){
            plateau[i][j-1]=0;
            plateau[i][j]=0;
            plateau[i][j+1]=0;
        }else if(orientation==VERTICAL){
            plateau[i-1][j]=0;
            plateau[i][j]=0;
            plateau[i+1][j]=0;
        }

        return ret;
    }

    public boolean findPath(){

        return true;
    }


    /**
     * place Wall on board
     * @param i
     * @param j
     * @param orientation
     * @return
     */
    public boolean placeWall(int i,int j,int orientation){
        int iw=i/2,jw=j/2;
        if((i&1)==0 && (j&1)==0 && i-1<0 && i+1>SIZE && j-1<0 && j+1>SIZE )return false;
        if((orientation==HORIZONTAL && plateau[i][j-1]<model.WALLH && plateau[i][j+1]<model.WALLH)||
           (orientation==VERTICAL && plateau[i-1][j]<model.WALLH && plateau[i+1][j]<model.WALLH)){
            switch(orientation){
                case VERTICAL:
                    plateau[i-1][j]=model.WALLV;
                    plateau[i][j]=model.WALLV;
                    plateau[i+1][j]=model.WALLV;

                    System.out.println(routegraph.disablePath(iw,jw,iw,jw+1));
                    System.out.println(routegraph.disablePath(iw+1,jw,iw+1,jw+1));

                    return true;
                case HORIZONTAL:
                    plateau[i][j-1]=model.WALLH;
                    plateau[i][j]=model.WALLH;
                    plateau[i][j+1]=model.WALLH;

                    System.out.println(routegraph.disablePath(iw,jw,iw+1,jw));
                    System.out.println(routegraph.disablePath(iw,jw+1,iw+1,jw+1));
                    return true;
            }
        }
        return false;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int[][] getPlateau() {
        return plateau;
    }

    public void setPlateau(int[][] plateau) {
        this.plateau = plateau;
    }


    /**
     * return the players board
     * @param i
     * @param j
     * @return
     */
    public int getPlateau(int i,int j) {
        return plateau[i][j];
    }

    public boolean[][] getMoves_Available() {
        return moves_Available;
    }

    public void setMoves_Available(boolean[][] moves_Available) {
        this.moves_Available = moves_Available;
    }

    public boolean[][] getWalls_Available() {
        return walls_Available;
    }

    public void setWalls_Available(boolean[][] walls_Available) {
        this.walls_Available = walls_Available;
    }

    public static int getSIZE() {
        return SIZE;
    }


    public static int getUP() {
        return UP;
    }

    public static int getDOWN() {
        return DOWN;
    }

    public static int getLEFT() {
        return LEFT;
    }

    public static int getRIGHT() {
        return RIGHT;
    }

    public static int getVERTICAL() {
        return VERTICAL;
    }

    public static int getHORIZONTAL() {
        return HORIZONTAL;
    }

    public static int getJ1X() {
        return J1X;
    }

    public static void setJ1X(int j1X) {
        J1X = j1X;
    }

    public static int getJ1Y() {
        return J1Y;
    }

    public static void setJ1Y(int j1Y) {
        J1Y = j1Y;
    }

    public static int getJ2X() {
        return J2X;
    }

    public static void setJ2X(int j2X) {
        J2X = j2X;
    }

    public static int getJ2Y() {
        return J2Y;
    }

    public static void setJ2Y(int j2Y) {
        J2Y = j2Y;
    }

    public int getWall(int i, int j) {
        if(plateau[i][j]==model.WALLV)
            return VERTICAL;
        if(plateau[i][j]==model.WALLH)
            return HORIZONTAL;
        return 0;
    }

    public boolean isWallFree(int i, int j) {
        return walls_Available[i][j];
    }

    public boolean isMoveFree(int i, int j) {
        return  moves_Available[i][j];
    }

    public void resetMoves(){
        for(int i=0;i<SIZE;i++)Arrays.fill(this.moves_Available[i],false);
    }
    public void resetWalls(){
        for(int i=0;i<SIZE;i++)Arrays.fill(this.walls_Available[i],false);
    }

    public void print(){
        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++)
                System.out.print("["+plateau[i][j]+"]");
            System.out.println();
        }

    }
}

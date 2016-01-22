package Model;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by molipate on 17/12/15.
 */
public class Board {

    private Model model;

    private int[][] plateau;
    private boolean[][] moves_Available,walls_Available;
    private final static int SIZE=17,SIZE_W=8,SIZE_P=9,UP=1,DOWN=2,LEFT=3,RIGHT=4,VERTICAL=1,HORIZONTAL=2;
    private static int J1X,J1Y,J2X,J2Y;


    public Board(Model m){
        this.model=m;
        this.plateau = new int[SIZE][SIZE];
        moves_Available= new boolean[SIZE_P][SIZE_P];
        walls_Available= new boolean[SIZE_W][SIZE_W];
    }

    public void initPlateau() {
        //vide le tableau et init à 0
        for(int i=0;i<SIZE;i++) Arrays.fill(plateau[i],0);
        //place les pions j1 et j2
        plateau[0][(SIZE-1)/2]=model.J1;
        plateau[SIZE-1][(SIZE-1)/2]=model.J2;
        J1Y=0;
        J1X=9;
        J2Y=9;
        J2X=9;
    }

    /**
     * move the player if possible or return false
     * @param joueur
     * @param direction
     * @return
     */
    public boolean move_Player(int joueur,int direction){
        if(joueur==model.J1){
            switch(direction){
                case UP:
                    if(isMovePossible(J1X,J1Y,UP)){
                        plateau[J1Y][J1X]=0;
                        plateau[J1Y-2][J1X]=model.J1;
                        J1Y-=2;
                        return true;
                    }
                case DOWN:
                    if(isMovePossible(J1X,J1Y,DOWN)){
                        plateau[J1Y][J1X]=0;
                        plateau[J1Y+2][J1X]=model.J1;
                        J1Y+=2;
                        return true;
                    }
                case LEFT:
                    if(isMovePossible(J1X,J1Y,LEFT)){
                        plateau[J1Y][J1X]=0;
                        plateau[J1Y][J1X-2]=model.J1;
                        J1X-=2;
                        return true;
                    }
                case RIGHT:
                    if(isMovePossible(J1X,J1Y,RIGHT)){
                        plateau[J1Y][J1X]=0;
                        plateau[J1Y][J1X+2]=model.J1;
                        J1X+=2;
                        return true;
                    }
                default:
                    return false;
            }
        }
        if(joueur==model.J2){
            switch(direction){
                case UP:
                    if(isMovePossible(J2X,J2Y,UP)){
                        plateau[J2Y][J2X]=0;
                        plateau[J2Y-2][J2X]=model.J2;
                        J2Y-=2;
                        return true;
                    }
                case DOWN:
                    if(isMovePossible(J2X,J2Y,DOWN)){
                        plateau[J2Y][J2X]=0;
                        plateau[J2Y+2][J2X]=model.J2;
                        J2Y+=2;
                        return true;
                    }
                case LEFT:
                    if(isMovePossible(J2X,J2Y,LEFT)){
                        plateau[J2Y][J2X]=0;
                        plateau[J2Y][J2X-2]=model.J2;
                        J2X-=2;
                        return true;
                    }
                case RIGHT:
                    if(isMovePossible(J2X,J2Y,RIGHT)){
                        plateau[J2Y][J2X]=0;
                        plateau[J2Y][J2X+2]=model.J2;
                        J2X+=2;
                        return true;
                    }
                default:
                    return false;
            }
        }
        return false;
    }

    /**
     * return the moves possibles
     * @param Joueur
     * @return
     */
    public boolean[][] getMovePossibles(int Joueur){
        for(int i=0;i<SIZE_P;i++)Arrays.fill(moves_Available[i],false);
        if(Joueur==model.J1){
            moves_Available[J1Y][J1X-2]=isMovePossible(J1Y,J1X,UP);
            moves_Available[J1Y][J1X+2]=isMovePossible(J1Y,J1X,DOWN);
            moves_Available[J1Y-2][J1X]=isMovePossible(J1Y,J1X,LEFT);
            moves_Available[J1Y+2][J1X]=isMovePossible(J1Y,J1X,RIGHT);
        }
        if(Joueur==model.J2){
            moves_Available[J2Y][J2X-2]=isMovePossible(J2Y,J2X,UP);
            moves_Available[J2Y][J2X+2]=isMovePossible(J2Y,J2X,DOWN);
            moves_Available[J2Y-2][J2X]=isMovePossible(J2Y,J2X,LEFT);
            moves_Available[J2Y+2][J2X]=isMovePossible(J2Y,J2X,RIGHT);
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
                return(i-2>0 && plateau[i-2][j]==0 && plateau[i-1][j]!=model.WALL);
            case DOWN:
                return(i+2<SIZE && plateau[i+2][j]==0 && plateau[i+1][j]!=model.WALL);
            case LEFT:
                return(j-2>0 && plateau[i][j-2]==0 && plateau[i][j-1]!=model.WALL);
            case RIGHT:
                return(j+2<SIZE && plateau[i][j+2]==0 && plateau[i][j+1]!=model.WALL);
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
        for(int i=0;i<SIZE_W;i++)Arrays.fill(walls_Available[i],false);
        for(int i=1;i<SIZE-1;i+=2){
            for(int j=1;j<SIZE-1;j+=2){
                if(plateau[i][j]!=model.WALL){
                    walls_Available[i][j]=((orientation==HORIZONTAL && plateau[i][j-1]!=model.WALL && plateau[i][j+1]!=model.WALL)||
                                           (orientation==VERTICAL && plateau[i-1][j]!=model.WALL && plateau[i+1][j]!=model.WALL));
                }
            }
        }
        return walls_Available;
    }

    /**
     * place Wall on board
     * @param i
     * @param j
     * @param orientation
     * @return
     */
    public boolean placeWall(int i,int j,int orientation){
        if(i%2==0 && j%2==0 && i-1<0 && i+1>SIZE && j-1<0 && j+1>SIZE)return false;
        if((orientation==HORIZONTAL && plateau[i][j-1]!=model.WALL && plateau[i][j+1]!=model.WALL)||
           (orientation==VERTICAL && plateau[i-1][j]!=model.WALL && plateau[i+1][j]!=model.WALL)){
            switch(orientation){
                case VERTICAL:
                    plateau[i-1][j]=model.WALL;
                    plateau[i][j]=model.WALL;
                    plateau[i+1][j]=model.WALL;
                    return true;
                case HORIZONTAL:
                    plateau[i][j-1]=model.WALL;
                    plateau[i][j]=model.WALL;
                    plateau[i][j+1]=model.WALL;
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

        return plateau[i*2][j*2];
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

    public static int getSizeW() {
        return SIZE_W;
    }

    public static int getSizeP() {
        return SIZE_P;
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
        if(i-1>0 && i+1<SIZE && plateau[i][j]==model.WALL && plateau[i-1][j]==model.WALL && plateau[i+1][j]==model.WALL)
            return VERTICAL;
        if(j-1>0 && j+1<SIZE && plateau[i][j]==model.WALL && plateau[i][j-1]==model.WALL && plateau[i][j+1]==model.WALL)
            return HORIZONTAL;
        return 0;
    }

    public boolean isWallFree(int i, int j) {
        return walls_Available[i][j];
    }

    public boolean isMoveFree(int i, int j) {
        return  moves_Available[i][j];
    }
}

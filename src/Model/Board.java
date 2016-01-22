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
    private final static int SIZE=17,UP=1,DOWN=2,LEFT=3,RIGHT=4;
    private static int J1X,J1Y,J2X,J2Y;


    public Board(Model m){
        this.model=m;
        this.plateau = new int[SIZE][SIZE];
    }

    public void initPlateau() {
        //vide le tableau et init à 0
        for(int i=0;i<SIZE;i++) Arrays.fill(plateau[i],0);
        //place les pions j1 et j2
        plateau[0][9]=model.J1;
        plateau[9][9]=model.J2;
        J1Y=0;
        J1X=9;
        J2Y=9;
        J2X=9;
    }

    public boolean placeVerticalWall(int i,int j){
        if(i-1<0 || i+1>=SIZE || i%2==0 || j%2==0)return false;
        if(plateau[i][j]==model.WALL || plateau[i-1][j]==model.WALL || plateau[i+1][j]==model.WALL)return false;
        plateau[i-1][j]=model.WALL;
        plateau[i][j]=model.WALL;
        plateau[i+1][j]=model.WALL;
        return true;
    }

    public boolean placeHorizontalWall(int i,int j){
        if(j-1<0 || j+1>=SIZE || i%2==0 || j%2==0)return false;
        if(plateau[i][j]==model.WALL || plateau[i][j-1]==model.WALL || plateau[i][j+1]==model.WALL)return false;
        plateau[i][j-1]=model.WALL;
        plateau[i][j]=model.WALL;
        plateau[i][j+1]=model.WALL;
        return true;
    }

    public boolean move_Player(int joueur,int direction){
        if(joueur==model.J1){
            switch(direction){
                case UP:
                    if(J1Y-2<0 || plateau[J1Y-1][J1X]==model.WALL || plateau[J1Y-2][J1X]==model.J2)return false;
                    plateau[J1Y][J1X]=0;
                    plateau[J1Y-2][J1X]=model.J1;
                    J1Y-=2;
                    return true;
                case DOWN:
                    if(J1Y+2<0 || plateau[J1Y+1][J1X]==model.WALL || plateau[J1Y+2][J1X]==model.J2)return false;
                    plateau[J1Y][J1X]=0;
                    plateau[J1Y+2][J1X]=model.J1;
                    J1Y+=2;
                    return true;
                case LEFT:
                    if(J1X-2<0 || plateau[J1Y][J1X-1]==model.WALL || plateau[J1Y][J1X-2]==model.J2)return false;
                    plateau[J1Y][J1X]=0;
                    plateau[J1Y][J1X-2]=model.J1;
                    J1X-=2;
                    return true;
                case RIGHT:
                    if(J1X+2<0 || plateau[J1Y][J1X+1]==model.WALL || plateau[J1Y][J1X+2]==model.J2)return false;
                    plateau[J1Y][J1X]=0;
                    plateau[J1Y][J1X+2]=model.J1;
                    J1X+=2;
                    return true;
                default:
                    return false;
            }
        }
        if(joueur==model.J2){
            switch(direction){
                case UP:
                    if(J2Y-2<0 || plateau[J2Y-1][J2X]==model.WALL || plateau[J2Y-2][J2X]==model.J1)return false;
                    plateau[J2Y][J2X]=0;
                    plateau[J2Y-2][J2X]=model.J2;
                    J2Y-=2;
                    return true;
                case DOWN:
                    if(J2Y+2<0 || plateau[J2Y+1][J2X]==model.WALL || plateau[J2Y+2][J2X]==model.J1)return false;
                    plateau[J2Y][J2X]=0;
                    plateau[J2Y+2][J2X]=model.J2;
                    J2Y+=2;
                    return true;
                case LEFT:
                    if(J2X-2<0 || plateau[J2Y][J2X-1]==model.WALL || plateau[J2Y][J2X-2]==model.J1)return false;
                    plateau[J2Y][J2X]=0;
                    plateau[J2Y][J2X-2]=model.J2;
                    J2X-=2;
                    return true;
                case RIGHT:
                    if(J2X+2<0 || plateau[J2Y][J2X+1]==model.WALL || plateau[J2Y][J2X+2]==model.J1)return false;
                    plateau[J2Y][J2X]=0;
                    plateau[J2Y][J2X+2]=model.J2;
                    J2X+=2;
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }

    public boolean[][] getMovePossibles(int Joueur){
        for(int i=0;i<SIZE;i++)Arrays.fill(moves_Available[i],false);
        if(Joueur==model.J1){
            if(J1X-2>0)moves_Available[J1Y][J1X-2]=(J1X-2>0 && plateau[J1Y][J1X-1]!=model.WALL);
            if(J1Y+2>SIZE)moves_Available[J1Y][J1X+2]=(J1X+2>0 && plateau[J1Y][J1X+1]!=model.WALL);
            if(J1X-2>0)moves_Available[J1Y-2][J1X]=(J1Y-2>0 && plateau[J1Y-1][J1X]!=model.WALL);
            if(J1Y+2<SIZE)moves_Available[J1Y+2][J1X]=(J1Y-2>0 && plateau[J1Y+1][J1X]!=model.WALL);
        }
        if(Joueur==model.J2){
            if(J2X-2>0)moves_Available[J2Y][J2X-2]=(J2X-2>0 && plateau[J2Y][J2X-1]!=model.WALL);
            if(J2X+2<SIZE)moves_Available[J2Y][J2X+2]=(J2X+2>0 && plateau[J2Y][J2X+1]!=model.WALL);
            if(J2Y-2>0)moves_Available[J2Y-2][J2X]=(J2Y-2>0 && plateau[J2Y-1][J2X]!=model.WALL);
            if(J2Y+2<SIZE)moves_Available[J2Y+2][J2X]=(J2Y-2>0 && plateau[J2Y+1][J2X]!=model.WALL);
        }
        return moves_Available;
    }

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

    public boolean[][] getWallPossibles(){
        for(int i=0;i<SIZE;i++)Arrays.fill(walls_Available[i],false);

    }

}

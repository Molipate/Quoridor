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
        plateau[0][9]=model.J1;
        plateau[9][9]=model.J2;
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
            moves_Available[J1Y][J1X-2]=(J1X-2>0 && J1X-2>0 && plateau[J1Y][J1X-1]!=model.WALL);
            moves_Available[J1Y][J1X+2]=(J1Y+2>SIZE && J1X+2>0 && plateau[J1Y][J1X+1]!=model.WALL);
            moves_Available[J1Y-2][J1X]=(J1X-2>0 && J1Y-2>0 && plateau[J1Y-1][J1X]!=model.WALL);
            moves_Available[J1Y+2][J1X]=(J1Y+2<SIZE && J1Y-2>0 && plateau[J1Y+1][J1X]!=model.WALL);
        }
        if(Joueur==model.J2){
            moves_Available[J2Y][J2X-2]=(J2X-2>0 && J2X-2>0 && plateau[J2Y][J2X-1]!=model.WALL);
            moves_Available[J2Y][J2X+2]=(J2X+2<SIZE && J2X+2>0 && plateau[J2Y][J2X+1]!=model.WALL);
            moves_Available[J2Y-2][J2X]=(J2Y-2>0 && J2Y-2>0 && plateau[J2Y-1][J2X]!=model.WALL);
            moves_Available[J2Y+2][J2X]=(J2Y+2<SIZE && J2Y-2>0 && plateau[J2Y+1][J2X]!=model.WALL);
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
}

package View;

import Control.ControlBoard;
import Control.ControlInterface;
import Control.ControlWall;
import Model.Model;

import javax.swing.*;
import java.awt.*;

/**
 * Created by molipate on 07/12/15.
 */
public class View extends JFrame{

    private Model model;
    private JPanel panel;

    // JButton for menu
    private JButton newGame;
    private JButton quitGame;

    private JButton[][] plateau;

    private JButton verticalWall;
    private JButton horizontalWall;

    private JLabel activePlayer;
    private JLabel remainingWall;

    public View(Model model) {
        super();
        setSize(new Dimension(900, 700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.model = model;
        panel = new JPanel();

        plateau = new JButton[17][17];
        for (int i = 0; i < 17; i++)
            for (int j = 0; j < 17; j++)
                plateau[i][j] = new JButton();

        initView();
        makeView();
    }

    public void display(boolean a){
        setVisible(a);
    }

    private void initView(){

        newGame = new JButton("Nouvelle partie");
        quitGame = new JButton("Quitter le jeu");

        newGame.setContentAreaFilled(false);
        newGame.setFocusPainted(false);
        quitGame.setContentAreaFilled(false);
        quitGame.setFocusPainted(false);

        newGame.setFont(new Font("Arial",1,30));
        quitGame.setFont(new Font("Arial",1,30));

        verticalWall = new JButton();
        horizontalWall = new JButton();

        activePlayer = new JLabel();
        remainingWall = new JLabel();
        activePlayer.setFont(new Font("Arial",1,15));
        remainingWall.setFont(new Font("Arial",1,15));

        verticalWall.setBounds(701,264,14,94);
        verticalWall.setIcon(model.getAssetsImage().getWallV());
        verticalWall.setBorder(BorderFactory.createEmptyBorder());
        horizontalWall.setBounds(715, 250,94,14);
        horizontalWall.setIcon(model.getAssetsImage().getWallH());
        horizontalWall.setBorder(BorderFactory.createEmptyBorder());
    }

    public void makeView(){
        System.out.println("-> Painting ...");
        if(model.getGameState() == 0){
            panel.setLayout(new GridLayout(2, 1));
            panel.add(newGame);
            panel.add(quitGame);
        }
        else if(model.getGameState() == 1){

            panel.setLayout(null);
            panel.removeAll();
            for (int i = 0,y=0; i < 17 && y<9; i+=2,y++) {
                for (int j = 0,x=0; j < 17 && x<9; j+=2,x++) {
                    panel.add(plateau[i][j]);
                    plateau[i][j].setSize(new Dimension(40, 40));
                    plateau[i][j].setBounds(x*54+200,y*54+100, 40, 40);
                    plateau[i][j].setContentAreaFilled(false);
                    plateau[i][j].setIcon(model.getAssetsImage().getIcon());
                    plateau[i][j].setDisabledIcon(null);
                    plateau[i][j].setBorder(BorderFactory.createEmptyBorder());
                    switch (model.getBoard().getPlateau(i, j)){
                        case 1:
                            System.out.print("j1"+i+""+j);
                            plateau[i][j].setIcon(model.getAssetsImage().getIconJ1());
                            break;
                        case 2:
                            System.out.print("j2"+i+""+j);
                            plateau[i][j].setIcon(model.getAssetsImage().getIconJ2());
                            break;
                    }
                }
            }

            for (int i = 1,y=0; i < 16 && y<9; i+=2,y++) {
                for (int j = 1,x=0; j < 16 && x<9; j+=2 ,x++) {
                    panel.add(plateau[i][j]);
                    plateau[i][j].setEnabled(true);
                    plateau[i][j].setBounds((x + 1) * 40 + x * 14 + 200, (y + 1) * 40 + y * 14 + 100, 14, 14);
                    plateau[i][j].setContentAreaFilled(false);
                    plateau[i][j].setIcon(model.getAssetsImage().getIntersection());
                    plateau[i][j].setBorder(BorderFactory.createEmptyBorder());

                }
            }
            String couleurJoueur="";
            switch (model.getActive_Player()){
                case 1:couleurJoueur="Bleu";break;
                case 2:couleurJoueur="Rouge";break;
                case 3:couleurJoueur="Vert";break;
                case 4:couleurJoueur="Violet";break;
                default: return;
            }
            activePlayer.setSize(400, 200);
            activePlayer.setBackground(Color.black);

            activePlayer.setText("Joueur " + couleurJoueur + " : c'est à toi !");
            activePlayer.setBounds(250,600, 250, 50);

            remainingWall.setSize(400, 200);
            remainingWall.setBackground(Color.black);
            remainingWall.setText("Il te reste " + model.getNbWall() + " murs !");
            remainingWall.setBounds(500, 600, 250, 50);

            if(model.isWin()){
                activePlayer.setText("Joueur " + couleurJoueur + " : tu as gagné !");
            }

            panel.add(verticalWall);
            panel.add(horizontalWall);
            panel.add(activePlayer);
            panel.add(remainingWall);
            panel.add(newGame);
            newGame.setBounds(0,0,450,50);
            newGame.setBorder(BorderFactory.createEmptyBorder());
            panel.add(quitGame);
            quitGame.setBounds(450,0,450,50);
            quitGame.setBorder(BorderFactory.createEmptyBorder());

        }
        setContentPane(panel);
    }

    public void updateView(){

        String couleurJoueur="";
        switch (model.getActive_Player()){
            case 1:couleurJoueur="Bleu";break;
            case 2:couleurJoueur="Rouge";break;
            case 3:couleurJoueur="Vert";break;
            case 4:couleurJoueur="Violet";break;
            default: return;
        }
        activePlayer.setSize(400, 200);
        activePlayer.setBackground(Color.black);

        activePlayer.setText("Joueur " + couleurJoueur + " : c'est à toi !");
        activePlayer.setBounds(250,600, 250, 50);

        remainingWall.setSize(400, 200);
        remainingWall.setBackground(Color.black);
        remainingWall.setText("Il te reste " + model.getNbWall() + " murs !");
        remainingWall.setBounds(500, 600, 250, 50);

        if(model.isWin()){
            activePlayer.setText("Joueur " + couleurJoueur + " : tu as gagné !");
        }
    }

    public void updatePlayer(){
        for(int i=0;i<17;i+=2){
            for(int j=0;j<17 ;j+=2){
               switch (model.getBoard().getPlateau(i,j)){
                   case 1:
                       plateau[i][j].setIcon(model.getAssetsImage().getIconJ1());
                       break;
                   case 2:
                       plateau[i][j].setIcon(model.getAssetsImage().getIconJ2());
                       break;
                   default:
                       plateau[i][j].setIcon(model.getAssetsImage().getIcon());
               }
            }
        }
    }

    public void movesPossible(){
        for(int i=0;i<17;i+=2){
            for(int j=0;j<17;j+=2){
                if(model.getBoard().isMoveFree(i,j))
                    plateau[i][j].setIcon(model.getAssetsImage().getMovePossible());
            }
        }
    }

    public void updateWalls(){
        for(int i=1,y=0;i<16;i+=2,y++){
            for(int j=1,x=0;j<16;j+=2,x++){
                switch (model.getBoard().getWall(i, j)) {
                    case 1:
                        plateau[i][j].setEnabled(false);
                        plateau[i][j].setIcon(model.getAssetsImage().getWallV());
                        plateau[i][j].setDisabledIcon(model.getAssetsImage().getWallV());
                        plateau[i][j].setBounds((x + 1)*40 + x*14+200,  y*54+100,14,94);
                        break;
                    case 2:
                        plateau[i][j].setEnabled(false);
                        plateau[i][j].setIcon(model.getAssetsImage().getWallH());
                        plateau[i][j].setDisabledIcon(model.getAssetsImage().getWallH());
                        plateau[i][j].setBounds(x*54+200, (y + 1)*40 + y * 14+100,94,14);
                        break;
                }
                plateau[i][j].setBorder(BorderFactory.createEmptyBorder());
            }
        }
    }

    public void wallsPossible(){
        for(int i=1;i<16;i+=2){
            for(int j=1;j<16;j+=2){
                if (model.getBoard().isWallFree(i, j))
                    plateau[i][j].setBorder(BorderFactory.createLineBorder(Color.orange, 3));
                else
                    plateau[i][j].setBorder(BorderFactory.createEmptyBorder());
            }
        }
    }

    public void startNewGame(){
        model.resetGame();
        model.setGameState(1);
        makeView();
    }

    public void setActionListener(ControlInterface menuListener, ControlBoard boardListener, ControlWall wallListener ) {
        newGame.setActionCommand("NG");
        newGame.addActionListener(menuListener);
        quitGame.setActionCommand("QG");
        quitGame.addActionListener(menuListener);
        verticalWall.setActionCommand("V");
        horizontalWall.setActionCommand("H");
        verticalWall.addActionListener(wallListener);
        horizontalWall.addActionListener(wallListener);

        for (int i = 0; i < 17; i+=2)
            for (int j = 0; j < 17; j+=2)
            {
                plateau[i][j].setActionCommand(i+","+j);
                plateau[i][j].addActionListener(boardListener);
            }


        for (int i = 1; i < 16; i+=2)
            for (int j = 1; j < 16 ; j+=2){
                plateau[i][j].setActionCommand(i+","+j);
                plateau[i][j].addActionListener(wallListener);
            }

    }
    public void enableWalls(boolean b){
        for(int i=1;i<16;i+=2){
            for(int j=1;j<16;j+=2){
                plateau[i][j].setEnabled(b);
            }
        }
    }
    public void enableBoard(boolean b){
        for(int i=0;i<17;i+=2){
            for(int j=0;j<17;j+=2){
                plateau[i][j].setEnabled(b);
            }
        }
    }

    public void setWinDisplay(){
        if(model.getActive_Player()==model.getJ1())
            plateau[model.getBoard().getActualPlayerI()][model.getBoard().getActualPlayerJ()].setDisabledIcon(model.getAssetsImage().getIconJ1());
        else
            plateau[model.getBoard().getActualPlayerI()][model.getBoard().getActualPlayerJ()].setDisabledIcon(model.getAssetsImage().getIconJ2());

    }

    public JButton getNewGame(){
        return newGame;
    }

    public JButton getQuitGame(){
        return quitGame;
    }

    public JButton getPlateau(int i, int j) {
        return plateau[i][j];
    }


    public JButton getVerticalWall(){
        return verticalWall;
    }

    public JButton getHorizontalWall(){
        return horizontalWall;
    }

    public void leave() {
        dispose();
    }
}

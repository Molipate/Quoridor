package Control;

import Model.Model;
import View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lfbarreto on 02/02/16.
 */
public class ControlBoard extends Control implements ActionListener {

    private String[] action;
    private int i1,j1,i2,j2;
    private boolean click;
    private boolean[][] moves;
    public ControlBoard(Model model, View view){
        super(model,view);
        click=false;
        moves = new boolean[board.getSIZE()][board.getSIZE()];
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        action = actionEvent.getActionCommand().split(",");
        board.resetWalls();
        view.wallsPossible();
        if(!click){
            i1=Integer.parseInt(action[0]);
            j1=Integer.parseInt(action[1]);
            if(board.getPlateau(i1,j1)==model.getActive_Player() && !model.isWin()){
                moves = board.getMovePossibles(model.getActive_Player());
                view.updateView();
                view.movesPossible();
                click=true;
            }else{
                board.resetMoves();
                view.updateView();
                view.movesPossible();
            }

        }else{
            i2=Integer.parseInt(action[0]);
            j2=Integer.parseInt(action[1]);

            if(moves[i2][j2]){
                board.move_Player(model.getActive_Player(),i2,j2);
                board.verifWin();
                if(!model.isWin())model.switchPlayer();
                else{
                    view.setWinDisplay();
                    view.enableBoard(false);
                    view.enableWalls(false);
                }
            }
            board.resetMoves();
            view.updateView();
            view.movesPossible();
            view.updatePlayer();
            click=false;
        }
    }
}


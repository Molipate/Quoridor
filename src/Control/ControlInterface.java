package Control;

import Model.Model;
import View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lfbarreto on 02/02/16.
 */
public class ControlInterface extends Control implements ActionListener {

    private String action;
    public ControlInterface(Model model, View view){
        super(model,view);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        action = actionEvent.getActionCommand();
        if(action == "NG"){
            model.resetGame();
            view.makeView();
            view.enableBoard(true);
            view.enableWalls(true);
        }else if(action =="QG"){
            System.exit(0);
        }
    }
}


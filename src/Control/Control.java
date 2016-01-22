package Control;

import Model.Model;
import Model.Board;
import View.View;

/**
 * Created by molipate on 07/12/15.
 */
public class Control {

    protected Model model;
    protected View view;
    protected Board board;
    public Control(Model model, View view){
        this.model = model;
        this.view = view;
        board=model.getBoard();
    }
}

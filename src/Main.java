import Control.ControlGroup;
import Model.Model;
import View.View;

/**
 * Created by molipate on 07/12/15.
 */
public class Main {

    public static void main(String[] args){

        javax.swing.SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                Model model = new Model();
                View view = new View(model);
                ControlGroup control = new ControlGroup(model,view);
            }
        });
    }
}

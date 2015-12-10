import Control.ControlGroup;
import Model.Model;

/**
 * Created by molipate on 07/12/15.
 */
public class Main {

    public static void main(String[] args){

        javax.swing.SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                Model model = new Model();
                ControlGroup control = new ControlGroup(model);
            }
        });
    }
}

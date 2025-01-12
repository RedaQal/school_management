import controller.ApppController;
import model.School;
import view.AppView;

public class App {

    public static void main(String[] args) throws Exception {
        School school = new School();
        AppView view = new AppView();
        ApppController controller = new ApppController(school,view);
        controller.start();
    }
}
package MainPackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    /**
     * The function that sets up the starting window of the application.
     * @param stage The window of the application.
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root;
        root= FXMLLoader.load(getClass().getResource("resources/Welcome.fxml"));
        stage = new Stage();
        stage.setTitle("Buzz Quiz");
        stage.setScene(new Scene(root,600,400));
        stage.show();
    }

    /**
     * Calls the start function that starts the whole app.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }



}

package MainPackage;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;


public class Main extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Parent root;
        root= FXMLLoader.load(getClass().getResource("resources/Welcome.fxml"));
        stage = new Stage();
        stage.setTitle("New Game");
        stage.setScene(new Scene(root,600,400));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }



}

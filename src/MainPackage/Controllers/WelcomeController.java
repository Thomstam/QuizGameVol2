package MainPackage.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {

    public void goToMenu(MouseEvent mouseEvent) {
        Parent root;
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("../resources/GameOptions.fxml"));
            root=loader.load();
            Stage stage = new Stage();
            stage.setTitle("New Game");
            stage.setScene(new Scene(root,600,400));
            stage.show();
            ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

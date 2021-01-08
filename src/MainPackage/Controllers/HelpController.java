package MainPackage.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HelpController {

    private final Stage thisStage;

    public HelpController(){
        thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/Help.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Buzz Quiz");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showStage(){
        thisStage.showAndWait();
    }

    public void backToMenu(MouseEvent mouseEvent) {
        ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
    }
}

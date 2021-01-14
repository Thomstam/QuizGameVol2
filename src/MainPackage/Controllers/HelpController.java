package MainPackage.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class sets up the scene in which all the controls of each player will be displayed.
 */
public class HelpController {

    private final Stage thisStage;

    /***
     * Links the controller of the game options stage with the corresponding FXML file and then loads it
     */
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

    /***
     * Displays the current stage
     */
    public void showStage(){
        thisStage.showAndWait();
    }

    /***
     * When the mouse is clicked the current stage gets closed
     * @param mouseEvent click of the mouse
     */
    public void backToMenu(MouseEvent mouseEvent) {
        ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
    }
}

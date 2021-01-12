package MainPackage.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class DisplayCategoryController {

    @FXML
    private Label categoryName;
    private final Stage thisStage;
    private final String categoriesToAsk;

    /***
     * Links the controller of the game options stage with the corresponding FXML file and then loads it
     * @param categoriesToAsk the randomly picked category of question that is going to be asked
     */
    public DisplayCategoryController(String categoriesToAsk){
        thisStage=new Stage();
        this.categoriesToAsk=categoriesToAsk;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/DisplayCategory.fxml"));
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
    public void showStage(){thisStage.showAndWait();}

    /***
     * Sets the FXML label to the name of the category which was picked
     */
    @FXML
    private void initialize(){
        categoryName.setText("The category is: "+categoriesToAsk);
    }

    /***
     * When the mouse gets clicked this stage gets hidden
     * @param mouseEvent the click of the mouse
     */
    public void clickToContinue(MouseEvent mouseEvent) {

        ((Node)mouseEvent.getSource()).getScene().getWindow().hide();

    }
}

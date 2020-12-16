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
    private Stage thisStage;
    private String categoriesToAsk;

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

    public void showStage(){thisStage.showAndWait();}

    @FXML
    private void initialize(){
        categoryName.setText("The category is: "+categoriesToAsk);
    }


    public void clickToContinue(MouseEvent mouseEvent) {

        ((Node)mouseEvent.getSource()).getScene().getWindow().hide();

    }
}

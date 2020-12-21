package MainPackage.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MultiPlayerScoresController {

    @FXML
    private Label displayScores;
    private String string;
    private Stage thisStage;

    public MultiPlayerScoresController(String string){

        this.string=string;
        thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/MultiPlayerScores.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Buzz Quiz");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showStage(){thisStage.show();}

    @FXML
    private void initialize(){
        displayScores.setText(string);
    }

}

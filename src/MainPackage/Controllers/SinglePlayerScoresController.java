package MainPackage.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class SinglePlayerScoresController {

    @FXML
    private Label displayScores;
    private final String string;
    private final Stage thisStage;


    public SinglePlayerScoresController(String string){

        this.string=string;
        thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/SinglePlayerScores.fxml"));
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

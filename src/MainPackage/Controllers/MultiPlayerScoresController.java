package MainPackage.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class sets up the scene in which all the multi player mode scores will be displayed.
 */
public class MultiPlayerScoresController {

    @FXML
    private Label displayScores;
    private final String string;
    private final Stage thisStage;

    /***
     * Links the controller of the game options stage with the corresponding FXML file and then loads it
     * @param string a string which contains all the scores in the single/multi player file specifically formatted
     *               to be displayed
     */
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

    /***
     * Displays the name of each player, the score and some text with who is the winner
     */
    public void showStage(){thisStage.show();}

    /***
     * Display all the scores of the multi player mode
     */
    @FXML
    private void initialize(){
        displayScores.setText(string);
    }

}

package MainPackage.Controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * This class sets up the scene in which the players choose the options of the whole game.
 */
public class GameOptionsController {

    private final Stage thisStage;
    public static UsernameInputController userController;

    @FXML
    private TextField numberOfPlayers;
    @FXML
    private TextField numberOfRounds;
    @FXML
    private TextField numberOfQuestions;
    @FXML
    private Button submitOptions;
    @FXML
    private Label playersError;
    @FXML
    private Label roundsError;
    @FXML
    private Label questionsError;


    /***
     * Links the controller of the game options stage with the corresponding FXML file and then loads it
     */
    public GameOptionsController(){

        thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/GameOptions.fxml"));
            // Set this class as the controller
            loader.setController(this);
            // Load the scene
            thisStage.setScene(new Scene(loader.load()));
            // Setup the window/stage
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
     * Gets the text typed by the user in the stage labels (Options of the Game), when the submitOptions button
     * is clicked. If the options are correct then the current stage is hidden and a function for creating the
     * next scene is called. If the options are wrong the corresponding error message is displayed.
     * @param mouseEvent the click of the mouse
     */
    public void onMouseClick(MouseEvent mouseEvent)  {
        int players = Integer.parseInt(numberOfPlayers.getText());
        int questions = Integer.parseInt(numberOfQuestions.getText());
        int rounds = Integer.parseInt(numberOfRounds.getText());

        if((players==1||players==2)&&(questions>=3 && questions<=6)&&(rounds>=3 && rounds<=6)){
            ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
            openUsernameInput();
        }
        if(!(players==1||players==2)){
            playersError.setText("Wrong Input");
        }else{
            playersError.setText("");
        }
        if(!(rounds>=3 && rounds<=6)){
            roundsError.setText("Wrong Input");
        }else {
            roundsError.setText("");
        }
        if(!(questions>=3 && questions<=6)){
            questionsError.setText("Wrong Input");
        }else{
            questionsError.setText("");
        }

    }

    /***
     * An Username Input Stage object gets created and displayed
     */
    private void openUsernameInput(){
        userController = new UsernameInputController();
        userController.showStage();

    }

    /***
     * @return the number of players that the users typed in the options label
     */
    public int getPlayers(){
        return Integer.parseInt(numberOfPlayers.getText());
    }

    /***
     * @return the number of rounds that the users typed in the options label
     */
    public int getRounds(){
        return Integer.parseInt(numberOfRounds.getText());
    }

    /***
     * @return the number of questions that the users typed in the options label
     */
    public int getQuestions(){
        return Integer.parseInt(numberOfQuestions.getText());
    }



}

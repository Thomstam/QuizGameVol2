package MainPackage.Controllers;

import MainPackage.Gamemodes.Gamemode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * This class sets up the scene where the name of the game mode, that will be played in the current round, gets displayed.
 */
public class GamemodeTypeController {

    @FXML
    private Label GamemodeType;
    public static int roundCounter =0;
    private final Stage thisStage;
    public Gamemode gamemode;

    /***
     * Links the controller of the game options stage with the corresponding FXML file and then loads it
     */
    public GamemodeTypeController(){
        thisStage=new Stage();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/GamemodeType.fxml"));
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
        thisStage.show();
    }

    /***
     * Closes the current stage
     */
    public void closeStage(){thisStage.close();}


    @FXML
    private void initialize() {
        displayGamemode();
    }

    /***
     * Displays the Stage with the Type of the Game Mode which will be played. If there are more rounds to be played
     * then a function which picks game modes randomly gets called (point builder and stop the clock if it's
     * one-player-mode and all of the game modes if it's two-player-mode). Based on the game mode picked the
     * corresponding message gets displayed and the an object of the game mode is created and saved in the
     * gamemode variable.
     * @return true if there are more rounds to be played or false if the game should end
     */
    public boolean displayGamemode(){
        showStage();
        int choice;
        if(roundCounter <WelcomeController.controller.getRounds()) {
            if(WelcomeController.controller.getPlayers()==1){
                choice=UsernameInputController.game.gamemodePicker(2);
            }else{
                choice=UsernameInputController.game.gamemodePicker(4);
            }

            if (choice == 0) {
                GamemodeType.setText("POINTBUILDER : Choose the correct answer and win 1000 points!");
            }else if(choice==1){
                GamemodeType.setText("STOP THE CLOCK: Choose the correct answer (within 5 seconds) and win as much points as the milliseconds you had left multiplied by 0.2!!!");
            }else if(choice==2) {
                GamemodeType.setText("BETTING : Win or lose the amount of points you bet!");
            }else if(choice==3){
                GamemodeType.setText("FASTEST WINS : First player to answer correctly gets 1000 points and the second 500!!! ");
            }else if(choice==4){
                GamemodeType.setText("HEAT UP: The player to answer 5 questions correctly wins 5000 points(Only the fastest answer counts)!!! ");
            }
            gamemode = UsernameInputController.game.start(choice);

            return true;
        }else return false;
    }

    /***
     * When the user clicks to start the round, the counter of the rounds gets increased and the current stage gets hidden.
     *The function which creates the asking question stage object gets called
     * @param mouseEvent
     */
    public void onMouseClick(MouseEvent mouseEvent)  {
        roundCounter++;
        ((Node)mouseEvent.getSource()).getScene().getWindow().hide();
        startAskingQuestions(gamemode);
    }

    /***
     * An Asking Questions Stage object gets created
     * @param gamemode
     */
    private void startAskingQuestions(Gamemode gamemode){
        AskQuestionController questionController = new AskQuestionController(gamemode);

    }

}



package MainPackage.Controllers;

import MainPackage.Gamemodes.Gamemode;
import MainPackage.Gamemodes.HeatUp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Sets up the scene in which the correct answer of the question and the points of each player will be displayed.
 */
public class DisplayCorrectAnswerController {
    @FXML
    private Label answerField;
    @FXML
    private Label playerName1;
    @FXML
    private Label correctAnswer1;
    @FXML
    private Label playerScore1;
    @FXML
    private Label playerName2;
    @FXML
    private Label correctAnswer2;
    @FXML
    private Label playerScore2;
    @FXML
    private Label heatUpScore1;
    @FXML
    private Label heatUpScore2;

    private final Stage thisStage;
    private final String correctAnswer;
    private final int qCounter;
    private final Gamemode gamemode;

    /***
     * Links the controller of the game options stage with the corresponding FXML file and then loads it
     * @param correctAnswer the correct answer of the current question
     * @param qCounter the number of the question being asked on this round
     * @param gamemode the type of game mode picked in this round
     */
    public DisplayCorrectAnswerController(String correctAnswer, int qCounter, Gamemode gamemode){
        thisStage=new Stage();
        this.gamemode=gamemode;
        this.qCounter = qCounter;
        this.correctAnswer=correctAnswer;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/DisplayCorrectAnswer.fxml"));
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
     * Displays the username of each player, if the answer given is correct, the current score of the player, (if the game mode
     * is heat up then the number of correct answer of this round is also displayed) and the correct answer.
     */
    @FXML
    private void initialize(){
        playerName1.setText(GameOptionsController.userController.listOfPlayers().get(0).getUsername());
        if(GameOptionsController.userController.listOfPlayers().get(0).getIsTheAnswerCorrect()){
            correctAnswer1.setText("Correct Answer");
        }else{
            correctAnswer1.setText("Wrong Answer");
        }
        playerScore1.setText(String.format("%.1f",GameOptionsController.userController.listOfPlayers().get(0).getScore()));
        if(gamemode instanceof HeatUp){
            heatUpScore1.setText(Integer.toString(GameOptionsController.userController.listOfPlayers().get(0).getNumberOfCorrectAnswers()));
        }
        if(GameOptionsController.userController.listOfPlayers().size()==2){
            playerName2.setText(GameOptionsController.userController.listOfPlayers().get(1).getUsername());
            if(GameOptionsController.userController.listOfPlayers().get(1).getIsTheAnswerCorrect()){
                correctAnswer2.setText("Correct Answer");
            }else{
                correctAnswer2.setText("Wrong Answer");
            }
            playerScore2.setText(String.format("%.1f",GameOptionsController.userController.listOfPlayers().get(1).getScore()));
            if(gamemode instanceof HeatUp){
                heatUpScore2.setText(Integer.toString(GameOptionsController.userController.listOfPlayers().get(1).getNumberOfCorrectAnswers()));
            }

        }
        answerField.setText(correctAnswer);
    }

    /***
     * When the mouse gets clicked the current stage gets closed. Then if all of the question of this round have been asked, we check
     * if there are more rounds to be played. If there are we just continue normally with our new round, else the stage where the type
     * of game mode is being displayed gets closed and the End Game Screen Stage object gets created and displayed
     * @param mouseEvent click of the mouse
     */
    public void clickToContinue(MouseEvent mouseEvent) {
        ((Node)mouseEvent.getSource()).getScene().getWindow().hide();
        if(gamemode instanceof HeatUp){
            if(GameOptionsController.userController.listOfPlayers().get(0).getNumberOfCorrectAnswers()==5||GameOptionsController.userController.listOfPlayers().get(1).getNumberOfCorrectAnswers()==5){
                boolean display=UsernameInputController.typeController.displayGamemode();
                if(!display){
                    UsernameInputController.typeController.closeStage();
                    EndGameScreenController endGameScreen=new EndGameScreenController();
                    endGameScreen.showStage();
                }
            }
        }else{
            if(qCounter ==WelcomeController.controller.getQuestions()){
                boolean display=UsernameInputController.typeController.displayGamemode();
                if(!display){
                    UsernameInputController.typeController.closeStage();
                    EndGameScreenController endGameScreen=new EndGameScreenController();
                    endGameScreen.showStage();
                }
            }
        }

    }
}

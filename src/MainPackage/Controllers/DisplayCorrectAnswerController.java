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

    public void showStage(){
        thisStage.showAndWait();
    }

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

    public void clickToContinue(MouseEvent mouseEvent) {
        ((Node)mouseEvent.getSource()).getScene().getWindow().hide();
        if(gamemode instanceof HeatUp){
            if(GameOptionsController.userController.listOfPlayers().get(0).getNumberOfCorrectAnswers()==5||GameOptionsController.userController.listOfPlayers().get(1).getNumberOfCorrectAnswers()==5){
                boolean display=UsernameInputController.typeController.displayGamemode();
                if(!display){
                    System.out.println("hi");
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

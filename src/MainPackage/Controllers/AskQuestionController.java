package MainPackage.Controllers;

import MainPackage.Gamemodes.Gamemode;
import MainPackage.Question;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class AskQuestionController {



    private final GameOptionsController controller;
    private final UsernameInputController usernameController;
    private final Gamemode gamemode;
    @FXML
    private Label question;
    @FXML
    private Label answerOne;
    @FXML
    private Label answerThree;
    @FXML
    private Label answerTwo;
    @FXML
    private Label answerFour;
    private Stage thisStage;

    public AskQuestionController(GameOptionsController controller, UsernameInputController usernameController, Gamemode gamemode) {
        this.controller=controller;
        this.usernameController=usernameController;
        this.gamemode=gamemode;
        thisStage = new Stage();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/askQuestion.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Buzz Quiz");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize(){
         Question questionSetUp=gamemode.gamemodeSetUp(usernameController.listOfPlayers(),controller.getQuestions(), usernameController.getCategories());
         List<String> possibleAnswers = questionSetUp.getPossibleAnswersToAsk();
         question.setText(questionSetUp.getQuestionToASk());
         answerOne.setText(possibleAnswers.get(0));
         answerTwo.setText(possibleAnswers.get(1));
         answerThree.setText(possibleAnswers.get(2));
         answerFour.setText(possibleAnswers.get(3));

    }

    public void showStage(){
        thisStage.show();
    }
}

package MainPackage.Controllers;

import MainPackage.Gamemodes.Betting;
import MainPackage.Gamemodes.Gamemode;
import MainPackage.Player;
import MainPackage.Question;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.JobSettings;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class AskQuestionController {



    Question questionSetUp;
    public int qcounter=0;
    public boolean firstAnswered=false;
    public boolean secondAnswered=false;
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
    private String categoriesToAsk;


    public AskQuestionController(Gamemode gamemode) {


        this.gamemode=gamemode;
        thisStage = new Stage();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/askQuestion.fxml"));
            loader.setController(this);
            Scene scene= new Scene(loader.load());
            thisStage.setScene(scene);
            thisStage.setTitle("Buzz Quiz");
            scene.setOnKeyPressed(this::onAnswerPicked);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize(){

        displayTheCategory();
        if(gamemode instanceof Betting){
            SetBetController setBet=new SetBetController();
            setBet.showStage();
        }

        displayQuestion();
    }

    private boolean displayQuestion(){
        if (qcounter<WelcomeController.controller.getQuestions()) {
            showStage();
            questionSetUp = gamemode.gamemodeSetUp(GameOptionsController.userController.listOfPlayers(), WelcomeController.controller.getQuestions(),GameOptionsController.userController.getCategories(),categoriesToAsk );
            List<String> possibleAnswers = questionSetUp.getPossibleAnswersToAsk();
            question.setText(questionSetUp.getQuestionToASk());
            answerOne.setText("A : "+possibleAnswers.get(0));
            answerTwo.setText("B : "+possibleAnswers.get(1));
            answerThree.setText("C : "+possibleAnswers.get(2));
            answerFour.setText("D : "+possibleAnswers.get(3));
            return true;
        }
        return false;
    }

    public void showStage(){
        thisStage.show();
    }

    public void onAnswerPicked(KeyEvent keyEvent) {

        if(WelcomeController.controller.getPlayers()==1){
            if(keyEvent.getCode()== KeyCode.A||keyEvent.getCode()== KeyCode.S||keyEvent.getCode()== KeyCode.D||keyEvent.getCode()== KeyCode.F){
                thisStage.close();
                if(keyEvent.getCode()== KeyCode.A){
                    GameOptionsController.userController.listOfPlayers().get(0).setAnswer("A");
                }else if(keyEvent.getCode()== KeyCode.S){
                    GameOptionsController.userController.listOfPlayers().get(0).setAnswer("B");
                }else if(keyEvent.getCode()== KeyCode.D){
                    GameOptionsController.userController.listOfPlayers().get(0).setAnswer("C");
                }else {
                    GameOptionsController.userController.listOfPlayers().get(0).setAnswer("D");
                }
                gamemode.callHandleTheScore(GameOptionsController.userController.listOfPlayers(),questionSetUp);
                qcounter++;
                displayIfTheAnswerWasCorrect();
                displayTheCategory();
                if(gamemode instanceof Betting && qcounter<WelcomeController.controller.getQuestions()){
                    SetBetController setBet=new SetBetController();
                    setBet.showStage();
                }
                if(!displayQuestion()){
                    thisStage.close();
                }
            }
        }else{
            if((keyEvent.getCode()== KeyCode.A||keyEvent.getCode()== KeyCode.S||keyEvent.getCode()== KeyCode.D||keyEvent.getCode()== KeyCode.F)&& !firstAnswered){
                if(keyEvent.getCode()== KeyCode.A){
                    GameOptionsController.userController.listOfPlayers().get(0).setAnswer("A");
                }else if(keyEvent.getCode()== KeyCode.S){
                    GameOptionsController.userController.listOfPlayers().get(0).setAnswer("B");
                }else if(keyEvent.getCode()== KeyCode.D){
                    GameOptionsController.userController.listOfPlayers().get(0).setAnswer("C");
                }else {
                    GameOptionsController.userController.listOfPlayers().get(0).setAnswer("D");
                }
                firstAnswered=true;
            }
            if((keyEvent.getCode()== KeyCode.H||keyEvent.getCode()== KeyCode.J||keyEvent.getCode()== KeyCode.K||keyEvent.getCode()== KeyCode.L)&& !secondAnswered){
                if(keyEvent.getCode()== KeyCode.H){
                    GameOptionsController.userController.listOfPlayers().get(1).setAnswer("A");
                }else if(keyEvent.getCode()== KeyCode.J){
                    GameOptionsController.userController.listOfPlayers().get(1).setAnswer("B");
                }else if(keyEvent.getCode()== KeyCode.K){
                    GameOptionsController.userController.listOfPlayers().get(1).setAnswer("C");
                }else {
                    GameOptionsController.userController.listOfPlayers().get(1).setAnswer("D");
                }
                secondAnswered=true;
            }
            if(firstAnswered&&secondAnswered){
                thisStage.close();
                gamemode.callHandleTheScore(GameOptionsController.userController.listOfPlayers(),questionSetUp);
                qcounter++;
                displayIfTheAnswerWasCorrect();
                firstAnswered=false;
                secondAnswered=false;
                displayTheCategory();
                if(gamemode instanceof Betting && qcounter<WelcomeController.controller.getQuestions()){
                    SetBetController setBet=new SetBetController();
                    setBet.showStage();
                }
                if(!displayQuestion()){
                    thisStage.close();

                }
            }
        }


    }

    private void displayTheCategory(){
        if(qcounter<WelcomeController.controller.getQuestions()) {
            categoriesToAsk = GameOptionsController.userController.getCategories().getRandomCategory();
            DisplayCategoryController displayCategory = new DisplayCategoryController(categoriesToAsk);
            displayCategory.showStage();
        }
    }


    private void displayIfTheAnswerWasCorrect() {
        DisplayCorrectAnswerController answerController=new DisplayCorrectAnswerController(questionSetUp.getCorrectAnswer(),qcounter);
        answerController.showStage();

    }
}

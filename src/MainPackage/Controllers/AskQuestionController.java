package MainPackage.Controllers;

import MainPackage.Gamemodes.Betting;
import MainPackage.Gamemodes.Gamemode;
import MainPackage.Gamemodes.HeatUp;
import MainPackage.Gamemodes.StopTheClock;
import MainPackage.Question;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AskQuestionController {

    Question questionSetUp;
    public int qCounter =0;
    public boolean firstAnswered=false;
    public boolean secondAnswered=false;
    public boolean answered=false;
    private final Stage thisStage;
    private String categoriesToAsk;
    private Timer timer;
    private final Gamemode gamemode;
    @FXML
    private ImageView questionImage;
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
    @FXML
    private Label TimeLeft;




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

    /***
     * Before even the question gets displayed, the function which creates the Display Category Stage object
     * gets called. If the gamemode is betting the Set Bet Stage object gets called and displayed for the
     * players to choose their bet. After that the function which displays the question gets called
     */
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
        if(gamemode instanceof HeatUp){
            if(GameOptionsController.userController.listOfPlayers().get(0).getNumberOfCorrectAnswers()<5&&GameOptionsController.userController.listOfPlayers().get(1).getNumberOfCorrectAnswers()<5){
                showStage();
                questionSetUp = gamemode.gamemodeSetUp(GameOptionsController.userController.getCategories(),categoriesToAsk );
                List<String> possibleAnswers = questionSetUp.getPossibleAnswersToAsk();
                question.setText(questionSetUp.getQuestionToASk());
                answerOne.setText("A : "+possibleAnswers.get(0));
                answerTwo.setText("B : "+possibleAnswers.get(1));
                answerThree.setText("C : "+possibleAnswers.get(2));
                answerFour.setText("D : "+possibleAnswers.get(3));
                if(questionSetUp.isQuestionContainsImage()){
                    questionImage.setImage(new Image(questionSetUp.pathOfTheImage()));
                }
                return true;
            }else{
                GameOptionsController.userController.listOfPlayers().get(0).setNumberOfCorrectAnswers(0);
                GameOptionsController.userController.listOfPlayers().get(1).setNumberOfCorrectAnswers(0);
                return false;
            }
        }else{
            if (qCounter <WelcomeController.controller.getQuestions()) {
                showStage();
                questionSetUp = gamemode.gamemodeSetUp(GameOptionsController.userController.getCategories(),categoriesToAsk );
                List<String> possibleAnswers = questionSetUp.getPossibleAnswersToAsk();
                question.setText(questionSetUp.getQuestionToASk());
                answerOne.setText("A : "+possibleAnswers.get(0));
                answerTwo.setText("B : "+possibleAnswers.get(1));
                answerThree.setText("C : "+possibleAnswers.get(2));
                answerFour.setText("D : "+possibleAnswers.get(3));
                if(questionSetUp.isQuestionContainsImage()){
                    questionImage.setImage(new Image(questionSetUp.pathOfTheImage()));
                }
                if(gamemode instanceof StopTheClock){
                    TimeLeft.setText("Time Left: 5000");
                    setTimer();
                }
                return true;
            }
            return false;
        }


    }

    public void showStage(){
        thisStage.show();
    }

    public void onAnswerPicked(KeyEvent keyEvent) {

        if(WelcomeController.controller.getPlayers()==1){
            if(keyEvent.getCode()== KeyCode.A||keyEvent.getCode()== KeyCode.S||keyEvent.getCode()== KeyCode.D||keyEvent.getCode()== KeyCode.F){
                answered=true;
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
                qCounter++;
                questionImage.setImage(null);
                displayIfTheAnswerWasCorrect();
                answered=false;
                displayTheCategory();
                if(gamemode instanceof Betting && qCounter <WelcomeController.controller.getQuestions()){
                    SetBetController setBet=new SetBetController();
                    setBet.showStage();
                }
                if(!displayQuestion()){
                    thisStage.close();
                }
            }
        }else{
            if((keyEvent.getCode()== KeyCode.A||keyEvent.getCode()== KeyCode.S||keyEvent.getCode()== KeyCode.D||keyEvent.getCode()== KeyCode.F)&& !firstAnswered){
                if(gamemode instanceof StopTheClock) {
                    GameOptionsController.userController.listOfPlayers().get(0).setTimeLeftFromAnswer(Integer.parseInt(TimeLeft.getText().replaceAll("\\D+", "")));
                }
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
                if(gamemode instanceof StopTheClock) {
                    GameOptionsController.userController.listOfPlayers().get(1).setTimeLeftFromAnswer(Integer.parseInt(TimeLeft.getText().replaceAll("\\D+", "")));
                }
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
            if(firstAnswered&&!secondAnswered){
                GameOptionsController.userController.listOfPlayers().get(0).setPlacement("First");
                GameOptionsController.userController.listOfPlayers().get(1).setPlacement("Second");
            }else if(!firstAnswered&&secondAnswered){
                GameOptionsController.userController.listOfPlayers().get(1).setPlacement("First");
                GameOptionsController.userController.listOfPlayers().get(0).setPlacement("Second");
            }
            if(firstAnswered&&secondAnswered){
                questionImage.setImage(null);
                thisStage.close();
                gamemode.callHandleTheScore(GameOptionsController.userController.listOfPlayers(),questionSetUp);
                qCounter++;
                displayIfTheAnswerWasCorrect();
                firstAnswered=false;
                secondAnswered=false;
                displayTheCategory();
                if(gamemode instanceof Betting && qCounter <WelcomeController.controller.getQuestions()){
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
        if(gamemode instanceof HeatUp){
            if(GameOptionsController.userController.listOfPlayers().get(0).getNumberOfCorrectAnswers()<5&&GameOptionsController.userController.listOfPlayers().get(1).getNumberOfCorrectAnswers()<5){
                categoriesToAsk = GameOptionsController.userController.getCategories().getRandomCategory();
                DisplayCategoryController displayCategory = new DisplayCategoryController(categoriesToAsk);
                displayCategory.showStage();
            }
        }else{
            if(qCounter <WelcomeController.controller.getQuestions()) {
                categoriesToAsk = GameOptionsController.userController.getCategories().getRandomCategory();
                DisplayCategoryController displayCategory = new DisplayCategoryController(categoriesToAsk);
                displayCategory.showStage();
            }
        }

    }


    private void displayIfTheAnswerWasCorrect() {
        DisplayCorrectAnswerController answerController=new DisplayCorrectAnswerController(questionSetUp.getCorrectAnswer(), qCounter,gamemode);
        answerController.showStage();

    }


    public void setTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int interval=5000;
            public void run() {
                if(WelcomeController.controller.getPlayers()==1) {
                    if (interval > 0 && (!answered)) {
                        Platform.runLater(() -> TimeLeft.setText("Time Left: " + interval));
                        interval--;
                    } else{
                        timer.cancel();
                    }
                }else{
                    if (interval > 0 && (!firstAnswered || !secondAnswered)) {
                        Platform.runLater(() -> TimeLeft.setText("Time Left: " + interval));
                        interval--;
                    } else {
                        timer.cancel();
                    }
                }
            }
        }, 2000,1);
    }


}






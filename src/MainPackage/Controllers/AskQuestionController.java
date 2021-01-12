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


    /***
     * Links the controller of the game options stage with the corresponding FXML file and then loads it
     * @param gamemode the game mode which is currently being played in this round
     */
    public AskQuestionController(Gamemode gamemode) {
        this.gamemode=gamemode;
        thisStage = new Stage();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/askQuestion.fxml"));
            // Set this class as the controller
            loader.setController(this);
            // Load the scene
            Scene scene= new Scene(loader.load());
            thisStage.setScene(scene);
            // Setup the window/stage
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

    /***
     * The question and the four answer get set up and displayed to their corresponding labels, if there exists
     * an image is also displayed and if the game mode is Stop The Clock a timer is set to 5000 ms and the function which
     * starts it gets called. If the game mode is Heat Up then the questions keep getting asked until someone reaches
     * five correct answers, before ending the round the correct answers of the players are set back to zero. For any other
     * game mode the questions keep getting asked for how many times the users picked at the start of the game.
     * @return true if there are more questions to be asked untill the round ends and false when the round is over
     */
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

    /***
     * Displays the current stage
     */
    public void showStage(){
        thisStage.show();
    }


    /***
     * If One-Player-Mode is played then when the player presses one of the keys corresponding to an answer the function
     * which handles the score for each game mode gets called, the counter of the questions gets increased, the FXML imageview
     * gets set to null so that it won't interfere with the next question, the function which creates the Display if the Answer
     * Was Correct Stage object gets called and the next category gets displayed. Then the next question is ready to get asked,
     * or if the game mode is betting the Set Bet Stage object gets called prior to that. The same things happens with the
     * Two-Player-Mode with the only difference that both of the players should answer before the game continues. If the round
     * is over (displayQuestion==false) then the current stage gets closed.
     * @param keyEvent the button the player presses to answer the question
     */
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

    /***
     * If the game mode is Heat Up then a Display the Category Stage object gets created, before displaying the question, for how many times it
     * takes until at least one of the players answers correctly five questions. For any other game mode do the same thing but for
     * how many times have picked at the start of the game
     */
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

    /***
     * Create a Display if the Answer Was Correct Stage object and display it
     */
    private void displayIfTheAnswerWasCorrect() {
        DisplayCorrectAnswerController answerController=new DisplayCorrectAnswerController(questionSetUp.getCorrectAnswer(), qCounter,gamemode);
        answerController.showStage();

    }

    /***
     * Sets a timer for 5000 ms and starts it after 2 seconds. If there is more time in the Timer and the players haven't
     * yet answered decrease the time left else cancel the timer.
     */
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






package MainPackage.Controllers;

import MainPackage.Categories;
import MainPackage.Game;
import MainPackage.Gamemodes.Gamemode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class GamemodeTypeController {

    @FXML
    private Label GamemodeType;
    public static int roundcounter=0;
    private Stage thisStage;
    public Gamemode gamemode;

    public GamemodeTypeController(){
        thisStage=new Stage();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/GamemodeType.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Buzz Quiz");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showStage(){
        thisStage.show();
    }

    public void closeStage(){thisStage.close();}


    @FXML
    private void initialize() {
        displayGamemode();
    }

    public boolean displayGamemode(){
        showStage();
        int choice;
        if(roundcounter<WelcomeController.controller.getRounds()) {
            if(WelcomeController.controller.getPlayers()==1){
                choice=UsernameInputController.game.gamemodePicker(2);
            }else{
                choice=UsernameInputController.game.gamemodePicker(4);
            }

            if (choice == 0) {
                GamemodeType.setText("POINTBUILDER : Choose the correct answer and win 1000 points!");
                //gamemode.gamemodeSetUp(usernameController.listOfPlayers(),controller.getQuestions(),usernameController.getCategories());
            } else if (choice == 1) {
                GamemodeType.setText("BETTING : Win or lose the amount of points you bet!");
            }else if (choice==2){
                GamemodeType.setText("FASTEST WINS : First player to answer correctly gets 1000 points and the second 500!!! ");
            }else if(choice==3){
                GamemodeType.setText("HEAT UP: The player to answer 5 questions correctly wins 5000 points(Only the fastest answer counts)!!! ");
            }
            gamemode = UsernameInputController.game.start(choice);

            return true;
        }else return false;
    }

    public void onMouseClick(MouseEvent mouseEvent)  {
        roundcounter++;
        ((Node)mouseEvent.getSource()).getScene().getWindow().hide();
        startAskingQuestions(gamemode);
    }

    private void startAskingQuestions(Gamemode gamemode){
        AskQuestionController questionController = new AskQuestionController(gamemode);

    }

}



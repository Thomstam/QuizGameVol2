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
        System.out.println((roundcounter));
        if(roundcounter<WelcomeController.controller.getRounds()) {
            int choice=UsernameInputController.game.gamemodePicker();
            if (choice == 0) {
                GamemodeType.setText("POINTBUILDER : Choose the correct answer \nand win 1000 points!");
                //gamemode.gamemodeSetUp(usernameController.listOfPlayers(),controller.getQuestions(),usernameController.getCategories());
            } else {
                GamemodeType.setText("BETTING : Win or lose the amount \nof points you bet!");
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
        questionController.showStage();

    }

}



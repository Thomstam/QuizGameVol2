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

    private Stage thisStage;
    private final GameOptionsController controller;
    private final UsernameInputController usernameController;
    public Gamemode gamemode;



    public GamemodeTypeController(GameOptionsController controller, UsernameInputController usernameController){

        this.controller = controller;
        this.usernameController= usernameController;
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

    @FXML
    private void initialize(){
        Game game = new Game(controller.getPlayers(),controller.getRounds(),controller.getQuestions(),usernameController.listOfPlayers());
        if(game.gamemodePicker()==0){
            GamemodeType.setText("POINTBUILDER : Choose the correct answer \nand win 1000 points!");
            gamemode =game.start(game.gamemodePicker());
            //gamemode.gamemodeSetUp(usernameController.listOfPlayers(),controller.getQuestions(),usernameController.getCategories());
        }else{
            GamemodeType.setText("BETTING : Win or lose the amount \nof points you bet!");
        }

    }

    public void onMouseClick(MouseEvent mouseEvent)  {
        ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
        startAskingQuestions(gamemode);
    }

    private void startAskingQuestions(Gamemode gamemode){
        AskQuestionController questionController = new AskQuestionController(controller,usernameController,gamemode);
        questionController.showStage();
    }

}



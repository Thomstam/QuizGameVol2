package MainPackage.Controllers;

import MainPackage.Player;
import MainPackage.ScoreSaving;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class EndGameScreenController {
    @FXML
    private Label winner;
    @FXML
    private Label playerUsername1;
    @FXML
    private Label playerScore1;
    @FXML
    private Label playerScore2;
    @FXML
    private Label playerUsername2;

    private Stage thisStage;


    public EndGameScreenController(){

        thisStage=new Stage();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/EndGameScreen.fxml"));
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
        playerUsername1.setText(GameOptionsController.userController.listOfPlayers().get(0).getUsername());
        double score1=GameOptionsController.userController.listOfPlayers().get(0).getScore();
        playerScore1.setText(Double.toString(score1));
        if(GameOptionsController.userController.listOfPlayers().size()==2){
            playerUsername2.setText(GameOptionsController.userController.listOfPlayers().get(1).getUsername());
            double score2=GameOptionsController.userController.listOfPlayers().get(1).getScore();
            playerScore2.setText(Double.toString(score2));
            if(score1>score2){
                winner.setText(GameOptionsController.userController.listOfPlayers().get(0).getUsername()+" is the winner!!!");
            }else if(score2>score1){
                winner.setText(GameOptionsController.userController.listOfPlayers().get(1).getUsername()+" is the winner!!!");
            }else{
                winner.setText("It's a draw!!!");
            }
        }

    }

    public void clickToQuit(MouseEvent mouseEvent) {
        ScoreSaving score = new ScoreSaving();
        if(GameOptionsController.userController.listOfPlayers().size()==1){
            score.scoreToSave(GameOptionsController.userController.listOfPlayers().get(0),1);
        }else{
            double score1=GameOptionsController.userController.listOfPlayers().get(0).getScore();
            double score2=GameOptionsController.userController.listOfPlayers().get(1).getScore();
            if(score1>score2){
                score.scoreToSave(GameOptionsController.userController.listOfPlayers().get(0),2);
            }else{
                score.scoreToSave(GameOptionsController.userController.listOfPlayers().get(1),2);
            }

        }

        ((Node)mouseEvent.getSource()).getScene().getWindow().hide();
    }
}

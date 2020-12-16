package MainPackage.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SetBetController {

    @FXML
    private Label playerUsername;
    private Stage thisStage;
    public int counter=0;

    public SetBetController(){
        thisStage = new Stage();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/SetBet.fxml"));
            loader.setController(this);
            Scene scene= new Scene(loader.load());
            thisStage.setScene(scene);
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
        displayUsername();
    }

    private void displayUsername(){
        playerUsername.setText(GameOptionsController.userController.listOfPlayers().get(counter).getUsername());
    }

    public void clickToSetBet250(MouseEvent mouseEvent) {
        GameOptionsController.userController.listOfPlayers().get(counter).setBet(250);
        counter++;
        if(counter==GameOptionsController.userController.listOfPlayers().size()){
            ((Node)mouseEvent.getSource()).getScene().getWindow().hide();
        }else{
            displayUsername();
        }
    }

    public void clickToSetBet500(MouseEvent mouseEvent) {
        GameOptionsController.userController.listOfPlayers().get(counter).setBet(500);
        counter++;
        if(counter==GameOptionsController.userController.listOfPlayers().size()){
            ((Node)mouseEvent.getSource()).getScene().getWindow().hide();
        }else{
            displayUsername();
        }
    }

    public void clickToSetBet750(MouseEvent mouseEvent) {
        GameOptionsController.userController.listOfPlayers().get(counter).setBet(750);
        counter++;
        if(counter==GameOptionsController.userController.listOfPlayers().size()){
            ((Node)mouseEvent.getSource()).getScene().getWindow().hide();
        }else{
            displayUsername();
        }
    }

    public void clickToSetBet1000(MouseEvent mouseEvent) {
        GameOptionsController.userController.listOfPlayers().get(counter).setBet(1000);
        counter++;
        if(counter==GameOptionsController.userController.listOfPlayers().size()){
            ((Node)mouseEvent.getSource()).getScene().getWindow().hide();
        }else{
            displayUsername();
        }
    }
}

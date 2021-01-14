package MainPackage.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * This class sets up the scene in which the player will choose his bet if the game mode of the round is "Betting".
 */
public class SetBetController {

    @FXML
    private Label playerUsername;
    private final Stage thisStage;
    public int counter=0;

    /***
     * Links the controller of the game options stage with the corresponding FXML file and then loads it
     */
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

    /***
     * Displays the current stage
     */
    public void showStage(){
        thisStage.showAndWait();
    }

    /***
     * Calls the display username function
     */
    @FXML
    private void initialize(){
        displayUsername();
    }

    /***
     * Display the username of the player who chooses bets at the time
     */
    private void displayUsername(){
        playerUsername.setText(GameOptionsController.userController.listOfPlayers().get(counter).getUsername());
    }

    /***
     * Sets the bet of the player to 250 point and increases the count of the players who have already chosen their bet.
     * If there are more the next player chooses their bet, else the current stage is closed.
     * @param mouseEvent click of the mouse
     */
    public void clickToSetBet250(MouseEvent mouseEvent) {
        GameOptionsController.userController.listOfPlayers().get(counter).setBet(250);
        counter++;
        if(counter==GameOptionsController.userController.listOfPlayers().size()){
            ((Node)mouseEvent.getSource()).getScene().getWindow().hide();
        }else{
            displayUsername();
        }
    }


    /***
     * Sets the bet of the player to 500 point and increases the count of the players who have already chosen their bet.
     * If there are more the next player chooses their bet, else the current stage is closed.
     * @param mouseEvent click of the mouse
     */
    public void clickToSetBet500(MouseEvent mouseEvent) {
        GameOptionsController.userController.listOfPlayers().get(counter).setBet(500);
        counter++;
        if(counter==GameOptionsController.userController.listOfPlayers().size()){
            ((Node)mouseEvent.getSource()).getScene().getWindow().hide();
        }else{
            displayUsername();
        }
    }

    /***
     * Sets the bet of the player to 750 point and increases the count of the players who have already chosen their bet.
     * If there are more the next player chooses their bet, else the current stage is closed.
     * @param mouseEvent click of the mouse
     */
    public void clickToSetBet750(MouseEvent mouseEvent) {
        GameOptionsController.userController.listOfPlayers().get(counter).setBet(750);
        counter++;
        if(counter==GameOptionsController.userController.listOfPlayers().size()){
            ((Node)mouseEvent.getSource()).getScene().getWindow().hide();
        }else{
            displayUsername();
        }
    }

    /***
     * Sets the bet of the player to 1000 point and increases the count of the players who have already chosen their bet.
     * If there are more the next player chooses their bet, else the current stage is closed.
     * @param mouseEvent click of the mouse
     */
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

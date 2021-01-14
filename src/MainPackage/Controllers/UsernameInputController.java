package MainPackage.Controllers;

import MainPackage.Categories;
import MainPackage.Game;
import MainPackage.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


/**
 * This class sets up the scene where the players choose their usernames.
 */
public class UsernameInputController {

    private final Stage thisStage;
    private final ArrayList<Player> players;
    public int counter=0;
    private final Categories categories;
    public static Game game;
    public static GamemodeTypeController typeController;
    @FXML
    private TextField username;

    /***
     * Links the controller of the game options stage with the corresponding FXML file and then loads it.
     * An arraylist to save all the players is created and a new categories object is created for the upcoming
     * questions
     */
    public UsernameInputController() {
        players=new ArrayList<>();
        thisStage = new Stage();
        categories = new Categories();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/UsernameInput.fxml"));
            // Set this class as the controller
            loader.setController(this);
            // Load the scene
            thisStage.setScene(new Scene(loader.load()));
            // Setup the window/stage
            thisStage.setTitle("Buzz Quiz");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * Displays the current stage
     */
    public void showStage(){
        thisStage.show();
    }

    /***
     * When the submit button is clicked, the username written by the user is saved in the players arraylist
     * The label where the user types the username is reset until the usernames submitted are as much as the
     * number of players that was previously picked. When all the players have submitted their usernames the current
     * stage gets hidden and the function which creates the Type of Gamemode Stage object is called.
     * @param mouseEvent the click of the mouse
     */
    public void submitUsername(MouseEvent mouseEvent) {
        if(!username.getText().equals("")){
            counter++;
            players.add(new Player(username.getText()));
            username.setText("");
        }

        if (counter==WelcomeController.controller.getPlayers()){
            ((Node)mouseEvent.getSource()).getScene().getWindow().hide();
            game = new Game(WelcomeController.controller.getPlayers(),WelcomeController.controller.getRounds(),WelcomeController.controller.getQuestions(),GameOptionsController.userController.listOfPlayers());
            typeOfGamemode();


        }

    }

    /***
     * The Type of Gamemode Stage object gets created
     */
    private void typeOfGamemode(){

        typeController = new GamemodeTypeController();

    }

    /***
     * @return the arraylist with all the Player Objects
     */
    public ArrayList<Player> listOfPlayers(){
        return players;
    }

    /***
     * @return return the categories object created
     */
    public Categories getCategories(){
        return categories;
    }




}

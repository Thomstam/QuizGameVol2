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

public class UsernameInputController {

    private Stage thisStage;
    private ArrayList<Player> players;
    public int counter=0;

    private Categories categories;
    public static Game game;
    public static GamemodeTypeController typeController;




    @FXML
    private TextField username;

    public UsernameInputController() {
        //
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

    public void showStage(){
        thisStage.show();
    }

    public void submitUsername(MouseEvent mouseEvent) {

        counter++;
        players.add(new Player(username.getText()));
        username.setText("");
        if (counter==WelcomeController.controller.getPlayers()){
            ((Node)mouseEvent.getSource()).getScene().getWindow().hide();
            game = new Game(WelcomeController.controller.getPlayers(),WelcomeController.controller.getRounds(),WelcomeController.controller.getQuestions(),GameOptionsController.userController.listOfPlayers());
            typeOfGamemode();


        }

    }


    private void typeOfGamemode(){

        typeController = new GamemodeTypeController();
        //typeController.showStage();

    }

    public ArrayList<Player> listOfPlayers(){
        return players;
    }

    public Categories getCategories(){
        return categories;
    }




}

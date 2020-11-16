package MainPackage.Controllers;

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

    private final GameOptionsController controller;

    @FXML
    private TextField username;

    public UsernameInputController(GameOptionsController controller) {
        //
        players=new ArrayList<>();

        this.controller=controller;

        thisStage = new Stage();

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
        if (counter==controller.getPlayers()){
            printPlayers();
            ((Node)mouseEvent.getSource()).getScene().getWindow().hide();
            Game game = new Game(controller.getPlayers(),controller.getRounds(),controller.getQuestions(),players);
            game.start();

        }

    }

    public void printPlayers(){
        for (Player player:players){
            System.out.println("player:"+player.getUsername());
        }
    }


}

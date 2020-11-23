package MainPackage.Controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOptionsController {

    private final Stage thisStage;

    @FXML
    private TextField numberOfPlayers;
    @FXML
    private TextField numberOfRounds;
    @FXML
    private TextField numberOfQuestions;
    @FXML
    private Button submitOptions;
    @FXML
    private Label playersError;
    @FXML
    private Label roundsError;
    @FXML
    private Label questionsError;

    public GameOptionsController(){
        thisStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/GameOptions.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Buzz Quiz");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showStage(){
        thisStage.showAndWait();
    }


    public void onMouseClick(MouseEvent mouseEvent) throws IOException {
        int players = Integer.parseInt(numberOfPlayers.getText());
        int questions = Integer.parseInt(numberOfQuestions.getText());
        int rounds = Integer.parseInt(numberOfRounds.getText());

        if((players==1||players==2)&&(questions>=3 && questions<=6)&&(rounds>=3 && rounds<=6)){
            ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
            openUsernameInput();
        }
        if(!(players==1||players==2)){
            playersError.setText("Wrong Input");
        }else{
            playersError.setText("");
        }
        if(!(rounds>=3 && rounds<=6)){
            roundsError.setText("Wrong Input");
        }else {
            roundsError.setText("");
        }
        if(!(questions>=3 && questions<=6)){
            questionsError.setText("Wrong Input");
        }else{
            questionsError.setText("");
        }

    }

    private void openUsernameInput(){
        UsernameInputController userController = new UsernameInputController(this);
        userController.showStage();

    }

    public int getPlayers(){
        return Integer.parseInt(numberOfPlayers.getText());
    }

    public int getRounds(){
        return Integer.parseInt(numberOfRounds.getText());
    }

    public int getQuestions(){
        return Integer.parseInt(numberOfQuestions.getText());
    }



}

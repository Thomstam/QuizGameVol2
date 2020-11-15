package MainPackage.Controllers;


import MainPackage.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOptionsController {

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

    public void onMouseClick(MouseEvent mouseEvent) throws IOException {
        int players = Integer.parseInt(numberOfPlayers.getText());
        int questions = Integer.parseInt(numberOfQuestions.getText());
        int rounds = Integer.parseInt(numberOfRounds.getText());

        if((players==1||players==2)&&(questions>=3 && questions<=6)&&(rounds>=3 && rounds<=6)){
            Parent root;
            root=FXMLLoader.load(getClass().getResource("../resources/UsernameInput.fxml"));
            Stage stage = new Stage();
            stage.setTitle("New Game");
            stage.setScene(new Scene(root,600,400));
            stage.show();
            ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();

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
}

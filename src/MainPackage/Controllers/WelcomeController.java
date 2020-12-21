package MainPackage.Controllers;

import MainPackage.ScoreSaving;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {

    public static GameOptionsController controller;

    public void goToMenu(MouseEvent mouseEvent) {

        ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
        controller= new GameOptionsController();
        controller.showStage();

    }

    public void goToHelp(MouseEvent mouseEvent) {
        HelpController help=new HelpController();
        help.showStage();
    }

    public void goToSingle(MouseEvent mouseEvent) throws IOException {
        ScoreSaving singleScores = new ScoreSaving();
        String string=singleScores.readFile("SinglePlayerFileScore.txt");
        SinglePlayerScoresController singlePlayerScores = new SinglePlayerScoresController(string);
        singlePlayerScores.showStage();
    }

    public void goToMulti(MouseEvent mouseEvent) throws IOException {
        ScoreSaving singleScores = new ScoreSaving();
        String string=singleScores.readFile("MultiplayerFileScore.txt");
        MultiPlayerScoresController multiPlayerScores= new MultiPlayerScoresController(string);
        multiPlayerScores.showStage();
    }
}

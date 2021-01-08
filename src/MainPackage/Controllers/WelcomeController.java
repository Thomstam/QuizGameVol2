package MainPackage.Controllers;

import MainPackage.ScoreSaving;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

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

    public void goToMulti(MouseEvent mouseEvent){
        ScoreSaving singleScores = new ScoreSaving();
        String string=singleScores.readFile("MultiplayerFileScore.txt");
        MultiPlayerScoresController multiPlayerScores= new MultiPlayerScoresController(string);
        multiPlayerScores.showStage();
    }
}

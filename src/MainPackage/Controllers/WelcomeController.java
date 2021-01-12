package MainPackage.Controllers;

import MainPackage.ScoreSaving;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class WelcomeController {

    public static GameOptionsController controller;

    /***
     * When the user clicks the menu button, the current stage gets hidden and a Game Options Stage gets created and displayed
     * @param mouseEvent the click of the mouse, which is also used for hiding the stage
     */
    public void goToMenu(MouseEvent mouseEvent) {

        ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
        controller= new GameOptionsController();
        controller.showStage();

    }


    /***
     * When the user clicks the help button a Help Stage object (advice on how to play the game) gets
     * created and displayed
     * @param mouseEvent the click of the mouse
     */
    public void goToHelp(MouseEvent mouseEvent) {
        HelpController help=new HelpController();
        help.showStage();
    }


    /***
     * When the user clicks the Single Player Scores button a ScoreSaving object gets created and the text file
     * with all the single player scores gets read, then a Single Player Scores Stage object gets created and displayed
     * @param mouseEvent the click of the mouse
     * @throws IOException
     */
    public void goToSingle(MouseEvent mouseEvent) throws IOException {
        ScoreSaving singleScores = new ScoreSaving();
        String string=singleScores.readFile("SinglePlayerFileScore.txt");
        SinglePlayerScoresController singlePlayerScores = new SinglePlayerScoresController(string);
        singlePlayerScores.showStage();
    }


    /***
     * When the user clicks the Multi Player Scores button a ScoreSaving object gets created and the text file
     * with all the multi player scores gets read, then a Multi Player Scores Stage object gets created and displayed
     * @param mouseEvent the click of the mouse
     */
    public void goToMulti(MouseEvent mouseEvent){
        ScoreSaving multiScores = new ScoreSaving();
        String string=multiScores.readFile("MultiplayerFileScore.txt");
        MultiPlayerScoresController multiPlayerScores= new MultiPlayerScoresController(string);
        multiPlayerScores.showStage();
    }
}

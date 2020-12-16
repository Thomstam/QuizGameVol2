package MainPackage.Gamemodes;

import MainPackage.Player;
import MainPackage.Question;

import java.util.ArrayList;

public class PointBuilder extends Gamemode {


    /**
     * For each player we are getting his answer we and we compare it with the correct one.
     * If the player is right we are giving him 1000 points. Otherwise nothing happens.
     * @param players List with all the players.
     */
    @Override
    void handleTheScore(ArrayList<Player> players, Question question) {
        for (Player player : players) {
            int indexOfChoice = handlePlayerChoice(player.getAnswer());
            if (question.getPossibleAnswersToAsk().get(indexOfChoice).equals(question.getCorrectAnswer())) {
                player.setScore(player.getScore() + 1000);
                player.setIsTheAnswerCorrect(true);
            }else{
                player.setIsTheAnswerCorrect(false);
            }
        }
    }
}

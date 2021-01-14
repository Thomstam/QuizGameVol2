package MainPackage.Gamemodes;

import MainPackage.Player;
import MainPackage.Question;

import java.util.ArrayList;

public class StopTheClock extends Gamemode {

    /***
     * For each player we are getting his answer we and we compare it with the correct one.
     * If the player is right we are giving him 0.2*(The time he had left when he answered in ms) points.
     * Otherwise nothing happens.
     * @param players List with all the players.
     * @param question The current question being played.
     */
    @Override
    public void handleTheScore(ArrayList<Player> players, Question question) {
        for (Player player : players) {
            int indexOfChoice = handlePlayerChoice(player.getAnswer());
            if (question.getPossibleAnswersToAsk().get(indexOfChoice).equals(question.getCorrectAnswer())) {
                player.setScore(player.getScore() + (player.getTimeLeftFromAnswer() * 0.2));
                player.setIsTheAnswerCorrect(true);
            }else{
                player.setIsTheAnswerCorrect(false);
            }
        }
    }
}

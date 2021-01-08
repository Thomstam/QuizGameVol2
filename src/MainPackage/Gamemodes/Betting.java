package MainPackage.Gamemodes;

import MainPackage.Player;
import MainPackage.Question;

import java.util.ArrayList;

public class Betting extends Gamemode {

    public Betting(){
        super();
    }


    /**
     * For each player we are getting his answer we and we compare it with the correct one.
     * If the player is right we are giving him points equal of his bet. Otherwise we subtract them.
     * @param players List with all the players.
     */
    @Override
    public void handleTheScore(ArrayList<Player> players, Question question) {
        for (Player player : players) {
            int indexOfChoice = handlePlayerChoice(player.getAnswer());
            if (question.getPossibleAnswersToAsk().get(indexOfChoice).equals(question.getCorrectAnswer())) {
                player.setScore(player.getScore() + player.getBet());
                player.setIsTheAnswerCorrect(true);
            }else {
                player.setScore(player.getScore() - player.getBet());
                player.setIsTheAnswerCorrect(false);
            }
        }
    }
}

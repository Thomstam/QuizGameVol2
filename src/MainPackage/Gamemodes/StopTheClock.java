package MainPackage.Gamemodes;

import MainPackage.Player;
import MainPackage.Question;

import java.util.ArrayList;

public class StopTheClock extends Gamemode {

    @Override
    void handleTheScore(ArrayList<Player> players, Question question) {
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

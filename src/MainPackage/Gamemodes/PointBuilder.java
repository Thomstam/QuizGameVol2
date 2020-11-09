package MainPackage.Gamemodes;

import MainPackage.Player;
import MainPackage.Question;

import java.util.ArrayList;

public class PointBuilder extends Gamemode {

    @Override
    void gamemodePlay(ArrayList<Player> players, Question question) {
        System.out.println(toString(question));
        players = settingPlayersChoice(players);
        handleTheScore(players, question);
    }

    @Override
    void handleTheScore(ArrayList<Player> players, Question question) {
        for (Player player : players) {
            int indexOfChoice = handlePlayerChoice(player.getAnswer());
            if (question.getPossibleAnswersToAsk().get(indexOfChoice).equals(question.getCorrectAnswer())) {
                player.setScore(player.getScore() + 1000);
                System.out.format("CORRECT ANSWER --- Your Score is: %d\n",player.getScore());
            }else{System.out.format("WRONG ANSWER --- Your Score is: %d\n",player.getScore());}
        }
    }
}

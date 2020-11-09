package MainPackage.Gamemodes;

import MainPackage.Player;
import MainPackage.Question;

import java.util.ArrayList;

public class PointBuilder extends Gamemode {

    /**
     * In this game mode all that we are doing is we ask a question, we get a players
     * choice and then we pass it out to handle the players score
     * @param players List with all the players
     * @param question The question we are going to ask the players
     */
    @Override
    void gamemodePlay(ArrayList<Player> players, Question question) {
        System.out.println(toString(question));
        players = settingPlayersChoice(players);
        handleTheScore(players, question);
    }

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
                System.out.format("CORRECT ANSWER --- Your Score is: %d\n",player.getScore());
            }else{System.out.format("WRONG ANSWER --- Your Score is: %d\n",player.getScore());}
        }
    }
}

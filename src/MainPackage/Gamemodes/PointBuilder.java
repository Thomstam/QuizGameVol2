package MainPackage.Gamemodes;

import MainPackage.Player;

import java.util.ArrayList;

public class PointBuilder extends Gamemode {

    @Override
    void gamemodePlay(ArrayList<Player> players) {
        System.out.println(toString(theArrayListWithTheQuestionAnswers));
        players = settingPlayersChoice(players);
        handleTheScore(players);
    }

    @Override
    void handleTheScore(ArrayList<Player> players) {
        for (Player player : players) {
            int indexOfChoice = handle(player.getAnswer());
            if (theArrayListWithTheQuestionAnswers.get(indexOfChoice).equals(correctAnswer)) {
                player.setScore(player.getScore() + 1000);
            }
        }
    }
}

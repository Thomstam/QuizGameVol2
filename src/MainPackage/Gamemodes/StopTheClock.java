package MainPackage.Gamemodes;

import MainPackage.Player;
import MainPackage.Question;

import java.util.ArrayList;

public class StopTheClock extends Gamemode {

    @Override
    void handleTheScore(ArrayList<Player> players, Question question) {
        for (Player player : players) {
            for (Integer time : player.getTimeLeftFromEachAnswer()) {
                player.setScore(player.getScore() + (time * 1000 * 0.2));
            }
            player.resetArrayWithTimLeft();
        }
    }
}

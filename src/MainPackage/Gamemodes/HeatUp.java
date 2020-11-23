package MainPackage.Gamemodes;

import MainPackage.Player;
import MainPackage.Question;
import java.util.ArrayList;

public class HeatUp extends Gamemode {

    @Override
    void handleTheScore(ArrayList<Player> players, Question question) {
        for (Player player : players) {
            if (player.getScoreAchieved()){
                player.setScore(player.getScore() + 5000);
                player.setScoreAchieved(false);
            }
        }
    }
}

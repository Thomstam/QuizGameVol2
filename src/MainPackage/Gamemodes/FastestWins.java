package MainPackage.Gamemodes;

import MainPackage.Player;
import MainPackage.Question;

import java.util.ArrayList;

public class FastestWins extends Gamemode {

    @Override
    void handleTheScore(ArrayList<Player> players, Question question) {
        for (Player player: players) {
            switch (player.getPlacement()){
                case "First":
                    player.setScore(player.getScore() + 1000);
                    break;
                case "Second":
                    player.setScore(player.getScore() + 500);
            }
        }
    }
}

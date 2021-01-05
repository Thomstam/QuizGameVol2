package MainPackage.Gamemodes;

import MainPackage.Controllers.GameOptionsController;
import MainPackage.Player;
import MainPackage.Question;
import java.util.ArrayList;
import java.util.Collections;

public class HeatUp extends Gamemode {

    @Override
    public void handleTheScore(ArrayList<Player> players, Question question) {

        boolean swapped=false;
        if(players.get(1).getPlacement().equals("First")){
            Collections.swap(players,0,1);
            swapped=true;
        }

        for (Player player : players) {

            int indexOfChoice;
            if ("First".equals(player.getPlacement())) {
                indexOfChoice = handlePlayerChoice(player.getAnswer());
                if (question.getPossibleAnswersToAsk().get(indexOfChoice).equals(question.getCorrectAnswer())) {
                    player.setNumberOfCorrectAnswers(player.getNumberOfCorrectAnswers() + 1);
                    player.setIsTheAnswerCorrect(true);
                } else {
                    player.setIsTheAnswerCorrect(false);
                    GameOptionsController.userController.listOfPlayers().get(1).setPlacement("First");
                }
            }

            if(player.getNumberOfCorrectAnswers()==5){
                player.setScoreAchieved(true);
            }

            if (player.getScoreAchieved()){
                player.setScore(player.getScore() + 5000);
                player.setScoreAchieved(false);
                //player.setNumberOfCorrectAnswers(0);
            }
        }
        if(swapped){
            Collections.swap(players,0,1);
        }
    }
}

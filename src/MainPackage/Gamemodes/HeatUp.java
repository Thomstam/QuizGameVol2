package MainPackage.Gamemodes;

import MainPackage.Controllers.GameOptionsController;
import MainPackage.Player;
import MainPackage.Question;
import java.util.ArrayList;
import java.util.Collections;

public class HeatUp extends Gamemode {


    /***
     * If the last player in the list has answered first then we put him first in the list.
     * Then for each player we check his placement in the list, if he is first in the placement and his answer is right then
     * gets 1 point out of the 5 he needs to win the round.
     * If he is wrong then we set the placement of the next player on the list to "First", so that he can get the point
     * if he is right.
     * The first player to reach 5 correct answers wins the 5000 points.
     * If the players' position had been changed at the start they are put back in order.
     * @param players List with all the players.
     * @param question The current question being played.
     */
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
            }
        }
        if(swapped){
            Collections.swap(players,0,1);
        }
    }
}

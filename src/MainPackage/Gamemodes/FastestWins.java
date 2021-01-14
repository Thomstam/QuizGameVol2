package MainPackage.Gamemodes;

import MainPackage.Controllers.GameOptionsController;
import MainPackage.Player;
import MainPackage.Question;
import java.util.ArrayList;
import java.util.Collections;

public class FastestWins extends Gamemode {

    /***
     * If the last player in the list has answered first then we put him first in the list.
     * Then for each player we check his placement in the list, if he is first in the placement and his answer is right then he gets 1000 points.
     * If he is wrong then we set the placement of the next player on the list to "First", so that he can get the 1000 point
     * if he is right. If he is second and his answer is correct then he gets 500 points, else nothing happens.
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

        for (Player player: players) {
            int indexOfChoice;
            switch (player.getPlacement()){
                case "First":
                    indexOfChoice = handlePlayerChoice(player.getAnswer());
                    if (question.getPossibleAnswersToAsk().get(indexOfChoice).equals(question.getCorrectAnswer())){
                        player.setScore(player.getScore() + 1000);
                        player.setIsTheAnswerCorrect(true);
                    }else{
                        player.setIsTheAnswerCorrect(false);
                        GameOptionsController.userController.listOfPlayers().get(1).setPlacement("First");
                    }
                    break;
                case "Second":
                    indexOfChoice = handlePlayerChoice(player.getAnswer());
                    if (question.getPossibleAnswersToAsk().get(indexOfChoice).equals(question.getCorrectAnswer())){
                        player.setScore(player.getScore() + 500);
                        player.setIsTheAnswerCorrect(true);
                    }else{
                        player.setIsTheAnswerCorrect(false);
                    }
            }
        }
        if(swapped){
            Collections.swap(players,0,1);
        }
    }
}

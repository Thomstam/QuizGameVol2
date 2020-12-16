package MainPackage.Gamemodes;

import MainPackage.Controllers.GameOptionsController;
import MainPackage.Player;
import MainPackage.Question;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class FastestWins extends Gamemode {

    @Override
    void handleTheScore(ArrayList<Player> players, Question question) {
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
        if(swapped==true){
            Collections.swap(players,0,1);
        }

    }
}

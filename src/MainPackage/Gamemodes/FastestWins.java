package MainPackage.Gamemodes;

import MainPackage.Player;
import MainPackage.Question;

import java.util.ArrayList;

public class FastestWins extends Gamemode {

    @Override
    void handleTheScore(ArrayList<Player> players, Question question) {
        boolean someoneWasAlreadyCorrect= false;
        for (Player player: players) {
            int indexOfChoice;
            switch (player.getPlacement()){
                case "First":
                    indexOfChoice = handlePlayerChoice(player.getAnswer());
                    if (question.getPossibleAnswersToAsk().get(indexOfChoice).equals(question.getCorrectAnswer())){
                        player.setScore(player.getScore() + 1000);
                        player.setIsTheAnswerCorrect(true);
                        someoneWasAlreadyCorrect = true;
                    }else{
                        player.setIsTheAnswerCorrect(false);
                    }
                    break;
                case "Second":
                    if (someoneWasAlreadyCorrect){
                        indexOfChoice = handlePlayerChoice(player.getAnswer());
                        if (question.getPossibleAnswersToAsk().get(indexOfChoice).equals(question.getCorrectAnswer())){
                            player.setScore(player.getScore() + 500);
                            player.setIsTheAnswerCorrect(true);
                        }else{
                            player.setIsTheAnswerCorrect(false);
                        }
                    }else {
                        indexOfChoice = handlePlayerChoice(player.getAnswer());
                        if (question.getPossibleAnswersToAsk().get(indexOfChoice).equals(question.getCorrectAnswer())){
                            player.setScore(player.getScore() + 1000);
                            player.setIsTheAnswerCorrect(true);
                        }else{
                            player.setIsTheAnswerCorrect(false);
                        }
                    }


            }
        }
    }
}

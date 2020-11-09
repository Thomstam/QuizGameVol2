package MainPackage.Gamemodes;

import MainPackage.Player;
import MainPackage.Question;

import java.util.ArrayList;

public class Betting extends Gamemode {


    public Betting(){
        super();
        initialBet = 0;
    }

    /**
     * In this game mode we have to get a specific after the player has seen the category
     * which the question is going to be.
     * @param players List with all the players
     */
    @Override
    void gamemodePlay(ArrayList<Player> players, Question question) {
        System.out.println(categoriesToAsk);
        System.out.println("Place Your Bets Choosing Between: 250, 500, 750, 1000");
        for (Player player : players) {
            while (initialBet != 250 && initialBet != 500 && initialBet != 750 && initialBet != 1000){
                initialBet = scanner.nextInt();
            }
            player.setBet(initialBet);
            initialBet = 0;
        }
        System.out.println(toString(question));
        players = settingPlayersChoice(players);
        handleTheScore(players, question);
    }

    /**
     *
     * @param players List with all the players.
     */
    @Override
    void handleTheScore(ArrayList<Player> players, Question question) {
        for (Player player : players) {
            int indexOfChoice = handlePlayerChoice(player.getAnswer());
            if (question.getPossibleAnswersToAsk().get(indexOfChoice).equals(question.getCorrectAnswer())) {
                player.setScore(player.getScore() + player.getBet());
                System.out.format("CORRECT ANSWER --- Your Score is: %d\n",player.getScore());
            }else {
                player.setScore(player.getScore() - player.getBet());
                System.out.format("WRONG ANSWER --- Your Score is: %d\n",player.getScore());
            }
        }
    }
}

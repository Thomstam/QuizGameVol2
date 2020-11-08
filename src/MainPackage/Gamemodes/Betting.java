package MainPackage.Gamemodes;

import MainPackage.Player;

import java.util.ArrayList;

public class Betting extends Gamemode {

    public Betting(){
        super();
        initialBet = 0;
    }
    @Override
    void gamemodePlay(ArrayList<Player> players) {
        System.out.println("Place Your Bets Choosing Between: 250, 500, 750, 1000");
        for (Player player : players) {
            while (initialBet != 250 && initialBet != 500 && initialBet != 750 && initialBet != 1000){
                initialBet = scanner.nextInt();
            }
            player.setBet(initialBet);
            initialBet = 0;
        }
        System.out.println(toString(theArrayListWithTheQuestionAnswers));
        players = settingPlayersChoice(players);
        handleTheScore(players);
    }

    @Override
    void handleTheScore(ArrayList<Player> players) {
        for (Player player : players) {
            int indexOfChoice = handle(player.getAnswer());
            if (theArrayListWithTheQuestionAnswers.get(indexOfChoice).equals(correctAnswer)) {
                player.setScore(player.getScore() + player.getBet());
                System.out.format("CORRECT ANSWER --- Your Score is: %d\n",player.getScore());
            }else {
                player.setScore(player.getScore() - player.getBet());
                System.out.format("WRONG ANSWER --- Your Score is: %d\n",player.getScore());
            }
        }
    }
}

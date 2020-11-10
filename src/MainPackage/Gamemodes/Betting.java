package MainPackage.Gamemodes;

import MainPackage.Player;
import MainPackage.Question;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class Betting extends Gamemode {

    public Betting(){
        super();
        initialBet = 0;
    }

    /**
     * In this game mode we have to get a specific amount(the bet) for each player after they have seen the category
     * which the question is going to be. Players decides between our 4 choices for their bet
     * and after question answer their score changes affected by their bet.
     * @param players List with all the players
     * @param question The question we are going to ask the players
     */
    @Override
    void gamemodePlay(ArrayList<Player> players, Question question) {
        System.out.println("Place Your Bets Choosing Between: 250, 500, 750, 1000");
        for (Player player : players) {
            do {
                try {
                    initialBet = scanner.nextInt();
                    if(initialBet != 250 && initialBet != 500 && initialBet != 750 && initialBet != 1000){
                        System.out.println("Wrong Input!!!");
                    }
                }catch (InputMismatchException e){
                    System.out.println("Wrong Input!!!");
                }
                scanner.nextLine();
            }while (initialBet != 250 && initialBet != 500 && initialBet != 750 && initialBet != 1000);
            player.setBet(initialBet);
            initialBet = 0;
        }
        System.out.println(toString(question));
        players = settingPlayersChoice(players);
        handleTheScore(players, question);
    }

    /**
     * For each player we are getting his answer we and we compare it with the correct one.
     * If the player is right we are giving him points equal of his bet. Otherwise we subtract them.
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

package MainPackage;


import MainPackage.Gamemodes.*;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    private final int numberOfRounds;
    private final ArrayList<Player> players;


    public  Game(int howManyPlayers, int numberOfRounds, int numberOfQuestions, ArrayList<Player> players){

        this.numberOfRounds=numberOfRounds;
        this.players=players;
    }

    /****
     * starts the game which will be played for how many rounds the player chose and for each round
     * a different game mode is chosen randomly .
     */
    public Gamemode start(int choice){
        switch (choice){
            case 0:
                return new PointBuilder();
            case 1:
                return new StopTheClock();
            case 2:
                return new Betting();
            case 3:
                return new FastestWins();
            case 4:
                return new HeatUp();

        }

        return null;
    }
    /***
     *
     * @return a random number which corresponds to the game mode which will be played in this round
     */
    public int gamemodePicker(int bound){

        Random rand = new Random();
        return(rand.nextInt(bound));

    }
}

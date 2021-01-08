package MainPackage;


import MainPackage.Gamemodes.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
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
    //}

    /***
     * displays the number of rounds played and the final score of the player, while also
     * giving the option to play again if the player wants
     * @return false if the player wants to play again and true if the player wants to end the game
     */
    public boolean end(){

        String playAgain="";
        Scanner scanner= new Scanner(System.in);
        System.out.format("GAME ENDED AFTER %d ROUNDS!\n",numberOfRounds);
        for(Player player:players){
            System.out.format("Player %s has a final score of: %s points\n",player.getUsername(),player.getScore());
        }
        System.out.println("Would you like to play again?(Y/N)");
        do {
            try{
                playAgain=scanner.nextLine();
                if(!(playAgain.equals("Y")||playAgain.equals("N"))){
                    System.out.println("Wrong Input!!!");
                }
            }catch (InputMismatchException e){
                System.out.println("Wrong Input!!!");
            }
        }while(!(playAgain.equals("Y")||playAgain.equals("N")));
        return !playAgain.equals("Y");
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

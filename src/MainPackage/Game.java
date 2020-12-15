package MainPackage;


import MainPackage.Gamemodes.Betting;
import MainPackage.Gamemodes.Gamemode;
import MainPackage.Gamemodes.PointBuilder;


import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Game {

    private int howManyPlayers;
    private int numberOfRounds;
    private int numberOfQuestions;
    private ArrayList<Player> players;
    //private Categories categories;


    public  Game(int howManyPlayers, int numberOfRounds, int numberOfQuestions, ArrayList<Player> players){

        this.howManyPlayers=howManyPlayers;
        this.numberOfRounds=numberOfRounds;
        this.numberOfQuestions=numberOfQuestions;
        this.players=players;
        //Scanner scanner = new Scanner(System.in);
        //categories = new Categories();




//        for(int i=0;i<howManyPlayers;i++){
//
//            System.out.format("Enter Username (Player %d):",i);
//            String username = scanner.nextLine();
//            players.add(new Player(username));
//        }


    }

    /****
     * starts the game which will be played for how many rounds the player chose and for each round
     * a different game mode is chosen randomly .
     */
    public Gamemode start(int choice){
        //int choice;


        //for(int i=0;i<numberOfRounds;i++){
//            System.out.println("Press ENTER to start the round!!!");
//            try {
//                System.in.read();
//            }catch (Exception e){}
        //choice=gamemodePicker();
        switch (choice){
            case 0:
                //System.out.println("POINTBUILDER : Choose the correct answer and win 1000 points!");
                return new PointBuilder();
                //gamemode1.gamemodeSetUp(players,numberOfQuestions, categories);
            case 1:
                //System.out.println("BETTING : Win or lose the amount points you bet!");
                return new Betting();
                //gamemode2.gamemodeSetUp(players,numberOfQuestions, categories);
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
            System.out.format("Player %s has a final score of: %d points\n",player.getUsername(),player.getScore());
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
    public int gamemodePicker(){

        Random rand = new Random();
        return(rand.nextInt(2));

    }
}
